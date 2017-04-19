package com.prefengine.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.api.services.qpxExpress.model.AirportData;
import com.google.api.services.qpxExpress.model.CarrierData;
import com.google.api.services.qpxExpress.model.CityData;
import com.google.api.services.qpxExpress.model.LegInfo;
import com.google.api.services.qpxExpress.model.PricingInfo;
import com.google.api.services.qpxExpress.model.SegmentInfo;
import com.google.api.services.qpxExpress.model.SliceInfo;
import com.google.api.services.qpxExpress.model.TripOption;
import com.google.api.services.qpxExpress.model.TripsSearchResponse;
import com.prefengine.dao.FlightRecordDAO;
import com.prefengine.domain.Airport;
import com.prefengine.domain.Carriers;
import com.prefengine.domain.City;
import com.prefengine.domain.Flights;
import com.prefengine.domain.Itinerary;
import com.prefengine.domain.SearchAttributes;

import apple.laf.JRSUIConstants.Size;

public class SearchService {
	
	
	String price = null;
	float finalPrice;

	private HashMap<String, Airport> mapAirp = new HashMap<>();
	private HashMap<String, City> mapCity = new HashMap<>();
	private HashMap<String, Carriers> mapCarrier = new HashMap<>();
	
	public ArrayList<Itinerary> search(SearchCriteria sc) throws IOException, GeneralSecurityException{
		APIService apiService  = new APIService();
		ArrayList<Itinerary> result = new ArrayList<Itinerary>();
		//List<TripOptio n>  tripResults =  apiService.requestData(sc);
		TripsSearchResponse response= apiService.requestData(sc);
		List<TripOption>  tripResults =  response.getTrips().getTripOption();
		List<CityData> cityData = response.getTrips().getData().getCity();
		List<AirportData> airport = response.getTrips().getData().getAirport();
		List<CarrierData> carriers = response.getTrips().getData().getCarrier();
		FlightRecordDAO frd = new FlightRecordDAO();
		ArrayList<Itinerary> result1=null;
		
		
		
		
		
		
		for(int air = 0; air<airport.size();air++){
			Airport airp = new Airport();
			airp.setAirportCode(airport.get(air).getCode());
			airp.setAirportCity(airport.get(air).getCity());
			airp.setAirportName(airport.get(air).getName());
			mapAirp.put(airp.getAirportCode(), airp);
		}
		
		
		for(int city = 0; city<cityData.size();city++){
			City cty = new City();
			cty.setCityCode(cityData.get(city).getCode());
			cty.setCityName(cityData.get(city).getName());
			cty.setCityCountry(cityData.get(city).getCountry());
			mapCity.put(cty.getCityCode(), cty);
		}
		
		for(int carrier = 0;carrier<carriers.size();carrier++){
			Carriers carr = new Carriers();
			carr.setCarrierCode(carriers.get(carrier).getCode());
			carr.setCarrierName(carriers.get(carrier).getName());
			mapCarrier.put(carr.getCarrierCode(), carr);
		}
		
		

		for(int i=0; i<tripResults.size(); i++){
			Itinerary tr = new Itinerary();
			
			
			
			tr.setOrigin(sc.getDeparture());
			tr.setDestination(sc.getDestination());
			tr.setOriginCityName(mapCity.get(sc.getDeparture()).getCityName());
			tr.setDestinationCityName(mapCity.get(sc.getDestination()).getCityName());
			
//			System.out.println("--->>>Departure Ciry name -->> "+mapCity.get(sc.getDeparture()).getCityName());
//			System.out.println("--->>>Arrival Ciry name -->> "+mapCity.get(sc.getDestination()).getCityName());
			
			
			tr.setTripId(tripResults.get(i).getId());
//			System.out.println("\n Carrier : "+carriers.get(0).getName());
			
			List<SliceInfo> sliceInfo= tripResults.get(i).getSlice();
			for(int j=0; j<sliceInfo.size(); j++){
				tr.setTotalDuration(sliceInfo.get(j).getDuration());
				List<SegmentInfo> segInfo= sliceInfo.get(j).getSegment();
				tr.setCoach(segInfo.get(j).getCabin());
				tr.setTripCarrier(segInfo.get(j).getFlight().getCarrier());
				
				System.out.println("-----Carrier ----- > "+mapCarrier.get(segInfo.get(j).getFlight().getCarrier()).getCarrierName());
				
				tr.setCarrierName(mapCarrier.get(segInfo.get(j).getFlight().getCarrier()).getCarrierName());
				
				ArrayList<Flights> fligts = this.getFlightRecords(segInfo,response);
				
				tr.setDepartureTime(fligts.get(j).getDepartureTime());
				tr.setArrivalTime(fligts.get(segInfo.size()-1).getDepartureTime());
				tr.setNumberOfStops(fligts.size()-1);
				tr.setFlightRecord(fligts);

			}
			
			
			List<PricingInfo> priceInfo= tripResults.get(i).getPricing();
			for(int p=0; p<priceInfo.size(); p++){
				price= priceInfo.get(p).getSaleTotal();
				finalPrice = Float.parseFloat(price.substring(3));
				tr.setPrice(finalPrice);
			}
			System.out.println("=======================");
			result.add(tr);
			
		}
		frd.saveFlightRecordsBatch(result);
		try {
			result1 = frd.searchByParameters(sc);
			for(int t=0;t<result.size();t++){
				for(int r=0;r<result1.size();r++){
					if(result.get(t).getTripId().equals(result1.get(r).getTripId())){
						result1.get(r).setFlightRecord(result.get(t).getFlightRecord());
						break;
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result1; 
	}
	
	public ArrayList<Flights> getFlightRecords(List<SegmentInfo> segInfo,TripsSearchResponse response) throws GeneralSecurityException, IOException{
		ArrayList<Flights> flightRecord = new ArrayList<>();
		//APIService apiService =new APIService();
			for(int k=0; k<segInfo.size(); k++){
				Carriers c = new Carriers();
				Flights fr = new Flights();
				List<LegInfo> leg=segInfo.get(k).getLeg();
				c.setCarrierCode((segInfo.get(k).getFlight().getCarrier()));
				
				c.setCarrierName(mapCarrier.get((segInfo.get(k).getFlight().getCarrier())).getCarrierName());
				
				fr.setCarrier(c);
				fr.setFlightNumber(segInfo.get(k).getFlight().getNumber());
				
				if(segInfo.get(k).getConnectionDuration()!=null){
					fr.setLayoverDuration(segInfo.get(k).getConnectionDuration());
					
				}
				else
				{
					fr.setLayoverDuration(0);
				}
				
				for(int l=0; l<leg.size(); l++){
					Airport arrival = new Airport();
					Airport departure = new Airport();
					
					arrival.setAirportCode(leg.get(l).getDestination());
					departure.setAirportCode(leg.get(l).getOrigin());
					
					Airport airport1 = mapAirp.get(arrival.getAirportCode());
//					System.out.println("airport1 ::::::"+ airport1.getAirportCity());
//					System.out.println("---->> intermediate "+mapCity.get(mapAirp.get(arrival.getAirportCode()).getAirportCity()).getCityName());
//					System.out.println("---->> intermediate "+mapCity.get(mapAirp.get(departure.getAirportCode()).getAirportCity()).getCityName());
					
					
					
					departure.setAirportCity(mapCity.get(mapAirp.get(departure.getAirportCode()).getAirportCity()).getCityName());
					arrival.setAirportCity(mapCity.get(mapAirp.get(arrival.getAirportCode()).getAirportCity()).getCityName());
					
					departure.setAirportName(mapAirp.get(arrival.getAirportCode()).getAirportName());
					arrival.setAirportName(mapAirp.get(departure.getAirportCode()).getAirportName());
				
					fr.setAirportArrival(arrival);
					fr.setAirportDeparture(departure);
					fr.setOnAirTime(leg.get(l).getDuration());
					fr.setArrivalTime(leg.get(l).getArrivalTime());
					fr.setDepartureTime(leg.get(l).getDepartureTime());
					fr.setMiles(leg.get(l).getMileage());

					
				}
				flightRecord.add(fr);

			}
		return flightRecord;
	}
	
	public SearchAttributes getSearchAttributes(SearchCriteria sc){
		SearchAttributes attributes= new SearchAttributes();
		FlightRecordDAO frd = new FlightRecordDAO();
		try {
			attributes.setStops(frd.getRecordsByStops(sc));
			attributes.setAirlines(frd.getRecordsByAirline(sc));
			attributes.setCoach(frd.getRecordsByCabin(sc));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return attributes;
	}
}

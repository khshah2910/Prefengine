package com.prefengine.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.google.api.services.qpxExpress.model.LegInfo;
import com.google.api.services.qpxExpress.model.PricingInfo;
import com.google.api.services.qpxExpress.model.SegmentInfo;
import com.google.api.services.qpxExpress.model.SliceInfo;
import com.google.api.services.qpxExpress.model.TripOption;
import com.prefengine.dao.FlightRecordDAO;
import com.prefengine.domain.Airport;
import com.prefengine.domain.Carriers;
import com.prefengine.domain.Flights;
import com.prefengine.domain.Itinerary;

import apple.laf.JRSUIConstants.Size;

public class SearchService {
	
	
	String price = null;
	float finalPrice;

	public ArrayList<Itinerary> search(SearchCriteria sc) throws IOException, GeneralSecurityException{
		APIService apiService  = new APIService();
		ArrayList<Itinerary> result = new ArrayList<Itinerary>();
		List<TripOption>  tripResults =  apiService.requestData(sc);
		FlightRecordDAO frd = new FlightRecordDAO();
		ArrayList<Itinerary> result1=null;

		for(int i=0; i<tripResults.size(); i++){
			Itinerary tr = new Itinerary();
			tr.setOrigin(sc.getDeparture());
			tr.setDestination(sc.getDestination());
			tr.setTripId(tripResults.get(i).getId());
			
			
			List<SliceInfo> sliceInfo= tripResults.get(i).getSlice();
			for(int j=0; j<sliceInfo.size(); j++){
				tr.setTotalDuration(sliceInfo.get(j).getDuration());
				List<SegmentInfo> segInfo= sliceInfo.get(j).getSegment();
				tr.setCoach(segInfo.get(j).getCabin());
				tr.setTripCarrier(segInfo.get(j).getFlight().getCarrier());
				
				tr.setDepartureTime(this.getFlightRecords(segInfo).get(j).getDepartureTime());
				tr.setArrivalTime(this.getFlightRecords(segInfo).get(segInfo.size()-1).getDepartureTime());
				tr.setNumberOfStops(this.getFlightRecords(segInfo).size()-1);
				
				tr.setFlightRecord(this.getFlightRecords(segInfo));
			
//				System.out.println("-> Departure Time ->"+tr.getDepartureTime());
//				System.out.println("-> Arrival Time ->"+tr.getArrivalTime());
//				System.out.println("======================================");
			}
			
			
			List<PricingInfo> priceInfo= tripResults.get(i).getPricing();

			for(int p=0; p<priceInfo.size(); p++){
				price= priceInfo.get(p).getSaleTotal();
				finalPrice = Float.parseFloat(price.substring(3));
				tr.setPrice(finalPrice);
			}
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
	
	public ArrayList<Flights> getFlightRecords(List<SegmentInfo> segInfo){
		ArrayList<Flights> flightRecord = new ArrayList<>();
		
			for(int k=0; k<segInfo.size(); k++){
				Carriers c = new Carriers();
				Flights fr = new Flights();
				List<LegInfo> leg=segInfo.get(k).getLeg();
				c.setCarrierCode((segInfo.get(k).getFlight().getCarrier()));
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
	

}

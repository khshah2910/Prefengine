package com.prefengine.externalAPI;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.qpxExpress.QPXExpressRequestInitializer;
import com.google.api.services.qpxExpress.QPXExpress;
import com.google.api.services.qpxExpress.model.FlightInfo;
import com.google.api.services.qpxExpress.model.LegInfo;
import com.google.api.services.qpxExpress.model.PassengerCounts;
import com.google.api.services.qpxExpress.model.PricingInfo;
import com.google.api.services.qpxExpress.model.SegmentInfo;
import com.google.api.services.qpxExpress.model.SliceInfo;
import com.google.api.services.qpxExpress.model.TripOption;
import com.google.api.services.qpxExpress.model.TripOptionsRequest;
import com.google.api.services.qpxExpress.model.TripsSearchRequest;
import com.google.api.services.qpxExpress.model.SliceInput;
import com.google.api.services.qpxExpress.model.TimeOfDayRange;
import com.google.api.services.qpxExpress.model.TripsSearchResponse;

import com.prefengine.domain.Cabin;
import com.prefengine.domain.Passenger;
import com.prefengine.domain.Trip;
import com.prefengine.domain.UserPreferences;
import com.prefengine.service.SDF;

public class QPXExpressAPI {
	private static final String APPLICATION_NAME = "MyFlightApplication";
	private static final String API_KEY = System.getenv("API_KEY"); //Define environment variable in system 

	//Change to get more or less data
	private static int RESULTLIMIT = 20;

    /** Global instance of the HTTP transport. */
    private static HttpTransport httpTransport;

    /** Global instance of the JSON factory. */
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    public static void main(String[] args) {
		new Timestamp(System.currentTimeMillis());
    	Trip trp = new Trip(0, "BOS", "LOS", Timestamp.valueOf("2017-04-29 00:00:00"), 
    			null, 0, null, 0, 0.0, new Passenger(1,0,0), Cabin.COACH, null);
    	UserPreferences upref = new UserPreferences(trp, 0.00, 0, 0, 10, null, null, false);
    	getQPXFlights(upref);
    }
    public static void getQPXFlights(UserPreferences usrpref) {
    	try {
    		httpTransport = GoogleNetHttpTransport.newTrustedTransport();
    
    		//get passenger count --adult, child and senior. senior is attached with adult
    		PassengerCounts passengers= new PassengerCounts();
    		passengers.setAdultCount(usrpref.getTrip().getPassengers().getAdults());
    		passengers.setChildCount(usrpref.getTrip().getPassengers().getChildren());
    		passengers.setSeniorCount(usrpref.getTrip().getPassengers().getSeniors());
    		passengers.setInfantInLapCount(usrpref.getInfantInLapCount());
    		passengers.setInfantInSeatCount(usrpref.getInfantInSeatCount());

    		//work with SliceInput 
    		List<SliceInput> slices = new ArrayList<SliceInput>();
    		SliceInput slice = new SliceInput();
    		slice.setOrigin(usrpref.getTrip().getDeparture()); 
    		slice.setDestination(usrpref.getTrip().getDestination()); 
    		slice.setPreferredCabin(usrpref.getTrip().getCabin().toString());
    		//Extract date from timestamp
    		slice.setDate(usrpref.getTrip().getDepartureTime().toString().split(" ")[0]);
    		slice.setMaxStops(usrpref.getMaxStop());

    		TimeOfDayRange todr = new TimeOfDayRange();
    		todr.setEarliestTime(usrpref.getEarliestDeptTime());
    		todr.setLatestTime(usrpref.getLatestDeptTime());
    		slice.setPermittedDepartureTime(todr);

    		//add slice to slices
    		slices.add(slice);

    		//add other non-functional requests
    		TripOptionsRequest request= new TripOptionsRequest();
    		request.setSolutions(RESULTLIMIT);
    		request.setPassengers(passengers);
    		request.setSlice(slices);
    		if(usrpref.getMaxPrice()!=0.00)
    			request.setMaxPrice(Double.toString(usrpref.getMaxPrice()));           	  
    		request.setRefundable(usrpref.isRefundable());

    		//Send the request to fetch dat from google's QPX API
    		TripsSearchRequest parameters = new TripsSearchRequest();
    		parameters.setRequest(request);
    		QPXExpress qpXExpress= new QPXExpress.Builder(httpTransport, JSON_FACTORY, null)
    				.setApplicationName(APPLICATION_NAME).setGoogleClientRequestInitializer
    				(new QPXExpressRequestInitializer(API_KEY)).build();

    		//This is the json data for all flights that meet the user's current criteria. 
    		//Can be saved to avoid refetching
    		TripsSearchResponse list = qpXExpress.trips().search(parameters).execute();
    		SDF.printline(list);
    		
    		
    		List<TripOption> tripResults = list.getTrips().getTripOption();
    		System.out.println("Count of all trips found: "+tripResults.size());
    		System.out.println("Rows in list king: "+list.getKind().length());
    		System.out.println("List of all trips alone: "+ tripResults);

    		System.out.println("List contains kind and trips: "+list.size());
    		String id;


    		for(int i=0; i<tripResults.size(); i++){
    			//Trip Option ID
    			id= tripResults.get(i).getId();
    			System.out.println("id "+id);

    			//Slice
    			List<SliceInfo> sliceInfo= tripResults.get(i).getSlice();

    			for(int j=0; j<sliceInfo.size(); j++){
    				int duration= sliceInfo.get(j).getDuration();
    				System.out.println("duration "+duration);
    				List<SegmentInfo> segInfo= sliceInfo.get(j).getSegment();
    				for(int k=0; k<segInfo.size(); k++){
    					String bookingCode= segInfo.get(k).getBookingCode();

    					System.out.println("bookingCode "+bookingCode);
    					FlightInfo flightInfo=segInfo.get(k).getFlight();
    					String flightNum= flightInfo.getNumber();
    					System.out.println("flightNum "+flightNum);

    					List<LegInfo> leg=segInfo.get(k).getLeg();
    					for(int l=0; l<leg.size(); l++){
    						String aircraft= leg.get(l).getAircraft();
    						System.out.print("aircraft "+aircraft + "\n");
    						String arrivalTime= leg.get(l).getArrivalTime();
    						System.out.print("arrivalTime "+arrivalTime + "\n");
    						String departTime=leg.get(l).getDepartureTime();
    						System.out.print("departTime "+departTime + "\n");
    						String dest=leg.get(l).getDestination();
    						System.out.print("Destination "+dest + "\n");

    						String destTer= leg.get(l).getDestinationTerminal();
    						System.out.print("DestTer "+destTer + "\n");
    						String start=leg.get(l).getOrigin();
    						System.out.print("origun " + start + "\n");
    						String originTer = leg.get(l).getOriginTerminal();
    						System.out.print("OriginTer " + originTer + "\n");
    						int durationLeg = leg.get(l).getDuration();
    						System.out.print("durationleg " + durationLeg + "\n");
    						int mil = leg.get(l).getMileage();
    						System.out.print("Mileage " + mil + "\n");
    					}

    				}
    			}

    			//Pricing
    			List<PricingInfo> priceInfo= tripResults.get(i).getPricing();
    			for(int p=0; p<priceInfo.size(); p++){
    				String price= priceInfo.get(p).getSaleTotal();
    				System.out.println("Price "+price);
    			}

    		}
    		return;
    	} catch (IOException e) {
    		System.err.println(e.getMessage());
    	} catch (Throwable t) {
    		t.printStackTrace();
    	}
    	System.exit(1);
    }
}

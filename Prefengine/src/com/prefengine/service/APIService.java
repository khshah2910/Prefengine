package com.prefengine.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.services.qpxExpress.QPXExpress;
import com.google.api.services.qpxExpress.QPXExpressRequestInitializer;
import com.google.api.services.qpxExpress.model.CityData;
import com.google.api.services.qpxExpress.model.PassengerCounts;
import com.google.api.services.qpxExpress.model.SliceInput;
import com.google.api.services.qpxExpress.model.TripOption;
import com.google.api.services.qpxExpress.model.TripOptionsRequest;
import com.google.api.services.qpxExpress.model.TripsSearchRequest;
import com.google.api.services.qpxExpress.model.TripsSearchResponse;
import com.prefengine.util.Constants;

public class APIService {
	private static HttpTransport httpTransport;
	
	private TripsSearchResponse response = null;
	
	public TripsSearchResponse requestData(SearchCriteria sc) throws GeneralSecurityException, IOException{
			if(response!= null){
				return response;
			}
		
			httpTransport = GoogleNetHttpTransport.newTrustedTransport();
			PassengerCounts passengers= new PassengerCounts();
			
			passengers.setAdultCount(sc.getNumberOfPassengers());
			List<SliceInput> slices = new ArrayList<SliceInput>();
			SliceInput slice = new SliceInput();
			slice.setOrigin(sc.getDeparture()); 
			slice.setDestination(sc.getDestination()); 
			slice.setDate(sc.getDepartureDate());
			slices.add(slice);
			TripOptionsRequest request= new TripOptionsRequest();
			request.setPassengers(passengers);
			request.setSlice(slices);
			TripsSearchRequest parameters = new TripsSearchRequest();
			parameters.setRequest(request);
			QPXExpress qpXExpress= new QPXExpress.Builder(httpTransport, Constants.JSON_FACTORY, null).setApplicationName(Constants.APPLICATION_NAME)
					.setGoogleClientRequestInitializer(new QPXExpressRequestInitializer(Constants.API_KEY)).build();

			TripsSearchResponse list= qpXExpress.trips().search(parameters).execute();
			//List<TripOption> tripResults=list.getTrips().getTripOption();
		response = list;
		return list;
		
	}
	
}

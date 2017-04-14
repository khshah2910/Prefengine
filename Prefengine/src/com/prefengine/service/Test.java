package com.prefengine.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

import com.prefengine.domain.Flights;
import com.prefengine.domain.Itinerary;

public class Test {

	public static void main(String[] args) {
		SearchService sc = new SearchService();
		SearchCriteria sc1 = new SearchCriteria();
		ArrayList<Itinerary> records;
		sc1.setDeparture("BOS");
		sc1.setDestination("AMD");
		sc1.setDepartureDate("2017-04-21");
		sc1.setNumberOfPassengers(1);
		
		
		try {
			records = sc.search(sc1);
			System.out.println(records.size());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

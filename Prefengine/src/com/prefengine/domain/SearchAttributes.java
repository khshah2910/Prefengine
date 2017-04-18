package com.prefengine.domain;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchAttributes {
	private HashMap<Integer,Float> stops;
	private HashMap<String, Float> coach;
	private HashMap<String, Float> airlines;
	
	
	
	public HashMap<Integer, Float> getStops() {
		return stops;
	}
	public void setStops(HashMap<Integer, Float> stops) {
		this.stops = stops;
	}
	public HashMap<String, Float> getCoach() {
		return coach;
	}
	public void setCoach(HashMap<String, Float> coach) {
		this.coach = coach;
	}
	public HashMap<String, Float> getAirlines() {
		return airlines;
	}
	public void setAirlines(HashMap<String, Float> airlines) {
		this.airlines = airlines;
	}
	
}

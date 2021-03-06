package com.prefengine.domain;

import java.util.ArrayList;

public class Itinerary {
	private ArrayList<Flights> flightRecord = new ArrayList<Flights>();
	private String tripId;
	private float price;
	private String origin;
	private String destination;
	
	private String originCityName;
	private String destinationCityName;
	
	private float totalDuration;
	private int numberOfStops;
	public void setNumberOfStops(int numberOfStops) {
		this.numberOfStops = numberOfStops;
	}
	private double totalMiles;
	private String tripCarrier;
	private String carrierName;
	private String coach;
	private String departureTime;
	private String arrivalTime;
	
	// ==============  Satisfaction degree fields: =================
	private double price_sat_deg;
	private double stop_sat_deg;
	private double duration_sat_deg;
	private double mileage_sat_deg;
	private double flight_sat_deg;
	
	public double getPriceSatisfaction() {
		return price_sat_deg;
	}
	public void setPriceSatisfaction(double price_sat_deg) {
		this.price_sat_deg = price_sat_deg;
	}
	
	public double getStopSatisfaction() {
		return stop_sat_deg;
	}
	public void setStopSatisfaction(double stop_sat_deg) {
		this.stop_sat_deg = stop_sat_deg;
	}
	
	public double getDurationSatisfaction() {
		return duration_sat_deg;
	}
	public void setDurationSatisfaction(double duration_sat_deg) {
		this.duration_sat_deg = duration_sat_deg;
	}
	
	public double getMileageSatisfaction() {
		return mileage_sat_deg;
	}
	public void setMileageSatisfaction(double mileage_sat_deg) {
		this.mileage_sat_deg = mileage_sat_deg;
	}
	
	public double getFlightSatisfaction() {
		return flight_sat_deg;
	}
	public void setFlightSatisfaction(double flight_sat_deg) {
		this.flight_sat_deg = flight_sat_deg;
	}
	//============================================================================
	
	public String getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public ArrayList<Flights> getFlightRecord() {
		return flightRecord;
	}
	public void setFlightRecord(ArrayList<Flights> flightRecord) {
		this.flightRecord = flightRecord;
	}
	public String getTripId() {
		return tripId;
	}
	public void setTripId(String tripId) {
		this.tripId = tripId;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public float getTotalDuration() {
		return totalDuration;
	}
	public void setTotalDuration(float totalDuration) {
		this.totalDuration = totalDuration;
	}
	public double getTotalMiles() {
		return totalMiles;
	}
	public void setTotalMiles(double totalMiles) {
		this.totalMiles = totalMiles;
	}
	public String getTripCarrier() {
		return tripCarrier;
	}
	public void setTripCarrier(String tripCarrier) {
		this.tripCarrier = tripCarrier;
	}

	public int getNumberOfStops() {
		return numberOfStops;
	}
	public String getCoach() {
		return coach;
	}
	public void setCoach(String coach) {
		this.coach = coach;
	}
	public String toString(){
		return this.getTripId();
	}
	public String getOriginCityName() {
		return originCityName;
	}
	public void setOriginCityName(String originCityName) {
		this.originCityName = originCityName;
	}
	public String getDestinationCityName() {
		return destinationCityName;
	}
	public void setDestinationCityName(String destinationCityName) {
		this.destinationCityName = destinationCityName;
	}
	public String getCarrierName() {
		return carrierName;
	}
	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}
}

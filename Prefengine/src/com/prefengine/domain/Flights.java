package com.prefengine.domain;

public class Flights {
	private Airport airportDeparture;
	private Airport airportArrival;
	private String departureTime;
	private String arrivalTime;
	private int layoverDuration; 
	private int onAirTime;
	private long miles;
	private Carriers carrier;
	private String cabin;
	private String flightNumber;
	public Airport getAirportDeparture() {
		return airportDeparture;
	}
	public void setAirportDeparture(Airport airportDeparture) {
		this.airportDeparture = airportDeparture;
	}
	public Airport getAirportArrival() {
		return airportArrival;
	}
	public void setAirportArrival(Airport airportArrival) {
		this.airportArrival = airportArrival;
	}
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
	public int getLayoverDuration() {
		return layoverDuration;
	}
	public void setLayoverDuration(int layoverDuration) {
		this.layoverDuration = layoverDuration;
	}
	public int getOnAirTime() {
		return onAirTime;
	}
	public void setOnAirTime(int onAirTime) {
		this.onAirTime = onAirTime;
	}
	public long getMiles() {
		return miles;
	}
	public void setMiles(long miles) {
		this.miles = miles;
	}
	public Carriers getCarrier() {
		return carrier;
	}
	public void setCarrier(Carriers carrier) {
		this.carrier = carrier;
	}
	public String getCabin() {
		return cabin;
	}
	public void setCabin(String cabin) {
		this.cabin = cabin;
	}
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	
	

}

package com.prefengine.service;

public class SearchCriteria {
	private String departure;
	private String destination;
	private String departureDate;
	private String returnDate;
	private int stops;
	private boolean nonStop=false;
	
	private boolean oneStop=false;
	private boolean twoOrMoreStop=false;
	private int numberOfPassengers;
	private float price;
	private String cabin;
	private String travelTimePreference;
	public String getDeparture() {
		return departure;
	}
	public void setDeparture(String departure) {
		this.departure = departure;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	public int getStops() {
		return stops;
	}
	public void setStops(int stops) {
		this.stops = stops;
	}
	public int getNumberOfPassengers() {
		return numberOfPassengers;
	}
	public void setNumberOfPassengers(int numberOfPassengers) {
		this.numberOfPassengers = numberOfPassengers;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getCabin() {
		return cabin;
	}
	public void setCabin(String cabin) {
		this.cabin = cabin;
	}
	public String getTravelTimePreference() {
		return travelTimePreference;
	}
	public void setTravelTimePreference(String travelTimePreference) {
		this.travelTimePreference = travelTimePreference;
	}
	public boolean isNonStop() {
		return nonStop;
	}
	public void setNonStop(boolean nonStop) {
		this.nonStop = nonStop;
	}
	public boolean isOneStop() {
		return oneStop;
	}
	public void setOneStop(boolean oneStop) {
		this.oneStop = oneStop;
	}
	public boolean isTwoOrMoreStop() {
		return twoOrMoreStop;
	}
	public void setTwoOrMoreStop(boolean twoOrMoreStop) {
		this.twoOrMoreStop = twoOrMoreStop;
	}
}

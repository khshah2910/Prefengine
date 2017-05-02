package com.prefengine.service;

import java.util.ArrayList;

public class SearchCriteria {
	private String departure;
	private String destination;
	private String departureDate;
	private String returnDate;
	private int stops;
	private boolean nonStop=false;
	private boolean oneStop=false;
	private boolean twoOrMoreStop=false;
	private boolean economy=false;
	private boolean first=false;
	private boolean business=false;
	private int numberOfPassengers;
	private float price;
	private float minLayout;
	private float maxLayout;
	private String cabin;
	private String travelTimePreference;
	private double maxPrice;
	private double minPrice;
	private double maxDuration;
	private double minDuration;
	private double maxMileage;
	private double minMileage;
	/**  a list of airline ranking number in String type	*/
	 private ArrayList<String> rankElements;
	/** sentences describe user's description */
	private String sentence;
	// Field for non funcional attributes
	private ArrayList<String> non_functional_attributes;
	
	/**
	 * set up sentence input from UI
	 * 
	 * @param sentence
	 * 			sentences describe user's description
	 */
	public void setSentence(String sentence) {
		this.sentence = sentence;
		//System.out.println("get here!!!" + this.sentence);
	}
	/**
	 * get sentence input from UI
	 * 
	 * @return  sentences describe user's description
	 */
	public String getSentence() {
			return this.sentence ;
	}
	
	
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
	public boolean isEconomy() {
		return economy;
	}
	public void setEconomy(boolean economy) {
		this.economy = economy;
	}
	public boolean isFirst() {
		return first;
	}
	public void setFirst(boolean first) {
		this.first = first;
	}
	public boolean isBusiness() {
		return business;
	}
	public void setBusiness(boolean business) {
		this.business = business;
	}
	public double getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(double maxPrice) {
		this.maxPrice = maxPrice;
	}
	public float getMaxLayout() {
		return maxLayout;
	}
	public void setMaxLayout(float maxLayout) {
		this.maxLayout = maxLayout;
	}
	public float getMinLayout() {
		return minLayout;
	}
	public void setMinLayout(float minLayout) {
		this.minLayout = minLayout;
	}
	public double getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(double minPrice) {
		this.minPrice = minPrice;
	}
	public double getMinDuration() {
		return minDuration;
	}
	public void setMinDuration(double minDuration) {
		this.minDuration = minDuration;
	}
	public double getMaxDuration() {
		return maxDuration;
	}
	public void setMaxDuration(double maxDuration) {
		this.maxDuration = maxDuration;
	}
	public double getMinMileage() {
		return minMileage;
	}
	public void setMinMileage(double minMileage) {
		this.minMileage = minMileage;
	}
	public double getMaxMileage() {
		return maxMileage;
	}
	public void setMaxMileage(double maxMileage) {
		this.maxMileage = maxMileage;
	}
	 /**  
	  * get range of airline ranking
	  * 
	  * @return  an array-list of airline ranking number in String type
	  */
	public ArrayList<String> getRankElements()
	{
		return this.rankElements;
	}
	 
	 /**  
	  * set up range of airline ranking
	  * 
	  * @param  rankRange
	  * 			an array-list of airline ranking number in String type
	  */
	public void setRankElements(ArrayList<String> rankElements)
	{
		 this.rankElements = rankElements;
	}
	
	// Set and get for non functional attributes.
	public void setNonFunctionalAttributes(ArrayList<String> non_functional_attributes){
		this.non_functional_attributes = non_functional_attributes;
	}
	public ArrayList<String> getNonFunctionalAttributes(){
		return this.non_functional_attributes;
	}
}

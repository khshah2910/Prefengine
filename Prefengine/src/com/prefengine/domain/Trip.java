package com.prefengine.domain;

/**
 * A trip POJO that represents a single trip.
 * 
 * @author Yinka - UMB Preference Based Search Engine Team
 * 
 */
import java.io.Serializable;
import java.sql.Timestamp;

public class Trip implements Serializable,Comparable<Trip>{
	/**
	 * Serialize this class
	 */
	private static final long serialVersionUID = 1L;
	private int tripId;
	private String departure;
	private String destination;
	private Timestamp departureTime;
	private Timestamp arrivalTime;
	private double price;
	private String carrier;
	private int duration;
	private int mileage;
	private Cabin cabin;
	private String thisTrip;
	private String jsonData;
	
	/**
	 * @param tripId
	 * @param departure
	 * @param destination
	 * @param departureTime
	 * @param arrivalTime
	 * @param price
	 * @param carrier
	 * @param duration
	 * @param mileage
	 * @param cabin
	 * @param jsonData
	 */
	public Trip(int tripId, String departure, String destination, Timestamp departureTime, Timestamp arrivalTime,
			double price, String carrier, int duration, int mileage, Cabin cabin, String jsonData) {
		super();
		this.tripId = tripId;
		this.departure = departure;
		this.destination = destination;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.price = price;
		this.carrier = carrier;
		this.duration = duration;
		this.mileage = mileage;
		this.cabin = cabin;
		this.jsonData = jsonData;
		thisTrip = getDeparture() + " to " + getDestination();
	}
	//should we add this?ontimeperformance
	/**
	 * @return the departure
	 */
	public String getDeparture() {
		return departure;
	}
	/**
	 * @param departure the departure to set
	 */
	public void setDeparture(String departure) {
		this.departure = departure;
	}
	/**
	 * @return the destination
	 */
	public String getDestination() {
		return destination;
	}
	/**
	 * @param destination the destination to set
	 */
	public void setArrival(String destination) {
		this.destination = destination;
	}
	/**
	 * @return the departureTime
	 */
	public Timestamp getDepartureTime() {
		return departureTime;
	}
	/**
	 * @param departureTime the departureTime to set
	 */
	public void setDepartureTime(Timestamp departureTime) {
		this.departureTime = departureTime;
	}
	/**
	 * @return the arrivalTime
	 */
	public Timestamp getArrivalTime() {
		return arrivalTime;
	}
	/**
	 * @param arrivalTime the arrivalTime to set
	 */
	public void setArrivalTime(Timestamp arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	/**
	 * @return the carrier
	 */
	public String getCarrier() {
		return carrier;
	}
	/**
	 * @param carrier the carrier to set
	 */
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	/**
	 * @return the duration
	 */
	public int getDuration() {
		return duration;
	}
	/**
	 * @param duration the duration to set
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}
	/**
	 * @return the mileage
	 */
	public int getMileage() {
		return mileage;
	}
	/**
	 * @param mileage the mileage to set
	 */
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}
	/**
	 * @return the cabin
	 */
	public Cabin getCabin() {
		return cabin;
	}
	/**
	 * @param cabin the cabin to set
	 */
	public void setCabin(Cabin cabin) {
		this.cabin = cabin;
	}
	/**
	 * @return the tripId
	 */
	public int getTripId() {
		return tripId;
	}
	/**
	 * @return the jsonData
	 */
	public String getJsonData() {
		return jsonData;
	}
	/**
	 * @param jsonData the jsonData to set
	 */
	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}
	/**
	 * @param destination the destination to set
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}
	/*
	 * This compares users by their email address
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Trip x)
	{
		return thisTrip.compareTo(x.thisTrip);
	}
	@Override
	public boolean equals(Object x)
	{
		if (x == null || x.getClass()!= getClass())
			return false;
		return thisTrip.equals(((Trip)x).thisTrip);
	}
	@Override
	public int hashCode()
	{
		return thisTrip.hashCode();
	}
	public String toString(){
		return thisTrip + ": departs: " + getDepartureTime() + ", arrives: " + getArrivalTime()
			+ ", price: USD " + getPrice();
	}
	
}

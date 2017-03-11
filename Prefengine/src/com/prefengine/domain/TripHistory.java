package com.prefengine.domain;

/**
 * A trip History class that represents a user's previously selected trip.
 * 
 * @author Yinka - UMB Preference Based Search Engine Team
 * 
 */
public class TripHistory {
	private int historyId;
	private int tripId;
	private int userId;
	private String userPreferences;
	
	/**
	 * @param historyId
	 * @param tripId
	 * @param userId
	 */
	public TripHistory(int historyId, int tripId, int userId, String userPreferences) {
		super();
		this.historyId = historyId;
		this.tripId = tripId;
		this.userId = userId;
		this.userPreferences = userPreferences;
	}
	
	/**
	 * @return the tripId
	 */
	public int getTripId() {
		return tripId;
	}
	/**
	 * @param tripId the tripId to set
	 */
	public void setTripId(int tripId) {
		this.tripId = tripId;
	}
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * @return the historyId
	 */
	public int getHistoryId() {
		return historyId;
	}

	/**
	 * @return the userPreferences
	 */
	public String getUserPreferences() {
		return userPreferences;
	}

	/**
	 * @param userPreferences the userPreferences to set
	 */
	public void setUserPreferences(String userPreferences) {
		this.userPreferences = userPreferences;
	}
	/**
	 * Get all user's preferences
	 * It returns a string array 
	 */
	public String[] getSplitPreferences(){
		return userPreferences.split(",");
	}
	//TODO: Used to test code. Should be removed when code is live
	public static void main(String[] args){
		System.out.println("maxprice=20,minprice=10,maxmileage=123,minmileage=100".split(",").length);
		System.out.println("maxprice=20,minprice=10,maxmileage=123,minmileage=100".split(",")[0]);
	}
	
}

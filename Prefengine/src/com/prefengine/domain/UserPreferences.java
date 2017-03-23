package com.prefengine.domain;

public class UserPreferences {
	private Trip trip;
	private double maxPrice = 0.0;
	private int infantInLapCount = 0;
	private int infantInSeatCount = 0;
	private int maxStop = 5;
	private String earliestDeptTime = "";
	private String latestDeptTime = "";
	private boolean isRefundable;
	
	/**
	 * @param trip
	 * @param maxPrice
	 * @param infantInLapCount
	 * @param infantInSeatCount
	 * @param maxStop
	 * @param earliestDeptTime
	 * @param latestDeptTIme
	 * @param isRefundable
	 */
	public UserPreferences(Trip trip, double maxPrice, int infantInLapCount, int infantInSeatCount,
			int maxStop, String earliestDeptTime, String latestDeptTime, boolean isRefundable) {
		super();
		this.trip = trip;
		this.maxPrice = maxPrice;
		this.infantInLapCount = infantInLapCount;
		this.infantInSeatCount = infantInSeatCount;
		this.maxStop = maxStop;
		this.earliestDeptTime = earliestDeptTime;
		this.latestDeptTime = latestDeptTime;
		this.isRefundable = isRefundable;
	}
	/**
	 * @return the trip
	 */
	public Trip getTrip() {
		return trip;
	}
	/**
	 * @param trip the trip to set
	 */
	public void setTrip(Trip trip) {
		this.trip = trip;
	}
	/**
	 * @return the maxPrice
	 */
	public double getMaxPrice() {
		return maxPrice;
	}
	/**
	 * @param maxPrice the maxPrice to set
	 */
	public void setMaxPrice(double maxPrice) {
		this.maxPrice = maxPrice;
	}
	/**
	 * @return the infantInLapCount
	 */
	public int getInfantInLapCount() {
		return infantInLapCount;
	}
	/**
	 * @param infantInLapCount the infantInLapCount to set
	 */
	public void setInfantInLapCount(int infantInLapCount) {
		this.infantInLapCount = infantInLapCount;
	}
	/**
	 * @return the infantInSeatCount
	 */
	public int getInfantInSeatCount() {
		return infantInSeatCount;
	}
	/**
	 * @param infantInSeatCount the infantInSeatCount to set
	 */
	public void setInfantInSeatCount(int infantInSeatCount) {
		this.infantInSeatCount = infantInSeatCount;
	}
	/**
	 * @return the maxStop
	 */
	public int getMaxStop() {
		return maxStop;
	}
	/**
	 * @param maxStop the maxStop to set
	 */
	public void setMaxStop(int maxStop) {
		this.maxStop = maxStop;
	}
	/**
	 * @return the earliestDeptTime
	 */
	public String getEarliestDeptTime() {
		return earliestDeptTime;
	}
	/**
	 * @param earliestDeptTime the earliestDeptTime to set
	 */
	public void setEarliestDeptTime(String earliestDeptTime) {
		this.earliestDeptTime = earliestDeptTime;
	}
	/**
	 * @return the latestDeptTime
	 */
	public String getLatestDeptTime() {
		return latestDeptTime;
	}
	/**
	 * @param latestDeptTime the latestDeptTIme to set
	 */
	public void setLatestDeptTime(String latestDeptTime) {
		this.latestDeptTime = latestDeptTime;
	}
	/**
	 * @return the isRefundable
	 */
	public boolean isRefundable() {
		return isRefundable;
	}
	/**
	 * @param isRefundable the isRefundable to set
	 */
	public void setRefundable(boolean isRefundable) {
		this.isRefundable = isRefundable;
	}
	
	
}

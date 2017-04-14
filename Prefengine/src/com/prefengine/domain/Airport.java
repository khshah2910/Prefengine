package com.prefengine.domain;

public class Airport {
	private String AirportName;
	private String AirportCode;
	private String AirportCity;
	
	public String getAirportName() {
		return AirportName;
	}
	public void setAirportName(String airportName) {
		AirportName = airportName;
	}
	public String getAirportCode() {
		return AirportCode;
	}
	public void setAirportCode(String airportCode) {
		AirportCode = airportCode;
	}
	public String getAirportCity() {
		return AirportCity;
	}
	public void setAirportCity(String airportCity) {
		AirportCity = airportCity;
	}
	public String getAirportCountry() {
		return AirportCountry;
	}
	public void setAirportCountry(String airportCountry) {
		AirportCountry = airportCountry;
	}
	private String AirportCountry;
}

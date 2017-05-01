package com.prefengine.domain;

public enum NonFunctionalAttributes {
	PRICE("s_price"), STOPS("s_stops"), DURATION("s_duration"), MILEAGE("s_mileage");
	
	private String sqlName;
	
	NonFunctionalAttributes(String sqlName){
		this.sqlName = sqlName;
	}
	public String sqlName(){
		return sqlName;
	}
}
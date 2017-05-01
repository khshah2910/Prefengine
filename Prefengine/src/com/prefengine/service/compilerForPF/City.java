package com.prefengine.service.compilerForPF;

public /**
 *  city code will be return as a TokenGeneralKind-subclass so parser can deal with it.
 */
 class City implements TokenGeneralKind
{	
	/**  cityCode of a City instance */
	private String cityCode;
	
	/**
	 * Construct a City.   
	 * 
	 *  @param cityCode
	 *  		 cityCode of a City instance
	 */
	public City( String cityCode)
	{
		this.cityCode = cityCode;
	}
	
	/**
	 * get cityCode of a City instance.   
	 * 
	 * @return cityCode of a city instance in String type
	 */
	public String getCityCode()
	{
		return this.cityCode;
	}

	@Override
	public String getImage() {
		// TODO Auto-generated method stub
		return this.cityCode;
	}	
}

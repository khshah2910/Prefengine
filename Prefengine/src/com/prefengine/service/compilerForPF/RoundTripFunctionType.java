package com.prefengine.service.compilerForPF;

import java.util.ArrayList;

/**  
 * sub-class of FunctionType with ServiceProperty.ROUNDTRIP	
 */
public class RoundTripFunctionType extends AbstractFunctionType
{
	 /**  is round trip or not */
	 boolean isRoundTrip;
	 /**  contain the back trip request clause */
	 private  ArrayList<TokenGeneralKind> backTripRequest;
	 
	 /**  constructor for RoundTripFunctionType
	  * define single trip by default
	  * 
	  * @param serviceProperty
	  * 			basic property
	  */
	public RoundTripFunctionType(ServiceProperty serviceProperty) {
		super(serviceProperty);
		// TODO Auto-generated constructor stub
		this.isRoundTrip = false;
	}

	 /**  
	  * setup round trip
	  * 
	  * @param  isRoundTrip
	  * 			boolean to describe is round trip or not
	  */
	public void setRoundtrip(boolean isRoundTrip)
	{
		this.isRoundTrip = isRoundTrip;
	}

	 /**  
	  * setup back trip request of a round trip
	  * 
	  * @param  backTripRequest
	  * 			clause of request about back trip stored in ArrayList
	  */
	public void  setBackTripRequest( ArrayList<TokenGeneralKind> backTripRequest)
	{
		 this.backTripRequest = backTripRequest;
	}

	 /**  
	  * get back trip request of a round trip
	  * 
	  * @return  clause of request about back trip stored in ArrayList
	  */
	public ArrayList<TokenGeneralKind>  getBackTripRequest( )
	{
		 return this.backTripRequest;
	}

	 /**  
	  * get round trip
	  * 
	  * @return  boolean to describe is round trip or not
	  */
	public boolean getRoundtrip()
	{
		return this.isRoundTrip ;
	}
	
	/**  
	  * function method for this class leave blank for now 
	  * @Override
	  */
	public void functionMethod() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ServiceProperty getProperty() {
		// TODO Auto-generated method stub
		return ServiceProperty.ROUNDTRIP;
	}	 
}

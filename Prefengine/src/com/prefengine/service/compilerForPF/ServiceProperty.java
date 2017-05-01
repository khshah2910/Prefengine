package com.prefengine.service.compilerForPF;

import com.prefengine.service.compilerForPF.BasicFunctionType;

/**
 *  Define all possible service properties with simple explanation and father service-property if there is one
 */
public enum ServiceProperty implements BasicFunctionType
{
	GENERALPROPERTY("general property",null),
	ABPROPERTY("abstract property",GENERALPROPERTY),
	BPROPERTY("basic property",GENERALPROPERTY),
	REPUTATION("reputation",ABPROPERTY),
	CONVENIENT("convenient",ABPROPERTY),
	AIRPORTUTILITY("airportutility",ABPROPERTY),
	LAYOUT("layout",BPROPERTY),
	SAFETY("safety",ABPROPERTY),
	ROUNDTRIP("round trip",BPROPERTY),
	SEATCLASS("seatclass",BPROPERTY),
	COST("price",BPROPERTY),
	LANDA("leave and arrive place or time", BPROPERTY),
	DURATION("duration",BPROPERTY),
	MILEAGE("mileage",BPROPERTY),
	NOSTOP("stops",BPROPERTY),
	SERVICE("service",ABPROPERTY),
	RELIABILITY("reliability",ABPROPERTY),
	ASERVICE("after & before service",SERVICE),	
	DSERVICE("during-fly service",SERVICE),
	PACKAGERULE("package rule", DSERVICE),
	OTHER("other",GENERALPROPERTY);
	
	/** The explanation of individual service-property element  */
	private String explanation;
	
	/** The parent-property of individual service-property element  */
	private ServiceProperty parentProperty;		
	
	/**
	 * Construct a ServiceProperty.   
	 * 
	 *  @param explanation
	 *  @param parentProperty
	 *  			the parent property of this element
	 */
 private ServiceProperty(String explanation,ServiceProperty parentProperty)
	{
		this.explanation = explanation;
		this.parentProperty = parentProperty;
	}
	
	/**
	 * get explanation of this service type.    
	 * 
	 * @return explanation in String
	 */
	public String getExplanation()
	{
		return this.explanation;
	}
	
	/**
	 * get parent service-function of this service type.    
	 * 
	 * @return  ServiceProperty instance
	 */
	public ServiceProperty getParentProperty()
	{
		return this.parentProperty;
	}
	
	/**
	 * set parent service-function of this service type.    
	 * 
	 * @param  parentProperty
	 * 				ServiceProperty instance
	 */
	public void setParentProperty(ServiceProperty parentProperty)
	{
		this.parentProperty = parentProperty;
	}

	@Override
	public ServiceProperty getProperty() {
		// TODO Auto-generated method stub
		return null;
	}



//	@Override
//	public void setWeight(float fixWeight) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public float getWeight() {
//		// TODO Auto-generated method stub
//		return 1f;
//	}
	}


 

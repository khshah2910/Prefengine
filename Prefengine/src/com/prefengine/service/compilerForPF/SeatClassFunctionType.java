package com.prefengine.service.compilerForPF;

/**  
 * sub-class of FunctionType with ServiceProperty.SEATCLASS	
 */
public class SeatClassFunctionType extends AbstractFunctionType
{	
	/** seat class in String  */
	private String seatClass;
	 
	 /**  constructor for SeatClassFunctionType
	  * define economy seat class by default
	  * 
	  * @param serviceProperty
	  * 			basic property
	  */
	public SeatClassFunctionType(ServiceProperty serviceProperty) {
		super(serviceProperty);
		// TODO Auto-generated constructor stub
		this.seatClass = "COACH";
	}

	 /**  
	  * set up seat class
	  * 
	  * @param  seatClass
	  * 			String to describe seat class
	  */
	public void setSeatClass(String seatClass)
	{	this.seatClass = seatClass;
	}

	 /**  
	  * get seat class
	  * 
	  * @return  String to describe seat class
	  */
	public String getSeatClass()
	{	return this.seatClass ;
	}
	
	/**  
	  * function method for this class leave blank for now 
	  * @Override
	  */@Override
	public void functionMethod() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ServiceProperty getProperty() {
		// TODO Auto-generated method stub
		return ServiceProperty.SEATCLASS;
	}	 
}


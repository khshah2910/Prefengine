package com.prefengine.service.compilerForPF;

/**  
 * sub-class of FunctionType with ServiceProperty.DURATION	
 */
public class DurationFunctionType extends AbstractFunctionType
{
	 /** store duration in hour */
	 private float[] durationInHour;
	 
	 /** store duration in Possibility */
	 private float durationInPossibility;
	 
	 /** when user only define possibility and no specific hour number, turn true */
	 private boolean hasPossibility;
	 
	 /**  
	  * constructor for DurationFunctionType
	  * 
	  * @param serviceProperty
	  * 			basic property
	  */
	public DurationFunctionType(ServiceProperty serviceProperty) {
		
		super(serviceProperty);
		// TODO Auto-generated constructor stub
		hasPossibility = false;
	}
	
	 /**  
	  * set up duration in Possibility
	  * @param durationInPossibility
	  * 			float type			
	  */
	public void setDuationInPossibility(float durationInPossibility)
	{
		this.durationInPossibility = durationInPossibility;
		hasPossibility = true;
	}
	 /**  
	  * get if the information stored in possibility or specific hour number 
	  * @return when stored in possibility return true			
	  */
	public boolean hasPossibility()
	{
		return this.hasPossibility;
	}
	
	 /**  
	  * get duration in Possibility
	  * @return float type			
	  */
	public float getDuationInPossibility()
	{
		return this.durationInPossibility ;
	}	
	 /**  
	  * set up duration in hour
	  * @param durationInHour
	  * 			float type			
	  */
	public void setDuationInHour(float[] durationInHour)
	{
		this.durationInHour = durationInHour;
	}
	
	 /**  
	  * get duration in hour
	  * @return float type			
	  */
	public float[] getDuationInHour()
	{
		return this.durationInHour ;
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
		return ServiceProperty.DURATION;
	}	 
}


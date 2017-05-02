package com.prefengine.service.compilerForPF;

/**  
 * sub-class of FunctionType with ServiceProperty.LAYOUT	
 */
public class LayoutFunctionType extends AbstractFunctionType
{
	 /** store layout in hour */
	 private float[] layoutInHour;
	 
	 /** store layout in Possibility */
	 private float layoutInPossibility;
	 
	 /** when user only define possibility and no specific hour number, turn true */
	 private boolean hasPossibility;
	 
	 /**  
	  * constructor for LayoutFunctionType
	  * 
	  * @param serviceProperty
	  * 			basic property
	  */
	public LayoutFunctionType(ServiceProperty serviceProperty) {
		
		super(serviceProperty);
		// TODO Auto-generated constructor stub
		hasPossibility = false;
	}
	
	 /**  
	  * set up layout in Possibility
	  * @param layoutInPossibility
	  * 			float type			
	  */
	public void setLayoutInPossibility(float layoutInPossibility)
	{
		this.layoutInPossibility = layoutInPossibility;
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
	  * get layout in Possibility
	  * @return float type			
	  */
	public float getLayoutInPossibility()
	{
		return this.layoutInPossibility ;
	}	
	 /**  
	  * set up layout in hour
	  * @param layoutInHour
	  * 			float type			
	  */
	public void setLayoutInHour(float[] layoutInHour)
	{
		this.layoutInHour = layoutInHour;
	}
	
	 /**  
	  * get layout in hour
	  * @return float type			
	  */
	public float[] getLayoutInHour()
	{
		return this.layoutInHour ;
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
		return ServiceProperty.LAYOUT;
	}	 
}





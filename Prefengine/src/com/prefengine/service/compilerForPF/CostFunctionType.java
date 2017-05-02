package com.prefengine.service.compilerForPF;

/**  
 * sub-class of FunctionType with ServiceProperty.COST	
 */
public class CostFunctionType extends AbstractFunctionType
{
	 /** store maximum price and minimum price from user */
	 private float[] priceRange = new float[2];
	 
	 /** store price range in possibility */
	 private boolean hasPossibility;
	 
	 /** 
	 *  with range of [0,1f], represents the price range of all tickets' price. example: if generalPriceRequest = 0.3,
	 *  and all tickets price between [100,300], system will get tickets between [100,160] 
	 *   */
	 private float priceInPossibility;
	 
	 /**  
	  * constructor for CostFunctionType
	  * define maximum and minimum to 0; and general price range to 1f
	  * 
	  * @param serviceProperty
	  * 			basic property
	  */
	 public CostFunctionType(ServiceProperty serviceProperty) {
		super(serviceProperty);
		// TODO Auto-generated constructor stub
		this.priceRange[0] = 0f;
		this.priceRange[1] = 0f;
		this.hasPossibility = false;
	}
	 /**  
	  * get if the information stored in possibility or specific price 
	  * @return when stored in possibility return true			
	  */
	public boolean hasPossibility()
	{
		return this.hasPossibility;
	}
		/**  
		  * set up for general price range
		  * 
		  * @param priceInPossibility
		  * 			
		  */
	 public void setPriceInPossibility(float priceInPossibility)
	{	this.priceInPossibility = priceInPossibility;
	}
	 /**  
	  * get general price range
	  * 
	  * @return general Price range
	  * 			
	  */
	 public float getPriceInPossibility()
	{	return this.priceInPossibility;
	}
		
	 /**  
	  * set up maximum and minimum price range
	  * 
	  * @param priceRange
	  * 			Price range in Array type
	  * 			
	  */
	public void setPriceRange(float[] priceRange)
	{	this.priceRange = priceRange;
	}
	
	 /**  
	  * get maximum and minimum price range
	  * 
	  * @return Price range in Array type
	  * 			
	  */
	public float[] getPriceRange()
	{	return this.priceRange;
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
		return ServiceProperty.COST	;
	}	 
}

package com.prefengine.service.compilerForPF;

/**  
 * sub-class of FunctionType with ServiceProperty.COST	
 */
public class CostFunctionType extends AbstractFunctionType
{
	 /** store maximum price and minimum price from user */
	 private float[] priceRange = new float[2];

	 /** if generalPriceRequest != 0, means user only request like "cheap price" without exactly price range
	 * 
	 *  with range of [0,1f], represents the price range of all tickets' price. example: if generalPriceRequest = 0.3,
	 *  and all tickets price between [100,300], system will get tickets between [100,160] 
	 *   */
	 private float generalPriceRequest;
	 
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
		this.generalPriceRequest = 1f;
	}

		/**  
		  * set up for general price range
		  * 
		  * @param generalPriceRequest
		  * 			
		  */
	 public void setgeneralPriceRequest(float generalPriceRequest)
	{	this.generalPriceRequest = generalPriceRequest;
	}
	 /**  
	  * get general price range
	  * 
	  * @return general Price range
	  * 			
	  */
	 public float getgeneralPriceRequest()
	{	return this.generalPriceRequest;
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

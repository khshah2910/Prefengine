package com.prefengine.service.compilerForPF;

/**  
 * sub-class of FunctionType with ServiceProperty.NOSTOP	
 */
public class NumberofStopFunctionType extends AbstractFunctionType
{
	 /**  store number of stop in int type*/
	 private int numberOfStop;
	 
	 /**  
	  * constructor for NumberofStopFunctionType
	  * define number of stop to 5 as default
	  * 
	  * @param serviceProperty
	  * 			basic property
	  */
	 public NumberofStopFunctionType(ServiceProperty serviceProperty) {
		super(serviceProperty);
		// TODO Auto-generated constructor stub
		this.numberOfStop = 5;
	}
	
	 /**  
	  * get number of stop
	  * @return int type			
	  */
	public int getNumberOfStop()
	{	return this.numberOfStop;
	}
	 
	 /**  
	  * set up number of stop
	  * @return int type			
	  */
	public void setNumberOfStop(int numberOfStop)
	{	this.numberOfStop = numberOfStop;
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
		return ServiceProperty.NOSTOP;
	}	 
}

package com.prefengine.service.compilerForPF;

/**  
 * sub-class of FunctionType with ServiceProperty.RELIABILITY	
 */
public class ReliabilityFunctionType extends AbstractFunctionType
{
	 /**  
	  * constructor for ReliabilityFunctionType
	  * 
	  * @param serviceProperty
	  * 			basic property
	  */
	public ReliabilityFunctionType(ServiceProperty serviceProperty) {
		super(serviceProperty);
		// TODO Auto-generated constructor stub
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
		return ServiceProperty.RELIABILITY;
	}	 
}



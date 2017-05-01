package com.prefengine.service.compilerForPF;

/**  
 * sub-class of FunctionType with ServiceProperty.DSERVICE	
 */
public class DuringFlyServiceFunctionType extends AbstractFunctionType
{
	 /**  
	  * constructor for DuringFlyServiceFunctionType
	  * @param serviceProperty
	  * 			basic property
	  */
	public DuringFlyServiceFunctionType(ServiceProperty serviceProperty) {
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
		return ServiceProperty.DSERVICE	;
	}	 
}




package com.prefengine.service.compilerForPF;

/**  
 * sub-class of FunctionType with ServiceProperty.GENERALPROPERTY	
 */
 public class GeneralPropertyFunctionType extends AbstractFunctionType
{
	 /**  
	  * constructor inherit from parent-class
	  * 
	  * @param serviceProperty
	  * 			should be ServiceProperty.GENERALPROPERTY
	  */
	public GeneralPropertyFunctionType(ServiceProperty serviceProperty) {
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
		return ServiceProperty.GENERALPROPERTY;
	}
	
}
 
package com.prefengine.service.compilerForPF;

/**  
 * sub-class of FunctionType with ServiceProperty.PACKAGERULE	
 */
public  class PackageRuleFunctionType extends AbstractFunctionType
{
	 /**  
	  * constructor for PackageRuleFunctionType
	  * 
	  * @param serviceProperty
	  * 			basic property
	  */
	public PackageRuleFunctionType(ServiceProperty serviceProperty) {
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
		return ServiceProperty.PACKAGERULE;
	}	 
}
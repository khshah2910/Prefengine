package com.prefengine.service.compilerForPF;

import java.util.ArrayList;

/**  
 * sub-class of FunctionType with ServiceProperty.REPUTATION	
 */
public class ReputationFunctionType extends AbstractFunctionType
{
	 /**  a list of airline ranking number in String type	*/
	 private ArrayList<String> rankElements;
	 
	 /**  
	  * constructor for ReputationFunctionType
	  * 
	  * @param serviceProperty
	  * 			basic property
	  */
	public ReputationFunctionType(ServiceProperty serviceProperty) {
		super(serviceProperty);
		rankElements = new ArrayList<String> ();
		// TODO Auto-generated constructor stub
	}

	/**  
	  * function method for this class leave blank for now 
	  * @Override
	  */
	public void functionMethod() {
		// TODO Auto-generated method stub
		
	}
	 
	 /**  
	  * get range of airline ranking
	  * 
	  * @return  an array-list of airline ranking number in String type
	  */
	public ArrayList<String> getRankElements()
	{
		return this.rankElements;
	}
	 
	 /**  
	  * set up range of airline ranking
	  * 
	  * @param  rankRange
	  * 			an array-list of airline ranking number in String type
	  */
	public void setRankElements(ArrayList<String> rankElements)
	{
		 this.rankElements = rankElements;
	}

	@Override
	public ServiceProperty getProperty() {
		// TODO Auto-generated method stub
		return ServiceProperty.REPUTATION;
	} 
}


package com.prefengine.service.compilerForPF;

import com.prefengine.service.compilerForPF.BasicFunctionType;

/**
 * A abstract class as a father class of all function-type sub-classes( those sub-classes has specific do-method for individual function-type).  
 */
 public abstract class AbstractFunctionType implements BasicFunctionType{

	/**  each FunctionType sub-class instance will have a variable in  ServiceProperty to avoid unrecognized FunctionType construct-request */
	private ServiceProperty serviceProperty;

	/**  weight calculated from what adj/adv or verb contains in clauses  */
	private float weight;

	/**  define descend or ascend order on data when do Fuzzy-function:
	 *   sortMethod <0 : descent
	 *   sortMethod >0 : ascent
	 *   sortMethod = 0 : not mention and not request for this function-type
	 */
	private int sortMethod;	

	/**  
	 * constructor for FunctionType
	 * 
	 * @param serviceProperty
	 * 				take what has defined in ServiceProperty as basic-function-type
	 */
	public AbstractFunctionType(ServiceProperty serviceProperty)
	{
		this.serviceProperty = serviceProperty;
		this.sortMethod = 0;
		this.weight = 0;
	}

	/**  
	 * get the function type of the instance
	 * 
	 * @return ServiceProperty type
	 */
	abstract public ServiceProperty getProperty ();

	/**  
	 * get the sort method of the instance
	 * 
	 * @return an int type
	 */
	public int getSortMethod ()
	{
		return this.sortMethod;
	}

	/**  
	 * set the sort method of the instance
	 * 
	 * @param sortMethod
	 * 				in int type
	 */
	public void setSortMethod(int sortMethod)
	{	this.sortMethod = sortMethod;
	}

	/**  
	 * set up the weight of the instance
	 * 
	 * @param weight
	 * 			in float type
	 */
	public void setWeight(float weight)
	{	this.weight = weight;
	}

	/**  
	 * get the weight of the instance
	 * 
	 * @return  float type
	 */
	public float getWeight()
	{	return this.weight ;
	}

	/**  
	 * abstract method to be rewrite by each sub-class to define individual execute method 		
	 */
	public abstract void functionMethod();
}


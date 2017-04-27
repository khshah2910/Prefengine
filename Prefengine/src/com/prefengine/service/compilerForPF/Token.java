package com.prefengine.service.compilerForPF;

/**
 *  All recognizable words, number and marks will be in Token type. 
 */
public class Token implements TokenGeneralKind{
	
	/**  representation of tokens in literal   */
	private String image;
	
	/**  core-word of words or number type  */
	private TokenGeneralKind coreMeaning;
	
	/**  word property or number property   */
	private PropertyType property;
	
	/**  multiple weight of word   */
	private float weight;
	
	/**  function type of word or number   */
	private BasicFunctionType functionType;
	
	/**
	 * Construct a Token instance from word.    
	 * 
	 * @param image
	 * 			image of word
	 * @param coreMeaning
	 * 			core-word of word
	 * @param property
	 * 			property of word
	 * @param weight
	 * 			multiple weight of word
	 * @param functionType
	 * 			function type of word
	 */
	public Token(String image,TokenGeneralKind coreMeaning,PropertyType property,
			float weight,BasicFunctionType functionType)
	{	this.image = image;
		this.coreMeaning = coreMeaning;
		this.property = property;
		this.weight = weight;
		this.functionType = functionType;
	}
	
	/**
	 * Construct a Token instance from number or mark.    
	 * 
	 * @param image
	 * 			image of number or mark
	 * @param coreMeaning
	 * 			CoreMeaning.NUMBERTYPE for numbers or SpecialToken for marks
	 * @param property
	 * 			property of number or mark
	 */
	public Token(String image,TokenGeneralKind coreMeaning,PropertyType property)
	{	this.image = image;
		this.coreMeaning = coreMeaning;
		this.property = property;
		this.weight = 1;
		this.functionType = null;		
	}
	
	/**
	 * Set up image for a Token instance.    
	 * 
	 * @param image
	 * 			image of number or mark
	 */
	public void setImage(String image)
	{
		this.image = image;
	}
	
	/**
	 * Set up TokenGeneralKind type as core-meaning for a Token instance.    
	 * 
	 * @param coreMeaning
	 * 			coreMeaning for word, number or mark
	 */
	public void setCoreMeaning(TokenGeneralKind coreMeaning)
	{
		this.coreMeaning = coreMeaning;
	}
	
	/**
	 * Set up property type as core-meaning for a Token instance.    
	 * 
	 * @param property
	 * 			property for word, number or mark
	 */
	public void setProperty(PropertyType property)
	{
		this.property = property;
	}
	
	/**
	 * Set up weight for a Token instance.    
	 * 
	 * @param weight
	 * 			weight for word
	 */
	public void setWeight(float weight)
	{
		this.weight = weight;
	}
	
	/**
	 * Set up BasicFunctionType type as functionType for a Token instance.    
	 * 
	 * @param functionType
	 * 			functionType for word, number or mark
	 */
	public void setFunctionType(BasicFunctionType functionType)
	{
		this.functionType = functionType;
	}
	
	/**
	 * get image for a Token instance.    
	 * 
	 * @return image in String
	 */
	public String getImage()
	{
		return this.image ;
	}
	
	/**
	 * get  core-meaning for a Token instance.    
	 * 
	 * @return coreMeaning as TokenGeneralKind type
	 */
	public TokenGeneralKind getCoreMeaning()
	{
		return this.coreMeaning;
	}
	
	/**
	 * get PropertyType for a Token instance.    
	 * 
	 * @return property type in PropertyType
	 */
	public PropertyType getProperty()
	{
		return this.property;
	}
	
	/**
	 * get weight for a Token instance.    
	 * 
	 * @return weight in float
	 */
	public float getWeight()
	{
		return this.weight ;
	}
	
	/**
	 * get BasicFunctionType for a Token instance.    
	 * 
	 * @return function type
	 */
	public BasicFunctionType getFunctionType()
	{
		return this.functionType;
	}

}

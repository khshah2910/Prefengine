package com.prefengine.service.compilerForPF;

/**
 *  An interface of words' property
 */
interface PropertyType
{}

/**
 *  general list of words' property with simple explanation.
 */
enum Properties implements PropertyType
{
	NOUN("meaningfulnoun"),
	//MLWORD("meaningless word"),
	VERB("verb"),
	NUMBER("number"),
	PNOUN("propernoun"),
	MONTH("month name"),
	CCONJ("clause conjunction"),
	SPREP("still preposition"),
	RPREP("reverse preposition"),
	//DETE("determiner"),
	//EXCL("exclamation"),
	ADJV("adjv"),
	FURTHERFIX("furtherfix");
	
	/** Define explanation of property */
	private String explanation;
	
	/**
	 * Construct a Properties.   
	 * 
	 *  @param explanation
	 *  		 store simple explanation 
	 */
	private Properties(String explanation)
	{
		this.explanation = explanation;
	}
	
	/**
	 * get explanation.   
	 * 
	 *  @return  store simple explanation 
	 */
	public String getExplanation()
	{
		return this.explanation;
	}
}

/**
 *  numbers' property .
 */
enum NumberProperties implements PropertyType
{
	DATENUMBER,REGULARNUMBER,TIMENUMBER,MONEYNUMBER;
}

/**
 * define basic weight range of CoreMeaning
 */
enum WeightOriginalRange 
{
	POSITIVESUPREME(2f),
	POSITIVEENHANCE(1.5f),
	POSITIVESTABLE(1.1f),
	POSITIVESLACK(0.5f),
	STABLE(0),
	NEGATIVESLACK(-0.5f),
	NEGATIVESTABLE(-1.1f),
	NEGATIVEENHANCE(-1.5f),
	NEGATIVESUPREME(-2f),
	USELESS(1f);
	 
	/** float weight of WeightOriginalRange instance */
	private float weight;
	
	/**
	 * Construct a WeightOriginalRange.   
	 * 
	 *  @param weight
	 */
	private WeightOriginalRange(float weight)
	{
		this.weight = weight;
	}
	
	/**
	 * get weight value of each element
	 *  @return weight in float
	 */
	public float getWeight()
	{
		return this.weight;
	}
}
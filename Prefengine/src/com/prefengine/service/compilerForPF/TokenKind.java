package com.prefengine.service.compilerForPF;

import java.util.HashMap;
import java.util.Map;

/**
 *  An interface of token kind, the general kind of mark, word or Arabic number
 */
 interface TokenGeneralKind
{
	 /**
	  *  get image of token
	  *  @return image in String type
	  */
	 public String getImage();
}

 

 /**
  * define connective operator type
  */
 enum ConnectiveOperator 
 {
	 //the FURTHERFIX type should not be used but to make the system more robust. just in case some unknown error occurs
	 AND("AND"),OR("OR"),COMPROMISE("COMPROMISE"),FURTHERFIX("?");
	 
	/** image of connective operator */
	 private String image;
	 
		/**
		 * Construct a ConnectiveOperator.   
		 * 
		 *  @param image
		 *  		 image of connective operator
		 */
	 private ConnectiveOperator (String image)
	 {
		 this.image = image;
	 }
	
		/**
		 * get image connective operator
		 * 
		 * @return image in String type
		 */
	 public String getImage()
	 {
		 return this.image;
	 }
 }
 
 /**
  *  define meaningful marks for airline booking service.
  */
enum SpecialToken implements TokenGeneralKind
{
	COLON(":"),COMMA(","),SEMI(";"),PERIOD("."),QUESTION("?"),NUMBERMARK("#"),EXCLAMATION("!"),PERCENT("%"),
	SINGLEQUOTE("'"),GREATERTHAN(">"),SMALLERTHAN("<"),EQUAL("="),DOLLAR("$"),AT("@"),OR("|"),DIVIDE("/"),WAVE("~"),
	AND("&"),PARAMETER("("),PARAMETERCLOSE(")");
	
	/** image of marks */
	private String image;
	
	/**
	 * Construct a SpecialToken.   
	 * 
	 *  @param image
	 *  		 image of marks
	 */
	private SpecialToken(String image)
	{
		this.image = image;
	}
	
	/**
	 * get image of a mark.   
	 * 
	 * @return image of a mark in String type
	 */
	public String getImage()
	{
		return this.image;
	}	
}

/**
 *  unrecognized token will be return all in this type.
 */
class UnrecognizeToken implements TokenGeneralKind
{	
	/** image of unrecognized token */
	private String image;
	
	/**
	 * Construct a UnrecognizeToken.   
	 * 
	 *  @param image
	 *  		 image of unrecognized token
	 */
	public UnrecognizeToken( String image)
	{
		this.image = image;
	}
	
	/**
	 * get image of a unrecognized token.   
	 * 
	 * @return image of a unrecognized token in String type
	 */
	public String getImage()
	{
		return this.image;
	}	
}



/**
 *  airline code will be return as a TokenGeneralKind-subclass so parser can deal with it.
 */
class AirLine implements TokenGeneralKind
{	
	/**  airlineCode of a AirLine instance */
	private String airlineCode;
	
	/**
	 * Construct a AirLine.   
	 * 
	 *  @param airlineCode
	 *  		 airlineCode of a AirLine instance
	 */
	public AirLine( String airlineCode)
	{
		this.airlineCode = airlineCode;
	}
	
	/**
	 * get airlineCode of a AirLine instance.   
	 * 
	 * @return airlineCode of a AirLine instance in String type
	 */
	public String getAirlineCode()
	{
		return this.airlineCode;
	}
	
	/**
	 * set up airlineCode of a AirLine instance.   
	 * 
	 * @param airlineCode of a AirLine instance in String type
	 */
	public void setAirlineCode( String airlineCode )
	{
		this.airlineCode = airlineCode;
	}

	@Override
	public String getImage() {
		// TODO Auto-generated method stub
		return this.airlineCode;
	}	
}

/**
 *  airline code will be return as a TokenGeneralKind-subclass so parser can deal with it.
 */
class RankOfAirLine implements TokenGeneralKind
{	
	/**  airlineCode of a AirLine instance */
	private String rank;
	
	/**
	 * Construct a RankOfAirLine.   
	 * 
	 *  @param rank
	 *  		 rank of a RankOfAirLine instance
	 */
	public RankOfAirLine( String rank)
	{
		this.rank = rank;
	}
	
	/**
	 * get rank of a RankOfAirLine instance.   
	 * 
	 * @return rank of a RankOfAirLine instance in String type
	 */
	public String getRankOfAirLine()
	{
		return this.rank;
	}

	@Override
	public String getImage() {
		// TODO Auto-generated method stub
		return this.rank;
	}	
}
/**
 *  all core-word stores here.
 */
interface CoreMeaning extends TokenGeneralKind
{	

	 /**  
	  * get image of CoreMeaning instance
	  * 
	  * @return image in String type
	  */
	public String getImage();

	 /**  
	  * get basic property of CoreMeaning instance
	  * 
	  * @return basic property in Properties type
	  */
	public Properties getBasicProperty();
	
	 /**  
	  * get basic weight of CoreMeaning instance
	  * 
	  * @return basic weight in WeightOriginalRange type
	  */
	public WeightOriginalRange getWeight();
	
	 /**  
	  * get service function type of CoreMeaning instance
	  * 
	  * @return service function type in ServiceProperty type
	  */
	public ServiceProperty getBasicFunctionType();
	
	 /**  
	  * get multiple weight of CoreMeaning instance
	  * 
	  * @return multiple weight in float type
	  */
	public float getMultipleWeight();
	
	 /**  
	  * set up multiple weight of CoreMeaning instance
	  * 
	  * @param multipleweight
	  * 			 multiple weight in float type
	  */
	public void setMultipleWeight(float multipleweight);
	
	 /**  
	  * set up basic property type of CoreMeaning instance
	  * 
	  * @return basicProperty
	  * 			basic property in Properties type
	  */
	public void setBasicProperty(Properties basicProperty);
	
	 /**  
	  * set up service function type of CoreMeaning instance
	  * 
	  * @return basicFunctiontype
	  * 			service function type in ServiceProperty type
	  */
	public void setBasicFunctionType(ServiceProperty basicFunctiontype);
	 /**  
	  * set up basic weight of CoreMeaning instance
	  * 
	  * @return weight
	  * 			basic weight in WeightOriginalRange type
	  */
	public void setWeight(WeightOriginalRange weight);
	}
enum CoreMeaningTwo implements CoreMeaning
{
//	
//Letter P starts here
//
	PACE("pace",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PACK("pack",Properties.VERB,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	PACKAGE("package",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	PAGE("page",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PAIN("pain",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	PAINT("paint",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.USELESS),
	PAIR("pair",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PALACE("palace",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PALE("pale",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESLACK),
	PAN("pan",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PANEL("panel",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PANTS("pants",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PAPER("paper",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PARALLEL("parallel",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	PARENT("parent",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PARK("park",Properties.NOUN,ServiceProperty.OTHER,WeightOriginalRange.POSITIVESTABLE),
	PARTICIPAT("participat",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PARTICULAR("particular",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	PART("part",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PARTLY("partly",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	PARTNER("partner",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PARTY("party",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PASS("pass",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PASSAGE("passage",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PASSION("passion",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	PASSPORT("passport",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	PAST("past",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	PATH("path",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PATIENT("patient",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	PATTERN("pattern",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PAUSE("pause",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PAY("pay",Properties.VERB,ServiceProperty.COST,WeightOriginalRange.POSITIVESTABLE),
	PEAR("pear",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	PEACE("peace",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PEAK("peak",Properties.NOUN,ServiceProperty.OTHER,WeightOriginalRange.POSITIVESTABLE),
	PEN("pen",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PENALTY("penalty",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.NEGATIVESTABLE),
	PENCIL("pencil",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PEPPER("pepper",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	PEOPLE("people",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PER("per",Properties.SPREP,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.STABLE),
	PERCENT("percent",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.STABLE),
	PERCENTAGE("percentage",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	PERFECT("perfect",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESUPREME),
	PERFORM("perform",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PERFORMANCE("performance",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PERHAPS("perhaps",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	PERIOD("period",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PERMANENT("permanent",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESUPREME),
	PERMIT("permit",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVEENHANCE),
	PERMISS("permiss",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVEENHANCE),
	PERSON("person",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PERSPECTIVE("perspective",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PERSUAD("persuad",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PET("pet",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	PHAS("phas",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PHONE("phone",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	PHOTO("photo",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PHOTOCOPY("photocopy",Properties.NOUN,ServiceProperty.ASERVICE,WeightOriginalRange.POSITIVESTABLE),
	PHOTOGRAPH("photograph",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PHRAS("phras",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PHYSICAL("physical",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PIANO("piano",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	PICK("pick",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PICTURE("picture",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PIG("pig",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	PILE("pile",Properties.VERB,ServiceProperty.DSERVICE,WeightOriginalRange.NEGATIVESTABLE),
	PILL("pill",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVEENHANCE),
	PILOT("pilot",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	PIN("pin",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PIP("pip",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	PITCH("pitch",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PLACE("place",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PLAIN("plain",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	PLAN("plan",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PLANE("plane",Properties.NOUN,ServiceProperty.BPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	PLANET("planet",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PLANT("plant",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	PLATFORM("platform",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	PLAY("play",Properties.VERB,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.USELESS),
	PLEASANT("pleasant",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	PLEAS("pleas",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	PLEASURE("pleasure",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	PLENTY("plenty",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	PLOT("plot",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PLUG("plug",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PLUS("plus",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PM("pm",Properties.NOUN,ServiceProperty.DURATION,WeightOriginalRange.POSITIVESTABLE),
	POCKET("pocket",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	POEM("poem",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	POETRY("poetry",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	POINT("point",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	POISON("poison",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVEENHANCE),
	POLE("pole",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	POLICE("police",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVEENHANCE),
	POLICY("policy",Properties.NOUN,ServiceProperty.ABPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	POLISH("polish",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	POLITE("polite",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	POLITIC("politic",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	POLLUT("pollut",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVEENHANCE),
	POOL("pool",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	POOR("poor",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	POP("pop",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	POPCORN("popcorn",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	POPULAR("popular",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	POPULATION("population",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PORT("port",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	POS("pos",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	POSITION("position",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	POSITIVE("positive",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	POSSESS("possess",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	POSSIBL("possibl",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	POST("post",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.STABLE),
	POSTOFFICE("postoffice",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.STABLE),
	POT("pot",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	POTATO("potato",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	POTENT("potent",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	POUR("pour",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	POVERTY("poverty",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	POWDER("powder",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	POWER("power",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	PRACTIC("practic",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	PRAIS("prais",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVEENHANCE),
	PRECIS("precis",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	PREDICT("predict",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	PREFER("prefer",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	PREGNANT("pregnant",Properties.ADJV,ServiceProperty.OTHER,WeightOriginalRange.POSITIVESTABLE),
	PREGNANCY("pregnancy",Properties.NOUN,ServiceProperty.OTHER,WeightOriginalRange.POSITIVESTABLE),
	PREMIUM("premium",Properties.ADJV,ServiceProperty.SEATCLASS,WeightOriginalRange.POSITIVESTABLE),
	PREMIS("premis",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	PREPARAT("preparat",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	PRESENT("present",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	PRESERV("preserv",Properties.VERB,ServiceProperty.ASERVICE,WeightOriginalRange.POSITIVESTABLE),
	PRESENTATION("presentation",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	PRESIDENT("president",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PRESS("press",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PRESSURE("pressure",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PRESUM("presum",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	PRESUMP("presump",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	PRETEND("pretend",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PRETTY("pretty",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	PREVENT("prevent",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	PREVIOUS("previous",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	PRIC("pric",Properties.NOUN,ServiceProperty.COST,WeightOriginalRange.POSITIVESTABLE),
	PRID("prid",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	PREIST("priest",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PRIMARY("primary",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESUPREME),
	PRIME("prime",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESUPREME),
	PRINCE("prince",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	PRINCESS("princess",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	PRINCIPAL("principal",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESUPREME),
	PRINCIPLE("principle",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVEENHANCE),
	PRINT("print",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PRIOR("prior",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	PRISON("prison",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.NEGATIVESTABLE),
	PRIVATE("private",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	PRIZ("priz",Properties.NOUN,ServiceProperty.COST,WeightOriginalRange.POSITIVESLACK),
	PROBABL("probabl",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	PROBABIL("probabil",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	PROBLEM("problem",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	PROCEDURE("procedure",Properties.NOUN,ServiceProperty.SERVICE,WeightOriginalRange.POSITIVESTABLE),
	PROFILE("profile",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	PROFIT("profit",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PROGRESS("progress",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	PROJECT("project",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PROJECTIL("projectil",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PROMIS("promis",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	PROMOT("promot",Properties.VERB,ServiceProperty.COST,WeightOriginalRange.POSITIVESLACK),
	PROMPT("prompt",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	PRONOUNC("pronounc",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	PRONUNCIAT("pronunciat",Properties.NOUN,ServiceProperty.SERVICE,WeightOriginalRange.POSITIVESTABLE),
	PROOF("proof",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PROPER("proper",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	PROPERTY("property",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PROPERTION("proportion",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PROPORTIONAT("proportionat",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PROPOS("propos",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	PROSPECT("prospect",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	PROTECT("protect",Properties.VERB,ServiceProperty.SAFETY,WeightOriginalRange.POSITIVESTABLE),
	PROTEST("protest",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.NEGATIVESTABLE),
	PROUD("proud",Properties.ADJV,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVEENHANCE),
	PROV("prov",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PROVID("provid",Properties.VERB,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	PUBLIC("public",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	PUBLICATION("publication",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	PUBLICITY("publicity",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	PUBLICIZ("publiciz",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	PUBLISH("publish",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PULL("pull",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PUNCH("punch",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PUNISH("punish",Properties.VERB,ServiceProperty.OTHER,WeightOriginalRange.POSITIVESTABLE),
	PURE("pure",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	PURCHAS("purchas",Properties.VERB,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	PURPLE("purple",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PURPOS("purpos",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	PUSH("push",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	PUT("put",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
//	
//Letter Q starts here
//
	QUALIFICATION("qualification",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVEENHANCE),
	QUALIFY("qualify",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	QUALITY("quality",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	QUANTITY("quantity",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	QUARTER("quarter",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	QUEEN("queen",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	QUESTION("question",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	QUICK("quick",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	QUIET("quiet",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	QUOT("quot",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
//	
//Letter R starts here
//
	RAC("rac",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	RACCOON("raccoon",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	RADIO("radio",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	RAIL("rail",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	RAILROAD("railroad",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	RAIN("rain",Properties.VERB,ServiceProperty.SAFETY,WeightOriginalRange.NEGATIVESLACK),
	RAIS("rais",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	RANG("rang",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	RANK("rank",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	RAPID("rapid",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	RARE("rare",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	RAT("rat",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	RATE("rate",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	RATHER("rather",Properties.RPREP,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	RAW("raw",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	RE("re",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	REACH("reach",Properties.VERB,ServiceProperty.LANDA,WeightOriginalRange.POSITIVESTABLE),
	REACT("react",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	READ("read",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	READY("ready",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	REAL("real",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	REALISTIC("realistic",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	REALITY("reality",Properties.NOUN,ServiceProperty.RELIABILITY,WeightOriginalRange.POSITIVESTABLE),
	REALIZ("realiz",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	REAR("rear",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	REASON("reason",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	RECALL("recall",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	RECANT("recant",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	RECEIPT("receipt",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	RECEIV("receiv",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	RECENT("recent",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	RECEPTION("reception",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	RECOGNITION("recognition",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVEENHANCE),
	RECOGNIZ("recogniz",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	RECOMMEND("recommend",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVEENHANCE),
	RECORD("record",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	RECOVER("recover",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	RED("red",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	REDUC("reduc",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	REDUCTION("reduction",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	REFER("refer",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	REFERENCE("referenc",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	REFLECT("reflect",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	REFORM("reform",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	REFRIGERATOR("refrigerator",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	REFUS("refus",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	REGARD("regard",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	REGION("region",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	REGISTER("register",Properties.VERB,ServiceProperty.ASERVICE,WeightOriginalRange.POSITIVESTABLE),
	REGRET("regret",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	REGULAR("regular",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	REGULAT("regulat",Properties.VERB,ServiceProperty.RELIABILITY,WeightOriginalRange.POSITIVEENHANCE),
	REJECT("reject",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	RELAT("relat",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	RELATIVE("relative",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	RELAX("relax",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	RELEAS("releas",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	RELEVANT("relevant",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	RELIEF("relief",Properties.NOUN,ServiceProperty.ASERVICE,WeightOriginalRange.POSITIVEENHANCE),
	RELIG("relig",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	RELY("rely",Properties.VERB,ServiceProperty.RELIABILITY,WeightOriginalRange.POSITIVEENHANCE),
	REMAIN("remain",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	REMARK("remark",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	REMEMBER("remember",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	REMIND("remind",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	REMOT("remot",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.NEGATIVESLACK),
	REMOV("remov",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	RENT("rent",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	REPAIR("repair",Properties.VERB,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	REPEAT("repeat",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	REPLAC("replac",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	REPLY("reply",Properties.VERB,ServiceProperty.SERVICE,WeightOriginalRange.POSITIVESTABLE),
	REPORT("report",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	REPRESENT("represent",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	REPRODUC("reproduc",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	REPUTATION("reputation",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	REQUEST("request",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	REQUIR("requir",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	RESCU("rescu",Properties.VERB,ServiceProperty.SAFETY,WeightOriginalRange.POSITIVEENHANCE),
	RESEARCH("research",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	RESERV("reserv",Properties.VERB,ServiceProperty.SERVICE,WeightOriginalRange.POSITIVESTABLE),
	RESIDENT("resident",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	RESIST("resist",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	RESOLV("resolv",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	RESORT("resort",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	RESOURC("resourc",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	RESPECT("respect",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVEENHANCE),
	RESPOND("respond",Properties.VERB,ServiceProperty.SERVICE,WeightOriginalRange.POSITIVESTABLE),
	RESPONS("respons",Properties.VERB,ServiceProperty.SERVICE,WeightOriginalRange.POSITIVESTABLE),
	REST("rest",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	RESTAURANT("restaurant",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	RESTOR("restor",Properties.VERB,ServiceProperty.RELIABILITY,WeightOriginalRange.POSITIVESTABLE),
	RESTRICT("restrict",Properties.VERB,ServiceProperty.OTHER,WeightOriginalRange.STABLE),
	RESULT("result",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	RETAIN("retain",Properties.VERB,ServiceProperty.COST,WeightOriginalRange.POSITIVEENHANCE),
	RETIR("retir",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	RETURN("return",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	REVEAL("reveal",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	REVERS("revers",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	REVIEW("review",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	REVIS("revis",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	REVOLUTION("revolution",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	REWARD("reward",Properties.VERB,ServiceProperty.COST,WeightOriginalRange.POSITIVESLACK),
	RHYTHM("rhythm",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	RICE("rice",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	RICH("rich",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	RID("rid",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	RIDE("ride",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	RIDICULOUS("ridiculous",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVEENHANCE),
	RIDING("riding",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	RIGHT("right",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	RING("ring",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.STABLE),
	RIS("ris",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	RISK("risk",Properties.NOUN,ServiceProperty.SAFETY,WeightOriginalRange.NEGATIVESTABLE),
	RIVAL("rival",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	RIVER("river",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	ROAD("road",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	ROB("rob",Properties.VERB,ServiceProperty.SAFETY,WeightOriginalRange.NEGATIVEENHANCE),
	ROCK("rock",Properties.VERB,ServiceProperty.CONVENIENT,WeightOriginalRange.NEGATIVESTABLE),
	ROLE("role",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	ROLL("roll",Properties.VERB,ServiceProperty.CONVENIENT,WeightOriginalRange.NEGATIVESTABLE),
	ROMANTIC("romantic",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	ROOF("roof",Properties.VERB,ServiceProperty.SAFETY,WeightOriginalRange.POSITIVESTABLE),
	ROOM("room",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	ROOT("root",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	ROOTBEER("rootbeer",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	ROPE("rope",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	ROSE("rose",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.STABLE),
	ROUGH("rough",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESLACK),
	ROUND("round",Properties.ADJV,ServiceProperty.ROUNDTRIP,WeightOriginalRange.STABLE),
	ROUNDTRIP("round",Properties.ADJV,ServiceProperty.ROUNDTRIP,WeightOriginalRange.STABLE),
	ROUT("rout",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	ROUTINE("routine",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	ROW("row",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	ROYAL("royal",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	RUB("rub",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	RUBBER("rubber",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	RUDE("rude",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	RUIN("ruin",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	RUL("rul",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	RULER("ruler",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	RUMOR("rumor",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.NEGATIVESLACK),
	RUN("run",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	RURAL("rural",Properties.ADJV,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.NEGATIVESLACK),
	RUSH("rush",Properties.VERB,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.NEGATIVESTABLE),
//	
//Letter S starts here
//	
	SACK("sack",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SAD("sad",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESLACK),
	SAFE("safe",Properties.ADJV,ServiceProperty.SAFETY,WeightOriginalRange.POSITIVESTABLE),
	SAFETY("safety",Properties.NOUN,ServiceProperty.SAFETY,WeightOriginalRange.POSITIVESTABLE),
	SAIL("sail",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SALAD("salad",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	SALARY("salary",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SAL("sal",Properties.VERB,ServiceProperty.COST,WeightOriginalRange.POSITIVESLACK),
	SALT("salt",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	SAME("same",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	SAMPLE("sample",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SAND("sand",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	SATISFACTION("satisfaction",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	SATISFACTIC("satisfactic",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	SATISFY("satisfy",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	SAUCE("sauce",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	SAV("sav",Properties.VERB,ServiceProperty.SAFETY,WeightOriginalRange.POSITIVESTABLE),
	SAY("say",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SCAL("scal",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	SCAR("scar",Properties.VERB,ServiceProperty.SAFETY,WeightOriginalRange.NEGATIVESTABLE),
	SCENE("scene",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	SCHEDULE("schedule",Properties.NOUN,ServiceProperty.BPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	SCHOOL("school",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SCIENCE("science",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	SCIENTIST("scientist",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	SCIENTIFI ("scientific",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	SCISSORS("scissors",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SCOR("scor",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SCRATCH("scratch",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SCREAM("scream",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SCREEN("screen",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SCREW("screw",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	SEA("sea",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	SEAL("seal",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	SEARCH("search",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SEASON("season",Properties.ADJV,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	SEAT("seat",Properties.NOUN,ServiceProperty.SEATCLASS,WeightOriginalRange.STABLE),
	SECOND("second",Properties.NUMBER,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	SECONDARY("secondary",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	SECRET("secret",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SECRETARY("secretary",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SECT("sect",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SECUR("secur",Properties.NOUN,ServiceProperty.SAFETY,WeightOriginalRange.POSITIVESTABLE),
	SEE("see",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SEED("seed",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	SEEK("seek",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SEEM("seem",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SELECT("select",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SELF("self",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SELL("sell",Properties.VERB,ServiceProperty.COST,WeightOriginalRange.POSITIVESLACK),
	SEMESTER("semester",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SENAT("senat",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SEND("send",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SENS("sens",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	SENIOR("senior",Properties.ADJV,ServiceProperty.SERVICE,WeightOriginalRange.POSITIVEENHANCE),
	SENSITIV("sensitiv",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	SENTENCE("sentence",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SEPARAT("separat",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SERIES("series",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	SERIOUS("serious",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	SERVANT("servant",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	SERV("serv",Properties.VERB,ServiceProperty.SERVICE,WeightOriginalRange.POSITIVESTABLE),
	SERVIC("servic",Properties.NOUN,ServiceProperty.SERVICE,WeightOriginalRange.POSITIVESTABLE),
	SESSION("session",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SET("set",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SETTL("settl",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SEVERE("severe",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	SEW("sew",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SEX("sex",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	SEXUAL("sexual",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SHAD("shad",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SHADOW("shadow",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SHAK("shak",Properties.VERB,ServiceProperty.CONVENIENT,WeightOriginalRange.NEGATIVESTABLE),
	SHALL("shall",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SHALLOW("shallow",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	SHAM("sham",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.NEGATIVESTABLE),
	SHAP("shap",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SHAR("shar",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SHARP("sharp",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	SHAV("shav",Properties.VERB,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	SHE("she",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SHEEP("sheep",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	SHEET("sheet",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SHELF("shelf",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SHELL("shell",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	SHELTER("shelter",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	SHIFT("shift",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESLACK),
	SHIN("shin",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	SHIP("ship",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	SHIRT("shirt",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.STABLE),
	SHOCK("shock",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVEENHANCE),
	SHOE("shoe",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.STABLE),
	SHOOT("shoot",Properties.VERB,ServiceProperty.SAFETY,WeightOriginalRange.NEGATIVESUPREME),
	SHOP("shop",Properties.VERB,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	SHOPPING("shopping",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	SHORT("short",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	SHOULD("should",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SHOT("shot",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	SHOUT("shout",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	SHOW("show",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SHOWER("shower",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	SHUT("shut",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESLACK),
	SHY("shy",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	SICK("sick",Properties.ADJV,ServiceProperty.OTHER,WeightOriginalRange.POSITIVESTABLE),
	SID("sid",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	SIDEWAYS("sideways",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SIGHT("sight",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SIGN("sign",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SIGNAL("signal",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	SIGNATURE("signature",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SIGNIFICANT("significant",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	SILENT("silent",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SILK("silk",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	SILLY("silly",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	SILVER("silver",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	SIMILAR("similar",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	SIMPLE("simple",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	SINCE("since",Properties.SPREP,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	SINCERE("sincere",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	SING("sing",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SINGL("singl",Properties.ADJV,ServiceProperty.ROUNDTRIP,WeightOriginalRange.POSITIVESLACK),
	ONEWAY("oneway",Properties.ADJV,ServiceProperty.ROUNDTRIP,WeightOriginalRange.POSITIVESLACK),
	SINK("sink",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SIR("sir",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SISTER("sister",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SIT("sit",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SITE("site",Properties.VERB,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	SITUATION("situation",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SIZ("siz",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SKILL("skill",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	SKIN("skin",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	SKIRT("skirt",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.STABLE),
	SKY("sky",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SLEEP("sleep",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SLEEEVE("sleeve",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SLIC("slic",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SLID("slid",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	SLIGHT("slight",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	SLIP("slip",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	SLOP("slop",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SLOW("slow",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	SMALL("small",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	SMART("smart",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	SMASH("smash",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	SMELL("smell",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	SMIL("smil",Properties.VERB,ServiceProperty.SERVICE,WeightOriginalRange.POSITIVESTABLE),
	SMOK("smok",Properties.VERB,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	SMOOTH("smooth",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	SNAKE("snake",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	SNOW("snow",Properties.VERB,ServiceProperty.RELIABILITY,WeightOriginalRange.POSITIVESTABLE),
	SO("so",Properties.CCONJ,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	SOAP("soap",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SOCIAL("social",Properties.ADJV,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	SOCIETY("society",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SOCK("sock",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.STABLE),
	SOFT("soft",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	SOFTWARE("software",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	SOIL("soil",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	SOLDIER("soldier",Properties.NOUN,ServiceProperty.SAFETY,WeightOriginalRange.POSITIVESTABLE),
	SOLID("solid",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	SOLUTION("solution",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SOLV("solv",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SOME("some",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	SOMEBODY("somebody",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SOMEHOW("somehow",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SOMETHING("something",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SOMEWHAT("somewhat",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	SOMEWHERE("somewhere",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SON("son",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SONG("song",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SOON("soon",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	SORE("sore",Properties.ADJV,ServiceProperty.DSERVICE,WeightOriginalRange.NEGATIVESLACK),
	SORRY("sorry",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	SORT("sort",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SOUL("soul",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	SOUND("sound",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SOUP("soup",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	SOUR("sour",Properties.ADJV,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	SOURC("sourc",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SOUTH("south",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	SOUTHERN("southern",Properties.VERB,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	SPAC("spac",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	SPAR("spar",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESLACK),
	SPEAK("speak",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SPECIAL("special",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	SPECIES("species",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	SPECIFIC("specific",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	SPEECH("speech",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SPEED("speed",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SPELL("spell",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SPEND("spend",Properties.VERB,ServiceProperty.COST,WeightOriginalRange.POSITIVESTABLE),
	SPICE("spice",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	SPICY("spicy",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	SPIDER("spider",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	SPILL("spill",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SPIN("spin",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SPIRIT("spirit",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SPIT("spit",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVEENHANCE),
	SPLIT("split",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SPOIL("spoil",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	SPOKEN("spoken",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SPOON("spoon",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SPORT("sport",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	SPOT("spot",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.NEGATIVESTABLE),
	SPRAY("spray",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SPREAD("spread",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SPRING("spring",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	SQUARE("square",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	SQUEEZ("squeez",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	STABLE("stable",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	STAFF("staff",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	STAGE("stage",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	STAIR("stair",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	STAMP("stamp",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	STAND("stand",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	STANDARD("standard",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	STAR("star",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVEENHANCE),
	STAT("stat",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	STATUE("statue",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.STABLE),
	STATUS("status",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	STAY("stay",Properties.VERB,ServiceProperty.NOSTOP,WeightOriginalRange.STABLE),
	STEADY("steady",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	STEAL("steal",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.NEGATIVESTABLE),
	STEAM("steam",Properties.VERB,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	STEEL("steel",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	STEEP("steep",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESUPREME),
	STEER("steer",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	STEP("step",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	STICK("stick",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	STIFF("stiff",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	STILL("still",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	STING("sting",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	STIR("stir",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	STOCK("stock",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	STOMACH("stomach",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVEENHANCE),
	STONE("stone",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	STOP("stop",Properties.VERB,ServiceProperty.NOSTOP,WeightOriginalRange.STABLE),
	SUMMARY("summary",Properties.CCONJ,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	SUMMER("summer",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	SUPERIOR("superior",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESUPREME),
	SUPERMARKET("supermarket",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	SUPPLY("supply",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SUPPORT("support",Properties.VERB,ServiceProperty.RELIABILITY,WeightOriginalRange.POSITIVESTABLE),
	SUPPOS("suppos",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SURE("sure",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	SURFAC("surfac",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SURGERY("surgery",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVEENHANCE),
	SURPRIS("surpris",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	SURROUND("surround",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SURVEY("survey",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SUSPECT("suspect",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.NEGATIVESTABLE),
	SURVIV("surviv",Properties.VERB,ServiceProperty.SAFETY,WeightOriginalRange.POSITIVESTABLE),
	SUSPIC("suspic",Properties.ADJV,ServiceProperty.REPUTATION,WeightOriginalRange.NEGATIVESTABLE),
	SWALLOW("swallow",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SWEAR("swear",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SWEAT("sweat",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SWEATER("sweater",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.STABLE),
	SWEEP("sweep",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SWEET("sweet",Properties.ADJV,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	SWELL("swell",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	SWIM("swim",Properties.VERB,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	SWIMMINGPOOL("swimmingpool",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	SWING("swing",Properties.VERB,ServiceProperty.RELIABILITY,WeightOriginalRange.NEGATIVESLACK),
	SWITH("swith",Properties.VERB,ServiceProperty.RELIABILITY,WeightOriginalRange.NEGATIVESLACK),
	SWOLLEN("swollen",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	SYMBOL("symbol",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SYMPATHETIC("sympathetic",Properties.ADJV,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVEENHANCE),
	SYMPATH("sympath",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVEENHANCE),
	SYSTEM("system",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	STOR("stor",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	STORM("storm",Properties.NOUN,ServiceProperty.RELIABILITY,WeightOriginalRange.NEGATIVESTABLE),
	STORY("story",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	STOV("stov",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	STAIGHT("straight",Properties.ADJV,ServiceProperty.NOSTOP,WeightOriginalRange.POSITIVESTABLE),
	STRAIN("strain",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	START("start",Properties.VERB,ServiceProperty.LANDA,WeightOriginalRange.NEGATIVESTABLE),
	STRANG("strang",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESLACK),
	STATEGY("strategy",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	STREAM("stream",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	STREET("street",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	STRENGTH("strength",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	STRESS("stress",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	STRETCH("stretch",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	STRICT("strict",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	STRIK("strik",Properties.VERB,ServiceProperty.RELIABILITY,WeightOriginalRange.NEGATIVESTABLE),
	STRING("string",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	STRIP("strip",Properties.VERB,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVEENHANCE),
	STRIPE("stripe",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	STROK("strok",Properties.VERB,ServiceProperty.DSERVICE,WeightOriginalRange.NEGATIVESTABLE),
	STRONG("strong",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	STRUCT("struct",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	STUDENT("student",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	STUDIO("studio",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	STUDY("study",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	STUFF("stuff",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	STUPID("stupid",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	STYL("styl",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	SUBJECT("subject",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SUBSTANCE("substance",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SUBSTANTIAL("substantial",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	SUBSTITUT("substitut",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	SUCCEED("succeed",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	SUCCESS("success",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVEENHANCE),
	SUCH("such",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SUCK("suck",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESLACK),
	SUDDEN("sudden",Properties.ADJV,ServiceProperty.RELIABILITY,WeightOriginalRange.NEGATIVESLACK),
	SUFFER("suffer",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.NEGATIVEENHANCE),
	SUFFICIENT("sufficient",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	SUGAR("sugar",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	SUGEST("sugest",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	SUIT("suit",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.STABLE),
	SUM("sum",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
//	
//Letter T starts here
//
	TABLE("table",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	TABLET("tablet",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	TACKL("tackl",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	TAIL("tail",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	TAK("tak",Properties.VERB,ServiceProperty.PACKAGERULE,WeightOriginalRange.USELESS),
	TALE("tale",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	TALENT("talent",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	TALK("talk",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	TALL("tall",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	TANK("tank",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	TAP("tap",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	TAPE("tape",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	TARGET("target",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	TASK("task",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	TAST("tast",Properties.VERB,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	TAX("tax",Properties.NOUN,ServiceProperty.COST,WeightOriginalRange.POSITIVESTABLE),
	TAXI("taxi",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	TEA("tea",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	TEACH("teach",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	TEAM("team",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	TEAR("tear",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	TACHNIC("technic",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	TECHIQUE("techique",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	TECHNOLOGY("technology",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	TELEPHONE("telephone",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	TELEVISION("television",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	TELL("tell",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	TEMPERATURE("temperature",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	TEMPORARY("temporary",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	TEND("tend",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	TENDENCY("tendency",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	TENSION("tension",Properties.VERB,ServiceProperty.SAFETY,WeightOriginalRange.NEGATIVESLACK),
	TENT("tent",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	TERM("term",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	TERRIBL("terribl",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVEENHANCE),
	TERRITORY("territory",Properties.NOUN,ServiceProperty.SAFETY,WeightOriginalRange.NEGATIVESUPREME),
	TERROR("terror",Properties.NOUN,ServiceProperty.SAFETY,WeightOriginalRange.NEGATIVESUPREME),
	TEST("test",Properties.VERB,ServiceProperty.RELIABILITY,WeightOriginalRange.POSITIVESTABLE),
	TEXT("text",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	THAN("than",Properties.RPREP,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	THANK("thank",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	THAT("that",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	THE("the",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	THEATER("theater",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	THEIR("their",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	THEM("them",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	THEME("theme",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	THEMSELF("themself",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	THEN("then",Properties.CCONJ,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	THEORY("theory",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	THERE("there",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	THEREFORE("therefore",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	THEY("they",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	THICK("thick",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	THIEF("thief",Properties.NOUN,ServiceProperty.SAFETY,WeightOriginalRange.NEGATIVESLACK),
	THIN("thin",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	THINK("think",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	THIRSTY("thirsty",Properties.ADJV,ServiceProperty.DSERVICE,WeightOriginalRange.NEGATIVESTABLE),
	THIS("this",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	THOROUGH("thorough",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESUPREME),
	THOUGH("though",Properties.RPREP,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	THOUGHT("thought",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	THREAD("thread",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	THREAT("threat",Properties.NOUN,ServiceProperty.SAFETY,WeightOriginalRange.NEGATIVEENHANCE),
	THROAT("throat",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVEENHANCE),
	THROUGH("through",Properties.SPREP,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	THROUGHOUT("throughout",Properties.SPREP,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	THROW("throw",Properties.VERB,ServiceProperty.PACKAGERULE,WeightOriginalRange.NEGATIVESTABLE),
	THUMB("thumb",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	THUS("thus",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	TICKEET("ticket",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	TIE("tie",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.STABLE),
	TIGHT("tight",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	TILL("till",Properties.CCONJ,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	TIME("time",Properties.NOUN,ServiceProperty.LANDA,WeightOriginalRange.POSITIVEENHANCE),
	TIMETABLE("timetable",Properties.NOUN,ServiceProperty.DURATION,WeightOriginalRange.POSITIVESTABLE),
	TIN("tin",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	TINY("tiny",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	TIP("tip",Properties.NOUN,ServiceProperty.COST,WeightOriginalRange.POSITIVESTABLE),
	TIR("tir",Properties.VERB,ServiceProperty.DSERVICE,WeightOriginalRange.NEGATIVESTABLE),
	TIRED("tired",Properties.ADJV,ServiceProperty.DSERVICE,WeightOriginalRange.NEGATIVESTABLE),
	TITL("titl",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	TO("to",Properties.SPREP,ServiceProperty.LANDA,WeightOriginalRange.POSITIVESTABLE),
	PRICETO("priceto",Properties.SPREP,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	TODAY("today",Properties.NOUN,ServiceProperty.LANDA,WeightOriginalRange.POSITIVEENHANCE),
	TOE("toe",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	TOGETHER("together",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.STABLE),
	TOILET("toilet",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	TOMATO("tomato",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	TOMORROW("tomorrow",Properties.NOUN,ServiceProperty.LANDA,WeightOriginalRange.POSITIVEENHANCE),
	TON("ton",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	TONE("tone",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	TONGUE("tongue",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	TONIGHT("tonight",Properties.NOUN,ServiceProperty.LANDA,WeightOriginalRange.POSITIVEENHANCE),
	TOO("too",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	TOOL("tool",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	TOOTH("tooth",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	TOP("top",Properties.ADJV,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESUPREME),
	TOPIC("topic",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	TOTAL("total",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	TOUCH("touch",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	TOUGH("tough",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESLACK),
	TOUR("tour",Properties.NOUN,ServiceProperty.SERVICE,WeightOriginalRange.POSITIVESTABLE),
	TOWARD("toward",Properties.SPREP,ServiceProperty.LANDA,WeightOriginalRange.POSITIVESTABLE),
	TOWEL("towel",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	TOWER("tower",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	TOWN("town",Properties.NOUN,ServiceProperty.CONVENIENT,WeightOriginalRange.POSITIVESTABLE),
	TOY("toy",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	TRAC("trac",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	TRACK("track",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	TRAD("trad",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	TRADITION("tradition",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	TRAFFIC("traffic",Properties.NOUN,ServiceProperty.CONVENIENT,WeightOriginalRange.NEGATIVESTABLE),
	TRAIN("train",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	TRANSFER("transfer",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	TRANSFORM("transform",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	TRANSIT("transit",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	TRANSLAT("translat",Properties.VERB,ServiceProperty.SERVICE,WeightOriginalRange.POSITIVESTABLE),
	TRANSPARENT("transparent",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	TRANSPORT("transport",Properties.VERB,ServiceProperty.CONVENIENT,WeightOriginalRange.POSITIVESTABLE),
	TRAP("trap",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.NEGATIVEENHANCE),
	TRASH("trash",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	TRAVEL("travel",Properties.VERB,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	TREAT("treat",Properties.VERB,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	TREE("tree",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	TREND("trend",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	TRIAL("trial",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	TRIANGLE("triangle",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	TRICK("trick",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.NEGATIVESTABLE),
	TRIP("trip",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	TROOP("troop",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	TROPIC("tropic",Properties.ADJV,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	TROUBLE("trouble",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.NEGATIVESTABLE),
	TRUCK("truck",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	TRUE("true",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	TRUST("trust",Properties.VERB,ServiceProperty.RELIABILITY,WeightOriginalRange.POSITIVESTABLE),
	TRY("try",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	TEETH("teeth",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVEENHANCE),
	TUB("tub",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	TUNE("tune",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	TUNNEL("tunnel",Properties.NOUN,ServiceProperty.CONVENIENT,WeightOriginalRange.POSITIVESTABLE),
	TURN("turn",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	TV("tv",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.STABLE),
	TWICE("twice",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	TWIST("twist",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	TWIN("twin",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	TY("typ",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	TYPIC("typic",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
/////////////////
// letter U starts here
/////////////////	
	ULTIMATE("ultimate",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESUPREME),
	UMBRELLA("umbrella",Properties.NOUN,ServiceProperty.CONVENIENT,WeightOriginalRange.POSITIVESTABLE),
	UNCLE("uncle",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	UNDER("under",Properties.SPREP,ServiceProperty.COST,WeightOriginalRange.NEGATIVESTABLE),
	UNDERNEATH("underneath",Properties.SPREP,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	UNDERSTAND("understand",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	UNDERWEAR("underwear",Properties.NOUN,ServiceProperty.CONVENIENT,WeightOriginalRange.POSITIVESTABLE),
	UNIFORM("uniform",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	UNION("union",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	UNIQUE("unique",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESUPREME),
	UNIT("unit",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	UNITE("unite",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	UNITED("united",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	UNIVERS("univers",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	UNLESS("unless",Properties.RPREP,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	UNTIL("until",Properties.RPREP,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	UP("up",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	UPON("upon",Properties.SPREP,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	UPSET("upset",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESLACK),
	UPSIDE("upside",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	UPSTAIR("upstair",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	UPWARD("upward",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	URBAN("urban",Properties.ADJV,ServiceProperty.CONVENIENT,WeightOriginalRange.POSITIVEENHANCE),
	URG("urg",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	URGENT("urgent",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	US("us",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	UGLY("ugly",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	USE("use",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	USED("used",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESLACK),
	USEFUL("useful",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	USELESS("useless",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	USUAL("usual",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
/////////////////
//letter v starts here
/////////////////
	VACATION("vacation",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	VALID("valid",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	VALLEY("valley",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	VALU("valu",Properties.VERB,ServiceProperty.COST,WeightOriginalRange.POSITIVESTABLE),
	VAN("van",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESUPREME),
	VARIAT("variat",Properties.NOUN,ServiceProperty.RELIABILITY,WeightOriginalRange.NEGATIVESTABLE),
	VARI("vari",Properties.VERB,ServiceProperty.RELIABILITY,WeightOriginalRange.NEGATIVESTABLE),
	VARIETY("variety",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVEENHANCE),
	VARIOUS("various",Properties.VERB,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVEENHANCE),
	VAST("vast",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	VEGETABLE("vegetable",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	VEHICLE("vehicle",Properties.NOUN,ServiceProperty.CONVENIENT,WeightOriginalRange.POSITIVESTABLE),
	VENTURE("venture",Properties.VERB,ServiceProperty.RELIABILITY,WeightOriginalRange.NEGATIVESLACK),
	VERSION("version",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	VERTIC("vertic",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESUPREME),
	VERY("very",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	VIA("via",Properties.SPREP,ServiceProperty.LANDA,WeightOriginalRange.POSITIVESTABLE),
	VIABLE("viable",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	VICTIM("victim",Properties.NOUN,ServiceProperty.SAFETY,WeightOriginalRange.NEGATIVESTABLE),
	VICTORY("victory",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	VIDEO("video",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	VIEW("view",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	VIOLENT("violent",Properties.ADJV,ServiceProperty.SAFETY,WeightOriginalRange.NEGATIVEENHANCE),
	VIRTUAL("virtual",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	VIRUS("virus",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	VISIBLE("visible",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	VISION("vision",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	VISIT("visit",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	VITAL("vital",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	VOCABULARY("vocabulary",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	VOICE("voice",Properties.NOUN,ServiceProperty.SERVICE,WeightOriginalRange.POSITIVESTABLE),
	VOLUME("volume",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	VOT("vot",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	WAGE("wage",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	WAIST("waist",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	WAIT("wait",Properties.VERB,ServiceProperty.CONVENIENT,WeightOriginalRange.NEGATIVESLACK),
	WAK("wak",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	WALK("walk",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	WALL("wall",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	WALLET("wallet",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	WANDER("wander",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	WANT("want",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	WAR("war",Properties.NOUN,ServiceProperty.SAFETY,WeightOriginalRange.NEGATIVESUPREME),
	WARM("warm",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	WARMTH("warmth",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	WARN("warn",Properties.VERB,ServiceProperty.SAFETY,WeightOriginalRange.POSITIVESTABLE),
	WASH("wash",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	WAST("wast",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	WATCH("watch",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	WATER("water",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	WAV("wav",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	WAY("way",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	WE("we",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	WEAK("weak",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	WEALTH("wealth",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	WEAPON("weapon",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	WEAR("wear",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	WEB("web",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	WEBSITE("website",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	WEDDING("wedding",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	WEEK("week",Properties.NOUN,ServiceProperty.DURATION,WeightOriginalRange.POSITIVESTABLE),
	WEEKDAY("weekday",Properties.NOUN,ServiceProperty.LANDA,WeightOriginalRange.POSITIVEENHANCE),
	WEEKEND("weekend",Properties.NOUN,ServiceProperty.LANDA,WeightOriginalRange.POSITIVEENHANCE),
	WEIGH("weigh",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	WEIRD("weird",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	WELCOM("welcom",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	WELL("well",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	WELLKNOWN("wellknown",Properties.ADJV,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVEENHANCE),
	WEST("west",Properties.ADJV,ServiceProperty.CONVENIENT,WeightOriginalRange.POSITIVESTABLE),
	WESTERN("western",Properties.ADJV,ServiceProperty.CONVENIENT,WeightOriginalRange.POSITIVESTABLE),
	WET("wet",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	WHAT("what",Properties.SPREP,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	WHATEVER("whatever",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	WHEEL("wheel",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	WHEELCHAIR("wheelchair",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	WHEN("when",Properties.SPREP,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	WHENEVER("whenever",Properties.SPREP,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	WHERE("where",Properties.SPREP,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	WHEREAS("whereas",Properties.RPREP,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	WHEREVER("wherever",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	WHETHER("whether",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	WHICH("which",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	WHILE("while",Properties.CCONJ,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	WHISP("whisp",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	WHISTL("whistl",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	WHO("who",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	WHITE("white",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	WHOEVER("whoever",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	WHOM("whom",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	WHOSE("whose",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	WHY("why",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	WIDE("wide",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	WIDTH("width",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	WIFE("wife",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	WILD("wild",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESLACK),
	WILL("will",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	WIN("win",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	WIND("wind",Properties.NOUN,ServiceProperty.RELIABILITY,WeightOriginalRange.NEGATIVESLACK),
	WINDOW("window",Properties.NOUN,ServiceProperty.BPROPERTY,WeightOriginalRange.STABLE),
	WON("won",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	WINE("wine",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	WING("wing",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	WINTER("winter",Properties.NOUN,ServiceProperty.RELIABILITY,WeightOriginalRange.STABLE),
	WIRE("wire",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	WISE("wise",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	WISEDOM("wisdom",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	WISH("wish",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	WITH("with",Properties.CCONJ,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	WITHDRAW("withdraw",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	WITHIN("within",Properties.SPREP,ServiceProperty.COST,WeightOriginalRange.NEGATIVESTABLE),
	WITHOUT("without",Properties.RPREP,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	WITNESS("witness",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	WOMAN("woman",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	WONDER("wonder",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	WOOD("wood",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	WOODEN("wooden",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	WOOL("wool",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	WORD("word",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	WORK("work",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	WORLD("world",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	WORRY("worry",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	WORSE("worse",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVEENHANCE),
	WORSHIP("worship",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESUPREME),
	WORST("worst",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESUPREME),
	WORTH("worth",Properties.ADJV,ServiceProperty.COST,WeightOriginalRange.POSITIVESTABLE),
	WOULD("would",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	WOUND("wound",Properties.VERB,ServiceProperty.SAFETY,WeightOriginalRange.NEGATIVESTABLE),
	WRAP("wrap",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	WRIST("wrist",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	WRIT("writ",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	WRONG("wrong",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	YARD("yard",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	YAWN("yawn",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	YEAH("yeah",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	YEAR("year",Properties.NOUN,ServiceProperty.DURATION,WeightOriginalRange.POSITIVESTABLE),
	YELLOW("yellow",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	YELL("yell",Properties.VERB,ServiceProperty.SERVICE,WeightOriginalRange.NEGATIVESTABLE),
	YES("yes",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	YESTERDAY("yesterday",Properties.NOUN,ServiceProperty.DURATION,WeightOriginalRange.POSITIVESTABLE),
	YET("yet",Properties.CCONJ,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	YOU("you",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	YOUNG("young",Properties.ADJV,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVEENHANCE),
	YOUR("your",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	YOURS("yours",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	YOURSELF("yourself",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	YOUTH("youth",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVEENHANCE),
	ZONE("zone",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	ZOO("zoo",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	ZEBRA("zebra",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	ZIPPO("zippo",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE);
	
	
	/**  image of core-word*/
	private String image;
	
	/**  basic property of core-word in type of Properties */
	private Properties basicProperty;
	
	/**  basic function meaning of core-word, will not change in the future*/
	private ServiceProperty basicFunctiontype;
	
	/**  basic weight of core-word */
	private WeightOriginalRange weight;
	
	/**  if core-word is special meaningful in number like number, month, will has multiple weight instead of basic weight   */
	private float multipleweight;

	 /**  
	  * constructor for non-number CoreMeaning
	  * 
	  * @param image
	  * @param basicProperty
	  * @param basicFunctiontype
	  * @param weight
	  * 		basic weight
	  * 		multiple weight is defined as the same with basic weight
	  */
	private CoreMeaningTwo(String image,Properties basicProperty,ServiceProperty basicFunctiontype,WeightOriginalRange weight)
	{
		this.image = image;
		this.basicProperty=basicProperty;
		this.basicFunctiontype=basicFunctiontype;
		this.weight = weight;
		this.multipleweight = weight.getWeight();
	}

	 /**  
	  * constructor for number CoreMeaning
	  * 
	  * @param image
	  * @param basicProperty
	  * @param basicFunctiontype
	  * @param weight
	  * 		multiple weight is defined as the same with basic weight
	  * 		no basic weight is declared here, define it in WeightOriginalRange.POSITIVESTABLE by default.
	  */
	private CoreMeaningTwo(String image,Properties basicProperty,ServiceProperty basicFunctiontype,float weight)
	{
		this.image = image;
		this.basicProperty=basicProperty;
		this.basicFunctiontype=basicFunctiontype;
		this.weight = WeightOriginalRange.POSITIVESTABLE;
		this.multipleweight = weight;
	}

	 /**  
	  * get image of CoreMeaning instance
	  * 
	  * @return image in String type
	  */
	public String getImage()
	{
		return this.image;
	}

	 /**  
	  * get basic property of CoreMeaning instance
	  * 
	  * @return basic property in Properties type
	  */
	public Properties getBasicProperty()
	{
		return this.basicProperty;
	}
	
	 /**  
	  * get basic weight of CoreMeaning instance
	  * 
	  * @return basic weight in WeightOriginalRange type
	  */
	public WeightOriginalRange getWeight()
	{
		return this.weight;
	}
	
	 /**  
	  * get service function type of CoreMeaning instance
	  * 
	  * @return service function type in ServiceProperty type
	  */
	public ServiceProperty getBasicFunctionType()
	{
		return this.basicFunctiontype;
	}
	
	 /**  
	  * get multiple weight of CoreMeaning instance
	  * 
	  * @return multiple weight in float type
	  */
	public float getMultipleWeight()
	{
		return this.multipleweight;
	}
	
	 /**  
	  * set up multiple weight of CoreMeaning instance
	  * 
	  * @param multipleweight
	  * 			 multiple weight in float type
	  */
	public void setMultipleWeight(float multipleweight)
	{
		this.multipleweight = multipleweight;
	}
	
	 /**  
	  * set up basic property type of CoreMeaning instance
	  * 
	  * @return basicProperty
	  * 			basic property in Properties type
	  */
	public void setBasicProperty(Properties basicProperty)
	{
		this.basicProperty=basicProperty;
	}
	
	 /**  
	  * set up service function type of CoreMeaning instance
	  * 
	  * @return basicFunctiontype
	  * 			service function type in ServiceProperty type
	  */
	public void setBasicFunctionType(ServiceProperty basicFunctiontype)
	{
		this.basicFunctiontype=basicFunctiontype;
	}
	
	 /**  
	  * set up basic weight of CoreMeaning instance
	  * 
	  * @return weight
	  * 			basic weight in WeightOriginalRange type
	  */
	public void setWeight(WeightOriginalRange weight)
	{
		this.weight = weight;
	}
}
enum CoreMeaningOne implements CoreMeaning
{
	// all Arabic number will be recognized in NUMBERTYPE
	NUMBERTYPE("<number>",Properties.NUMBER,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	
	ZERO("zero",Properties.NUMBER,ServiceProperty.COST,0f),
	ONE("one",Properties.NUMBER,ServiceProperty.COST,1f),
	TWO("two",Properties.NUMBER,ServiceProperty.COST,2f),
	THREE("three",Properties.NUMBER,ServiceProperty.COST,3f),
	THIR("thir",Properties.NUMBER,ServiceProperty.COST,3f),
	FOUR("four",Properties.NUMBER,ServiceProperty.COST,4f),
	FIVE("five",Properties.NUMBER,ServiceProperty.COST,5f),
	FIF("fif",Properties.NUMBER,ServiceProperty.COST,5f),
	SIX("six",Properties.NUMBER,ServiceProperty.COST,6f),
	SEVEN("seven",Properties.NUMBER,ServiceProperty.COST,7f),
	EIGHT("eight",Properties.NUMBER,ServiceProperty.COST,8f),
	EIGH("eigh",Properties.NUMBER,ServiceProperty.COST,8f),
	NINE("nine",Properties.NUMBER,ServiceProperty.COST,9f),
	NIN("nin",Properties.NUMBER,ServiceProperty.COST,9f),
	TEN("ten",Properties.NUMBER,ServiceProperty.COST,10f),
	ELEVEN("eleven",Properties.NUMBER,ServiceProperty.COST,11f),
	TWELVE("twelve",Properties.NUMBER,ServiceProperty.COST,12f),
	TWENTY("twenty",Properties.NUMBER,ServiceProperty.COST,20f),
	HUNDRED("hundred",Properties.NUMBER,ServiceProperty.COST,100f),
	GRAND("grand",Properties.NUMBER,ServiceProperty.COST,1000f),
	THOUSAND("thousand",Properties.NUMBER,ServiceProperty.COST,1000f),
	MILLION("million",Properties.NUMBER,ServiceProperty.COST,1000000f),
	BILLION("billion",Properties.NUMBER,ServiceProperty.COST,1000000000f),
	DOLLAR("dollar",Properties.NOUN,ServiceProperty.COST,WeightOriginalRange.STABLE),
	BUCK("buck",Properties.NOUN,ServiceProperty.COST,WeightOriginalRange.STABLE),
	USD("usd",Properties.NOUN,ServiceProperty.COST,WeightOriginalRange.STABLE),
	MONDAY("monday",Properties.NOUN,ServiceProperty.LANDA,1f),
	TUESDAY("tuesday",Properties.NOUN,ServiceProperty.LANDA,2f),
	WEDNESDAY("wednesday",Properties.NOUN,ServiceProperty.LANDA,3f),
	THURSDAY("thursday",Properties.NOUN,ServiceProperty.LANDA,4f),
	FRIDAY("friday",Properties.NOUN,ServiceProperty.LANDA,5f),
	SATURDAY("saturday",Properties.NOUN,ServiceProperty.LANDA,6f),
	SUNDAY("sunday",Properties.NOUN,ServiceProperty.LANDA,7f),
	MON("mon",Properties.NOUN,ServiceProperty.LANDA,1f),
	TUES("tues",Properties.NOUN,ServiceProperty.LANDA,2f),
	WED("wed",Properties.NOUN,ServiceProperty.LANDA,3f),
	THURS("thurs",Properties.NOUN,ServiceProperty.LANDA,4f),
	FRI("fri",Properties.NOUN,ServiceProperty.LANDA,5f),
	SAT("sat",Properties.NOUN,ServiceProperty.LANDA,6f),
	SUN("sun",Properties.NOUN,ServiceProperty.LANDA,7f),
	MOND("mon.",Properties.NOUN,ServiceProperty.LANDA,1f),
	TUESD("tues.",Properties.NOUN,ServiceProperty.LANDA,2f),
	WEDD("wed.",Properties.NOUN,ServiceProperty.LANDA,3f),
	THURSD("thurs.",Properties.NOUN,ServiceProperty.LANDA,4f),
	FRID("fri.",Properties.NOUN,ServiceProperty.LANDA,5f),
	SATD("sat.",Properties.NOUN,ServiceProperty.LANDA,6f),
	SUND("sun.",Properties.NOUN,ServiceProperty.LANDA,7f),
	JANUARY("january",Properties.MONTH,ServiceProperty.LANDA,1F),
	FEBRUARY("February",Properties.MONTH,ServiceProperty.LANDA,2F),
	MARCH("march",Properties.MONTH,ServiceProperty.LANDA,3F),
	APRIL("april",Properties.MONTH,ServiceProperty.LANDA,4F),
	MAY("may",Properties.MONTH,ServiceProperty.LANDA,5F),
	JUNE("june",Properties.MONTH,ServiceProperty.LANDA,6F),
	JULY("july",Properties.MONTH,ServiceProperty.LANDA,7F),
	AUGUST("august",Properties.MONTH,ServiceProperty.LANDA,8F),
	SEPTEMBER("september",Properties.MONTH,ServiceProperty.LANDA,9F),
	OCTOBER("october",Properties.MONTH,ServiceProperty.LANDA,10F),
	NOVEMBER("november",Properties.MONTH,ServiceProperty.LANDA,11F),
	DECEMBER("december",Properties.MONTH,ServiceProperty.LANDA,12F),
	
	ABANDON("abandon",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	ABIL("abil",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	ABLE("abl",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	ABOUT("about",Properties.SPREP,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	ABOVE("above",Properties.SPREP,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	ABROAD("abroad",Properties.ADJV,ServiceProperty.BPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	ABSENCE("absence",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	ABSENT("absent",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	ABSOLUTE("absolut",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESUPREME),
	ABSORB("absorb",Properties.VERB,ServiceProperty.DSERVICE,WeightOriginalRange.USELESS),
	ABUSE("abus",Properties.NOUN,ServiceProperty.SERVICE,WeightOriginalRange.NEGATIVESUPREME),
	ACADEM("academ",Properties.NOUN,ServiceProperty.ABPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	ACCENT("accent",Properties.NOUN,ServiceProperty.SERVICE,WeightOriginalRange.NEGATIVESLACK),
	ACCENTUATE("accentuat",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	ACCEPT("accept",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	ACCESS("access",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	ACCIDENT("accident",Properties.NOUN,ServiceProperty.SAFETY,WeightOriginalRange.NEGATIVEENHANCE),
	ACCOMPANY("accompany",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	ACCORD("accord",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	ACCOUNT("account",Properties.NOUN,ServiceProperty.COST,WeightOriginalRange.POSITIVESTABLE),
	ACCOMMODAT("accommodat",Properties.NOUN,ServiceProperty.SEATCLASS,WeightOriginalRange.STABLE),
	ACCURATE("accurate",Properties.ADJV,ServiceProperty.RELIABILITY,WeightOriginalRange.POSITIVEENHANCE),
	ACCURACY("accuracy",Properties.NOUN,ServiceProperty.RELIABILITY,WeightOriginalRange.POSITIVEENHANCE),
	ACCUS("accus",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.NEGATIVEENHANCE),
	ACHIEV("achiev",Properties.VERB,ServiceProperty.RELIABILITY,WeightOriginalRange.POSITIVESLACK),
	ACID("acid",Properties.ADJV,ServiceProperty.DSERVICE,WeightOriginalRange.NEGATIVESLACK),
	ACKOWLEDH("ackowledg",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	ACQUIRE("acquire",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	ACROSS("across",Properties.SPREP,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	ACT("act",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	ACTUAL("actual",Properties.ADJV,ServiceProperty.RELIABILITY,WeightOriginalRange.POSITIVEENHANCE),
	AD("ad",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.NEGATIVESLACK),
	ADAPT("adapt",Properties.VERB,ServiceProperty.SERVICE,WeightOriginalRange.POSITIVESTABLE),
	ADD("add",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	ADDI("addi",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	ADDRESS("address",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	ADEQUANT("adequat",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	ADJUST("adjust",Properties.VERB,ServiceProperty.RELIABILITY,WeightOriginalRange.POSITIVESTABLE),
	ADMINISTRAT("administrat",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.STABLE),
	ADMIR("admir",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVEENHANCE),
	ADMIT("admit",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	ADOPT("adopt",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	ADULT("adult",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	ADVANC("advanc",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	ADVANTAG("advantag",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVEENHANCE),
	ADVEN("adven",Properties.NOUN,ServiceProperty.RELIABILITY,WeightOriginalRange.NEGATIVESLACK),
	ADVENTITI("adventiti",Properties.ADJV,ServiceProperty.RELIABILITY,WeightOriginalRange.POSITIVESLACK),
	ADVENTUR("adventur",Properties.ADJV,ServiceProperty.RELIABILITY,WeightOriginalRange.POSITIVESLACK),
	ADVERTIS("advertis",Properties.VERB,ServiceProperty.SERVICE,WeightOriginalRange.NEGATIVESLACK),
	AFFAIR("affair",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.NEGATIVEENHANCE),
	AFFECT("affect",Properties.VERB,ServiceProperty.ASERVICE,WeightOriginalRange.POSITIVESTABLE),
	AFFORD("afford",Properties.VERB,ServiceProperty.COST,WeightOriginalRange.POSITIVESLACK),
	AFFRAID("affraid",Properties.VERB,ServiceProperty.SAFETY,WeightOriginalRange.NEGATIVEENHANCE),
	AFTER("after",Properties.SPREP,ServiceProperty.LANDA,WeightOriginalRange.POSITIVESTABLE),
	AFTERNOON("afternoon",Properties.NOUN,ServiceProperty.LANDA,WeightOriginalRange.STABLE),
	AFTERWARD("afterward",Properties.SPREP,ServiceProperty.LANDA,WeightOriginalRange.POSITIVESTABLE),
	AGAIN("again",Properties.SPREP,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	AGAINST("against",Properties.RPREP,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	AGE("age",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	AGENCY("agency",Properties.NOUN,ServiceProperty.ASERVICE,WeightOriginalRange.POSITIVEENHANCE),
	AGENDA("agenda",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	AGENT("agent",Properties.NOUN,ServiceProperty.ASERVICE,WeightOriginalRange.POSITIVEENHANCE),
	AGGRESS("aggress",Properties.VERB,ServiceProperty.SERVICE,WeightOriginalRange.NEGATIVEENHANCE),
	AGO("ago",Properties.ADJV,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	AGONY("agony",Properties.NOUN,ServiceProperty.SERVICE,WeightOriginalRange.NEGATIVESUPREME),
	AGREE("agree",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	AHEAD("ahead",Properties.ADJV,ServiceProperty.RELIABILITY,WeightOriginalRange.POSITIVEENHANCE),
	AID("aid",Properties.NOUN,ServiceProperty.SERVICE,WeightOriginalRange.POSITIVEENHANCE),
	AIM("aim",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	AIR("air",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	AIRCRAFT("aircraft",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVEENHANCE),
	AIRPORT("airport",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVEENHANCE),
	ALARM("alarm",Properties.VERB,ServiceProperty.SERVICE,WeightOriginalRange.POSITIVEENHANCE),
	ALCHOL("alcohol",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	ALIVE("alive",Properties.ADJV,ServiceProperty.OTHER,WeightOriginalRange.POSITIVEENHANCE),
	ALL("all",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESUPREME),	
	ALLY("ally",Properties.VERB,ServiceProperty.SERVICE,WeightOriginalRange.POSITIVEENHANCE),
	ALLOW("allow",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	ALMOST("almost",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	ALONE("alone",Properties.ADJV,ServiceProperty.OTHER,WeightOriginalRange.POSITIVESLACK),
	ALONG("along",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	ALONGSIDE("alongside",Properties.SPREP,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	ALOUD("aloud",Properties.ADJV,ServiceProperty.SERVICE,WeightOriginalRange.NEGATIVEENHANCE),
	ALPHABET("alphabet",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	ALREADY("already",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	ALSO("also",Properties.CCONJ,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	ALTER("alter",Properties.VERB,ServiceProperty.NOSTOP,WeightOriginalRange.STABLE),
	ALTERNA("alterna",Properties.ADJV,ServiceProperty.SERVICE,WeightOriginalRange.POSITIVEENHANCE),
	ALTHOUGH("although",Properties.CCONJ,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	ALTOGETHER("altogether",Properties.CCONJ,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	ALWAYS("always",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	AM("am",Properties.VERB,ServiceProperty.OTHER,WeightOriginalRange.USELESS),
	A_M("a.m.",Properties.NOUN,ServiceProperty.DURATION,WeightOriginalRange.POSITIVESTABLE),
	AMAZ("amaz",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESUPREME),
	AMBITION("ambition",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	AMBULANCE("ambulance",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	AMONG("among",Properties.SPREP,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	AMOUNT("amount",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	AMUS("amus",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	AN("an",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	ANALYSIS("analysis",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	ANALYZ("analyz",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	ANCIENT("ancient",Properties.ADJV,ServiceProperty.OTHER,WeightOriginalRange.NEGATIVEENHANCE),
	AND("and",Properties.SPREP,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	ANGER("anger",Properties.NOUN,ServiceProperty.SERVICE,WeightOriginalRange.NEGATIVEENHANCE),
	ANGLE("angle",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	ANGRY("angry",Properties.ADJV,ServiceProperty.SERVICE,WeightOriginalRange.NEGATIVEENHANCE),
	ANIMAL("animal",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	ANKLE("ankle",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	ANNIVERSARY("anniversary",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	ANNOUNC("announc",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	ANNOY("annoy",Properties.VERB,ServiceProperty.DSERVICE,WeightOriginalRange.NEGATIVEENHANCE),
	ANNUL("annual",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	ANOTHER("another",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	ANSWER("answer",Properties.VERB,ServiceProperty.ASERVICE,WeightOriginalRange.POSITIVESTABLE),
	ANTICIPAT("anticipat",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	ANXIETY("anxiety",Properties.NOUN,ServiceProperty.SAFETY,WeightOriginalRange.NEGATIVEENHANCE),
	ANXIOUS("anxious",Properties.ADJV,ServiceProperty.SAFETY,WeightOriginalRange.NEGATIVEENHANCE),
	ANY("any",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	ANYMORE("anymore",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESUPREME),
	ANYONE("anyone",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	ANYTHING("anything",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	ANYWAY("anyway",Properties.CCONJ,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	APART("apart",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	APARTMENT("apartment",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	APLOGIZ("apologiz",Properties.VERB,ServiceProperty.SERVICE,WeightOriginalRange.POSITIVEENHANCE),
	APPARENT("apparent",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	APPEAL("appeal",Properties.VERB,ServiceProperty.OTHER,WeightOriginalRange.POSITIVESTABLE),
	APPEAR("appear",Properties.VERB,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	APPLE("apple",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	APPLICAT("applicat",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVEENHANCE),
	APPLY("apply",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	APPOINT("appoint",Properties.VERB,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	APPRECIAT("appreciat",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	APPROCH("approach",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	APPROPRIATE("appropriate",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	APPROV("approv",Properties.VERB,ServiceProperty.OTHER,WeightOriginalRange.POSITIVEENHANCE),
	APPROXIMATE("approximate",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	AREA("area",Properties.NOUN,ServiceProperty.OTHER,WeightOriginalRange.STABLE),
	ARGU("argu",Properties.VERB,ServiceProperty.SERVICE,WeightOriginalRange.NEGATIVEENHANCE),
	ARIS("aris",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	ARM("arm",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	ARMY("army",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVEENHANCE),
	AROUND("around",Properties.SPREP,ServiceProperty.LANDA,WeightOriginalRange.POSITIVESTABLE),
	ARRANG("arrang",Properties.VERB,ServiceProperty.RELIABILITY,WeightOriginalRange.POSITIVEENHANCE),
	ARREST("arrest",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.NEGATIVEENHANCE),
	ARRIV("arriv",Properties.VERB,ServiceProperty.LANDA,WeightOriginalRange.POSITIVESTABLE),
	ARROW("arrow",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	ART("art",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	ARTICLE("article",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	ARTIFICE("artifice",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.NEGATIVEENHANCE),
	ARTIFICIAL("artificial",Properties.ADJV,ServiceProperty.DSERVICE,WeightOriginalRange.NEGATIVESLACK),
	ASH("ash",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.NEGATIVEENHANCE),
	AS("as",Properties.CCONJ,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	ASHAMED("ashamed",Properties.ADJV,ServiceProperty.REPUTATION,WeightOriginalRange.NEGATIVEENHANCE),
	ASIDE("aside",Properties.RPREP,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	ASK("ask",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	ASLEEP("asleep",Properties.ADJV,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	APECT("aspect",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.STABLE),
	ASSIST("assist",Properties.VERB,ServiceProperty.OTHER,WeightOriginalRange.POSITIVEENHANCE),
	ASSISTANCE("assistance",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVEENHANCE),
	ASSISTANT("assistant",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVEENHANCE),
	ASSOCIAT("associat",Properties.VERB,ServiceProperty.SERVICE,WeightOriginalRange.POSITIVESTABLE),
	ASSUM("assum",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	ASSUR("assur",Properties.VERB,ServiceProperty.ASERVICE,WeightOriginalRange.POSITIVEENHANCE),
	AT("at",Properties.SPREP,ServiceProperty.LANDA,WeightOriginalRange.POSITIVEENHANCE),
	ATMOSPHERE("atmosphere",Properties.NOUN,ServiceProperty.SAFETY,WeightOriginalRange.STABLE),
	ATOM("atom",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	ATTACK("attack",Properties.VERB,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.NEGATIVEENHANCE),
	ATTEMPT("attempt",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	ATTEND("attend",Properties.VERB,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVEENHANCE),
	ATTENT("attent",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVEENHANCE),
	ATTITUDE("attitude",Properties.NOUN,ServiceProperty.SERVICE,WeightOriginalRange.STABLE),
	ATTORNEY("attorney",Properties.NOUN,ServiceProperty.SERVICE,WeightOriginalRange.POSITIVESTABLE),
	ATTRACT("attract",Properties.VERB,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVEENHANCE),
	AUDIENCE("audience",Properties.NOUN,ServiceProperty.ASERVICE,WeightOriginalRange.POSITIVEENHANCE),
	AUDIO("audio",Properties.ADJV,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	AUDIENT("audient",Properties.NOUN,ServiceProperty.ASERVICE,WeightOriginalRange.POSITIVEENHANCE),
	AUNT("aunt",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	AUTHOR("author",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	AUTOMAT("automat",Properties.VERB,ServiceProperty.OTHER,WeightOriginalRange.POSITIVESTABLE),
	AVAIL("avail",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	AVERAGE("average",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	AVOID("avoid",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVEENHANCE),
	AWAK("awak",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	AWARD("award",Properties.VERB,ServiceProperty.SERVICE,WeightOriginalRange.POSITIVEENHANCE),
	AWAY("away",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	AWFUL("awful",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVEENHANCE),
	AWKWARD("awkward",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVEENHANCE),
//
//Letter B starts here
//
	BABY("baby",Properties.NOUN,ServiceProperty.SERVICE,WeightOriginalRange.POSITIVEENHANCE),
	BACK("back",Properties.NOUN,ServiceProperty.ROUNDTRIP,WeightOriginalRange.POSITIVEENHANCE),
	BACKHAND("backhand",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	BANANA("banana",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	BACKGROUND("background",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	BACKWARD("backward",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESLACK),
	BACTERIA("bacteria",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.NEGATIVEENHANCE),
	BAD("bad",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	BAG("bag",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	BAGGAGE("baggage",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	BAK("bak",Properties.VERB,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	BAKERY("bakery",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	BALANC("balanc",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	BALL("ball",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	BAN("ban",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESUPREME),
	BAND("band",Properties.NOUN,ServiceProperty.OTHER,WeightOriginalRange.STABLE),
	BANDAGE("bandage",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	BANK("bank",Properties.NOUN,ServiceProperty.OTHER,WeightOriginalRange.POSITIVESTABLE),
	BAR("bar",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	BARELY("barely",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESLACK),
	BARGAIN("bargain",Properties.NOUN,ServiceProperty.OTHER,WeightOriginalRange.POSITIVEENHANCE),
	BARRIER("barrier",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	BAS("bas",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	BASEBALL("baseball",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.STABLE),
	BASIC("basic",Properties.ADJV,ServiceProperty.SEATCLASS,WeightOriginalRange.STABLE),
	BASIS("basis",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	BASKETBALL("baskdetball",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.STABLE),
	BATH("bath",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVEENHANCE),
	BATHROOM("bathroom",Properties.NOUN,ServiceProperty.OTHER,WeightOriginalRange.POSITIVESTABLE),
	BATTERY("battery",Properties.NOUN,ServiceProperty.OTHER,WeightOriginalRange.POSITIVESTABLE),
	BATTLE("battle",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.NEGATIVESUPREME),
	BAY("bay",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	BE("be",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	BEACH("beach",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	BEAK("beak",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	BEAKER("beaker",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	BEAR("bear",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	BEARD("beard",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	BEAT("beat",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	BEAUTIFUL("beautiful",Properties.ADJV,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVEENHANCE),
	BEAUTY("beauty",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVEENHANCE),
	BECAUSE("beacuse",Properties.CCONJ,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	BECOM("becom",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.STABLE),
	BED("bed",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVEENHANCE),
	BEDROOM("bedroom",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESUPREME),
	BEEF("beef",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	BEER("beer",Properties.NOUN,ServiceProperty.OTHER,WeightOriginalRange.POSITIVESTABLE),
	BEFORE("before",Properties.SPREP,ServiceProperty.LANDA,WeightOriginalRange.POSITIVESLACK),
	BEGIN("begin",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	BEHALF("behalf",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVEENHANCE),
	BEHAV("behav",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	BEHIND("behind",Properties.SPREP,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	BELIEF("belief",Properties.NOUN,ServiceProperty.OTHER,WeightOriginalRange.POSITIVEENHANCE),
	BELIEV("believ",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	BELL("bell",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	BELONG("belong",Properties.VERB,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	BELOW("below",Properties.SPREP,ServiceProperty.COST,WeightOriginalRange.NEGATIVESTABLE),
	BELT("belt",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	BEND("bend",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	BENEATH("beneath",Properties.SPREP,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	BENEFIT("benefit",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	BENT("bent",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	BESIDE("beside",Properties.CCONJ,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	BEST("best",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESUPREME),
	BET("bet",Properties.VERB,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	BETTER("better",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	BETWEEN("between",Properties.SPREP,ServiceProperty.LANDA,WeightOriginalRange.NEGATIVESTABLE),
	BEYOND("beyond",Properties.SPREP,ServiceProperty.COST,WeightOriginalRange.POSITIVEENHANCE),
	BICYCLE("bicycle",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	BID("bid",Properties.VERB,ServiceProperty.RELIABILITY,WeightOriginalRange.NEGATIVEENHANCE),
	BIG("big",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	BILL("bill",Properties.NOUN,ServiceProperty.OTHER,WeightOriginalRange.NEGATIVEENHANCE),
	BIOLOG("biolog",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	BIRD("bird",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	BIRTHLAND("birthland",Properties.NOUN,ServiceProperty.BPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	BIRTHDAY("birthday",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	BIT("bit",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	BITE("bite",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	BITTER("bitter",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVEENHANCE),
	BLACK("black",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.STABLE),
	BLADE("blade",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	BLAM("blam",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.NEGATIVEENHANCE),
	BLANK("blank",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	BLANKET("blanket",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	BLIND("blind",Properties.ADJV,ServiceProperty.SERVICE,WeightOriginalRange.POSITIVEENHANCE),
	BLOCK("block",Properties.VERB,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.NEGATIVEENHANCE),
	BLOND("blond",Properties.ADJV,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	BLOOD("blood",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	BLOW("blow",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	BLUE("blue",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.STABLE),
	BOARD("board",Properties.VERB,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	BOAT("boat",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	BODY("body",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	BOIL("boil",Properties.VERB,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	BOMB("bomb",Properties.NOUN,ServiceProperty.SAFETY,WeightOriginalRange.NEGATIVESUPREME),
	BOND("bond",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVEENHANCE),
	BONE("bone",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	BOOK("book",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	BOOT("boot",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	BOOTH("booth",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	BORDER("border",Properties.VERB,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	BOR("bor",Properties.VERB,ServiceProperty.DSERVICE,WeightOriginalRange.NEGATIVEENHANCE),
	BORN("born",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	BORROW("borrow",Properties.VERB,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	BOSS("boss",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	BOTH("both",Properties.CCONJ,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	BOTHER("bother",Properties.VERB,ServiceProperty.DSERVICE,WeightOriginalRange.NEGATIVEENHANCE),
	BOTTLE("bottle",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	BOTTOM("bottom",Properties.ADJV,ServiceProperty.PACKAGERULE,WeightOriginalRange.STABLE),
	BOUND("bound",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	BOWL("bowl",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	BOX("box",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	BOY("boy",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	BOYFRIEND("boyfriend",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	BRAIN("brain",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	BRANCH("branch",Properties.VERB,ServiceProperty.SERVICE,WeightOriginalRange.POSITIVEENHANCE),
	BRAND("brand",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	BRAVE("brave",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	BRAVERY("bravery",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	BREAD("bread",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	BREAK("break",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	BREAKFAST("breakfast",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	BREAST("breast",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	BREATH("breath",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	BREED("breed",Properties.VERB,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	BRICK("brick",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	BRIDGE("bridge",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	BRIEF("brief",Properties.ADJV,ServiceProperty.OTHER,WeightOriginalRange.POSITIVEENHANCE),
	BRIGHT("bright",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	BRILLIANT("brilliant",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	BRING("bring",Properties.VERB,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	BROAD("broad",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	BROADCAST("broadcast",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	BROKEN("broken",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVEENHANCE),
	BROTHER("brother",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	BROWN("brown",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.STABLE),
	BRUSH("brush",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	BUBBLE("bubble",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	BUDGET("budget",Properties.ADJV,ServiceProperty.COST,WeightOriginalRange.POSITIVESLACK),
	BUILD("build",Properties.VERB,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	BUILT("built",Properties.VERB,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	BULLET("bullet",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	BUNCH("bunch",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	BURN("burn",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	BURST("burst",Properties.VERB,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.NEGATIVEENHANCE),
	BURY("bury",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	BUS("bus",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVEENHANCE),
	BUSH("bush",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	BUSINESS("business",Properties.NOUN,ServiceProperty.SEATCLASS,WeightOriginalRange.POSITIVEENHANCE),
	BUSY("busy",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	BUT("but",Properties.RPREP,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	BUTT("butt",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	BUTTER("butter",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	BUTTON("button",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	BUY("buy",Properties.VERB,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.USELESS),
	BUYER("buyer",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.USELESS),
	BY("by",Properties.SPREP,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	BYE("bye",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
//
//Letter C starts here
//
	CABINET("cabinet",Properties.NOUN,ServiceProperty.SEATCLASS,WeightOriginalRange.STABLE),
	CABLE("cable",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	CAKE("cake",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	CALCULAT("calculat",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	CALL("call",Properties.VERB,ServiceProperty.ASERVICE,WeightOriginalRange.USELESS),
	CABIN("cabin",Properties.NOUN,ServiceProperty.SEATCLASS,WeightOriginalRange.STABLE),
	CALM("calm",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	CAMERA("camera",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESLACK),
	CAMP("camp",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	CAMPAIGN("campaign",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	CAN("can",Properties.VERB,ServiceProperty.OTHER,WeightOriginalRange.POSITIVESTABLE),
	CANCEL("cancel",Properties.VERB,ServiceProperty.RELIABILITY,WeightOriginalRange.NEGATIVEENHANCE),
	CANCER("cancer",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	CANDIDATE("candidate",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	CANDIDLY("candidly",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	CANDY("candy",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	CAPABLE("capable",Properties.ADJV,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVEENHANCE),
	CAPACITY("capacity",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	CAPITAL("capital",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	CAPTAIN("captain",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	CAPTURE("capture",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	CAR("car",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	CARD("card",Properties.NOUN,ServiceProperty.OTHER,WeightOriginalRange.POSITIVESTABLE),
	CARE("care",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	CAREER("career",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	CARPET("carpet",Properties.NOUN,ServiceProperty.OTHER,WeightOriginalRange.POSITIVESTABLE),
	CARROT("carrot",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	CARRY("carry",Properties.VERB,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	CASE("case",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	CASH("cash",Properties.NOUN,ServiceProperty.COST,WeightOriginalRange.POSITIVESTABLE),
	CAST("cast",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	CAT("cat",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	CATCH("catch",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.STABLE),
	CATEGORY("category",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	CATTLE("cattle",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	CAUS("caus",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	CD("cd",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	CEASE("cease",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	CEIL("ceil",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	CELEBRAT("celebrat",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVEENHANCE),
	CELL("cell",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	CELLPHONE("cellphone",Properties.NOUN,ServiceProperty.OTHER,WeightOriginalRange.POSITIVESTABLE),
	CENT("cent",Properties.NOUN,ServiceProperty.COST,WeightOriginalRange.POSITIVESTABLE),
	CENTER("center",Properties.NOUN,ServiceProperty.BPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	CENTIMETER("centimeter",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESLACK),
	CM("cm",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESLACK),
	CENTRAL("central",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESUPREME),
	CENTURY("century",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	CEREMONY("ceremony",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	CERTAIN("certain",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	CERTIFICAT("certificat",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVEENHANCE),
	CHAIN("chain",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	CHAIR("chair",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	CHALLENG("challeng",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	CHAMBER("chamber",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVEENHANCE),
	CHANCE("chance",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	CHANGE("change",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	CHANNEL("channel",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	CHAPTER("chapter",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	CHARACTER("character",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	CHARACTERISTIC("characteristic",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESUPREME),
	CHARG("charg",Properties.VERB,ServiceProperty.OTHER,WeightOriginalRange.POSITIVEENHANCE),
	CHARITY("charity",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.NEGATIVEENHANCE),
	CHART("chart",Properties.VERB,ServiceProperty.SAFETY,WeightOriginalRange.POSITIVEENHANCE),
	CHAS("chas",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	CHAT("chat",Properties.VERB,ServiceProperty.ASERVICE,WeightOriginalRange.POSITIVESTABLE),
	CHEAP("cheap",Properties.ADJV,ServiceProperty.COST,WeightOriginalRange.POSITIVESLACK),
	CHEAT("cheat",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.NEGATIVEENHANCE),
	CHECK("check",Properties.VERB,ServiceProperty.OTHER,WeightOriginalRange.POSITIVESTABLE),
	CHEER("cheer",Properties.VERB,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVEENHANCE),
	CHEESE("cheese",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	CHEMICAL("chemical",Properties.ADJV,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	CHEMIST("chemist",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	CHEST("chest",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	CORN("corn",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	CHESTNUT("chestnut",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	CHEW("chew",Properties.VERB,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	CHICKEN("chicken",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	CHIEF("chief",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESUPREME),
	CHILD("child",Properties.NOUN,ServiceProperty.BPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	CHILDREN("children",Properties.NOUN,ServiceProperty.BPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	CHILDHOOD("childhood",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	CHIN("chin",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	CHIPS("chips",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	CHIPMUNK("chipmunk",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	CHOCOLATE("chocolate",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	CHOICE("choice",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	CHOOS("choos",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	CHOPSTICK("chopstick",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	CHURCH("church",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	CIGARETTE("cigarette",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	CIRCLE("circle",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	CIRCUMSTANCE("circumstance",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	CITIZEN("citizen",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	CITY("city",Properties.NOUN,ServiceProperty.BPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	CIVIL("civil",Properties.ADJV,ServiceProperty.REPUTATION,WeightOriginalRange.NEGATIVESTABLE),
	CLAIM("claim",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	CLASS("class",Properties.NOUN,ServiceProperty.SEATCLASS,WeightOriginalRange.STABLE),
	CLASSIFY("classify",Properties.VERB,ServiceProperty.SEATCLASS,WeightOriginalRange.STABLE),
	CLASSIFICATION("classification",Properties.NOUN,ServiceProperty.SEATCLASS,WeightOriginalRange.STABLE),
	CLASSIC("classic",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	CLASSROOM("classroom",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	CLEAN("clean",Properties.ADJV,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVEENHANCE),
	CLEANS("cleans",Properties.VERB,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVEENHANCE),
	CLEAR("clear",Properties.ADJV,ServiceProperty.SERVICE,WeightOriginalRange.POSITIVEENHANCE),
	CLERK("clerk",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	CLICK("click",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	CLIENT("client",Properties.NOUN,ServiceProperty.SERVICE,WeightOriginalRange.POSITIVESTABLE),
	CLIFFHANG("cliffhang",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	CLIMATE("climate",Properties.NOUN,ServiceProperty.RELIABILITY,WeightOriginalRange.POSITIVESTABLE),
	CLIMB("climb",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	CLOCK("clock",Properties.NOUN,ServiceProperty.LANDA,WeightOriginalRange.POSITIVEENHANCE),
	CLOSE("close",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	CLOSET("closet",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	CLOTH("cloth",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	CLOUD("cloud",Properties.NOUN,ServiceProperty.RELIABILITY,WeightOriginalRange.POSITIVESTABLE),
	CLUB("club",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	COACH("coach",Properties.NOUN,ServiceProperty.SEATCLASS,WeightOriginalRange.POSITIVEENHANCE),
	COAL("coal",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	COAST("coast",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	COAT("coat",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	CODE("code",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	COFFEE("coffee",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	COIN("coin",Properties.NOUN,ServiceProperty.COST,WeightOriginalRange.POSITIVESTABLE),
	COLD("cold",Properties.ADJV,ServiceProperty.DSERVICE,WeightOriginalRange.NEGATIVESTABLE),
	COLLAPS("collaps",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	COLLEAGUE("colleague",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.STABLE),
	COLLECT("collect",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	COLLEGE("college",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	COLOR("color",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	COLUMN("column",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	COMBIN("combin",Properties.VERB,ServiceProperty.ROUNDTRIP,WeightOriginalRange.POSITIVESTABLE),
	COM("com",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	COMEDY("comedy",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	COMETIC("cometic",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	COMPROMIS("compromis",Properties.CCONJ,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	COMFORT("comfort",Properties.VERB,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVEENHANCE),
	COMMAND("command",Properties.VERB,ServiceProperty.RELIABILITY,WeightOriginalRange.POSITIVESTABLE),
	COMMENT("comment",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	COMMERCIAL("commercial",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	COMMIS("commis",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	COMMISERAT("commiserat",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	COMMISSARY("commissary",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	COMMIT("commit",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.NEGATIVEENHANCE),
	COMMITTEE("committee",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	COMMODE("commode",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	COMMODIFY("commodify",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	COMMON("common",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	COMMUNICAT("communicat",Properties.VERB,ServiceProperty.SERVICE,WeightOriginalRange.POSITIVESTABLE),
	COMMUNITY("community",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	COMMUTAT("commutat",Properties.VERB,ServiceProperty.COST,WeightOriginalRange.POSITIVESTABLE),
	COMPANY("company",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	COMPAR("compar",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	COMPET("compet",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVEENHANCE),
	COMPLAIN("complain",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.NEGATIVESTABLE),
	COMPLET("complet",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	COMPLEX("complex",Properties.ADJV,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	COMPLICAT("complicat",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	COMPONENT("component",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	COMPORT("comport",Properties.VERB,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	COMPUTER("computer",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	CONCENTRAT("concentrat",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	CONCEPT("concept",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	CONCERN("concern",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	CONCERT("concert",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	CONCLUD("conclud",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	CONCLUS("conclus",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	CONCRETE("concrete",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	CONDITION("condition",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	CONDUCT("conduct",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	CONFERENCE("conference",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	CONFIDENT("confident",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	CONFIDENCE("confidence",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	CONFIRM("confirm",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	CONFIEMATORY("confirmatory",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	CONFLICT("conflict",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.NEGATIVEENHANCE),
	CONFRONT("confront",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	CONFUS("confus",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESLACK),
	CONGRATULAT("congratulat",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	CONGRESS("congress",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	CONNECT("connect",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	CONSCIOUS("conscious",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	CONSEQUENT("consequent",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	CONSERVAT("conservat",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVEENHANCE),
	CONSIDER("consider",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	CONSISG("consist",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	CONSTANT("constant",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	CONSTRUCT("construct",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	CONSULT("consult",Properties.VERB,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVEENHANCE),
	CONSUM("consum",Properties.VERB,ServiceProperty.COST,WeightOriginalRange.POSITIVESTABLE),
	CONTACT("contact",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	CONTAIN("contain",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	CONTEMPORARY("contemporary",Properties.ADJV,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	CONTENT("content",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	CONTEST("contest",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVEENHANCE),
	CONTEXT("context",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	CONTINENT("continent",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	CONTINU("continu",Properties.VERB,ServiceProperty.RELIABILITY,WeightOriginalRange.POSITIVEENHANCE),
	CONTRACT("contract",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	CONTRAST("contrast",Properties.RPREP,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	CONTRIBUT("contribut",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	CONTROL("control",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVEENHANCE),
	CONVENIENT("convenient",Properties.ADJV,ServiceProperty.SERVICE,WeightOriginalRange.POSITIVEENHANCE),
	CONVENTION("convention",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	CONVERS("convers",Properties.VERB,ServiceProperty.ASERVICE,WeightOriginalRange.POSITIVESTABLE),
	CONVERT("convert",Properties.VERB,ServiceProperty.RELIABILITY,WeightOriginalRange.NEGATIVESTABLE),
	CONVINC("convinc",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	COOK("cook",Properties.VERB,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	COOKIE("cookie",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	COOL("cool",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	COOPERAT("cooperat",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	COP("cop",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	COPY("copy",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	CORE("core",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	CORNER("corner",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	CORRECT("correct",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	COST("cost",Properties.VERB,ServiceProperty.COST,WeightOriginalRange.POSITIVESTABLE),
	COTTAGE("cottage",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	COTTON("cotton",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	COUGH("cough",Properties.VERB,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	COULD("could",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	COUNCIL("council",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	COUNT("count",Properties.VERB,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	COUNTRY("country",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	COUNTY("county",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	COUNTRYSIDE("countryside",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	COUPLE("couple",Properties.VERB,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	COURAGE("courage",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	COURT("court",Properties.VERB,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVEENHANCE),
	COUSIN("cousin",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	COURSE("course",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	COVER("cover",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	COW("cow",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	CRACK("crack",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	CRAFT("craft",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	CRASH("crash",Properties.VERB,ServiceProperty.SAFETY,WeightOriginalRange.NEGATIVESUPREME),
	CRAZY("crazy",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVEENHANCE),
	CREAM("cream",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	CREAT("creat",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	CREDIT("credit",Properties.NOUN,ServiceProperty.COST,WeightOriginalRange.POSITIVESTABLE),
	CREDITCARD("creditcard",Properties.NOUN,ServiceProperty.COST,WeightOriginalRange.POSITIVESTABLE),
	CRIME("crime",Properties.NOUN,ServiceProperty.SAFETY,WeightOriginalRange.NEGATIVEENHANCE),
	CRIMINAL("criminal",Properties.ADJV,ServiceProperty.SAFETY,WeightOriginalRange.NEGATIVEENHANCE),
	CRISIS("crisis",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	CRISP("crisp",Properties.VERB,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	CRISPER("crisper",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	CRITERION("criterion",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVEENHANCE),
	CRITIC("critic",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	CROP("crop",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	CROSS("cross",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	CROWD("crowd",Properties.VERB,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.NEGATIVESTABLE),
	CROWN("crown",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESUPREME),
	CRUCIAL("crucial",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESUPREME),
	CRUEL("cruel",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESUPREME),
	CRUSH("crush",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	CRY("cry",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	CRYSTAL("crystal",Properties.ADJV,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	CULTURE("culture",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	CUP("cup",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	CUPBOARD("cupboard",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	CUPCAKE("cupcake",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	CUR("cur",Properties.VERB,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	CURIOUS("curious",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	CURL("curl",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	CURRENT("current",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	CURTAIN("curtain",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	CURV("curv",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	CUSTOM("custom",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	CUSTOMERY("customery",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	CUT("cut",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	CUTE("cute",Properties.ADJV,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVEENHANCE),
	CYCL("cycl",Properties.VERB,ServiceProperty.DSERVICE,WeightOriginalRange.NEGATIVESTABLE),
//
//Letter D starts here
//
	DAD("dad",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	DAILY("daily",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	DAMAG("damag",Properties.VERB,ServiceProperty.PACKAGERULE,WeightOriginalRange.NEGATIVEENHANCE),
	DAMP("damp",Properties.ADJV,ServiceProperty.DSERVICE,WeightOriginalRange.NEGATIVEENHANCE),
	DANC("danc",Properties.VERB,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	DANGER("danger",Properties.NOUN,ServiceProperty.SAFETY,WeightOriginalRange.NEGATIVEENHANCE),
	DARE("dare",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	DARK("dark",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	DATA("data",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	DATE("date",Properties.NOUN,ServiceProperty.LANDA,WeightOriginalRange.POSITIVESTABLE),
	DAUGHTER("daughter",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	DAY("day",Properties.NOUN,ServiceProperty.DURATION,WeightOriginalRange.POSITIVEENHANCE),
	DEAD("dead",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	DEAF("deaf",Properties.ADJV,ServiceProperty.SERVICE,WeightOriginalRange.POSITIVESTABLE),
	DEAL("deal",Properties.VERB,ServiceProperty.COST,WeightOriginalRange.POSITIVESLACK),
	DEAR("dear",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	DEATH("death",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESUPREME),
	DEBAT("debat",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	DECAY("decay",Properties.VERB,ServiceProperty.DSERVICE,WeightOriginalRange.NEGATIVESUPREME),
	DECENT("decent",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	DECENTRALIS("decentralis",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	DECID("decid",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	DECISION("decision",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	DECLAR("declar",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	DECLIN("declin",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	DECORAT("decorat",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	DECREAS("decreas",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	DEEP("deep",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	DEFEAT("defeat",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	DEFEND("defend",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.NEGATIVESTABLE),
	DEFENS("defens",Properties.VERB,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	DEFIN("defin",Properties.VERB,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	DEFINITE("definite",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	DEGREE("degree",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	DELAY("delay",Properties.VERB,ServiceProperty.RELIABILITY,WeightOriginalRange.NEGATIVESTABLE),
	DELIBERAT("deliberat",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.STABLE),
	DELICATE("delicate",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	DELIGHT("delight",Properties.VERB,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVEENHANCE),
	DELIVER("deliver",Properties.VERB,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	DAMAND("demand",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	DEMOCRACY("democracy",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	DEMOCRATIC("democratic",Properties.ADJV,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	DEMONSTRAT("demonstrat",Properties.VERB,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	DENTIST("dentist",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	DENY("deny",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	DEODORANT("deodorant",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	DEPARTMENT("department",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	DEPART("depart",Properties.VERB,ServiceProperty.LANDA,WeightOriginalRange.NEGATIVESTABLE),
	DEPARTURE("departure",Properties.NOUN,ServiceProperty.DURATION,WeightOriginalRange.POSITIVESTABLE),
	DEPEND("depend",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	DEPENDABILITY("dependability",Properties.NOUN,ServiceProperty.RELIABILITY,WeightOriginalRange.POSITIVEENHANCE),
	DEPOSIT("deposit",Properties.VERB,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	DEPRESS("depress",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESLACK),
	DEPRESSANT("depressant",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.NEGATIVESLACK),
	DEPTH("depth",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	DEPUT("deput",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	DERIV("deriv",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	DESCRIB("describ",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	DESCRIPTION("description",Properties.NOUN,ServiceProperty.SERVICE,WeightOriginalRange.POSITIVESTABLE),
	DESERT("desert",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	DESSERT("dessert",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	DESERV("deserv",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	DESIGN("design",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	DESIR("desir",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	DESK("desk",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	DESPERATE("desperate",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVEENHANCE),
	DESPITE("despite",Properties.RPREP,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	DESTROY("destroy",Properties.VERB,ServiceProperty.SAFETY,WeightOriginalRange.NEGATIVEENHANCE),
	DESTINAT("destinat",Properties.NOUN,ServiceProperty.LANDA,WeightOriginalRange.POSITIVESTABLE),
	DESTRUCT("destruct",Properties.ADJV,ServiceProperty.SAFETY,WeightOriginalRange.NEGATIVESTABLE),
	DETAIL("detail",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	DETERMINAT("determinat",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	DETERMIN("determin",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	DEVELOP("develop",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	DEVICE("device",Properties.NOUN,ServiceProperty.SAFETY,WeightOriginalRange.POSITIVESTABLE),
	DEVOT("devot",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	DIAGRAM("diagram",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	DIALOGUE("dialogue",Properties.NOUN,ServiceProperty.SERVICE,WeightOriginalRange.POSITIVESTABLE),
	DIALYSIS("dialysis",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	DIAMOND("diamond",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	DIARY("diary",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	DICTIONARY("dictionary",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	DIE("die",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	DIET("diet",Properties.VERB,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	DIFFERENT("different",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.STABLE),
	DIFFERENTIAT("differentiat",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.STABLE),
	DIG("dig",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	DIGITAL("digital",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	DINNER("dinner",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	DIRECT("direct",Properties.ADJV,ServiceProperty.NOSTOP,WeightOriginalRange.POSITIVESTABLE),
	DIRT("dirt",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESLACK),
	DISAST("disast",Properties.VERB,ServiceProperty.SAFETY,WeightOriginalRange.NEGATIVESUPREME),
	DISC("disc",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	DISCARD("discard",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	DISCIPLIN("disciplin",Properties.VERB,ServiceProperty.SAFETY,WeightOriginalRange.POSITIVESTABLE),
	DISCOUNT("discount",Properties.VERB,ServiceProperty.COST,WeightOriginalRange.POSITIVESLACK),
	DISCOVER("discover",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	DISCUSS("discuss",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	DISEASE("disease",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.NEGATIVESTABLE),
	DISGUST("disgust",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVEENHANCE),
	DISH("dish",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	DISK("disk",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	DISMISS("dismiss",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	DISPLAY("display",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	DISSOLV("dissolv",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESLACK),
	DISSONANT("dissonant",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	DISTANT("distant",Properties.ADJV,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.NEGATIVESTABLE),
	DISTAT("distat",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	DISTINGUISH("distinguish",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	DISTRIBUT("distribut",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	DISTRICT("district",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	DISTRINCT("distrinct",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	DISTURB("disturb",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVEENHANCE),
	DIVID("divid",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	DIVIS("divis",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	DIVORC("divorc",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	DO("do",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	DOC("doc",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	DOCENT("docent",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	DOCIL("docil",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	DOCTOR("doctor",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	DOCUMENT("document",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	DOG("dog",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	DOMESTIC("domestic",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	DOOR("door",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	DOT("dot",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	DOUBLE("double",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	DOWN("down",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	DOWNSTAIRS("downstairs",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	DOWNTOWN("downtown",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	DOWNWARD("downward",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	DOZEN("dozen",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	DR("dr",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	DRAFT("draft",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	DRAG("drag",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.STABLE),
	DRAMA("drama",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	DRAMATIC("dramatic",Properties.ADJV,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	DRAW("draw",Properties.VERB,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	DREAM("dream",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	DRAWER("drawer",Properties.VERB,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	DRESS("dress",Properties.VERB,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	DRINK("drink",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	DRIV("driv",Properties.VERB,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	DROP("drop",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	DRUG("drug",Properties.NOUN,ServiceProperty.OTHER,WeightOriginalRange.STABLE),
	DRUGSTORE("drugstore",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	DRUM("drum",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	DRUNK("drunk",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	DRY("dry",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	DUE("due",Properties.ADJV,ServiceProperty.DURATION,WeightOriginalRange.POSITIVESTABLE),
	DURATION("duration",Properties.NOUN,ServiceProperty.DURATION,WeightOriginalRange.POSITIVESTABLE),
	DULL("dull",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	DMP("dump",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	DURING("during",Properties.SPREP,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	DUST("dust",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.NEGATIVESLACK),
	DUTY("duty",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	DVD("dvd",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	DYING("dying",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
//
//Letter E starts here
//
	EACH("each",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	EAGER("eager",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	EAR("ear",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	EARLY("early",Properties.ADJV,ServiceProperty.RELIABILITY,WeightOriginalRange.POSITIVEENHANCE),
	EARN("earn",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	EARTH("earth",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	EAS("eas",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	EAST("east",Properties.VERB,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	EAT("eat",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	ECONOM("econom",Properties.VERB,ServiceProperty.SEATCLASS,WeightOriginalRange.STABLE),
	ECONOMICAL("economical",Properties.ADJV,ServiceProperty.SEATCLASS,WeightOriginalRange.STABLE),
	EDG("edg",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	EDITION("edition",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	EDITOR("editor",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	EDUCAT("educat",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	EFFECT("effect",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	EFFICIENT("efficient",Properties.ADJV,ServiceProperty.COST,WeightOriginalRange.POSITIVESLACK),
	EFFORT("effort",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESUPREME),
	EG("eg",Properties.CCONJ,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.STABLE),
	EGG("egg",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	EITHER("either",Properties.CCONJ,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	ELBOW("elbow",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	ELD("eld",Properties.ADJV,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVEENHANCE),
	ELECT("elect",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	ELECTRIC("electric",Properties.VERB,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	ELEGANT("elegant",Properties.ADJV,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVEENHANCE),
	ELEMENT("element",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	ELEVATOR("elevator",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	ELIMINAT("eliminat",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESLACK),
	ELSE("else",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	ELSEWHERE("elsewhere",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	EMAIL("email",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	EMBARRASS("embarrass",Properties.VERB,ServiceProperty.DSERVICE,WeightOriginalRange.NEGATIVEENHANCE),
	EMERG("emerg",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	EMERGENCY("emergency",Properties.NOUN,ServiceProperty.RELIABILITY,WeightOriginalRange.NEGATIVESTABLE),
	EMOT("emot",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESLACK),
	EMPHASIZ("emphasiz",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	EMPIRE("empire",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	EMPIRIC("empiric",Properties.NOUN,ServiceProperty.SAFETY,WeightOriginalRange.POSITIVESTABLE),
	EMPLOY("employ",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVEENHANCE),
	EMPTY("empty",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESLACK),
	ENABL("enabl",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	ENCOUNTER("encounter",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	END("end",Properties.VERB,ServiceProperty.LANDA,WeightOriginalRange.POSITIVESTABLE),
	ENCOURAG("encourag",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	ENEMY("enemy",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	ENGAG("engag",Properties.VERB,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVEENHANCE),
	ENGIN("engin",Properties.VERB,ServiceProperty.SAFETY,WeightOriginalRange.POSITIVESTABLE),
	ENJOY("enjoy",Properties.VERB,ServiceProperty.SERVICE,WeightOriginalRange.POSITIVEENHANCE),
	ENORMOUS("enormous",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESUPREME),
	ENOUGH("enough",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	ENSUR("ensur",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	ENTER("enter",Properties.VERB,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	ENTERTAIN("entertain",Properties.VERB,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESUPREME),
	ENTHUSIASM("enthusiasm",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESUPREME),
	ENTHUSIASTIC("enthusiastic",Properties.ADJV,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESUPREME),
	ENTIRE("entire",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESUPREME),
	ENTITL("entitl",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	ENTRANC("entranc",Properties.VERB,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	ENTRY("entry",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	ENVELOPE("envelope",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	ENVIRONMENT("environment",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	EQUAL("equal",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	EQUIPMENT("equipment",Properties.NOUN,ServiceProperty.SAFETY,WeightOriginalRange.POSITIVESTABLE),
	EQUIVALENT("equivalent",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	ERA("era",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	ERROR("error",Properties.NOUN,ServiceProperty.RELIABILITY,WeightOriginalRange.NEGATIVESTABLE),
	ESCAP("escap",Properties.VERB,ServiceProperty.SAFETY,WeightOriginalRange.POSITIVESTABLE),
	ESPECIAL("especial",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	ESSAY("essay",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	ESSENTIAL("essential",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	ESTABLISH("establish",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	ESTIMAT("estimat",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	ETC("etc",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	ETHNIC("ethnic",Properties.ADJV,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	EVEN("even",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	EVENING("evening",Properties.NOUN,ServiceProperty.DURATION,WeightOriginalRange.STABLE),
	EVENT("event",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	EVENTUAL("eventual",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	EVENTUAT("eventuat",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	EVER("ever",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	EVERY("every",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	EVERYONG("everyone",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	EVERTHING("everything",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESUPREME),
	EVERYWHERE("everywhere",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESUPREME),
	EVIDENT("evident",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	EVIL("evil",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVEENHANCE),
	EX("ex",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	EXACT("exact",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	EXAGGERAT("exaggerat",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	EXAMIN("examin",Properties.VERB,ServiceProperty.SAFETY,WeightOriginalRange.POSITIVESTABLE),
	EXAMPLE("example",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	EXCELLENT("excellent",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	EXCEPT("except",Properties.RPREP,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	EXCHANG("exchang",Properties.VERB,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	EXCIT("excit",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	EXCLUD("exclud",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	EXCUS("excus",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	EXECUT("execut",Properties.VERB,ServiceProperty.SEATCLASS,WeightOriginalRange.POSITIVEENHANCE),
	EXERCIS("exercis",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	EXHIBIT("exhibit",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	EXIST("exist",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	EXPAND("expand",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	EXPECT("expect",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	EXPENS("expens",Properties.VERB,ServiceProperty.COST,WeightOriginalRange.POSITIVESTABLE),
	EXPERENC("experenc",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	EXPERIMENT("experiment",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.NEGATIVESTABLE),
	EXPERT("expert",Properties.NOUN,ServiceProperty.RELIABILITY,WeightOriginalRange.POSITIVESTABLE),
	EXTEND("extend",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	EXPLAIN("explain",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	EXPLOD("explod",Properties.VERB,ServiceProperty.SAFETY,WeightOriginalRange.NEGATIVEENHANCE),
	EXPLOR("explor",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	EXPLOSION("explosion",Properties.NOUN,ServiceProperty.SAFETY,WeightOriginalRange.NEGATIVEENHANCE),
	EXPORT("export",Properties.VERB,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	EXPOS("expos",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.NEGATIVESTABLE),
	EXPRESS("express",Properties.VERB,ServiceProperty.OTHER,WeightOriginalRange.POSITIVESTABLE),
	EXTENS("extens",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	EXTENT("extent",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	EXTRA("extra",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	EXTRAORDINARY("extraordinary",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESUPREME),
	EXTREME("extreme",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESUPREME),
	EYE("eye",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	ELEPHENT("elephent",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
//
//Letter F starts here
//
	FACE("face",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	FACIL("facil",Properties.VERB,ServiceProperty.DSERVICE,WeightOriginalRange.NEGATIVESLACK),
	FACILITAT("facilitat",Properties.VERB,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESLACK),
	FACT("fact",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	FACTORY("factory",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	FACTOR("factor",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	FAIL("fail",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	FAINT("faint",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	FAIR("fair",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	FAITH("faith",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	FALL("fall",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	FALSE("false",Properties.ADJV,ServiceProperty.REPUTATION,WeightOriginalRange.NEGATIVESTABLE),
	FAME("fame",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	FAMILIAR("familiar",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	FAMILY("family",Properties.NOUN,ServiceProperty.SERVICE,WeightOriginalRange.POSITIVESTABLE),
	FAMOUS("famous",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	FAN("fan",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	FANCY("fancy",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	FAR("far",Properties.ADJV,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	FARM("farm",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	FARTHER("farther",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	FARTHEST("farthest",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESUPREME),
	FASHION("fashion",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	FAST("fast",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	FASTEN("fasten",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	FAT("fat",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVEENHANCE),
	FATHER("father",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	FAULT("fault",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	FAVOR("favor",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	FAVORITE("favorite",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESUPREME),
	FEAR("fear",Properties.VERB,ServiceProperty.SAFETY,WeightOriginalRange.NEGATIVESLACK),
	FEATHER("feather",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	FEATURE("feature",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	FEDERAL("federal",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	FEE("fee",Properties.NOUN,ServiceProperty.COST,WeightOriginalRange.POSITIVESTABLE),
	FEEBLE("feeble",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	FEED("feed",Properties.VERB,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	FEEL("feel",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	FELLOW("fellow",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	FEMALE("female",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	FENCE("fence",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	FESTIVAL("festival",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	FEVER("fever",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.NEGATIVESTABLE),
	FEW("few",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	FIELD("field",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	FIGHT("fight",Properties.VERB,ServiceProperty.SAFETY,WeightOriginalRange.NEGATIVESTABLE),
	FIGURE("figure",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	FIL("fil",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	FILL("fill",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	FILM("film",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	FINAL("final",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	FINANC("financ",Properties.VERB,ServiceProperty.COST,WeightOriginalRange.POSITIVESTABLE),
	FIND("find",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	FINE("fine",Properties.ADJV,ServiceProperty.OTHER,WeightOriginalRange.POSITIVESTABLE),
	FINGER("finger",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	FINISH("finish",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	FIRE("fire",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	FIRM("firm",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	FIRST("first",Properties.NUMBER,ServiceProperty.SEATCLASS,WeightOriginalRange.POSITIVESUPREME),
	FISH("fish",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	FIT("fit",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	FLAG("flag",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	FLAM("flam",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	FLASH("flash",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	FLAT("flat",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	FLAVOR("flavor",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	FLESH("flesh",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	FLIGHT("flight",Properties.NOUN,ServiceProperty.BPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	FLOAT("float",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESLACK),
	FLOOD("flood",Properties.NOUN,ServiceProperty.RELIABILITY,WeightOriginalRange.NEGATIVESTABLE),
	FLOOR("floor",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	FLOUR("flour",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	FLOW("flow",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	FLOWER("flower",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.STABLE),
	FLU("flu",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.NEGATIVESLACK),
	FLUCTUANT("fluctuant",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESLACK),
	FLY("fly",Properties.VERB,ServiceProperty.BPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	FOCUS("focus",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	FOLD("fold",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	FOLK("folk",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	FOLLOW("follow",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	FOOD("food",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	FOOT("foot",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	FOOTBALL("football",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.STABLE),
	FOR("for",Properties.SPREP,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	FORCE("force",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	FORECAST("forecast",Properties.NOUN,ServiceProperty.RELIABILITY,WeightOriginalRange.POSITIVESTABLE),
	FOREIGN("foreign",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	FOREST("forest",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	FOREVER("forever",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESUPREME),
	FORGET("forget",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	FORGIV("forgiv",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	FORK("fork",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	FORM("form",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	FORMAL("formal",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	FORMER("former",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	FORTUNATE("fortunate",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	FORMULA("formula",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	FORTUN("fortun",Properties.VERB,ServiceProperty.COST,WeightOriginalRange.POSITIVESTABLE),
	FORWARD("forward",Properties.SPREP,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	FOUND("found",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	FRAME("fram",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	FREE("free",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	FREEDOM("freedom",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	FREEZ("freez",Properties.VERB,ServiceProperty.DSERVICE,WeightOriginalRange.NEGATIVESTABLE),
	FREQUENT("frequent",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	FRESH("fresh",Properties.ADJV,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	FRIEND("friend",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	FRIGHT("fright",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVEENHANCE),
	FROM("from",Properties.SPREP,ServiceProperty.LANDA,WeightOriginalRange.NEGATIVESTABLE),
	FRONT("front",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	FROZEN("frozen",Properties.ADJV,ServiceProperty.DSERVICE,WeightOriginalRange.NEGATIVEENHANCE),
	FRUIT("fruit",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	FRY("fry",Properties.VERB,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	FUEL("fuel",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	FULL("full",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	FUN("fun",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	FUNCTION("function",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	FUND("fund",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	FUNDAMENT("fundament",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	FUNERAL("funeral",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	FUNNY("funny",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	FUR("fur",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	FURNITURE("furniture",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	FURTH("furth",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	FUTURE("future",Properties.NOUN,ServiceProperty.RELIABILITY,WeightOriginalRange.POSITIVESTABLE),
//	
//Letter G starts here
//
	GAIN("gain",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	GAMBL("gambl",Properties.VERB,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	GAM("gam",Properties.VERB,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	GAP("gap",Properties.VERB,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.STABLE),
	GARAG("garag",Properties.VERB,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	GARBAGE("garbage",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	GARDEN("garden",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	GAS("gas",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	GASOLINE("gasoline",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	GAT("gat",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	GATHER("gather",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	GAY("gay",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	GEAR("gear",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	GENE("gene",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	GENRAL("general",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	GENERAT("generat",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	GENEROUS("generous",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	GENTLE("gentle",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	GENUINE("genuine",Properties.ADJV,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	GEOGRAPH("geograph",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	GET("get",Properties.VERB,ServiceProperty.LANDA,WeightOriginalRange.POSITIVESTABLE),
	GIANT("giant",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	GIFT("gift",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	GIRL("girl",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	GIRLFRIEND("girlfriend",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	GIV("giv",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	GLAD("glad",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	GLASS("glass",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	GLOBAL("global",Properties.ADJV,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVEENHANCE),
	GLOVE("glove",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	GLUE("glue",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	GO("go",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	GOAL("goal",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	GOD("god",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	GOLD("gold",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	GOOD("good",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	GOODBYE("goodbye",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	GOODS("goods",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	GOVERN("govern",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	GRAB("grab",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	GRAD("grad",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	GRADUAT("graduat",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVEENHANCE),
	GRAIN("grain",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	GRAMM("gramm",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	GRANDCHILD("grandchild",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	GRANDDAUGHTER("granddaughter",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	GRANDMOTHER("grandmother",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	GRANDPARENT("grandparent",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	GRANDSON("grandson",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	GRANT("grant",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	GRASS("grass",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	GRATE("grate",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	GRAVE("grave",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	GREAT("great",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	GREEN("green",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	GROCERY("grocery",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	GROUND("ground",Properties.NOUN,ServiceProperty.DURATION,WeightOriginalRange.POSITIVESTABLE),
	GROUP("group",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	GROW("grow",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	GROWTH("growth",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	GUARANTEE("guarantee",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	GUARD("guard",Properties.NOUN,ServiceProperty.SAFETY,WeightOriginalRange.POSITIVEENHANCE),
	GUESS("guess",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	GUID("guid",Properties.VERB,ServiceProperty.SERVICE,WeightOriginalRange.POSITIVEENHANCE),
	GUIDANCE("guidance",Properties.NOUN,ServiceProperty.SERVICE,WeightOriginalRange.POSITIVEENHANCE),
	GUILTY("guilty",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	GUITAR("guitar",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.STABLE),
	GUN("gun",Properties.NOUN,ServiceProperty.OTHER,WeightOriginalRange.POSITIVESTABLE),
	GUY("guy",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	GUZZL("guzzl",Properties.VERB,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVEENHANCE),
//	
//Letter H starts here
//	
	HABIT("habit",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	HAIR("hair",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	HAIRDRESSER("hairdresser",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	HAIRDRYER("hairdryer",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	HALF("half",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	HALL("hall",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	HAMMER("hammer",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	HAND("hand",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	HANDL("handl",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	HANG("hang",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	HAPPEN("happen",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	HAPPY("happy",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	HARD("hard",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	HARDLY("hardly",Properties.RPREP,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	HARM("harm",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	HAT("hat",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	HATE("hate",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	HATRED("hatred",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	HAV("hav",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	HE("he",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	HEAD("head",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	HEADACHE("headache",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVEENHANCE),
	HEAL("heal",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	HEALTH("health",Properties.NOUN,ServiceProperty.ASERVICE,WeightOriginalRange.POSITIVESTABLE),
	HEALTHCARE("healthcare",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVEENHANCE),
	HEAR("hear",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	HEART("heart",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVEENHANCE),
	HEAT("heat",Properties.VERB,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	HEAVEN("heaven",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESUPREME),
	HEAVY("heavy",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	HEEL("heel",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	HEIGHT("height",Properties.VERB,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	HELL("hell",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	HELLO("hello",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	HELP("help",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	HER("her",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	HERE("here",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	HERO("hero",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	HERSELF("herself",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	HESITAT("hesitat",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESLACK),
	HI("hi",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	HID("hid",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESLACK),
	HIGH("high",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	HIGHLIGHT("highlight",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	HIGHWAY("highway",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	HILL("hill",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	HIM("him",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	HIMSELF("himself",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	HIP("hip",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	HIR("hir",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	HIS("his",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	HISTORY("history",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	HISTORICAL("hitstorical",Properties.ADJV,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	HIT("hit",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVEENHANCE),
	HOBBY("hobby",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	HOLD("hold",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	HOLE("hole",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	HOLIDAY("holiday",Properties.NOUN,ServiceProperty.COST,WeightOriginalRange.POSITIVESLACK),
	HOLLOW("hollow",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	HOLY("holy",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	HOME("home",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVEENHANCE),
	HOMEWORK("homework",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	HONEST("honest",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	HONOR("honor",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	HOOK("hook",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	HOP("hop",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	HORIZON("horizon",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	HORN("horn",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	HORROR("horror",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESUPREME),
	HORSE("horse",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	HOSPITAL("hospital",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVEENHANCE),
	HOST("host",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	HOT("hot",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	HOTEL("hotel",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVEENHANCE),
	HOUR("hour",Properties.NOUN,ServiceProperty.DURATION,WeightOriginalRange.POSITIVESTABLE),
	HOUSE("house",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	HOUSEHOLD("household",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	HOW("how",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	HOWEVER("however",Properties.RPREP,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	HUGE("huge",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	HUMAN("human",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	HUMOR("humor",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	HUNGRY("hungry",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.NEGATIVEENHANCE),
	HUNT("hunt",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	HUNTAWAY("huntaway",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.STABLE),
	HURRY("hurry",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	HURT("hurt",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	HUSBAND("husband",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
//	
//Letter I starts here
//
	I("i",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	ICE("ice",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	ICECREAM("icecream",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	IDEA("idea",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	IDEAL("ideal",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	ID("id",Properties.NOUN,ServiceProperty.SAFETY,WeightOriginalRange.POSITIVEENHANCE),
	IDENTITY("identity",Properties.NOUN,ServiceProperty.SAFETY,WeightOriginalRange.POSITIVEENHANCE),
	IDENTIFY("identify",Properties.VERB,ServiceProperty.SAFETY,WeightOriginalRange.POSITIVEENHANCE),
	IE("ie",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	IF("if",Properties.CCONJ,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	IGNORE("ignor",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESLACK),
	ILL("ill",Properties.ADJV,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVEENHANCE),
	ILLUSTRAT("illustrat",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	IMAGE("image",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	IMAGINAT("imaginat",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESLACK),
	IMAGINARY("imaginary",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESLACK),
	IMAGIN("imagin",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	IMMEDIATE("immediate",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESUPREME),
	IMPACT("impact",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	IMPLICAT("implicat",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	IMPLY("imply",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	IMPORT("import",Properties.VERB,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.STABLE),
	IMPORTANT("important",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	IMPOS("impos",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	IMPRESS("impress",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	IMPROV("improv",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	IN("in",Properties.SPREP,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	INCIDENT("incident",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	INCLUD("includ",Properties.CCONJ,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	INCOM("incom",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	INCREAS("increas",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	INDEED("indeed",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	INDEX("index",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	INDICAT("indicat",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	INDIVIDUAL("individual",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	INDOOR("indoor",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	INDUSTR("industr",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	INFECT("infect",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	INFECTANT("infectant",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.NEGATIVESTABLE),
	INFLUENT("influent",Properties.ADJV,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVEENHANCE),
	INFORM("inform",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	INGREDIENT("ingredient",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	INITIAL("initial",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	INITIAT("initiat",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	INJUR("injur",Properties.VERB,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVEENHANCE),
	INK("ink",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	INNER("inner",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	INNOCENT("innocent",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	INNOCUOUS("innocuous",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESLACK),
	INSECT("insect",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESLACK),
	INSIDE("inside",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	INSIST("insist",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	INSTALL("install",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	INSTANCE("instance",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	INSTEAD("instead",Properties.RPREP,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	INSTITUT("institut",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	INSTRUMENT("instrument",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	INSULT("insult",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVEENHANCE),
	INSURANCE("insurance",Properties.NOUN,ServiceProperty.SAFETY,WeightOriginalRange.POSITIVEENHANCE),
	INTELLENT("intellent",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	INTELLIGENT("intelligent",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	INTEND("intend",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	INTENSE("intense",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	INTENTION("intention",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	INTEREST("interest",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	INTERIOR("interior",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	INTERNAL("internal",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	INTERNET("internet",Properties.NOUN,ServiceProperty.SERVICE,WeightOriginalRange.POSITIVESTABLE),
	INTERPRET("interpret",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	INTERRUPT("interrupt",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	INTERVAL("interval",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	INTERVIEW("interview",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	INTO("into",Properties.SPREP,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	INTRODUC("introduc",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	INVENT("invent",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	INVEST("invest",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	INVESTIGAT("investigat",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	INVITAT("invitat",Properties.VERB,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	INVOLV("involv",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	IRON("iron",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	IRRITAT("irritat",Properties.VERB,ServiceProperty.DSERVICE,WeightOriginalRange.NEGATIVEENHANCE),
	ISLAND("island",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	ISSU("issu",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESLACK),
	IT("it",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	ITEM("item",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	ITS("its",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	ITSELF("itself",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
//	
//Letter J starts here
//	
	JACKET("jacket",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	JAIL("jail",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	JEALOUS("jealous",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESLACK),
	JEAN("jean",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.STABLE),
	JELLY("jelly",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	JEWELRY("jewelry",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.STABLE),
	JOB("job",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	JOIN("join",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	JOINT("joint",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	JOK("jok",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	JOURNALIST("journalist",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVEENHANCE),
	JOURNEY("journey",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	JOY("joy",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	JUDG("judg",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	JUICE("juice",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	JUMP("jump",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	JUNIOR("junior",Properties.ADJV,ServiceProperty.BPROPERTY,WeightOriginalRange.POSITIVESLACK),
	JURY("jury",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	JUST("just",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	JUSTIC("justic",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVEENHANCE),
	JUSTIFY("justify",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
//	
//Letter K starts here
//
	KEEN("keen",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	KEEP("keep",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	KEY("key",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	KEYBOARD("keyboard",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	KICK("kick",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	KID("kid",Properties.NOUN,ServiceProperty.BPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	KILL("kill",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	KIND("kind",Properties.VERB,ServiceProperty.OTHER,WeightOriginalRange.POSITIVESTABLE),
	KING("king",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	KISS("kiss",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	KITCHEN("kitchen",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	KNEE("knee",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	KNIFE("knife",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	KNIVES("knives",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	KNOCK("knock",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	KNOT("knot",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	KNOW("know",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	KNOWLEDG("knowledg",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
//	
//Letter L starts here
//		
	LABEL("label",Properties.VERB,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	LABOR("labor",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	LABORATORY("laboratory",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	LACK("lack",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	LADY("lady",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	LAKE("lake",Properties.NOUN,ServiceProperty.ABPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	LAMP("lamp",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	LAND("land",Properties.VERB,ServiceProperty.DURATION,WeightOriginalRange.POSITIVESTABLE),
	LANDSCAPE("landscape",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	LANE("lane",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	LANGUAGE("language",Properties.VERB,ServiceProperty.SERVICE,WeightOriginalRange.POSITIVESTABLE),
	LARG("larg",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	LAST("last",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	LATE("late",Properties.ADJV,ServiceProperty.RELIABILITY,WeightOriginalRange.NEGATIVESTABLE),
	LATTER("latter",Properties.ADJV,ServiceProperty.RELIABILITY,WeightOriginalRange.NEGATIVESTABLE),
	LAUGH("laugh",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	LAUNCH("launch",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	LAUNDRY("laundry",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	LAW("law",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	LAWYER("lawyer",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	LAY("lay",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	LAYER("layer",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	LAZY("lazy",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	LEAD("lead",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	LEAF("leaf",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	LEAGU("leagu",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	LEAN("lean",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	LEARN("learn",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	LEAST("least",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESUPREME),
	LEATHER("leather",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	LEAV("leav",Properties.VERB,ServiceProperty.LANDA,WeightOriginalRange.NEGATIVESTABLE),
	LECTURE("lecture",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	LEFT("left",Properties.ADJV,ServiceProperty.BPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	LEG("leg",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	LEGAL("legal",Properties.ADJV,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	LEMON("lemon",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	LEMONADE("lemonade",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	LEND("lend",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	LENGTH("length",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	LESS("less",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVEENHANCE),
	LET("let",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	LETTER("letter",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	LEVEL("level",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.STABLE),
	LIBERAL("liberal",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	LIBRARY("library",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	LICENSE("license",Properties.NOUN,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	LID("lid",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	LIE("lie",Properties.VERB,ServiceProperty.OTHER,WeightOriginalRange.NEGATIVESLACK),
	LIFE("life",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	LIFT("lift",Properties.VERB,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	LIGHT("light",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	LIK("lik",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	LIMIT("limit",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	LINE("line",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.NEGATIVESLACK),
	LINK("link",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	LIP("lip",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	LIQUID("liquid",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	LIST("list",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	LISTEN("listen",Properties.VERB,ServiceProperty.ASERVICE,WeightOriginalRange.POSITIVESTABLE),
	LITERATURE("literature",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	LITTLE("little",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	LIV("liv",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	LOAD("load",Properties.VERB,ServiceProperty.ABPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	LOAN("loan",Properties.VERB,ServiceProperty.COST,WeightOriginalRange.POSITIVESLACK),
	LOCAL("local",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	LOCAT("locat",Properties.VERB,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	LOCK("lock",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	LOGIC("logic",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	LONE("lone",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	LONG("long",Properties.ADJV,ServiceProperty.DURATION,WeightOriginalRange.POSITIVEENHANCE),
	LOOK("look",Properties.VERB,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	LOOS("loos",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESLACK),
	LORD("lord",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	LOS("los",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	LOSS("loss",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	LOST("lost",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	LOT("lot",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	LOUD("loud",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	LOVE("love",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	LOW("low",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESLACK),
//	
//Letter M starts here
//
	MEAN("mean",Properties.VERB,ServiceProperty.OTHER,WeightOriginalRange.NEGATIVESTABLE),
	MEANWHILE("meanwhile",Properties.CCONJ,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	MEASUR("measur",Properties.VERB,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	MEATT("meat",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	MEDIA("media",Properties.VERB,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	MEDICAL("medical",Properties.ADJV,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVEENHANCE),
	MEDICINE("medicine",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVEENHANCE),
	MEDIUM("medium",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	MEET("meet",Properties.VERB,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	MELT("melt",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	MEMBER("member",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	MEMBERSHIP("membership",Properties.NOUN,ServiceProperty.COST,WeightOriginalRange.POSITIVESLACK),
	MEMORY("memory",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	MENTAL("mental",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	MENTION("mention",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	MENU("menu",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	MERE("mere",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	MESS("mess",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	MESSAG("messag",Properties.VERB,ServiceProperty.ASERVICE,WeightOriginalRange.POSITIVESTABLE),
	MASSAG("massag",Properties.VERB,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVEENHANCE),
	METAL("metal",Properties.ADJV,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	METHOD("method",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	MID("mid",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	MIDDLE("middle",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	MIDNIGHT("midnight",Properties.NOUN,ServiceProperty.LANDA,WeightOriginalRange.STABLE),
	MIGHT("might",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	MILITARY("military",Properties.NOUN,ServiceProperty.SAFETY,WeightOriginalRange.POSITIVESTABLE),
	MIL("mil",Properties.NOUN,ServiceProperty.MILEAGE,WeightOriginalRange.STABLE),
	MILE("mile",Properties.NOUN,ServiceProperty.MILEAGE,WeightOriginalRange.STABLE),
	MILAGE("milage",Properties.NOUN,ServiceProperty.MILEAGE,WeightOriginalRange.STABLE),
	MILEAGE("mileage",Properties.NOUN,ServiceProperty.MILEAGE,WeightOriginalRange.STABLE),
	MILK("milk",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	MIND("mind",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	MINE("mine",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	MINERAL("mineral",Properties.VERB,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	MINIMUM("minimum",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.STABLE),
	MINISTER("minister",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	MINISTRY("ministry",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	MINOR("minor",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	MINUT("minut",Properties.NOUN,ServiceProperty.DURATION,WeightOriginalRange.POSITIVESTABLE),
	MINORITY("minority",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	MISS("miss",Properties.VERB,ServiceProperty.RELIABILITY,WeightOriginalRange.NEGATIVEENHANCE),
	MIRROR("mirror",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	MISTAK("mistak",Properties.VERB,ServiceProperty.RELIABILITY,WeightOriginalRange.NEGATIVESTABLE),
	MISSING("missing",Properties.ADJV,ServiceProperty.RELIABILITY,WeightOriginalRange.NEGATIVEENHANCE),
	MIX("mix",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	MOBILE("mobile",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	MODEL("model",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	MODERN("modern",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	MOM("mom",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	MOMENT("moment",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	MONEY("money",Properties.NOUN,ServiceProperty.COST,WeightOriginalRange.POSITIVESTABLE),
	MONITOR("monitor",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	MONTH("month",Properties.NOUN,ServiceProperty.DURATION,WeightOriginalRange.POSITIVEENHANCE),
	MOOD("mood",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	MOON("moon",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	MORAL("moral",Properties.ADJV,ServiceProperty.REPUTATION,WeightOriginalRange.POSITIVESTABLE),
	MORE("more",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	MOREOVER("moreover",Properties.CCONJ,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	MORNING("morning",Properties.NOUN,ServiceProperty.LANDA,WeightOriginalRange.STABLE),
	MOST("most",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESUPREME),
	MOTHER("mother",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	MOTION("motion",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	MOTOR("motor",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	MOTORCYCLE("motorcycle",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	MOUNT("mount",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	MOUNTAIN("mountain",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVEENHANCE),
	MOUSE("mouse",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	MOUTH("mouth",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	MOVE("move",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	MOVIE("movie",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	MR("mr",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	MRS("mrs",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	MS("ms",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	MUCH("much",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	MANY("many",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	MUD("mud",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	MUDD("muddy",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	MUDL("mudl",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	MULTIPL("multipl",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	MURDER("murder",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	MUSCLE("muscle",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	MUSCULAR("musclar",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	MUSEUM("museum",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	MUSIC("music",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	MUST("must",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	BMYELONG("my",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	MYSTIC("mystic",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	MYSTERY("mystery",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
//	
//Letter N starts here
//	
	NAIL("nail",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	NAK("nak",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESLACK),
	NAM("nam",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	NARROW("narrow",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	NATION("nation",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	NATIVE("native",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	NATUR("natur",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVEENHANCE),
	NAVY("navy",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	NEAR("near",Properties.SPREP,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	NEARBY("nearby",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	NEARLY("nearly",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	NEAT("neat",Properties.ADJV,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVEENHANCE),
	NECESSARY("necessary",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	NECK("neck",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	NEED("need",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	NEEDLE("needle",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.NEGATIVESTABLE),
	NEGATIV("negativ",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVEENHANCE),
	NEIGHBOR("neighbor",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	NEITHER("neither",Properties.RPREP,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	NEPHEW("nephew",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	NERV("nerv",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	NEST("nest",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	NET("net",Properties.ADJV,ServiceProperty.ASERVICE,WeightOriginalRange.POSITIVESTABLE),
	NERVER("network",Properties.NOUN,ServiceProperty.ASERVICE,WeightOriginalRange.POSITIVESTABLE),
	NEVER("never",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	NEVERTHELESS("nevertheless",Properties.RPREP,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	NEW("new",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	NEWS("news",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	NEWSPAPER("newspaper",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	NEXT("next",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	NICE("nice",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	NIECE("niece",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	NIGHT("night",Properties.NOUN,ServiceProperty.LANDA,WeightOriginalRange.STABLE),
	NO("no",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	NOBODY("nobody",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	NOD("nod",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	NOIS("nois",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	NON("non",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESUPREME),
	NONSTOP("nonstop",Properties.ADJV,ServiceProperty.NOSTOP,WeightOriginalRange.POSITIVESTABLE),
	NONSENS("nonsens",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	NOR("nor",Properties.RPREP,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	NORMAL("normal",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	NORTH("north",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	NORTHERN("northern",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	NOSE("nose",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	NOT("not",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	NOTE("note",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	NOON("noon",Properties.NOUN,ServiceProperty.LANDA,WeightOriginalRange.STABLE),
	NOTEBOOK("notebook",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	NOTHING("nothing",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	NOTIC("notic",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	NOXIOUS("noxious",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	NOTION("notion",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	NOVEL("novel",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	NOW("now",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	NOWHERE("nowhere",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	NUCLEAR("nuclear",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVEENHANCE),
	NUMBER("number",Properties.NUMBER,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	NURSE("nurse",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.NEGATIVEENHANCE),
	NUT("nut",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
//	
//Letter O starts here
//
	OAT("oat",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	OBEY("obey",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	OBJECT("object",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	OBSERV("observ",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	OBTAIN("obtain",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	OBVIOUS("obvious",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	OCCASION("occasion",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	OCCUPY("occupy",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	OCCUR("occur",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	OCEAN("ocean",Properties.NOUN,ServiceProperty.AIRPORTUTILITY,WeightOriginalRange.POSITIVESTABLE),
	OCLOCK("oclock",Properties.NOUN,ServiceProperty.LANDA,WeightOriginalRange.POSITIVEENHANCE),
	ODD("odd",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	OF("of",Properties.SPREP,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	OFF("off",Properties.ADJV,ServiceProperty.LANDA,WeightOriginalRange.NEGATIVESTABLE),
	OFFEND("offend",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	OFFSENSIVE("offsensive",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	OFFER("offer",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	OFFICE("office",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	OFTEN("often",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	OH("oh",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	OIL("oil",Properties.NOUN,ServiceProperty.PACKAGERULE,WeightOriginalRange.POSITIVESTABLE),
	OK("ok",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	OLD("old",Properties.ADJV,ServiceProperty.OTHER,WeightOriginalRange.STABLE),
	ON("on",Properties.SPREP,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	OLDFASHION("oldfashion",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	ONCE("once",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	ONION("onion",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	ONLINE("online",Properties.ADJV,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	ONLY("only",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	ONTO("onto",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	OPEN("open",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	OPERAT("operat",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	OPINION("opinion",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	OPPONENT("opponent",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	OPPORTUNITY("opportunity",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	OPPOS("oppos",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	OPPOSIT("opposit",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	OPTION("option",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	OR("or",Properties.CCONJ,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	ORANGE("orange",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.STABLE),
	ORDER("order",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	ORDINARY("ordinary",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESLACK),
	ORGAN("organ",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	ORGANIZ("organiz",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	ORIGIN("origin",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.STABLE),
	OTHER("other",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	OTHERWISE("otherwise",Properties.RPREP,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	OUGHT("ought",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	OUR("our",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	OURSELF("ourself",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	OUT("out",Properties.SPREP,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVESTABLE),
	OUTDOOR("ourdoor",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	OUTER("outer",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	OUTLINE("outline",Properties.NOUN,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	OUTLOOK("outlook",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	OUTLYING("outlying",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.NEGATIVESTABLE),
	OUTPUT("output",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	OUTSIDE("outside",Properties.SPREP,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	OUTSTANDING("outstanding",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	OVEN("oven",Properties.NOUN,ServiceProperty.DSERVICE,WeightOriginalRange.POSITIVESTABLE),
	OVER("over",Properties.SPREP,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	OVERALL("overall",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	OVERCOM("overcom",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	OVERSEA("oversea",Properties.ADJV,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.POSITIVEENHANCE),
	OWE("owe",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS),
	OWN("own",Properties.VERB,ServiceProperty.GENERALPROPERTY,WeightOriginalRange.USELESS)


	;
	
	
	
	/**  image of core-word*/
	private String image;
	
	/**  basic property of core-word in type of Properties */
	private Properties basicProperty;
	
	/**  basic function meaning of core-word, will not change in the future*/
	private ServiceProperty basicFunctiontype;
	
	/**  basic weight of core-word */
	private WeightOriginalRange weight;
	
	/**  if core-word is special meaningful in number like number, month, will has multiple weight instead of basic weight   */
	private float multipleweight;

	 /**  
	  * constructor for non-number CoreMeaning
	  * 
	  * @param image
	  * @param basicProperty
	  * @param basicFunctiontype
	  * @param weight
	  * 		basic weight
	  * 		multiple weight is defined as the same with basic weight
	  */
	private CoreMeaningOne(String image,Properties basicProperty,ServiceProperty basicFunctiontype,WeightOriginalRange weight)
	{
		this.image = image;
		this.basicProperty=basicProperty;
		this.basicFunctiontype=basicFunctiontype;
		this.weight = weight;
		this.multipleweight = weight.getWeight();
	}

	 /**  
	  * constructor for number CoreMeaning
	  * 
	  * @param image
	  * @param basicProperty
	  * @param basicFunctiontype
	  * @param weight
	  * 		multiple weight is defined as the same with basic weight
	  * 		no basic weight is declared here, define it in WeightOriginalRange.POSITIVESTABLE by default.
	  */
	private CoreMeaningOne(String image,Properties basicProperty,ServiceProperty basicFunctiontype,float weight)
	{
		this.image = image;
		this.basicProperty=basicProperty;
		this.basicFunctiontype=basicFunctiontype;
		this.weight = WeightOriginalRange.POSITIVESTABLE;
		this.multipleweight = weight;
	}

	 /**  
	  * get image of CoreMeaning instance
	  * 
	  * @return image in String type
	  */
	public String getImage()
	{
		return this.image;
	}

	 /**  
	  * get basic property of CoreMeaning instance
	  * 
	  * @return basic property in Properties type
	  */
	public Properties getBasicProperty()
	{
		return this.basicProperty;
	}
	
	 /**  
	  * get basic weight of CoreMeaning instance
	  * 
	  * @return basic weight in WeightOriginalRange type
	  */
	public WeightOriginalRange getWeight()
	{
		return this.weight;
	}
	
	 /**  
	  * get service function type of CoreMeaning instance
	  * 
	  * @return service function type in ServiceProperty type
	  */
	public ServiceProperty getBasicFunctionType()
	{
		return this.basicFunctiontype;
	}
	
	 /**  
	  * get multiple weight of CoreMeaning instance
	  * 
	  * @return multiple weight in float type
	  */
	public float getMultipleWeight()
	{
		return this.multipleweight;
	}
	
	 /**  
	  * set up multiple weight of CoreMeaning instance
	  * 
	  * @param multipleweight
	  * 			 multiple weight in float type
	  */
	public void setMultipleWeight(float multipleweight)
	{
		this.multipleweight = multipleweight;
	}
	
	 /**  
	  * set up basic property type of CoreMeaning instance
	  * 
	  * @return basicProperty
	  * 			basic property in Properties type
	  */
	public void setBasicProperty(Properties basicProperty)
	{
		this.basicProperty=basicProperty;
	}
	
	 /**  
	  * set up service function type of CoreMeaning instance
	  * 
	  * @return basicFunctiontype
	  * 			service function type in ServiceProperty type
	  */
	public void setBasicFunctionType(ServiceProperty basicFunctiontype)
	{
		this.basicFunctiontype=basicFunctiontype;
	}
	
	 /**  
	  * set up basic weight of CoreMeaning instance
	  * 
	  * @return weight
	  * 			basic weight in WeightOriginalRange type
	  */
	public void setWeight(WeightOriginalRange weight)
	{
		this.weight = weight;
	}
	
}

/**  
 * a class to manage the CoreMeaning into 26 HashMap to increase the speed of matching
 */
public class TokenKind {
	
	/** a static flag to show if static maps is created or not */
	private static boolean mapAlreadyGenerated = false;
	
	/** HashMap of HashMap to manage all CoreMeaning elements, static variable, all HashMap inside are static also*/
	private static Map<String,Map<String,CoreMeaning>> mapOfMapOfCoreVacabulary = new  HashMap<String,Map<String,CoreMeaning>>();
	
	/**  HashMap of CoreMeaning elements start with 'a'*/
	private static Map<String,CoreMeaning> coreVacabularyStartWithA = new HashMap<String,CoreMeaning>();
	
	/**  HashMap of CoreMeaning elements start with 'b'*/
	private static Map<String,CoreMeaning> coreVacabularyStartWithB= new HashMap<String,CoreMeaning>();
	
	/**  HashMap of CoreMeaning elements start with 'c'*/
	private static Map<String,CoreMeaning> coreVacabularyStartWithC= new HashMap<String,CoreMeaning>();
	
	/**  HashMap of CoreMeaning elements start with 'd'*/
	private static Map<String,CoreMeaning> coreVacabularyStartWithD= new HashMap<String,CoreMeaning>();
	
	/**  HashMap of CoreMeaning elements start with 'e'*/
	private static Map<String,CoreMeaning> coreVacabularyStartWithE= new HashMap<String,CoreMeaning>();
	
	/**  HashMap of CoreMeaning elements start with 'f'*/
	private static Map<String,CoreMeaning> coreVacabularyStartWithF= new HashMap<String,CoreMeaning>();
	
	/**  HashMap of CoreMeaning elements start with 'g'*/
	private static Map<String,CoreMeaning> coreVacabularyStartWithG= new HashMap<String,CoreMeaning>();
	
	/**  HashMap of CoreMeaning elements start with 'h'*/
	private static Map<String,CoreMeaning> coreVacabularyStartWithH= new HashMap<String,CoreMeaning>();
	
	/**  HashMap of CoreMeaning elements start with 'i'*/
	private static Map<String,CoreMeaning> coreVacabularyStartWithI= new HashMap<String,CoreMeaning>();
	
	/**  HashMap of CoreMeaning elements start with 'j'*/
	private static Map<String,CoreMeaning> coreVacabularyStartWithJ= new HashMap<String,CoreMeaning>();
	
	/**  HashMap of CoreMeaning elements start with 'k'*/
	private static Map<String,CoreMeaning> coreVacabularyStartWithK= new HashMap<String,CoreMeaning>();
	
	/**  HashMap of CoreMeaning elements start with 'l'*/
	private static Map<String,CoreMeaning> coreVacabularyStartWithL= new HashMap<String,CoreMeaning>();
	
	/**  HashMap of CoreMeaning elements start with 'm'*/
	private static Map<String,CoreMeaning> coreVacabularyStartWithM= new HashMap<String,CoreMeaning>();
	
	/**  HashMap of CoreMeaning elements start with 'n'*/
	private static Map<String,CoreMeaning> coreVacabularyStartWithN= new HashMap<String,CoreMeaning>();
	
	/**  HashMap of CoreMeaning elements start with 'o'*/
	private static Map<String,CoreMeaning> coreVacabularyStartWithO= new HashMap<String,CoreMeaning>();
	
	/**  HashMap of CoreMeaning elements start with 'p'*/
	private static Map<String,CoreMeaning> coreVacabularyStartWithP= new HashMap<String,CoreMeaning>();
	
	/**  HashMap of CoreMeaning elements start with 'q'*/
	private static Map<String,CoreMeaning> coreVacabularyStartWithQ= new HashMap<String,CoreMeaning>();	
	
	/**  HashMap of CoreMeaning elements start with 'r'*/
	private static Map<String,CoreMeaning> coreVacabularyStartWithR= new HashMap<String,CoreMeaning>();
	
	/**  HashMap of CoreMeaning elements start with 's'*/
	private static Map<String,CoreMeaning> coreVacabularyStartWithS= new HashMap<String,CoreMeaning>();
	
	/**  HashMap of CoreMeaning elements start with 't'*/
	private static Map<String,CoreMeaning> coreVacabularyStartWithT= new HashMap<String,CoreMeaning>();
	
	/**  HashMap of CoreMeaning elements start with 'u'*/
	private static Map<String,CoreMeaning> coreVacabularyStartWithU= new HashMap<String,CoreMeaning>();
	
	/**  HashMap of CoreMeaning elements start with 'v'*/
	private static Map<String,CoreMeaning> coreVacabularyStartWithV= new HashMap<String,CoreMeaning>();
	
	/**  HashMap of CoreMeaning elements start with 'w'*/
	private static Map<String,CoreMeaning> coreVacabularyStartWithW= new HashMap<String,CoreMeaning>();
	
	/**  HashMap of CoreMeaning elements start with 'x'*/
	private static Map<String,CoreMeaning> coreVacabularyStartWithX= new HashMap<String,CoreMeaning>();
	
	/**  HashMap of CoreMeaning elements start with 'y'*/
	private static Map<String,CoreMeaning> coreVacabularyStartWithY= new HashMap<String,CoreMeaning>();
	
	/**  HashMap of CoreMeaning elements start with 'z'*/
	private static Map<String,CoreMeaning> coreVacabularyStartWithZ= new HashMap<String,CoreMeaning>();

	 /**  
	  * a static method to put all CoreMeaning elements into the map belonged to
	  */
	public static void putInMapByFirstLetter()
	
	{
	CoreMeaning[] interactionOfCoremeaningOne = CoreMeaningOne.values();
	CoreMeaning[] interactionOfCoremeaningTwo = CoreMeaningTwo.values();
	for(CoreMeaning coreMeaning :interactionOfCoremeaningOne)
	{
		if(coreMeaning.getImage().startsWith("a"))
		{
			coreVacabularyStartWithA.put(coreMeaning.getImage(),coreMeaning);
		}
		else if(coreMeaning.getImage().startsWith("b",0))
			coreVacabularyStartWithB.put(coreMeaning.getImage(),coreMeaning);
		else if(coreMeaning.getImage().startsWith("c",0))
			coreVacabularyStartWithC.put(coreMeaning.getImage(),coreMeaning);
		else if(coreMeaning.getImage().startsWith("d",0))
			coreVacabularyStartWithD.put(coreMeaning.getImage(),coreMeaning);
		else if(coreMeaning.getImage().startsWith("e",0))
			coreVacabularyStartWithE.put(coreMeaning.getImage(),coreMeaning);
		else if(coreMeaning.getImage().startsWith("f",0))
			coreVacabularyStartWithF.put(coreMeaning.getImage(),coreMeaning);
		else if(coreMeaning.getImage().startsWith("g",0))
			coreVacabularyStartWithG.put(coreMeaning.getImage(),coreMeaning);
		else if(coreMeaning.getImage().startsWith("h",0))
			coreVacabularyStartWithH.put(coreMeaning.getImage(),coreMeaning);
		else if(coreMeaning.getImage().startsWith("i",0))
			coreVacabularyStartWithI.put(coreMeaning.getImage(),coreMeaning);
		else if(coreMeaning.getImage().startsWith("j",0))
			coreVacabularyStartWithJ.put(coreMeaning.getImage(),coreMeaning);
		else if(coreMeaning.getImage().startsWith("k",0))
			coreVacabularyStartWithK.put(coreMeaning.getImage(),coreMeaning);
		else if(coreMeaning.getImage().startsWith("l",0))
			coreVacabularyStartWithL.put(coreMeaning.getImage(),coreMeaning);
		else if(coreMeaning.getImage().startsWith("m",0))
			coreVacabularyStartWithM.put(coreMeaning.getImage(),coreMeaning);
		else if(coreMeaning.getImage().startsWith("n",0))
			coreVacabularyStartWithN.put(coreMeaning.getImage(),coreMeaning);
		else if(coreMeaning.getImage().startsWith("o",0))
			coreVacabularyStartWithO.put(coreMeaning.getImage(),coreMeaning);
		else if(coreMeaning.getImage().startsWith("p",0))
			coreVacabularyStartWithP.put(coreMeaning.getImage(),coreMeaning);
		else if(coreMeaning.getImage().startsWith("q",0))
			coreVacabularyStartWithQ.put(coreMeaning.getImage(),coreMeaning);
		else if(coreMeaning.getImage().startsWith("r",0))
			coreVacabularyStartWithR.put(coreMeaning.getImage(),coreMeaning);
		else if(coreMeaning.getImage().startsWith("s",0))
			coreVacabularyStartWithS.put(coreMeaning.getImage(),coreMeaning);
		else if(coreMeaning.getImage().startsWith("t",0))
			coreVacabularyStartWithT.put(coreMeaning.getImage(),coreMeaning);
		else if(coreMeaning.getImage().startsWith("u",0))
			coreVacabularyStartWithU.put(coreMeaning.getImage(),coreMeaning);
		else if(coreMeaning.getImage().startsWith("v",0))
			coreVacabularyStartWithV.put(coreMeaning.getImage(),coreMeaning);
		else if(coreMeaning.getImage().startsWith("w",0))
			coreVacabularyStartWithW.put(coreMeaning.getImage(),coreMeaning);
		else if(coreMeaning.getImage().startsWith("x",0))
			coreVacabularyStartWithX.put(coreMeaning.getImage(),coreMeaning);
		else if(coreMeaning.getImage().startsWith("y",0))
			coreVacabularyStartWithY.put(coreMeaning.getImage(),coreMeaning);
		else 
			coreVacabularyStartWithZ.put(coreMeaning.getImage(),coreMeaning);
		
	}
	for(CoreMeaning coreMeaning :interactionOfCoremeaningTwo)
	{
		if(coreMeaning.getImage().startsWith("a"))
		{
			coreVacabularyStartWithA.put(coreMeaning.getImage(),coreMeaning);
		}
		else if(coreMeaning.getImage().startsWith("b",0))
			coreVacabularyStartWithB.put(coreMeaning.getImage(),coreMeaning);
		else if(coreMeaning.getImage().startsWith("c",0))
			coreVacabularyStartWithC.put(coreMeaning.getImage(),coreMeaning);
		else if(coreMeaning.getImage().startsWith("d",0))
			coreVacabularyStartWithD.put(coreMeaning.getImage(),coreMeaning);
		else if(coreMeaning.getImage().startsWith("e",0))
			coreVacabularyStartWithE.put(coreMeaning.getImage(),coreMeaning);
		else if(coreMeaning.getImage().startsWith("f",0))
			coreVacabularyStartWithF.put(coreMeaning.getImage(),coreMeaning);
		else if(coreMeaning.getImage().startsWith("g",0))
			coreVacabularyStartWithG.put(coreMeaning.getImage(),coreMeaning);
		else if(coreMeaning.getImage().startsWith("h",0))
			coreVacabularyStartWithH.put(coreMeaning.getImage(),coreMeaning);
		else if(coreMeaning.getImage().startsWith("i",0))
			coreVacabularyStartWithI.put(coreMeaning.getImage(),coreMeaning);
		else if(coreMeaning.getImage().startsWith("j",0))
			coreVacabularyStartWithJ.put(coreMeaning.getImage(),coreMeaning);
		else if(coreMeaning.getImage().startsWith("k",0))
			coreVacabularyStartWithK.put(coreMeaning.getImage(),coreMeaning);
		else if(coreMeaning.getImage().startsWith("l",0))
			coreVacabularyStartWithL.put(coreMeaning.getImage(),coreMeaning);
		else if(coreMeaning.getImage().startsWith("m",0))
			coreVacabularyStartWithM.put(coreMeaning.getImage(),coreMeaning);
		else if(coreMeaning.getImage().startsWith("n",0))
			coreVacabularyStartWithN.put(coreMeaning.getImage(),coreMeaning);
		else if(coreMeaning.getImage().startsWith("o",0))
			coreVacabularyStartWithO.put(coreMeaning.getImage(),coreMeaning);
		else if(coreMeaning.getImage().startsWith("p",0))
			coreVacabularyStartWithP.put(coreMeaning.getImage(),coreMeaning);
		else if(coreMeaning.getImage().startsWith("q",0))
			coreVacabularyStartWithQ.put(coreMeaning.getImage(),coreMeaning);
		else if(coreMeaning.getImage().startsWith("r",0))
			coreVacabularyStartWithR.put(coreMeaning.getImage(),coreMeaning);
		else if(coreMeaning.getImage().startsWith("s",0))
			coreVacabularyStartWithS.put(coreMeaning.getImage(),coreMeaning);
		else if(coreMeaning.getImage().startsWith("t",0))
			coreVacabularyStartWithT.put(coreMeaning.getImage(),coreMeaning);
		else if(coreMeaning.getImage().startsWith("u",0))
			coreVacabularyStartWithU.put(coreMeaning.getImage(),coreMeaning);
		else if(coreMeaning.getImage().startsWith("v",0))
			coreVacabularyStartWithV.put(coreMeaning.getImage(),coreMeaning);
		else if(coreMeaning.getImage().startsWith("w",0))
			coreVacabularyStartWithW.put(coreMeaning.getImage(),coreMeaning);
		else if(coreMeaning.getImage().startsWith("x",0))
			coreVacabularyStartWithX.put(coreMeaning.getImage(),coreMeaning);
		else if(coreMeaning.getImage().startsWith("y",0))
			coreVacabularyStartWithY.put(coreMeaning.getImage(),coreMeaning);
		else 
			coreVacabularyStartWithZ.put(coreMeaning.getImage(),coreMeaning);
		
	}
	mapOfMapOfCoreVacabulary.put("a", coreVacabularyStartWithA);
	mapOfMapOfCoreVacabulary.put("b", coreVacabularyStartWithB);
	mapOfMapOfCoreVacabulary.put("c", coreVacabularyStartWithC);
	mapOfMapOfCoreVacabulary.put("d", coreVacabularyStartWithD);
	mapOfMapOfCoreVacabulary.put("e", coreVacabularyStartWithE);
	mapOfMapOfCoreVacabulary.put("f", coreVacabularyStartWithF);
	mapOfMapOfCoreVacabulary.put("g", coreVacabularyStartWithG);
	mapOfMapOfCoreVacabulary.put("h", coreVacabularyStartWithH);
	mapOfMapOfCoreVacabulary.put("i", coreVacabularyStartWithI);
	mapOfMapOfCoreVacabulary.put("j", coreVacabularyStartWithJ);
	mapOfMapOfCoreVacabulary.put("k", coreVacabularyStartWithK);
	mapOfMapOfCoreVacabulary.put("l", coreVacabularyStartWithL);
	mapOfMapOfCoreVacabulary.put("m", coreVacabularyStartWithM);
	mapOfMapOfCoreVacabulary.put("n", coreVacabularyStartWithN);
	mapOfMapOfCoreVacabulary.put("o", coreVacabularyStartWithO);
	mapOfMapOfCoreVacabulary.put("p", coreVacabularyStartWithP);
	mapOfMapOfCoreVacabulary.put("q", coreVacabularyStartWithQ);
	mapOfMapOfCoreVacabulary.put("r", coreVacabularyStartWithR);
	mapOfMapOfCoreVacabulary.put("s", coreVacabularyStartWithS);
	mapOfMapOfCoreVacabulary.put("t", coreVacabularyStartWithT);
	mapOfMapOfCoreVacabulary.put("u", coreVacabularyStartWithU);
	mapOfMapOfCoreVacabulary.put("v", coreVacabularyStartWithV);
	mapOfMapOfCoreVacabulary.put("w", coreVacabularyStartWithW);
	mapOfMapOfCoreVacabulary.put("x", coreVacabularyStartWithX);
	mapOfMapOfCoreVacabulary.put("y", coreVacabularyStartWithY);
	mapOfMapOfCoreVacabulary.put("z", coreVacabularyStartWithZ);
	mapAlreadyGenerated = true;
	}
	
	 /**  
	  * get the HashMap of CoreMeaning elements with first letter as the same as what input
	  * 
	  * @param firstLetter
	  * 			first letter in String type
	  * 
	  * @return HashMap of all CoreMeaning starts with firstLetter
	  */
	public static Map<String,CoreMeaning> getCoreVacabularyByLetter(String firstLetter)
	{
		if(mapAlreadyGenerated == true)
		return mapOfMapOfCoreVacabulary.get(firstLetter);
		else 
		{	putInMapByFirstLetter();
			return mapOfMapOfCoreVacabulary.get(firstLetter);
		}
	}
	
	 /**  
	  * get all CoreMeaning HashMaps as HashMap
	  * 
	  * @return static HashMap of HashMap
	  */
	public static Map<String,Map<String,CoreMeaning>> getCoreWholeVacabulary()
	{
		if(mapAlreadyGenerated == true)
			return mapOfMapOfCoreVacabulary;
			else 
			{	putInMapByFirstLetter();
				return mapOfMapOfCoreVacabulary;
			}
	}
}

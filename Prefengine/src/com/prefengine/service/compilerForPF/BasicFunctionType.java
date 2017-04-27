package com.prefengine.service.compilerForPF;

import java.util.ArrayList;

/**
 *  An interface of all service property sub-class and enum ServiceProperty
 */
public interface BasicFunctionType 
{
	/**
	 * get service-function of this service type.    
	 * 
	 * @return  ServiceProperty instance
	 */
	public  ServiceProperty getProperty();
	//public void setWeight(float fixWeight);
	//public float getWeight();
	}

/**
 *  Define all possible service properties with simple explanation and father service-property if there is one
 */
enum ServiceProperty implements BasicFunctionType
{
	GENERALPROPERTY("general property",null),
	ABPROPERTY("abstract property",GENERALPROPERTY),
	BPROPERTY("basic property",GENERALPROPERTY),
	REPUTATION("reputation",ABPROPERTY),
	CONVENIENT("convenient",ABPROPERTY),
	AIRPORTUTILITY("airportutility",ABPROPERTY),
	LAYOUT("layout",BPROPERTY),
	SAFETY("safety",ABPROPERTY),
	ROUNDTRIP("round trip",BPROPERTY),
	SEATCLASS("seatclass",BPROPERTY),
	COST("cost",BPROPERTY),
	LANDA("leave and arrive place or time", BPROPERTY),
	DURATION("duration",BPROPERTY),
	NOSTOP("number of stop",BPROPERTY),
	SERVICE("service",ABPROPERTY),
	RELIABILITY("reliability",ABPROPERTY),
	ASERVICE("after & before service",SERVICE),	
	DSERVICE("during-fly service",SERVICE),
	PACKAGERULE("package rule", DSERVICE),
	OTHER("other",GENERALPROPERTY);
	
	/** The explanation of individual service-property element  */
	private String explanation;
	
	/** The parent-property of individual service-property element  */
	private ServiceProperty parentProperty;		
	
	/**
	 * Construct a ServiceProperty.   
	 * 
	 *  @param explanation
	 *  @param parentProperty
	 *  			the parent property of this element
	 */
	private ServiceProperty(String explanation,ServiceProperty parentProperty)
	{
		this.explanation = explanation;
		this.parentProperty = parentProperty;
	}
	
	/**
	 * get explanation of this service type.    
	 * 
	 * @return explanation in String
	 */
	public String getExplanation()
	{
		return this.explanation;
	}
	
	/**
	 * get parent service-function of this service type.    
	 * 
	 * @return  ServiceProperty instance
	 */
	public ServiceProperty getParentProperty()
	{
		return this.parentProperty;
	}
	
	/**
	 * set parent service-function of this service type.    
	 * 
	 * @param  parentProperty
	 * 				ServiceProperty instance
	 */
	public void setParentProperty(ServiceProperty parentProperty)
	{
		this.parentProperty = parentProperty;
	}

	@Override
	public ServiceProperty getProperty() {
		// TODO Auto-generated method stub
		return this
				;
	}

//	@Override
//	public void setWeight(float fixWeight) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public float getWeight() {
//		// TODO Auto-generated method stub
//		return 1f;
//	}
	}

/**
 * A abstract class as a father class of all function-type sub-classes( those sub-classes has specific do-method for individual function-type).  
 */
 abstract class abstractFunctionType implements BasicFunctionType{

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
	public abstractFunctionType(ServiceProperty serviceProperty)
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
	public ServiceProperty getServiceProperty ()
	{
		return this.serviceProperty;
	}

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

/**  
 * sub-class of FunctionType with ServiceProperty.GENERALPROPERTY	
 */
 class GeneralPropertyFunctionType extends abstractFunctionType
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
 
 
 /**  
  * sub-class of FunctionType with ServiceProperty.ROUNDTRIP	
  */
 class RoundTripFunctionType extends abstractFunctionType
 {
	 /**  is round trip or not */
	 boolean isRoundTrip;
	 /**  contain the back trip request clause */
	 private  ArrayList<TokenGeneralKind> backTripRequest;
	 
	 /**  constructor for RoundTripFunctionType
	  * define single trip by default
	  * 
	  * @param serviceProperty
	  * 			basic property
	  */
	public RoundTripFunctionType(ServiceProperty serviceProperty) {
		super(serviceProperty);
		// TODO Auto-generated constructor stub
		this.isRoundTrip = false;
	}

	 /**  
	  * setup round trip
	  * 
	  * @param  isRoundTrip
	  * 			boolean to describe is round trip or not
	  */
	public void setRoundtrip(boolean isRoundTrip)
	{
		this.isRoundTrip = isRoundTrip;
	}

	 /**  
	  * setup back trip request of a round trip
	  * 
	  * @param  backTripRequest
	  * 			clause of request about back trip stored in ArrayList
	  */
	public void  setBackTripRequest( ArrayList<TokenGeneralKind> backTripRequest)
	{
		 this.backTripRequest = backTripRequest;
	}

	 /**  
	  * get back trip request of a round trip
	  * 
	  * @return  clause of request about back trip stored in ArrayList
	  */
	public ArrayList<TokenGeneralKind>  getBackTripRequest( )
	{
		 return this.backTripRequest;
	}

	 /**  
	  * get round trip
	  * 
	  * @return  boolean to describe is round trip or not
	  */
	public boolean getRoundtrip()
	{
		return this.isRoundTrip ;
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
		return ServiceProperty.ROUNDTRIP;
	}	 
 }
 
 /**  
  * sub-class of FunctionType with ServiceProperty.SEATCLASS	
  */
 class SeatClassFunctionType extends abstractFunctionType
 {	
	/** seat class in String  */
	private String seatClass;
	 
	 /**  constructor for SeatClassFunctionType
	  * define economy seat class by default
	  * 
	  * @param serviceProperty
	  * 			basic property
	  */
	public SeatClassFunctionType(ServiceProperty serviceProperty) {
		super(serviceProperty);
		// TODO Auto-generated constructor stub
		this.seatClass = "economy";
	}

	 /**  
	  * set up seat class
	  * 
	  * @param  seatClass
	  * 			String to describe seat class
	  */
	public void setSeatClass(String seatClass)
	{	this.seatClass = seatClass;
	}

	 /**  
	  * get seat class
	  * 
	  * @return  String to describe seat class
	  */
	public String getSeatClass()
	{	return this.seatClass ;
	}
	
	/**  
	  * function method for this class leave blank for now 
	  * @Override
	  */@Override
	public void functionMethod() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ServiceProperty getProperty() {
		// TODO Auto-generated method stub
		return ServiceProperty.SEATCLASS;
	}	 
 }
 
 /**  
  * sub-class of FunctionType with ServiceProperty.ABPROPERTY	
  */
 class AbstractPropertyFunctionType extends abstractFunctionType
 {
	 /**  
	  * constructor for AbstractPropertyFunctionType
	  * 
	  * @param serviceProperty
	  * 			basic property
	  */
	public AbstractPropertyFunctionType(ServiceProperty serviceProperty) {
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
		return ServiceProperty.ABPROPERTY;
	}	 
 }
 
 /**  
  * sub-class of FunctionType with ServiceProperty.BPROPERTY	
  */
 class BasicPropertyFunctionType extends abstractFunctionType
 {
	 /**  
	  * constructor for BasicPropertyFunctionType
	  * 
	  * @param serviceProperty
	  * 			basic property
	  */
	public BasicPropertyFunctionType(ServiceProperty serviceProperty) {
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
		return ServiceProperty.BPROPERTY;
	}	 
 } 
 
 /**  
  * sub-class of FunctionType with ServiceProperty.PACKAGERULE	
  */
 class PackageRuleFunctionType extends abstractFunctionType
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
 
 /**  
  * sub-class of FunctionType with ServiceProperty.REPUTATION	
  */
 class ReputationFunctionType extends abstractFunctionType
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
 
 /**  
  * sub-class of FunctionType with ServiceProperty.CONVENIENT	
  */
 class ConvenientFunctionType extends abstractFunctionType
 {
	 /**  
	  * constructor for ConvenientFunctionType
	  * 
	  * @param serviceProperty
	  * 			basic property
	  */
	public ConvenientFunctionType(ServiceProperty serviceProperty) {
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
		return ServiceProperty.CONVENIENT	;
	}	 
 }
 
 /**  
  * sub-class of FunctionType with ServiceProperty.AIRPORTUTILITY	
  */
 class AirportUtilityFunctionType extends abstractFunctionType
 {
	 /**  
	  * constructor for AirportUtilityFunctionType
	  * 
	  * @param serviceProperty
	  * 			basic property
	  */
	public AirportUtilityFunctionType(ServiceProperty serviceProperty) {
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
		return ServiceProperty.AIRPORTUTILITY;
	}	 
 }
 
 /**  
  * sub-class of FunctionType with ServiceProperty.SAFETY	
  */
 class SafetyFunctionType extends abstractFunctionType
 {
	 /**  
	  * constructor for SafetyFunctionType
	  * 
	  * @param serviceProperty
	  * 			basic property
	  */
	public SafetyFunctionType(ServiceProperty serviceProperty) {
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
		return ServiceProperty.SAFETY;
	}	 
 }
 
 /**  
  * sub-class of FunctionType with ServiceProperty.COST	
  */
 class CostFunctionType extends abstractFunctionType
 {
	 /** store maximum price and minimum price from user */
	 private float[] priceRange = new float[2];

	 /** if generalPriceRequest != 0, means user only request like "cheap price" without exactly price range
	 * 
	 *  with range of [0,1f], represents the price range of all tickets' price. example: if generalPriceRequest = 0.3,
	 *  and all tickets price between [100,300], system will get tickets between [100,160] 
	 *   */
	 private float generalPriceRequest;
	 
	 /**  
	  * constructor for CostFunctionType
	  * define maximum and minimum to 0; and general price range to 1f
	  * 
	  * @param serviceProperty
	  * 			basic property
	  */
	 public CostFunctionType(ServiceProperty serviceProperty) {
		super(serviceProperty);
		// TODO Auto-generated constructor stub
		this.priceRange[0] = 0f;
		this.priceRange[1] = 0f;
		this.generalPriceRequest = 1f;
	}

		/**  
		  * set up for general price range
		  * 
		  * @param generalPriceRequest
		  * 			
		  */
	 public void setgeneralPriceRequest(float generalPriceRequest)
	{	this.generalPriceRequest = generalPriceRequest;
	}
	 /**  
	  * get general price range
	  * 
	  * @return general Price range
	  * 			
	  */
	 public float getgeneralPriceRequest()
	{	return this.generalPriceRequest;
	}
		
	 /**  
	  * set up maximum and minimum price range
	  * 
	  * @param priceRange
	  * 			Price range in Array type
	  * 			
	  */
	public void setPriceRange(float[] priceRange)
	{	this.priceRange = priceRange;
	}
	
	 /**  
	  * get maximum and minimum price range
	  * 
	  * @return Price range in Array type
	  * 			
	  */
	public float[] getPriceRange()
	{	return this.priceRange;
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
		return ServiceProperty.COST	;
	}	 
 }
 
 /**  
  * sub-class of FunctionType with ServiceProperty.LAYOUT	
  */
 class LayoutFunctionType extends abstractFunctionType
 {
	 /** store layout in hour */
	 private float layoutInHour;
	 
	 /** store layout in Possibility */
	 private float layoutInPossibility;
	 
	 /** when user only define possibility and no specific hour number, turn true */
	 private boolean hasPossibility;
	 
	 /**  
	  * constructor for LayoutFunctionType
	  * 
	  * @param serviceProperty
	  * 			basic property
	  */
	public LayoutFunctionType(ServiceProperty serviceProperty) {
		
		super(serviceProperty);
		// TODO Auto-generated constructor stub
		hasPossibility = false;
	}
	
	 /**  
	  * set up layout in Possibility
	  * @param layoutInPossibility
	  * 			float type			
	  */
	public void setLayoutInPossibility(float layoutInPossibility)
	{
		this.layoutInPossibility = layoutInPossibility;
		hasPossibility = true;
	}
	 /**  
	  * get if the information stored in possibility or specific hour number 
	  * @return when stored in possibility return true			
	  */
	public boolean hasPossibility()
	{
		return this.hasPossibility;
	}
	
	 /**  
	  * get layout in Possibility
	  * @return float type			
	  */
	public float getLayoutInPossibility()
	{
		return this.layoutInPossibility ;
	}	
	 /**  
	  * set up layout in hour
	  * @param layoutInHour
	  * 			float type			
	  */
	public void setLayoutInHour(float layoutInHour)
	{
		this.layoutInHour = layoutInHour;
	}
	
	 /**  
	  * get layout in hour
	  * @return float type			
	  */
	public float getLayoutInHour()
	{
		return this.layoutInHour ;
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
		return ServiceProperty.LAYOUT;
	}	 
 }
 /**  
  * sub-class of FunctionType with ServiceProperty.DURATION	
  */
 class DurationFunctionType extends abstractFunctionType
 {
	 /** store duration in hour */
	 private float durationInHour;
	 
	 /** store duration in Possibility */
	 private float durationInPossibility;
	 
	 /** when user only define possibility and no specific hour number, turn true */
	 private boolean hasPossibility;
	 
	 /**  
	  * constructor for DurationFunctionType
	  * 
	  * @param serviceProperty
	  * 			basic property
	  */
	public DurationFunctionType(ServiceProperty serviceProperty) {
		
		super(serviceProperty);
		// TODO Auto-generated constructor stub
		hasPossibility = false;
	}
	
	 /**  
	  * set up duration in Possibility
	  * @param durationInPossibility
	  * 			float type			
	  */
	public void setDuationInPossibility(float durationInPossibility)
	{
		this.durationInPossibility = durationInPossibility;
		hasPossibility = true;
	}
	 /**  
	  * get if the information stored in possibility or specific hour number 
	  * @return when stored in possibility return true			
	  */
	public boolean hasPossibility()
	{
		return this.hasPossibility;
	}
	
	 /**  
	  * get duration in Possibility
	  * @return float type			
	  */
	public float getDuationInPossibility()
	{
		return this.durationInPossibility ;
	}	
	 /**  
	  * set up duration in hour
	  * @param durationInHour
	  * 			float type			
	  */
	public void setDuationInHour(float durationInHour)
	{
		this.durationInHour = durationInHour;
	}
	
	 /**  
	  * get duration in hour
	  * @return float type			
	  */
	public float getDuationInHour()
	{
		return this.durationInHour ;
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
		return ServiceProperty.DURATION;
	}	 
 }
 
 /**  
  * sub-class of FunctionType with ServiceProperty.NOSTOP	
  */
 class NumberofStopFunctionType extends abstractFunctionType
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
 
 /**  
  * sub-class of FunctionType with ServiceProperty.SERVICE	
  */
 class ServiceFunctionType extends abstractFunctionType
 {
	 /**  
	  * constructor for ServiceFunctionType
	  * 
	  * @param serviceProperty
	  * 			basic property
	  */
	public ServiceFunctionType(ServiceProperty serviceProperty) {
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
		return ServiceProperty.SERVICE;
	}	 
 }
 
 /**  
  * sub-class of FunctionType with ServiceProperty.RELIABILITY	
  */
 class ReliabilityFunctionType extends abstractFunctionType
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
 
 /**  
  * sub-class of FunctionType with ServiceProperty.ASERVICE	
  */
 class AfterServiceFunctionType extends abstractFunctionType
 {
	 /**  
	  * constructor for AfterServiceFunctionType
	  * 
	  * @param serviceProperty
	  * 			basic property
	  */
	public AfterServiceFunctionType(ServiceProperty serviceProperty) {
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
		return ServiceProperty.ASERVICE	;
	}	 
 }
 
 /**  
  * sub-class of FunctionType with ServiceProperty.DSERVICE	
  */
 class DuringFlyServiceFunctionType extends abstractFunctionType
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
 
 /**  
  * sub-class of FunctionType with ServiceProperty.OTHER	
  */
 class OtherFunctionType extends abstractFunctionType
 {
	 /**  
	  * constructor for DuringFlyServiceFunctionType
	  * 
	  * @param serviceProperty
	  * 			basic property
	  */
	public OtherFunctionType(ServiceProperty serviceProperty) {
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
		return ServiceProperty.OTHER;
	}	 
 }

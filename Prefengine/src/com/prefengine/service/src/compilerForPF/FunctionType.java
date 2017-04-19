package compilerForPF;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *  An interface of all service property sub-class and enum ServiceProperty
 */
 interface BasicFunctionType 
{
	/**
	 * get service-function of this service type.    
	 * 
	 * @return  ServiceProperty instance
	 */
	public  ServiceProperty getProperty();
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
	}

/**
 * A abstract class as a father class of all function-type sub-classes( those sub-classes has specific do-method for individual function-type).  
 */
public abstract class FunctionType implements BasicFunctionType{

	/**  each FunctionType sub-class instance will have a variable in  ServiceProperty to avoid unrecognized FunctionType construct-request */
	private ServiceProperty serviceProperty;

	/**  weight calculated from what adj/adv or verb contains in clauses  */
	private float weight;

	/**  define descend orascend order on data when do Fuzzy-function:
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
	public FunctionType(ServiceProperty serviceProperty)
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
 class GeneralPropertyFunctionType extends FunctionType
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
  * sub-class of FunctionType with ServiceProperty.LANDA	
  */
 class LeaveAndArriveFunctionType extends FunctionType
{	
	/** depart city or airport name  */
	 private ArrayList<String> leavePlace;
		
	/** arrive city or airport name  */
	 private ArrayList<String> arrivePlace;
	 
	 /** depart date  */
	 private Timestamp leaveDay;
	 
	 /** arrive date  */
	 private Timestamp arriveDay;
	 
	 /** depart time range  */
	 private Timestamp[] leaveTimeRange;
	 
	 /** arrive time range  */
	 private Timestamp[] arriveTimeRange; 

	 /**  
	  * constructor inherit from parent-class
	  * 
	  * @param serviceProperty
	  * 			should be ServiceProperty.LANDA
	  */
		public LeaveAndArriveFunctionType(ServiceProperty serviceProperty) {
			super(serviceProperty);
			// TODO Auto-generated constructor stub
			this.leavePlace = null;
			this.arrivePlace = null;
			this.leaveDay = null;
			this.arriveDay = null;
			this.leaveTimeRange = null;
			this.arriveTimeRange = null;
		}

		 /**  
		  * set up depart city or airport name
		  * 
		  * @param leavePlace
		  * 			in Array type, might contains more than one token-in-String
		  */
		public void setLeavePlace(ArrayList<String> leavePlace)
		{
			this.leavePlace = leavePlace;
		}

		 /**  
		  * set up arrive city or airport name
		  * 
		  * @param arrivePlace
		  * 			in Array type, might contains more than one token-in-String
		  */
		public void setArrivePlace(ArrayList<String> arrivePlace)
		{
			this.arrivePlace = arrivePlace;
		}	

		 /**  
		  * set up depart date
		  * 
		  * @param leaveDay
		  * 			in TimeStamp with 0 on all digits under "day"
		  */
		public void setLeaveDay(Timestamp leaveDay)
		{
			this.leaveDay = leaveDay;
		}

		 /**  
		  * set up arrive date
		  * 
		  * @param arriveDay
		  * 			in TimeStamp with 0 on all digits under "day"
		  */
		public void setArriveDay(Timestamp arriveDay)
		{
			this.arriveDay = arriveDay;
		}

		 /**  
		  * set up depart time range of a day
		  * 
		  * @param leaveTimeRange
		  * 			in an Array with two elements
		  */
		public void setLeaveTimeRange(Timestamp[] leaveTimeRange)
		{
			this.leaveTimeRange = leaveTimeRange;
		}

		 /**  
		  * set up arrive time range of a day
		  * 
		  * @param arriveTimeRange
		  * 			in an Array with two elements
		  */
		public void setArriveTimeRange(Timestamp[] arriveTimeRange)
		{
			this.arriveTimeRange = arriveTimeRange;
		}	

		 /**  
		  * get depart city or airport name
		  * 
		  * @return in Array type, might contains more than one token-in-String
		  */
		public ArrayList<String> getLeavePlace()
		{
			return this.leavePlace;
		}

		 /**  
		  * get arrive city or airport name
		  * 
		  * @return in Array type, might contains more than one token-in-String
		  */
		public ArrayList<String> getArrivePlace()
		{
			return this.arrivePlace ;
		}	

		 /**  
		  * get depart date
		  * 
		  * @return  TimeStamp with 0 on all digits under "day"
		  */
		public Timestamp getLeaveDay()
		{
			return this.leaveDay ;
		}

		 /**  
		  * get arrive date
		  * 
		  * @return  TimeStamp with 0 on all digits under "day"
		  */
		public Timestamp getArriveDay()
		{
			return this.arriveDay ;
		}

		 /**  
		  * get depart time range of a day
		  * 
		  * @return in an Array with two elements
		  */
		public Timestamp[] getLeaveTimeRange()
		{
			return this.leaveTimeRange ;
		}

		 /**  
		  * get arrive time range of a day
		  * 
		  * @return in an Array with two elements
		  */
		public Timestamp[] getArriveTimeRange()
		{
			return this.arriveTimeRange;
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
			return ServiceProperty.LANDA;
		}		 
	 }
 
 /**  
  * sub-class of FunctionType with ServiceProperty.ROUNDTRIP	
  */
 class RoundTripFunctionType extends FunctionType
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
 class SeatClassFunctionType extends FunctionType
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
 class AbstractPropertyFunctionType extends FunctionType
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
 class BasicPropertyFunctionType extends FunctionType
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
 class PackageRuleFunctionType extends FunctionType
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
 class ReputationFunctionType extends FunctionType
 {
	 /**  
	  * constructor for ReputationFunctionType
	  * 
	  * @param serviceProperty
	  * 			basic property
	  */
	public ReputationFunctionType(ServiceProperty serviceProperty) {
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
		return ServiceProperty.REPUTATION;
	} 
 }
 
 /**  
  * sub-class of FunctionType with ServiceProperty.CONVENIENT	
  */
 class ConvenientFunctionType extends FunctionType
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
 class AirportUtilityFunctionType extends FunctionType
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
 class SafetyFunctionType extends FunctionType
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
 class CostFunctionType extends FunctionType
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
  * sub-class of FunctionType with ServiceProperty.DURATION	
  */
 class DurationFunctionType extends FunctionType
 {
	 /** store duration in hour */
	 private float durationInHour;
	 
	 /**  
	  * constructor for DurationFunctionType
	  * 
	  * @param serviceProperty
	  * 			basic property
	  */
	public DurationFunctionType(ServiceProperty serviceProperty) {
		super(serviceProperty);
		// TODO Auto-generated constructor stub
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
 class NumberofStopFunctionType extends FunctionType
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
 class ServiceFunctionType extends FunctionType
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
 class ReliabilityFunctionType extends FunctionType
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
 class AfterServiceFunctionType extends FunctionType
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
 class DuringFlyServiceFunctionType extends FunctionType
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
 class OtherFunctionType extends FunctionType
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

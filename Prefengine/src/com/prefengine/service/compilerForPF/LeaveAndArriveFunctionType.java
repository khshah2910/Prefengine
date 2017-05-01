package com.prefengine.service.compilerForPF;

import java.sql.Timestamp;

/**  
 * sub-class of FunctionType with ServiceProperty.LANDA	
 */
public class LeaveAndArriveFunctionType extends AbstractFunctionType
{	
	/** depart city or airport name  */
	 private City leavePlace;
		
	/** arrive city or airport name  */
	 private City arrivePlace;
	 
	 /** depart date  */
	 private String leaveDay;
	 
	 /** arrive date  */
	 private String arriveDay;
	 
	 /** depart time range  */
	 private String[] leaveTimeRange;
	 
	 /** arrive time range  */
	 private String[] arriveTimeRange; 

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
		  * @param cityCode
		  * 			city code
		  */
		public void setLeavePlace(City cityCode)
		{
			this.leavePlace = cityCode;
		}

		 /**  
		  * set up arrive city or airport name
		  * 
		  * @param citycode
		  * 			city code
		  */
		public void setArrivePlace(City citycode)
		{
			this.arrivePlace = citycode;
		}	

		 /**  
		  * set up depart date
		  * 
		  * @param leaveDay
		  * 			in TimeStamp with 0 on all digits under "day"
		  */
		public void setLeaveDay(String leaveDay)
		{
			this.leaveDay = leaveDay;
		}

		 /**  
		  * set up arrive date
		  * 
		  * @param arriveDay
		  * 			in TimeStamp with 0 on all digits under "day"
		  */
		public void setArriveDay(String arriveDay)
		{
			this.arriveDay = arriveDay;
		}

		 /**  
		  * set up depart time range of a day
		  * 
		  * @param leaveTimeRange
		  * 			in an Array with two elements
		  */
		public void setLeaveTimeRange(String[] leaveTimeRange)
		{
			this.leaveTimeRange = leaveTimeRange;
		}

		 /**  
		  * set up arrive time range of a day
		  * 
		  * @param arriveTimeRange
		  * 			in an Array with two elements
		  */
		public void setArriveTimeRange(String[] arriveTimeRange)
		{
			this.arriveTimeRange = arriveTimeRange;
		}	

		 /**  
		  * get depart city or airport name
		  * 
		  * @return in Array type, might contains more than one token-in-String
		  */
		public City getLeavePlace()
		{
			return this.leavePlace;
		}

		 /**  
		  * get arrive city or airport name
		  * 
		  * @return in Array type, might contains more than one token-in-String
		  */
		public City getArrivePlace()
		{
			return this.arrivePlace ;
		}	

		 /**  
		  * get depart date
		  * 
		  * @return  TimeStamp with 0 on all digits under "day"
		  */
		public String getLeaveDay()
		{
			return this.leaveDay ;
		}

		 /**  
		  * get arrive date
		  * 
		  * @return  TimeStamp with 0 on all digits under "day"
		  */
		public String getArriveDay()
		{
			return this.arriveDay ;
		}

		 /**  
		  * get depart time range of a day
		  * 
		  * @return in an Array with two elements
		  */
		public String[] getLeaveTimeRange()
		{
			return this.leaveTimeRange ;
		}

		 /**  
		  * get arrive time range of a day
		  * 
		  * @return in an Array with two elements
		  */
		public String[] getArriveTimeRange()
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


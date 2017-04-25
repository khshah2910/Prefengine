package compilerForPF;

import java.sql.Timestamp;
import java.time.Year;
import java.util.ArrayList;

/**
 * A Parser takes the ArrayList of sentence clauses(ArrayList<ArrayList<TokenGeneralKind>>) as an input, 
 * analyze what is inside each clause then generate corresponding FunctionType sub-class with weight.
 * The output is a ArrayList of FunctionType sub-classes. And the weight is return as a property of FunctionType sub-classes.
 * The ArrayList of Connective Operator will be changed if RoundTripFunctionType shows in clauses.
 */

public class Parser {
	
	/** contain the whole NFR and connective operators, if is round trip, then both of the trips is inside also. This is used for Display
	 *  */
	private ArrayList<Object> totalArray = new ArrayList<Object>();
	
	/** Define the output ArrayList of non-functional request. */
	private ArrayList<BasicFunctionType> propertyArray = new ArrayList<BasicFunctionType>();
	
	/** the final result to server, contain Clause instances with left-hand, connective operator and right-hand; for round trip also. */
	private ArrayList<ArrayList<Clause>> resultArray = new ArrayList<ArrayList<Clause>>();
	/** Define the output ArrayList of connective operators between requests. */
	private ArrayList<ConnectiveOperator> connevtiveOperatorArray = new ArrayList<ConnectiveOperator>();

	/** Store the output of scanner */
	private ArrayList<ArrayList<TokenGeneralKind>> targetClauseArray = new ArrayList<ArrayList<TokenGeneralKind>> ();
	

	/** Define the output ArrayList of connective operators between requests for back trip. */
	private ArrayList<ConnectiveOperator> connevtiveOperatorArrayOfBackTrip = new ArrayList<ConnectiveOperator>();

	/** Define the output ArrayList of non-functional request for back trip. */
	private ArrayList<BasicFunctionType> propertyArrayOfBackTrip = new ArrayList<BasicFunctionType>();

	/** Store tokens of back trip request */
	private ArrayList<ArrayList<TokenGeneralKind>> targetClauseArrayofBackTrip = new ArrayList<ArrayList<TokenGeneralKind>> ();

	/** Store the requirement of back trip in a round trip */
	private ArrayList<ArrayList<TokenGeneralKind>> backTripRequirement = new ArrayList<ArrayList<TokenGeneralKind>>();

	/** if true, then server class has to separate the elements inside ArrayList to two connection to API */
	private boolean isRoundTrip ;
	
	/**
	 * Construct a parser.   
	 * 
	 *  @param scanner
	 *  		a Scanner instance  
	 */
	public Parser(Scanner scanner)
	{	
		this.targetClauseArray = scanner.getClauseArray();
		this.connevtiveOperatorArray = scanner.getConnectiveOperatorArray();
		this.isRoundTrip = false;
	}
	
	/**
     * Start the whole parser function as an interface.
     * 
     * @return the list of final result of Clause instances with left-hand, connective operator and right-hand; for round trip also.
     */
	public ArrayList<ArrayList<Clause>> parserEngine()
	{			
		ArrayList<Float> weight = new ArrayList<Float>();
		organizeClauseByFunctionType();
		ArrayList<ArrayList<TokenGeneralKind>> midResult = new ArrayList<ArrayList<TokenGeneralKind>> ();
		for(ArrayList<TokenGeneralKind> o : targetClauseArray)
		{
			midResult.add(o);
		}
		//calculate FunctionType and its weight from each clause.    
		for(int i = 0; i< midResult.size();i++ )
		{	
			BasicFunctionType here = calculateFunctionProperty(midResult.get(i),i);
//			for(TokenGeneralKind token : midResult.get(i))
//			{
//				if(token instanceof Token)
//				System.out.println(((Token)token).getFunctionType() + "+++");
//				System.out.println(targetClauseArray.get(i).size());
//			}
			propertyArray.add(here);
			((FunctionType)(propertyArray.get(propertyArray.size()-1))).setWeight(fixWeight(midResult.get(i)));
			weight.add(fixWeight(midResult.get(i)));						
		}	
		weight = rerangeWeights(weight);		
		//set weight after re-range as a property of FunctionType instance
		for(int i = 0;i< propertyArray.size();i++)
		{
			((FunctionType)propertyArray.get(i)).setWeight(weight.get(i));
		}
		ArrayList<Clause> resultOfGoTrip = new ArrayList<Clause>();
		for(int i =0; i< propertyArray.size() -1; i ++)
		{
			Clause newClause = new Clause(this.propertyArray.get(i),this.connevtiveOperatorArray.get(i),this.propertyArray.get(i+1));
			resultOfGoTrip.add(newClause);
		}
		this.resultArray.add(resultOfGoTrip);
		if(this.isRoundTrip)
			generateSecondSliceForRoundTrip();
		weight = new ArrayList<Float>();
		if(isRoundTrip && targetClauseArrayofBackTrip.size() >0)
		{	for(ArrayList<TokenGeneralKind> o : targetClauseArrayofBackTrip )
			{	
				((FunctionType)(propertyArrayOfBackTrip.get(propertyArrayOfBackTrip.size()-1))).setWeight(fixWeight(o));
				weight.add(fixWeight(o));						
			}	
			weight = rerangeWeights(weight);
			for(int i = 0;i< propertyArrayOfBackTrip.size();i++)
			{	
				((FunctionType)propertyArrayOfBackTrip.get(i)).setWeight(weight.get(i));
			}
			resultOfGoTrip =  new ArrayList<Clause>(); 
			for(int i =0; i< propertyArrayOfBackTrip.size() -1; i ++)
			{
				Clause newClause = new Clause(this.propertyArrayOfBackTrip.get(i),this.connevtiveOperatorArrayOfBackTrip.get(i)
						,this.propertyArrayOfBackTrip.get(i+1));
				resultOfGoTrip.add(newClause);
				this.resultArray.add(resultOfGoTrip);
			}
			
		}
		
		return this.resultArray;
	}
	
	/**
     * Get the output of parser.
     * 
     * @return the ArrayList of FunctionType sub-classes.           
     */
	public ArrayList<ArrayList<Clause>> getFinalResultArray()
	{	return this.resultArray;
	}	
	
	/**
     * Get the output of parser.
     * 
     * @return the ArrayList of FunctionType sub-classes.           
     */
	public ArrayList<BasicFunctionType> getFunctionArray()
	{	return this.propertyArray;
	}
	
	/**
     * Get if the whole request from user is round trip or not .
     * 
     * @return the ArrayList of FunctionType sub-classes.           
     */
	public boolean isRoundTrip()
	{	return this.isRoundTrip;
	}
	
	/**
     * Get the output of parser.
     * 
     * @return a boolean type.           
     */
	public ArrayList<ConnectiveOperator> getConnectiveOperatorArray()
	{	return this.connevtiveOperatorArray;
	}
	
	/**
     * separate logical clause within one literal clause, in case user type in requirements without any conjunction.
     * 
     * @param  functionFound
     * 				list of function-type recognized in one literal clause
     * @param  indexOfClause
     * 				index of the literal clause in the whole clause list          
     */
	private void separateFunctionTypeWithoutCojunction ( ArrayList<BasicFunctionType> functionFound,int indexOfClause)
	{
		ArrayList<ArrayList<TokenGeneralKind>> separateFunctionTypeList = new ArrayList<ArrayList<TokenGeneralKind>>();
		ArrayList<ArrayList<TokenGeneralKind>> copyOfClausesBefore = new ArrayList<ArrayList<TokenGeneralKind>>();
		ArrayList<ArrayList<TokenGeneralKind>> copyOfClausesAfter = new ArrayList<ArrayList<TokenGeneralKind>>();
		if(indexOfClause <targetClauseArray.size() -1 && indexOfClause !=0)
		{
			for(int i = 0; i< indexOfClause; i++)	
			{
				copyOfClausesBefore.add(targetClauseArray.get(i));
			}
			for(int i = indexOfClause + 1; i< targetClauseArray.size(); i++)
			{
				copyOfClausesAfter.add(targetClauseArray.get(i));
			}
			int endIndex = findSeparateIndex(targetClauseArray.get(indexOfClause));
			int startIndex = 0;
			for(int size = 0; size < functionFound.size(); size ++)
			{
				ArrayList<TokenGeneralKind> target= new ArrayList<TokenGeneralKind> ();
				for(int i = startIndex; i<endIndex;i++)
					target.add( targetClauseArray.get(indexOfClause).get(i));
				separateFunctionTypeList.add(target);
				startIndex = endIndex ;
				ArrayList<TokenGeneralKind> tool = new ArrayList<TokenGeneralKind>();
				for(int j = startIndex; j <targetClauseArray.get(indexOfClause).size(); j++)
					tool.add(targetClauseArray.get(indexOfClause).get(j));
				if (endIndex < targetClauseArray.size()-1)
				endIndex = findSeparateIndex(tool);
				System.out.println(endIndex + "]]]]]]");
			}
			ArrayList<ArrayList<TokenGeneralKind>> resultFunctionList =  copyOfClausesBefore;
			ArrayList<ConnectiveOperator>  resultConjunctionList = new ArrayList<ConnectiveOperator> ();
			for(int i = 0; i< indexOfClause;i++)
				resultConjunctionList.add( connevtiveOperatorArray.get(i));
			for(int i = 0;i< separateFunctionTypeList.size();i++)
			{
				resultFunctionList.add(separateFunctionTypeList.get(i));
				resultConjunctionList.add(ConnectiveOperator.AND);
			}
			for(int i = 0; i< copyOfClausesAfter.size() -1; i++)
			{
				resultFunctionList.add(copyOfClausesBefore.get(i));
				resultConjunctionList.add(connevtiveOperatorArray.get(i));
			}
			if(copyOfClausesAfter.size() >0)
				resultFunctionList.add(copyOfClausesAfter.get(copyOfClausesAfter.size()-1));
			connevtiveOperatorArray = resultConjunctionList;
			targetClauseArray = resultFunctionList;
		}
		else if(indexOfClause == targetClauseArray.size()-1 && indexOfClause !=0)
		{
			for(int i = 0; i< indexOfClause; i++)	
			{
				copyOfClausesBefore.add(targetClauseArray.get(i));
			}
			int endIndex = findSeparateIndex(targetClauseArray.get(indexOfClause));
			int startIndex = 0;
			while(startIndex != endIndex)
			{
				ArrayList<TokenGeneralKind> target= new ArrayList<TokenGeneralKind> ();
				for(int i = startIndex; i<endIndex;i++)
					target.add( targetClauseArray.get(indexOfClause).get(i));
				separateFunctionTypeList.add(target);
				startIndex = endIndex ;
				ArrayList<TokenGeneralKind> tool = new ArrayList<TokenGeneralKind>();
				for(int j = startIndex; j <targetClauseArray.get(indexOfClause).size(); j++)
					tool.add(targetClauseArray.get(indexOfClause).get(j));
				if (endIndex < targetClauseArray.size()-1)
				endIndex = findSeparateIndex(tool);
			}
			ArrayList<ArrayList<TokenGeneralKind>> resultFunctionList =  copyOfClausesBefore;
			ArrayList<ConnectiveOperator>  resultConjunctionList = new ArrayList<ConnectiveOperator> ();
			for(int i = 0; i< indexOfClause;i++)
				resultConjunctionList.add( connevtiveOperatorArray.get(i));
			for(int i = 0;i< separateFunctionTypeList.size();i++)
			{
				resultFunctionList.add(separateFunctionTypeList.get(i));
				resultConjunctionList.add(ConnectiveOperator.AND);
			}
			resultConjunctionList.remove(resultConjunctionList.size()-1);
			connevtiveOperatorArray = resultConjunctionList;
			targetClauseArray = resultFunctionList;
		}
		else if(indexOfClause == targetClauseArray.size()-1 && indexOfClause ==0)
		{
			int endIndex = findSeparateIndex(targetClauseArray.get(indexOfClause));
			System.out.println("--===-=" + endIndex);
			int startIndex = 0;
			while(startIndex != endIndex)
			{
				ArrayList<TokenGeneralKind> target= new ArrayList<TokenGeneralKind> ();
				for(int i = startIndex; i<endIndex;i++)
					target.add( targetClauseArray.get(indexOfClause).get(i));
				separateFunctionTypeList.add(target);
				startIndex = endIndex ;
				ArrayList<TokenGeneralKind> tool = new ArrayList<TokenGeneralKind>();
				for(int j = startIndex; j <targetClauseArray.get(indexOfClause).size(); j++)
					tool.add(targetClauseArray.get(indexOfClause).get(j));
				if (endIndex < targetClauseArray.size()-1)
				endIndex = findSeparateIndex(tool);
			}
			ArrayList<ArrayList<TokenGeneralKind>> resultFunctionList =  new ArrayList<ArrayList<TokenGeneralKind>>();
			ArrayList<ConnectiveOperator>  resultConjunctionList = new ArrayList<ConnectiveOperator> ();
			for(int i = 0; i< indexOfClause;i++)
				resultConjunctionList.add( connevtiveOperatorArray.get(i));
			for(int i = 0;i< separateFunctionTypeList.size();i++)
			{
				resultFunctionList.add(separateFunctionTypeList.get(i));
				resultConjunctionList.add(ConnectiveOperator.AND);
			}
			resultConjunctionList.remove(resultConjunctionList.size()-1);
			connevtiveOperatorArray = resultConjunctionList;
			targetClauseArray = resultFunctionList;
		}
		else if(indexOfClause != targetClauseArray.size()-1 && indexOfClause ==0)
		{
			int endIndex = findSeparateIndex(targetClauseArray.get(indexOfClause));
			int startIndex = 0;
			while(startIndex != endIndex)
			{
				ArrayList<TokenGeneralKind> target= new ArrayList<TokenGeneralKind> ();
				for(int i = startIndex; i<endIndex;i++)
					target.add( targetClauseArray.get(indexOfClause).get(i));
				separateFunctionTypeList.add(target);
				startIndex = endIndex ;
				ArrayList<TokenGeneralKind> tool = new ArrayList<TokenGeneralKind>();
				for(int j = startIndex; j <targetClauseArray.get(indexOfClause).size(); j++)
					tool.add(targetClauseArray.get(indexOfClause).get(j));
				if (endIndex < targetClauseArray.size()-1)
				endIndex = findSeparateIndex(tool);
			}
			ArrayList<ArrayList<TokenGeneralKind>> resultFunctionList =  new ArrayList<ArrayList<TokenGeneralKind>>();
			ArrayList<ConnectiveOperator>  resultConjunctionList = new ArrayList<ConnectiveOperator> ();
			for(int i = 0; i< indexOfClause;i++)
				resultConjunctionList.add( connevtiveOperatorArray.get(i));
			for(int i = 0;i< separateFunctionTypeList.size();i++)
			{
				resultFunctionList.add(separateFunctionTypeList.get(i));
				resultConjunctionList.add(ConnectiveOperator.AND);
			}

			for(int i = 0; i< copyOfClausesAfter.size() -1; i++)
			{
				resultFunctionList.add(copyOfClausesBefore.get(i));
				resultConjunctionList.add(connevtiveOperatorArray.get(i));
			}
			if(copyOfClausesAfter.size() >0)
				resultFunctionList.add(copyOfClausesAfter.get(copyOfClausesAfter.size()-1));
			connevtiveOperatorArray = resultConjunctionList;
			targetClauseArray = resultFunctionList;
		}
	}
	private int findSeparateIndex(ArrayList<TokenGeneralKind> tokens)
	{
		int index = 0;
		BasicFunctionType functionType = null;
		for(int i =0; i< tokens.size();i++)
		{
			if(tokens.get(i) instanceof Token 
					&& ((Token)tokens.get(i)).getFunctionType() !=  ServiceProperty.GENERALPROPERTY
					&& ((CoreMeaning)((Token)tokens.get(i)).getCoreMeaning()).getBasicProperty() != Properties.NUMBER )
			{
				if( functionType == null)
					functionType = ((Token)tokens.get(i)).getFunctionType();
				else if(functionType != ((Token)tokens.get(i)).getFunctionType())
					return i;
			}
		}
		return index;
	}
	/**
     * if this request is round trip, generate another slice for connecting to API.          
     */
	
	private void generateSecondSliceForRoundTrip()
	{	totalArray.add(this.propertyArray);
		totalArray.add(this.connevtiveOperatorArray);
		//organizeClauseByFunctionType();
		ArrayList<BasicFunctionType> functionListOfBackTripRequest = new ArrayList<BasicFunctionType>();
		ArrayList<BasicFunctionType> copy = new ArrayList<BasicFunctionType>();
		
		if(backTripRequirement.size() != 0)
		{	
			//calculate the back trip request list of tokens to basic function type list
			for(int i = 0;i< this.backTripRequirement.size();i++)
			{
				
				BasicFunctionType backTripRequest = calculateFunctionProperty(backTripRequirement.get(i),i);
				functionListOfBackTripRequest.add(backTripRequest);  
			}
			for(BasicFunctionType o : functionListOfBackTripRequest)
				copy.add(o);
			//replace the request with same function in go trip by the one from back trip
			for(int i = 0;i< copy.size();i++)
			{	for(int j = 0;j< this.propertyArray.size();j++)
				{
					if(copy.get(i).getProperty().equals(this.propertyArray.get(j).getProperty()))
					{
						if(copy.get(i) instanceof LeaveAndArriveFunctionType)
						{
							if(((LeaveAndArriveFunctionType)copy.get(i)).getLeavePlace() == null)
								((LeaveAndArriveFunctionType)copy.get(i)).setLeavePlace(((LeaveAndArriveFunctionType)
										this.propertyArray.get(j)).getArrivePlace());
							if(((LeaveAndArriveFunctionType)copy.get(i)).getArrivePlace() == null)
								((LeaveAndArriveFunctionType)copy.get(i)).setArrivePlace(((LeaveAndArriveFunctionType)
										this.propertyArray.get(j)).getLeavePlace());
						}						
						this.propertyArrayOfBackTrip .add(copy.get(i));
						targetClauseArrayofBackTrip.add(backTripRequirement.get(i));
						functionListOfBackTripRequest.remove(i);
					}
					else
					{	
						this.propertyArrayOfBackTrip .add(this.propertyArray.get(j));
						targetClauseArrayofBackTrip.add(targetClauseArray.get(j));
					}
				}
			}
			//if there is any extra request left in back trip request list that is not exist in go trip request list, 
			//add it to the end and "AND" connective operator by default
			connevtiveOperatorArrayOfBackTrip = this.connevtiveOperatorArray;
			if(functionListOfBackTripRequest.size() !=0)
			{
				for(int i = 0;i< functionListOfBackTripRequest.size();i++)
				{	this.propertyArrayOfBackTrip .add(functionListOfBackTripRequest.get(i));
					this.connevtiveOperatorArrayOfBackTrip.add(ConnectiveOperator.AND);
				}
			}
			totalArray.add(this.propertyArrayOfBackTrip);			
			totalArray.add(this.connevtiveOperatorArrayOfBackTrip);
		}
		else
		{
			this.propertyArrayOfBackTrip = this.propertyArray;
			this.connevtiveOperatorArrayOfBackTrip = this.connevtiveOperatorArray;
			for(BasicFunctionType function : this.propertyArrayOfBackTrip)
			{
				if(function instanceof LeaveAndArriveFunctionType)
				{
					((LeaveAndArriveFunctionType) function).setArriveDay(null);
					((LeaveAndArriveFunctionType) function).setLeaveDay(null);
					ArrayList<String> oldArrivePlace = ((LeaveAndArriveFunctionType) function).getArrivePlace();
					ArrayList<String> oldLeavePlace = ((LeaveAndArriveFunctionType) function).getLeavePlace();
					((LeaveAndArriveFunctionType) function).setArrivePlace(oldLeavePlace);
					((LeaveAndArriveFunctionType) function).setLeavePlace(oldArrivePlace);
				}
			}
			totalArray.add(this.propertyArrayOfBackTrip);			
			totalArray.add(this.connevtiveOperatorArrayOfBackTrip);
		}
	}
	
	/**
     * Analyze if there is requirement of back trip in a clause.
     * 
     * @param clause
     * 			 one sentence clause 
     * 
     * @return the original clause without back trip request information
     */	
	private ArrayList<TokenGeneralKind>  analyzeBackTripRequirement(ArrayList<TokenGeneralKind> clause)
	{	int indexCopy = -1;
		ArrayList<TokenGeneralKind> backTripInformationElement = new ArrayList<TokenGeneralKind> ();
		for(int i = 0; i < clause.size();i++)
		{
			if(this.isRoundTrip == true && clause.get(i) instanceof Token 
					&& ((Token)clause.get(i)).getFunctionType() == ServiceProperty.ROUNDTRIP
					&&((CoreMeaning)((Token)clause.get(i)).getCoreMeaning()).getWeight() == WeightOriginalRange.POSITIVEENHANCE)
			{
				indexCopy = i;
				for(int j = 1; i+j<clause.size(); j++)
				{	
					backTripInformationElement.add(clause.get(i+j));
				}
				backTripRequirement.add(backTripInformationElement);
				i = clause.size();
			}
		}
		if(indexCopy != -1)
		{
			ArrayList<TokenGeneralKind> newRegularClause = new ArrayList<TokenGeneralKind>();
			for(int i = 0; i < indexCopy;i++)
				newRegularClause.add(clause.get(i));
			return newRegularClause;
		}
		if(indexCopy == 0)
		{
			return null;
		}
		else 
			return clause;
	}
	
	private void organizeClauseByFunctionType()
	{
		ArrayList<ArrayList<TokenGeneralKind>> copyClause = new ArrayList<ArrayList<TokenGeneralKind>>();
		for(int i = 0; i< targetClauseArray.size(); i++)
			copyClause.add(targetClauseArray.get(i));
		for(int i = 0; i< copyClause.size(); i++) 
		{
			ArrayList <BasicFunctionType> functionTypeArray = new ArrayList <BasicFunctionType>();
			for(TokenGeneralKind token : copyClause.get(i) )
			{	if((token instanceof Token)
					&&(((Token)token).getFunctionType() != ServiceProperty.GENERALPROPERTY))
				{	if(!functionTypeArray.contains(((Token)token).getFunctionType()))
						functionTypeArray.add(((Token)token).getFunctionType());
				}
				if(token instanceof Token 
					&& ((Token)token).getFunctionType() == ServiceProperty.ROUNDTRIP)
				{	if(fixWeight(copyClause.get(i)) > 0)
						isRoundTrip = true;
					else
						isRoundTrip = false;				
				}				
			}		
			if(functionTypeArray.contains(ServiceProperty.PACKAGERULE) 
					&& functionTypeArray.contains(ServiceProperty.DSERVICE) )
			{
				functionTypeArray.remove(ServiceProperty.PACKAGERULE);
			}
			if( (functionTypeArray.contains(ServiceProperty.SERVICE) 
					&& functionTypeArray.contains(ServiceProperty.DSERVICE) )
				|| (functionTypeArray.contains(ServiceProperty.SERVICE) 
						&& functionTypeArray.contains(ServiceProperty.ASERVICE) ) )
			{
				functionTypeArray.remove(ServiceProperty.SERVICE);
			} 
			if(functionTypeArray.contains(ServiceProperty.ASERVICE) 
					&& functionTypeArray.contains(ServiceProperty.DSERVICE) ) 
			{
				functionTypeArray.remove(ServiceProperty.ASERVICE);
			}
			if(functionTypeArray.contains(ServiceProperty.REPUTATION))
			{
				if( functionTypeArray.contains(ServiceProperty.RELIABILITY) )
				{	
					functionTypeArray.remove(ServiceProperty.RELIABILITY);
				}
				else if(functionTypeArray.contains(ServiceProperty.CONVENIENT) )
				{
					functionTypeArray.remove(ServiceProperty.CONVENIENT);
				} 
			}
			ArrayList <BasicFunctionType> removeNull = new ArrayList <BasicFunctionType>();
			for(BasicFunctionType function : functionTypeArray)
			{
				if(function != null)
					removeNull.add(function);
			}
			if(removeNull.size() > 1)
			{ separateFunctionTypeWithoutCojunction( removeNull, i);
			for(BasicFunctionType b : removeNull)
			{
				System.out.println(b );
			}
			}
		}
		
	}
	/**
     * Analyze one sentence clause find exact request of the clause.
     * 
     * @param clause
     * 			 one sentence clause 
     * @return a FunctionType sub-class instance of the input sentence clause.           
     */
	private BasicFunctionType calculateFunctionProperty (ArrayList<TokenGeneralKind> clause,int indexOfClause)
	{	ArrayList <BasicFunctionType> functionTypeArray = new ArrayList <BasicFunctionType>();
		//put all clause's BasicFunctionType instance to a ArrayList, except for the GENERALPROPERTY service property
		clause = analyzeBackTripRequirement(clause);
		for(TokenGeneralKind token : clause )
		{	if((token instanceof Token)
				&&(((Token)token).getFunctionType() != ServiceProperty.GENERALPROPERTY))
			{	
				if(!functionTypeArray.contains(((Token)token).getFunctionType()))
					{functionTypeArray.add(((Token)token).getFunctionType());
					}
			}
			if(token instanceof Token 
				&& ((Token)token).getFunctionType() == ServiceProperty.ROUNDTRIP)
			{	if(fixWeight(clause) > 0)
					isRoundTrip = true;
				else
					isRoundTrip = false;				
			}
			
		}
			 if(functionTypeArray.contains(ServiceProperty.OTHER) )
				return getOtherFunctionType(clause);
			else if(functionTypeArray.contains(ServiceProperty.NOSTOP) )
				return getNumberofStopFunctionType(clause);
			else if(functionTypeArray.contains(ServiceProperty.COST) )
				return getCostProperties(clause);				
			else if(functionTypeArray.contains(ServiceProperty.LANDA) )
				return getLeaveAndArriveProperties(clause);				
			else if(functionTypeArray.contains(ServiceProperty.SEATCLASS) )
				return getSeatClassFunctionType(clause);						
			else if(functionTypeArray.contains(ServiceProperty.DURATION) )
				return getDurationFunctionType(clause);		
			else if(functionTypeArray.contains(ServiceProperty.SAFETY) )
				return getSafetyFunctionType(clause);
			else if(functionTypeArray.contains(ServiceProperty.RELIABILITY) )
				return getReliabilityFunctionType(clause);
			else if(functionTypeArray.contains(ServiceProperty.REPUTATION) )
				return getReputationFunctionType(clause);
			else if(functionTypeArray.contains(ServiceProperty.PACKAGERULE) )
				return getPackageRuleFunctionType(clause);	
			else if(functionTypeArray.contains(ServiceProperty.CONVENIENT) )
				return getConvenientFunctionType(clause);
			else if(functionTypeArray.contains(ServiceProperty.AIRPORTUTILITY) )
				return getAirportUtilityFunctionType(clause);
			else if(functionTypeArray.contains(ServiceProperty.DSERVICE) )
				return getDuringFlyServiceFunctionType(clause);
			else if(functionTypeArray.contains(ServiceProperty.ASERVICE) )
				return getAfterServiceFunctionType(clause);
			else if(functionTypeArray.contains(ServiceProperty.ROUNDTRIP) )
					return getRoundTripFunctionType(clause);
			else if(functionTypeArray.contains(ServiceProperty.SERVICE) )
				return getServiceFunctionType(clause);
			else if(functionTypeArray.contains(ServiceProperty.BPROPERTY) )
				return getBasicPropertyFunctionType(clause);
			else if(functionTypeArray.contains(ServiceProperty.ABPROPERTY) )
				return getAbstractPropertyFunctionType(clause);
			else 
				{
				return getGeneralPropertyFunctionType(clause);}		
	}
	
	/**
     * Analyze an OTHER service-property clause,and generate an OtherFunctionType instance.
     * 
     * @param clause
     * 			 one sentence clause 
     * @return an OtherFunctionType instance.           
     */
	private BasicFunctionType getOtherFunctionType(ArrayList<TokenGeneralKind> clause)
	{return new OtherFunctionType(ServiceProperty.OTHER);
	}
	/**
     * Analyze an GENERALPROPERTY service-property clause,and generate an GeneralPropertyFunctionType instance.
     * 
     * @param clause
     * 			 one sentence clause 
     * @return an GeneralPropertyFunctionType instance.           
     */
	private BasicFunctionType getGeneralPropertyFunctionType(ArrayList<TokenGeneralKind> clause)
	{return new GeneralPropertyFunctionType(ServiceProperty.GENERALPROPERTY);
	}
	/**
     * Analyze an ABPROPERTY service-property clause,and generate an OtherFunctionType instance.
     * 
     * @param clause
     * 			 one sentence clause 
     * @return an OtherFunctionType instance.           
     */
	private BasicFunctionType getAbstractPropertyFunctionType(ArrayList<TokenGeneralKind> clause)
	{return new AbstractPropertyFunctionType(ServiceProperty.ABPROPERTY);
	}
	/**
     * Analyze an BPROPERTY service-property clause,and generate an BasicPropertyFunctionType instance.
     * 
     * @param clause
     * 			 one sentence clause 
     * @return an BasicPropertyFunctionType instance.           
     */
	private BasicFunctionType getBasicPropertyFunctionType(ArrayList<TokenGeneralKind> clause)
	{return new BasicPropertyFunctionType(ServiceProperty.BPROPERTY);
	}
	/**
     * Analyze an SERVICE service-property clause,and generate an ServiceFunctionType instance.
     * 
     * @param clause
     * 			 one sentence clause 
     * @return an ServiceFunctionType instance.           
     */
	private BasicFunctionType getServiceFunctionType(ArrayList<TokenGeneralKind> clause)
	{return new ServiceFunctionType(ServiceProperty.SERVICE);
	}
	/**
     * Analyze an ASERVICE service-property clause,and generate an AfterServiceFunctionType instance.
     * 
     * @param clause
     * 			 one sentence clause 
     * @return an AfterServiceFunctionType instance.           
     */
	private BasicFunctionType getAfterServiceFunctionType(ArrayList<TokenGeneralKind> clause)
	{return new AfterServiceFunctionType(ServiceProperty.ASERVICE);
	}
	/**
     * Analyze an DSERVICE service-property clause,and generate an DuringFlyServiceFunctionType instance.
     * 
     * @param clause
     * 			 one sentence clause 
     * @return an DuringFlyServiceFunctionType instance.           
     */
	private BasicFunctionType getDuringFlyServiceFunctionType(ArrayList<TokenGeneralKind> clause)
	{return new DuringFlyServiceFunctionType(ServiceProperty.DSERVICE);
	}
	/**
     * Analyze an AIRPORTUTILITY service-property clause,and generate an AirportUtilityFunctionType instance.
     * 
     * @param clause
     * 			 one sentence clause 
     * @return an AirportUtilityFunctionType instance.           
     */
	private BasicFunctionType getAirportUtilityFunctionType(ArrayList<TokenGeneralKind> clause)
	{return new AirportUtilityFunctionType(ServiceProperty.AIRPORTUTILITY);
	}
	/**
     * Analyze an CONVENIENT service-property clause,and generate an ConvenientFunctionType instance.
     * 
     * @param clause
     * 			 one sentence clause 
     * @return an ConvenientFunctionType instance.           
     */
	private BasicFunctionType getConvenientFunctionType(ArrayList<TokenGeneralKind> clause)
	{return new ConvenientFunctionType(ServiceProperty.CONVENIENT);
	}
	/**
     * Analyze an PACKAGERULE service-property clause,and generate an PackageRuleFunctionType instance.
     * 
     * @param clause
     * 			 one sentence clause 
     * @return an PackageRuleFunctionType instance.           
     */
	private BasicFunctionType getPackageRuleFunctionType(ArrayList<TokenGeneralKind> clause)
	{return new PackageRuleFunctionType(ServiceProperty.PACKAGERULE);
	}
	/**
     * Analyze an REPUTATION service-property clause,and generate an ReputationFunctionType instance.
     * 
     * @param clause
     * 			 one sentence clause 
     * @return an ReputationFunctionType instance.           
     */
	private BasicFunctionType getReputationFunctionType(ArrayList<TokenGeneralKind> clause)
	{return new ReputationFunctionType(ServiceProperty.REPUTATION);
	}
	/**
     * Analyze an SAFETY service-property clause,and generate an SafetyFunctionType instance.
     * 
     * @param clause
     * 			 one sentence clause 
     * @return an SafetyFunctionType instance.           
     */
	private BasicFunctionType getSafetyFunctionType(ArrayList<TokenGeneralKind> clause)
	{return new SafetyFunctionType(ServiceProperty.SAFETY);
	}
	/**
     * Analyze an RELIABILITY service-property clause,and generate an ReliabilityFunctionType instance.
     * 
     * @param clause
     * 			 one sentence clause 
     * @return an ReliabilityFunctionType instance.           
     */
	private BasicFunctionType getReliabilityFunctionType(ArrayList<TokenGeneralKind> clause)
	{return new ReliabilityFunctionType(ServiceProperty.RELIABILITY);
	}
	
	/**
     * Analyze an seat-class service-property clause,and generate an SeatClassFunctionType instance.
     * 
     * @param clause
     * 			 one sentence clause 
     * @return an SeatClassFunctionType instance.           
     */
	private BasicFunctionType getSeatClassFunctionType(ArrayList<TokenGeneralKind> clause)
	{	SeatClassFunctionType seatClassInstance = new SeatClassFunctionType(ServiceProperty.SEATCLASS);
		for(int i = 0; i < clause.size();i++)
		{	if (clause.get(i) instanceof Token && 
					((Token)clause.get(i)).getFunctionType() == ServiceProperty.SEATCLASS)
			{	if(((CoreMeaning)((Token)clause.get(i)).getCoreMeaning()).getWeight() == WeightOriginalRange.POSITIVESUPREME)
					{seatClassInstance.setSeatClass("first");
					return seatClassInstance;					
					}
				else if(((CoreMeaning)((Token)clause.get(i)).getCoreMeaning()).getWeight() == WeightOriginalRange.POSITIVEENHANCE)
					{seatClassInstance.setSeatClass("business");
					return seatClassInstance;
					}
				else if(((CoreMeaning)((Token)clause.get(i)).getCoreMeaning()).getWeight() == WeightOriginalRange.POSITIVESTABLE)
					{seatClassInstance.setSeatClass("premium");
					return seatClassInstance;					
					}
				else if(((CoreMeaning)((Token)clause.get(i)).getCoreMeaning()).getWeight() == WeightOriginalRange.STABLE)
					{seatClassInstance.setSeatClass("economy");
					return seatClassInstance;
					}
			}
		}
		seatClassInstance.setSeatClass("economy");
		return seatClassInstance;
	}
	
	/**
     * Analyze an round trip service-property clause,
     * generate a RoundTripFunctionType instance with the back trip request clause as a variable.
     * 
     * @param clause
     * 			 one sentence clause 
     * @return an RoundTripFunctionType instance.           
     */
	private BasicFunctionType getRoundTripFunctionType(ArrayList<TokenGeneralKind> clause)
	{	RoundTripFunctionType roundtripClassInstance = new RoundTripFunctionType(ServiceProperty.ROUNDTRIP);
		
		ArrayList<TokenGeneralKind> newRequest = new ArrayList<TokenGeneralKind>();
		for(int i = 0; i < clause.size();i++)
		{	if (clause.get(i) instanceof Token 
					&& ((Token)clause.get(i)).getFunctionType() == ServiceProperty.ROUNDTRIP
					&&((CoreMeaning)((Token)clause.get(i)).getCoreMeaning()).getWeight() == WeightOriginalRange.STABLE)
			{	this.isRoundTrip = true;
				roundtripClassInstance.setRoundtrip(true);
				for(int j=0;i+j <clause.size(); j++)
				{ 
					// when there is some different request of back trip
					if (clause.get(i+j) instanceof Token 
						&& ((Token)clause.get(i+j)).getFunctionType() == ServiceProperty.ROUNDTRIP
						&&((CoreMeaning)((Token)clause.get(i+j)).getCoreMeaning()).getWeight() == WeightOriginalRange.POSITIVEENHANCE)
					{
						
						for(int z = 1; i+j+z<clause.size(); z++)
						{
							newRequest.add(clause.get(i+j+z));
						}
						roundtripClassInstance.setBackTripRequest(newRequest);
					}
				}
			}
			else if (clause.get(i) instanceof Token 
				&& ((Token)clause.get(i)).getFunctionType() == ServiceProperty.ROUNDTRIP
				&&((CoreMeaning)((Token)clause.get(i)).getCoreMeaning()).getWeight() == WeightOriginalRange.POSITIVESLACK)
			{	roundtripClassInstance.setRoundtrip(false);				
			}
		}
		return roundtripClassInstance;
	}
	
	/**
     * Analyze an trip-duration service-property clause,and generate an DurationFunctionType instance.
     * 
     * @param clause
     * 			 one sentence clause 
     * @return an DurationFunctionType instance.           
     */
	private BasicFunctionType getDurationFunctionType (ArrayList<TokenGeneralKind> clause)
	{	float durationInHour = 0;
		DurationFunctionType durationInstance = new DurationFunctionType(ServiceProperty.DURATION);
		for(int i = 1; i < clause.size();i++)
		{	if (clause.get(i) instanceof Token  
					&& ((Token)clause.get(i)).getFunctionType() == ServiceProperty.DURATION 
					&& ((Token)clause.get(i)).getCoreMeaning() == CoreMeaning.HOUR
					&& (clause.get(i-1) instanceof Token && 
					((Token)clause.get(i-1)).getProperty() == NumberProperties.REGULARNUMBER))
			{	durationInHour = durationInHour + Float.valueOf(((Token)clause.get(i-1)).getImage());				
			}
			else if (clause.get(i) instanceof Token  
					&& ((Token)clause.get(i)).getFunctionType() == ServiceProperty.DURATION 
					&& ((Token)clause.get(i)).getCoreMeaning() == CoreMeaning.MINUT
					&& (clause.get(i-1) instanceof Token && 
					((Token)clause.get(i-1)).getProperty() == NumberProperties.REGULARNUMBER))
			{	durationInHour = durationInHour + (Float.valueOf(((Token)clause.get(i-1)).getImage())/ 60f);
			}
			else if (clause.get(i) instanceof Token  
					&& ((Token)clause.get(i)).getFunctionType() == ServiceProperty.DURATION 
					&& ((Token)clause.get(i)).getCoreMeaning() == CoreMeaning.DAY
					&& (clause.get(i-1) instanceof Token && 
					((Token)clause.get(i-1)).getProperty() == NumberProperties.REGULARNUMBER))
			{	durationInHour = durationInHour + (Float.valueOf(((Token)clause.get(i-1)).getImage())* 24f);
			}
		}
		durationInstance.setDuationInHour(durationInHour);
		return durationInstance;
	}
	
	/**
     * Analyze an number-of-stop service-property clause,and generate an DurationFunctionType instance.
     * 
     * @param clause
     * 			 one sentence clause 
     * @return an DurationFunctionType instance.           
     */
	private BasicFunctionType getNumberofStopFunctionType(ArrayList<TokenGeneralKind> clause)
	{	NumberofStopFunctionType noStopInstance = new NumberofStopFunctionType(ServiceProperty.NOSTOP);
		for(int i = 0; i < clause.size();i++)
		{	if (clause.get(i) instanceof Token && 
					(((CoreMeaning)((Token)clause.get(i)).getCoreMeaning()).getWeight().getWeight()< 0)	)
			{	noStopInstance.setNumberOfStop(0);}
			else if(clause.get(i) instanceof Token && ((((Token)clause.get(i)).getFunctionType() == ServiceProperty.NOSTOP)
					&&(((CoreMeaning)((Token)clause.get(i)).getCoreMeaning()).getWeight() == WeightOriginalRange.POSITIVESTABLE)))
			{	noStopInstance.setNumberOfStop(0);	}
			else if (clause.get(i) instanceof Token && 
					((((CoreMeaning)((Token)clause.get(i)).getCoreMeaning()).getBasicProperty() ==  Properties.NUMBER)
					|| ((((Token)clause.get(i)).getCoreMeaning()  == CoreMeaning.NUMBERTYPE) 
						&&(((Token)clause.get(i)).getProperty() == NumberProperties.REGULARNUMBER))	))
			{ 	noStopInstance.setNumberOfStop(Integer.parseInt(((Token)clause.get(i)).getImage()));	}
	//here define system as "less stop" means 0~1 stop during one trip
			else if (clause.get(i) instanceof Token && 
					(((CoreMeaning)((Token)clause.get(i)).getCoreMeaning()).getWeight() == WeightOriginalRange.POSITIVESLACK)	)
			{ 	noStopInstance.setNumberOfStop(1);}			 			
		}
		return noStopInstance;
	}
	
	/**
     * Analyze an cost-of-ticket service-property clause,and generate an CostFunctionType instance.
     * 
     * @param clause
     * 			 one sentence clause 
     * @return an CostFunctionType instance.           
     */
	private BasicFunctionType getCostProperties(ArrayList<TokenGeneralKind> clause)
	{	CostFunctionType costInstance = new CostFunctionType(ServiceProperty.COST);
		ArrayList<String> numberArray  = new ArrayList<String>();
		float[] priceRange = new float[2];
		for(int i = 0; i < clause.size();i++)
		{  
			if(clause.get(i) instanceof Token && (((Token)clause.get(i)).getCoreMeaning()  == CoreMeaning.NUMBERTYPE) 
						&&((((Token)clause.get(i)).getProperty() == NumberProperties.REGULARNUMBER)	
							||(((Token)clause.get(i)).getProperty() == NumberProperties.MONEYNUMBER)))
			{	numberArray.add(((Token)clause.get(i)).getImage());
			}
		}
		if(numberArray.size() == 1 )
		{	priceRange[0] = 0f;
			priceRange[1] = Float.valueOf(numberArray.get(0));
		}
		else if(numberArray.size() == 2)
		{	float midNumber = Float.valueOf(numberArray.get(0));
			if(Float.valueOf(numberArray.get(1))< midNumber)
			{	priceRange[0] = Float.valueOf(numberArray.get(1));
				priceRange[1] = midNumber;
			}
			else
			{	priceRange[0] = midNumber;
				priceRange[1] = Float.valueOf(numberArray.get(1));
			}
		}
		else if(numberArray.size()>2)
		{	float minNumber = Float.valueOf(numberArray.get(0));
			float maxNumber = Float.valueOf(numberArray.get(0));
			for(int i = 1;i<numberArray.size();i++)
			{	if(Float.valueOf(numberArray.get(i))< minNumber)
				{	minNumber = Float.valueOf(numberArray.get(i));
				}
				if(Float.valueOf(numberArray.get(i))> maxNumber)
				{	maxNumber = Float.valueOf(numberArray.get(i));
				}
			}
			priceRange[0] = minNumber;
			priceRange[1] = maxNumber;			
		} 
		else
		{ 	float generalPriceRequest = 1;
			for(TokenGeneralKind token : clause)
			{
			if(token instanceof Token && ((Token)token).getFunctionType() == ServiceProperty.COST 
					&& ((Token)token).getProperty() == Properties.ADJV)
				generalPriceRequest *= ((Token)token).getWeight();
		}
		costInstance.setgeneralPriceRequest(generalPriceRequest);
		return costInstance;
		}
		costInstance.setPriceRange(priceRange);
		return costInstance;		
	}
	
	/**
     * put unrecognized token together in String type.
     * 
     * @param clause
     * 			 one sentence clause 
     * @param startIndex
     * 			 the start index of clause to scan city name 
     * @return city name might with airport name and country name               
     */	
	private ArrayList<String> generateCityClause(ArrayList<TokenGeneralKind> clause,int startIndex )
	{	boolean canBreak = false;
		ArrayList<String> cityName = new ArrayList<String>();
		for(int j = startIndex;j<clause.size() && canBreak == false;j++)
		{	if(clause.get(j) instanceof UnrecognizeToken )
			{	
				for(int z = 0; ( j + z)<clause.size() ;z ++)
				{	if(clause.get( j + z) instanceof UnrecognizeToken )
					{cityName.add(((UnrecognizeToken )clause.get( j + z) ).getImage());
					}
					else 
					{
						z = clause.size() - j + 1;
						canBreak = true;
					}
				}			
			}
		}
		return cityName;
	}
	
	/**
     * organize date number user put in to TimeStamp format.
     * 
     * @param clause
     * 			 one sentence clause 
     * @param startIndex
     * 			 the start index of clause to scan city name 
     * @return date information in Time-stamp format               
     */	
	private Timestamp generateTimeStamp(ArrayList<TokenGeneralKind> clause,int startIndex )
	{	String dateNumber = ((Token)clause.get(startIndex)).getImage();
		String[] datenumberArray= dateNumber.split("/");
		dateNumber = "";
		if(datenumberArray[0].length() == 1 )
			datenumberArray[0] = "0" + datenumberArray[0];
		if(datenumberArray[1].length() == 1 )
			datenumberArray[1] = "0" + datenumberArray[1];
		if(datenumberArray[2].length() == 2 )
			datenumberArray[2] = "20" + datenumberArray[2];							
		if(datenumberArray.length == 2)
		{	dateNumber = dateNumber + Year.now().getValue() + "-" + datenumberArray[0] + "-" + datenumberArray[1] + " 00:00:00.000000000"; 
		}
		else if(datenumberArray.length == 3)
		{	dateNumber = datenumberArray[2] + "-" + datenumberArray[0] + "-" + datenumberArray[1] + " 00:00:00.000000000"; 							 
		}
		Timestamp timeStamp = Timestamp.valueOf(dateNumber);
		return timeStamp;
	}
	
	/**
     * Analyze an leave and arrive service-property clause,and generate an LeaveAndArriveFunctionType instance.
     * 
     * @param clause
     * 			 one sentence clause 
     * @return an LeaveAndArriveFunctionType instance.               
     */
	private BasicFunctionType getLeaveAndArriveProperties(ArrayList<TokenGeneralKind> clause)
	{	LeaveAndArriveFunctionType landaInstance = new LeaveAndArriveFunctionType(ServiceProperty.LANDA);
		boolean findLANDACoreMeaning = false;
		for(int i =0;i<clause.size();i++)
		{if(clause.get(i) instanceof Token && 
					((CoreMeaning)((Token)clause.get(i)).getCoreMeaning()).getBasicFunctionType() ==  ServiceProperty.LANDA)
			{	findLANDACoreMeaning = true;
				if(((CoreMeaning)((Token)clause.get(i)).getCoreMeaning()).getWeight() == WeightOriginalRange.NEGATIVESTABLE)
				{//boolean findUnrecognizeToken = false;
					for(int j = 0;j<5;j++)
					{	if(clause.get(i+j) instanceof UnrecognizeToken )
						{	
							landaInstance.setLeavePlace(generateCityClause(clause, i+j));							
						}
						else if(clause.get(i+j) instanceof Token && ((Token)clause.get(i+j)).getProperty() == NumberProperties.DATENUMBER)
						{
							landaInstance.setLeaveDay(generateTimeStamp(clause,i+j));							
						}
					}				
				}
				else if(((CoreMeaning)((Token)clause.get(i)).getCoreMeaning()).getWeight() == WeightOriginalRange.POSITIVESTABLE)
				{	//boolean findUnrecognizeToken = false;
					for(int j = 0;i+j<clause.size();j++)
					{	if(clause.get(i+j) instanceof UnrecognizeToken )
						{
							landaInstance.setArrivePlace(generateCityClause(clause, i+j));
						}
						else if(clause.get(i+j) instanceof Token && ((Token)clause.get(i+j)).getProperty() == NumberProperties.DATENUMBER)
						{	if(isRoundTrip == false)					
								landaInstance.setArriveDay(generateTimeStamp(clause,i+j));
							else
								landaInstance.setLeaveDay(generateTimeStamp(clause,i+j));
						}
					}				
				}	
				else
				{
					if(clause.get(i) instanceof UnrecognizeToken)
						landaInstance.setArrivePlace(generateCityClause(clause, i));
					else if (clause.get(i) instanceof Token && ((Token)clause.get(i)).getProperty() == NumberProperties.DATENUMBER)
						{landaInstance.setLeaveDay(generateTimeStamp(clause,i));
						}
				}
			}
		}
		if(findLANDACoreMeaning == true)
			return landaInstance;
		return null;
	}
	
	/**
     * Calculate weight of one clause
     * 
     * @param clause
     * 			 one sentence clause 
     * @return the weight of this sentence clause.           
     */
	private float fixWeight(ArrayList<TokenGeneralKind> clause)
	{	float weight = 1f;
		for(int i = 0;i<clause.size();i++)
		{	if(clause.get(i) instanceof Token && (((Token)clause.get(i)).getProperty() ==  Properties.ADJV 
					||((Token)clause.get(i)).getProperty() ==  Properties.VERB )
						&& ((Token)clause.get(i)).getFunctionType() ==  ServiceProperty.GENERALPROPERTY )
			weight *= ((Token)clause.get(i)).getWeight();
			else 
				weight *= 1;
		}
		return weight;
	}
	
	/**
     * Fix weights of all clauses between -1f and 1f
     * 
     * @param weightArray
     * 			 a set of all weights
     * @return a set of all weights between -1f and 1f.           
     */
	private ArrayList<Float> rerangeWeights(ArrayList<Float> weightArray)
	{	float max = 0;
		ArrayList<Float> copyArray = weightArray;
		for(int i =0;i<copyArray.size();i++)
		{	if(Math.abs(copyArray.get(i))> max)
				max = Math.abs(copyArray.get(i));		
		}
		for(int i =0;i<copyArray.size();i++)
		{	float valuehere = copyArray.get(i);
			copyArray.set(i, valuehere/max);			
		}
		return copyArray;
	}	
	
	/**
     * Print the detail of result for testing.           
     */
	@SuppressWarnings("unchecked")
	public void printMessage()
	{			
		for(BasicFunctionType here : propertyArray )
		{	
			if(here instanceof CostFunctionType)
			{	System.out.println(here.getClass());
				float[] f = ((CostFunctionType)here).getPriceRange();
				if(f[1] != 0f)
					System.out.println("price :"+f[0] + " ~ " +f[1]);
				else
					System.out.println("price percentage:  "+((CostFunctionType)here).getgeneralPriceRequest());
			}
			else if(here instanceof LeaveAndArriveFunctionType)				
			{	System.out.println(here.getClass());
				System.out.println("leave : "+((LeaveAndArriveFunctionType)here).getLeavePlace() + "   at:" 
			+((LeaveAndArriveFunctionType)here).getLeaveDay()+ "---arrive : "
			+((LeaveAndArriveFunctionType)here).getArrivePlace() + "   at:" 
			+((LeaveAndArriveFunctionType)here).getArriveDay());
			}		
			else if(here instanceof NumberofStopFunctionType)				
			{	System.out.println(here.getClass());
				System.out.println("number of Stop : "+((NumberofStopFunctionType)here).getNumberOfStop());
			}else if(here instanceof SeatClassFunctionType)				
			{	System.out.println(here.getClass());
				System.out.println("seat class : "+((SeatClassFunctionType)here).getSeatClass());
			}
			else if(here instanceof DurationFunctionType)				
			{	System.out.println(here.getClass());
				System.out.println("duration : "+((DurationFunctionType)here).getDuationInHour());
			}
			else if(here != null)
			{	System.out.println(here.toString());
			}
			System.out.print("weights after fix: ---");
			System.out.println(((FunctionType)here).getWeight());
		}		
		System.out.println("------------------------");
		if(propertyArrayOfBackTrip.size() >0)
		for(BasicFunctionType here : propertyArrayOfBackTrip )
		{	
			if(here instanceof CostFunctionType)
			{	System.out.println(here.getClass());
				float[] f = ((CostFunctionType)here).getPriceRange();
				if(f[1] != 0f)
					System.out.println("price :"+f[0] + " ~ " +f[1]);
				else
					System.out.println("price percentage:  "+((CostFunctionType)here).getgeneralPriceRequest());
			}
			else if(here instanceof LeaveAndArriveFunctionType)				
			{	System.out.println(here.getClass());
				System.out.println("leave : "+((LeaveAndArriveFunctionType)here).getLeavePlace() + "   at:" 
			+((LeaveAndArriveFunctionType)here).getLeaveDay()+ "---arrive : "
			+((LeaveAndArriveFunctionType)here).getArrivePlace() + "   at:" 
			+((LeaveAndArriveFunctionType)here).getArriveDay());
			}		
			else if(here instanceof NumberofStopFunctionType)				
			{	System.out.println(here.getClass());
				System.out.println("number of Stop : "+((NumberofStopFunctionType)here).getNumberOfStop());
			}else if(here instanceof SeatClassFunctionType)				
			{	System.out.println(here.getClass());
				System.out.println("seat class : "+((SeatClassFunctionType)here).getSeatClass());
			}
			else if(here instanceof DurationFunctionType)				
			{	System.out.println(here.getClass());
				System.out.println("duration : "+((DurationFunctionType)here).getDuationInHour());
			}
			else if(here != null)
			{	System.out.println(here.toString());
			}
			System.out.print("weights after fix: ---");
			System.out.println(((FunctionType)here).getWeight());
		}
		System.out.println("============================" );
		for(int i =0; i <totalArray.size(); i ++)
		{
			for(int j =0; j <((ArrayList<Object>) totalArray.get(i)).size(); j ++)
			{
				if(((ArrayList<Object>) totalArray.get(i)).get(j) instanceof BasicFunctionType)
					System.out.println(((ArrayList<Object>) totalArray.get(i)).get(j).getClass());
				else if(((ArrayList<Object>) totalArray.get(i)).get(j) instanceof ConnectiveOperator)
					System.out.println(((ArrayList<Object>) totalArray.get(i)).get(j).toString());
			}
		}
	}
}

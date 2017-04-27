package com.prefengine.service.compilerForPF;

import java.io.FileNotFoundException;
import java.io.IOException;
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
	private ArrayList<ArrayList<Object>> totalArray = new ArrayList<ArrayList<Object>>();
	
	/** Define the output ArrayList of non-functional request. */
	private ArrayList<BasicFunctionType> propertyArray = new ArrayList<BasicFunctionType>();

	/** Define the output ArrayList of connective operators between requests. */
	private ArrayList<ConnectiveOperator> connevtiveOperatorArray = new ArrayList<ConnectiveOperator>();

	/** Store the output of scanner */
	private ArrayList<ArrayList<TokenGeneralKind>> targetClauseArray = new ArrayList<ArrayList<TokenGeneralKind>> ();
	
	
	/** a set of connective operators as an output of scanner  */
	private Data data = new Data ();
	
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
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public Parser(Scanner scanner) throws FileNotFoundException, IOException
	{	
		this.targetClauseArray = scanner.getClauseArray();
		this.connevtiveOperatorArray = scanner.getConnectiveOperatorArray();
		this.isRoundTrip = false;
		Data.setupData();
	}
	
	/**
     * Start the whole parser function as an interface.
     * 
     * @return the list of final result of Clause instances with left-hand, connective operator and right-hand; for round trip also.
     */
	public ArrayList<ArrayList<Object>> parserEngine()
	{			
		//ArrayList<Float> weight = new ArrayList<Float>();
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
			propertyArray.add(here);
//			((BasicFunctionType)(propertyArray.get(propertyArray.size()-1))).setWeight(fixFunctionWeight(midResult.get(i)));
//			weight.add(fixFunctionWeight(midResult.get(i)));						
		}	
//		weight = rerangeWeights(weight);		
//		//set weight after re-range as a property of FunctionType instance
//		for(int i = 0;i< propertyArray.size();i++)
//		{
//			((BasicFunctionType)propertyArray.get(i)).setWeight(weight.get(i));
//		}
		ArrayList<Object> resultOfGoTrip = new ArrayList<Object>();
		for(int index =0; index< propertyArray.size() -1; index ++)
		{
			resultOfGoTrip.add(propertyArray.get(index));
			resultOfGoTrip.add(connevtiveOperatorArray.get(index));
		}
		resultOfGoTrip.add(propertyArray.get(propertyArray.size() -1));
		totalArray.add(resultOfGoTrip);
		if(this.isRoundTrip)
			generateSecondSliceForRoundTrip();
		//weight = new ArrayList<Float>();
		if(isRoundTrip && targetClauseArrayofBackTrip.size() >0)
	{	
	
//			weight = rerangeWeights(weight);
//			for(int i = 0;i< propertyArrayOfBackTrip.size();i++)
//			{	
//				((BasicFunctionType)propertyArrayOfBackTrip.get(i)).setWeight(weight.get(i));
//			}
			resultOfGoTrip =  new ArrayList<Object>(); 
			for(int index =0; index< propertyArrayOfBackTrip.size() -1; index ++)
			{
				resultOfGoTrip.add(propertyArrayOfBackTrip.get(index));
				resultOfGoTrip.add(connevtiveOperatorArrayOfBackTrip.get(index));
			}
			resultOfGoTrip.add(propertyArrayOfBackTrip.get(propertyArrayOfBackTrip.size() -1));
			totalArray.add(resultOfGoTrip);						
		}
		for(ArrayList<Object> o : totalArray)
		{
			for(Object oo : o)
			{
				System.out.println(oo.getClass());
			}
			System.out.println("-----------");
		}
		return this.totalArray;
	}
	
	/**
     * Get the arrays of go and round trip's function properties and operations.
     * 
     * @return the ArrayList of the total array.           
     */
	public ArrayList<ArrayList<Object>> getTotalArray()
	{	return this.totalArray;
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
     * @param  indexOfFunctionFound
     * 				list of the index of the function-type recognized in one literal clause
     * @param  indexOfClause
     * 				index of the literal clause in the whole clause list          
     */
	private void separateFunctionTypeWithoutCojunction ( ArrayList<Integer> indexOfFunctionFound,int indexOfClause)
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
			int endIndex = 0;
			int startIndex = 0;
			for(int index = 0; index < indexOfFunctionFound.size(); index ++)
			{
				ArrayList<TokenGeneralKind> target= new ArrayList<TokenGeneralKind> ();
				endIndex = indexOfFunctionFound.get(index);
				for(int i = startIndex; i<endIndex;i++)
					target.add( targetClauseArray.get(indexOfClause).get(i));
				separateFunctionTypeList.add(target);
				startIndex = endIndex ;				
			}
			if(endIndex != targetClauseArray.get(indexOfClause).size()-1)
			{
				ArrayList<TokenGeneralKind> target= new ArrayList<TokenGeneralKind> ();
				for(int i = endIndex; i<targetClauseArray.get(indexOfClause).size();i++)
					target.add( targetClauseArray.get(indexOfClause).get(i));
				separateFunctionTypeList.add(target);
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
			int endIndex = 0;
			int startIndex = 0;
			for(int index = 0; index < indexOfFunctionFound.size(); index ++)
			{
				ArrayList<TokenGeneralKind> target= new ArrayList<TokenGeneralKind> ();
				endIndex = indexOfFunctionFound.get(index);
				for(int i = startIndex; i<endIndex;i++)
					target.add( targetClauseArray.get(indexOfClause).get(i));
				separateFunctionTypeList.add(target);
				startIndex = endIndex ;
				
			}
			if(endIndex != targetClauseArray.get(indexOfClause).size()-1)
			{
				ArrayList<TokenGeneralKind> target= new ArrayList<TokenGeneralKind> ();
				for(int i = endIndex; i<targetClauseArray.get(indexOfClause).size();i++)
					target.add( targetClauseArray.get(indexOfClause).get(i));
				separateFunctionTypeList.add(target);
				
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
			int endIndex = 0;
			int startIndex = 0;
			for(int index = 0; index < indexOfFunctionFound.size(); index ++)
			{
				ArrayList<TokenGeneralKind> target= new ArrayList<TokenGeneralKind> ();
				endIndex = indexOfFunctionFound.get(index);
				for(int i = startIndex; i<endIndex;i++)
					target.add( targetClauseArray.get(indexOfClause).get(i));
				separateFunctionTypeList.add(target);
				startIndex = endIndex ;
			}
			if(endIndex != targetClauseArray.get(indexOfClause).size()-1)
			{
				ArrayList<TokenGeneralKind> target= new ArrayList<TokenGeneralKind> ();
				for(int i = endIndex; i<targetClauseArray.get(indexOfClause).size();i++)
					target.add( targetClauseArray.get(indexOfClause).get(i));
				separateFunctionTypeList.add(target);				
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
			int endIndex = 0;
			int startIndex = 0;
			for(int index = 0; index < indexOfFunctionFound.size(); index ++)
			{
				ArrayList<TokenGeneralKind> target= new ArrayList<TokenGeneralKind> ();
				endIndex = indexOfFunctionFound.get(index);
				for(int i = startIndex; i<endIndex;i++)
					target.add( targetClauseArray.get(indexOfClause).get(i));
				separateFunctionTypeList.add(target);
				startIndex = endIndex ;				
			}
			if(endIndex != targetClauseArray.get(indexOfClause).size()-1)
			{
				ArrayList<TokenGeneralKind> target= new ArrayList<TokenGeneralKind> ();
				for(int i = endIndex; i<targetClauseArray.get(indexOfClause).size();i++)
					target.add( targetClauseArray.get(indexOfClause).get(i));
				separateFunctionTypeList.add(target);				
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
	
	/**
     * if this request is round trip, generate another slice for connecting to API.          
     */
	
	private void generateSecondSliceForRoundTrip()
	{	
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
					City oldArrivePlace = ((LeaveAndArriveFunctionType) function).getArrivePlace();
					City oldLeavePlace = ((LeaveAndArriveFunctionType) function).getLeavePlace();
					((LeaveAndArriveFunctionType) function).setArrivePlace(oldLeavePlace);
					((LeaveAndArriveFunctionType) function).setLeavePlace(oldArrivePlace);
				}
			}
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
			ArrayList <Integer> functionIndexArray = new ArrayList <Integer>();
			ArrayList <BasicFunctionType> functionTypeArray = new ArrayList <BasicFunctionType> ();
			boolean isFirstFunctionType = false;
			for(int secondIndex = 0;secondIndex <  copyClause.get(i).size(); secondIndex ++ )
			{	if((copyClause.get(i).get(secondIndex) instanceof Token)
					&&(((Token)copyClause.get(i).get(secondIndex)).getFunctionType() != ServiceProperty.GENERALPROPERTY)
					&&(((Token)copyClause.get(i).get(secondIndex)).getFunctionType() != null))
				{	if(isFirstFunctionType == false)
					{
						isFirstFunctionType = true;
					}
					else if(!functionTypeArray.contains(((Token)copyClause.get(i).get(secondIndex)).getFunctionType()))
					{
						functionIndexArray.add(secondIndex);
						functionTypeArray.add(((Token)copyClause.get(i).get(secondIndex)).getFunctionType());
					}
				}
				if(copyClause.get(i).get(secondIndex) instanceof Token 
					&& ((Token)copyClause.get(i).get(secondIndex)).getFunctionType() == ServiceProperty.ROUNDTRIP)
				{	if(fixFunctionWeight(copyClause.get(i)) > 0)
						isRoundTrip = true;
					else
						isRoundTrip = false;				
				}				
			}		
			boolean hasChanged = false;
			while( hasChanged == true )
			{
				hasChanged = false;
				if(functionTypeArray.contains(ServiceProperty.PACKAGERULE) 
				
						&& functionTypeArray.contains(ServiceProperty.DSERVICE) )
				{
					if(functionTypeArray.indexOf(ServiceProperty.PACKAGERULE) > functionTypeArray.indexOf(ServiceProperty.DSERVICE))
						functionIndexArray.remove(functionTypeArray.indexOf(ServiceProperty.PACKAGERULE)-1);
					else
						functionIndexArray.remove(functionTypeArray.indexOf(ServiceProperty.PACKAGERULE)-1);
					functionTypeArray.remove(ServiceProperty.PACKAGERULE);
					hasChanged = true;
				}
				if( (functionTypeArray.contains(ServiceProperty.SERVICE) 
						&& functionTypeArray.contains(ServiceProperty.DSERVICE) )
					|| (functionTypeArray.contains(ServiceProperty.SERVICE) 
							&& functionTypeArray.contains(ServiceProperty.ASERVICE) ) )
				{
					if(functionTypeArray.indexOf(ServiceProperty.SERVICE) > functionTypeArray.indexOf(ServiceProperty.DSERVICE)
							||functionTypeArray.indexOf(ServiceProperty.SERVICE) > functionTypeArray.indexOf(ServiceProperty.ASERVICE))
						
						functionIndexArray.remove(functionTypeArray.indexOf(ServiceProperty.SERVICE)-1);
					else
						functionIndexArray.remove(functionTypeArray.indexOf(ServiceProperty.SERVICE));
					functionTypeArray.remove(ServiceProperty.SERVICE);
					hasChanged = true;
				} 
				if(functionTypeArray.contains(ServiceProperty.ASERVICE) 
						&& functionTypeArray.contains(ServiceProperty.DSERVICE) ) 
				{
					if(functionTypeArray.indexOf(ServiceProperty.ASERVICE) > functionTypeArray.indexOf(ServiceProperty.DSERVICE))						
						functionIndexArray.remove(functionTypeArray.indexOf(ServiceProperty.ASERVICE)-1);
					else
						functionIndexArray.remove(functionTypeArray.indexOf(ServiceProperty.ASERVICE));
					functionTypeArray.remove(ServiceProperty.ASERVICE);
					hasChanged = true;
				}
				if(functionTypeArray.contains(ServiceProperty.REPUTATION))
				{
					if( functionTypeArray.contains(ServiceProperty.RELIABILITY) )
					{	
						if(functionTypeArray.indexOf(ServiceProperty.RELIABILITY) > functionTypeArray.indexOf(ServiceProperty.REPUTATION))											
							functionIndexArray.remove(functionTypeArray.indexOf(ServiceProperty.RELIABILITY)-1);
						else
							functionIndexArray.remove(functionTypeArray.indexOf(ServiceProperty.RELIABILITY));				
						functionTypeArray.remove(ServiceProperty.RELIABILITY);
						hasChanged = true;
					}
					else if(functionTypeArray.contains(ServiceProperty.CONVENIENT) )
					{
						if(functionTypeArray.indexOf(ServiceProperty.CONVENIENT) > functionTypeArray.indexOf(ServiceProperty.REPUTATION))																	
							functionIndexArray.remove(functionTypeArray.indexOf(ServiceProperty.CONVENIENT)-1);
						else
							functionIndexArray.remove(functionTypeArray.indexOf(ServiceProperty.CONVENIENT));
						functionTypeArray.remove(ServiceProperty.CONVENIENT);
						hasChanged = true;
					} 
				}
			}
			ArrayList <Integer> removeNull = new ArrayList <Integer>();
			for(Integer index : functionIndexArray)
			{
				if(index != null)
					removeNull.add(index);
			}
			if(removeNull.size() >= 1)
			{ separateFunctionTypeWithoutCojunction( removeNull, i);
			for(Integer b : removeNull)
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
			{ if(!functionTypeArray.contains(((Token)token).getFunctionType()))
					{functionTypeArray.add(((Token)token).getFunctionType());
					}
			
			if(token instanceof Token 
				&& ((Token)token).getFunctionType() == ServiceProperty.ROUNDTRIP)
			{	if(fixFunctionWeight(clause) > 0)
					isRoundTrip = true;
				else
					isRoundTrip = false;				
			}
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
			else if(functionTypeArray.contains(ServiceProperty.LAYOUT) )
				return getLayoutFunctionType(clause);
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
	{
		//the airline rank field will be stored as the real rank number of specific airline so is the range of ranks.
		ArrayList<String> rank = new ArrayList<String>();
		String [] rankRange = new String[2];
		for(int i = 0; i<  clause.size();i++)
		{
			if(clause.get(i) instanceof Token && ((CoreMeaning)((Token)clause.get(i)).getCoreMeaning()).getBasicProperty() == Properties.NUMBER)
			{
				if(i+2 < clause.size() && clause.get(i +1).getImage().equals("to"))
				{
					rankRange[0] = ((Token)clause.get(i)).getImage();
					if( clause.get(i+2) instanceof Token 
							&& ((CoreMeaning)((Token)clause.get(i+2)).getCoreMeaning()).getBasicProperty() == Properties.NUMBER)
						rankRange[1] = ((Token)clause.get(i+2)).getImage();
					else
					{
						rankRange[0] = null;
						rank.add(((Token)clause.get(i)).getImage());
					}
				}
				else
					rank.add(((Token)clause.get(i)).getImage());			
			}
			else if(clause.get(i) instanceof UnrecognizeToken )
			{
				int j = 1;
				String airlineName = "";
				while(i+j <  clause.size() && clause.get(i+ j) instanceof UnrecognizeToken)
					airlineName = airlineName + " " + ((UnrecognizeToken) clause.get(i+ j)).getImage();
				String airlineCode;
				if((airlineCode = data.matchToAirLineName(airlineName)) != null)
					{
						String rankNumber = data.getAirlineRankNumberByName(airlineCode);
						rank.add(rankNumber);						
					}
			}
			else if(clause.get(i) instanceof Token 
					&& ((CoreMeaning)((Token)clause.get(i)).getCoreMeaning()).getBasicProperty() == Properties.ADJV
					&& ((CoreMeaning)((Token)clause.get(i)).getCoreMeaning()).getBasicFunctionType() == ServiceProperty.REPUTATION)
			{
				
			}		
		}
		float weight = fixFunctionWeight(clause);
		if(rankRange.length == 2)
		{
			for(int i = Integer.parseInt(rankRange[0]);i<= Integer.parseInt(rankRange[1]); i ++)
				rank.add(String.valueOf(i));
		}
		else if( weight >= 1.1f && weight <= 1.65)
		{
			// low level request: top 5
			for(int i = 1; i<6; i++)
				rank.add(String.valueOf(i));
		}
		else if( weight > 1.65f &&  weight < 2f)
		{
			// higher level request: top 3
			for(int i = 1; i<4; i++)
				rank.add(String.valueOf(i));
		}
		else if(  weight >= 2f)
		{
			// highest level request: top 1
			rank.add("1");
		}
		ReputationFunctionType reputation = new ReputationFunctionType(ServiceProperty.REPUTATION);
		reputation.setRankElements(rank);
		return reputation;
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
					{seatClassInstance.setSeatClass("FIRST");
					return seatClassInstance;					
					}
				else if(((CoreMeaning)((Token)clause.get(i)).getCoreMeaning()).getWeight() == WeightOriginalRange.POSITIVEENHANCE)
					{seatClassInstance.setSeatClass("BUSINESS");
					return seatClassInstance;
					}
				else if(((CoreMeaning)((Token)clause.get(i)).getCoreMeaning()).getWeight() == WeightOriginalRange.POSITIVESTABLE)
					{seatClassInstance.setSeatClass("PREMIUM");
					return seatClassInstance;					
					}
				else if(((CoreMeaning)((Token)clause.get(i)).getCoreMeaning()).getWeight() == WeightOriginalRange.STABLE)
					{seatClassInstance.setSeatClass("COACH");
					return seatClassInstance;
					}
			}
		}
		seatClassInstance.setSeatClass("COACH");
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
     * Analyze an layout service-property clause,and generate an LayoutFunctionType instance.
     * 
     * @param clause
     * 			 one sentence clause 
     * @return an LayoutFunctionType instance.           
     */
	private BasicFunctionType getLayoutFunctionType (ArrayList<TokenGeneralKind> clause)
	{	float layoutInHour = 0;
		LayoutFunctionType layoutInstance = new LayoutFunctionType(ServiceProperty.LAYOUT);
		for(int i = 1; i < clause.size();i++)
		{	if (clause.get(i) instanceof Token  
					&& ((Token)clause.get(i)).getFunctionType() == ServiceProperty.DURATION 
					&& ((Token)clause.get(i)).getCoreMeaning() == CoreMeaningOne.HOUR
					&& (clause.get(i-1) instanceof Token && 
					((Token)clause.get(i-1)).getProperty() == NumberProperties.REGULARNUMBER))
			{	layoutInHour = layoutInHour + Float.valueOf(((Token)clause.get(i-1)).getImage());				
			}
			else if (clause.get(i) instanceof Token  
					&& ((Token)clause.get(i)).getFunctionType() == ServiceProperty.DURATION 
					&& ((Token)clause.get(i)).getCoreMeaning() == CoreMeaningOne.MINUT
					&& (clause.get(i-1) instanceof Token && 
					((Token)clause.get(i-1)).getProperty() == NumberProperties.REGULARNUMBER))
			{	layoutInHour = layoutInHour + (Float.valueOf(((Token)clause.get(i-1)).getImage())/ 60f);
			}
			else if (clause.get(i) instanceof Token  
					&& ((Token)clause.get(i)).getFunctionType() == ServiceProperty.DURATION 
					&& ((Token)clause.get(i)).getCoreMeaning() == CoreMeaningOne.DAY
					&& (clause.get(i-1) instanceof Token && 
					((Token)clause.get(i-1)).getProperty() == NumberProperties.REGULARNUMBER))
			{	layoutInHour = layoutInHour + (Float.valueOf(((Token)clause.get(i-1)).getImage())* 24f);
			}
		}
		if( layoutInHour == 0)
		{
			float weight = fixFunctionWeight(clause);
			
			// eg: key word : less = -1.5f; -1/weight means flip the weight to possibility range with meaning; 
			//					0.5f means make the possibility more reasonable : 0.3 for less
			if(weight <= -1f)
				weight = 0.5f  *(-1/ weight) ;
			
			// eg: key word : not tiny = -0.5f; -1/weight means flip the weight to possibility range with meaning; 
			//					0.75f means make the possibility more reasonable : when the possibility is above 1,
			//					means user wants longer duration
			else if(weight > -1f && weight <= 0)
				weight = 0.75f  *(-1/ weight);
			
			// eg: key word : short = 0.5f, many = 1.5f;  the original value of weight is good enough; 

			else if(weight > 0 && weight <= 1f)
				;
			else if(weight > 1f )
				;
			
			layoutInstance.setLayoutInPossibility(weight);
			
		}
		else
			layoutInstance.setLayoutInHour(layoutInHour);
		
		return layoutInstance;
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
					&& ((Token)clause.get(i)).getCoreMeaning() == CoreMeaningOne.HOUR
					&& (clause.get(i-1) instanceof Token && 
					((Token)clause.get(i-1)).getProperty() == NumberProperties.REGULARNUMBER))
			{	durationInHour = durationInHour + Float.valueOf(((Token)clause.get(i-1)).getImage());				
			}
			else if (clause.get(i) instanceof Token  
					&& ((Token)clause.get(i)).getFunctionType() == ServiceProperty.DURATION 
					&& ((Token)clause.get(i)).getCoreMeaning() == CoreMeaningOne.MINUT
					&& (clause.get(i-1) instanceof Token && 
					((Token)clause.get(i-1)).getProperty() == NumberProperties.REGULARNUMBER))
			{	durationInHour = durationInHour + (Float.valueOf(((Token)clause.get(i-1)).getImage())/ 60f);
			}
			else if (clause.get(i) instanceof Token  
					&& ((Token)clause.get(i)).getFunctionType() == ServiceProperty.DURATION 
					&& ((Token)clause.get(i)).getCoreMeaning() == CoreMeaningOne.DAY
					&& (clause.get(i-1) instanceof Token && 
					((Token)clause.get(i-1)).getProperty() == NumberProperties.REGULARNUMBER))
			{	durationInHour = durationInHour + (Float.valueOf(((Token)clause.get(i-1)).getImage())* 24f);
			}
		}
		if( durationInHour == 0)
		{
			float weight = fixFunctionWeight(clause);
			
			// eg: key word : less = -1.5f; -1/weight means flip the weight to possibility range with meaning; 
			//					0.5f means make the possibility more reasonable : 0.3 for less
			if(weight <= -1f)
				weight = 0.5f  *(-1/ weight) ;
			
			// eg: key word : not tiny = -0.5f; -1/weight means flip the weight to possibility range with meaning; 
			//					0.75f means make the possibility more reasonable : when the possibility is above 1,
			//					means user wants longer duration
			else if(weight > -1f && weight <= 0)
				weight = 0.75f  *(-1/ weight);
			
			// eg: key word : short = 0.5f, many = 1.5f;  the original value of weight is good enough; 

			else if(weight > 0 && weight <= 1f)
				;
			else if(weight > 1f )
				;
			
			durationInstance.setDuationInPossibility(weight);
			
		}
		else
			durationInstance.setDuationInHour(durationInHour);
		
		return durationInstance;
	}
	
	/**
     * Analyze an number-of-stop service-property clause,and generate an NumberOfStopFunctionType instance.
     * 
     * @param clause
     * 			 one sentence clause 
     * @return an NumberOfStopFunctionType instance.           
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
					|| ((((Token)clause.get(i)).getCoreMeaning()  == CoreMeaningOne.NUMBERTYPE) 
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
			if(clause.get(i) instanceof Token && (((Token)clause.get(i)).getCoreMeaning()  == CoreMeaningOne.NUMBERTYPE) 
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
	private TokenGeneralKind generateCityClause(ArrayList<TokenGeneralKind> clause,int startIndex )
	{	boolean canBreak = false;
		String cityName = "";
		String possibleCountryName = null;
		for(int j = startIndex;j<clause.size() && canBreak == false;j++)
		{	if(clause.get(j) instanceof UnrecognizeToken )
			{	
				for(int z = 0; ( j + z)<clause.size() ;z ++)
				{	if(clause.get( j + z) instanceof UnrecognizeToken )
					{	cityName = cityName + " " + clause.get( j + z) ;
						String name;						
						if(j + z + 1 < clause.size() && clause.get( j + z + 1)instanceof UnrecognizeToken 	)
							possibleCountryName = ((UnrecognizeToken)clause.get( j + z)).getImage();
						if((name = data.matchToAirLineName(((UnrecognizeToken)clause.get( j + z)).getImage())) != null)
						{
							return new AirLine(name);
						}
						else if((name = data.matchToCityName(((UnrecognizeToken)clause.get( j + z)).getImage(),possibleCountryName)) != null)
						{
							return new City(name);
						}
					}
					else 
					{
						z = clause.size() - j + 1;
						canBreak = true;
					}
				}			
			}
		}
		String name;
			if((name = data.matchToAirLineName(cityName)) != null)
				return new AirLine(name);
			else if((name = data.matchToCityName(cityName,possibleCountryName)) != null)
				return new City(name);
		
		return null;
	}
	
	/**
     * organize date number user put in to TimeStamp format.
     * 
     * @param clause
     * 			 one sentence clause 
     * @param startIndex
     * 			 the start index of clause to scan city name 
     * @return date information in String format               
     */	
	private String generateTimeStamp(ArrayList<TokenGeneralKind> clause,int startIndex )
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
		{	dateNumber = dateNumber + Year.now().getValue() + "-" + datenumberArray[0] + "-" + datenumberArray[1] ; 
		}
		else if(datenumberArray.length == 3)
		{	dateNumber = datenumberArray[2] + "-" + datenumberArray[0] + "-" + datenumberArray[1] ; 							 
		}
		return dateNumber;
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
				City cityCode;
				if(((CoreMeaning)((Token)clause.get(i)).getCoreMeaning()).getWeight() == WeightOriginalRange.NEGATIVESTABLE)
				{//boolean findUnrecognizeToken = false;					
					for(int j = 0;j<5;j++)
					{
						if(  i + j < clause.size() &&clause.get(i+j) instanceof UnrecognizeToken )
						{	if(( cityCode = (City)generateCityClause(clause, i+j)) != null);							
							landaInstance.setLeavePlace(cityCode);
						}
						else if(i + j < clause.size() &&clause.get(i+j) instanceof Token && ((Token)clause.get(i+j)).getProperty() == NumberProperties.DATENUMBER)
						{
							landaInstance.setLeaveDay(generateTimeStamp(clause,i+j));							
						}
					}				
				}
				else if(((CoreMeaning)((Token)clause.get(i)).getCoreMeaning()).getWeight() == WeightOriginalRange.POSITIVESTABLE)
				{	//boolean findUnrecognizeToken = false;
					for(int j = 0;i+j<clause.size();j++)
					{	if( i+j < clause.size() && clause.get(i+j) instanceof UnrecognizeToken )
						{
							if(( cityCode = (City)generateCityClause(clause, i+j)) != null);
							landaInstance.setArrivePlace(cityCode);
						}
						else if( i+j < clause.size() && clause.get(i+j) instanceof Token && ((Token)clause.get(i+j)).getProperty() == NumberProperties.DATENUMBER)
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
						{if(( cityCode = (City)generateCityClause(clause, i)) != null)
							landaInstance.setArrivePlace(cityCode);
						
						}
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
     * Calculate functional weight of one clause not the weight of the clause
     * 
     * @param clause
     * 			 one sentence clause 
     * @return the weight of this sentence clause.           
     */
	private float fixFunctionWeight(ArrayList<TokenGeneralKind> clause)
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
	
//	/**
//     * Fix weights of all clauses between -1f and 1f
//     * 
//     * @param weightArray
//     * 			 a set of all weights
//     * @return a set of all weights between -1f and 1f.           
//     */
//	private ArrayList<Float> rerangeWeights(ArrayList<Float> weightArray)
//	{	float max = 0;
//		ArrayList<Float> copyArray = weightArray;
//		for(int i =0;i<copyArray.size();i++)
//		{	if(Math.abs(copyArray.get(i))> max)
//				max = Math.abs(copyArray.get(i));		
//		}
//		for(int i =0;i<copyArray.size();i++)
//		{	float valuehere = copyArray.get(i);
//			copyArray.set(i, valuehere/max);			
//		}
//		return copyArray;
//	}	
	
	/**
     * Print the detail of result for testing.           
     */
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
			System.out.println("    .................." );
			//System.out.println(((BasicFunctionType)here).getWeight());
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
			System.out.println("    ......................" );
			//System.out.println(((BasicFunctionType)here).getWeight());
		}
		System.out.println("===============...............=============" );
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

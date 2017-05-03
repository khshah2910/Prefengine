package com.prefengine.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.prefengine.domain.Itinerary;
import com.prefengine.domain.SearchAttributes;
import com.prefengine.service.APIService;
import com.prefengine.service.SearchCriteria;
import com.prefengine.service.compilerForPF.CostFunctionType;
import com.prefengine.service.compilerForPF.DurationFunctionType;
import com.prefengine.service.compilerForPF.LayoutFunctionType;
import com.prefengine.service.compilerForPF.LeaveAndArriveFunctionType;
import com.prefengine.service.compilerForPF.MileageFunctionType;
import com.prefengine.service.compilerForPF.NumberofStopFunctionType;
import com.prefengine.service.compilerForPF.Parser;
import com.prefengine.service.compilerForPF.ReputationFunctionType;
import com.prefengine.service.compilerForPF.Scanner;
import com.prefengine.service.compilerForPF.SeatClassFunctionType;
import com.prefengine.service.SearchService;

/**
 * Servlet implementation class SearchController
 */
@WebServlet("/SearchController")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchController() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		SearchCriteria searchCriteria = new SearchCriteria();
		SearchService service = new SearchService();
		SearchCriteria searchCriteriaFromSentence = new SearchCriteria();
		SearchService serviceFromSentence = new SearchService();
		SearchAttributes searchAttrFromSentence = null ;			
		ArrayList<Itinerary> tripRecordFromSentence = null;
		String outputString= "";
		String sentenceInput = "eg: I want flight from boston to new york....";
		
		try {
			
			String departure =request.getParameter("departure");
			String destination =request.getParameter("destination");
			String departureDate =request.getParameter("departureDate");
			String returnDate =request.getParameter("returnDate");
			String[] stops =request.getParameterValues("stops");
			String[] cabin = request.getParameterValues("cabin");
 			String numberOfPassengers = request.getParameter("numberOfPassengers");

 			sentenceInput = request.getParameter("requirementSentence");
			//int actualStops = Integer.parseInt(stops);
			//String price =request.getParameter("price");
			searchCriteria.setDeparture(departure);
			searchCriteria.setDestination(destination);
			searchCriteria.setDepartureDate(departureDate);
			searchCriteria.setNumberOfPassengers(Integer.parseInt(numberOfPassengers));
			//================================================================================
			// Get non functional attributes and their operators.
			// This array is at form: attribute-operator-attribute-operator-attribute...
			ArrayList<String> non_functional_attributes = new ArrayList<String>();
			/*String[] attributes =request.getParameterValues("req1");
			String[] operators =request.getParameterValues("operator1");
			if(attributes.length==0 || operators.length==0){
			}else{
				int k = 0;
				for(k = 0; k < operators.length; k++){
					non_functional_attributes.add(attributes[k]);
					non_functional_attributes.add(operators[k]);
				}
				//non_functional_attributes.add(attributes[k]);
				searchCriteria.setNonFunctionalAttributes(non_functional_attributes);
			*/
			
			//String maxPrice1 = request.getParameter("maxPrice");
			//String minPrice1 = request.getParameter("minPrice");
			
			
			if(!sentenceInput.equals("eg: I want flight from boston to new york....") )
 			{
				// when there is sentences input from UI
				if(request.getParameter("getCompilerReview") != null)
				{
						
						Scanner scanner = new Scanner(sentenceInput);
						scanner.scannerEngine();
						scanner.printMessage();
						Parser parser = new Parser(scanner);
						parser.parserEngine();
						outputString = parser.generateMessageForUI();					
				}
				else
				{
				searchCriteriaFromSentence.setNumberOfPassengers(1);	
 				Scanner scanner = new Scanner(sentenceInput);
 				scanner.scannerEngine();
 				scanner.printMessage();
 				Parser parser = new Parser(scanner);
 				ArrayList<ArrayList<Object>> resultFromCompiler = parser.parserEngine();
 				parser.printMessage();
 				non_functional_attributes = parser.getNonFunctionalAttributeArrayList();
 				//===============================================================================
 				
 				//int actualStops = Integer.parseInt(stops);
 				//String price =request.getParameter("price");
 				searchCriteriaFromSentence.setNonFunctionalAttributes(non_functional_attributes);
 				//even user request round trip, still only use requirements for single trip for now
 				if(resultFromCompiler.size() !=0)
 				{
 					for(Object content : resultFromCompiler.get(0))
 					{
 						if(content instanceof LeaveAndArriveFunctionType)
 						{
 							LeaveAndArriveFunctionType landaInstance = (LeaveAndArriveFunctionType)content;
 							if(landaInstance.getArrivePlace() != null)
 								searchCriteriaFromSentence.setDestination(landaInstance.getArrivePlace().getImage().toUpperCase());
 							if(landaInstance.getLeavePlace() != null)
 	 							searchCriteriaFromSentence.setDeparture(landaInstance.getLeavePlace().getImage().toUpperCase());
 							if(landaInstance.getLeaveDay() != null)
	 							searchCriteriaFromSentence.setDepartureDate(landaInstance.getLeaveDay().toString());
 							else if (landaInstance.getArriveDay() != null)
 								searchCriteriaFromSentence.setDepartureDate(landaInstance.getArriveDay().toString());
 							else
 							{	Date date = new Date();
 								SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
 								String currentDate = "2017-05-03";
 							searchCriteriaFromSentence.setDepartureDate(currentDate);
 								
 								
 							}
 						}
 						else if(content instanceof CostFunctionType)
 						{
 							CostFunctionType costInstance = (CostFunctionType)content;
 							if(costInstance.hasPossibility() == true)
 							{
 								//if there is only general price range requested by user, the max and min price will be same, this is the sign of general price range
 								searchCriteriaFromSentence.setMaxPrice(costInstance.getPriceInPossibility());
 								searchCriteriaFromSentence.setMinPrice(costInstance.getPriceInPossibility());
 								
 							}else
 							{	searchCriteriaFromSentence.setMaxPrice(costInstance.getPriceRange()[1]);
								searchCriteriaFromSentence.setMinPrice(costInstance.getPriceRange()[0]);							
 							}
 						}
 						else if(content instanceof DurationFunctionType)
 						{
 							DurationFunctionType durationInstance = (DurationFunctionType)content;
 							if(durationInstance.hasPossibility() == true)
 							{
 								//if there is only general duration range requested by user, the max and min price will be same, this is the sign of general price range
 								searchCriteriaFromSentence.setMinDuration(durationInstance.getDuationInPossibility());
 								searchCriteriaFromSentence.setMaxDuration(durationInstance.getDuationInPossibility());
 								
 							}else
 							{	searchCriteriaFromSentence.setMaxDuration(durationInstance.getDuationInHour()[1]);
								searchCriteriaFromSentence.setMinDuration(durationInstance.getDuationInHour()[0]);							
 							}
 						}
 						else if(content instanceof NumberofStopFunctionType)
 						{
 							NumberofStopFunctionType stopsInstance = (NumberofStopFunctionType)content;
 							switch( stopsInstance.getNumberOfStop())
 							{
 							case 0:
	 								searchCriteriaFromSentence.setNonStop(true);
 							case 1:
	 								searchCriteriaFromSentence.setOneStop(true);
	 								searchCriteriaFromSentence.setNonStop(true);
 							default:
 								searchCriteriaFromSentence.setTwoOrMoreStop(true);
 								searchCriteriaFromSentence.setOneStop(true);
 								searchCriteriaFromSentence.setNonStop(true);
 							
 							}
 								
 						}
 						else if(content instanceof MileageFunctionType)
 						{
 							MileageFunctionType mileageInstance = (MileageFunctionType)content;
 							if( mileageInstance.hasPossibility() == true)
 							{
 								//if there is only general price range requested by user, the max and min price will be same, this is the sign of general price range
 								searchCriteriaFromSentence.setMaxMileage(mileageInstance.getMileageInPossibility());
 								searchCriteriaFromSentence.setMinMileage(mileageInstance.getMileageInPossibility()); 							
							
 							}
 							else
 							{
 								searchCriteriaFromSentence.setMaxMileage(mileageInstance.getMileage()[1]);
								searchCriteriaFromSentence.setMinMileage(mileageInstance.getMileage()[0]); 							
							
 							}
 								
 						}
 						else if(content instanceof LayoutFunctionType)
 						{
 							LayoutFunctionType layoutInstance = (LayoutFunctionType)content;
 							if( layoutInstance.hasPossibility() == true)
 							{
 								//if there is only general price range requested by user, the max and min price will be same, this is the sign of general price range
 								searchCriteriaFromSentence.setMinLayout(layoutInstance.getLayoutInPossibility());
 								searchCriteriaFromSentence.setMaxLayout(layoutInstance.getLayoutInPossibility()); 							
							
 							}
 							else
 							{
 								searchCriteriaFromSentence.setMinLayout(layoutInstance.getLayoutInHour()[1]);
 								searchCriteriaFromSentence.setMaxLayout(layoutInstance.getLayoutInHour()[01]); 							
							}
 								
 						}
 						else if(content instanceof SeatClassFunctionType)
 						{
 							SeatClassFunctionType seatclassInstance = (SeatClassFunctionType)content;
 							switch( seatclassInstance.getSeatClass() )
 							{
 							
 							case "BUSINESS":
 								searchCriteriaFromSentence.setBusiness(true);
 							case "FIRST":
 								searchCriteriaFromSentence.setFirst(true);
 							default:
 								searchCriteriaFromSentence.setEconomy(true);
 							}
 						}
 						else if(content instanceof ReputationFunctionType)
 						{
 							ReputationFunctionType reputationInstance = (ReputationFunctionType)content;				
 							searchCriteriaFromSentence.setRankElements(reputationInstance.getRankElements());							
 						}
 							
 							
 					}
 					searchCriteriaFromSentence.setReturnDate(searchCriteriaFromSentence.getDepartureDate());
					 searchAttrFromSentence = serviceFromSentence.getSearchAttributes(searchCriteriaFromSentence);
					 tripRecordFromSentence = serviceFromSentence.search(searchCriteriaFromSentence);
					request.setAttribute("searchAttr", searchAttrFromSentence);
					request.setAttribute("tripRecord", tripRecordFromSentence);
 				}
			}
 				
 			}
			else
			{
				String price = request.getParameter("price");
				String minPrice1 = price.split(" - ")[0];
				String maxPrice1 = price.split(" - ")[1];
			if((maxPrice1==null||"".equals(maxPrice1)) && (minPrice1==null||"".equals(minPrice1))){
				searchCriteria.setMaxPrice(0);
				searchCriteria.setMinPrice(0);
			}	
			else{
				float maxPrice = Float.parseFloat(maxPrice1);
				float minPrice = Float.parseFloat(minPrice1);
				searchCriteria.setMaxPrice(maxPrice);
				searchCriteria.setMinPrice(minPrice);
			}
			
			if(stops!=null){
				for(int i=0;i<stops.length;i++){
					if("0".equals(stops[i])){
						searchCriteria.setNonStop(true);
					}
					else if("1".equals(stops[i])){
						searchCriteria.setOneStop(true);
					}
					else if("2".equals(stops[i])){
						searchCriteria.setTwoOrMoreStop(true);
					}
				}
			}
			if(cabin!=null){
				for(int j=0;j<cabin.length;j++){
					if("COACH".equals(cabin[j])){
						searchCriteria.setEconomy(true);
					}
					else if("BUSINESS".equals(cabin[j])){
						searchCriteria.setBusiness(true);
					}
					else if("FIRST".equals(cabin[j])){
						searchCriteria.setFirst(true);
					}
				}
			}
			
			SearchAttributes searchAttr = service.getSearchAttributes(searchCriteria);
			ArrayList<Itinerary> tripRecord = service.search(searchCriteria);
			System.out.println(tripRecord.get(0));
			request.setAttribute("searchAttr", searchAttr);
			request.setAttribute("tripRecord", tripRecord);
			}
		
		}
		catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(request.getParameter("getCompilerReview") == null)
		{
			RequestDispatcher rd = request.getRequestDispatcher("./web/search_result.jsp");
			rd.forward(request, response);	
		}
		else
		{
			request.setAttribute("outputOfReview", outputString);
			request.setAttribute("sentenceRequest", sentenceInput);
			RequestDispatcher rd = request.getRequestDispatcher("./web/review.jsp");
			rd.forward(request, response);	
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

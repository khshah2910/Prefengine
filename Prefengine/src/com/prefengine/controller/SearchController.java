package com.prefengine.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

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

import com.prefengine.service.compilerForPF.LeaveAndArriveFunctionType;
import com.prefengine.service.compilerForPF.Parser;
import com.prefengine.service.compilerForPF.Scanner;
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
		 boolean hasSentenceInput = false;
		try {
			
			String departure =request.getParameter("departure");
			String destination =request.getParameter("destination");
			String departureDate =request.getParameter("departureDate");
			String returnDate =request.getParameter("returnDate");
			String[] stops =request.getParameterValues("stops");
			String[] cabin = request.getParameterValues("cabin");
 			String numberOfPassengers = request.getParameter("numberOfPassengers");

 			String sentenceInput = request.getParameter("requirementSentence");
			//int actualStops = Integer.parseInt(stops);
			//String price =request.getParameter("price");
			searchCriteria.setDeparture(departure);
			searchCriteria.setDestination(destination);
			searchCriteria.setDepartureDate(departureDate);
			searchCriteria.setNumberOfPassengers(Integer.parseInt(numberOfPassengers));
			
			String price = request.getParameter("price");
			String minPrice1 = price.split(" - ")[0];
			String maxPrice1 = price.split(" - ")[1];
			//String maxPrice1 = request.getParameter("maxPrice");
			//String minPrice1 = request.getParameter("minPrice");
			
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
			
 			
// when there is sentences input from UI
			
			
 			if(!sentenceInput.equals("eg: I want flight from boston to new york....") )
 			{
 				hasSentenceInput = true;
 				Scanner scanner = new Scanner(sentenceInput);
 				scanner.scannerEngine();
 				scanner.printMessage();
 				Parser parser = new Parser(scanner);
 				ArrayList<ArrayList<Object>> resultFromCompiler = parser.parserEngine();
 				parser.printMessage();
 				if(resultFromCompiler.size() == 1)
 				{
 					for(Object content : resultFromCompiler.get(0))
 					{
 						if(content instanceof LeaveAndArriveFunctionType)
 						{
 							LeaveAndArriveFunctionType landaInstance = (LeaveAndArriveFunctionType)content;
 							if(landaInstance.getArrivePlace() != null)
 								searchCriteriaFromSentence.setDestination(landaInstance.getArrivePlace().getImage());
 							if(landaInstance.getLeavePlace() != null)
 	 							searchCriteriaFromSentence.setDeparture(landaInstance.getLeavePlace().getImage());
 							if(landaInstance.getLeaveDay() != null)
	 							searchCriteriaFromSentence.setDepartureDate(landaInstance.getLeaveDay().toString());
 							 searchAttrFromSentence = serviceFromSentence.getSearchAttributes(searchCriteriaFromSentence);
 							 tripRecordFromSentence = serviceFromSentence.search(searchCriteriaFromSentence);
 						}
 					}
 				}

 				
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
			
			//if there is sentence input, then use another set of fields to deal with searching
			if(hasSentenceInput = true)
			{
				request.setAttribute("searchAttr", searchAttrFromSentence);
				request.setAttribute("tripRecord", tripRecordFromSentence);
			}
			else
			{
				request.setAttribute("searchAttr", searchAttr);
				request.setAttribute("tripRecord", tripRecord);
			}
		
		}
		catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("./web/search_result.jsp");
		rd.forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

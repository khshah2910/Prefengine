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
		try {
			
			String departure =request.getParameter("departure");
			String destination =request.getParameter("destination");
			String departureDate =request.getParameter("departureDate");
			String returnDate =request.getParameter("returnDate");
			String[] stops =request.getParameterValues("stops");
			String[] cabin = request.getParameterValues("cabin");
 			String numberOfPassengers = request.getParameter("numberOfPassengers");
 			
 			
 			
 			
			//int actualStops = Integer.parseInt(stops);
			String price =request.getParameter("price");
			searchCriteria.setDeparture(departure);
			searchCriteria.setDestination(destination);
			searchCriteria.setDepartureDate(departureDate);
			searchCriteria.setNumberOfPassengers(Integer.parseInt(numberOfPassengers));
			
			String maxPrice1 = request.getParameter("maxPrice");
			String minPrice1 = request.getParameter("minPrice");
			
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

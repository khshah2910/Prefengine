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
			String numberOfPassengers = request.getParameter("numberOfPassengers");
			//int actualStops = Integer.parseInt(stops);
			String price =request.getParameter("price");
			String cabin =request.getParameter("cabin");
			
			searchCriteria.setDeparture(departure);
			searchCriteria.setDestination(destination);
			searchCriteria.setDepartureDate(departureDate);
			searchCriteria.setNumberOfPassengers(Integer.parseInt(numberOfPassengers));
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
			ArrayList<Itinerary> tripRecord = service.search(searchCriteria);
			System.out.println("----->>>>"+tripRecord.get(0));
			request.setAttribute("tripRecord", tripRecord);
		
		}
		catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//response.sendRedirect("/Prefengine/web/search_result.jsp");
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

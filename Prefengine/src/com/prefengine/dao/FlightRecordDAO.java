package com.prefengine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.prefengine.domain.Itinerary;
import com.prefengine.service.SearchCriteria;
import com.prefengine.util.SQLConnection;

public class FlightRecordDAO {
	
	Connection connection = SQLConnection.getConnection();
	Statement stmt;
	ResultSet rs;
	
	public void truncateRecord() throws SQLException{
		
		String query = "truncate flightRecord";
		PreparedStatement pst = connection.prepareStatement(query);
		pst.execute();
		System.out.println("Records Deleted!!	");
	}
	
	public void saveFlightRecordsBatch(ArrayList<Itinerary> tripRec){
		System.out.println("--->>Insertion Begins----> ");
		try {
			truncateRecord();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(tripRec.size());
		for(int i=0;i<tripRec.size();i++)
		{
			String tripId = tripRec.get(i).getTripId();
			String origin = tripRec.get(i).getOrigin();
			String destination =tripRec.get(i).getDestination();
			String departureTime = tripRec.get(i).getDepartureTime();
			String arrivalTime = tripRec.get(i).getArrivalTime();
			int stops = tripRec.get(i).getNumberOfStops();
			float price = tripRec.get(i).getPrice();
			String carrier =  tripRec.get(i).getTripCarrier();
			float totalDuration=  tripRec.get(i).getTotalDuration();
			double miles = tripRec.get(i).getTotalMiles();
			String coach = tripRec.get(i).getCoach();
			String departureCityName = tripRec.get(i).getOriginCityName();
			String arrivalCityName = tripRec.get(i).getDestinationCityName();
			String carrierName = tripRec.get(i).getCarrierName();			
			String query = "insert into flightRecord (tripId, departure, destination, stops, departureTime, arrivalTime, price, carrier,duration, milage,cabin, thisTrip, jsonData,departureCityName,destinationCityName,carrierName) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			try {
				PreparedStatement pst = connection.prepareStatement(query);
				pst.setString(1,tripId);
				pst.setString(2, origin);
				pst.setString(3, destination);
				pst.setInt(4, stops);
				pst.setString(5,departureTime);
				pst.setString(6,arrivalTime);
				pst.setFloat(7, price);
				pst.setString(8, carrier);
				pst.setFloat(9,totalDuration );
				pst.setDouble(10, miles);
				pst.setString(11, coach);
				pst.setString(12, null);
				pst.setString(13, null);
				pst.setString(14, departureCityName);
				pst.setString(15, arrivalCityName);
				pst.setString(16, carrierName);
				pst.execute();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		System.out.println("--->>Insertion Ends----> ");


	}
	
	private ArrayList<Itinerary> processResultSet(ResultSet rs) throws SQLException{
		
			ArrayList<Itinerary> records = new ArrayList<>();
			
			while(rs.next()&& rs != null){
				Itinerary tr = new Itinerary();
				
				tr.setTripId(rs.getString("tripId"));
				tr.setOrigin(rs.getString("departure"));
				tr.setDestination(rs.getString("destination"));
				tr.setNumberOfStops(rs.getInt("stops"));
				tr.setDepartureTime(rs.getString("departureTime"));
				tr.setArrivalTime(rs.getString("arrivalTime"));
				tr.setPrice(rs.getFloat("price"));
				tr.setTripCarrier(rs.getString("carrier"));
				tr.setTotalDuration(rs.getFloat("duration"));
				tr.setTotalMiles(rs.getDouble("milage"));
				tr.setCoach(rs.getString("cabin"));
				tr.setOriginCityName(rs.getString("departureCityName"));
				tr.setDestinationCityName(rs.getString("destinationCityName"));
				tr.setCarrierName(rs.getString("carrierName"));
				
				tr.setFlightRecord(new ArrayList<>());
				
				records.add(tr);
			}
			return records;
	}
	
	public ArrayList<Itinerary> sortBy(String orderBy) throws SQLException{
		String sql="select * from flightRecord order by "+orderBy;
		stmt = connection.createStatement();
		rs = stmt.executeQuery(sql);
		return this.processResultSet(rs);
	}
	public HashMap<Integer,Float> getRecordsByStops(SearchCriteria sc) throws SQLException{
		String sql="select distinct stops,min(price) as price from flightRecord group by stops order by stops asc";
		HashMap<Integer,Float> result= new HashMap<>();
		stmt = connection.createStatement();
		rs = stmt.executeQuery(sql);
		while(rs.next()){
			result.put(rs.getInt(1), rs.getFloat(2));
		}
		return result;
	}
	
	public HashMap<String, Float> getRecordsByAirline(SearchCriteria sc) throws SQLException{
		String sql="select distinct carrierName ,min(price) as price from flightRecord group by carrierName order by price asc";
		HashMap<String, Float> result= new HashMap<>();
		stmt = connection.createStatement();
		rs = stmt.executeQuery(sql);
		while(rs.next()){
			result.put(rs.getString(1), rs.getFloat(2));
		}
		return result;
	}
	
	
	public HashMap<String, Float> getRecordsByCabin(SearchCriteria sc) throws SQLException{
		String sql = "select distinct cabin,min(price) as price from flightRecord group by cabin order by price asc";
		HashMap<String, Float> result1 = new HashMap<>();
		stmt = connection.createStatement();
		rs = stmt.executeQuery(sql);
		while(rs.next()){
			result1.put(rs.getString(1),rs.getFloat(2));
			
		}
		//result1.forEach((k,v)->System.out.println("Key: "+k+ " And Value :" +v));
		return result1;
	}
	
	
	public ArrayList<Itinerary> searchByAirline(String choice) throws SQLException{
		String sql="select * from flightRecord where carrier LIKE "+choice;
		stmt = connection.createStatement();
		rs = stmt.executeQuery(sql);
		return this.processResultSet(rs);
	}
	public ArrayList<Itinerary> searchByParameters(SearchCriteria sc) throws SQLException{
		String sql = "select * from flightRecord where departure LIKE ? AND destination LIKE ? ";
		String stopsCriteria = "";
		String cabinCriteria = "";
		String priceCriteria = "";
		// search by Stops
		if(sc.isNonStop()){
			stopsCriteria += "AND (stops = 0";
		}
		if(sc.isOneStop()){
			if("".equals(stopsCriteria)){
				stopsCriteria += "AND (stops = 1";
			}
			else{
				stopsCriteria += " OR stops = 1";
			}
		}
		if(sc.isTwoOrMoreStop()){
			if("".equals(stopsCriteria)){
				stopsCriteria += "AND (stops >= 2";
			}
			else{
				stopsCriteria += " OR stops >= 2";
			}
		}
		if(!"".equals(stopsCriteria)){
			stopsCriteria += ")";
			sql+= stopsCriteria;
			System.out.println(sql);
		}
		// search by cabin(Coach)
		if(sc.isEconomy()){
			cabinCriteria += " AND (cabin LIKE 'COACH'";
		}
		if(sc.isBusiness()){
			if("".equals(cabinCriteria)){
				cabinCriteria += "AND (cabin LIKE 'BUSINESS'";
			}
			else{
				cabinCriteria += " OR cabin LIKE 'BUSINESS'";
			}
		}
		if(sc.isFirst()){
			if("".equals(cabinCriteria)){
				cabinCriteria += "AND (cabin LIKE 'FIRST'";
			}
			else{
				cabinCriteria += " OR cabin LIKE 'FIRST'";
			}
		}
		if(!"".equals(cabinCriteria)){
			cabinCriteria += ")";
			sql+= cabinCriteria;
			System.out.println(sql);
		}
		//search by price
		System.out.println(sc.getMaxPrice());
		System.out.println(sc.getMinPrice());
		if(sc.getMaxPrice()!=0 && sc.getMinPrice()!=0){
			priceCriteria = " AND price between ? AND ?";
			sql+= priceCriteria;
			System.out.println(sql);
		}
		
		PreparedStatement pst = connection.prepareStatement(sql);
		pst.setString(1, sc.getDeparture());
		pst.setString(2, sc.getDestination());
		if(sc.getMaxPrice()!=0 && sc.getMinPrice()!=0){
			pst.setFloat(3, sc.getMinPrice());
			pst.setFloat(4, sc.getMaxPrice());
		}
		rs = pst.executeQuery();
		System.out.println(sql);
		return this.processResultSet(rs);
		
	}
	
}

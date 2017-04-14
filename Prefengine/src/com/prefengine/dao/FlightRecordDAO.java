package com.prefengine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
			
			String query = "insert into flightRecord (tripId, departure, destination, stops, departureTime, arrivalTime, price, carrier,duration, milage,cabin, thisTrip, jsonData) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
	public ArrayList<Itinerary> getRecordsBy(String choice) throws SQLException{
		String sql="select distinct "+choice+" from flightRecord;";
		stmt = connection.createStatement();
		rs = stmt.executeQuery(sql);
		return this.processResultSet(rs);
	}
	public ArrayList<Itinerary> searchByAirline(String choice) throws SQLException{
		String sql="select * from flightRecord where carrier LIKE "+choice;
		stmt = connection.createStatement();
		rs = stmt.executeQuery(sql);
		return this.processResultSet(rs);
	}
	public ArrayList<Itinerary> searchByParameters(SearchCriteria sc) throws SQLException{
		ArrayList<Itinerary> newtr = new ArrayList<>();
		String sql = "select * from flightRecord where departure LIKE ? AND destination LIKE ? ";
		String stopsCriteria = "";
		
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
		}
		PreparedStatement pst = connection.prepareStatement(sql);
		pst.setString(1, sc.getDeparture());
		pst.setString(2, sc.getDestination());
		rs = pst.executeQuery();
		return this.processResultSet(rs);
		
		//return newtr;
	}
	
}

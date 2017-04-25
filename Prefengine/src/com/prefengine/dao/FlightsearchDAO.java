package com.prefengine.dao;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.prefengine.domain.Cabin;
import com.prefengine.domain.Epunch;
import com.prefengine.domain.Flight;
import com.prefengine.domain.Trip;

public class FlightsearchDAO {
	//private DbDAO dbdao;
	
	public FlightsearchDAO(DbDAO db) {
		//dbdao = db;
	}
	
	// Returns the list of flights in certain order,
	// satisfying all the required conditions.
	public List<Flight> execute_fuzzy_logic(List<Flight> flight_records_list, ArrayList<String> non_functional_attributes)
							throws SQLException{
		// This method runs all the fuzzy logic process.
		// The life time of temp table is per connection's life time.
		
		List<Flight> flight_records = null;
		Connection conn = null;
		
		try{
			// Create db connection here and control it.
			conn = get_connection();
			
			if(conn != null){
				// Create temp table to store flight data.
				create_flights_temp_table(conn);
				
				// Upload the flight data to temp table so it can be used.
				load_flight_temp_table(flight_records_list, conn);
				
				// Create temp table to store flight data.
				create_attributes_temp_table(conn);
				
				// Load the attributes into temp table
				// with non-functional attributes, so it can be used.
				load_attributes_temp_table(non_functional_attributes, conn);
				
				// Calculate and set the satisfaction degree for each attribute of each flight.
				set_attribuites_satisfactions(non_functional_attributes, conn);
				
				// Calculate the satisfaction degree for each flight.
				double satisfaction_degree = set_flights_satisfactions(conn);
				
				// Get all flight records for returning.
				flight_records = get_flight_records(satisfaction_degree, conn);
			}
		}finally{
			// Close the db connection here to guarantee that it will be closed.
			close_connection(conn);
		}
		
		// Return the list of all records for display.
		return flight_records;
	}
	
	public List<Flight> get_flight_records(double satisfactory, Connection conn)
											throws SQLException {
		// This method get all the records in the flight temp table.
		
		List<Flight> flight_records = new LinkedList<>();
		Flight flight_record = new Flight();
		
		// Use this connection to access the database ...
		
		if(conn != null){
			
			// Open connection to sql statement: cStmt.
			CallableStatement cStmt = conn.prepareCall("{call get_satisfactory_flights(?)}");
			
			// Execute and get the result: List of flights data.
			ResultSet rs = cStmt.executeQuery();
			
			// Extract the flights data and store them in a list.
			while (rs.next()) {
				
				// Get the data of each flight.
				int flight_id = rs.getInt(1);
				String departure_time = rs.getString(2);
				String arrive_time  = rs.getString(3);
				float flight_price  = rs.getFloat(4);
				String flight_carrier  = rs.getString(5);
				String flight_duration  = rs.getString(6);
				float flight_mileage = rs.getFloat(7);
				String flight_cabin  = rs.getString(8);
				String flight_ontimeperformance = rs.getString(9);
				
				// Create flight record
				flight_record.setFlightid(flight_id);
				flight_record.setFlightdeparturetime(departure_time);
				flight_record.setFlightarrivetime(arrive_time);
				flight_record.setFlightprice(flight_price);
				flight_record.setFlightcarrier(flight_carrier);
				flight_record.setFlightduration(flight_duration);
				flight_record.setFlightmileage(flight_mileage);
				flight_record.setFlightcabin(flight_cabin);
				flight_record.setFlighttimeperformance(flight_ontimeperformance);
				
				// Add the record to the list.
				flight_records.add(flight_record);
			}
			
			// Clean-up environment
			cStmt.close();
		}
		
		// Return the flight records.
		return flight_records;
	}
	
	// Calculates an set satisfaction degree of each flight.
	public double set_flights_satisfactions(Connection conn)
									throws SQLException{
		double satisfactory = 0;
		
		try{
			// The life time of temp table is per connection's life time.
			if(conn != null){
				// Open connection to sql statement: cStmt.
				CallableStatement cStmt = conn.prepareCall("{? = call set_flights_satisfaction_degree()}");
				
				// Register the output so it can be caught in the return.
				cStmt.registerOutParameter(1, Types.DOUBLE);  
				
				// Execute the query.
				ResultSet rs = cStmt.executeQuery();
				
				// Get the returning result.
				satisfactory = rs.getDouble(1);
			}
		}finally{
			// Close the db connection here to guarantee that it will be closed.
			close_connection(conn);
		}
		
		// Return the list of all records for display.
		return satisfactory;
	}
	
	// Returns the list of flights in certain order,
	// satisfying all the required conditions described by non functional attributes.
	public double set_attribuites_satisfactions(ArrayList<String> non_functional_attributes, Connection conn)
			throws SQLException{
		// This method runs all the fuzzy logic process.
		// The life time of temp table is per connection's life time.
		double satisfactory = 0;
		
		try{
			if(conn != null){
				// Create temp table to store flight data.
				create_attributes_temp_table(conn);
				
				// Upload the flight data to temp table
				// with non-functional attributes, so it can be used.
				load_attributes_temp_table(non_functional_attributes, conn);
				
				// Do whatever is needed to be done.
				
				// Get all flight records for returning.
				//flight_temp_records = get_flight_temp_records(conn);
				
				// Open connection to sql statement: cStmt.
				//CallableStatement cStmt = conn.prepareCall("{ call get_non_functional_flights(?)}");
				CallableStatement cStmt = conn.prepareCall("{? = call set_attributes_satisfaction_degree(?,?,?,?,?,?,?)}");
				
				
				
				
				// Register the output so it can be caught in the return.
				cStmt.registerOutParameter(1, Types.DOUBLE);  
								
				ResultSet rs = cStmt.executeQuery();
				
				satisfactory = rs.getDouble(1);
			}
		}finally{
			// Close the db connection here to guarantee that it will be closed.
			close_connection(conn);
		}
		
		// Return the list of all records for display.
		return satisfactory;
	}
	
	public void load_attributes_temp_table(ArrayList<String> attributes_list, Connection conn)
									throws SQLException {
		// This method load temp with list of attributes.
		
		//... use this connection to access the database ...
		if(conn != null){
			
			for(String attribute: attributes_list){
				
				// Load one flight data for each record in the list.
				insert_attribute(attribute, conn);
			}
			//close_connection(conn);
			// To determine when to close connection to keep temp table.
		}
	}
	
	public void insert_attribute(String attribute, Connection conn)
								throws SQLException {
		// This method load one flight data into temp table.
		
		if(conn != null){
			
			// Open connection to sql statement: cStmt.
			CallableStatement cStmt = conn.prepareCall("{? = call insert_attribute_into_temp_table(?)}");
			
			// Register the output so it can be caught in the return.
			cStmt.registerOutParameter(1, Types.INTEGER);  
			
			// Set values to the sql function.
			cStmt.setString(2, attribute);
			
			// Execute the sql statement.
			cStmt.execute();
			
			// Get the return value from the execution result.
			int result = cStmt.getInt (1);
			
			if(result == 1){
		        // Do something if needed in case of satisfied.
				
			}
			else{
				// Do something if needed in case of not satisfied.
			}
			
		    // Clean-up environment
			cStmt.close();
		}
	}
	
	// Create temp table to store the flight search result.
	public void create_attributes_temp_table(Connection conn)
									throws SQLException{
		// This method creates flight temp table.
		
		// Proceed if connected to server
		if(conn != null){
			
			// Open connection to sql statement: cStmt.
			CallableStatement cStmt = conn.prepareCall("{? = call create_attributes_temp_table ()}");
			
			// Register the output so it can be caught in the return.
			cStmt.registerOutParameter(1, Types.INTEGER);  
			
			// Execute the sql statement.
			cStmt.execute();
			
			// Get the return value from the execution result.
			int result = cStmt.getInt(1);
			
			if(result == 1){
				// The result is always 1;
			}
			else{
				// Never accessed for now;
			}
			
			// Clean-up environment
			cStmt.close();
		}
	}
	
	// Create temp table to store the flight search result.
	public void create_flights_temp_table(Connection conn)
									throws SQLException{
		// This method creates flight temp table.
		
		// Proceed if connected to server
		if(conn != null){
			
			// Open connection to sql statement: cStmt.
			CallableStatement cStmt = conn.prepareCall("{? = call create_flight_record_temp_table ()}");
			
			// Register the output so it can be caught in the return.
			cStmt.registerOutParameter(1, Types.INTEGER);  
			
			// Execute the sql statement.
			cStmt.execute();
			
			// Get the return value from the execution result.
			int result = cStmt.getInt(1);
			
			if(result == 1){
				// The result is always 1;
			}
			else{
				// Never accessed for now;
			}
			
			// Clean-up environment
			cStmt.close();
		}
	}
	
	public void load_flight_temp_table(List<Flight> flight_records, Connection conn)
											throws SQLException {
		// This method load temp temp with flight records.
		
		//... use this connection to access the database ...
		if(conn != null){
			
			for(Flight flight: flight_records){
				
				// Load one flight data for each record in the list.
				insert_flight_record(flight, conn);
			}
			//close_connection(conn);
			// To determine when to close connection to keep temp table.
		}
	}
	
	public void insert_flight_record(Flight flight, Connection conn)
										throws SQLException {
		// This method load one flight data into temp table.
		
		if(conn != null){
			
			// Open connection to sql statement: cStmt.
			CallableStatement cStmt = conn.prepareCall("{? = call insert_record_in_flight_temp_table(?, ?, ?, ?, ?, ?, ?, ?, ?)}");
			
			// Register the output so it can be caught in the return.
			cStmt.registerOutParameter(1, Types.INTEGER);  
			
			// Set values to the sql function.
			cStmt.setInt(2, flight.getFlightid());
			cStmt.setString(3, flight.getFlightdeparturetime());
			cStmt.setString(4, flight.getFlightarrivetime());
			cStmt.setFloat(5, flight.getFlightprice());
			cStmt.setString(6, flight.getFlightcarrier());
			cStmt.setString(7, flight.getFlightduration());
			cStmt.setFloat(8, flight.getFlightmileage());
			cStmt.setString(9, flight.getFlightcabin());
			cStmt.setString(10, flight.getFlighttimeperformance());
			
			// Execute the sql statement.
			cStmt.execute();
			
			// Get the return value from the execution result.
			String result = cStmt.getString (1);
			
			if(result.equals("Active")){
				// Do something if needed in case of satisfied.
			}
			else{
				// Do something if needed in case of not satisfied.
			}
			
			// Clean-up environment
			cStmt.close();
		}
	}
	
	public Connection get_connection() throws SQLException{
		// This method get a connection to the database.
		//==============================================================
		Context initCtx = null;
		try {
			initCtx = new InitialContext();
		}catch(NamingException e){
			
		}
		
		Context envCtx = null;
		try {
			envCtx = (Context) initCtx.lookup("java:comp/env");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			//session.setAttribute("error",e.printStackTrace());
		}
		
		// Look up our data source
		DataSource ds = null;
		try {
			ds = (DataSource)envCtx.lookup("jdbc/mysql");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			//session.setAttribute("error",e.printStackTrace());
		}
		
		// Allocate and use a connection from the pool
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		return conn;
	}
	
	public void close_connection(Connection conn) throws SQLException{
		// This method closes the connection from the database, conn.
		//=============================================================
		
		if(conn != null){
			conn.close();
		}
	}
}

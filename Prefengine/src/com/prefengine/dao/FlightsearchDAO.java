package carmo.reno.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import carmo.reno.domain.Itinerary;
import carmo.reno.domain.SearchCriteria;

// This class does not close sql connection.
// It is the resposability of program that calls this class to close any connection to DB.
public class FlightsearchDAO {
	
	// Returns the list of flights in certain order,
	// satisfying all the required conditions.
	public ArrayList<Itinerary> execute_fuzzy_logic(ArrayList<Itinerary> flight_records_list, ArrayList<String> non_functional_attributes, SearchCriteria searchCriteria, Connection conn)
							throws SQLException{
		// This method runs all the fuzzy logic process.
		// The life time of temp table is per connection's life time.
		
		ArrayList<Itinerary> flight_records = null;
		
		// Confirm if the connection is active.
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
			set_attribuites_satisfaction(searchCriteria, conn);
			
			// Calculate the satisfaction degree for each flight.
			double satisfaction_degree = set_flights_satisfactions(conn);
			
			// Get all flight records for returning.
			flight_records = get_flight_records(satisfaction_degree, conn);
		}
		else{
			
		}
		
		// Return the list of all records for display.
		return flight_records;
	}
	
	public ArrayList<Itinerary> get_flight_records(double satisfactory, Connection conn)
											throws SQLException {
		// This method get all the records in the flight temp table.
		
		ArrayList<Itinerary> flight_records = new ArrayList<>();
		
		// Use this connection to access the database ...
		
		if(conn != null){
			
			// Open connection to sql statement: cStmt.
			CallableStatement cStmt = conn.prepareCall("{call get_satisfactory_flights(?)}");
			
			// Execute and get the result: List of flights data.
			ResultSet rs = cStmt.executeQuery();
			
			// Extract the flights data and store them in a list.
			while (rs.next()) {
				Itinerary flight_record = new Itinerary();
				
				// Get the data of each flight.
				flight_record.setTripId(rs.getString("f_tripId"));
				flight_record.setOrigin(rs.getString("f_departure"));
				flight_record.setDestination(rs.getString("f_destination"));
				flight_record.setNumberOfStops(rs.getInt("f_stops"));
				flight_record.setDepartureTime(rs.getString("f_departureTime"));
				flight_record.setArrivalTime(rs.getString("f_arrivalTime"));
				flight_record.setPrice(rs.getFloat("f_price"));
				flight_record.setTripCarrier(rs.getString("f_carrier"));
				flight_record.setTotalDuration(rs.getFloat("f_duration"));
				flight_record.setTotalMiles(rs.getDouble("f_mileage"));
				flight_record.setCoach(rs.getString("f_cabin"));
				flight_record.setOriginCityName(rs.getString("f_departureCityName"));
				flight_record.setDestinationCityName(rs.getString("f_destinationCityName"));
				flight_record.setCarrierName(rs.getString("f_carrierName"));
				
				flight_record.setFlightRecord(new ArrayList<>());
								
				// Add the record to the list.
				flight_records.add(flight_record);
			}
			
			// Clean-up environment closing statement.
			cStmt.close();
		}
		
		// Return the flight records.
		return flight_records;
	}
	
	// Calculates satisfaction degree of each flight.
	// Return the maximum satisfaction degree of the flights.
	public double set_flights_satisfactions(Connection conn)
									throws SQLException{
		double satisfactory = 0;
		
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
		
		// Return the list of all records for display.
		return satisfactory;
	}
	
	// Nothing to return.
	public void set_attribuites_satisfaction(SearchCriteria searchCriteria, Connection conn)
			throws SQLException{
		// This method runs all the fuzzy logic process.
		CallableStatement cStmt = null;
		try{
			if(conn != null){
				// Open connection to sql statement: cStmt.
				//CallableStatement cStmt = conn.prepareCall("{ call get_non_functional_flights(?)}");
				cStmt = conn.prepareCall("{? = call set_attributes_satisfaction_degree(?,?,?,?,?,?,?)}");
				
				// Register the output so it can be caught in the return.
				cStmt.registerOutParameter(1, Types.DOUBLE);  
				
				cStmt.setDouble(2, searchCriteria.getMinPrice());
				cStmt.setDouble(3, searchCriteria.getMaxPrice());
				
				
				
				ResultSet rs = cStmt.executeQuery();
				
				double sat = rs.getDouble(1);
				if(sat==1){
					// Do something.
				}
				else{
					// Do something else.
				}
			}
		}finally{
			// Close the statement connection
			cStmt.close();
		}
	}
	
	// Nothing to return.
	public void load_attributes_temp_table(ArrayList<String> attributes_list, Connection conn)
									throws SQLException {
		// This method load temp with list of attributes.
		
		// Use this connection to access the database ...
		if(conn != null){
			
			for(String attribute: attributes_list){
				
				// Load one flight data for each record in the list.
				insert_attribute(attribute, conn);
			}
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
			
		    // Clean-up environment closing the statement.
			cStmt.close();
		}
	}
	
	// Create temp table to store the attributes.
	public void create_attributes_temp_table(Connection conn)
									throws SQLException{
		// This method creates attributes temp table.
		
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
			
			// Clean-up environment closing the statement.
			cStmt.close();
		}
	}
	
	// Create temp table to store the flight search result.
	// Nothing to return.
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
			
			// Clean-up environment closing the statement.
			cStmt.close();
		}
	}
	
	// Nothing to return.
	public void load_flight_temp_table(ArrayList<Itinerary> flight_records, Connection conn)
											throws SQLException {
		// This method load temp temp with flight records.
		
		// Use this connection to access the database ...
		if(conn != null){
			// Loop through list of flights.
			for(Itinerary flight: flight_records){
				
				// Insert one flight data for each record in the list.
				insert_flight_record(flight, conn);
			}
		}
	}
	
	// Nothing to return.
	public void insert_flight_record(Itinerary flight, Connection conn)
										throws SQLException {
		// This method load one flight data into temp table.
		
		if(conn != null){
			
			// Open connection to sql statement: cStmt.
			CallableStatement cStmt = conn.prepareCall("{? = call insert_record_in_flight_temp_table(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
			
			// Register the output so it can be caught in the return.
			cStmt.registerOutParameter(1, Types.INTEGER);  
			
			// Set values to the sql function.
			String tripId = flight.getTripId();
			String origin = flight.getOrigin();
			String destination =flight.getDestination();
			String departureTime = flight.getDepartureTime();
			String arrivalTime = flight.getArrivalTime();
			int stops = flight.getNumberOfStops();
			float price = flight.getPrice();
			String carrier = flight.getTripCarrier();
			float totalDuration= flight.getTotalDuration();
			double miles = flight.getTotalMiles();
			String coach = flight.getCoach();
			String departureCityName = flight.getOriginCityName();
			String arrivalCityName = flight.getDestinationCityName();
			String carrierName = flight.getCarrierName();
			
			cStmt.setString(2,tripId);
			cStmt.setString(3, origin);
			cStmt.setString(4, destination);
			cStmt.setInt(5, stops);
			cStmt.setString(6,departureTime);
			cStmt.setString(7,arrivalTime);
			cStmt.setFloat(8, price);
			cStmt.setString(9, carrier);
			cStmt.setFloat(10,totalDuration );
			cStmt.setDouble(11, miles);
			cStmt.setString(12, coach);
			cStmt.setString(13, null);
			cStmt.setString(14, null);
			cStmt.setString(15, departureCityName);
			cStmt.setString(16, arrivalCityName);
			cStmt.setString(17, carrierName);
			
			// Execute the sql statement.
			cStmt.execute();
			
			// Get the id (primary key of the inserted row).
			int result = cStmt.getInt (1);
			
			if(result == 0){
				// Do something if needed in case of satisfied.
			}
			else{
				// Do something if needed in case of not satisfied.
			}
			
			// Clean-up environment closing the statement.
			cStmt.close();
		}
	}
}

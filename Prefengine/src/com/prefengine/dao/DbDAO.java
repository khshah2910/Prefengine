package com.prefengine.dao;


import com.prefengine.config.PrefEngineConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.prefengine.dao.DbConstants.*;

/**
 * Initialization and connection of database
 * This is a singleton class. only one instance can be called
 * 
 * @author Chung-Hsien (Jacky) Yu
 * @author Yinka Martins (modified)
 * 
 */
public class DbDAO {
	   
	private Connection connection;
	//used for insert command when type timestamp
	private static String timestampstr; 	

	
	/**
	 *  Parameters to use JDBC drivers to connect to the database
	 *  @param dbUrl string to connect to database
	 *  @param user  username 
	 *  @param pwd password
	 *  @throws  SQLException
	 */
	public DbDAO(String dbUrl, String user, String pwd) throws SQLException {
			String driver = null;
			if (dbUrl == null){
				dbUrl = HSQLDB_URL;  // default to HSQLDB
				timestampstr = "";
			}
			if (dbUrl.contains("oracle")) {
				driver = ORACLE_DRIVER;
				timestampstr = "timestamp";
			} else if (dbUrl.contains("mysql")) {
				driver = MYSQL_DRIVER;
				timestampstr = "timestamp";
			} else if (dbUrl.contains("hsqldb")) {
				driver = HSQLDB_DRIVER;
				user = "sa";
				pwd = "Temi1234.";
			} else throw new SQLException("Unknown DB URL pattern in DbDAO constructor");
			System.out.println("Connecting using driver "+ driver+ ", DB URL " + dbUrl);
			
			// TODO change the connection to an inprocess connection
			
			//Server connection to HSQLDB ( Change to in process connection
			try { 
				Class.forName(driver);
			} catch (Exception e) {
				throw new SQLException("Problem with loading driver: " + e);
			}
			connection = DriverManager.getConnection(dbUrl, user, pwd);
	   }
	/**
	 *  Return the built connection
	 *  @return  Connection object established by DbDAO
	 */
	public Connection getConnection() {
		return connection;
	}
	
	/**
	 *  Terminate the built connection
	 *  @throws  SQLException
	 */
	public void close() throws SQLException {
		connection.close();  // this object opened it, so it gets to close it
	}
	
	
	/**
	*  bring DB back to original state
	*  @throws  SQLException
	**/
	public void initializeDb() throws SQLException {
		Statement stmt = connection.createStatement();
		try {
			
			dropTable(TRIP_TABLE);
			dropTable(ADDRESSES_TABLE);
			dropTable(USER_TABLE);
			dropTable(ADMIN_TABLE);
			//dropTable(USERLOGIN_TABLE);
			dropTable(ADMINLOGIN_TABLE);
			dropTable(ADMINROLE_TABLE);
			dropTable(TRIPHISTORY_TABLE);
			
			//TODO admin logs and user logs if needed
			/*dropTable(USERLOGS_TABLE);
			dropTable(ADMINLOGS_TABLE);
			dropTable(HISTORY_TABLE);*/
			
			
			/**stmt.execute("CREATE TABLE IF NOT EXISTS site_user (user_id INT NOT NULL AUTO_INCREMENT,"
					+ "first_name VARCHAR(50) NOT NULL, last_name VARCHAR(50) NOT NULL,"
					+ "email_address VARCHAR(100) NOT NULL, subscription_plan VARCHAR(50) NOT NULL,"
					+ "phone_number VARCHAR(50) NOT NULL, active_user TINYINT(1) DEFAULT 1 NOT NULL,"
					+ "is_Verified TINYINT(1) DEFAULT 0 NOT NULL, registration_date TIMESTAMP NOT NULL,"
					+ "UNIQUE(email_address), PRIMARY KEY (user_id))");
			
			stmt.execute("CREATE TABLE IF NOT EXISTS address(address_id INT NOT NULL AUTO_INCREMENT,"
					+ "address_name VARCHAR(100) NOT NULL, street_number INT NOT NULL," 
					+ "street_name VARCHAR(50) NOT NULL, city_name VARCHAR(50) NOT NULL,"
					+ "state_name VARCHAR(20) NOT NULL, zip_code VARCHAR(10) NOT NULL,"
					+ "latitude DECIMAL(9,6) NOT NULL, longitude DECIMAL(9,6) NOT NULL,"
					+ "UNIQUE (address_name), PRIMARY KEY (address_id))");
			
			stmt.execute("CREATE TABLE IF NOT EXISTS trip (trip_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
					+ "origin_id INT NOT NULL, destination_id INT NOT NULL, user_id INT NOT NULL,"
					+ "time_created TIMESTAMP NOT NULL, departure_time TIMESTAMP NOT NULL,"
					+ "arrival_time TIMESTAMP NOT NULL, mode_of_transport VARCHAR(20) NOT NULL,"
					+ "travel_time INT NOT NULL, traffic_model VARCHAR(20) NOT NULL, "
					+ "completed TINYINT(1) NOT NULL, FOREIGN KEY (origin_id) REFERENCES address (address_id),"
					+ "FOREIGN KEY (destination_id) REFERENCES address (address_id),"
					+ "FOREIGN KEY (user_id) REFERENCES site_user (user_id))");
			
			stmt.execute("CREATE TABLE IF NOT EXISTS reminder (reminder_id INT NOT NULL AUTO_INCREMENT,"
					+ "trip_id INT NOT NULL, user_id INT NOT NULL, created_date TIMESTAMP NOT NULL,"
					+ "is_active TINYINT(1) NOT NULL, PRIMARY KEY (reminder_id), "
					+ "FOREIGN KEY (user_id) REFERENCES site_user (user_id),"
					+ "FOREIGN KEY (trip_id) REFERENCES trip (trip_id))");
			
			stmt.execute("CREATE TABLE IF NOT EXISTS notification(notification_id INT NOT NULL,"
					+ "user_id INT NOT NULL, subject  VARCHAR(100) NOT NULL, message VARCHAR(1000) NOT NULL,"
					+ "delivery_time TIMESTAMP NOT NULL, is_notified TINYINT(1) NOT NULL,"
					+ "PRIMARY KEY (notification_id), FOREIGN KEY (user_id) REFERENCES site_user (user_id))");
			
			stmt.execute("CREATE TABLE IF NOT EXISTS administrator(admin_id INT NOT NULL AUTO_INCREMENT,"
					+ "first_name VARCHAR(50) NOT NULL, last_name VARCHAR(50) NOT NULL,"
					+ "email_address VARCHAR(100) NOT NULL, phone_number VARCHAR(50) NOT NULL,"
					+ "active_admin TINYINT(1) DEFAULT 1 NOT NULL, is_Verified TINYINT(1) DEFAULT 0 NOT NULL,"
					+ "creation_date TIMESTAMP NOT NULL, UNIQUE(email_address), PRIMARY KEY (admin_id))");
			
			stmt.execute("CREATE TABLE IF NOT EXISTS userLogin (user_email VARCHAR(100) NOT NULL PRIMARY KEY,"
					+ "hashed_password VARCHAR(100) NOT NULL, salt_key VARCHAR(100) NOT NULL)");
			
			stmt.execute("CREATE TABLE IF NOT EXISTS adminLogin (admin_email VARCHAR(100) NOT NULL PRIMARY KEY,"
					+ "hashed_password VARCHAR(100) NOT NULL, salt_key VARCHAR(100) NOT NULL)");
			
			stmt.execute("CREATE TABLE IF NOT EXISTS adminLogin (admin_email VARCHAR(100) NOT NULL PRIMARY KEY,"
					+ "hashed_password VARCHAR(100) NOT NULL,salt_key VARCHAR(100) NOT NULL)");**/
		
		}finally {
			System.out.println("Database Created");
			stmt.close();
		}	
	}
	
	/**
	 * Drops all table for re-initialization
	 * Get rid of such a command when system goes live
	 * @throws SQLException
	 */
	public void dropAllTables() throws SQLException{
		dropTable(ADDRESSES_TABLE);
		dropTable(ADMIN_TABLE);
		dropTable(ADMINLOGS_TABLE);
		dropTable(TRIPHISTORY_TABLE);
		//dropTable(USERLOGIN_TABLE);
		dropTable(ADMINLOGIN_TABLE);
		dropTable(TRIP_TABLE);
		dropTable(USER_TABLE);
		dropTable(USERLOGS_TABLE);
	}
	
	/**
	 * Drop a table using it's given name
	 * @param tableName
	 * @throws SQLException
	 */
	private void dropTable(String tableName) throws SQLException {
		Statement stmt = connection.createStatement();
		try {
			Boolean dropped = stmt.execute("DROP TABLE IF EXISTS " + tableName );
			if(dropped)
				System.out.println(tableName + " dropped");
		} finally {
			stmt.close();
		}
	}
	
	/**
	 * Starts the database
	 * @throws Exception 
	 */
	public static void checkDb(String dbUrl, String usr, String passwd) throws Exception{
		try {
			PrefEngineConfig.configureServices(dbUrl, usr, passwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		PrefEngineConfig.shutdownServices();
	}

	/**
	*  Delete all records from the given table
	*  Get rid of such a command whe code goes live
	*  @param tableName table name from which to delete records
	*  @throws  SQLException
	*/
	private void clearTable(String tableName) throws SQLException {
		Statement stmt = connection.createStatement();
		try {
			stmt.execute("delete from " + tableName);
		} finally {
			stmt.close();
		}
	}
	/**
	 * format a date type date into appropriate string base on database 
	 * that current connection connects to.  Using package protection
	 * to indicate this is for DAO use--it's specific to DB needs.
	 * @param date
	 * @return time stamp as string
	 * */
	 
    private static String formatTimestamp(Date date) {
    	String outstr="";
    	
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    	outstr = timestampstr + " '" + formatter.format(date)+"'";
    	
    	return outstr;
    }

}


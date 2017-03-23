package com.prefengine.dao;

public class DbConstants {
	public static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
	public static final String ORACLE_DRIVER = "oracle.jdbc.OracleDriver";
	public static final String HSQLDB_DRIVER = "org.hsqldb.jdbcDriver";
	public static final String HSQLDB_URL = "jdbc:hsqldb:hsql://localhost/";

	public static final String ADMIN_TABLE = "administrator";
	public static final String USER_TABLE = "user";
	
	//public static final String USERLOGIN_TABLE = "userLogin";
	public static final String ADMINLOGIN_TABLE = "adminLogin";
	public static final String ADMINROLE_TABLE = "adminRole";
	
	public static final String ADDRESSES_TABLE = "address";
	public static final String TRIP_TABLE = "trip";
	public static final String TRIPHISTORY_TABLE = "trip_history";
	
	public static final String USERLOGS_TABLE = "userlog";
	public static final String ADMINLOGS_TABLE = "adminlog";
}

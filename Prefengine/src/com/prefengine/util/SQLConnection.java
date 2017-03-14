package com.prefengine.util;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class SQLConnection {

	public static Connection connection = null;
	//private static final String DBNAME = "jdbc:mysql://localhost:3306/prefengine";
	private static final String DB_USERNAME = "root";
	private static final String DB_PASSWORD = "root";

	public static Connection getConnection(){
		if(connection!= null){
			return connection;
		}
		else{
			try{
				
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/prefengine",DB_USERNAME,DB_PASSWORD);
				//connection.close();
			}
			catch(Exception e){
				e.printStackTrace();
				
			}
			return connection;
		}

	}
}

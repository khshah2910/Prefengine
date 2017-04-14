package com.prefengine.util;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class SQLConnection {

	public static Connection connection = null;
	public static Connection getConnection(){
		
		if(connection!= null){
			return connection;
		}
		else{
			try{
				Class.forName(Constants.DB_URL);
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/prefengine",Constants.DB_USERNAME,Constants.DB_PASSWORD);
				//connection.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
			return connection;
		}
	}
}

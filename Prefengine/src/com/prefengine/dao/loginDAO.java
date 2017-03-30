package com.prefengine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.prefengine.util.SQLConnection;

public class loginDAO {
	private static Connection connection;
	public static boolean validate(String name, String pass) {   
		connection = SQLConnection.getConnection();
        boolean status = false;
       
        PreparedStatement pst = null;
        ResultSet rs = null;

        try{
            pst = connection.prepareStatement("select * from user where username=? and password=?");
            pst.setString(1, name);
            pst.setString(2, pass);
            rs = pst.executeQuery();
            status = rs.next();
        }
        catch(Exception e){
        	e.printStackTrace();
        }
       
        return status;
    }
}



package com.prefengine.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.prefengine.domain.User;
import com.prefengine.util.SQLConnection;


public class RegistrationDAO{

    private Connection connection;

    public RegistrationDAO() {
        connection = SQLConnection.getConnection();
    }

    public void addUser(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into user(username,email,password,security_question,security_question_answer) values ( ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getSecQue());
            preparedStatement.setString(5, user.getSecAns());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

package com.prefengine.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.prefengine.dao.RegistrationDAO;
import com.prefengine.domain.User;
import com.prefengine.dao.RegistrationDAO;

public class UserController extends HttpServlet {
   // private static String INSERT = "/web/Register.jsp";
    private RegistrationDAO dao;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	dao = new RegistrationDAO();
    	User user = new User();
        //user.setId(request.getParameter("id"));
        user.setUserName(request.getParameter("userName"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        user.setSecQue(request.getParameter("s_que"));
        user.setSecAns(request.getParameter("s_ans"));
        dao.addUser(user);
        RequestDispatcher rd= request.getRequestDispatcher("/web/login.jsp");
        rd.forward(request, response);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd= request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }

}
package com.prefengine.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.prefengine.dao.loginDAO;
import com.prefengine.domain.User;


@WebServlet("/Welcome")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//private loginDAO ldao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		User u = new User();
//		PrintWriter out = response.getWriter();
//		if(request.getParameter("username").equals(null) || request.getParameter("password").equals(null)){
//			out.write("Please enter username and password");
//		}
//		
//		u.setUserName(request.getParameter("username"));
//		u.setPassword(request.getParameter("password"));
//		if(request.getParameter("username").equals(null) || request.getParameter("password").equals(null)){
//			out.write("Please enter username and password");
//			RequestDispatcher rd= request.getRequestDispatcher("/web/login_register.jsp");
//			rd.forward(request, response);
//		}
//		u=loginDAO.login(u);
//		
//		RequestDispatcher rd= request.getRequestDispatcher("/web/index.html");
//        rd.forward(request, response);
		
		response.setContentType("text/html");  
        PrintWriter out = response.getWriter();  
        
        String n=request.getParameter("username");  
        String p=request.getParameter("password"); 
        
        HttpSession session = request.getSession(false);
        if(session!=null)
        session.setAttribute("name", n);
        if(loginDAO.validate(n, p)){  
        	//request.setAttribute("username",n);
            RequestDispatcher rd=request.getRequestDispatcher("web/welcome.jsp");  
            rd.forward(request,response);  
        }  
        else{  
          //  out.print("<p style=\"color:red\">Please enter valid username and password</p>");  
            request.setAttribute("error", "Please enter valid username and password");
            RequestDispatcher rd=request.getRequestDispatcher("web/login_register.jsp");  
            rd.include(request,response);  
        }  

        out.close(); 
	}

}

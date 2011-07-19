package com.ejda.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tcd.ejda.dao.UserDAO;
import com.tcd.ejda.dao.UserDAOImpl;

/**
 * Servlet implementation class CheckUsernamePasswordServlet
 */
public class CheckUsernamePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("[ Start : CheckUsernamePasswordServlet ]");
		String username = request.getParameter("user");
		String pwd = request.getParameter("pwd");
		System.out.println(" get Parameter : " + username + ":" + pwd);
		
		UserDAO usr = new UserDAOImpl();
		String result = usr.checkUsernamePassword("", username, pwd);
		System.out.println("result >>> " + result);
		
		if (result.equals("N")){
			response.sendRedirect("user_screen.jsp");
		}else{
			System.out.println("else >> " + result);
			request.setAttribute("messages",result);
			response.sendRedirect("index.jsp");
		}
		
	}

}

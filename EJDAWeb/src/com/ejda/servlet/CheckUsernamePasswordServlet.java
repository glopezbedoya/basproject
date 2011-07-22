package com.ejda.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ejda.constant.EJDAConstant;
import com.tcd.ejda.dao.UserDAO;
import com.tcd.ejda.dao.UserDAOImpl;


/**
 * Servlet implementation class CheckUsernamePasswordServlet
 */
public class CheckUsernamePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(CheckUsernamePasswordServlet.class);
   
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
		log.debug("[ Start : CheckUsernamePasswordServlet ]");
		String username = request.getParameter("user");
		String pwd = request.getParameter("pwd");
		log.debug(" get Parameter : " + username + ":" + pwd);
		
		UserDAO usr = new UserDAOImpl();
		String result = usr.checkUsernamePassword("", username, pwd);
		log.debug("result >>> " + result);
		result = "N";
		if (result.equals("N")){
//			request.getSession().setAttribute(EJDAConstant.SESSION_NAME.PAGE, "user_screen.jsp");
			request.getSession().setAttribute(EJDAConstant.SESSION_NAME.PAGE, "role_menu.jsp");
			response.sendRedirect(EJDAConstant.PAGE.INDEX_PAGE);
		}else{
			log.debug("else >> " + result);
			request.setAttribute("messages",result);
			response.sendRedirect("index.jsp");
		}
		
	}

}

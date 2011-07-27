package com.ejda.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import com.tcd.ejda.dao.UserDAO;
import com.tcd.ejda.dao.UserDAOImpl;

/**
 * Servlet implementation class AjaxUserServlet
 */
public class AjaxUserServlet extends HttpServlet {
	
	private Logger log = Logger.getLogger(AjaxUserServlet.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String returnValue ="";
		log.debug("[Start : AjaxUserServlet]");
		String user_name = request.getParameter("user_name");
		String ejda_id = request.getParameter("ejda_id");
		log.debug("user_name >>> " +user_name);
		log.debug("ejda_id >>> " +ejda_id);
		UserDAO dao = new UserDAOImpl();
		try {
			if (dao.checkUsernameDup(user_name,ejda_id)){
				returnValue="Y";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.debug("AjaxUserServlet",e);
		}
		writeResult(request, response, returnValue);
		log.debug("returnValue >> " + returnValue);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	protected void writeResult(HttpServletRequest request,HttpServletResponse response, String result)throws ServletException, IOException {
		response.setContentType("text/xml;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter pw = response.getWriter();
		log.debug("[ writeResult ]   : result = " + result);
		pw.write(result);
		pw.close();
	}
}

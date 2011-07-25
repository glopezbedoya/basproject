package com.ejda.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.tcd.ejda.dao.UserDAO;
import com.tcd.ejda.dao.UserDAOImpl;

public class CheckUsernamePasswordAction extends AbstractAction {
	
	private Logger log = Logger.getLogger(CheckUsernamePasswordAction.class);
	
	@Override
	public void clearSessionNotUsed() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean methodAction(String ejdaMethod) {
		// TODO Auto-generated method stub
		if(ejdaMethod.equalsIgnoreCase("checkUser")){
			return checkUser();
		}
		
		
		return false;
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean checkUser(){
		
		boolean result = false;
		// TODO Auto-generated method stub
		log.debug("[ Start : CheckUsernamePasswordServlet ]");
		String username = getRequest().getParameter("user");
		String pwd = getRequest().getParameter("pwd");
		log.debug(" get Parameter : " + username + ":" + pwd);
		
		UserDAO usr = new UserDAOImpl();
		String resultCheckUser = usr.checkUsernamePassword("", username, pwd);
		log.debug("result >>> " + result);
		resultCheckUser = "N";
		if (resultCheckUser.equals("N")){
			result = true;
//			request.getSession().setAttribute(EJDAConstant.SESSION_NAME.PAGE, "user_screen.jsp");
//			request.getSession().setAttribute(EJDAConstant.SESSION_NAME.PAGE, "role_menu.jsp");
//			response.sendRedirect(EJDAConstant.PAGE.INDEX_PAGE);
		}else{
			log.debug("else >> " + result);
			result = false;
//			request.setAttribute("messages",result);
//			response.sendRedirect("index.jsp");
		}
		return result;
	}

}

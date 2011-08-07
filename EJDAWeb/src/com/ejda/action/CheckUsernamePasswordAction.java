package com.ejda.action;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.tcd.ejda.dao.RoleMenuDAO;
import com.tcd.ejda.dao.RoleMenuDAOImpl;
import com.tcd.ejda.dao.UserDAO;
import com.tcd.ejda.dao.UserDAOImpl;
import com.tcd.ejda.md5.EncryptMD5;
import com.tcd.ejda.model.RoleMenuModel;

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
		Vector vc = new Vector();
		// TODO Auto-generated method stub
		log.debug("[ Start : CheckUsernamePasswordServlet ]");
		String username = getRequest().getParameter("user");
		String pwd = getRequest().getParameter("pwd");
		log.debug(" get Parameter : " + username + ":" + pwd);
		
		EncryptMD5 en = new EncryptMD5();
		pwd = en.getHash(pwd);
		
		UserDAO usr = new UserDAOImpl();
		String resultCheckUser = usr.checkUsernamePassword("", username, pwd);
		log.debug("result >>> " + resultCheckUser);
		//resultCheckUser = "N";
		if (resultCheckUser.equals("N")){
			
			RoleMenuDAO dao = new RoleMenuDAOImpl();
			try {
				vc = dao.getRoleMenu(username);
				getRequest().getSession().setAttribute("iuser", username);
				log.debug("getRoleMenu vc >>>> " + vc.size());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.debug("getRoleMenu",e);
				e.printStackTrace();
			}
			
			result = true;

		}else{
			log.debug("else >> " + result);
			getRequest().getSession().setAttribute("messages",resultCheckUser);
			result = false;
			
//			response.sendRedirect("index.jsp");
		}
		return result;
	}
	
}

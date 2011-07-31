package com.ejda.action;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.tcd.ejda.dao.UserDAO;
import com.tcd.ejda.dao.UserDAOImpl;
public class LogoutAction extends AbstractAction {
	private Logger log = Logger.getLogger(LogoutAction.class);
	@Override
	public void clearSessionNotUsed() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		log.debug("*********** init ***********");
		log.debug("[Start : Logout Action]");
		log.debug("i user >> " + getRequest().getSession().getAttribute("iuser"));
		String iuser = (String)getRequest().getSession().getAttribute("iuser");
		UserDAO dao = new UserDAOImpl();
		try {
			if (dao.logoutUser(iuser)){
				log.debug("Logout Success");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.debug("LogoutAction",e);
			e.printStackTrace();
		}
	}

	@Override
	public boolean methodAction(String ejdaMethod) {
		// TODO Auto-generated method stub
		
		return false;
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}

}

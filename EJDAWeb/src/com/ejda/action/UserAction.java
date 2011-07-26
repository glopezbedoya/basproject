package com.ejda.action;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.tcd.ejda.dao.RoleDAO;
import com.tcd.ejda.dao.RoleDAOImpl;
import com.tcd.ejda.dao.UserDAO;
import com.tcd.ejda.dao.UserDAOImpl;
import com.tcd.ejda.model.RoleModel;
import com.tcd.ejda.model.UserRoleModel;
import com.tcd.ejda.model.UsrModel;
import org.apache.log4j.Logger;
public class UserAction extends AbstractAction {
	private Logger log = Logger.getLogger(UserAction.class);
	@Override
	public void clearSessionNotUsed() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean methodAction(String ejdaAction) {
		// TODO Auto-generated method stub
		log.debug("[ Start : User Action ]");
		if(ejdaAction.equalsIgnoreCase("doSearch")){
			return doSearch();
		}else if(ejdaAction.equalsIgnoreCase("doNew")){
			return doNew();
		}else if(ejdaAction.equalsIgnoreCase("doAdd")){
			return doAdd();
		}else if(ejdaAction.equalsIgnoreCase("doUpdate")){
			return doUpdate();
		}else if(ejdaAction.equalsIgnoreCase("doEdit")){
			return doEdit();
		}else if(ejdaAction.equalsIgnoreCase("doDelete")){
			return doDelete();
		}
		
		return false;
	}

	private boolean doDelete() {
		log.debug("[Start User Action : do Delete ]");
		boolean result = false;
		String jda_id = getRequest().getParameter("ejda_id");
	
		log.debug("role id >>> " +  jda_id);
		UserDAO user = new UserDAOImpl();
		try {
			if (user.deleteUser(jda_id)){
				result = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e.getMessage());
		}
		
		
		
		return result;
	}

	private boolean doEdit() {
		// TODO Auto-generated method stub
		boolean result = false;
		log.debug("[ Start : do Edit ]");
		String ejda_id = getRequest().getParameter("ejda_id");
		String usrname = getRequest().getParameter("user_name");
		String pwd = getRequest().getParameter("pwd");
		String conpwd = getRequest().getParameter("crmpwd");
		String iv_user = getRequest().getParameter("iv_user");
		String firstname = getRequest().getParameter("firstname");
		String lastname = getRequest().getParameter("lastname");
		String eff_date = getRequest().getParameter("eff_date");
		String exp_date = getRequest().getParameter("exp_date");
		String department =getRequest().getParameter("department");
		//String ipAddress = request.getRemoteAddr();
		
		log.debug("ejda_id >> " + ejda_id);
		log.debug("usrname >> " + usrname);
		log.debug("pwd >> " + pwd);
		log.debug("conpwd >> " + conpwd);
		log.debug("iv_user >> " + iv_user);
		log.debug("firstname >> " + firstname);
		log.debug("lastname >> " + lastname);
		log.debug("eff_date >> " + eff_date);
		log.debug("exp_date >> " + exp_date);
		log.debug("department >> " + department);
		UsrModel um = new UsrModel();
		um.setJda_id(ejda_id);
		um.setIV_USER(iv_user);
		um.setUSERNAME(usrname);
		um.setPWD(pwd);
		um.setCONPWD(conpwd);
		um.setLASTNAME(lastname);
		um.setFIRSTNAME(firstname);

//		um.setEFFECTIVE_DATE(Date.valueOf(DateFormat(eff_date)));
//		um.setEXPIRY_DATE(Date.valueOf(DateFormat(exp_date)));
		um.setCreate_by("veena");
		um.setUpdate_by("veena");
		um.setDEPARTMENT(department);
		
		getRequest().getSession().setAttribute("userModel", um);
		getRequest().getSession().setAttribute("returnVal", "UPDATE");
		Vector vc = new Vector();
		Vector roleUser = new Vector();
		RoleDAO role = new RoleDAOImpl();
		try {
			vc = role.selectRole(iv_user);
			String returnValue = getInnerTable(vc);
			roleUser = role.selectUserRole(ejda_id);
			String returnHidden = getInnerTextBox(roleUser);
			getRequest().getSession().setAttribute("UserRole", returnHidden);
			getRequest().getSession().setAttribute("rolename", returnValue);
			log.debug("role >> " + vc);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		result = true;
		return result;
	}

	private boolean doSearch() {
		// TODO Auto-generated method stub
		log.debug("[Start : do search ]");
		boolean result = false;
		String user_name = getRequest().getParameter("rolename");
		String iv_user = getRequest().getParameter("rolename");
		String first_name = getRequest().getParameter("rolename");
		String last_name = getRequest().getParameter("rolename");
		String locked = getRequest().getParameter("rolename");
		Vector vc = new Vector();

		UserDAO usr = new UserDAOImpl();
		try {
			vc = usr.selectUserforUpdate(iv_user, user_name, first_name,last_name, locked);
			log.debug("returnVC >>> " + vc);
			getRequest().getSession().setAttribute("returnVC", vc);
			result = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			

		
		
		return result;
	}

	private boolean doUpdate() {
		boolean result = false;
		Vector vc = new Vector();
		UsrModel um = new UsrModel();
		String ejda_id = getRequest().getParameter("ejda_id");
		String usrname = getRequest().getParameter("user_name");
		String pwd = getRequest().getParameter("password");
		String conpwd = getRequest().getParameter("crmpwd");
		String iv_user = getRequest().getParameter("iv_user");
		String firstname = getRequest().getParameter("firstname");
		String lastname = getRequest().getParameter("lastname");
		String eff_date = getRequest().getParameter("eff_date");
		String exp_date = getRequest().getParameter("exp_date");
		String department =getRequest().getParameter("department");
		String ipAddress = getRequest().getRemoteAddr();
		
		log.debug("user action result iv user >> " +iv_user);
		log.debug("user action result usrname >> " +usrname);
		log.debug("user action result pwd >> " +pwd);
		log.debug("user action result conpwd >> " +conpwd);
		log.debug("user action result lastname >> " +lastname);
		log.debug("user action result firstname >> " +firstname);
		log.debug("user action result eff_date >> " +eff_date);
		log.debug("user action result exp_date >> " +exp_date);
		log.debug("user action result department >> " +department);
		

		log.debug("[ Date : effective date ]" + DateFormat(eff_date) +":"+Date.valueOf(DateFormat(eff_date)));
		log.debug("[ Date : expiry date ]" + DateFormat(exp_date) +":"+Date.valueOf(DateFormat(exp_date)));
		um.setJda_id(ejda_id);
		um.setIV_USER(iv_user);
		um.setUSERNAME(usrname);
		um.setPWD(pwd);
		um.setCONPWD(conpwd);
		um.setLASTNAME(lastname);
		um.setFIRSTNAME(firstname);
		um.setEFFECTIVE_DATE(Date.valueOf(DateFormat(eff_date)));
		um.setEXPIRY_DATE(Date.valueOf(DateFormat(exp_date)));
		um.setCreate_by("veena");
		um.setUpdate_by("veena");
		um.setDEPARTMENT(department);
		um.setUSER_IP(ipAddress);
		
		log.debug("[ ADD ] : " + usrname);
		
		String check[] = getRequest().getParameterValues("checkbok");
		if (null!=check){
			log.debug("[ check >> ]" + check.length);
		
			for(int i=0;i<check.length;i++){
				UserRoleModel urm = new UserRoleModel();
				urm.setJda_id(ejda_id);
				urm.setIv_user(iv_user);
				urm.setRole_id(check[i]);
				urm.setCreate_by("veena");
				urm.setUpdate_by("veena");
				vc.add(urm);
				log.debug("checked box : " + check[i]);
			}
		}
		UserDAO userDAO = new UserDAOImpl();
		try {
			if (userDAO.updateUser(um, vc)){
				result = true;
				log.debug("success");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e.getStackTrace());
		}
	
		return result;
	}

	private boolean doAdd() {
		// TODO Auto-generated method stub
		boolean result = false;
		Vector vc = new Vector();
		UsrModel um = new UsrModel();
		
		String usrname = getRequest().getParameter("user_name");
		String pwd = getRequest().getParameter("crmpwd");
		String conpwd = getRequest().getParameter("crmpwd");
		String iv_user = getRequest().getParameter("iv_user");
		String firstname = getRequest().getParameter("firstname");
		String lastname = getRequest().getParameter("lastname");
		String eff_date = getRequest().getParameter("eff_date");
		String exp_date = getRequest().getParameter("exp_date");
		String department =getRequest().getParameter("department");
		String ipAddress = getRequest().getRemoteAddr();
		
		log.debug("user action result iv user >> " +iv_user);
		log.debug("user action result usrname >> " +usrname);
		log.debug("user action result pwd >> " +pwd);
		log.debug("user action result conpwd >> " +conpwd);
		log.debug("user action result lastname >> " +lastname);
		log.debug("user action result firstname >> " +firstname);
		log.debug("user action result eff_date >> " +eff_date);
		log.debug("user action result exp_date >> " +exp_date);
		log.debug("user action result department >> " +department);
		

		log.debug("[ Date : effective date ]" + DateFormat(eff_date) +":"+Date.valueOf(DateFormat(eff_date)));
		log.debug("[ Date : expiry date ]" + DateFormat(exp_date) +":"+Date.valueOf(DateFormat(exp_date)));
		um.setIV_USER(iv_user);
		um.setUSERNAME(usrname);
		um.setPWD(pwd);
		um.setCONPWD(conpwd);
		um.setLASTNAME(lastname);
		um.setFIRSTNAME(firstname);
		um.setEFFECTIVE_DATE(Date.valueOf(DateFormat(eff_date)));
		um.setEXPIRY_DATE(Date.valueOf(DateFormat(exp_date)));
		um.setCreate_by("veena");
		um.setUpdate_by("veena");
		um.setDEPARTMENT(department);
		um.setUSER_IP(ipAddress);
		
		log.debug("[ ADD ] : " + usrname);
		
		String check[] = getRequest().getParameterValues("checkbok");
		if (null!=check){
			log.debug("[ check >> ]" + check.length);
		
			for(int i=0;i<check.length;i++){
				UserRoleModel urm = new UserRoleModel();
				
				urm.setIv_user(iv_user);
				urm.setRole_id(check[i]);
				urm.setCreate_by("veena");
				urm.setUpdate_by("veena");
				vc.add(urm);
				log.debug("checked box : " + check[i]);
			}
		}
		UserDAO userDAO = new UserDAOImpl();
		try {
			if (userDAO.addNewUser(um, vc)){
				result = true;
				log.debug("success");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e.getStackTrace());
		}
	
		return result;
	}

	private boolean doNew() {
		// TODO Auto-generated method stub
		log.debug("[Start : do new ]");
		String iv_user = "";
		Vector vc = new Vector();
		RoleDAO role = new RoleDAOImpl();
		try {
			vc = role.selectRole(iv_user);
			String returnValue = getInnerTable(vc);
			
			getRequest().getSession().setAttribute("rolename", returnValue);
			log.debug("role >> " + vc);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		getRequest().getSession().setAttribute("returnVal", "NEW");
		
		return true;
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	private String getInnerTable(Vector vc){
		String innerTable = "";
		int count=0;
		String temp="";
		try{
			
				String div = "";	
				log.debug("getInnerTable vc >> " + vc.size());
					if(vc != null && vc.size()>0){	
						for(int j=0;j<vc.size();j++){
							RoleModel model = (RoleModel)vc.get(j);
							//div += "<td>"
							div	+= "<input type=\"checkbox\" name=\"checkbok\" id=\"checkbox\" value = \""+model.getRole_id()+ "\" /><font class = \"text\">"+model.getRole_name()+"</font>";
							//	+ "</td>";
							
						}
					}else{
								div += 		"No Role";
					}
				
				
				innerTable +=div;

			log.debug("[ getInnerTable ] : innerTable = "+innerTable);
		}catch(Exception e){
			log.debug("Error >>> "+ e);
		}
		return innerTable;
	}
	private String getInnerTextBox(Vector vc){
		String innerTable = "";
		int count=0;
		String temp="";
		try{
			
				String div = "";	
				log.debug("getInnerTextBox vc >> " + vc.size());
					if(vc != null && vc.size()>0){	
						for(int j=0;j<vc.size();j++){
							UserRoleModel model = (UserRoleModel)vc.get(j);
							//div += "<td>"
							div	+= "<input type=\"hidden\" name=\"roletextbox\" id=\"roletextbox\" value = \""+model.getRole_id()+ "\" />";
							//	+ "</td>";
							
						}
					}else{
								div += 		"No Role";
					}
				
				
				innerTable +=div;

			log.debug("[ getInnerTable ] : innerTable = "+innerTable);
		}catch(Exception e){
			log.debug("Error >>> "+ e);
		}
		return innerTable;
	}
	private String DateFormat(String dt){
		SimpleDateFormat inFmt = new SimpleDateFormat("dd/MM/yyyy");

		SimpleDateFormat outFmt = new SimpleDateFormat("yyyy-MM-dd");

		java.util.Date rdt = null;
		try {
			rdt = inFmt.parse(dt);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String dateOut = outFmt.format(rdt);
		
		return dateOut;
	}
	private String CurDate(){
		java.sql.Timestamp curDate = new java.sql.Timestamp(new java.util.Date().getTime());
		
		SimpleDateFormat outFmt = new SimpleDateFormat("yyyy-MM-dd");
		
		String dateOut = outFmt.format(curDate);
				
		return dateOut;
	}
}

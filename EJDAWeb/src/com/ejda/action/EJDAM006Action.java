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
import com.tcd.ejda.md5.EncryptMD5;
import com.tcd.ejda.model.RoleModel;
import com.tcd.ejda.model.TransactionLogModel;
import com.tcd.ejda.model.UserRoleModel;
import com.tcd.ejda.model.UsrModel;
import com.tcd.ejda.model.ValueListModel;

import org.apache.log4j.Logger;

import com.ejda.constant.EJDAConstant;
import com.ejda.sessionBean.UserBean;
import com.ejda.util.DisplayFormatUtil;
import com.ejda.util.EJDAUtil;
public class EJDAM006Action extends AbstractAction {
	
	
	/*
	 * User Action*/
	private Logger log = Logger.getLogger(EJDAM006Action.class);
	private UserBean userBean;
	
	private UserBean getUserBean(){
		UserBean userBean = (UserBean)getRequest().getSession().getAttribute("userBean");
		if(null == userBean){
			userBean = new UserBean();
		}
		return userBean;
	}
	private void setUserBean(UserBean userBean){
		getRequest().getSession().setAttribute("userBean", userBean);
	}
	
	@Override
	public void clearSessionNotUsed() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		getRequest().getSession().removeAttribute("returnVal");
		userBean = getUserBean();
		userBean.setUsrMSP(new UsrModel());
		userBean.setUsrVt(new Vector());		
		ValueListModel valueListM = new ValueListModel();
		valueListM.setReturnModel("UsrModel");
		userBean.setValueListM(valueListM);
		setUserBean(userBean);
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
		String ipAddress = getRequest().getRemoteAddr();
		String iuser = (String) getRequest().getSession().getAttribute("iuser");
		if (null==iuser || "".equals(iuser)){
			iuser = "system";
		}
		log.debug("role id >>> " +  jda_id);
		UserDAO user = new UserDAOImpl();
		try {
			if (user.deleteUser(jda_id)){
				TransactionLogModel transactionLogModel = new TransactionLogModel() ;
				EJDAUtil ejda = new EJDAUtil();
				transactionLogModel.setMenuId("M006");
				transactionLogModel.setTranAction("DEL");
				transactionLogModel.setDescription("Delete User");
				transactionLogModel.setIpAddress(ipAddress);
				transactionLogModel.setTranBy(iuser);
				ejda.insertTranLog(transactionLogModel);
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
		String user_status =getRequest().getParameter("user_status");
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
		log.debug("user_status >> " + user_status);
		UsrModel um = new UsrModel();
		um.setJda_id(ejda_id);
		um.setIV_USER(iv_user);
		um.setUSERNAME(usrname);
		um.setPWD(pwd);
		um.setCONPWD(conpwd);
		um.setLASTNAME(lastname);
		um.setFIRSTNAME(firstname);
		log.debug("value date >> "+Date.valueOf(eff_date));
//		log.debug("value date >> "+DisplayFormatUtil.DateFormatYYYYMMDD(eff_date));
		um.setEFFECTIVE_DATE(Date.valueOf(eff_date));
		um.setEXPIRY_DATE(Date.valueOf(exp_date));
		um.setCreate_by("veena");
		um.setUpdate_by("veena");
		um.setDEPARTMENT(department);
		um.setUSER_STATUS(user_status);
		
		getRequest().getSession().setAttribute("userModel", um);
		getRequest().getSession().setAttribute("returnVal", "UPDATE");
		Vector vc = new Vector();
		Vector roleUser = new Vector();
		RoleDAO role = new RoleDAOImpl();
		try {
			vc = role.selectRole("");
			String returnValue = getInnerTable(vc);
			roleUser = role.selectUserRole(ejda_id);
			String returnHidden = getInnerTextBox(roleUser);
			getRequest().getSession().setAttribute("UserRole", returnHidden);
			getRequest().getSession().setAttribute("rolename", returnValue);
			log.debug("role >> " + vc);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.debug("UserAction : doEdit",e);
		}
		
		result = true;
		return result;
	}

	private boolean doSearch() {
		// TODO Auto-generated method stub
		log.debug("[Start : do search ]");
		boolean result = false;

		String locked = getRequest().getParameter("rdoStatus");
		log.debug("locked >> " +locked);
		Vector vc = new Vector();
		
		userBean = getUserBean();
		setCriteriaParameter();
		ValueListModel valueListM = new ValueListModel();
		ValueListAction valueListA = new ValueListAction();
		try {
			Vector userVt = new Vector();
			valueListM = userBean.getValueListM();
			valueListM.setSQL(this.setSQL(userBean.getUsrMSP()));
			valueListM.setParameters(getValueListParameters());
			valueListM.setPage(getRequest().getParameter("page"));
			valueListM.setItemsPerPage(Integer.parseInt(getRequest().getParameter("volumePerPage")));
			userBean.setValueListM(valueListA.doSearch(valueListM));
			userBean.setUsrVt(userBean.getValueListM().getResult());
			log.debug("tranLogVt.size = " + userBean.getUsrVt().size());
			log.debug("tranLogBean.getValueListM().getCount() = "+userBean.getValueListM().getCount());
			getRequest().getSession().removeAttribute("VALUE_LIST");
			setUserBean(userBean);
			result = true;
			
		} catch (Exception e) {
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
		String user_status[] = getRequest().getParameterValues("user_status");
		String iuser = (String) getRequest().getSession().getAttribute("iuser");
		if (null==iuser || "".equals(iuser)){
			iuser = "system";
		}
		String tempUser="";
		log.debug("user action result iv user >> " +iv_user);
		log.debug("user action result usrname >> " +usrname);
		log.debug("user action result pwd >> " +pwd);
		log.debug("user action result conpwd >> " +conpwd);
		log.debug("user action result lastname >> " +lastname);
		log.debug("user action result firstname >> " +firstname);
		log.debug("user action result eff_date >> " +eff_date);
		log.debug("user action result exp_date >> " +exp_date);
		log.debug("user action result department >> " +department);
		if (!"".equals(conpwd)){
			EncryptMD5 en = new EncryptMD5();
			conpwd = en.getHash(pwd);
		}
//		PwdEncryption pwdEncrypt = new PwdEncryption();
//		conpwd = pwdEncrypt.PwdEncryption(conpwd);
		log.debug("EncryptMD5 >>> " + conpwd);
		if (null!=user_status){
			log.debug("[ != null >> ]");
			tempUser = "N";
		}
		log.debug("user_status >>> " + tempUser);

//		log.debug("[ Date : effective date ]" + DateFormat(eff_date) +":"+Date.valueOf(DateFormat(eff_date)));
//		log.debug("[ Date : expiry date ]" + DateFormat(exp_date) +":"+Date.valueOf(DateFormat(exp_date)));
		um.setJda_id(ejda_id);
		um.setIV_USER(iv_user);
		um.setUSERNAME(usrname);
		um.setPWD(conpwd);
		um.setCONPWD(conpwd);
		um.setLASTNAME(lastname);
		um.setFIRSTNAME(firstname);
		um.setEFFECTIVE_DATE(Date.valueOf(DisplayFormatUtil.DateFormat(eff_date)));
		um.setEXPIRY_DATE(Date.valueOf(DisplayFormatUtil.DateFormat(exp_date)));
		um.setCreate_by(iuser);
		um.setUpdate_by(iuser);
		um.setDEPARTMENT(department);
		um.setUSER_IP(ipAddress);
		um.setUSER_STATUS(tempUser);
		log.debug("[ ADD ] : " + usrname);
		
		String check[] = getRequest().getParameterValues("checkbok");
		if (null!=check){
			log.debug("[ check >> ]" + check.length);
		
			for(int i=0;i<check.length;i++){
				UserRoleModel urm = new UserRoleModel();
				urm.setJda_id(ejda_id);
				urm.setIv_user(iv_user);
				urm.setRole_id(check[i]);
				urm.setCreate_by(iuser);
				urm.setUpdate_by(iuser);
				vc.add(urm);
				log.debug("checked box : " + check[i]);
			}
		}
		UserDAO userDAO = new UserDAOImpl();
		try {
			if (userDAO.updateUser(um, vc)){
				getRequest().getSession().removeAttribute("UserRole");
				getRequest().getSession().removeAttribute("rolename");
				getRequest().getSession().removeAttribute("userModel");
				getRequest().getSession().removeAttribute("returnVal");
				TransactionLogModel transactionLogModel = new TransactionLogModel() ;
				EJDAUtil ejda = new EJDAUtil();
				transactionLogModel.setMenuId("M006");
				transactionLogModel.setTranAction("UPDATE");
				transactionLogModel.setDescription("Update User");
				transactionLogModel.setIpAddress(ipAddress);
				transactionLogModel.setTranBy(iuser);
				ejda.insertTranLog(transactionLogModel);
				result = doSearch();
				//result = true;
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
		String iuser = (String) getRequest().getSession().getAttribute("iuser");
		
		EncryptMD5 en = new EncryptMD5();
		conpwd = en.getHash(conpwd);
		
		if (null==iuser || "".equals(iuser)){
			iuser = "system";
		}
		log.debug("user action result iv user >> " +iv_user);
		log.debug("user action result usrname >> " +usrname);
		log.debug("user action result pwd >> " +pwd);
		log.debug("user action result conpwd >> " +conpwd);
		log.debug("user action result lastname >> " +lastname);
		log.debug("user action result firstname >> " +firstname);
		log.debug("user action result eff_date >> " +eff_date);
		log.debug("user action result exp_date >> " +exp_date);
		log.debug("user action result department >> " +department);
		

		log.debug("[ Date : effective date ]" + DisplayFormatUtil.DateFormat(eff_date) +":"+Date.valueOf(DisplayFormatUtil.DateFormat(eff_date)));
		log.debug("[ Date : expiry date ]" + DisplayFormatUtil.DateFormat(exp_date) +":"+Date.valueOf(DisplayFormatUtil.DateFormat(exp_date)));
		um.setIV_USER(iv_user);
		um.setUSERNAME(usrname);
		um.setPWD(conpwd);
		um.setCONPWD(conpwd);
		um.setLASTNAME(lastname);
		um.setFIRSTNAME(firstname);
		um.setEFFECTIVE_DATE(Date.valueOf(DisplayFormatUtil.DateFormat(eff_date)));
		um.setEXPIRY_DATE(Date.valueOf(DisplayFormatUtil.DateFormat(exp_date)));
		um.setCreate_by(iuser);
		um.setUpdate_by(iuser);
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
				urm.setCreate_by(iuser);
				urm.setUpdate_by(iuser);
				vc.add(urm);
				log.debug("checked box : " + check[i]);
			}
		}
		UserDAO userDAO = new UserDAOImpl();
		try {
			if (userDAO.addNewUser(um, vc)){
				TransactionLogModel transactionLogModel = new TransactionLogModel() ;
				EJDAUtil ejda = new EJDAUtil();
				transactionLogModel.setMenuId("M006");
				transactionLogModel.setTranAction("ADD");
				transactionLogModel.setDescription("Add User");
				transactionLogModel.setIpAddress(ipAddress);
				transactionLogModel.setTranBy(iuser);
				ejda.insertTranLog(transactionLogModel);
				result = doSearch();
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
							//div += "<tr><td>"
								div += "<input type=\"checkbox\" name=\"checkbok\" id=\"checkbox\" value = \""+model.getRole_id()+ "\" /><font class = \"text\">"+model.getRole_name()+"</font> |";
							//	+ "</td></tr>";
							
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
	
	private void setCriteriaParameter(){
		UsrModel usrMSP = new UsrModel();
		usrMSP.setIV_USER(getRequest().getParameter("txtIVUser"));
		usrMSP.setFIRSTNAME(getRequest().getParameter("txtFirstName"));
		usrMSP.setLASTNAME(getRequest().getParameter("txtLastName"));
		usrMSP.setUSER_STATUS(getRequest().getParameter("rdoStatus"));
		getUserBean().setUsrMSP(usrMSP);
	}
	
	private Vector getValueListParameters() {
		Vector parameters = new Vector();
		UsrModel usrCri = getUserBean().getUsrMSP();
		log.debug("getValueListParameters >> " + usrCri.getUSER_STATUS());
		log.info("parameters.size() = "+parameters.size());
		if(!"".equals(usrCri.getIV_USER()) || !"".equals(usrCri.getFIRSTNAME()) || !"".equals(usrCri.getLASTNAME()) || !"".equals(usrCri.getUSER_STATUS())){
			if(!"".equals(usrCri.getIV_USER())) {
				log.debug("User name = "+usrCri.getIV_USER());
				parameters.add("%"+usrCri.getIV_USER().toUpperCase()+"%");
			}
			if(!"".equals(usrCri.getFIRSTNAME())) {
				log.debug("User name = "+usrCri.getFIRSTNAME());
				parameters.add("%"+usrCri.getFIRSTNAME().toUpperCase()+"%");
			}
			if(!"".equals(usrCri.getLASTNAME())) {
				log.debug("User name = "+usrCri.getLASTNAME());
				parameters.add("%"+usrCri.getLASTNAME().toUpperCase()+"%");
			}
			if(!"".equals(usrCri.getUSER_STATUS())) {
				log.debug("getUSER_STATUS = "+usrCri.getUSER_STATUS());
				if (!"ALL".equals(usrCri.getUSER_STATUS())){
					parameters.add("'L'");
				}
			}
		}
		return parameters;
	}
	private String setSQL(UsrModel usrM){
		StringBuffer sql = new StringBuffer();
		log.debug("setSQL >>> "+usrM.getUSER_STATUS());
		try{
			sql.append(EJDAConstant.SQL.USER__SCREEN_SQL);
			if(!"".equals(usrM.getIV_USER()) || !"".equals(usrM.getFIRSTNAME()) || !"".equals(usrM.getLASTNAME()) || !"".equals(usrM.getUSER_STATUS())){
				sql.append(" WHERE ");
				if(!"".equals(usrM.getIV_USER()))
					sql.append(" UPPER(IV_USER) like ? AND ");
				if(!"".equals(usrM.getFIRSTNAME()))
					sql.append(" UPPER(FIRST_NAME) like ? AND ");
				if(!"".equals(usrM.getLASTNAME()))
					sql.append(" UPPER(LAST_NAME) like ? AND ");
				if(!"".equals(usrM.getUSER_STATUS())){
					if (!"ALL".equals(usrM.getUSER_STATUS())){
						if ("LOCK".equals(usrM.getUSER_STATUS()))
							sql.append(" USER_STATUS = ? AND ");
						if ("UNLOCK".equals(usrM.getUSER_STATUS()))
							sql.append(" USER_STATUS <> ? AND ");
					}
					
				}
					
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		sql = removeWasteSQL(sql);
		return sql.toString();
	}
//	public TransactionLogModel tranLogModel(String doType){
//		TransactionLogModel TransactionLogModel = null;
//		EJDAUtil ejda = new EJDAUtil();
//		TransactionLogModel.setMenuId("M006");
//		TransactionLogModel.setTranAction(doType);
//		TransactionLogModel.setDescription(doType);
//		TransactionLogModel.setIpAddress(ipAddress);
//		ejda.insertTranLog(TransactionLogModel);
//		return TransactionLogModel;
//	}
}

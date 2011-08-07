package com.ejda.action;

import java.sql.SQLException;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.ejda.constant.EJDAConstant;
import com.ejda.sessionBean.RoleBean;
import com.ejda.util.EJDAUtil;
import com.tcd.ejda.dao.MenuDAO;
import com.tcd.ejda.dao.MenuDAOImpl;
import com.tcd.ejda.dao.RoleDAO;
import com.tcd.ejda.dao.RoleDAOImpl;
import com.tcd.ejda.model.MenuModel;
import com.tcd.ejda.model.RoleFunctionModel;
import com.tcd.ejda.model.RoleModel;
import com.tcd.ejda.model.TransactionLogModel;
import com.tcd.ejda.model.ValueListModel;

import org.apache.log4j.Logger;

public class EJDAM007Action extends AbstractAction {
	/*
	 * Role Menu
	 * */
	private Logger log = Logger.getLogger(EJDAM007Action.class);
	private RoleBean roleBean;
	
	private RoleBean getRoleBean(){
		roleBean = (RoleBean)getRequest().getSession().getAttribute("roleBean");
		if(null == roleBean){
			roleBean = new RoleBean();
		}
		return roleBean;
	}
	
	private void setRoleBean(RoleBean roleBean){
		getRequest().getSession().setAttribute("roleBean", roleBean);
	}
	
	
	@Override
	public void clearSessionNotUsed() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		log.debug("*********** init ***********");
		getRequest().getSession().removeAttribute("returnVal");
		getRequest().getSession().removeAttribute("returnValUpdate");
		getRequest().getSession().removeAttribute("returnVC");
		
		roleBean = getRoleBean();
		roleBean.setRoleVt(new Vector());
		roleBean.setRoleMSP(new RoleModel());
		ValueListModel valueListM = new ValueListModel();
		valueListM.setReturnModel("RoleModel");
		roleBean.setValueListM(valueListM);
		setRoleBean(roleBean);
	}

	@Override
	public boolean methodAction(String ejdaAction) {
		// TODO Auto-generated method stub
		log.debug("[ Start : Role Action ]");
		if(ejdaAction.equalsIgnoreCase("doSearch")){
			return doSearch();
		}else if(ejdaAction.equalsIgnoreCase("doNew")){
			return doNew();
		}else if(ejdaAction.equalsIgnoreCase("doAdd")){
			return doAdd();
		}else if(ejdaAction.equalsIgnoreCase("doEdit")){
			return doEdit();
		}else if(ejdaAction.equalsIgnoreCase("doUpdate")){
			return doUpdate();
		}else if(ejdaAction.equalsIgnoreCase("doDelete")){
			return doDelete();
		}
		
		return false;
	}

	private boolean doDelete() {
		// TODO Auto-generated method stub
		log.debug("[Start : do Delete ]");
		boolean result = false;
		String role_id = getRequest().getParameter("role_id");
		String role_name = getRequest().getParameter("hrole_name");
		String ipAddress = getRequest().getRemoteAddr();
		String iuser = (String) getRequest().getSession().getAttribute("iuser");
		if (null==iuser || "".equals(iuser)){
			iuser = "system";
		}
		log.debug("role id >>> " + role_id);
		RoleDAO role = new RoleDAOImpl();
		try {
			if (role.deleteRole(role_id)){
				TransactionLogModel transactionLogModel = new TransactionLogModel() ;
				EJDAUtil ejda = new EJDAUtil();
				transactionLogModel.setMenuId("M007");
				transactionLogModel.setTranAction("DEL");
				transactionLogModel.setDescription("Delete Role");
				transactionLogModel.setIpAddress(ipAddress);
				transactionLogModel.setTranBy(iuser);
				ejda.insertTranLog(transactionLogModel);
				result = doSearch();
//				result = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return result;
	}

	private boolean doEdit() {
		// TODO Auto-generated method stub
		log.debug("[ Start : do Edit ]" );
		Vector vc = new Vector();
		boolean result = false;
		String role_id = getRequest().getParameter("role_id");
		log.debug("Role action : doEdit 1 setCriteriaParameter >>> "+getRequest().getParameter("txtRoleName"));
//		String role_id = "R35";
		log.debug("== role id : " + role_id);
		String role_name = getRequest().getParameter("hrole_name");
		log.debug("=== role_name >> " +role_name);
		RoleDAO role = new RoleDAOImpl();
		try {
			vc = role.selectEditRole(role_id); 
			String returnValue = getInnerTableEdit(vc);
			log.debug("getInnerTableEdit >> " + returnValue);
			getRequest().getSession().setAttribute("returnValUpdate", returnValue);
			getRequest().getSession().setAttribute("hidrolename", role_name);
			log.debug("Role action : doEdit 2 setCriteriaParameter >>> "+getRequest().getParameter("txtRoleName"));
			result = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	private boolean doSearch() {
		// TODO Auto-generated method stub
		boolean result = false;
		String role_name = getRequest().getParameter("rolename");
		Vector vc = new Vector();
		ValueListModel valueListM = new ValueListModel();
		ValueListAction valueListA = new ValueListAction();
		RoleDAO role = new RoleDAOImpl();
		setCriteriaParameter();
		try {
			Vector tranLogVt = new Vector();
			valueListM = roleBean.getValueListM();
			valueListM.setSQL(this.setSQL(roleBean.getRoleMSP()));
			valueListM.setParameters(getValueListParameters());
			valueListM.setPage(getRequest().getParameter("page"));
			valueListM.setItemsPerPage(Integer.parseInt(getRequest().getParameter("volumePerPage")));
			roleBean.setValueListM(valueListA.doSearch(valueListM));
			roleBean.setRoleVt(roleBean.getValueListM().getResult());
			log.debug("tranLogVt.size = " + roleBean.getRoleVt().size());
			log.debug("tranLogBean.getValueListM().getCount() = "+roleBean.getValueListM().getCount());
			getRequest().getSession().removeAttribute("VALUE_LIST");
			setRoleBean(roleBean);
			result = true;
//			vc = role.selectRole(role_name);
//			log.debug("vc >>> " + vc.size());
//			getRequest().getSession().setAttribute("returnVC", vc);
//			result = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return result;
	}

	private boolean doNew() {
		// TODO Auto-generated method stub
		boolean result = false;
		Vector vc = new Vector();
		MenuDAO menus = new MenuDAOImpl();
		vc = menus.SearchMenu();
		
		String returnValue = getInnerTable(vc);
		log.debug("returnValue >> " + returnValue);
		getRequest().getSession().setAttribute("returnVal", returnValue);
		result = true;

		return result;
	}

	private boolean doUpdate() {
		// TODO Auto-generated method stub
		boolean result =false;
		log.debug("Role action : doUpdate setCriteriaParameter >>> "+getRequest().getParameter("txtRoleName"));
		String role_name = getRequest().getParameter("rolename");
		String role_fund_id = getRequest().getParameter("role_func_id");
		String role_id = getRequest().getParameter("role_id");
		String ipAddress = getRequest().getRemoteAddr();
		String iuser = (String) getRequest().getSession().getAttribute("iuser");
		if (null==iuser || "".equals(iuser)){
			iuser = "system";
		}
		log.debug("role_name >> "+role_name);
		log.debug("role_fund_id >> "+role_fund_id);
		log.debug("role_id >> "+role_id);
		
		String strInqs = ""; 
		Vector vc = new Vector();
		RoleModel role = new RoleModel();
		role.setRole_id(role_id);
		role.setRole_name(role_name);
		role.setUpdate_by(iuser);
		
		strInqs = getRequest().getParameter("func_type");
		log.debug("strinqs >>> " + strInqs);
		if (null!=strInqs){
			String inqsTmp[] = strInqs.split("\\|"); 
			for(int j=0;j<inqsTmp.length;j++){
				//log.debug("inqsTmp[j] >>> " + inqsTmp[j]);
				//inqsM001=M001=Y:addM001=M001=Y:updM001=M001=Y:delM001=M001=Y:
				if (null!=inqsTmp[j] && !"".equals(inqsTmp[j])){
					//log.debug("inqsTmp[j] >>> " + inqsTmp[j]);
					String temps[] = inqsTmp[j].split(":");
						if (!"".equals(temps[0])){
						RoleFunctionModel rfModel = new RoleFunctionModel();
						//inqsM001=M001=Y
						for (int k=0;k<temps.length; k++){
							//log.debug("temp >>> " + temps[k]);
							
							String ftype[]=temps[k].split("=");
							String tmpmenu = "";
	
							rfModel.setMenu_id(ftype[1]);
	
							if (ftype[0].subSequence(0,4).equals("inqs")){
								//log.debug("inqs >> " + ftype[2]);
								rfModel.setFunc_inqs(ftype[2]);
								
							}
							if (ftype[0].subSequence(0,3).equals("add")){
								//log.debug("add >> " + ftype[2]);
								rfModel.setFunc_add(ftype[2]);
								
							}
							if (ftype[0].subSequence(0,3).equals("upd")){
								//log.debug("upd >> " + ftype[2]);
								rfModel.setFunc_update(ftype[2]);
								
							}
							if (ftype[0].subSequence(0,3).equals("del")){
								//log.debug("del >> " + ftype[2]);
								rfModel.setFunc_del(ftype[2]);
								
							}
//							rfModel.setCreate_by(iuser);
							rfModel.setRole_func_id(role_fund_id);
							rfModel.setRole_id(role_id);
							rfModel.setUpdate_by(iuser);
						}
						
						vc.add(rfModel);
					}
				}
			}
		}
		
		getRequest().getSession().setAttribute("returnValUpdate", "");
		RoleDAO rd = new RoleDAOImpl();
		try {
			if (rd.updateRole(role, vc)){
				log.debug("Role action : do Update success setCriteriaParameter >>> "+getRequest().getParameter("txtRoleName"));
				TransactionLogModel transactionLogModel = new TransactionLogModel() ;
				EJDAUtil ejda = new EJDAUtil();
				transactionLogModel.setMenuId("M007");
				transactionLogModel.setTranAction("UPDATE");
				transactionLogModel.setDescription("Update Role");
				transactionLogModel.setIpAddress(ipAddress);
				transactionLogModel.setTranBy(iuser);
				ejda.insertTranLog(transactionLogModel);
				result = doSearch();
//				result = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		result=true;
	
		
		//getRequest().getSession().setAttribute("returnVal", "");
		return result;
	}

	private boolean doAdd() {
		// TODO Auto-generated method stub
		boolean result =false;
		String ipAddress = getRequest().getRemoteAddr();
		String role_name = getRequest().getParameter("rolename");
		String iuser = (String) getRequest().getSession().getAttribute("iuser");
		if (null==iuser || "".equals(iuser)){
			iuser = "system";
		}
		String strInqs = "";
		Vector vc = new Vector();
		RoleModel role = new RoleModel();
		role.setRole_name(role_name);
		role.setCreate_by(iuser);
		role.setUpdate_by(iuser);
		
		strInqs = getRequest().getParameter("func_type");
		//log.debug("strinqs >>> " + strInqs);
		if (null!=strInqs){
			String inqsTmp[] = strInqs.split("\\|"); 
			for(int j=0;j<inqsTmp.length;j++){
				//log.debug("inqsTmp[j] >>> " + inqsTmp[j]);
				//inqsM001=M001=Y:addM001=M001=Y:updM001=M001=Y:delM001=M001=Y:
				if (null!=inqsTmp[j] && !"".equals(inqsTmp[j])){
					//log.debug("inqsTmp[j] >>> " + inqsTmp[j]);
					String temps[] = inqsTmp[j].split(":");
						if (!"".equals(temps[0])){
						RoleFunctionModel rfModel = new RoleFunctionModel();
						//inqsM001=M001=Y
						for (int k=0;k<temps.length; k++){
							//log.debug("temp >>> " + temps[k]);
							
							String ftype[]=temps[k].split("=");
							String tmpmenu = "";
	
							rfModel.setMenu_id(ftype[1]);
	
							if (ftype[0].subSequence(0,4).equals("inqs")){
								//log.debug("inqs >> " + ftype[2]);
								rfModel.setFunc_inqs(ftype[2]);
								
							}
							if (ftype[0].subSequence(0,3).equals("add")){
								//log.debug("add >> " + ftype[2]);
								rfModel.setFunc_add(ftype[2]);
								
							}
							if (ftype[0].subSequence(0,3).equals("upd")){
								//log.debug("upd >> " + ftype[2]);
								rfModel.setFunc_update(ftype[2]);
								
							}
							if (ftype[0].subSequence(0,3).equals("del")){
								//log.debug("del >> " + ftype[2]);
								rfModel.setFunc_del(ftype[2]);
								
							}
							rfModel.setCreate_by(iuser);
							rfModel.setUpdate_by(iuser);
						}
						
						vc.add(rfModel);
					}
				}
			}
		}
		RoleDAO rd = new RoleDAOImpl();
		try {
			if (rd.insertRole(role, vc)){
				//getRequest().getSession().setAttribute("returnVal", "");	
				TransactionLogModel transactionLogModel = new TransactionLogModel() ;
				EJDAUtil ejda = new EJDAUtil();
				transactionLogModel.setMenuId("M007");
				transactionLogModel.setTranAction("ADD");
				transactionLogModel.setDescription("Add Role");
				transactionLogModel.setIpAddress(ipAddress);
				transactionLogModel.setTranBy(iuser);
				ejda.insertTranLog(transactionLogModel);
				result = doSearch();
//				result = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		result=true;
	
		
		getRequest().getSession().setAttribute("returnVal", "");
		return result;
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	private String getInnerTable(Vector vc){
		log.debug("Start : getInnerTable ");
		String innerTable = "";
		int count=0;
		String temp="";
		try{
			
				String div = "<table align=\"center\" width=\"800\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">";
					div += "<tr bgcolor=\"#0000CC\">";
					div += "<td width=\"100\"align = \"center\" ><font class=\"textHeader\">Menu</font></td>";
					div += "<td width=\"100\"align = \"center\"><font class=\"textHeader\">Inquery</font></td>";
					div += "<td width=\"100\"align = \"center\"><font class=\"textHeader\">Add</font></td>";
					div += "<td width=\"100\"align = \"center\"><font class=\"textHeader\">Update</font></td>";
					div += "<td width=\"100\"align = \"center\"><font class=\"textHeader\">Delete</font></td>";
					div += "</tr>";
					div += "<tr bgcolor=\"#0099CC\">";
					div += "<td width=\"100\"align = \"center\" ><font class=\"textHeader\">Main</font></td>";
					div += "<td width=\"100\"align = \"center\"><input type=\"checkbox\" name=\"inqs\" id=\"inqs\"value=\"\" / onclick=\"checkAllList('inqs')\"/></td>";
					div += "<td width=\"100\"align = \"center\"><input type=\"checkbox\" name=\"add\" id=\"add\"value=\"\" onclick=\"checkAllList('add')\" /></td>";
					div += "<td width=\"100\"align = \"center\"><input type=\"checkbox\" name=\"upd\" id=\"upd\"value=\"\" onclick=\"checkAllList('upd')\" /></td>";
					div += "<td width=\"100\"align = \"center\"><input type=\"checkbox\" name=\"del\" id=\"del\"value=\"\"onclick=\"checkAllList('del')\" /></td>";
					div += "</tr>";
					div += "<tr><td colspan = \"5\" height=\"10\"></td></tr>";
					if(vc != null && vc.size()>0){	
						for(int j=0;j<vc.size();j++){
							MenuModel model = (MenuModel)vc.get(j);
							
							div += "<tr>";
							div += "<td width=\"100\" align = \"left\" ><font class=\"text\">"+model.getMenu_name()+"</font></td>";
							div += "<input type = \"hidden\"name = \"menuid\" value=\""+model.getMenu_id()+"\">";
							div += "<input type = \"hidden\"name = \"status\" value=\""+model.getMenu_status()+"\">";
							
							if (null!= model.getMenu_status() && !model.getMenu_owner().equals("D")){
								div += "<td width=\"100\"><input type=\"checkbox\" name=\"inqs"+model.getMenu_id()+"\" id=\"inqs\"value=\""+model.getMenu_id()+"\" onclick=\"cancleDeleteAllCheckBox(this,'inqs')\" /></td>";
								div += "<td width=\"100\"><input type=\"checkbox\" name=\"add"+model.getMenu_id()+"\" id=\"add\"value=\""+model.getMenu_id()+"\"  onclick=\"cancleDeleteAllCheckBox(this,'add')\"/></td>";
								div += "<td width=\"100\"><input type=\"checkbox\" name=\"upd"+model.getMenu_id()+"\" id=\"upd\"value=\""+model.getMenu_id()+"\"  onclick=\"cancleDeleteAllCheckBox(this,'upd')\"/></td>";
								div += "<td width=\"100\"><input type=\"checkbox\" name=\"del"+model.getMenu_id()+"\" id=\"del\"value=\""+model.getMenu_id()+"\" onclick=\"cancleDeleteAllCheckBox(this,'del')\"/></td>";
							}
							log.debug("div >> " + div);
							div += "</tr>";
						}
					}else{
								div += 		"			<tr class=\"ROW\">"+
						"									<td colspan=\"6\" height=\"19\" align=\"center\" class=\"bu2\">No record found.</td>"+
						"								</tr>";
					}
				
					div += "</table>";
				innerTable +=div;

			log.debug("[ getInnerTable ] : innerTable = "+innerTable);
		}catch(Exception e){
			log.debug("Error >>> "+ e);
		}
		return innerTable;
	}
	private String getInnerTableEdit(Vector vc){
		log.debug("Start : getInnerTable ");
		String innerTable = "";
		int count=0;
		String temp="";
		try{
			
				String div = "<table align=\"center\" width=\"800\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">";
						div += "<tr bgcolor=\"#0000CC\">";
						div += "<td width=\"100\"align = \"center\" ><font class=\"textHeader\">Menu</font></td>";
						div += "<td width=\"100\"align = \"center\"><font class=\"textHeader\">Inquery</font></td>";
						div += "<td width=\"100\"align = \"center\"><font class=\"textHeader\">Add</font></td>";
						div += "<td width=\"100\"align = \"center\"><font class=\"textHeader\">Update</font></td>";
						div += "<td width=\"100\"align = \"center\"><font class=\"textHeader\">Delete</font></td>";
						div += "</tr>";
						div += "<tr bgcolor=\"#0099CC\">";
						div += "<td width=\"100\"align = \"center\" ><font class=\"textHeader\">Main</font></td>";
						div += "<td width=\"100\"align = \"center\"><input type=\"checkbox\" name=\"inqs\" id=\"inqs\"value=\"\" / onclick=\"checkAllList('inqs')\"/></td>";
						div += "<td width=\"100\"align = \"center\"><input type=\"checkbox\" name=\"add\" id=\"add\"value=\"\" onclick=\"checkAllList('add')\" /></td>";
						div += "<td width=\"100\"align = \"center\"><input type=\"checkbox\" name=\"upd\" id=\"upd\"value=\"\" onclick=\"checkAllList('upd')\" /></td>";
						div += "<td width=\"100\"align = \"center\"><input type=\"checkbox\" name=\"del\" id=\"del\"value=\"\"onclick=\"checkAllList('del')\" /></td>";
						div += "</tr>";
						div += "<tr><td colspan = \"5\" height=\"10\"></td></tr>";
					if(vc != null && vc.size()>0){	
						for(int j=0;j<vc.size();j++){
							RoleFunctionModel model = (RoleFunctionModel)vc.get(j);
							div += "<tr >";
							div += "<td width=\"100\" align = \"left\" ><font class=\"text\">"+model.getMenu_name()+"</font></td>";
							div += "<input type = \"hidden\"name = \"menuid\" value=\""+model.getMenu_id()+"\">";
							div += "<input type = \"hidden\"name = \"status\" value=\""+model.getMenu_status()+"\">";
							div += "<input type = \"hidden\"name = \"role_func_id\" value=\""+model.getRole_func_id()+"\">";
							div += "<input type = \"hidden\"name = \"role_id\" value=\""+model.getRole_id()+"\">";
							if (null!= model.getMenu_status() && !model.getMenu_status().equals("C")){
								div += "<td width=\"100\"align = \"center\"><input type=\"checkbox\" name=\"inqs"+model.getMenu_id()+"\" id=\"inqs\"value=\""+model.getFunc_inqs()+"\" onclick=\"cancleDeleteAllCheckBox(this,'inqs');cancleDeleteAll(this)\" /></td>";
								div += "<td width=\"100\"align = \"center\"><input type=\"checkbox\" name=\"add"+model.getMenu_id()+"\" id=\"add\"value=\""+model.getFunc_add()+"\" onclick=\"cancleDeleteAllCheckBox(this,'add');cancleDeleteAll(this)\"  /></td>";
								div += "<td width=\"100\"align = \"center\"><input type=\"checkbox\" name=\"upd"+model.getMenu_id()+"\" id=\"upd\"value=\""+model.getFunc_update()+"\"  onclick=\"cancleDeleteAllCheckBox(this,'upd');cancleDeleteAll(this)\" /></td>";
								div += "<td width=\"100\"align = \"center\"><input type=\"checkbox\" name=\"del"+model.getMenu_id()+"\" id=\"del\"value=\""+model.getFunc_del()+"\" onclick=\"cancleDeleteAllCheckBox(this,'del');cancleDeleteAll(this)\" /></td>";
							}
							log.debug("div >> " + div);
							div += "</tr>";
						}
					}else{
								div += 		"			<tr class=\"ROW\">"+
						"									<td colspan=\"6\" height=\"19\" align=\"center\" class=\"bu2\">No record found.</td>"+
						"								</tr>";
					}
				
					div += "</table>";
				innerTable +=div;

			log.debug("[ getInnerTable func role id ] : innerTable = "+innerTable);
		}catch(Exception e){
			log.debug("Error >>> "+ e);
		}
		return innerTable;
	}
	
	private void setCriteriaParameter(){
		RoleModel roleMSP = new RoleModel();
		log.debug("Role action : setCriteriaParameter >>> "+getRequest().getParameter("txtRoleName"));
		roleMSP.setRole_name(getRequest().getParameter("txtRoleName"));
		getRoleBean().setRoleMSP(roleMSP);
	}
	
	private Vector getValueListParameters() {
		Vector parameters = new Vector();
		
		log.info("parameters.size() = "+parameters.size());
		RoleModel roleCri = getRoleBean().getRoleMSP();
		if(!"".equals(roleCri.getRole_name())) {
			log.debug("Role name = "+roleCri.getRole_name());
			parameters.add("%"+roleCri.getRole_name().toUpperCase()+"%");
		}
		
		return parameters;
	}
	private String setSQL(RoleModel roleCri){
		StringBuffer sql = new StringBuffer();
		try{
			sql.append(EJDAConstant.SQL.ROLE_SQL);
			if(!"".equals(roleCri.getRole_name())){
					sql.append(" WHERE ");
					if(!"".equals(roleCri.getRole_name()))
						sql.append(" UPPER(ROLE_NAME) like ? AND ");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		sql = removeWasteSQL(sql);
		return sql.toString();
	}
	
	
}

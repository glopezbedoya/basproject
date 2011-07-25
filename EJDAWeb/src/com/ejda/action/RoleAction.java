package com.ejda.action;

import java.sql.SQLException;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.ejda.constant.EJDAConstant;
import com.tcd.ejda.dao.MenuDAO;
import com.tcd.ejda.dao.MenuDAOImpl;
import com.tcd.ejda.dao.RoleDAO;
import com.tcd.ejda.dao.RoleDAOImpl;
import com.tcd.ejda.model.MenuModel;
import com.tcd.ejda.model.RoleFunctionModel;
import com.tcd.ejda.model.RoleModel;

import org.apache.log4j.Logger;

public class RoleAction extends AbstractAction {
	private Logger log = Logger.getLogger(RoleAction.class);
	@Override
	public void clearSessionNotUsed() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean methodAction(String ejdaAction, HttpServletRequest request) {
		// TODO Auto-generated method stub
		log.debug("[ Start : Role Action ]");
		if(ejdaAction.equalsIgnoreCase("doSearch")){
			return doSearch(request);
		}else if(ejdaAction.equalsIgnoreCase("doNew")){
			return doNew(request);
		}else if(ejdaAction.equalsIgnoreCase("doAdd")){
			return doAdd(request);
		}else if(ejdaAction.equalsIgnoreCase("doEdit")){
			return doEdit(request);
		}else if(ejdaAction.equalsIgnoreCase("doUpdate")){
			return doUpdate(request);
		}else if(ejdaAction.equalsIgnoreCase("doDelete")){
			return doDelete(request);
		}
		
		return false;
	}

	private boolean doDelete(HttpServletRequest request) {
		// TODO Auto-generated method stub
		log.debug("[Start : do Delete ]");
		boolean result = false;
		String role_id = request.getParameter("role_id");
		String role_name = request.getParameter("hrole_name");
		log.debug("role id >>> " + role_id);
		RoleDAO role = new RoleDAOImpl();
		try {
			if (role.deleteRole(role_id)){
				Vector mn = new Vector();
				mn = role.selectRole(role_name);
				log.debug("mn >>> " + mn.size());
				request.getSession().setAttribute("returnVC", mn);
				result = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return result;
	}

	private boolean doEdit(HttpServletRequest request) {
		// TODO Auto-generated method stub
		log.debug("[ Start : do Edit ]" );
		Vector vc = new Vector();
		boolean result = false;
		String role_id = request.getParameter("role_id");
//		String role_id = "R35";
		log.debug("== role id : " + role_id);
		String role_name = request.getParameter("hrole_name");
		log.debug("=== role_name >> " +role_name);
		RoleDAO role = new RoleDAOImpl();
		try {
			vc = role.selectEditRole(role_id);
			String returnValue = getInnerTableEdit(vc);
			log.debug("getInnerTableEdit >> " + returnValue);
			request.getSession().setAttribute("returnValUpdate", returnValue);
			request.getSession().setAttribute("hidrolename", role_name);
			result = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	private boolean doSearch(HttpServletRequest request) {
		// TODO Auto-generated method stub
		boolean result = false;
		String role_name = request.getParameter("rolename");
		Vector vc = new Vector();
		RoleDAO role = new RoleDAOImpl();
		try {
			vc = role.selectRole(role_name);
			log.debug("vc >>> " + vc.size());
			request.getSession().setAttribute("returnVC", vc);
			result = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return result;
	}

	private boolean doNew(HttpServletRequest request) {
		// TODO Auto-generated method stub
		boolean result = false;
		Vector vc = new Vector();
		MenuDAO menus = new MenuDAOImpl();
		vc = menus.SearchMenu();
		
		String returnValue = getInnerTable(vc);
		log.debug("returnValue >> " + returnValue);
		request.getSession().setAttribute("returnVal", returnValue);
		result = true;

		return result;
	}

	private boolean doUpdate(HttpServletRequest request) {
		// TODO Auto-generated method stub
		boolean result =false;
		
		String role_name = request.getParameter("rolename");
		String role_fund_id = request.getParameter("role_func_id");
		String role_id = request.getParameter("role_id");
		log.debug("role_name >> "+role_name);
		log.debug("role_fund_id >> "+role_fund_id);
		log.debug("role_id >> "+role_id);
		
		String strInqs = ""; 
		Vector vc = new Vector();
		RoleModel role = new RoleModel();
		role.setRole_id(role_id);
		role.setRole_name(role_name);
		role.setUpdate_by("veena");
		
		strInqs = request.getParameter("func_type");
		log.debug("strinqs >>> " + strInqs);
		if (null!=strInqs){
			String inqsTmp[] = strInqs.split("\\|"); 
			for(int j=0;j<inqsTmp.length;j++){
				log.debug("inqsTmp[j] >>> " + inqsTmp[j]);
				//inqsM001=M001=Y:addM001=M001=Y:updM001=M001=Y:delM001=M001=Y:
				if (null!=inqsTmp[j] && !"".equals(inqsTmp[j])){
					log.debug("inqsTmp[j] >>> " + inqsTmp[j]);
					String temps[] = inqsTmp[j].split(":");
						if (!"".equals(temps[0])){
						RoleFunctionModel rfModel = new RoleFunctionModel();
						//inqsM001=M001=Y
						for (int k=0;k<temps.length; k++){
							log.debug("temp >>> " + temps[k]);
							
							String ftype[]=temps[k].split("=");
							String tmpmenu = "";
	
							rfModel.setMenu_id(ftype[1]);
	
							if (ftype[0].subSequence(0,4).equals("inqs")){
								log.debug("inqs >> " + ftype[2]);
								rfModel.setFunc_inqs(ftype[2]);
								
							}
							if (ftype[0].subSequence(0,3).equals("add")){
								log.debug("add >> " + ftype[2]);
								rfModel.setFunc_add(ftype[2]);
								
							}
							if (ftype[0].subSequence(0,3).equals("upd")){
								log.debug("upd >> " + ftype[2]);
								rfModel.setFunc_update(ftype[2]);
								
							}
							if (ftype[0].subSequence(0,3).equals("del")){
								log.debug("del >> " + ftype[2]);
								rfModel.setFunc_del(ftype[2]);
								
							}
//							rfModel.setCreate_by("veena");
							rfModel.setRole_func_id(role_fund_id);
							rfModel.setRole_id(role_id);
							rfModel.setUpdate_by("veena");
						}
						
						vc.add(rfModel);
					}
				}
			}
		}
		
		request.getSession().setAttribute("returnValUpdate", "");
		RoleDAO rd = new RoleDAOImpl();
		try {
			if (rd.updateRole(role, vc)){
				Vector mn = new Vector();
				mn = rd.selectRole(role_name);
				log.debug("mn >>> " + vc.size());
				request.getSession().setAttribute("returnVC", mn);
				result = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		result=true;
	
		
		request.getSession().setAttribute("returnVal", "");
		return result;
	}

	private boolean doAdd(HttpServletRequest request) {
		// TODO Auto-generated method stub
		boolean result =false;
		
		String role_name = request.getParameter("rolename");
		String strInqs = "";
		Vector vc = new Vector();
		RoleModel role = new RoleModel();
		role.setRole_name(role_name);
		role.setCreate_by("veena");
		role.setUpdate_by("veena");
		
		strInqs = request.getParameter("func_type");
		log.debug("strinqs >>> " + strInqs);
		if (null!=strInqs){
			String inqsTmp[] = strInqs.split("\\|"); 
			for(int j=0;j<inqsTmp.length;j++){
				log.debug("inqsTmp[j] >>> " + inqsTmp[j]);
				//inqsM001=M001=Y:addM001=M001=Y:updM001=M001=Y:delM001=M001=Y:
				if (null!=inqsTmp[j] && !"".equals(inqsTmp[j])){
					log.debug("inqsTmp[j] >>> " + inqsTmp[j]);
					String temps[] = inqsTmp[j].split(":");
						if (!"".equals(temps[0])){
						RoleFunctionModel rfModel = new RoleFunctionModel();
						//inqsM001=M001=Y
						for (int k=0;k<temps.length; k++){
							log.debug("temp >>> " + temps[k]);
							
							String ftype[]=temps[k].split("=");
							String tmpmenu = "";
	
							rfModel.setMenu_id(ftype[1]);
	
							if (ftype[0].subSequence(0,4).equals("inqs")){
								log.debug("inqs >> " + ftype[2]);
								rfModel.setFunc_inqs(ftype[2]);
								
							}
							if (ftype[0].subSequence(0,3).equals("add")){
								log.debug("add >> " + ftype[2]);
								rfModel.setFunc_add(ftype[2]);
								
							}
							if (ftype[0].subSequence(0,3).equals("upd")){
								log.debug("upd >> " + ftype[2]);
								rfModel.setFunc_update(ftype[2]);
								
							}
							if (ftype[0].subSequence(0,3).equals("del")){
								log.debug("del >> " + ftype[2]);
								rfModel.setFunc_del(ftype[2]);
								
							}
							rfModel.setCreate_by("veena");
							rfModel.setUpdate_by("veena");
						}
						
						vc.add(rfModel);
					}
				}
			}
		}
		RoleDAO rd = new RoleDAOImpl();
		try {
			if (rd.insertRole(role, vc)){
				request.getSession().setAttribute("returnVal", "");	
				result = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		result=true;
	
		
		request.getSession().setAttribute("returnVal", "");
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
						
					if(vc != null && vc.size()>0){	
						for(int j=0;j<vc.size();j++){
							MenuModel model = (MenuModel)vc.get(j);
							div += "<tr>";
							div += "<td width=\"100\" align = \"left\" ><font class=\"text\">"+model.getMenu_name()+"</font></td>";
							div += "<input type = \"hidden\"name = \"menuid\" value=\""+model.getMenu_id()+"\">";
							div += "<input type = \"hidden\"name = \"status\" value=\""+model.getMenu_status()+"\">";
							if (null!= model.getMenu_status() && !model.getMenu_owner().equals("D")){
								div += "<td width=\"100\"><input type=\"checkbox\" name=\"inqs"+model.getMenu_id()+"\" id=\"inqs\"value=\""+model.getMenu_id()+"\" /><font class=\"text\">Inquiry Data</font></td>";
								div += "<td width=\"100\"><input type=\"checkbox\" name=\"add"+model.getMenu_id()+"\" id=\"add\"value=\""+model.getMenu_id()+"\"  /><font class=\"text\">Add Data</font></td>";
								div += "<td width=\"100\"><input type=\"checkbox\" name=\"upd"+model.getMenu_id()+"\" id=\"upd\"value=\""+model.getMenu_id()+"\"  /><font class=\"text\">Update Data</font></td>";
								div += "<td width=\"100\"><input type=\"checkbox\" name=\"del"+model.getMenu_id()+"\" id=\"del\"value=\""+model.getMenu_id()+"\" /><font class=\"text\">Delete Data</font></td>";
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
						
					if(vc != null && vc.size()>0){	
						for(int j=0;j<vc.size();j++){
							RoleFunctionModel model = (RoleFunctionModel)vc.get(j);
							div += "<tr>";
							div += "<td width=\"100\" align = \"left\" ><font class=\"text\">"+model.getMenu_name()+"</font></td>";
							div += "<input type = \"hidden\"name = \"menuid\" value=\""+model.getMenu_id()+"\">";
							div += "<input type = \"hidden\"name = \"status\" value=\""+model.getMenu_status()+"\">";
							div += "<input type = \"hidden\"name = \"role_func_id\" value=\""+model.getRole_func_id()+"\">";
							div += "<input type = \"hidden\"name = \"role_id\" value=\""+model.getRole_id()+"\">";
							if (null!= model.getMenu_status() && !model.getMenu_status().equals("C")){
								div += "<td width=\"100\"><input type=\"checkbox\" name=\"inqs"+model.getMenu_id()+"\" id=\"inqs\"value=\""+model.getFunc_inqs()+"\" /><font class=\"text\">Inquiry Data</font></td>";
								div += "<td width=\"100\"><input type=\"checkbox\" name=\"add"+model.getMenu_id()+"\" id=\"add\"value=\""+model.getFunc_add()+"\"  /><font class=\"text\">Add Data</font></td>";
								div += "<td width=\"100\"><input type=\"checkbox\" name=\"upd"+model.getMenu_id()+"\" id=\"upd\"value=\""+model.getFunc_update()+"\"  /><font class=\"text\">Update Data</font></td>";
								div += "<td width=\"100\"><input type=\"checkbox\" name=\"del"+model.getMenu_id()+"\" id=\"del\"value=\""+model.getFunc_del()+"\" /><font class=\"text\">Delete Data</font></td>";
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
}
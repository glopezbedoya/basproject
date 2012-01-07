package com.ejda.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ejda.action.CheckUsernamePasswordAction;
import com.tcd.ejda.model.MenuModel;
import com.tcd.ejda.dao.MenuDAO;
import com.tcd.ejda.dao.MenuDAOImpl;
import org.apache.log4j.Logger;
/**
 * Servlet implementation class MenuServlet
 */
public class MenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(MenuServlet.class);  
  	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		log.debug("[ veena test servlet]");
		
		MenuModel menuModel = new MenuModel();
		
		log.debug("menuModel.setMenu_id : " + menuModel.getMenu_id());

		MenuDAO menus = new MenuDAOImpl();
		Vector mn = new Vector();
		mn = menus.SearchMenu();
		
		String returnValue = getInnerTable(mn);
//		log.debug("--- mn : " + mn);
		request.setAttribute("MyMenu",returnValue);
		
		
	}
	
	private String getInnerTable(Vector vc){
		String innerTable = "";
		int count=0;
		String temp="";
		try{
			
				String div = "<ul id=\"MenuBar1\" class=\"MenuBarHorizontal\">";	
					if(vc != null && vc.size()>0){	
						div += "<li><a href=\"./index2.jsp\">HOME</a></li>";
						for(int j=0;j<vc.size();j++){
							MenuModel model = (MenuModel)vc.get(j);
							if (count!=0 && (model.getMenu_status().equals("A") || model.getMenu_status().equals("C"))){
								div += "</ul></li>";
								count=0;
							}
							if (null!=model.getMenu_status() && model.getMenu_status().equals("A")){
								
								div += "<li><a href=\"#\">" + model.getMenu_name() + "</a></li>";	
								
							}
							if (null!=model.getMenu_status() && model.getMenu_status().equals("C")){
								
								div += "<li><a class=\"MenuBarItemSubmenu\" href=\"#\"> " + model.getMenu_name() + "</a>";	
								
							}
							
							if (null!=model.getMenu_status() && model.getMenu_status().equals("D")){
								if (count==0){
									div += "<ul>";
									
								}
								div += "<li><a href=\"./EJDAControler?screenName=EJDA"+model.getMenu_id()+".jsp&ejdaAction=EJDA"+model.getMenu_id()+"\">" + model.getMenu_name() + "</a></li>";

								count=1;
								
							}
							if (null!=model.getMenu_status() && model.getMenu_status().equals("E")){
								
								div += "<li><a href=\"./EJDAControler?screenName=EJDA"+model.getMenu_id()+".jsp&ejdaAction=EJDA"+model.getMenu_id()+"\">" + model.getMenu_name() + "</a></li>";
								
							}
//							if(model.getMenu_owner().equals("C")){
//								div += "</li>";
//							}
							
						}
						if (count!=0){
							div += "</ul>";
							div += "</li>";
//							div += "</ul>";
						}
						div += "<li><a href=\"./EJDAControler?screenName=index.jsp&ejdaAction=Logout\">LOGOUT</a></li>";
						div += "</ul>";
					}else{
								div += 		"			<tr class=\"ROW\">"+
						"									<td colspan=\"6\" height=\"19\" align=\"center\" class=\"bu2\">No record found.</td>"+
						"								</tr>";
					}
				
				
				innerTable +=div;

//			log.debug("[ getInnerTable ] : innerTable = "+innerTable);
		}catch(Exception e){
			log.debug("Error >>> "+ e);
		}
		return innerTable;
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

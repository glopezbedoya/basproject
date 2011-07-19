package com.ejda.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tcd.ejda.model.MenuModel;
import com.tcd.ejda.dao.MenuDAO;
import com.tcd.ejda.dao.MenuDAOImpl;

/**
 * Servlet implementation class MenuServlet
 */
public class MenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("[ veena test servlet]");
		
		MenuModel menuModel = new MenuModel();
		
		System.out.println("menuModel.setMenu_id : " + menuModel.getMenu_id());

		MenuDAO menus = new MenuDAOImpl();
		Vector mn = new Vector();
		mn = menus.SearchMenu();
		
		String returnValue = getInnerTable(mn);
		System.out.println("--- mn : " + mn);
		request.setAttribute("MyMenu",returnValue);
		
		
	}
	
	private String getInnerTable(Vector vc){
		String innerTable = "";
		int count=0;
		String temp="";
		try{
			
				String div = "<ul id=\"MenuBar1\" class=\"MenuBarHorizontal\">";	
					if(vc != null && vc.size()>0){	
						for(int j=0;j<vc.size();j++){
							MenuModel model = (MenuModel)vc.get(j);
							
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
				
								div += "<li><a href=\"#\">" + model.getMenu_name() + "</a></li>";
								count=1;
								
							}
							if(model.getMenu_owner().equals("C")){
								div += "</li>";
							}
													
								
						}
						if (count!=0){
							div += "</ul>";
							div += "</li>";
							div += "</ul>";
						}
						
					}else{
								div += 		"			<tr class=\"ROW\">"+
						"									<td colspan=\"6\" height=\"19\" align=\"center\" class=\"bu2\">No record found.</td>"+
						"								</tr>";
					}
				
				
				innerTable +=div;

			System.out.println("[ getInnerTable ] : innerTable = "+innerTable);
		}catch(Exception e){
			System.out.println("Error >>> "+ e);
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

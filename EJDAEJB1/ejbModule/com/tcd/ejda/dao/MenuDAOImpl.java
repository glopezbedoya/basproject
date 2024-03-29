package com.tcd.ejda.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.tcd.ejda.connection.JDBCServiceLocator;
import com.tcd.ejda.model.MenuModel;
public class MenuDAOImpl implements MenuDAO {
	JDBCServiceLocator db = new JDBCServiceLocator();
	private Logger log = Logger.getLogger(MenuDAOImpl.class);  
	@Override
	public Vector SearchMenu(){
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Vector searchMenu = new Vector();
		
		try {
			conn = db.getConnection();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		StringBuffer sqlupd = new StringBuffer();
//		sqlupd.append("INSERT INTO EJDA_MENU()")
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT MENU_ID,MENU_NAME,MENU_STATUS,DESCRIPTION,CREATE_DATE,CREATE_BY,UPDATE_DATE,UPDATE_BY,MENU_OWNER,MENU_SORT,MENU_LINKED FROM JDA_MENU ORDER BY MENU_SORT ");
		log.debug("sql >> " + sql.toString());
		try {
		ps = conn.prepareStatement(sql.toString());
		
		rs = ps.executeQuery();
		while (rs.next()) {
			MenuModel menuModel = new MenuModel();
			
			menuModel.setMenu_id(rs.getString("MENU_ID"));
			menuModel.setMenu_name(rs.getString("MENU_NAME"));
			menuModel.setMenu_status(rs.getString("MENU_STATUS"));
			menuModel.setMenu_description(rs.getString("DESCRIPTION"));
			menuModel.setCreate_date(rs.getDate("CREATE_DATE"));
			menuModel.setCreate_by(rs.getString("CREATE_BY"));
			menuModel.setUpdate_date(rs.getDate("UPDATE_DATE"));
			menuModel.setUpdate_by(rs.getString("UPDATE_BY"));
			menuModel.setMenu_owner(rs.getString("MENU_OWNER"));
			menuModel.setMenu_sort(rs.getInt("MENU_SORT"));
			menuModel.setMenu_linked(rs.getString("MENU_LINKED"));
			searchMenu.add(menuModel);
//			log.debug("ID >>> " + rs.getString("MENU_ID") + ":"
//					+ rs.getString("MENU_NAME") + ":" + rs.getString("MENU_OWNER"));
		}	
//		
		log.debug("Connection session: ");
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if (conn != null)
					conn.commit();
			} catch (Exception e) {
			}
			try {
				if (rs != null)
					rs.close();
				rs = null;
			} catch (Exception e) {
			}
			try {
				if (ps != null)
					ps.close();
				ps = null;
			} catch (Exception e) {
			}
			try {
				if (conn != null)
					conn.close();
				conn = null;
			} catch (Exception e) {
				
			}
		}
		return searchMenu;
	}

	

}

package com.tcd.ejda.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.tcd.ejda.connection.ConnectionService;
import com.tcd.ejda.connection.JDBCServiceLocator;
import com.tcd.ejda.connection.JDBCConnection;
import com.tcd.ejda.model.MenuModel;
public class MenuDAOImpl implements MenuDAO {
	JDBCConnection db = new JDBCConnection();
	@Override
	public Vector SearchMenu(){
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Vector searchMenu = new Vector();
		
		conn = db.getConnection();
		StringBuffer sqlupd = new StringBuffer();
//		sqlupd.append("INSERT INTO EJDA_MENU()")
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT MENU_ID,MENU_NAME,MENU_STATUS,DESCRIPTION,CREATE_DATE,CREATE_BY,UPDATE_DATE,UPDATE_BY,MENU_OWNER,MENU_SORT FROM JDA_MENU ORDER BY MENU_SORT ");
		System.out.println("sql >> " + sql.toString());
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
			searchMenu.add(menuModel);
			System.out.println("ID >>> " + rs.getString("MENU_ID") + ":"
					+ rs.getString("MENU_NAME") + ":" + rs.getString("MENU_OWNER"));
		}	
//		
		System.out.println("Connection session: ");
		
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

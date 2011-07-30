package com.tcd.ejda.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.tcd.ejda.connection.JDBCConnection;
import com.tcd.ejda.model.RoleMenuModel;
import com.tcd.ejda.model.RoleModel;

public class RoleMenuDAOImpl implements RoleMenuDAO {
	private Logger log = Logger.getLogger(RoleMenuDAOImpl.class);
	JDBCConnection db = new JDBCConnection();
	@Override
	public Vector getRoleMenu(String user_name) throws SQLException {
		Vector vc = new Vector();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
				
		conn = db.getConnection();
		StringBuffer sql = new StringBuffer();
		
		
		boolean isSuccess=false;
		
		
//		sql.append("SELECT JU.JDA_ID, JU.IV_USER, JU.USER_NAME, JU.DEPARTMENT, JU.USER_STATUS, JR.ROLE_ID, ");
//		sql.append("JF.ROLE_FUN_ID, JF.MENU_ID, JF.FUN_ADD, JF.FUN_UPDATE, JF.FUN_DELETE, JF.FUN_INQUIRY ");
//		sql.append("FROM JDA_USER JU INNER JOIN JDA_USER_ROLE JR ON JU.JDA_ID = JR.JDA_ID ");
//		sql.append("INNER JOIN JDA_ROLE_FUNCTION JF ON JF.ROLE_ID = JR.ROLE_ID WHERE UPPER(JU.JDA_ID) = ? ");
		sql.append("SELECT JF.MENU_ID , max(JF.FUN_ADD) AS FUN_ADD, max(JF.FUN_UPDATE) AS FUN_UPDATE, max(JF.FUN_DELETE) AS FUN_DELETE, ");
		sql.append("max(JF.FUN_INQUIRY) AS FUN_INQUIRY FROM JDA_USER JU INNER JOIN JDA_USER_ROLE JR ON JU.JDA_ID = JR.JDA_ID ");
		sql.append("INNER JOIN JDA_ROLE_FUNCTION JF ON JF.ROLE_ID = JR.ROLE_ID WHERE UPPER(JU.USER_NAME) = ?  GROUP BY JF.MENU_ID order by menu_id");
		
		try {
			log.debug("sql >> " + sql.toString());
			ps = conn.prepareStatement(sql.toString());
		
			ps.setString(1,user_name.toUpperCase());
			
			
			rs = ps.executeQuery();
			
			while (rs.next()){
				RoleMenuModel rm = new RoleMenuModel();
//				rm.setJDA_ID(rs.getString("JDA_ID"));
//				rm.setIV_USER(rs.getString("IV_USER"));
//				rm.setUSER_NAME(rs.getString("USER_NAME"));
//				rm.setDEPARTMENT(rs.getString("DEPARTMENT"));
//				rm.setUSER_STATUS(rs.getString("USER_STATUS"));
//				rm.setROLE_ID(rs.getString("ROLE_ID"));
//				rm.setROLE_FUN_ID(rs.getString("ROLE_FUN_ID"));
				rm.setMENU_ID(rs.getString("MENU_ID"));
				rm.setFUN_ADD(rs.getString("FUN_ADD"));
				rm.setFUN_UPDATE(rs.getString("FUN_UPDATE"));
				rm.setFUN_DELETE(rs.getString("FUN_DELETE"));
				rm.setFUN_INQUIRY(rs.getString("FUN_INQUIRY"));
				vc.add(rm);
			}
			
			log.debug("[selsectRole : result ] "+vc);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
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
		
		return vc;
	}

}

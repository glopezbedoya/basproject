package com.tcd.ejda.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.tcd.ejda.connection.JDBCConnection;
import com.tcd.ejda.model.RoleFunctionModel;
import com.tcd.ejda.model.RoleModel;

public class RoleDAOImpl implements RoleDAO {

	JDBCConnection db = new JDBCConnection();
	@Override
	public boolean insertRole(RoleModel roleModel, Vector vc) throws SQLException{
		// TODO Auto-generated method stub
		boolean blSuccess=false;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		conn = db.getConnection();
		StringBuffer sql = new StringBuffer();
		StringBuffer sql1 = new StringBuffer();
		conn.setAutoCommit(false); 
		
		try {
			sql.append("INSERT INTO JDA_ROLE (ROLE_ID, ROLE_NAME, CREATE_DATE, CREATE_BY, UPDATE_DATE, UPDATE_BY) ");
			sql.append("VALUES(?, ?, ?, ?, ?, ? )");
			
			ps = conn.prepareStatement(sql.toString());
			int seq=0;
			ps.setString(seq++, roleModel.getRole_id());
			ps.setString(seq++, roleModel.getRole_name());
			ps.setDate(seq++, roleModel.getCreate_date());
			ps.setString(seq++, roleModel.getCreate_by());
			ps.setDate(seq++, roleModel.getUpdate_date());
			ps.setString(seq++, roleModel.getUpdate_by());
			
			int rsInt = ps.executeUpdate();
			if (rsInt > 0) {
				blSuccess = true;
			}
			
			sql1.append("INSERT INTO JDA_ROLE_FUNCTION(ROLE_FUN_ID, ROLE_ID, MENU_ID, FUN_ADD, FUN_UPDATE, FUN_DELETE, ");
			sql1.append("FUN_INQUIRY, CREATE_DATE, CREATE_BY UPDATE_DATE UPDATE_BY) ");
			sql1.append("VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
			
			ps=conn.prepareStatement(sql1.toString());
			for (int i=0;i<vc.size();i++){
				int seqq=0;
				RoleFunctionModel rfm = (RoleFunctionModel)vc.get(i);
				ps.setString(seqq++, rfm.getRole_func_id());
				ps.setString(seqq++, rfm.getRole_id());
				ps.setString(seqq++, rfm.getMenu_id());
				ps.setString(seqq++, rfm.getFunc_add());
				ps.setString(seqq++, rfm.getFunc_update());
				ps.setString(seqq++, rfm.getFunc_del());
				ps.setString(seqq++, rfm.getFunc_inqs());
				ps.setDate(seqq++, rfm.getCreate_date());
				ps.setString(seqq++, rfm.getCreate_by());
				ps.setDate(seqq++, rfm.getUpdate_date());
				ps.setString(seqq++, rfm.getUpdate_by());
				
				int rsInt1 = ps.executeUpdate();
				if (rsInt1 > 0) {
					blSuccess = true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
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
		
		return blSuccess;
	}
	@Override
	public Vector selsectRole(String RoleName) throws SQLException {
		// TODO Auto-generated method stub
		Vector vc = new Vector();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
				
		conn = db.getConnection();
		StringBuffer sqlusr = new StringBuffer();
		
		
		boolean isSuccess=false;
		
		sqlusr.append("SELECT  ROLE_ID, ROLE_NAME, CREATE_DATE, CREATE_BY, UPDATE_DATE, UPDATE_BY FROM JDA_ROLE WHERE UPPER(ROLE_NAME) = ? ");
		try {
			System.out.println("sqlusr >> " + sqlusr.toString());
			ps = conn.prepareStatement(sqlusr.toString());
			ps.setString(1,RoleName.toUpperCase());
			
			rs = ps.executeQuery();
			while (rs.next()){
				RoleModel rm = new RoleModel();
				rm.setRole_id(rs.getString("ROLE_ID"));
				rm.setRole_id(rs.getString("ROLE_NAME"));
				rm.setCreate_date(rs.getDate("CREATE_DATE"));
				rm.setRole_id(rs.getString("CREATE_BY"));
				rm.setUpdate_date(rs.getDate("UPDATE_DATE"));
				rm.setRole_id(rs.getString("UPDATE_BY"));
				vc.add(rm);
			}
			
			System.out.println("[selsectRole : result ] "+vc);
		
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

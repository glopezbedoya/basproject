package com.tcd.ejda.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.tcd.ejda.connection.JDBCConnection;
import com.tcd.ejda.model.UserModel;

public class UserDAOImpl implements UserDAO {
	

	JDBCConnection db = new JDBCConnection();
	
	@Override
	public String checkUsernamePassword(String ivUser, String username, String pwd) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Vector vc = new Vector();
		
		conn = db.getConnection();
		StringBuffer sqlusr = new StringBuffer();
		
		String result="STATUS";
		boolean isSuccess=false;
		
		sqlusr.append("SELECT  IV_USER, USER_NAME, USER_COUNT FROM JDA_USER WHERE UPPER(USER_NAME) = ? ");
		try {
			System.out.println("sqlusr >> " + sqlusr.toString());
			ps = conn.prepareStatement(sqlusr.toString());
			ps.setString(1,username.toUpperCase());
			
			rs = ps.executeQuery();
			if (rs.next()){
				if (rs.getInt("USER_COUNT") >= 3){
					isSuccess = lockedUser(rs.getString("IV_USER"),rs.getString("USER_NAME"), conn);
				}else{
					result = checkPassword(rs.getString("USER_NAME"), pwd, conn);
				}
				
			}
			
			System.out.println("[checkUsernamePassword : result ] "+result);
		
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
		return result;
	}
	private String checkPassword(String user_name, String pwd, Connection conn){
		
		StringBuffer sql = new StringBuffer();
		String result = "";
		boolean blSuccess = false;
		Vector vc = new Vector();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		sql.append("SELECT  IV_USER, USER_NAME, FIRST_NAME, LAST_NAME, DEPARTMENT, USER_STATUS, ");
		sql.append("USER_IP, USER_COUNT, EFFECTIVE_DATE, EXPIRY_DATE, CREATE_DATE, CREATE_BY, UPDATE_DATE FROM JDA_USER ");
		sql.append("WHERE UPPER(USER_NAME) = ? AND PASSWORD = ? ");
		
		System.out.println("sql >> " + sql.toString());
		try {
		ps = conn.prepareStatement(sql.toString());
		System.out.println("---- 1 " + user_name.toUpperCase() + ":" + pwd);
		ps.setString(1, user_name.toUpperCase());
		ps.setString(2, pwd);
		System.out.println("---- 2 " + user_name.toUpperCase() + ":" + pwd);
		rs = ps.executeQuery();
		
		if (rs.next()) {
			
			if(rs.getString("USER_STATUS").equals("N")){ //Not activate (can use)
				UserModel um = new UserModel();
				um.setIV_USER(rs.getString("IV_USER"));
				um.setUSERNAME(rs.getString("USER_NAME"));
				um.setFIRSTNAME(rs.getString("FIRST_NAME"));
				vc.add(um);
				System.out.println("IV_USER >>> " + rs.getString("IV_USER") + ":"
						+ rs.getString("IV_USER") + ":" + rs.getString("IV_USER"));
				
				if (updateStatusActivate(rs.getString("IV_USER"),rs.getString("USER_NAME"), conn)){
					result = "N";
				}

			}else if(rs.getString("USER_STATUS").equals("A")){ //Activate (can't use)
				result = "A";//Activate
			}else {
				result = "L";//Locked
			}
		
		}else{
			result = "W"; //Wring
			blSuccess = updateCountUser(user_name.toUpperCase(),conn);
		}	

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

		}
		return result;	
	}
	private boolean updateStatusActivate(String iv_user,String user_name, Connection conn){
		boolean blSuccess = false;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE JDA_USER SET USER_STATUS = 'A', USER_COUNT = 0 WHERE IV_USER = ? AND UPPER(USER_NAME) = ?");
		
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, iv_user);
			ps.setString(2, user_name.toUpperCase());
			
			int rsInt = ps.executeUpdate();
			if (rsInt > 0) {
				blSuccess = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch blockd
			e.printStackTrace();
		} finally {
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

		}
		
		
		return blSuccess;
	}
	private boolean updateCountUser(String user_name, Connection conn){
		boolean blSuccess = false;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE JDA_USER SET USER_COUNT = USER_COUNT + 1 WHERE UPPER(USER_NAME) = ? ");
		System.out.println("sql updateCountUser >>> " + sql);
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, user_name.toUpperCase());
			
			int rsInt = ps.executeUpdate();
			if (rsInt > 0) {
				blSuccess = true;
			}
			System.out.println(" rsInt = " +rsInt );
		} catch (SQLException e) {
			// TODO Auto-generated catch blockd
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					System.out.println("commit");
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

		}
		
		
		return blSuccess;
	}
	private boolean lockedUser(String iv_user,String user_name, Connection conn){
		boolean blSuccess = false;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE JDA_USER SET USER_STATUS = 'L', USER_COUNT = 0 WHERE IV_USER = ? AND UPPER(USER_NAME) = ? ");
		System.out.println("lockedUser >> " + sql);
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, iv_user);
			ps.setString(2, user_name.toUpperCase());
			
			int rsInt = ps.executeUpdate();
			if (rsInt > 0) {
				blSuccess = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch blockd
			e.printStackTrace();
		} finally {
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

		}
		
		
		return blSuccess;
	}
}

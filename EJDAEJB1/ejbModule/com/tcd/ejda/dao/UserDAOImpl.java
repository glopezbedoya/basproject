package com.tcd.ejda.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.tcd.ejda.connection.JDBCConnection;
import com.tcd.ejda.model.RoleModel;
import com.tcd.ejda.model.UserRoleModel;
import com.tcd.ejda.model.UsrModel;
import org.apache.log4j.Logger;

public class UserDAOImpl implements UserDAO {
	

	JDBCConnection db = new JDBCConnection();
	private Logger log = Logger.getLogger(UserDAOImpl.class);
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
		String status="";
		sqlusr.append("SELECT  IV_USER, USER_NAME, USER_COUNT, USER_STATUS FROM JDA_USER WHERE UPPER(USER_NAME) = ? ");
		try {
			log.debug("sqlusr >> " + sqlusr.toString());
			ps = conn.prepareStatement(sqlusr.toString());
			ps.setString(1,username.toUpperCase());
			
			rs = ps.executeQuery();
			if (rs.next()){
				
				if (!"".equals(rs.getString("USER_STATUS")) && !"L".equals(rs.getString("USER_STATUS"))){
					
				
					if (rs.getInt("USER_COUNT") >= 3){
						isSuccess = lockedUser(rs.getString("IV_USER"),rs.getString("USER_NAME"), conn);
						result = "L";
					}else{
						result = checkPassword(rs.getString("USER_NAME"), pwd, conn);
					}
				}else{
					result = rs.getString("USER_STATUS");
				}
			}
			
			log.debug("[checkUsernamePassword : result ] "+result);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("checkUsernamePassword",e);
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
		
		log.debug("sql >> " + sql.toString());
		try {
		ps = conn.prepareStatement(sql.toString());
		log.debug("---- 1 " + user_name.toUpperCase() + ":" + pwd);
		ps.setString(1, user_name.toUpperCase());
		ps.setString(2, pwd);
		log.debug("---- 2 " + user_name.toUpperCase() + ":" + pwd);
		rs = ps.executeQuery();
		
		if (rs.next()) {
			
			if(rs.getString("USER_STATUS").equals("N")){ //Not activate (can use)
				UsrModel um = new UsrModel();
				um.setIV_USER(rs.getString("IV_USER"));
				um.setUSERNAME(rs.getString("USER_NAME"));
				um.setFIRSTNAME(rs.getString("FIRST_NAME"));
				vc.add(um);
				log.debug("IV_USER >>> " + rs.getString("IV_USER") + ":"
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
			log.error("checkPassword",e);
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
			log.error("updateStatusActivate",e);
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
		log.debug("sql updateCountUser >>> " + sql);
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, user_name.toUpperCase());
			
			int rsInt = ps.executeUpdate();
			if (rsInt > 0) {
				blSuccess = true;
			}
			log.debug(" rsInt = " +rsInt );
		} catch (SQLException e) {
			// TODO Auto-generated catch blockd
			e.printStackTrace();
			log.error("updateCountUser",e);
		} finally {
			try {
				if (conn != null)
					log.debug("commit");
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
		log.debug("lockedUser >> " + sql);
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
			log.error("lockedUser",e);
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
	@Override
	public boolean addNewUser(UsrModel usrmodel, Vector role) throws SQLException {
		// TODO Auto-generated method stub
		boolean blSuccess = false;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		conn = db.getConnection();
		String jda_id = "";
		//conn.setAutoCommit(false);
		log.debug("Strat : add New User Result >> " + usrmodel.getIV_USER());
		log.debug("Strat : add New User Result >> " + usrmodel.getUSERNAME());
		log.debug("Strat : add New User Result >> " + usrmodel.getPWD());
		log.debug("Strat : add New User Result >> " + usrmodel.getFIRSTNAME());
		log.debug("Strat : add New User Result >> " + usrmodel.getLASTNAME());
		log.debug("Strat : add New User Result >> " + usrmodel.getDEPARTMENT());
		log.debug("Strat : add New User Result >> " + usrmodel.getUSER_IP());
		log.debug("Strat : add New User Result >> " + usrmodel.getEFFECTIVE_DATE());
		log.debug("Strat : add New User Result >> " + usrmodel.getEXPIRY_DATE());
		log.debug("Strat : add New User Result >> " + usrmodel.getCreate_by());
		log.debug("Strat : add New User Result >> " + usrmodel.getUpdate_by());
	
		log.debug("role.size : " + role.size());
		try {
			StringBuffer sqlSeq = new StringBuffer();
			sqlSeq.append("select USER_SEQ.nextval as SEQ from dual ");
			
			log.debug("sql insert>>>>>>>>>>>>  " + sqlSeq.toString());
			ps = conn.prepareStatement(sqlSeq.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				
				jda_id = "J" + rs.getString("SEQ");

			}
			rs.close();
			
			StringBuffer sql = new StringBuffer();
			
			sql.append("INSERT INTO JDA_USER (JDA_ID, IV_USER, USER_NAME, PASSWORD, FIRST_NAME, LAST_NAME, DEPARTMENT, ");
			sql.append("USER_STATUS, USER_IP, USER_COUNT, EFFECTIVE_DATE, EXPIRY_DATE, CREATE_DATE, CREATE_BY, UPDATE_DATE, UPDATE_BY)");
			sql.append("VALUES(?, ?, ?, ?, ?, ?, ?, 'N', ?, 0, ?, ?, SYSDATE, ?, SYSDATE, ?) ");
					
			log.debug("sql addNewUser 1>>> " + sql);
			ps = conn.prepareStatement(sql.toString());
			
			int seq=1;
			ps.setString(seq++, jda_id);
			ps.setString(seq++, usrmodel.getIV_USER());
			ps.setString(seq++, usrmodel.getUSERNAME());
			ps.setString(seq++, usrmodel.getPWD());
			ps.setString(seq++, usrmodel.getFIRSTNAME());
			ps.setString(seq++, usrmodel.getLASTNAME());
			ps.setString(seq++, usrmodel.getDEPARTMENT());
			ps.setString(seq++, usrmodel.getUSER_IP());
			ps.setDate(seq++, new Date(usrmodel.getEFFECTIVE_DATE().getTime()));
			ps.setDate(seq++, new Date(usrmodel.getEXPIRY_DATE().getTime()));
			ps.setString(seq++, usrmodel.getCreate_by());
			ps.setString(seq++, usrmodel.getUpdate_by());
			
			int rsInt = ps.executeUpdate();
			if (rsInt > 0) {
				blSuccess = true;
			}
			
			StringBuffer sql1 = new StringBuffer();
			sql1.append("INSERT INTO JDA_USER_ROLE (JDA_ID, IV_USER, ROLE_ID, CREATE_DATE, CREATE_BY, UPDATE_DATE, UPDATE_BY )");
			sql1.append("VALUES(?, ?, ?, SYSDATE, ?, SYSDATE, ?)");
			log.debug("sql1 add New user >>> " + sql1.toString());
			ps = conn.prepareStatement(sql1.toString());
			
			for (int i=0;i<role.size();i++){
				int seq1=1;
				
				UserRoleModel um = (UserRoleModel)role.get(i);
				log.debug("role result >> "+um.getIv_user());
				log.debug("role result >> "+um.getRole_id());
				log.debug("role result >> "+um.getCreate_by());
				log.debug("role result >> "+um.getUpdate_by());
				ps.setString(seq1++, jda_id);
				ps.setString(seq1++, um.getIv_user());
				ps.setString(seq1++, um.getRole_id());
				ps.setString(seq1++, um.getCreate_by());
				ps.setString(seq1++, um.getUpdate_by());
				
				int rsInt1 = ps.executeUpdate();
				if (rsInt1 > 0) {
					blSuccess = true;
				}
			}
			log.debug(" rsInt = " +rsInt );
		} catch (SQLException e) {
			// TODO Auto-generated catch blockd
			e.printStackTrace();
			log.error("addNewUser",e);
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
	@Override
	public boolean updateUser(UsrModel usrmodel, Vector role)
			throws SQLException {
		boolean blSuccess = false;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		conn = db.getConnection();
		//conn.setAutoCommit(false);
		log.debug("Strat : add New User Result >> " + usrmodel.getIV_USER());
		log.debug("Strat : add New User Result >> " + usrmodel.getUSERNAME());
		log.debug("Strat : add New User Result >> " + usrmodel.getPWD());
		log.debug("Strat : add New User Result >> " + usrmodel.getFIRSTNAME());
		log.debug("Strat : add New User Result >> " + usrmodel.getLASTNAME());
		log.debug("Strat : add New User Result >> " + usrmodel.getDEPARTMENT());
		log.debug("Strat : add New User Result >> " + usrmodel.getUSER_IP());
		log.debug("Strat : add New User Result >> " + usrmodel.getEFFECTIVE_DATE());
		log.debug("Strat : add New User Result >> " + usrmodel.getEXPIRY_DATE());
		log.debug("Strat : add New User Result >> " + usrmodel.getCreate_by());
		log.debug("Strat : add New User Result >> " + usrmodel.getUpdate_by());
	
		log.debug("role.size : " + role.size());
		try {
			StringBuffer sql = new StringBuffer();
			
			sql.append("UPDATE JDA_USER SET IV_USER = ?, USER_NAME = ?, FIRST_NAME = ?, LAST_NAME = ?, ");
			sql.append("DEPARTMENT = ?, USER_IP = ?, USER_COUNT = 0, EFFECTIVE_DATE = ?, EXPIRY_DATE = ?, ");
			sql.append("UPDATE_DATE = SYSDATE, UPDATE_BY = ? ");
			if (null!=usrmodel.getPWD() && !"".equals(usrmodel.getPWD())){
				sql.append(", PASSWORD = ? ");
			}
			if (null!=usrmodel.getUSER_STATUS() && !"".equals(usrmodel.getUSER_STATUS())){
				sql.append(", USER_STATUS = ? ");
			}
			sql.append(" WHERE JDA_ID = ? ");
			log.debug("sql addNewUser 1>>> " + sql);
			ps = conn.prepareStatement(sql.toString());
			
			int seq=1;
			
			ps.setString(seq++, usrmodel.getIV_USER());
			ps.setString(seq++, usrmodel.getUSERNAME());
			ps.setString(seq++, usrmodel.getFIRSTNAME());
			ps.setString(seq++, usrmodel.getLASTNAME());
			ps.setString(seq++, usrmodel.getDEPARTMENT());
			ps.setString(seq++, usrmodel.getUSER_IP());
			ps.setDate(seq++, new Date(usrmodel.getEFFECTIVE_DATE().getTime()));
			ps.setDate(seq++, new Date(usrmodel.getEXPIRY_DATE().getTime()));
			ps.setString(seq++, usrmodel.getUpdate_by());
			if (null!=usrmodel.getPWD() && !"".equals(usrmodel.getPWD())){
				ps.setString(seq++, usrmodel.getPWD());
			}
			if (null!=usrmodel.getUSER_STATUS() && !"".equals(usrmodel.getUSER_STATUS())){
				ps.setString(seq++, usrmodel.getUSER_STATUS());
			}
			ps.setString(seq++, usrmodel.getJda_id());
			
			int rsInt = ps.executeUpdate();
			if (rsInt > 0) {
				blSuccess = true;
			}
			StringBuffer sqldel = new StringBuffer();
			sqldel.append("DELETE FROM JDA_USER_ROLE WHERE JDA_ID = ? ");
			log.debug("sql JDA_USER_ROLE >> " + sqldel.toString());
			ps = conn.prepareStatement(sqldel.toString());
			
			ps.setString(1, usrmodel.getJda_id());
			
			int rsInt2 = ps.executeUpdate();
			if (rsInt2 > 0) {
				blSuccess = true;
			}
			log.debug("rsInt2 >> " + rsInt2);
			
			StringBuffer sql1 = new StringBuffer();
			sql1.append("INSERT INTO JDA_USER_ROLE (JDA_ID, IV_USER, ROLE_ID, CREATE_DATE, CREATE_BY, UPDATE_DATE, UPDATE_BY )");
			sql1.append("VALUES(?, ?, ?, SYSDATE, ?, SYSDATE, ?)");
			log.debug("sql1 add New user >>> " + sql1.toString());
			ps = conn.prepareStatement(sql1.toString());
			
			for (int i=0;i<role.size();i++){
				int seq1=1;
				
				UserRoleModel um = (UserRoleModel)role.get(i);
				log.debug("role result >> "+um.getIv_user());
				log.debug("role result >> "+um.getRole_id());
				log.debug("role result >> "+um.getCreate_by());
				log.debug("role result >> "+um.getUpdate_by());
				ps.setString(seq1++, um.getJda_id());
				ps.setString(seq1++, um.getIv_user());
				ps.setString(seq1++, um.getRole_id());
				ps.setString(seq1++, um.getCreate_by());
				ps.setString(seq1++, um.getUpdate_by());
				
				int rsInt1 = ps.executeUpdate();
				if (rsInt1 > 0) {
					blSuccess = true;
				}
			}
			log.debug(" rsInt = " +rsInt );
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
	@Override
	public Vector selectUserforUpdate(String ivUser, String userName, String first_name,String lastName, String locked) throws SQLException {
		// TODO Auto-generated method stub
		Vector vc = new Vector();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
				
		conn = db.getConnection();
		StringBuffer sqlusr = new StringBuffer();
		
		
		boolean isSuccess=false;
		
		sqlusr.append("SELECT JDA_ID, IV_USER,PASSWORD, USER_NAME, FIRST_NAME, LAST_NAME, DEPARTMENT, USER_STATUS,TO_CHAR(EFFECTIVE_DATE,'YYYY-MM-DD') AS EFFECTIVE_DATE, ");
		sqlusr.append("TO_CHAR(EXPIRY_DATE,'YYYY-MM-DD') AS EXPIRY_DATE,TO_CHAR(CREATE_DATE,'YYYY-MM-DD') AS CREATE_DATE, ");
		sqlusr.append("'<img src=\"images/edit.JPG\" name=\"edit\" id=\"edit\" onclick=\"EditUser('|| (rownum-1)||')\">' AS EDITS, ");
		sqlusr.append("'<img src=\"images/delete.JPG\" name=\"delete\" id=\"delete\" value=\"delete\" onclick=\"DeleteUser('''|| JDA_ID ||''')\">' AS DELETES ");
		sqlusr.append("FROM JDA_USER ");
		if (!"".equals(ivUser) || !"".equals(userName) || !"".equals(first_name) || !"".equals(lastName) || !"".equals(locked)){
		
		}
		try {
			log.debug("sqlusr >> " + sqlusr.toString());
			ps = conn.prepareStatement(sqlusr.toString());
//			ps.setString(1,RoleName.toUpperCase());
			
			rs = ps.executeQuery();
			
			while (rs.next()){
				UsrModel um = new UsrModel();
				um.setJda_id(rs.getString("JDA_ID"));
				um.setIV_USER(rs.getString("IV_USER"));
				um.setUSERNAME(rs.getString("USER_NAME"));
				um.setPWD(rs.getString("PASSWORD"));
				um.setFIRSTNAME(rs.getString("FIRST_NAME"));
				um.setLASTNAME(rs.getString("LAST_NAME"));
				um.setDEPARTMENT(rs.getString("DEPARTMENT"));
				um.setUSER_STATUS(rs.getString("USER_STATUS"));
//				log.debug("rs.getString(EFFECTIVE_DATE) >> " + Date.valueOf(rs.getString("EFFECTIVE_DATE")));
				um.setEFFECTIVE_DATE(Date.valueOf(rs.getString("EFFECTIVE_DATE")));
				um.setEXPIRY_DATE(Date.valueOf(rs.getString("EXPIRY_DATE")));
				um.setCreate_date(Date.valueOf(rs.getString("CREATE_DATE")));
				um.setShow_edit(rs.getString("EDITS"));
				um.setShow_delete(rs.getString("DELETES"));
				vc.add(um);
			}
			
			log.debug("[selectUserforUpdate : result ] "+vc);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("selectUserforUpdate",e);
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
	@Override
	public boolean deleteUser(String jdaId) throws SQLException {
		log.debug("[Start : delete User ]");
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
			sql1.append("DELETE FROM JDA_USER WHERE JDA_ID = ? ");
			log.debug("sql JDA_USER >> " + sql1.toString());
			ps = conn.prepareStatement(sql1.toString());
			
			ps.setString(1, jdaId);
			
			int rsInt1 = ps.executeUpdate();
			if (rsInt1 > 0) {
				blSuccess = true;
			}
			
			sql.append("DELETE FROM JDA_USER_ROLE WHERE JDA_ID = ? ");
			log.debug("sql JDA_USER_ROLE >> " + sql.toString());
			ps = conn.prepareStatement(sql.toString());
			
			ps.setString(1, jdaId);
			
			int rsInt = ps.executeUpdate();
			if (rsInt > 0) {
				blSuccess = true;
			}
			log.debug("rsInt >> " + rsInt);
			
			log.debug("blSuccess >> " + blSuccess );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				log.error(e1, e1);
			}
			log.debug(e.getMessage());
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
	public boolean checkUsernameDup(String user_name, String ejda_id) throws SQLException {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
				
		conn = db.getConnection();
		StringBuffer sqlusr = new StringBuffer();
		String result = "";
		
		boolean isSuccess=false;
		
		sqlusr.append("SELECT JDA_ID, IV_USER,PASSWORD, USER_NAME FROM JDA_USER WHERE UPPER(USER_NAME) = ? ");
		if (!"".equals(ejda_id)){
			sqlusr.append("AND JDA_ID <> ? ");
		}
		
		try {
			log.debug("sqlusr >> " + sqlusr.toString());
			ps = conn.prepareStatement(sqlusr.toString());
			ps.setString(1,user_name.toUpperCase());
			if (!"".equals(ejda_id)){
				ps.setString(2,ejda_id);
			}
			rs = ps.executeQuery();
			
			if (rs.next()){
				isSuccess = true;
			}
			
			log.debug("[checkUsernameDup : result ] "+isSuccess);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("selectUserforUpdate",e);
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
		
		return isSuccess;
	}
	@Override
	public boolean logoutUser(String userName) throws SQLException {
		boolean blSuccess = false;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = db.getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE JDA_USER SET USER_COUNT = 0, USER_STATUS = 'N' WHERE UPPER(USER_NAME) = ? ");
		log.debug("sql logoutUser >>> " + sql);
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, userName.toUpperCase());
			
			int rsInt = ps.executeUpdate();
			if (rsInt > 0) {
				blSuccess = true;
			}
			log.debug(" rsInt = " +rsInt );
		} catch (SQLException e) {
			// TODO Auto-generated catch blockd
			e.printStackTrace();
			log.error("updateCountUser",e);
		} finally {
			try {
				if (conn != null)
					log.debug("commit");
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

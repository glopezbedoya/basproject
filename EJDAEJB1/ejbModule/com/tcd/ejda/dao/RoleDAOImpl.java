package com.tcd.ejda.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.tcd.ejda.connection.JDBCConnection;
import com.tcd.ejda.model.MenuModel;
import com.tcd.ejda.model.RoleFunctionModel;
import com.tcd.ejda.model.RoleModel;

import org.apache.log4j.Logger;

public class RoleDAOImpl implements RoleDAO {
	private Logger log = Logger.getLogger(RoleDAOImpl.class);
	JDBCConnection db = new JDBCConnection();
	@Override
	public boolean insertRole(RoleModel roleModel, Vector vc) throws SQLException{
		// TODO Auto-generated method stub
		boolean blSuccess=false;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String role_id = "";
		String func_role_id = "";
		
		conn = db.getConnection();
		StringBuffer sql = new StringBuffer();
		StringBuffer sql1 = new StringBuffer();
		conn.setAutoCommit(false); 
		
		
		try {
			StringBuffer sqlSeq = new StringBuffer();
			sqlSeq.append("select ROLE_ID_SEQ.nextval as SEQ from dual ");
			
			log.debug("sql insert>>>>>>>>>>>>  " + sqlSeq.toString());
			ps = conn.prepareStatement(sqlSeq.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				
				role_id = "R" + rs.getString("SEQ");

			}
			rs.close();
			
			sql.append("INSERT INTO JDA_ROLE (ROLE_ID, ROLE_NAME, CREATE_DATE, CREATE_BY, UPDATE_DATE, UPDATE_BY) ");
			sql.append("VALUES(?, ?, SYSDATE, ?, SYSDATE, ? )");
			
			ps = conn.prepareStatement(sql.toString());
			int seq=1;
			ps.setString(seq++, role_id);
			ps.setString(seq++, roleModel.getRole_name());
			ps.setString(seq++, roleModel.getCreate_by());
			ps.setString(seq++, roleModel.getUpdate_by());
			
			int rsInt = ps.executeUpdate();
			if (rsInt > 0) {
				blSuccess = true;
			}
			StringBuffer sqlSeq1 = new StringBuffer();
			sqlSeq1.append("select ROLE_FUNC_ID_SEQ.nextval as SEQ from dual ");
			
			log.debug("sql insert>>>>>>>>>>>>  " + sqlSeq1.toString());
			ps = conn.prepareStatement(sqlSeq1.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				
				func_role_id = "FR" + rs.getString("SEQ");

			}
			log.debug("func_role_id >> "+func_role_id);
			rs.close();
			
			sql1.append("INSERT INTO JDA_ROLE_FUNCTION(ROLE_FUN_ID, ROLE_ID, MENU_ID, FUN_ADD, FUN_UPDATE, FUN_DELETE, ");
			sql1.append("FUN_INQUIRY, CREATE_DATE, CREATE_BY, UPDATE_DATE, UPDATE_BY) ");
			sql1.append("VALUES(?, ?, ?, ?, ?, ?, ?, SYSDATE, ?, SYSDATE, ? )");
			log.debug("insert function >>> " + sql1.toString());
			ps=conn.prepareStatement(sql1.toString());
			log.debug("vc.size >> " + vc.size());
			for (int i=0;i<vc.size();i++){
				int seqq=1;
				RoleFunctionModel rfm = (RoleFunctionModel)vc.get(i);
				if (null!=rfm.getMenu_id()){
					log.debug("insert func_role_id >>> " + func_role_id);
					log.debug("insert role_id >>> " + role_id);
					log.debug("insert rfm.getMenu_id() >>> " + rfm.getMenu_id());
					log.debug("insert rfm.getFunc_add() >>> " + rfm.getFunc_add());
					log.debug("insert rfm.getFunc_update() >>> " + rfm.getFunc_update());
					log.debug("insert rfm.getFunc_del() >>> " + rfm.getFunc_del());
					log.debug("insert rfm.getFunc_inqs() >>> " + rfm.getFunc_inqs());
					log.debug("insert rfm.getFunc_inqs() >>> " + rfm.getFunc_inqs());
					log.debug("insert rfm.getCreate_by() >>> " + rfm.getCreate_by() + ":" +rfm.getCreate_by());
				
					ps.setString(seqq++, func_role_id);
					ps.setString(seqq++, role_id);
					ps.setString(seqq++, rfm.getMenu_id());
					ps.setString(seqq++, rfm.getFunc_add());
					ps.setString(seqq++, rfm.getFunc_update());
					ps.setString(seqq++, rfm.getFunc_del());
					ps.setString(seqq++, rfm.getFunc_inqs());
					ps.setString(seqq++, rfm.getCreate_by());
					ps.setString(seqq++, rfm.getUpdate_by());
				
					int rsInt1 = ps.executeUpdate();
					log.debug("insert jda_role_function >> " +rsInt1);
					if (rsInt1 > 0) {
						blSuccess = true;
					}
				}
			}
			log.debug("blSuccess >> " + blSuccess );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			log.equals(e.getMessage());
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
	public Vector selectRole(String RoleName) throws SQLException {
		// TODO Auto-generated method stub
		Vector vc = new Vector();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
				
		conn = db.getConnection();
		StringBuffer sqlusr = new StringBuffer();
		
		
		boolean isSuccess=false;
		
		//sqlusr.append("SELECT  ROLE_ID, ROLE_NAME, CREATE_DATE, CREATE_BY, UPDATE_DATE, UPDATE_BY FROM JDA_ROLE WHERE UPPER(ROLE_NAME) = ? ");
//		sqlusr.append("SELECT ROLE_ID, ROLE_NAME,'<input type=\"hidden\" name=role_id\" value='|| ROLE_ID ||'\"><IMG src=\"images/edit.JPG\"></IMG>' AS EDITS,");
//		sqlusr.append("'<input type=\"hidden\" name=role_id\" value='|| ROLE_ID ||'\"><IMG src=\"images/delete.JPG\"></IMG>' AS DELETES FROM JDA_ROLE");
		
		sqlusr.append("SELECT ROLE_ID, ROLE_NAME,'<input type=\"hidden\" name=\"role_id\" value=\"'|| ROLE_ID ||'\"><input type=\"button\" name=\"edit\" id=\"edit\" value=\"Edit\" onclick=\"EditRole(this.form,'''|| ROLE_ID ||''','''|| ROLE_NAME ||''')\">' AS EDITS,");
		sqlusr.append("'<input type=\"hidden\" name=role_id\" value='|| ROLE_ID ||'\"><input type=\"button\" name=\"delete\" id=\"delete\" value=\"delete\" onclick=\"DeleteRole(this.form,'''|| ROLE_ID ||''','''|| ROLE_NAME ||''')\">' AS DELETES FROM JDA_ROLE");
		
		try {
			log.debug("sqlusr >> " + sqlusr.toString());
			ps = conn.prepareStatement(sqlusr.toString());
//			ps.setString(1,RoleName.toUpperCase());
			
			rs = ps.executeQuery();
			
			while (rs.next()){
				RoleModel rm = new RoleModel();
				rm.setRole_id(rs.getString("ROLE_ID"));
				rm.setRole_name(rs.getString("ROLE_NAME"));
				rm.setShow_edit(rs.getString("EDITS"));
				rm.setShow_del(rs.getString("DELETES"));
//				rm.setCreate_date(rs.getDate("CREATE_DATE"));
//				rm.setRole_id(rs.getString("CREATE_BY"));
//				rm.setUpdate_date(rs.getDate("UPDATE_DATE"));
//				rm.setRole_id(rs.getString("UPDATE_BY"));
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
	public Vector selectRole() throws SQLException {
		// TODO Auto-generated method stub
		log.debug("[Start : selectRole]");
		Vector vc = new Vector();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
				
		conn = db.getConnection();
		StringBuffer sqlusr = new StringBuffer();
		conn.setAutoCommit(false);
		
		boolean isSuccess=false;
		
		sqlusr.append("SELECT  ROLE_ID, ROLE_NAME, CREATE_DATE, CREATE_BY, UPDATE_DATE, UPDATE_BY FROM JDA_ROLE ");
//		sqlusr.append("SELECT  MENU_ID FROM JDA_MENU ");
		try {
			log.debug("sqlusr selectRole >> " + sqlusr.toString());
			String dsqlusr = sqlusr.toString();
			ps = conn.prepareStatement(dsqlusr);
			//ps.setString(1,RoleName.toUpperCase());
			
			rs = ps.executeQuery();
			
			while (rs.next()){
				RoleModel rm = new RoleModel();
				log.debug("values : "+rs.getString("ROLE_ID"));
				rm.setRole_id(rs.getString("ROLE_ID"));
				rm.setRole_name(rs.getString("ROLE_NAME"));
				rm.setCreate_date(rs.getDate("CREATE_DATE"));
				rm.setCreate_by(rs.getString("CREATE_BY"));
				rm.setUpdate_date(rs.getDate("UPDATE_DATE"));
				rm.setUpdate_by(rs.getString("UPDATE_BY"));
				vc.add(rm);
			}
			
			log.debug("[selsectRole : result ] "+vc);
		
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
		
		return vc;
	}
	@Override
	public Vector selectMenufor(String roleId) throws SQLException {
		// TODO Auto-generated method stub
		Vector vc = new Vector();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
				
		conn = db.getConnection();
		StringBuffer sql = new StringBuffer();
		
		
		boolean isSuccess=false;
		
		sql.append("SELECT  MENU_ID, MENU_NAME, MENU_STATUS, DESCRIPTION, CREATE_DATE, CREATE_BY, UPDATE_DATE, ");
		sql.append("UPDATE_BY, MENU_OWNER, MENU_SORT FROM JDA_ROLE ");
		if(null!=roleId && !roleId.equals("")){
			sql.append(" WHERE ROLE_ID = ? " );
		}
		
		try {
			log.debug("sql >> " + sql.toString());
			ps = conn.prepareStatement(sql.toString());
			if(null!=roleId && !roleId.equals("")){
				ps.setString(1,roleId);
			}
			
			
			rs = ps.executeQuery();
			while (rs.next()){
				MenuModel mn = new MenuModel();
//				mn.setMenu_id(menuId)
				
				vc.add(mn);
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
	@Override
	public Vector selectEditRole(String roleId) throws SQLException {
		// TODO Auto-generated method stub
		Vector vc = new Vector();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
				
		conn = db.getConnection();
		StringBuffer sql = new StringBuffer();
		
		
		boolean isSuccess=false;
		
//		sql.append("select jm.menu_id, jm.menu_status,jm.menu_name,jrf.role_fun_id,jrf.role_id,jrf.fun_add,jrf.fun_update,jrf.fun_delete, ");
//		sql.append("jrf.fun_inquiry from jda_menu jm inner join jda_role_function jrf on jm.menu_id = jrf.menu_id ");
//		sql.append("inner join jda_role jr on jr.role_id = jrf.role_id and jr.role_id = ? ");
		sql.append("select jm.menu_id, jm.menu_status, jm.menu_name, jf.role_fun_id, jf.role_id, jf.fun_add, jf.fun_update, jf.fun_delete, ");
		sql.append("jf.fun_inquiry, MENU_SORT from jda_menu jm left join jda_role_function jf on jm.menu_id = jf.menu_id AND jf.role_id = ? ");
		sql.append("order by jm.menu_sort");
		
		try {
			log.debug("sql selectEditRole >> " + sql.toString());
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, roleId);
			
			rs = ps.executeQuery();
			while (rs.next()){
				RoleFunctionModel rf = new RoleFunctionModel();
				rf.setMenu_id(rs.getString("menu_id"));
				rf.setMenu_status(rs.getString("menu_status"));
				rf.setMenu_name(rs.getString("menu_name"));
				rf.setRole_func_id(rs.getString("role_fun_id"));
				rf.setRole_id(rs.getString("role_id"));
				rf.setFunc_add(rs.getString("fun_add"));
				rf.setFunc_update(rs.getString("fun_update"));
				rf.setFunc_del(rs.getString("fun_delete"));
				rf.setFunc_inqs(rs.getString("fun_inquiry"));
				vc.add(rf);
			}
			
			log.debug("[selsect func role : result ] "+vc);
		
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
	@Override
	public boolean updateRole(RoleModel roleModel, Vector vc)
			throws SQLException {
		log.debug("[Start : update Role ]");
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
						
			sql.append("UPDATE JDA_ROLE SET ROLE_NAME = ?, UPDATE_DATE = SYSDATE, UPDATE_BY = ? ");
			sql.append("WHERE ROLE_ID = ? ");
			log.debug("updateRole jda_role >>> " + sql.toString());
			ps = conn.prepareStatement(sql.toString());
			int seq=1;
			log.debug("roleModel.getRole_name() >> "+roleModel.getRole_name());
			log.debug("roleModel.getUpdate_by() >> "+roleModel.getUpdate_by());
			log.debug("roleModel.getRole_id() >> "+roleModel.getRole_id());
			ps.setString(seq++, roleModel.getRole_name());
			ps.setString(seq++, roleModel.getUpdate_by());
			ps.setString(seq++, roleModel.getRole_id());
			
			int rsInt = ps.executeUpdate();
			if (rsInt > 0) {
				blSuccess = true;
			}
			log.debug("rsInt >> " +rsInt);
			sql1.append("UPDATE JDA_ROLE_FUNCTION SET FUN_ADD = ? , FUN_UPDATE = ? , FUN_DELETE = ?,FUN_INQUIRY = ?, ");
			sql1.append("UPDATE_DATE = SYSDATE , UPDATE_BY = ? WHERE ROLE_FUN_ID = ? AND ROLE_ID = ? AND MENU_ID =? ");
//			sql1.append("INSERT INTO JDA_ROLE_FUNCTION(ROLE_FUN_ID, ROLE_ID, MENU_ID, FUN_ADD, FUN_UPDATE, FUN_DELETE, ");
//			sql1.append("FUN_INQUIRY, CREATE_DATE, CREATE_BY, UPDATE_DATE, UPDATE_BY) ");
//			sql1.append("VALUES(?, ?, ?, ?, ?, ?, ?, SYSDATE, ?, SYSDATE, ? )");
			log.debug("insert function >>> " + sql1.toString());
			ps=conn.prepareStatement(sql1.toString());
			log.debug("vc.size >> " + vc.size());
			for (int i=0;i<vc.size();i++){
				int seqq=1;
				RoleFunctionModel rfm = (RoleFunctionModel)vc.get(i);
				if (null!=rfm.getMenu_id()){
					
					log.debug("update rfm.getMenu_id() >>> " + rfm.getMenu_id());
					log.debug("update rfm.getRole_func_id() >>> " + rfm.getRole_func_id());
					log.debug("update rfm.getRole_id() >>> " + rfm.getRole_id());
					log.debug("update rfm.getFunc_add() >>> " + rfm.getFunc_add());
					log.debug("update rfm.getFunc_update() >>> " + rfm.getFunc_update());
					log.debug("update rfm.getFunc_del() >>> " + rfm.getFunc_del());
					log.debug("update rfm.getFunc_inqs() >>> " + rfm.getFunc_inqs());
					log.debug("update rfm.getCreate_by() >>> " + rfm.getUpdate_by());
					ps.setString(seqq++, rfm.getFunc_add());
					ps.setString(seqq++, rfm.getFunc_update());
					ps.setString(seqq++, rfm.getFunc_del());
					ps.setString(seqq++, rfm.getFunc_inqs());
					ps.setString(seqq++, rfm.getUpdate_by());
					ps.setString(seqq++, rfm.getRole_func_id());
					ps.setString(seqq++, rfm.getRole_id());
					ps.setString(seqq++, rfm.getMenu_id());
			
					int rsInt1 = ps.executeUpdate();
					log.debug("update jda_role_function >> " +rsInt1);
					if (rsInt1 > 0) {
						blSuccess = true;
					}
				}
			}
			log.debug("blSuccess >> " + blSuccess );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			log.equals(e.getMessage());
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
	public boolean deleteRole(String roleId) throws SQLException {
		// TODO Auto-generated method stub
		log.debug("[Start : update Role ]");
		// TODO Auto-generated method stub
		boolean blSuccess=false;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		conn = db.getConnection();
		StringBuffer sql = new StringBuffer();
		StringBuffer sql1 = new StringBuffer();
		StringBuffer sql2 = new StringBuffer();
		conn.setAutoCommit(false); 
		
		try {
						
			sql.append("DELETE FROM JDA_USER_ROLE WHERE ROLE_ID = ? ");
			log.debug("sql JDA_USER_ROLE >> " + sql.toString());
			ps = conn.prepareStatement(sql.toString());
			
			ps.setString(1, roleId);
			
			int rsInt = ps.executeUpdate();
			if (rsInt > 0) {
				blSuccess = true;
			}
			log.debug("rsInt >> " + rsInt);
			
			sql1.append("DELETE FROM JDA_ROLE_FUNCTION WHERE ROLE_ID = ? ");
			log.debug("sql JDA_ROLE_FUNCTION >> " + sql1.toString());
			ps = conn.prepareStatement(sql1.toString());
			
			ps.setString(1, roleId);
			
			int rsInt1 = ps.executeUpdate();
			if (rsInt1 > 0) {
				blSuccess = true;
			}
			log.debug("rsInt1 >> " + rsInt1);
			
			sql2.append("DELETE FROM JDA_ROLE WHERE ROLE_ID = ? ");
			log.debug("sql JDA_ROLE >> " + sql2.toString());
			ps = conn.prepareStatement(sql2.toString());
			
			ps.setString(1, roleId);
			
			int rsInt2 = ps.executeUpdate();
			if (rsInt2 > 0) {
				blSuccess = true;
			}
			log.debug("rsInt2 >> " + rsInt2);
			
			log.debug("blSuccess >> " + blSuccess );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			log.equals(e.getMessage());
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

}

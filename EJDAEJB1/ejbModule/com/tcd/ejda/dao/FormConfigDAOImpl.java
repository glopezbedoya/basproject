package com.tcd.ejda.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.tcd.ejda.connection.JDBCServiceLocator;
import com.tcd.ejda.model.FormConfigModel;
import com.tcd.ejda.model.FormDocAttachModel;

public class FormConfigDAOImpl implements FormConfigDAO {
	
	JDBCServiceLocator db = new JDBCServiceLocator();
	private Logger log = Logger.getLogger(FormConfigDAOImpl.class); 
	
	@Override
	public boolean insertFormConfig(Vector vc,String jda_type) throws SQLException {
		log.debug("[Start : insertFormConfig ]");
		
		boolean blSuccess=false;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		try {
			conn = db.getConnection();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		StringBuffer sql = new StringBuffer();
		StringBuffer sqlDel = new StringBuffer();
		StringBuffer sql1 = new StringBuffer();
		boolean checkDel = true;
		int count = 0;
		conn.setAutoCommit(false); 
		
		try {
			sql1.append("SELECT COUNT(JDA_TYPE) AS JDA_TYPE FROM JDA_ROLE_T_DOC WHERE JDA_TYPE = ? ");
			log.debug("select JDA_ROLE_T_DOC >>> " + sql1.toString());
			ps = conn.prepareStatement(sql1.toString());
			int seq=1;
			log.debug("jda_type -- > " +jda_type);
			ps.setString(seq++, jda_type);
			
			rs = ps.executeQuery();
			if (rs.next()){
				count = rs.getInt("JDA_TYPE");
			}
			log.debug("[ Count ] " + count);
			if (count > 0){
				
			
				sqlDel.append("delete from JDA_ROLE_T_DOC where JDA_TYPE = ? ");
				log.debug("deleteInsertDetail JDA_ROLE_T_DOC >>> " + sqlDel.toString());
				
				ps = conn.prepareStatement(sqlDel.toString());
				int seqdel=1;
				
				ps.setString(seqdel++, jda_type);
				
				int rsInt = ps.executeUpdate();
				if (rsInt > 0) {
					checkDel = true;
				}
			}
			if (checkDel){
				sql.append("insert into JDA_ROLE_T_DOC(ROLE_DOC_ID, ROLE_ID, JDA_TYPE, FORM_FIELD, PERMISSIONS, ");
				sql.append("CREATE_DATE, CREATE_BY, UPDATE_DATE, UPDATE_BY, REMARK) ");
				sql.append("values (JDA_ROLE_T_DOC_SEQ.nextval, '1', ?, ?, null, SYSDATE, ?, SYSDATE, ?, null)");
				log.debug("sql4 >> " + sql.toString());
				
				ps = conn.prepareStatement(sql.toString());
				
				for(int i =0;i<vc.size();i++){
					int parameterIndex = 1;
					FormConfigModel cm = (FormConfigModel)vc.get(i);
//					log.debug(" getJda_type() " +  cm.getJda_type());
//					log.debug(" getInput_field() " +  cm.getInput_field());
//					log.debug(" getPermissions() " +  cm.getPermissions());
//					log.debug(" getUser_name() " +  cm.getUser_name());
					
					ps.setString(parameterIndex++, cm.getJda_type());
					ps.setString(parameterIndex++, cm.getInput_field());
	//				ps.setString(parameterIndex++, cm.getPermissions());
					ps.setString(parameterIndex++, cm.getUser_name());
					ps.setString(parameterIndex++, cm.getUser_name());
					rs = ps.executeQuery();
				}
				blSuccess = true;
			}
			
			log.debug("blSuccess >> " + blSuccess );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			log.debug("insertFormConfig >> " + e.getMessage());
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

	public Vector<FormConfigModel> searchFormConfigModel(String jda_type) throws SQLException{
		log.debug("[Start : searchFormConfigModel ]");
		log.debug("jda_type = "+jda_type);
		FormConfigModel formConfigM = new FormConfigModel();
		Vector<FormConfigModel> vc = new  Vector<FormConfigModel>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = db.getConnection();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		StringBuffer sql = new StringBuffer();
		
		try {
						
			sql.append(" SELECT ROLE_DOC_ID, ROLE_ID, JDA_TYPE, FORM_FIELD, PERMISSIONS, ");
			sql.append("CREATE_DATE, CREATE_BY, UPDATE_DATE, UPDATE_BY, REMARK FROM JDA_ROLE_T_DOC ");
			sql.append("WHERE JDA_TYPE = ? ");
			log.debug("Search JDA_ROLE_T_DOC >>> " + sql.toString());
			ps = conn.prepareStatement(sql.toString());
			int seq=1;
			
			ps.setString(seq++, jda_type);
			
			rs = ps.executeQuery();
			while(rs.next()){
				formConfigM = new FormConfigModel();
				formConfigM.setInput_field(rs.getString("FORM_FIELD"));
				vc.add(formConfigM);
			}
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
		return vc;
	}
}

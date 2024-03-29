package com.tcd.ejda.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.tcd.ejda.connection.JDBCServiceLocator;
import com.tcd.ejda.model.Form4Model;
import com.tcd.ejda.model.RoleFunctionModel;

public class Form4DAOImpl implements Form4DAO {
	JDBCServiceLocator db = new JDBCServiceLocator();
	
	private Logger log = Logger.getLogger(Form4DAOImpl.class); 
	@Override
	public void saveFrom4Table1(Form4Model form4) throws SQLException {
		log.debug("[ Start : submitFrom1Table1 ]");
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String form_no = "";
		Vector searchMenu = new Vector();
		
		try {
			conn = db.getConnection();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		StringBuffer sqlSeq = new StringBuffer();
		sqlSeq.append("select FORM4_SEQ.nextval as SEQ from dual ");
		
		log.debug("sql insert>>>>>>>>>>>>  " + sqlSeq.toString());
		ps = conn.prepareStatement(sqlSeq.toString());
		rs = ps.executeQuery();
		while (rs.next()) {
			
			form_no = "FN" + rs.getString("SEQ");

		}
		rs.close();
		
		StringBuffer sqlupd = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		sql.append("insert into JDA_FORM4_CTRL (FORM_NO, FORM_NAME, STATUS, UPDATE_BY) values (?, ?, ?, ?)");
		log.debug("sql >> " + sql.toString());
		try {
			ps = conn.prepareStatement(sql.toString());
			int parameterIndex = 1;
			ps.setString(parameterIndex++, form_no);
			ps.setString(parameterIndex++, form4.getForm_name());
			ps.setString(parameterIndex++, form4.getForm_status());
			ps.setString(parameterIndex++, form4.getUpdate_by());
			rs = ps.executeQuery();
		
			log.debug("Connection session: ");
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error("submitFrom1Table4",e);
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
	}
	@Override
	public boolean UpdateFrom4Table(Form4Model form4) throws SQLException {
		log.debug("[Start : UpdateFrom4Table ]");
		// TODO Auto-generated method stub
		boolean blSuccess=false;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = db.getConnection();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		StringBuffer sql = new StringBuffer();
		conn.setAutoCommit(false); 
		
		try {
						
			sql.append("UPDATE JDA_FORM4_CTRL SET STATUS = ?, UPDATE_BY = ? ");
			sql.append("WHERE FORM_NO = ? ");
			log.debug("updateRole JDA_FORM1_CTRL >>> " + sql.toString());
			ps = conn.prepareStatement(sql.toString());
			int seq=1;
			log.debug("form1.getForm_no() >> "+form4.getForm_no());
			
			ps.setString(seq++, form4.getForm_status());
			ps.setString(seq++, form4.getUpdate_by());
			ps.setString(seq++, form4.getForm_no());
			
			int rsInt = ps.executeUpdate();
			if (rsInt > 0) {
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

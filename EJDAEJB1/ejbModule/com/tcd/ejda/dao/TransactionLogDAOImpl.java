package com.tcd.ejda.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.tcd.ejda.connection.JDBCConnection;
import com.tcd.ejda.connection.JDBCServiceLocator;
import com.tcd.ejda.model.MenuModel;
import com.tcd.ejda.model.TransactionLogModel;

public class TransactionLogDAOImpl implements TransactionLogDAO {
	
	JDBCServiceLocator db = new JDBCServiceLocator();
	
	private Logger log = Logger.getLogger(TransactionLogDAOImpl.class); 
	
	public void insertTranLog(TransactionLogModel tranlog) throws SQLException{
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String trans_id = "";
		Vector searchMenu = new Vector();
		
		try {
			conn = db.getConnection();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		StringBuffer sqlSeq = new StringBuffer();
		sqlSeq.append("select TRAN_ID_SEQ.nextval as SEQ from dual ");
		
		log.debug("sql insert>>>>>>>>>>>>  " + sqlSeq.toString());
		ps = conn.prepareStatement(sqlSeq.toString());
		rs = ps.executeQuery();
		while (rs.next()) {
			
			trans_id = "T" + rs.getString("SEQ");

		}
		rs.close();
		
		StringBuffer sqlupd = new StringBuffer();
//		sqlupd.append("INSERT INTO EJDA_MENU()")
		StringBuffer sql = new StringBuffer();
//		sql.append("insert into JDA_TRANSACTION_LOG (TRANS_ID,TRANS_MENU_ID, TRANS_ACTION, DESCRIPTION, IP_ADDRESS, TRANS_DATE, TRANS_BY) values ((select TRAN_ID_SEQ.nextval as SEQ from dual ), ?, ?, ?, ?, ?, ?)");
		sql.append("insert into JDA_TRANSACTION_LOG (TRANS_ID,TRANS_MENU_ID, TRANS_ACTION, DESCRIPTION, IP_ADDRESS, TRANS_DATE, TRANS_BY) values (?, ?, ?, ?, ?, SYSDATE, ?)");
		log.debug("sql >> " + sql.toString());
		try {
			ps = conn.prepareStatement(sql.toString());
			int parameterIndex = 1;
			ps.setString(parameterIndex++, trans_id);
			ps.setString(parameterIndex++, tranlog.getMenuId());
			ps.setString(parameterIndex++, tranlog.getTranAction());
			ps.setString(parameterIndex++, tranlog.getDescription());
			ps.setString(parameterIndex++, tranlog.getIpAddress());
//			ps.setDate(parameterIndex++, new Date(tranlog.getTranDate().getTime()));
			ps.setString(parameterIndex++, tranlog.getTranBy());
			rs = ps.executeQuery();
		
			log.debug("Connection session: ");
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error("insertTranLog",e);
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
	
	public Vector searchTransactionLog(TransactionLogModel tranLogCri) throws SQLException{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Vector resultVt = new Vector();
		try{
			try {
				conn = db.getConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			StringBuffer sqlupd = new StringBuffer();
//			sqlupd.append("INSERT INTO EJDA_MENU()")
			StringBuffer sql = new StringBuffer();
			sql.append("select l.TRANS_ID, l.TRANS_ACTION, l.DESCRIPTION, l.IP_ADDRESS, l.TRANS_DATE, l.TRANS_BY, u.FIRST_NAME, u.LAST_NAME, u.DEPARTMENT from jda_transaction_log l " +
						" join jda_user u on u.iv_user = l.trans_by ");
			log.debug("sql >> " + sql.toString());
			
			ps = conn.prepareStatement(sql.toString());
//			int parameterIndex = 1;
			rs = ps.executeQuery();
			while(rs.next()){
				TransactionLogModel tranLogM = new TransactionLogModel();
				tranLogM.setTranId(rs.getString("TRANS_ID"));
				tranLogM.setTranAction(rs.getString("TRANS_ACTION"));
				tranLogM.setDescription(rs.getString("DESCRIPTION"));
				tranLogM.setIpAddress(rs.getString("IP_ADDRESS"));
				tranLogM.setTranDate(rs.getDate("TRANS_DATE"));
				tranLogM.setTranBy(rs.getString("TRANS_BY"));
				tranLogM.setFirstName(rs.getString("FIRST_NAME"));
				tranLogM.setLastName(rs.getString("LAST_NAME"));
				tranLogM.setDepartment(rs.getString("DEPARTMENT"));
				resultVt.add(tranLogM);
			}
			
		}catch (SQLException e) {
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
		return resultVt;
		
	}
	
	public boolean deleteTransactionLog(String[] tranId) throws SQLException{
		boolean result = false;
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			StringBuffer sql = new StringBuffer();
			conn = db.getConnection();
			sql.append("DELETE FROM JDA_TRANSACTION_LOG WHERE TRANS_ID in (");
			for(int i=0;i<tranId.length;i++){
				if(i != tranId.length-1){
					sql.append("?,");
				}else{
					sql.append("?");
				}
			}
			sql.append(")");
			log.debug("deleteTransactionLog sql >> "+sql.toString());
			ps = conn.prepareStatement(sql.toString());
			int parameterIndex = 1;
			for(int i=0;i<tranId.length;i++){
				log.debug("tranId"+i+" = "+tranId[i]);
				ps.setString(parameterIndex++, tranId[i]);
			}
			int deleteRow = ps.executeUpdate();
			log.debug("deleteRow = "+deleteRow);
			result = true;
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if (conn != null)
					conn.commit();
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
}

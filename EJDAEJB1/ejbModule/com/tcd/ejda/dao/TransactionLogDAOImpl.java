package com.tcd.ejda.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.tcd.ejda.connection.JDBCConnection;
import com.tcd.ejda.model.MenuModel;
import com.tcd.ejda.model.TransactionLogModel;

public class TransactionLogDAOImpl implements TransactionLogDAO {
	
	JDBCConnection db = new JDBCConnection();
	
	public void insertTranLog(TransactionLogModel tranlog){
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Vector searchMenu = new Vector();
		
		conn = db.getConnection();
		StringBuffer sqlupd = new StringBuffer();
//		sqlupd.append("INSERT INTO EJDA_MENU()")
		StringBuffer sql = new StringBuffer();
		sql.append("insert into JDA_TRANSACTION_LOG (TRANS_ID, TRANS_ACTION, DESCRIPTION, IP_ADDRESS, TRANS_DATE, TRANS_BY) values (?, ?, ?, ?, ?, ?)");
		System.out.println("sql >> " + sql.toString());
		try {
		ps = conn.prepareStatement(sql.toString());
		int parameterIndex = 1;
		ps.setString(parameterIndex++, tranlog.getTranId());
		ps.setString(parameterIndex++, tranlog.getTranAction());
		ps.setString(parameterIndex++, tranlog.getDescription());
		ps.setString(parameterIndex++, tranlog.getIpAddress());
		ps.setDate(parameterIndex++, tranlog.getTranDate());
		ps.setString(parameterIndex++, tranlog.getTranBy());
		rs = ps.executeQuery();
		
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
	}
}

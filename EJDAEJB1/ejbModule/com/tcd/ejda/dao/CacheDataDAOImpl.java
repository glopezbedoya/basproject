package com.tcd.ejda.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.tcd.ejda.connection.JDBCServiceLocator;
import com.tcd.ejda.model.CacheDataM;

public class CacheDataDAOImpl implements CacheDataDAO {
	
	JDBCServiceLocator db = new JDBCServiceLocator();
	
	private Logger log = Logger.getLogger(Form1DAOImpl.class); 
	@Override
	public Vector LoadCountry() throws SQLException {
		log.debug("[Start : LoadCountry ]");
		Vector vc = new Vector();
		
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
						
			sql.append(" SELECT COUNTRYID, COUNTRYCODE, COUNTRYNAME, COUNTRYNAMEFULL, COUNTRYSTATUS FROM JDA_M_COUNTRY ");
			log.debug("Search JDA_M_COUNTRY >>> " + sql.toString());
			ps = conn.prepareStatement(sql.toString());
			int seq=1;
			
			
			rs = ps.executeQuery();
			while(rs.next()){
				CacheDataM cache = new CacheDataM();
				cache.setCode(rs.getString("COUNTRYCODE"));
				cache.setShortDesc(rs.getString("COUNTRYNAME"));
				cache.setLongDesc(rs.getString("COUNTRYNAMEFULL"));
				cache.setStatus(rs.getString("COUNTRYSTATUS"));
				vc.add(cache);
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
	@Override
	public Vector LoadUnit() throws SQLException {
		log.debug("[Start : LoadUnit ]");
		Vector vc = new Vector();
		
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
						
			sql.append(" SELECT UNIT_ID, UNIT_CODE, UNIT_NAME, UNIT_STATUS FROM JDA_M_UNIT ");
			log.debug("Search JDA_M_UNIT >>> " + sql.toString());
			ps = conn.prepareStatement(sql.toString());
			int seq=1;
			
			
			rs = ps.executeQuery();
			while(rs.next()){
				CacheDataM cache = new CacheDataM();
				cache.setCode(rs.getString("UNIT_CODE"));
				cache.setShortDesc(rs.getString("UNIT_NAME"));
				cache.setStatus(rs.getString("UNIT_STATUS"));
				vc.add(cache);
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
	@Override
	public Vector LoadExchangeRAte() {
		log.debug("[Start : LoadExchangeRAte ]");
		Vector vc = new Vector();
		
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
						
			sql.append(" SELECT EXCHGRATEID, EXCHANGERATTYPE, RATNAME, RATSTATUS FROM JDA_M_EXCHANGERATE ");
			log.debug("Search JDA_M_EXCHANGERATE >>> " + sql.toString());
			ps = conn.prepareStatement(sql.toString());
			int seq=1;
			
			
			rs = ps.executeQuery();
			while(rs.next()){
				CacheDataM cache = new CacheDataM();
				cache.setCode(rs.getString("EXCHANGERATTYPE"));
				cache.setShortDesc(rs.getString("RATNAME"));
				cache.setStatus(rs.getString("RATSTATUS"));
				vc.add(cache);
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

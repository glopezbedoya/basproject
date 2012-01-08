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
						
			sql.append(" SELECT EXCHGRATEID, EXCHANGERATTYPE, RATNAME, RATE_AMT, RATSTATUS FROM JDA_M_EXCHANGERATE ");
			log.debug("Search JDA_M_EXCHANGERATE >>> " + sql.toString());
			ps = conn.prepareStatement(sql.toString());
			int seq=1;
			
			
			rs = ps.executeQuery();
			while(rs.next()){
				CacheDataM cache = new CacheDataM();
				cache.setCode(rs.getString("EXCHANGERATTYPE"));
				cache.setShortDesc(rs.getString("RATNAME"));
				//cache.setValueDesc(String.valueOf(rs.getDouble("RATE_AMT")));
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
	@Override
	public Vector LoadCustomTanli() throws SQLException {
		log.debug("[Start : LoadCustomTanli ]");
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
						
			sql.append(" SELECT TANLI_ID, TANLI_CODE, DESCRIPTION FROM JDA_M_CUSTOM_TANLI ");
			log.debug("Search JDA_M_CUSTOM_TANLI >>> " + sql.toString());
			ps = conn.prepareStatement(sql.toString());
			int seq=1;
			
			
			rs = ps.executeQuery();
			while(rs.next()){
				CacheDataM cache = new CacheDataM();
				cache.setCode(rs.getString("TANLI_CODE"));
				cache.setShortDesc(rs.getString("DESCRIPTION"));
				//cache.setStatus(rs.getString("RATSTATUS"));
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
	public Vector LoadCountryOrigin() throws SQLException {
		log.debug("[Start : LoadCountryOrigin ]");
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
						
			sql.append(" SELECT ORIG_ID, ORIG_CODE, ORIG_DESC FROM JDA_M_COUNTRY_ORIG ");
			log.debug("Search JDA_M_COUNTRY_ORIG >>> " + sql.toString());
			ps = conn.prepareStatement(sql.toString());
			int seq=1;
			
			
			rs = ps.executeQuery();
			while(rs.next()){
				CacheDataM cache = new CacheDataM();
				cache.setCode(rs.getString("ORIG_CODE"));
				cache.setShortDesc(rs.getString("ORIG_DESC"));
				//cache.setStatus(rs.getString("RATSTATUS"));
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
	public Vector LoadImporter() throws SQLException {
		log.debug("[Start : LoadImporter ]");
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
						
			sql.append(" SELECT IMPORT_CODE, IMPORT_NAME, IMPORT_ADDR, IMPORT_DESC FROM JDA_M_IMPORTER ");
			log.debug("Search JDA_M_IMPORTER >>> " + sql.toString());
			ps = conn.prepareStatement(sql.toString());
			int seq=1;
			
			
			rs = ps.executeQuery();
			while(rs.next()){
				CacheDataM cache = new CacheDataM();
				cache.setCode(rs.getString("IMPORT_CODE"));
				cache.setShortDesc(rs.getString("IMPORT_NAME"));
				cache.setLongDesc(rs.getString("IMPORT_ADDR"));
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
	public Vector LoadAgent() throws SQLException {
		log.debug("[Start : LoadAgent ]");
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
						
			sql.append(" SELECT AGENT_CODE, AGENT_NAME, AGENT_ADDR, AGENT_DESC FROM JDA_M_AGENT ");
			log.debug("Search JDA_M_AGENT >>> " + sql.toString());
			ps = conn.prepareStatement(sql.toString());
			int seq=1;
			
			
			rs = ps.executeQuery();
			while(rs.next()){
				CacheDataM cache = new CacheDataM();
				cache.setCode(rs.getString("AGENT_CODE"));
				cache.setShortDesc(rs.getString("AGENT_NAME"));
				cache.setLongDesc(rs.getString("AGENT_ADDR"));
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
	public String GetImporterAddress(String ImportCode) throws SQLException {
		log.debug("[Start : GetImporterAddress ]");
		String returnValue="";
		
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
						
			sql.append(" SELECT IMPORT_CODE, IMPORT_NAME, IMPORT_ADDR, IMPORT_DESC FROM JDA_M_IMPORTER ");
			sql.append(" WHERE IMPORT_CODE = ? ");
			log.debug("Search GetImporterAddress >>> " + sql.toString());
			ps = conn.prepareStatement(sql.toString());
			int seq=1;
			
			ps.setString(seq++, ImportCode);
			
			rs = ps.executeQuery();
			if(rs.next()){
				returnValue = rs.getString("IMPORT_NAME") + "|" + rs.getString("IMPORT_ADDR");
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
		return returnValue;
	}
	@Override
	public String GetAgentAddress(String AgentCode) throws SQLException {
		log.debug("[Start : GetAgentAddress ]");
		String returnValue="";
		
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
						
			sql.append(" SELECT AGENT_CODE, AGENT_NAME, AGENT_ADDR, AGENT_DESC FROM JDA_M_AGENT ");
			sql.append(" WHERE AGENT_CODE = ? ");
			log.debug("Search GetImporterAddress >>> " + sql.toString());
			ps = conn.prepareStatement(sql.toString());
			int seq=1;
			
			ps.setString(seq++, AgentCode);
			
			rs = ps.executeQuery();
			if(rs.next()){
				returnValue = rs.getString("AGENT_NAME") + "|" + rs.getString("AGENT_ADDR");
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
		return returnValue;
	}

}

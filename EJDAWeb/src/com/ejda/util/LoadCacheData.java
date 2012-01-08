package com.ejda.util;

import java.sql.SQLException;
import java.util.Vector;

import com.tcd.ejda.dao.CacheDataDAO;
import com.tcd.ejda.dao.CacheDataDAOImpl;
import com.tcd.ejda.dao.Form1DAO;
import com.tcd.ejda.dao.Form1DAOImpl;

public class LoadCacheData {
	
	public static Vector GetCountryCache() throws SQLException{
		Vector vc = new Vector();
		
		CacheDataDAO dao = new CacheDataDAOImpl();
		vc = dao.LoadCountry();
		return vc;
		
	}

	public static Vector GetUnit() throws SQLException{
		Vector vc = new Vector();
		
		CacheDataDAO dao = new CacheDataDAOImpl();
		vc = dao.LoadUnit();
		return vc;
		
	}
	
	
	public static Vector GetExchangeRate() throws SQLException{
		Vector vc = new Vector();
		
		CacheDataDAO dao = new CacheDataDAOImpl();
		vc = dao.LoadExchangeRAte();
		return vc;
		
	}
	
	public static Vector GetCustomTanli() throws SQLException{
		Vector vc = new Vector();
		
		CacheDataDAO dao = new CacheDataDAOImpl();
		vc = dao.LoadCustomTanli();
		
		return vc;
		
	}
	
	public static Vector GetCountryOrigin() throws SQLException{
		Vector vc = new Vector();
		
		CacheDataDAO dao = new CacheDataDAOImpl();
		vc = dao.LoadCountryOrigin();
		
		return vc;
		
	}
	
	public static Vector GetImporterCache() throws SQLException{
		Vector vc = new Vector();
		
		CacheDataDAO dao = new CacheDataDAOImpl();
		vc = dao.LoadImporter();
		return vc;
		
	}
	
	public static Vector GetAgentCache() throws SQLException{
		Vector vc = new Vector();
		
		CacheDataDAO dao = new CacheDataDAOImpl();
		vc = dao.LoadAgent();
	
		return vc;
		
	}
}

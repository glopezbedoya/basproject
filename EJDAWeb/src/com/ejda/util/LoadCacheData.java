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
}

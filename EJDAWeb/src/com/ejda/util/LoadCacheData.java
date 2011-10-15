package com.ejda.util;

import java.sql.SQLException;
import java.util.Vector;

import com.tcd.ejda.dao.Form1DAO;
import com.tcd.ejda.dao.Form1DAOImpl;

public class LoadCacheData {
	
	public static Vector GetCountryCache() throws SQLException{
		Vector vc = new Vector();
		
		Form1DAO dao = new Form1DAOImpl();
		vc = dao.LoadCountry();
		return vc;
		
	}

}

package com.tcd.ejda.dao;

import java.sql.SQLException;
import java.util.Vector;

public interface CacheDataDAO {

	public Vector LoadCountry()throws SQLException;
	public Vector LoadUnit()throws SQLException;
	public Vector LoadExchangeRAte()throws SQLException;
}

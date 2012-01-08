package com.tcd.ejda.dao;

import java.sql.SQLException;
import java.util.Vector;

public interface CacheDataDAO {

	public Vector LoadCountry()throws SQLException;
	public Vector LoadUnit()throws SQLException;
	public Vector LoadExchangeRAte()throws SQLException;
	public Vector LoadCustomTanli()throws SQLException;
	public Vector LoadCountryOrigin()throws SQLException;
	public Vector LoadImporter()throws SQLException;
	public Vector LoadAgent()throws SQLException;
	
	public String GetImporterAddress(String ImportCode)throws SQLException;
	public String GetAgentAddress(String AgentCode)throws SQLException;
}

package com.tcd.ejda.dao;

import java.sql.SQLException;
import java.util.Vector;

public interface RoleMenuDAO {
	
	public Vector getRoleMenu(String user_name) throws SQLException;

}

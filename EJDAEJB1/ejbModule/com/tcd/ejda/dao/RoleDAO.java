package com.tcd.ejda.dao;

import java.sql.SQLException;
import java.util.Vector;

import com.tcd.ejda.model.RoleModel;

public interface RoleDAO {
	
	public boolean insertRole(RoleModel roleModel, Vector vc) throws SQLException;
	public Vector selsectRole(String RoleName) throws SQLException;
	

}

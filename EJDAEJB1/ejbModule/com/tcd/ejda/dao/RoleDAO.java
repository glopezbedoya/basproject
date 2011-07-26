package com.tcd.ejda.dao;

import java.sql.SQLException;
import java.util.Vector;

import com.tcd.ejda.model.RoleModel;

public interface RoleDAO {
	
	public boolean insertRole(RoleModel roleModel, Vector vc) throws SQLException;
	public Vector selectRole(String RoleName) throws SQLException;
	public Vector selectRoleUser(String iv_user)throws SQLException;
	public Vector selectMenufor(String role_id)throws SQLException;
	public Vector selectEditRole(String role_id)throws SQLException;
	public boolean updateRole(RoleModel roleModel, Vector vc) throws SQLException;
	public boolean deleteRole(String role_id) throws SQLException;
	public Vector selectUserRole(String jda_id)throws SQLException;
}

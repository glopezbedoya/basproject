package com.tcd.ejda.dao;

import java.sql.SQLException;
import java.util.Vector;

import com.tcd.ejda.model.UsrModel;

//import com.tcd.ejda.model.UserModel;

public interface UserDAO {
	
	public String checkUsernamePassword(String iv_user, String username, String pwd);
	public boolean addNewUser(UsrModel usrmodel, Vector role) throws SQLException;
	public boolean updateUser(UsrModel usrmodel, Vector role) throws SQLException;
	public Vector selectUserforUpdate(String iv_user,String user_name, String first_name, String last_name,String locked) throws SQLException;
	public boolean deleteUser(String jda_id) throws SQLException;
	
}

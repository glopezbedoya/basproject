package com.tcd.ejda.dao;

import java.util.Vector;

public interface UserDAO {
	
	public String checkUsernamePassword(String iv_user, String username, String pwd);

}

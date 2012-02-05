package com.tcd.ejda.dao;

import java.sql.SQLException;
import java.util.Vector;

import com.tcd.ejda.model.FormConfigModel;

public interface FormConfigDAO {

	public boolean insertFormConfig(Vector vc, String jda_type)throws SQLException;
	public Vector<FormConfigModel> searchFormConfigModel(String jda_type) throws SQLException;
}

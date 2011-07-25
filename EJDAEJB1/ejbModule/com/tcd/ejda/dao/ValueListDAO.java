package com.tcd.ejda.dao;

import java.sql.SQLException;

import com.tcd.ejda.model.ValueListModel;

public interface ValueListDAO {
	
	public ValueListModel getResult(ValueListModel valueListM) throws SQLException;
	
}

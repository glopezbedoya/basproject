package com.tcd.ejda.dao;

import java.sql.SQLException;

import com.tcd.ejda.model.Form1Model;

public interface Form1DAO {
	public void saveFrom1Table1(Form1Model form1) throws SQLException;
	public boolean UpdateFrom1Table(Form1Model form1) throws SQLException;
}

package com.tcd.ejda.dao;

import java.sql.SQLException;

import com.tcd.ejda.model.Form1Model;
import com.tcd.ejda.model.Form3Model;
import com.tcd.ejda.model.Form4Model;

public interface Form4DAO {
	public void saveFrom4Table1(Form4Model form4) throws SQLException;
	public boolean UpdateFrom4Table(Form4Model form4) throws SQLException;
}

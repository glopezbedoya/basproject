package com.tcd.ejda.dao;

import java.sql.SQLException;

import com.tcd.ejda.model.Form1Model;
import com.tcd.ejda.model.Form3Model;

public interface Form3DAO {
	public void saveFrom3Table1(Form3Model form3) throws SQLException;
	public boolean UpdateFrom3Table(Form3Model form3) throws SQLException;
}

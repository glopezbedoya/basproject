package com.tcd.ejda.dao;

import java.sql.SQLException;
import java.util.Vector;

import com.tcd.ejda.model.Form1Model;
import com.tcd.ejda.model.FormDetail2Model;

public interface Form1DAO {
	public void saveFrom1Table1(Form1Model form1) throws SQLException;
	public boolean UpdateFrom1Table(Form1Model form1) throws SQLException;
	public void saveFromEJDA(Form1Model form, Vector detail1, Vector detail2) throws SQLException;
	public boolean UpdateFromTable(Form1Model form1) throws SQLException;
}

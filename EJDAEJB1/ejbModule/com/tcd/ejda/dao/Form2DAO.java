package com.tcd.ejda.dao;

import java.sql.SQLException;

import com.tcd.ejda.model.Form1Model;
import com.tcd.ejda.model.Form2Model;

public interface Form2DAO {

	public void saveFrom2Table2(Form2Model form2Model) throws SQLException;
	public boolean UpdateFrom2Table(Form2Model form2Model) throws SQLException;
}

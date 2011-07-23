package com.tcd.ejda.dao;

import java.sql.SQLException;
import java.util.Vector;

import com.tcd.ejda.model.TransactionLogModel;

public interface TransactionLogDAO {

	public void insertTranLog(TransactionLogModel tranlog) throws SQLException;
	public Vector searchTransactionLog(TransactionLogModel tranLogCri) throws SQLException;
}

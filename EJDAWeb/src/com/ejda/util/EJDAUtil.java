package com.ejda.util;

import org.apache.log4j.Logger;

import com.tcd.ejda.dao.TransactionLogDAO;
import com.tcd.ejda.dao.TransactionLogDAOImpl;
import com.tcd.ejda.model.TransactionLogModel;

public class EJDAUtil {
	private Logger log = Logger.getLogger(EJDAUtil.class);
	
	public void insertTranLog(TransactionLogModel tranLogM){
		try{
			TransactionLogDAO dao = new TransactionLogDAOImpl();
			dao.insertTranLog(tranLogM);
		}catch (Exception e) {
			log.error("Error insertTranLog "+e);
		}
		
	}
}

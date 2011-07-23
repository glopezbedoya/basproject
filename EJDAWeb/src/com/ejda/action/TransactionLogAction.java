package com.ejda.action;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.tcd.ejda.dao.TransactionLogDAO;
import com.tcd.ejda.dao.TransactionLogDAOImpl;
import com.tcd.ejda.model.TransactionLogModel;

public class TransactionLogAction extends AbstractAction {

	private Logger log = Logger.getLogger(TransactionLogAction.class);
	
	@Override
	public void clearSessionNotUsed() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean methodAction(String ejdaAction, HttpServletRequest request) {
		// TODO Auto-generated method stub
		if(ejdaAction.equalsIgnoreCase("doSearch")){
			return doSearch(request);
		}
		
		return false;
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean doSearch(HttpServletRequest request){
		log.debug("*********** doSearch ***********");
		boolean result = false;
		try{
			Vector tranLogVt = new Vector();
			TransactionLogModel tranLogCri = new TransactionLogModel();
			TransactionLogDAO tranLog = new TransactionLogDAOImpl();
			tranLogVt = tranLog.searchTransactionLog(tranLogCri);
			request.getSession().setAttribute("TranLogResultSession", tranLogVt);
			result = true;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}

}

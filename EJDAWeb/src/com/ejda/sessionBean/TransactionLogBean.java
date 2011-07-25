package com.ejda.sessionBean;

import java.util.Vector;

import com.tcd.ejda.model.TransactionLogModel;
import com.tcd.ejda.model.ValueListModel;

public class TransactionLogBean extends AbstractBean {
	
	
	public TransactionLogModel tranLogModelSP;
	public Vector tranLogVt;

	public Vector getTranLogVt() {
		return tranLogVt;
	}

	public void setTranLogVt(Vector tranLogVt) {
		this.tranLogVt = tranLogVt;
	}

	public TransactionLogModel getTranLogModelSP() {
		return tranLogModelSP;
	}

	public void setTranLogModelSP(TransactionLogModel tranLogModelSP) {
		this.tranLogModelSP = tranLogModelSP;
	}
	
	
	
}

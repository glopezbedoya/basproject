package com.ejda.sessionBean;

import java.util.Vector;

import com.tcd.ejda.model.Form1Model;
import com.tcd.ejda.model.Form3Model;
import com.tcd.ejda.model.Form4Model;
import com.tcd.ejda.model.TransactionLogModel;

public class Form4Bean extends AbstractBean {
	
	public Form4Model form4ModelSP;
	public Vector form4Vt;
	public String actionName;
	
	
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public Form4Model getForm4ModelSP() {
		return form4ModelSP;
	}
	public void setForm4ModelSP(Form4Model form4ModelSP) {
		this.form4ModelSP = form4ModelSP;
	}
	public Vector getForm4Vt() {
		return form4Vt;
	}
	public void setForm4Vt(Vector form4Vt) {
		this.form4Vt = form4Vt;
	}
	
	
}

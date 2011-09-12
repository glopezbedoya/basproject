package com.ejda.sessionBean;

import java.util.Vector;

import com.tcd.ejda.model.Form1Model;
import com.tcd.ejda.model.Form3Model;
import com.tcd.ejda.model.TransactionLogModel;

public class Form3Bean extends AbstractBean {
	
	public Form3Model form3ModelSP;
	public Vector form3Vt;
	public String actionName;
	
	
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public Form3Model getForm3ModelSP() {
		return form3ModelSP;
	}
	public void setForm3ModelSP(Form3Model form3ModelSP) {
		this.form3ModelSP = form3ModelSP;
	}
	public Vector getForm3Vt() {
		return form3Vt;
	}
	public void setForm3Vt(Vector form3Vt) {
		this.form3Vt = form3Vt;
	}
	
	
}

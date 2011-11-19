package com.ejda.sessionBean;

import java.util.Vector;

import com.tcd.ejda.model.Form1Model;
import com.tcd.ejda.model.Form3Model;
import com.tcd.ejda.model.TransactionLogModel;

public class Form3Bean extends AbstractBean {
	
	public Form1Model form3ModelSP;
	public Vector form3Vt;
	public String actionName;
	public Vector detail1MVt;
	public Vector detail2MVt;
	public Vector docAttachMVt;
	public Vector unitVt;
	
	
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public Form1Model getForm3ModelSP() {
		return form3ModelSP;
	}
	public void setForm3ModelSP(Form1Model form3ModelSP) {
		this.form3ModelSP = form3ModelSP;
	}
	public Vector getForm3Vt() {
		return form3Vt;
	}
	public void setForm3Vt(Vector form3Vt) {
		this.form3Vt = form3Vt;
	}
	public Vector getDetail1MVt() {
		return detail1MVt;
	}
	public void setDetail1MVt(Vector detail1mVt) {
		detail1MVt = detail1mVt;
	}
	public Vector getDetail2MVt() {
		return detail2MVt;
	}
	public void setDetail2MVt(Vector detail2mVt) {
		detail2MVt = detail2mVt;
	}
	public Vector getDocAttachMVt() {
		return docAttachMVt;
	}
	public void setDocAttachMVt(Vector docAttachMVt) {
		this.docAttachMVt = docAttachMVt;
	}
	public Vector getUnitVt() {
		return unitVt;
	}
	public void setUnitVt(Vector unitVt) {
		this.unitVt = unitVt;
	}
	
	
}

package com.ejda.sessionBean;

import java.util.Vector;

import com.tcd.ejda.model.Form2Model;

public class Form2Bean extends AbstractBean {
	
	public Form2Model form2ModelSP;
	public Vector form2Vt;
	public String actionName;
	
	
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public Vector getForm2Vt() {
		return form2Vt;
	}
	public void setForm2Vt(Vector form2Vt) {
		this.form2Vt = form2Vt;
	}
	public Form2Model getForm2ModelSP() {
		return form2ModelSP;
	}
	public void setForm2ModelSP(Form2Model form2ModelSP) {
		this.form2ModelSP = form2ModelSP;
	}
	
	
}

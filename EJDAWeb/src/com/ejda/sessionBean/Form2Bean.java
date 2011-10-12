package com.ejda.sessionBean;

import java.util.Vector;

import com.tcd.ejda.model.Form1Model;
import com.tcd.ejda.model.Form2Model;
import com.tcd.ejda.model.FormDetail1Model;
import com.tcd.ejda.model.FormDetail2Model;

public class Form2Bean extends AbstractBean {
	
	public Form1Model form2ModelSP;
	public Vector form2Vt;
	public String actionName;
	public FormDetail1Model detail1ModelSP;
	public FormDetail2Model detail2ModelSP;
	
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
	
	
	public Form1Model getForm2ModelSP() {
		return form2ModelSP;
	}
	public void setForm2ModelSP(Form1Model form2ModelSP) {
		this.form2ModelSP = form2ModelSP;
	}
	public FormDetail1Model getDetail1ModelSP() {
		return detail1ModelSP;
	}
	public void setDetail1ModelSP(FormDetail1Model detail1ModelSP) {
		this.detail1ModelSP = detail1ModelSP;
	}
	public FormDetail2Model getDetail2ModelSP() {
		return detail2ModelSP;
	}
	public void setDetail2ModelSP(FormDetail2Model detail2ModelSP) {
		this.detail2ModelSP = detail2ModelSP;
	}
		
}

package com.ejda.sessionBean;

import java.util.Vector;

import com.tcd.ejda.model.FormConfigModel;
import com.tcd.ejda.model.RoleModel;

public class FormConfigBean extends AbstractBean {
	
	private String Action;
	public Vector FormConfigVt;
	public Vector FormPermissionVt;
	public FormConfigModel formConfigModelSP;
	
	public String getAction() {
		return Action;
	}

	public void setAction(String action) {
		Action = action;
	}

	public Vector getFormConfigVt() {
		return FormConfigVt;
	}

	public void setFormConfigVt(Vector formConfigVt) {
		FormConfigVt = formConfigVt;
	}

	public FormConfigModel getFormConfigModelSP() {
		return formConfigModelSP;
	}

	public void setFormConfigModelSP(FormConfigModel formConfigModelSP) {
		this.formConfigModelSP = formConfigModelSP;
	}

	public Vector getFormPermissionVt() {
		return FormPermissionVt;
	}

	public void setFormPermissionVt(Vector formPermissionVt) {
		FormPermissionVt = formPermissionVt;
	}
	
}

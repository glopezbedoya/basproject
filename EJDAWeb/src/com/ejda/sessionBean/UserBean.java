package com.ejda.sessionBean;

import java.util.Vector;

import com.tcd.ejda.model.UsrModel;

public class UserBean extends AbstractBean {
	
	public UsrModel usrMSP;
	public Vector usrVt;
	public UsrModel getUsrMSP() {
		return usrMSP;
	}
	public void setUsrMSP(UsrModel usrMSP) {
		this.usrMSP = usrMSP;
	}
	public Vector getUsrVt() {
		return usrVt;
	}
	public void setUsrVt(Vector usrVt) {
		this.usrVt = usrVt;
	}
}

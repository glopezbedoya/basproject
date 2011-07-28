package com.ejda.sessionBean;

import java.util.Vector;

import com.tcd.ejda.model.RoleModel;

public class RoleBean extends AbstractBean {
	
	public RoleModel roleMSP;
	public Vector roleVt;

	public Vector getRoleVt() {
		return roleVt;
	}

	public void setRoleVt(Vector roleVt) {
		this.roleVt = roleVt;
	}

	public RoleModel getRoleMSP() {
		return roleMSP;
	}

	public void setRoleMSP(RoleModel roleMSP) {
		this.roleMSP = roleMSP;
	}
	
}

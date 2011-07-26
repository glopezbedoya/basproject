package com.tcd.ejda.model;

import java.sql.Date;

public class UserRoleModel {
	private String jda_id;
	private String iv_user;
	private String role_id;
	private String create_by;
	private String update_by;
	
	private Date create_date;
	private Date update_date;
	
	
	public String getJda_id() {
		return jda_id;
	}
	public void setJda_id(String jdaId) {
		jda_id = jdaId;
	}
	public String getIv_user() {
		return iv_user;
	}
	public void setIv_user(String ivUser) {
		iv_user = ivUser;
	}
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String roleId) {
		role_id = roleId;
	}
	public String getCreate_by() {
		return create_by;
	}
	public void setCreate_by(String createBy) {
		create_by = createBy;
	}
	public String getUpdate_by() {
		return update_by;
	}
	public void setUpdate_by(String updateBy) {
		update_by = updateBy;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date createDate) {
		create_date = createDate;
	}
	public Date getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(Date updateDate) {
		update_date = updateDate;
	}
	
	

}

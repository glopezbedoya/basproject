package com.tcd.ejda.model;

import java.sql.Date;

public class FormConfigModel {
	private String jda_type;
	private String role_id;
	private String input_field;
	private String name_field;
	private String permissions;
	private String user_name;
	
	private Date create_date;
	private Date update_date;
	public String getJda_type() {
		return jda_type;
	}
	public void setJda_type(String jdaType) {
		jda_type = jdaType;
	}
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String roleId) {
		role_id = roleId;
	}
	public String getInput_field() {
		return input_field;
	}
	public void setInput_field(String inputField) {
		input_field = inputField;
	}
	public String getName_field() {
		return name_field;
	}
	public void setName_field(String nameField) {
		name_field = nameField;
	}
	public String getPermissions() {
		return permissions;
	}
	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String userName) {
		user_name = userName;
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

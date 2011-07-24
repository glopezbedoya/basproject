package com.tcd.ejda.model;

import java.sql.Date;

public class RoleFunctionModel {
	private String role_func_id;
	private String create_by;
	private String update_by;
	private String menu_id;
	private String role_id;
	
	private String func_add;
	private String func_del;
	private String func_update;
	private String func_inqs;
	
	private String menu_status;
	private String menu_name;
	
	private Date create_date;
	private Date update_date;
	
	
	public String getMenu_status() {
		return menu_status;
	}
	public void setMenu_status(String menuStatus) {
		menu_status = menuStatus;
	}
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menuName) {
		menu_name = menuName;
	}
	public String getRole_func_id() {
		return role_func_id;
	}
	public void setRole_func_id(String roleFuncId) {
		role_func_id = roleFuncId;
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
	public String getMenu_id() {
		return menu_id;
	}
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String roleId) {
		role_id = roleId;
	}
	public void setMenu_id(String menuId) {
		menu_id = menuId;
	}
	public String getFunc_add() {
		return func_add;
	}
	public void setFunc_add(String funcAdd) {
		func_add = funcAdd;
	}
	public String getFunc_del() {
		return func_del;
	}
	public void setFunc_del(String funcDel) {
		func_del = funcDel;
	}
	public String getFunc_update() {
		return func_update;
	}
	public void setFunc_update(String funcUpdate) {
		func_update = funcUpdate;
	}
	public String getFunc_inqs() {
		return func_inqs;
	}
	public void setFunc_inqs(String funcInqs) {
		func_inqs = funcInqs;
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

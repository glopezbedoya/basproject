package com.tcd.ejda.model;

import java.sql.Date;

public class MenuModel {
	private String menu_id;
	private String menu_name;
	private String menu_status;
	private String menu_description;
	private String create_by;
	private String update_by;
	private String menu_owner;
	private String menu_linked;
	
	private Date create_date;
	private Date update_date;
	
	private int menu_sort;
	
	public String getMenu_linked() {
		return menu_linked;
	}
	public void setMenu_linked(String menuLinked) {
		menu_linked = menuLinked;
	}
	public String getMenu_status() {
		return menu_status;
	}
	public void setMenu_status(String menuStatus) {
		menu_status = menuStatus;
	}
	public String getMenu_description() {
		return menu_description;
	}
	public void setMenu_description(String menuDescription) {
		menu_description = menuDescription;
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
	public String getMenu_owner() {
		return menu_owner;
	}
	public void setMenu_owner(String menuOwner) {
		menu_owner = menuOwner;
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
	public int getMenu_sort() {
		return menu_sort;
	}
	public void setMenu_sort(int menuSort) {
		menu_sort = menuSort;
	}
	public String getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(String menuId) {
		menu_id = menuId;
	}
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menuName) {
		menu_name = menuName;
	}

}

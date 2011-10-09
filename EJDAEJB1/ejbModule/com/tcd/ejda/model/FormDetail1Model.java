package com.tcd.ejda.model;

import java.sql.Date;

public class FormDetail1Model {

	private String Item_no;
	private String doc_id;
	private String marks_no;
	private String no_type_package;
	private String good_desc;
	private String cust_code;
	private String cust_unit;
	private Date Create_Date;
	private String Create_By;
	private Date update_Date;
	private String Update_by;
	public String getItem_no() {
		return Item_no;
	}
	public void setItem_no(String itemNo) {
		Item_no = itemNo;
	}
	public String getDoc_id() {
		return doc_id;
	}
	public void setDoc_id(String docId) {
		doc_id = docId;
	}
	public String getMarks_no() {
		return marks_no;
	}
	public void setMarks_no(String marksNo) {
		marks_no = marksNo;
	}
	public String getNo_type_package() {
		return no_type_package;
	}
	public void setNo_type_package(String noTypePackage) {
		no_type_package = noTypePackage;
	}
	public String getGood_desc() {
		return good_desc;
	}
	public void setGood_desc(String goodDesc) {
		good_desc = goodDesc;
	}
	public String getCust_code() {
		return cust_code;
	}
	public void setCust_code(String custCode) {
		cust_code = custCode;
	}
	public String getCust_unit() {
		return cust_unit;
	}
	public void setCust_unit(String custUnit) {
		cust_unit = custUnit;
	}
	public Date getCreate_Date() {
		return Create_Date;
	}
	public void setCreate_Date(Date createDate) {
		Create_Date = createDate;
	}
	public String getCreate_By() {
		return Create_By;
	}
	public void setCreate_By(String createBy) {
		Create_By = createBy;
	}
	public Date getUpdate_Date() {
		return update_Date;
	}
	public void setUpdate_Date(Date updateDate) {
		update_Date = updateDate;
	}
	public String getUpdate_by() {
		return Update_by;
	}
	public void setUpdate_by(String updateBy) {
		Update_by = updateBy;
	}

}

package com.tcd.ejda.model;

import java.sql.Date;

public class FormDocAttachModel {

	private String ref_no;
	private String doc_id;
	private String doc_name;
	private String doc_path;
	private String doc_jda_type;
	private String doc_status;
	private Date Create_Date;
	private String Create_By;
	private Date update_Date;
	private String Update_by;
	
	public String getRef_no() {
		return ref_no;
	}
	public void setRef_no(String refNo) {
		ref_no = refNo;
	}
	public String getDoc_id() {
		return doc_id;
	}
	public void setDoc_id(String docId) {
		doc_id = docId;
	}
	public String getDoc_name() {
		return doc_name;
	}
	public void setDoc_name(String docName) {
		doc_name = docName;
	}
	public String getDoc_path() {
		return doc_path;
	}
	public void setDoc_path(String docPath) {
		doc_path = docPath;
	}
	public String getDoc_jda_type() {
		return doc_jda_type;
	}
	public void setDoc_jda_type(String docJdaType) {
		doc_jda_type = docJdaType;
	}
	public String getDoc_status() {
		return doc_status;
	}
	public void setDoc_status(String docStatus) {
		doc_status = docStatus;
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

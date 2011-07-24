package com.tcd.ejda.model;


import java.sql.Date;

public class UsrModel {
	private String jda_id;
	private String IV_USER;
	private String USERNAME;
	private String FIRSTNAME;
	private String LASTNAME;
	private String DEPARTMENT;
	private String USER_STATUS;
	private String USER_IP;
	private String PWD;
	private String CONPWD;
	private String create_by;
	private String update_by;
	
	private String show_edit;
	private String show_delete;
	
	private Date EFFECTIVE_DATE;
	private Date EXPIRY_DATE;
	private Date create_date;
	private Date update_date;
	
	public String getJda_id() {
		return jda_id;
	}
	public void setJda_id(String jdaId) {
		jda_id = jdaId;
	}
	public String getShow_edit() {
		return show_edit;
	}
	public void setShow_edit(String showEdit) {
		show_edit = showEdit;
	}
	public String getShow_delete() {
		return show_delete;
	}
	public void setShow_delete(String showDelete) {
		show_delete = showDelete;
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
	public String getPWD() {
		return PWD;
	}
	public void setPWD(String pWD) {
		PWD = pWD;
	}
	public String getCONPWD() {
		return CONPWD;
	}
	public void setCONPWD(String cONPWD) {
		CONPWD = cONPWD;
	}
	public String getIV_USER() {
		return IV_USER;
	}
	public void setIV_USER(String iVUSER) {
		IV_USER = iVUSER;
	}
	public String getUSERNAME() {
		return USERNAME;
	}
	public void setUSERNAME(String uSERNAME) {
		USERNAME = uSERNAME;
	}
	public String getFIRSTNAME() {
		return FIRSTNAME;
	}
	public void setFIRSTNAME(String fIRSTNAME) {
		FIRSTNAME = fIRSTNAME;
	}
	public String getLASTNAME() {
		return LASTNAME;
	}
	public void setLASTNAME(String lASTNAME) {
		LASTNAME = lASTNAME;
	}
	public String getDEPARTMENT() {
		return DEPARTMENT;
	}
	public void setDEPARTMENT(String dEPARTMENT) {
		DEPARTMENT = dEPARTMENT;
	}
	public String getUSER_STATUS() {
		return USER_STATUS;
	}
	public void setUSER_STATUS(String uSERSTATUS) {
		USER_STATUS = uSERSTATUS;
	}
	public String getUSER_IP() {
		return USER_IP;
	}
	public void setUSER_IP(String uSERIP) {
		USER_IP = uSERIP;
	}
	public Date getEFFECTIVE_DATE() {
		return EFFECTIVE_DATE;
	}
	public void setEFFECTIVE_DATE(Date eFFECTIVEDATE) {
		EFFECTIVE_DATE = eFFECTIVEDATE;
	}
	public Date getEXPIRY_DATE() {
		return EXPIRY_DATE;
	}
	public void setEXPIRY_DATE(Date eXPIRYDATE) {
		EXPIRY_DATE = eXPIRYDATE;
	}
	
	

}


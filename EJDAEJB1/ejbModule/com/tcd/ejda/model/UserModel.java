package com.tcd.ejda.model;

import java.sql.Date;

public class UserModel {
	
	private String IV_USER;
	private String USERNAME;
	private String FIRSTNAME;
	private String LASTNAME;
	private String DEPARTMENT;
	private String USER_STATUS;
	private String USER_IP;
	
	private Date EFFECTIVE_DATE;
	private Date EXPIRY_DATE;
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

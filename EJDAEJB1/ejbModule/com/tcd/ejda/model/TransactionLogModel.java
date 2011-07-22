package com.tcd.ejda.model;

import java.sql.Date;

public class TransactionLogModel {
	
	private String tranId;
	private String tranAction;
	private String description;
	private String ipAddress;
	private Date tranDate;
	private String tranBy;
	
	public String getTranId() {
		return tranId;
	}
	public void setTranId(String tranId) {
		this.tranId = tranId;
	}
	public String getTranAction() {
		return tranAction;
	}
	public void setTranAction(String tranAction) {
		this.tranAction = tranAction;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public Date getTranDate() {
		return tranDate;
	}
	public void setTranDate(Date tranDate) {
		this.tranDate = tranDate;
	}
	public String getTranBy() {
		return tranBy;
	}
	public void setTranBy(String tranBy) {
		this.tranBy = tranBy;
	}
	
	
}

package com.tcd.ejda.model;

import java.util.Date;

public class TransactionLogModel {
	
	private String tranId;
	private String tranAction;
	private String description;
	private String ipAddress;
	private Date tranDate;
	private String tranBy;
	private String firstName;
	private String lastName;
	private String department;
	private String tranDateFrom;
	private String tranDateTo;
	private String menuId;
	private String userName;
	
	
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
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getTranDateFrom() {
		return tranDateFrom;
	}
	public void setTranDateFrom(String tranDateFrom) {
		this.tranDateFrom = tranDateFrom;
	}
	public String getTranDateTo() {
		return tranDateTo;
	}
	public void setTranDateTo(String tranDateTo) {
		this.tranDateTo = tranDateTo;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}

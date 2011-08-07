package com.tcd.ejda.model;

import java.util.Vector;

public class ValueListModel {

	private int atPage = 1;
    private int itemsPerPage = 20;
    private String SQL = "";
    private boolean nextPage = false;
    private int count = 0;
    private String returnToPage = "";
    private String returnToAction = "";
    private String returnModel;
    private boolean firstLog;
    private Vector result = new Vector();
    private String language;
    private String orderBy = "";
    private String orderScheme = "";
    private Vector parameters = new Vector();
    private String page;
    
	public int getAtPage() {
		return atPage;
	}
	public void setAtPage(int atPage) {
		this.atPage = atPage;
	}
	public int getItemsPerPage() {
		return itemsPerPage;
	}
	public void setItemsPerPage(int itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}
	public String getSQL() {
		return SQL;
	}
	public void setSQL(String sQL) {
		SQL = sQL;
	}
	public boolean isNextPage() {
		return nextPage;
	}
	public void setNextPage(boolean nextPage) {
		this.nextPage = nextPage;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getReturnToPage() {
		return returnToPage;
	}
	public void setReturnToPage(String returnToPage) {
		this.returnToPage = returnToPage;
	}
	public String getReturnToAction() {
		return returnToAction;
	}
	public void setReturnToAction(String returnToAction) {
		this.returnToAction = returnToAction;
	}
	public String getReturnModel() {
		return returnModel;
	}
	public void setReturnModel(String returnModel) {
		this.returnModel = returnModel;
	}
	public boolean isFirstLog() {
		return firstLog;
	}
	public void setFirstLog(boolean firstLog) {
		this.firstLog = firstLog;
	}
	public Vector getResult() {
		return result;
	}
	public void setResult(Vector result) {
		this.result = result;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public String getOrderScheme() {
		return orderScheme;
	}
	public void setOrderScheme(String orderScheme) {
		this.orderScheme = orderScheme;
	}
	public Vector getParameters() {
		return parameters;
	}
	public void setParameters(Vector parameters) {
		this.parameters = parameters;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
    
}

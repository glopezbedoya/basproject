package com.ejda.sessionBean;

public class ReportBean extends AbstractBean {
	private String reportType;
	public String actionName;

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	
}

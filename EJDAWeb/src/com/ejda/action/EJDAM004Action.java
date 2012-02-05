package com.ejda.action;

import org.apache.log4j.Logger;

import com.ejda.sessionBean.ReportBean;

public class EJDAM004Action extends AbstractAction {

	private Logger log = Logger.getLogger(EJDAM006Action.class);
	
	public ReportBean getReportBean() {
		ReportBean reportBean = (ReportBean)getRequest().getSession().getAttribute("reportBean");
		if (null == reportBean){
			reportBean = new ReportBean();
		}
		return reportBean;
	}

	public void setReportBean(ReportBean reportBean) {
		getRequest().getSession().setAttribute("reportBean", reportBean);
	}
	
	@Override
	public void clearSessionNotUsed() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
	}

	@Override
	public boolean methodAction(String ejdaMethod) throws Exception {
		if(ejdaMethod.equalsIgnoreCase("selectReportType")){
			selectReportType();
		}
		return false;
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void selectReportType(){
		
	}
	

}

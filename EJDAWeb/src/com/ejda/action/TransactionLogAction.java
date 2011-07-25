package com.ejda.action;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.ejda.constant.EJDAConstant;
import com.ejda.sessionBean.TransactionLogBean;
import com.tcd.ejda.dao.TransactionLogDAO;
import com.tcd.ejda.dao.TransactionLogDAOImpl;
import com.tcd.ejda.model.TransactionLogModel;
import com.tcd.ejda.model.ValueListModel;

public class TransactionLogAction extends AbstractAction {

	private Logger log = Logger.getLogger(TransactionLogAction.class);
	private TransactionLogBean tranLogBean;
	
	@Override
	public void clearSessionNotUsed() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		log.debug("*********** init ***********");
		tranLogBean = getTranLogBean();
		tranLogBean.setTranLogVt(new Vector<TransactionLogModel>());
		ValueListModel valueListM = new ValueListModel();
		valueListM.setReturnModel("TransactionLogModel");
		tranLogBean.setValueListM(valueListM);
		setTranLogBean(tranLogBean);
	}

	@Override
	public boolean methodAction(String ejdaMethod) {
		// TODO Auto-generated method stub
		if(ejdaMethod.equalsIgnoreCase("doSearch")){
			return doSearch();
		}
		
		return false;
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean doSearch(){
		log.debug("*********** doSearch ***********");
		log.debug("ejdaMethod = "+getRequest().getParameter("ejdaMethod"));
		boolean result = false;
		setCriteriaPameter();
		tranLogBean = getTranLogBean();
		ValueListModel valueListM = new ValueListModel();
		ValueListAction valueListA = new ValueListAction();
		log.debug("getRequest().getParameter(Page)"+getRequest().getParameter("Page"));
		try{
			Vector tranLogVt = new Vector();
			
			
//			TransactionLogDAO tranLog = new TransactionLogDAOImpl();
//			tranLogVt = tranLog.searchTransactionLog(tranLogCri);
			valueListM = tranLogBean.getValueListM();
			valueListM.setSQL(this.setSQL(tranLogBean.tranLogModelSP));
			valueListM.setParameters(getValueListParameters());
			valueListM.setPage(getRequest().getParameter("page"));
			valueListM.setItemsPerPage(Integer.parseInt(getRequest().getParameter("volumePerPage")));
			tranLogBean.setValueListM(valueListA.doSearch(valueListM));
			tranLogBean.setTranLogVt(tranLogBean.getValueListM().getResult());
			log.debug("tranLogVt.size = " + tranLogBean.getTranLogVt().size());
			
			getRequest().getSession().removeAttribute("VALUE_LIST");
			setTranLogBean(tranLogBean);
			result = true;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}
	
	public TransactionLogBean getTranLogBean(){
		TransactionLogBean tranLogBean = (TransactionLogBean) getRequest().getSession().getAttribute("TransactionLogBean");
		if(tranLogBean == null){
			tranLogBean = new TransactionLogBean();
		}
		return tranLogBean;
	}
	
	public void setTranLogBean(TransactionLogBean tranLogBean){
		getRequest().getSession().setAttribute("TransactionLogBean", tranLogBean);
	}
	
	private void setCriteriaPameter(){
		TransactionLogModel tranLogCri = new TransactionLogModel();
		tranLogCri.setTranDateFrom(parseParameterToDate(getRequest().getParameter("txtTranDateFrom")));
		tranLogCri.setTranDateTo(parseParameterToDate(getRequest().getParameter("txtTranDateTo")));
		tranLogCri.setUserName(getRequest().getParameter("txtUserName"));
		tranLogCri.setFirstName(getRequest().getParameter("txtFirstName"));
		tranLogCri.setLastName(getRequest().getParameter("txtLastName"));
		
		getTranLogBean().setTranLogModelSP(tranLogCri);
	}
	
	protected Vector getValueListParameters() {
		Vector parameters = new Vector();
		TransactionLogModel tranLogCri = getTranLogBean().getTranLogModelSP();
		if(!"".equals(tranLogCri.getUserName()) || !"".equals(tranLogCri.getFirstName()) || !"".equals(tranLogCri.getLastName())
				|| (null != tranLogCri.getTranDateFrom() && null != tranLogCri.getTranDateTo())){
			if(!"".equals(tranLogCri.getUserName().trim())) {
				log.debug("User = "+tranLogCri.getUserName());
				parameters.add(tranLogCri.getUserName());
			}
			if(!"".equals(tranLogCri.getFirstName().trim())) {
				log.debug("First Name = "+tranLogCri.getFirstName());
				parameters.add("%"+tranLogCri.getFirstName()+"%");
			}
			if(!"".equals(tranLogCri.getLastName().trim())) {
				log.debug("First Name = "+tranLogCri.getLastName());
				parameters.add("%"+tranLogCri.getLastName()+"%");
			}
		}
		log.info("parameters.size() = "+parameters.size());
		return parameters;
	}
	
	private String setSQL(TransactionLogModel tranLogCri){
		StringBuffer sql = new StringBuffer();
		try{
			sql.append(EJDAConstant.SQL.TRAN_LOG_SQL);
			
			if(!"".equals(tranLogCri.getUserName()) || !"".equals(tranLogCri.getFirstName()) || !"".equals(tranLogCri.getLastName())
				|| (null != tranLogCri.getTranDateFrom() && null != tranLogCri.getTranDateTo())){
				sql.append(" WHERE ");
				if(!"".equals(tranLogCri.getUserName()))
					sql.append(" U.USER_NAME = ? AND ");
				if(!"".equals(tranLogCri.getFirstName()))
					sql.append(" U.FIRST_NAME = ? AND ");
				if(!"".equals(tranLogCri.getLastName()))
					sql.append(" U.LAST_NAME = ? AND ");
			}
//			if(!"".equals(marketingCode.trim()) || !"".equals(marketingName.trim()) || !"".equals(applicationCode.trim())){
//				sql.append(" WHERE ");
//				if(!"".equals(marketingCode.trim())){
//					sql.append(" UPPER(CMR_CODE) = ? AND ");
//				}
//				if(!"".equals(marketingName.trim())){
//					sql.append(" UPPER(CMR_NAME_TH) LIKE ? AND");
//				}
//				if(!"".equals(applicationCode.trim())){
//					sql.append(" MATCH_APP_CODE = ? AND ");
//				}
//			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		sql = removeWasteSQL(sql);
		return sql.toString();
	}
	
	
}

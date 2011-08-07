package com.ejda.action;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.ejda.constant.EJDAConstant;
import com.ejda.sessionBean.TransactionLogBean;
import com.ejda.util.EJDAUtil;
import com.tcd.ejda.dao.TransactionLogDAO;
import com.tcd.ejda.dao.TransactionLogDAOImpl;
import com.tcd.ejda.model.TransactionLogModel;
import com.tcd.ejda.model.ValueListModel;

public class EJDAM008Action extends AbstractAction {

	private Logger log = Logger.getLogger(EJDAM008Action.class);
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
		tranLogBean.setTranLogModelSP(new TransactionLogModel());
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
		}else if(ejdaMethod.equalsIgnoreCase("doDelete")){
			return doDelete();
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
//		log.debug("ejdaMethod = "+getRequest().getParameter("ejdaMethod"));
		boolean result = false;
		setCriteriaPameter();
		tranLogBean = getTranLogBean();
		ValueListModel valueListM = new ValueListModel();
		ValueListAction valueListA = new ValueListAction();
		log.debug("getRequest().getParameter(Page)"+getRequest().getParameter("Page"));
		try{
			Vector tranLogVt = new Vector();
			valueListM = tranLogBean.getValueListM();
			valueListM.setSQL(this.setSQL(tranLogBean.tranLogModelSP));
			valueListM.setParameters(getValueListParameters());
			valueListM.setPage(getRequest().getParameter("page"));
			valueListM.setItemsPerPage(Integer.parseInt(getRequest().getParameter("volumePerPage")));
			tranLogBean.setValueListM(valueListA.doSearch(valueListM));
			tranLogBean.setTranLogVt(tranLogBean.getValueListM().getResult());
			log.debug("tranLogVt.size = " + tranLogBean.getTranLogVt().size());
			log.debug("tranLogBean.getValueListM().getCount() = "+tranLogBean.getValueListM().getCount());
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
		tranLogCri.setTranDateFrom(getRequest().getParameter("txtTranDateFrom"));
		tranLogCri.setTranDateTo(getRequest().getParameter("txtTranDateTo"));
		tranLogCri.setUserName(getRequest().getParameter("txtUserName"));
		tranLogCri.setFirstName(getRequest().getParameter("txtFirstName"));
		tranLogCri.setLastName(getRequest().getParameter("txtLastName"));
		
		getTranLogBean().setTranLogModelSP(tranLogCri);
	}
	
	private String setSQL(TransactionLogModel tranLogCri){
		StringBuffer sql = new StringBuffer();
		try{
			sql.append(EJDAConstant.SQL.TRAN_LOG_SQL);
			
			if(!"".equals(tranLogCri.getUserName()) || !"".equals(tranLogCri.getFirstName()) || !"".equals(tranLogCri.getLastName())
				|| (!"".equals(tranLogCri.getTranDateFrom()) && !"".equals(tranLogCri.getTranDateTo()))){
				sql.append(" WHERE ");
				if(!"".equals(tranLogCri.getUserName()))
					sql.append(" U.USER_NAME = ? AND ");
				if(!"".equals(tranLogCri.getFirstName()))
					sql.append(" U.FIRST_NAME = ? AND ");
				if(!"".equals(tranLogCri.getLastName()))
					sql.append(" U.LAST_NAME = ? AND ");
				if(!"".equals(tranLogCri.getTranDateFrom()) && !"".equals(tranLogCri.getTranDateTo())){
					sql.append(" L.TRANS_DATE BETWEEN TO_DATE(?,'dd/mm/yyyy HH24:MI') AND TO_DATE(?,'dd/mm/yyyy HH24:MI') AND ");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		sql = removeWasteSQL(sql);
		return sql.toString();
	}
	
	private Vector getValueListParameters() {
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
				log.debug("Last Name = "+tranLogCri.getLastName());
				parameters.add("%"+tranLogCri.getLastName()+"%");
			}
			if(!"".equals(tranLogCri.getTranDateFrom()) && !"".equals(tranLogCri.getTranDateTo())){
				parameters.add(tranLogCri.getTranDateFrom() + "00:00");
				parameters.add(tranLogCri.getTranDateTo() + "23:59");
			}
		}
		log.info("parameters.size() = "+parameters.size());
		return parameters;
	}
	
	public boolean doDelete(){
		log.debug("********** doDelete **********");
		boolean result = false;
		String ipAddress = getRequest().getRemoteAddr();
		String iuser = (String) getRequest().getSession().getAttribute("iuser");
		if (null==iuser || "".equals(iuser)){
			iuser = "system";
		}
		TransactionLogModel tranLogCri = new TransactionLogModel();
		String[] deleteTranId = (String[])getRequest().getParameterValues("checkBox");
		try{
			TransactionLogDAO dao = new TransactionLogDAOImpl();
			result = dao.deleteTransactionLog(deleteTranId);
			TransactionLogModel transactionLogModel = new TransactionLogModel() ;
			EJDAUtil ejda = new EJDAUtil();
			transactionLogModel.setMenuId("M008");
			transactionLogModel.setTranAction("DEL");
			transactionLogModel.setDescription("Delete Transaction");
			transactionLogModel.setIpAddress(ipAddress);
			transactionLogModel.setTranBy(iuser);
			ejda.insertTranLog(transactionLogModel);
			result = doSearch();
			
			log.debug("result = "+result);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}
	
}

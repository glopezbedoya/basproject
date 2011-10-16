package com.ejda.action;

import java.util.Vector;

import org.apache.log4j.Logger;

import com.ejda.constant.EJDAConstant;
import com.ejda.sessionBean.Form1Bean;
import com.ejda.util.EJDAUtil;
import com.tcd.ejda.dao.Form1DAO;
import com.tcd.ejda.dao.Form1DAOImpl;
import com.tcd.ejda.dao.TransactionLogDAO;
import com.tcd.ejda.dao.TransactionLogDAOImpl;
import com.tcd.ejda.model.Form1Model;
import com.tcd.ejda.model.FormDetail1Model;
import com.tcd.ejda.model.FormDetail2Model;
import com.tcd.ejda.model.FormDocAttachModel;
import com.tcd.ejda.model.TransactionLogModel;
import com.tcd.ejda.model.ValueListModel;

public class EJDAM018Action extends AbstractAction {

	private Logger log = Logger.getLogger(EJDAM018Action.class);
	private Form1Bean form1Bean;
	
	@Override
	public void clearSessionNotUsed() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		/** EJDA Form no 1****/
		log.debug("*********** EJDAM018Action ***********");
		
		form1Bean = getForm1Bean();
		form1Bean.setForm1Vt(new Vector<Form1Model>());
		form1Bean.setForm1ModelSP(new Form1Model());
		form1Bean.setDetail1ModelSP(new FormDetail1Model());
		form1Bean.setDetail2ModelSP(new FormDetail2Model());
		form1Bean.setDocAttachModelSP(new FormDocAttachModel());
		
		ValueListModel valueListM = new ValueListModel();
		valueListM.setReturnModel("Form1Model");
		form1Bean.setValueListM(valueListM);
		setForm1Bean(form1Bean);
	}

	@Override
	public boolean methodAction(String ejdaMethod)throws Exception  {
		// TODO Auto-generated method stub
		if(ejdaMethod.equalsIgnoreCase("doSearch")){
			return doSearch();
		}else if(ejdaMethod.equalsIgnoreCase("doDelete")){
			return doDelete();
		}else if (ejdaMethod.equalsIgnoreCase("doUpdate")){
			return doUpdate();
		}else if (ejdaMethod.equalsIgnoreCase("doSubmitButton")){
			return doSubmitButton();
		}
		
		
		return false;
	}
	private boolean doSubmitButton() throws Exception {
		boolean result = false;
		String iuser = (String) getRequest().getSession().getAttribute("iuser");
		String formNo = (String) getRequest().getSession().getAttribute("form_no");
		String ipAddress = getRequest().getRemoteAddr();
		
		if (null==iuser || "".equals(iuser)){
			iuser = "system";
		}
		EJDAM010Action ejdam010Action = new EJDAM010Action();
		ejdam010Action.setRequest(getRequest()); 
		Form1Model form1 = ejdam010Action.setValueModel("1","P",iuser);
		
		try{
			Form1DAO dao = new Form1DAOImpl();
			dao.UpdateFromTable(form1);
			
			TransactionLogModel transactionLogModel = new TransactionLogModel() ;
			EJDAUtil ejda = new EJDAUtil();
			transactionLogModel.setMenuId("M018");
			transactionLogModel.setTranAction("UPD");
			transactionLogModel.setDescription("Update EJDA Table 3 Form 1");
			transactionLogModel.setIpAddress(ipAddress);
			transactionLogModel.setTranBy(iuser);
			ejda.insertTranLog(transactionLogModel);
			
			getRequest().getSession().setAttribute("responseMessage", "Submit Form 1 Successfully.");
			result = doSearch();
			
			log.debug("result = "+result);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	private boolean doUpdate() {
		String form_no = getRequest().getParameter("form_no");
		getRequest().getSession().setAttribute("form_no", form_no);
		return true;
	}

	private boolean openForm1() {
		// TODO Auto-generated method stub
		log.debug("*********** doChangePage ***********");
		return true;
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean doSearch(){
		log.debug("*********** doSearch ***********");
		boolean result = false;
		setCriteriaPameter();
		form1Bean = getForm1Bean();
		ValueListModel valueListM = new ValueListModel();
		ValueListAction valueListA = new ValueListAction();
		log.debug("getRequest().getParameter(Page)"+getRequest().getParameter("Page"));
		try{
			Vector tranLogVt = new Vector();
			valueListM = form1Bean.getValueListM();
			valueListM.setSQL(this.setSQL(form1Bean.form1ModelSP));
			valueListM.setParameters(getValueListParameters());
			valueListM.setPage(getRequest().getParameter("page"));
			valueListM.setItemsPerPage(Integer.parseInt(getRequest().getParameter("volumePerPage")));
			
			form1Bean.setValueListM(valueListA.doSearch(valueListM));
			form1Bean.setForm1Vt(form1Bean.getValueListM().getResult());
			form1Bean.setActionName("EJDAM018");
			log.debug("form1Bean = " + form1Bean.getForm1Vt().size());
			log.debug("tranLogBean.getValueListM().getCount() = "+form1Bean.getValueListM().getCount());
			getRequest().getSession().removeAttribute("VALUE_LIST");
			setForm1Bean(form1Bean);
			result = true;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}
	
	public Form1Bean getForm1Bean() {
		Form1Bean form1Bean = (Form1Bean)getRequest().getSession().getAttribute("Form1Bean");
		if (null == form1Bean){
			form1Bean = new Form1Bean();
		}
		return form1Bean;
	}

	public void setForm1Bean(Form1Bean form1Bean) {
		getRequest().getSession().setAttribute("Form1Bean", form1Bean);
	}
	
private void setCriteriaPameter(){
		
		Form1Model form1 = new Form1Model();
		form1.setForm_name(getRequest().getParameter("txtFormName"));
		
		getForm1Bean().setForm1ModelSP(form1);
	}
	
	private String setSQL(Form1Model form1Cri){
		StringBuffer sql = new StringBuffer();
		StringBuffer sql1 = new StringBuffer();
		String sqlCommand ="";
		String sqlWhere="";
		try{
			sql.append(EJDAConstant.SQL.FORM_T_DOC_1);
			sql.append(" WHERE JDA_TYPE = '1' AND DOC_STATUS = 'S' ");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		sql = removeWasteSQL(sql);
		return sql.toString();
	}
	
	private Vector getValueListParameters() {
		Vector parameters = new Vector();
		Form1Model form1 = getForm1Bean().getForm1ModelSP();
		if (null!= form1.getForm_name() && !"".equals(form1.getForm_name())){
			log.debug("Form Name = "+form1.getForm_name());
			parameters.add(form1.getForm_name());
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
//			TransactionLogModel transactionLogModel = new TransactionLogModel() ;
//			EJDAUtil ejda = new EJDAUtil();
//			transactionLogModel.setMenuId("M008");
//			transactionLogModel.setTranAction("DEL");
//			transactionLogModel.setDescription("Delete Transaction");
//			transactionLogModel.setIpAddress(ipAddress);
//			transactionLogModel.setTranBy(iuser);
//			ejda.insertTranLog(transactionLogModel);
			result = doSearch();
			
			log.debug("result = "+result);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}
	
}

package com.ejda.action;

import java.util.Vector;

import org.apache.log4j.Logger;

import com.ejda.constant.EJDAConstant;
import com.ejda.sessionBean.Form2Bean;
import com.ejda.util.EJDAUtil;
import com.tcd.ejda.dao.Form2DAO;
import com.tcd.ejda.dao.Form2DAOImpl;
import com.tcd.ejda.dao.TransactionLogDAO;
import com.tcd.ejda.dao.TransactionLogDAOImpl;
import com.tcd.ejda.model.Form2Model;
import com.tcd.ejda.model.TransactionLogModel;
import com.tcd.ejda.model.ValueListModel;

public class EJDAM023Action extends AbstractAction {

	private Logger log = Logger.getLogger(EJDAM023Action.class);
	private Form2Bean form2Bean;
	
	@Override
	public void clearSessionNotUsed() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		/** EJDA Form no 2****/
		log.debug("*********** EJDAM023Action ***********");
		
		
		form2Bean = getform2Bean();
		form2Bean.setForm2Vt(new Vector<Form2Model>());
		form2Bean.setForm2ModelSP(new Form2Model());
		ValueListModel valueListM = new ValueListModel();
		valueListM.setReturnModel("form2Model");
		form2Bean.setValueListM(valueListM);
		setform2Bean(form2Bean);
	}

	@Override
	public boolean methodAction(String ejdaMethod) {
		// TODO Auto-generated method stub
		if(ejdaMethod.equalsIgnoreCase("doSearch")){
			return doSearch();
		
		}else if (ejdaMethod.equalsIgnoreCase("doAdd")){
			return doAdd();
		}else if (ejdaMethod.equalsIgnoreCase("doSubmitButton")){
			return doSubmitButton();
		
		}else if (ejdaMethod.equalsIgnoreCase("doUpdate")){
			return doUpdate();
		}
		
		
		return false;
	}
	
	private boolean doUpdate() {
		String form_no = getRequest().getParameter("form_no");
		getRequest().getSession().setAttribute("form_no", form_no);
		return true;
	}
	
	private boolean doSaveButton() {
		boolean result = false;
		String iuser = (String) getRequest().getSession().getAttribute("iuser");
		if (null==iuser || "".equals(iuser)){
			iuser = "system";
		}
		Form2Model form2 = new Form2Model();
		form2.setForm_name("FN_" + iuser);
		form2.setForm_status("D");
		form2.setUpdate_by(iuser);
		try{
//			form2DAO dao = new form2DAOImpl();
//			dao.saveFrom1Table1(form2);
			result = doSearch();
			
			log.debug("result = "+result);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	private boolean doSubmitButton() {
		log.debug("---Start : doSubmitButton---");
		boolean result = false;
		String iuser = (String) getRequest().getSession().getAttribute("iuser");
		String formNo = (String) getRequest().getSession().getAttribute("form_no");
		String ipAddress = getRequest().getRemoteAddr();
		
		log.debug("iuser = " + iuser);
		log.debug("formNo = " + formNo);
		if (null==iuser || "".equals(iuser)){
			iuser = "system";
		}
		Form2Model form2 = new Form2Model();
		form2.setForm_name("FN_" + iuser);
		form2.setForm_status("C");
		form2.setUpdate_by(iuser);
		form2.setForm_no(formNo);
		try{
			Form2DAO dao = new Form2DAOImpl();
			dao.UpdateFrom2Table(form2);
			
			TransactionLogModel transactionLogModel = new TransactionLogModel() ;
			EJDAUtil ejda = new EJDAUtil();
			transactionLogModel.setMenuId("M023");
			transactionLogModel.setTranAction("UPD");
			transactionLogModel.setDescription("Update EJDA Table 4 Form 2");
			transactionLogModel.setIpAddress(ipAddress);
			transactionLogModel.setTranBy(iuser);
			ejda.insertTranLog(transactionLogModel);
			
			getRequest().getSession().setAttribute("responseMessage", "Submit Form 2 Successfully.");
			doSearch();
			result = doSearch();
			
			log.debug("result = "+result);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	private boolean doAdd() {
		// TODO Auto-generated method stub
		log.debug("*********** doChangePage ***********");
		form2Bean = getform2Bean();
		form2Bean.setActionName("EJDAM023");
		return true;
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
		form2Bean = getform2Bean();
		ValueListModel valueListM = new ValueListModel();
		ValueListAction valueListA = new ValueListAction();
		log.debug("getRequest().getParameter(Page)"+getRequest().getParameter("Page"));
		try{
			Vector tranLogVt = new Vector();
			valueListM = form2Bean.getValueListM();
			valueListM.setSQL(this.setSQL(form2Bean.form2ModelSP));
			valueListM.setParameters(getValueListParameters());
			valueListM.setPage(getRequest().getParameter("page"));
			valueListM.setItemsPerPage(Integer.parseInt(getRequest().getParameter("volumePerPage")));
			
			form2Bean.setValueListM(valueListA.doSearch(valueListM));
			form2Bean.setForm2Vt(form2Bean.getValueListM().getResult());
			form2Bean.setActionName("EJDAM023");
			log.debug("form2Bean = " + form2Bean.getForm2Vt().size());
			log.debug("form2Bean.getValueListM().getCount() = "+form2Bean.getValueListM().getCount());
			log.debug("form2Bean setActionName = " + form2Bean.getActionName());
			getRequest().getSession().removeAttribute("VALUE_LIST");
			setform2Bean(form2Bean);
			result = true;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}
	
	public Form2Bean getform2Bean() {
		Form2Bean form2Bean = (Form2Bean)getRequest().getSession().getAttribute("form2Bean");
		if (null == form2Bean){
			form2Bean = new Form2Bean();
		}
		return form2Bean;
	}

	public void setform2Bean(Form2Bean form2Bean) {
		getRequest().getSession().setAttribute("form2Bean", form2Bean);
	}

	
	private void setCriteriaPameter(){
		
		Form2Model form2 = new Form2Model();
		form2.setForm_name(getRequest().getParameter("txtFormName"));
		
		getform2Bean().setForm2ModelSP(form2);
	}
	
	private String setSQL(Form2Model form2Cri){
		StringBuffer sql = new StringBuffer();
		StringBuffer sql1 = new StringBuffer();
		String sqlCommand ="";
		String sqlWhere="";
		try{
			sql.append(EJDAConstant.SQL.FORM2_TABLE4_SQL);
//			if (sql.indexOf("WHERE") != -1){
//				sqlWhere = sql.substring(sql.indexOf("WHERE"),sql.length());
//				sqlCommand = sql.substring(0, sql.lastIndexOf("WHERE"));
//			}
//			if (!"".equals(form2Cri.getForm_name())){
//				//sql.append(" WHERE ");
//				sqlWhere = " FORM_NAME = ? AND ";
//				
//			}
//			
//			log.debug("sqlWhere >> " + sqlWhere);
//			log.debug("sqlCommand >> " + sqlCommand);
//			
//			sql1.append(sqlCommand + " " + sqlWhere);
//			//sql.append(sql.toString());
//			log.debug("sql >> " + sql1.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		sql = removeWasteSQL(sql);
		return sql.toString();
	}
	
	private Vector getValueListParameters() {
		Vector parameters = new Vector();
		Form2Model form2 = getform2Bean().getForm2ModelSP();
		if (!"".equals(form2.getForm_name())){
			log.debug("Form Name = "+form2.getForm_name());
			parameters.add(form2.getForm_name());
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
			result = doSearch();
			
			log.debug("result = "+result);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}
	
}

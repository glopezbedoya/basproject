package com.ejda.action;

import java.util.Vector;

import org.apache.log4j.Logger;

import com.ejda.constant.EJDAConstant;
import com.ejda.sessionBean.Form4Bean;
import com.ejda.util.EJDAUtil;
import com.tcd.ejda.dao.Form1DAO;
import com.tcd.ejda.dao.Form1DAOImpl;
import com.tcd.ejda.dao.Form3DAO;
import com.tcd.ejda.dao.Form3DAOImpl;
import com.tcd.ejda.dao.Form4DAO;
import com.tcd.ejda.dao.Form4DAOImpl;
import com.tcd.ejda.model.Form1Model;
import com.tcd.ejda.model.Form4Model;
import com.tcd.ejda.model.TransactionLogModel;
import com.tcd.ejda.model.ValueListModel;

public class EJDAM013Action extends AbstractAction {

	private Logger log = Logger.getLogger(EJDAM013Action.class);
	private Form4Bean form4Bean;
	
	public Form4Bean getForm4Bean() {
		Form4Bean form4Bean = (Form4Bean)getRequest().getSession().getAttribute("form4Bean");
		if (null == form4Bean){
			form4Bean = new Form4Bean();
		}
		return form4Bean;
	}

	public void setForm4Bean(Form4Bean form4Bean) {
		getRequest().getSession().setAttribute("form4Bean", form4Bean);
	}
	
	@Override
	public void clearSessionNotUsed() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		/** EJDA Form no 3****/
		log.debug("*********** EJDAM013Action ***********");
		
		
		form4Bean = getForm4Bean();
		form4Bean.setForm4Vt(new Vector<Form4Model>());
		form4Bean.setForm4ModelSP(new Form1Model());
		ValueListModel valueListM = new ValueListModel();
		valueListM.setReturnModel("Form1Model");
		form4Bean.setValueListM(valueListM);
		setForm4Bean(form4Bean);
	}

	@Override
	public boolean methodAction(String ejdaMethod) {
		if(ejdaMethod.equalsIgnoreCase("doSearch")){
			return doSearch();
		}else if (ejdaMethod.equalsIgnoreCase("doAdd")){
			return doAdd();
		}else if (ejdaMethod.equalsIgnoreCase("doSubmitButton")){
			return doSubmitButton();
		}else if (ejdaMethod.equalsIgnoreCase("doSaveButton")){
			return doSaveButton();
		}else if (ejdaMethod.equalsIgnoreCase("doUpdate")){
			return doUpdate();
		}
		return false;
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean doAdd(){
		log.debug("*********** doChangePage ***********");
		getForm4Bean().setActionName("EJDAM013");
		return true;
	}
	
	private boolean doSubmitButton() {
		boolean result = false;
		String iuser = (String) getRequest().getSession().getAttribute("iuser");
		String formNo = (String) getRequest().getSession().getAttribute("form_no");
		String ipAddress = getRequest().getRemoteAddr();
		if (null==iuser || "".equals(iuser)){
			iuser = "system";
		}
//		Form4Model form4 = new Form4Model();
//		form4.setForm_name("FN_" + iuser);
//		form4.setForm_status("A");
//		form4.setUpdate_by(iuser);
//		form4.setForm_no(formNo);
		try{
			EJDAM010Action ejdam010Action = new EJDAM010Action();
			ejdam010Action.setRequest(getRequest());
			Form1Model form1 = ejdam010Action.setValueModel("4","A",iuser);
			log.debug("Form1Model >> " + form1);
			Vector vcDetail1 = ejdam010Action.setValueDetail1Model();
			Vector vcDetail2 = ejdam010Action.setValueDetail2Model();
//			Vector vcDetail2 = new Vector();
			Vector vcDocAttach = ejdam010Action.setValueDocumentAttach("4","");
		
			Form1DAO dao = new Form1DAOImpl();
			dao.saveFromEJDA(form1,vcDetail1,vcDetail2,vcDocAttach);
//			Form4DAO dao = new Form4DAOImpl();
//			dao.UpdateFrom4Table(form4);
//			dao.saveFrom4Table1(form4);
//			TransactionLogModel transactionLogModel = new TransactionLogModel() ;
//			EJDAUtil ejda = new EJDAUtil();
//			transactionLogModel.setMenuId("M013");
//			transactionLogModel.setTranAction("ADD");
//			transactionLogModel.setDescription("Save and submit EJDA Table 1 Form 4");
//			transactionLogModel.setIpAddress(ipAddress);
//			transactionLogModel.setTranBy(iuser);
//			ejda.insertTranLog(transactionLogModel);
			
			getRequest().getSession().setAttribute("responseMessage", "Submit Form 4 Successfully.");
			result = doSearch();
			
			log.debug("result = "+result);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean doSearch(){
		log.debug("*********** doSearch ***********");
//		log.debug("ejdaMethod = "+getRequest().getParameter("ejdaMethod"));
		boolean result = false;
		setCriteriaPameter();
		form4Bean = getForm4Bean();
		ValueListModel valueListM = new ValueListModel();
		ValueListAction valueListA = new ValueListAction();
		log.debug("getRequest().getParameter(Page)"+getRequest().getParameter("Page"));
		try{
			Vector tranLogVt = new Vector();
			valueListM = form4Bean.getValueListM();
			valueListM.setSQL(this.setSQL(form4Bean.getForm4ModelSP()));
			valueListM.setParameters(getValueListParameters());
			valueListM.setPage(getRequest().getParameter("page"));
			valueListM.setItemsPerPage(Integer.parseInt(getRequest().getParameter("volumePerPage")));
			
			form4Bean.setValueListM(valueListA.doSearch(valueListM));
			form4Bean.setForm4Vt(form4Bean.getValueListM().getResult());
			form4Bean.setActionName("EJDAM013");
			log.debug("form4Bean = " + form4Bean.getForm4Vt().size());
			log.debug("tranLogBean.getValueListM().getCount() = "+form4Bean.getValueListM().getCount());
			log.debug("form4Bean setActionName = " + form4Bean.getActionName());
			getRequest().getSession().removeAttribute("VALUE_LIST");
			setForm4Bean(form4Bean);
			result = true;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}
	
	private Vector getValueListParameters() {
		Vector parameters = new Vector();
		Form1Model form4 = getForm4Bean().getForm4ModelSP();
		if (!"".equals(form4.getForm_name())){
			log.debug("Form Name = "+form4.getForm_name());
			parameters.add(form4.getForm_name());
		}
		log.info("parameters.size() = "+parameters.size());
		return parameters;
	}
	
	private void setCriteriaPameter(){
		
		Form1Model form4 = new Form1Model();
		form4.setForm_name(getRequest().getParameter("txtFormName"));
		
		getForm4Bean().setForm4ModelSP(form4);
	}
	
	private String setSQL(Form1Model form4Cri){
		StringBuffer sql = new StringBuffer();
		StringBuffer sql1 = new StringBuffer();
		String sqlCommand ="";
		String sqlWhere="";
		try{
			sql.append(EJDAConstant.SQL.FORM_T_DOC_1);
			sql.append("  WHERE JDA_TYPE = '4' AND DOC_STATUS = 'A' ");
//			if (sql.indexOf("WHERE") != -1){
//				sqlWhere = sql.substring(sql.indexOf("WHERE"),sql.length());
//				sqlCommand = sql.substring(0, sql.lastIndexOf("WHERE"));
//			}
//			if (!"".equals(form4Cri.getForm_name())){
//				//sql.append(" WHERE ");
//				sqlWhere += " FORM_NAME = ? AND ";
//				
//			}
			
			log.debug("sqlWhere >> " + sqlWhere);
			log.debug("sqlCommand >> " + sqlCommand);
			
			sql1.append(sqlCommand + " " + sqlWhere);
			
			log.debug("sql >> " + sql1.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		sql1 = removeWasteSQL(sql1);
		return sql.toString();
	}
	
	private boolean doSaveButton() {
		boolean result = false;
		String iuser = (String) getRequest().getSession().getAttribute("iuser");
		if (null==iuser || "".equals(iuser)){
			iuser = "system";
		}
		Form4Model form4 = new Form4Model();
		form4.setForm_name("FN_" + iuser);
		form4.setForm_status("A");
		form4.setUpdate_by(iuser);
		try{
			Form4DAO dao = new Form4DAOImpl();
			dao.saveFrom4Table1(form4);
			result = doSearch();
			
			log.debug("result = "+result);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	
	private boolean doUpdate() {
		form4Bean = getForm4Bean();
		String docId = (String)getRequest().getParameter("doc_id");
		log.debug("docId = "+docId);
//		getRequest().getSession().setAttribute("form_no", form_no);
		try{
			Form1DAO dao = new Form1DAOImpl();
			form4Bean.setForm4ModelSP(dao.searchFormModel(docId));
			form4Bean.setDetail1MVt(dao.searchFormDetail1Model(docId));
			form4Bean.setDetail2MVt(dao.searchFormDetail2Model(docId));
			form4Bean.setDocAttachMVt(dao.searchFormDocAttachModel(docId));
			log.debug("form4Bean = "+form4Bean.getForm4ModelSP().getMovementPemitNo());
			log.debug("form4Bean.getDetail1MVt().size() = "+form4Bean.getDetail1MVt().size());
			log.debug("form4Bean.getDetail2MVt().size() = "+form4Bean.getDetail2MVt().size());
			log.debug("form4Bean.getDocAttachMVt().size() = "+form4Bean.getDocAttachMVt().size());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		setForm4Bean(form4Bean);
		return true;
	}
}

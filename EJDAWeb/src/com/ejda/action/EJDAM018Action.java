package com.ejda.action;

import java.util.Vector;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.ejda.constant.EJDAConstant;
import com.ejda.sessionBean.Form1Bean;
import com.ejda.util.DisplayFormatUtil;
import com.ejda.util.EJDAUtil;
import com.tcd.ejda.dao.CacheDataDAO;
import com.tcd.ejda.dao.CacheDataDAOImpl;
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
		Vector unitVt = new Vector();
		Vector tanliCodeVt = new Vector();
		Vector dutyRateVt = new Vector();
		Vector countryOriginVt = new Vector();
		log.debug("*********** EJDAM018Action ***********");
		
		form1Bean = getForm1Bean();
		form1Bean.setForm1Vt(new Vector<Form1Model>());
		form1Bean.setDetail1MVt(new Vector<FormDetail1Model>());
		form1Bean.setDetail2MVt(new Vector<FormDetail2Model>());
		
		form1Bean.setForm1ModelSP(new Form1Model());
		form1Bean.setForm1ModelCri(new Form1Model());
		form1Bean.setDetail1ModelSP(new FormDetail1Model());
		form1Bean.setDetail2ModelSP(new FormDetail2Model());
		form1Bean.setDocAttachModelSP(new FormDocAttachModel());
		
		ValueListModel valueListM = new ValueListModel();
		valueListM.setReturnModel("Form1Model");
		form1Bean.setValueListM(valueListM);
		try{
			CacheDataDAO dao = new CacheDataDAOImpl();
			unitVt = dao.LoadUnit();
			tanliCodeVt = dao.LoadCustomTanli();
			dutyRateVt = dao.LoadExchangeRAte();
			countryOriginVt = dao.LoadCountryOrigin();
			log.debug("countryOriginVt = " + countryOriginVt.size());
		}catch (Exception e) {
			log.debug("Action init 18 error >> "+e.getStackTrace());
			e.printStackTrace();
		}
		form1Bean.setUnitVt(unitVt);
		form1Bean.setTanliCodeVt(tanliCodeVt);
		form1Bean.setDutyRateVt(dutyRateVt);
		form1Bean.setCountryOriginVt(countryOriginVt);
		setForm1Bean(form1Bean);
	}

	@Override
	public boolean methodAction(String ejdaMethod)throws Exception  {
		// TODO Auto-generated method stub
		if(ejdaMethod.equalsIgnoreCase("doSearch")){
			setCriteriaPameter();
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
		String doc_id = (String) getRequest().getParameter("doc_id");
		String flag_payment = (String) getRequest().getParameter("flag_payment");//STATUS
		String formNo = (String) getRequest().getParameter("form_no");
		String ipAddress = getRequest().getRemoteAddr();
		
		if (null==iuser || "".equals(iuser)){
			iuser = "system";
		}
//		EJDAM010Action ejdam010Action = new EJDAM010Action();
//		ejdam010Action.setRequest(getRequest()); 
//		Form1Model form1 = ejdam010Action.setValueModel(formNo,"P",iuser);
//		Vector vcDetail1 = ejdam010Action.setValueDetail1Model();
////		Vector vcDetail2 = ejdam010Action.setValueDetail2Model();
//		Vector vcDetail2 = null;
//		if("1".equals(formNo) || "2".equals(formNo) || "4".equals(formNo)){
//			vcDetail2 = ejdam010Action.setValueDetail2Model();
//		}else if("3".equals(formNo)){
//			vcDetail2 = ejdam010Action.setValueDetail2ModelForm3();
//		}
//		Vector vcDocAttach = ejdam010Action.setValueDocumentAttach(formNo,"");
//		
		try{
			Form1DAO dao = new Form1DAOImpl();
			if (dao.UpdatePayment(doc_id, flag_payment, iuser)) {
//			dao.UpdateFromTable(form1,vcDetail1,vcDetail2,vcDocAttach);
//			dao.UpdateFromTable(form1);
			
				TransactionLogModel transactionLogModel = new TransactionLogModel() ;
				EJDAUtil ejda = new EJDAUtil();
				transactionLogModel.setMenuId("M018");
				transactionLogModel.setTranAction("UPD");
				transactionLogModel.setDescription("Update Payment");
				transactionLogModel.setIpAddress(ipAddress);
				transactionLogModel.setTranBy(iuser);
				ejda.insertTranLog(transactionLogModel);
			}
			getRequest().getSession().setAttribute("responseMessage", "Submit Type of Payment Successfully.");
			result = doSearch();
			
			log.debug("result = "+result);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	private boolean doUpdate() {
		form1Bean = getForm1Bean();
		String docId = (String)getRequest().getParameter("doc_id");
		log.debug("docId = "+docId);
//		getRequest().getSession().setAttribute("form_no", form_no);
		try{
			Form1DAO dao = new Form1DAOImpl();
			form1Bean.setForm1ModelSP(dao.searchFormModel(docId));
			form1Bean.setDetail1MVt(dao.searchFormDetail1Model(docId));
			form1Bean.setDetail2MVt(dao.searchFormDetail2Model(docId));
			form1Bean.setDocAttachMVt(dao.searchFormDocAttachModel(docId));
		}catch (Exception e) {
			e.printStackTrace();
		}
		setForm1Bean(form1Bean);
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
//		setCriteriaPameter();
		form1Bean = getForm1Bean();
		ValueListModel valueListM = new ValueListModel();
		ValueListAction valueListA = new ValueListAction();
		log.debug("getRequest().getParameter(Page)"+getRequest().getParameter("Page"));
		try{
			Vector tranLogVt = new Vector();
			valueListM = form1Bean.getValueListM();
			valueListM.setSQL(this.setSQL(form1Bean.form1ModelCri));
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
		Form1Bean form1Bean = (Form1Bean)getRequest().getSession().getAttribute("form1Bean");
		if (null == form1Bean){
			form1Bean = new Form1Bean();
		}
		return form1Bean;
	}

	public void setForm1Bean(Form1Bean form1Bean) {
		getRequest().getSession().setAttribute("form1Bean", form1Bean);
	}
	
	private void setCriteriaPameter(){
		
		Form1Model form1 = new Form1Model();
		try{
			log.debug(" getRequest().getParameter(jdaType) = " + getRequest().getParameter("jdaType"));
			form1.setForm_name(getRequest().getParameter("txtFormName"));
			form1.setDoc_ID(getRequest().getParameter("txtDocID"));
			form1.setJDA_Type(getRequest().getParameter("jdaType"));
			form1.setConsignor_code(getRequest().getParameter("txtConsignorCode"));
			form1.setConsignor_name(getRequest().getParameter("txtConsignorName"));
			form1.setConsignee_code(getRequest().getParameter("txtConsigneeCode"));
			form1.setConsignee_name(getRequest().getParameter("txtConsigneeName"));
			form1.setDate_Receipt_From(DisplayFormatUtil.stringToDateSql((String)getRequest().getParameter("txtDocDateFrom"), "dd/mm/yyyy"));
			form1.setDate_Receipt_To(DisplayFormatUtil.stringToDateSql((String)getRequest().getParameter("txtDocDateTo"), "dd/mm/yyyy"));
		}catch (Exception e) {
			e.printStackTrace();
		}
		getForm1Bean().setForm1ModelCri(form1);
	}
	
	private String setSQL(Form1Model form1Cri){
		StringBuffer sql = new StringBuffer();
		StringBuffer sql1 = new StringBuffer();
		String sqlCommand ="";
		String sqlWhere="";
		log.debug("form1Cri = "+form1Cri);
		log.debug("form1Cri.getJDA_Type() = "+form1Cri.getJDA_Type());
		try{
			sql.append(EJDAConstant.SQL.FORM_T_DOC_1);
//			sql.append(" WHERE JDA_TYPE = '1' AND DOC_STATUS = 'S' ");
			sql.append(" WHERE DOC_STATUS = 'A' AND FLAG_PAYMENT IS NULL ");
			if(StringUtils.isNotEmpty(form1Cri.getDoc_ID())){
				sql.append(" AND DOC_ID = ? ");
			}
			if(StringUtils.isNotEmpty(form1Cri.getJDA_Type())){
				sql.append(" AND JDA_TYPE = ? ");
			}
			if(StringUtils.isNotEmpty(form1Cri.getConsignor_code())){
				sql.append(" AND CONSIGNOR_CODE = ? ");
			}
			if(StringUtils.isNotEmpty(form1Cri.getConsignor_name())){
				sql.append(" AND UPPER(CONSIGNOR_NAME) like ? ");
			}
			if(StringUtils.isNotEmpty(form1Cri.getConsignee_code())){
				sql.append(" AND CONSIGNEE_CODE = ? ");
			}
			if(StringUtils.isNotEmpty(form1Cri.getConsignee_name())){
				sql.append(" AND UPPER(CONSIGNEE_NAME) like ? ");
			}
			if(form1Cri.getDate_Receipt_From() != null && form1Cri.getDate_Receipt_To() != null){
				sql.append(" AND DATE_RECEIPT BETWEEN ? AND ? ");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		sql = removeWasteSQL(sql);
		log.debug("sql.toString() = "+sql.toString()); 
		return sql.toString();
	}
	
	private Vector getValueListParameters() {
		Vector parameters = new Vector();
		Form1Model form1 = getForm1Bean().getForm1ModelCri();
		if (null!= form1.getForm_name() && !"".equals(form1.getForm_name())){
			log.debug("Form Name = "+form1.getForm_name());
			parameters.add(form1.getForm_name());
		}if(StringUtils.isNotEmpty(form1.getDoc_ID())){
			parameters.add(form1.getDoc_ID());
		}
		if(StringUtils.isNotEmpty(form1.getJDA_Type())){
			log.debug("getJDA_Type = " +form1.getJDA_Type() );
			parameters.add(form1.getJDA_Type());
		}
		if(StringUtils.isNotEmpty(form1.getConsignor_code())){
			parameters.add(form1.getConsignor_code());
		}
		if(StringUtils.isNotEmpty(form1.getConsignor_name())){
			parameters.add("%"+ form1.getConsignor_name().toUpperCase() + "%");
		}
		if(StringUtils.isNotEmpty(form1.getConsignee_code())){
			parameters.add(form1.getConsignee_code());
		}
		if(StringUtils.isNotEmpty(form1.getConsignee_name())){
			parameters.add("%" + form1.getConsignee_name().toUpperCase() +"%");
		}
		
		if(form1.getDate_Receipt_From() != null && form1.getDate_Receipt_To() != null){
			parameters.add(form1.getDate_Receipt_From());
			parameters.add(form1.getDate_Receipt_To());
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

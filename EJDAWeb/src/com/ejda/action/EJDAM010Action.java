package com.ejda.action;

import java.util.Vector;

import org.apache.log4j.Logger;

import com.ejda.constant.EJDAConstant;
import com.ejda.sessionBean.Form1Bean;
import com.ejda.util.DisplayFormatUtil;
import com.ejda.util.EJDAUtil;
import com.tcd.ejda.dao.Form1DAO;
import com.tcd.ejda.dao.Form1DAOImpl;
import com.tcd.ejda.dao.TransactionLogDAO;
import com.tcd.ejda.dao.TransactionLogDAOImpl;
import com.tcd.ejda.model.Form1Model;
import com.tcd.ejda.model.FormDetail1Model;
import com.tcd.ejda.model.FormDetail2Model;
import com.tcd.ejda.model.TransactionLogModel;
import com.tcd.ejda.model.ValueListModel;

public class EJDAM010Action extends AbstractAction {

	private Logger log = Logger.getLogger(EJDAM010Action.class);
	private Form1Bean form1Bean;
	
	@Override
	public void clearSessionNotUsed() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		/** EJDA Form no 1****/
		log.debug("*********** EJDAM010Action ***********");
		
		
		form1Bean = getForm1Bean();
		form1Bean.setForm1Vt(new Vector<Form1Model>());
		form1Bean.setForm1ModelSP(new Form1Model());
		form1Bean.setDetail1ModelSP(new FormDetail1Model());
		form1Bean.setDetail2ModelSP(new FormDetail2Model());
		ValueListModel valueListM = new ValueListModel();
		valueListM.setReturnModel("Form1Model");
		form1Bean.setValueListM(valueListM);
		setForm1Bean(form1Bean);
	}

	@Override
	public boolean methodAction(String ejdaMethod) {
		// TODO Auto-generated method stub
		if(ejdaMethod.equalsIgnoreCase("doSearch")){
			return doSearch();
		}else if(ejdaMethod.equalsIgnoreCase("doDelete")){
			return doDelete();
		}else if (ejdaMethod.equalsIgnoreCase("doAdd")){
			return doAdd();
		}else if (ejdaMethod.equalsIgnoreCase("doSubmitButton")){
			try {
				return doSubmitButton();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if (ejdaMethod.equalsIgnoreCase("doSaveButton")){
			return doSaveButton();
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
		Form1Model form1 = new Form1Model();
		form1.setForm_name("FN_" + iuser);
		form1.setForm_status("D");
		form1.setUpdate_by(iuser);
		try{
			Form1DAO dao = new Form1DAOImpl();
			dao.saveFrom1Table1(form1);
			result = doSearch();
			
			log.debug("result = "+result);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	private boolean doSubmitButton() throws Exception {
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
//		Form1Model form1 = new Form1Model();
//		form1.setForm_name("FN_" + iuser);
//		form1.setForm_status("A");
//		form1.setUpdate_by(iuser);
//		form1.setForm_no(formNo);
		Form1Model form1 = setValueModel();
		log.debug("Form1Model >> " + form1);
		Vector vcDetail1 = setValueDetail1Model();
		Vector vcDetail2 = setValueDetail2Model();
		try{ 
			Form1DAO dao = new Form1DAOImpl();
			//dao.UpdateFrom1Table(form1);
			dao.saveFromEJDA(form1,vcDetail1,vcDetail2);
//			
//			TransactionLogModel transactionLogModel = new TransactionLogModel() ;
//			EJDAUtil ejda = new EJDAUtil();
//			transactionLogModel.setMenuId("M010");
//			transactionLogModel.setTranAction("ADD");
//			transactionLogModel.setDescription("Save and submit EJDA Table 1 Form 1");
//			transactionLogModel.setIpAddress(ipAddress);
//			transactionLogModel.setTranBy(iuser);
//			ejda.insertTranLog(transactionLogModel);
			
			getRequest().getSession().setAttribute("responseMessage", "Submit Form 1 Successfully.");
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
		//form1Bean = getForm1Bean();
		getForm1Bean().setActionName("EJDAM010");
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
			form1Bean.setActionName("EJDAM010");
			log.debug("form1Bean = " + form1Bean.getForm1Vt().size());
			log.debug("tranLogBean.getValueListM().getCount() = "+form1Bean.getValueListM().getCount());
			log.debug("form1Bean setActionName = " + form1Bean.getActionName());
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
		log.debug("getRequest().getParameter(txtFormName)>>> " + getRequest().getParameter("txtFormName"));
		form1.setForm_name(getRequest().getParameter("txtFormName"));
		
		getForm1Bean().setForm1ModelSP(form1);
	}
	
	private String setSQL(Form1Model form1Cri){
		StringBuffer sql = new StringBuffer();
		StringBuffer sql1 = new StringBuffer();
		String sqlCommand ="";
		String sqlWhere="";
		try{
			sql.append(EJDAConstant.SQL.FORM_T_DOC_2);
//			if (sql.indexOf("WHERE") != -1){
//				sqlWhere = sql.substring(sql.indexOf("WHERE"),sql.length());
//				sqlCommand = sql.substring(0, sql.lastIndexOf("WHERE"));
//			}
//			if (!"".equals(form1Cri.getForm_name())){
//				//sql.append(" WHERE ");
//				sqlWhere = " FORM_NAME = ? AND ";
//				
//			}
			
			
			
			//sql.append(sql.toString());
			log.debug("sql >> " + sql.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		sql = removeWasteSQL(sql);
		return sql.toString();
	}
	
	private Vector getValueListParameters() {
		Vector parameters = new Vector();
		Form1Model form1 = getForm1Bean().getForm1ModelSP();
		log.debug("form1.getForm_name() >> " + form1.getForm_name());
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
			result = doSearch();
			
			log.debug("result = "+result);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}
	private Form1Model setValueModel() throws Exception{
		log.debug("[ Start : setValueModel ]");
		Form1Model form = new Form1Model();
		String iuser = (String) getRequest().getSession().getAttribute("iuser");
		if (null==iuser || "".equals(iuser)){
			iuser = "system";
		}
		String doc_id = "";//	DOC_ID
		String country_id = "0";//	COUNTRY_ID
		String jda_type = "1";//JDA_TYPE
		String doc_status = "A";//DOC_STATUS
		String invoice_no = "";//INVOICE_NO
		String consignorExportCode = (String) getRequest().getParameter("consignorExportCode");//CONSIGNOR_CODE
		String consignorExportName = (String) getRequest().getParameter("consignorExportName");//CONSIGNOR_NAME
		String consignorExportAddress = (String) getRequest().getParameter("consignorExportAddress");//CONSIGNOR_ADDRESS
		String Consignee_code = (String) getRequest().getParameter("Consignee_code");//CONSIGNEE_CODE
		String Consignee_name = (String) getRequest().getParameter("Consignee_name");//CONSIGNEE_NAME
		String Consignee_address = (String) getRequest().getParameter("Consignee_address");//CONSIGNEE_ADDRESS
		String AuthorAgent_code = (String) getRequest().getParameter("AuthorAgent_code");//AUTHORAGENT_CODE
		String AuthorAgent_name = (String) getRequest().getParameter("AuthorAgent_name");//AUTHORAGENT_NAME
		String AuthorAgent_address = (String) getRequest().getParameter("AuthorAgent_address");//AUTHORAGENT_ADDRESS
		String mode_trans = (String) getRequest().getParameter("mode_trans");//MODE_TRANS
		String trans_other = (String) getRequest().getParameter("trans_other");//TRANS_OTHER Trans detail of mode trans = 6
		String Date_Import = (String) getRequest().getParameter("Date_Import");//DATE_IMPORT
		String trans_detail = "";//TRANS_DETAIL
		String PortImport_Code = (String) getRequest().getParameter("PortImport_Code");//PORTIMPORT_CODE
		String PortImport_Desc = (String) getRequest().getParameter("PortImport_Desc");//PORTIMPORT_DESC
		String PortLoad_Code = (String) getRequest().getParameter("PortLoad_Code");//PORTLOAD_CODE
		String PortLoad_Desc = (String) getRequest().getParameter("PortLoad_Desc");//PORTLOAD_DESC
		String Via_Code = (String) getRequest().getParameter("Via_Code");//VIA_CODE
		String Via_Desc = (String) getRequest().getParameter("Via_Desc");//VIA_DESC
		String Date_Receipt = (String) getRequest().getParameter("Date_Receipt");//DATE_RECEIPT
		String ref_no ="";//REF_NO
		String Regis_no = (String) getRequest().getParameter("Regis_no");//REGIS_NO
		String cus_name_code = (String) getRequest().getParameter("cus_name_code");//CUS_NAME_CODE
		String cus_name_desc = (String) getRequest().getParameter("cus_name_desc");//CUS_NAME_DESC
		String ManifestNo = (String) getRequest().getParameter("ManifestNo");//MANIFEST_NO
		String duty_tax_receipt_date = (String) getRequest().getParameter("duty_tax_receipt_date");//DUTY_TAX_RECEIPT_DATE
		String duty_tax_receipt_desc = (String) getRequest().getParameter("duty_tax_receipt_desc");//DUTY_TAX_RECEIPT_DESC
		String import_permit_no = (String) getRequest().getParameter("import_permit_no");//IMPORT_PERMIT_NO
		String exchg_ctrl_ref = (String) getRequest().getParameter("exchg_ctrl_ref");//EXCHG_CTRL_REF
		String special_treatment = (String) getRequest().getParameter("special_treatment");//SPECIAL_TREATMENT
		String country_origin_code = (String) getRequest().getParameter("country_origin_code");//COUNTRY_ORIGIN_CODE
		String country_origin_desc = (String) getRequest().getParameter("country_origin_desc");//COUNTRY_ORIGIN_DESC
		String country_final_code = (String) getRequest().getParameter("country_final_code");//COUNTRY_FINAL_CODE
		String country_final_desc = (String) getRequest().getParameter("country_final_desc");//COUNTRY_FINAL_DESC
		String bill_no = (String) getRequest().getParameter("bill_no");//BILL_NO
		String term_payment = (String) getRequest().getParameter("term_payment");//TERM_PAYMENT
		String cur_code = (String) getRequest().getParameter("cur_code");//CUR_CODE
		String Received_amount = (String) getRequest().getParameter("Received_amount");//RECEIVED_AMOUNT
		String ExchgRate_ID = (String) getRequest().getParameter("ExchgRate_ID");//EXCHGRATE_ID
		String Equivalent = (String) getRequest().getParameter("Equivalent");//EQUIVALENT
		String good_payment = (String) getRequest().getParameter("good_payment");//GOOD_PAYMENT
		String country_of_good = (String) getRequest().getParameter("country_of_good");//COUNTRY_OF_GOOD
		String fob_value = (String) getRequest().getParameter("fob_value");//FOB_VALUE
		String Insurance = (String) getRequest().getParameter("Insurance");//INSURANCE
		String Freight = (String) getRequest().getParameter("Freight");//FREIGHT
		String cif_value = (String) getRequest().getParameter("cif_value");//CIF_VALUE
		String gross_weight = (String) getRequest().getParameter("gross_weight");//GROSS_WEIGHT
		String Measurement = (String) getRequest().getParameter("Measurement");//MEASUREMENT
		String Other_charg = (String) getRequest().getParameter("Other_charg");//OTHER_CHARG
		String declarant_name = (String) getRequest().getParameter("declarant_name");//DECLARANT_NAME
		String id_card_no = (String) getRequest().getParameter("id_card_no");//ID_CARD_NO
		String status = (String) getRequest().getParameter("status");//STATUS
		String cerify = (String) getRequest().getParameter("cerify");//CERIFY
		String cus_removal = (String) getRequest().getParameter("cus_removal");//CUS_REMOVAL
		String tax_total = (String) getRequest().getParameter("tax_total");//TAX_TOTAL
		String Other_charg2 = (String) getRequest().getParameter("Other_charg2");//OTHER_CHARG2
		String payable_amount = (String) getRequest().getParameter("payable_amount");//PAYABLE_AMOUNT
		String manualscript_recerpt = (String) getRequest().getParameter("manualscript_recerpt");//MANUALSCRIPT_RECERPT
		//CREATE_DATE
		//ps.setString(parameterIndex++, form.getCreate_By());//CREATE_BY
		//UPDATE_DATE
		//ps.setString(parameterIndex++, form.getUpdate_by());//UPDATE_BY
		form.setDoc_ID("");
		form.setCountry_ID(country_id);//	COUNTRY_ID
		form.setJDA_Type(jda_type);//JDA_TYPE
		form.setDoc_Status(doc_status);//DOC_STATUS
		form.setInvoice_No(invoice_no);//INVOICE_NO
		form.setConsignor_code(consignorExportCode);//CONSIGNOR_CODE
		form.setConsignor_name(consignorExportName);//CONSIGNOR_NAME
		form.setConsignor_address(consignorExportAddress);//CONSIGNOR_ADDRESS
		form.setConsignee_code(Consignee_code);//CONSIGNEE_CODE
		form.setConsignee_name(Consignee_name);//CONSIGNEE_NAME
		form.setConsignee_address(Consignee_address) ;//CONSIGNEE_ADDRESS
		form.setAuthorAgent_code(AuthorAgent_code);//AUTHORAGENT_CODE
		form.setAuthorAgent_name(AuthorAgent_name) ;//AUTHORAGENT_NAME
		form.setAuthorAgent_address(AuthorAgent_address);//AUTHORAGENT_ADDRESS
		form.setMode_Trans(mode_trans) ;//MODE_TRANS
		form.setTrans_Other(trans_other);//TRANS_OTHER
		form.setDate_Import(DisplayFormatUtil.stringToDateSql(Date_Import, "YYYY-MM-DD"));//DATE_IMPORT
		form.setTrans_Detail(trans_detail);//TRANS_DETAIL
		form.setPortImport_Code(PortImport_Code);//PORTIMPORT_CODE
		form.setPortImport_Desc(PortImport_Desc);//PORTIMPORT_DESC
		form.setPortLoad_Code(PortLoad_Code);//PORTLOAD_CODE
		form.setPortLoad_Desc(PortLoad_Desc);//PORTLOAD_DESC
		form.setVia_Code(Via_Code);//VIA_CODE
		form.setVia_Desc(Via_Desc) ;//VIA_DESC
		form.setDate_Receipt(DisplayFormatUtil.stringToDateSql(Date_Receipt, "YYYY-MM-DD"));//DATE_RECEIPT
		form.setRef_no(ref_no);//REF_NO
		form.setRegis_no(Regis_no);//REGIS_NO
		form.setCus_name_code(cus_name_code);//CUS_NAME_CODE
		form.setCus_name_desc(cus_name_desc);//CUS_NAME_DESC
		form.setManifest_no(ManifestNo);//MANIFEST_NO
		form.setDuty_tax_receipt_date(DisplayFormatUtil.stringToDateSql(duty_tax_receipt_date, "YYYY-MM-DD"));//DUTY_TAX_RECEIPT_DATE
		form.setDuty_tax_receipt_desc(duty_tax_receipt_desc);//DUTY_TAX_RECEIPT_DESC
		form.setImport_permit_no(import_permit_no);//IMPORT_PERMIT_NO
		form.setExchg_ctrl_ref(exchg_ctrl_ref);//EXCHG_CTRL_REF
		form.setSpecial_treatment(special_treatment);//SPECIAL_TREATMENT
		form.setCountry_origin_code(country_origin_code);//COUNTRY_ORIGIN_CODE
		form.setCountry_origin_desc(country_origin_desc);//COUNTRY_ORIGIN_DESC
		form.setCountry_final_code(country_final_code);//COUNTRY_FINAL_CODE
		form.setCountry_final_desc(country_final_desc);//COUNTRY_FINAL_DESC
		form.setBill_no(bill_no);//BILL_NO
		form.setTerm_payment(term_payment);//TERM_PAYMENT
		form.setCur_code(cur_code);//CUR_CODE
//		form.setReceived_amount(Double.parseDouble(Received_amount));//RECEIVED_AMOUNT
		form.setExchgRate_ID(ExchgRate_ID);//EXCHGRATE_ID
		form.setEquivalent(Equivalent);//EQUIVALENT
		form.setGood_payment(good_payment);//GOOD_PAYMENT
		form.setCountry_of_good(country_of_good) ;//COUNTRY_OF_GOOD
		form.setFob_value(fob_value);//FOB_VALUE
		form.setInsurance(Insurance) ;//INSURANCE
		form.setFreight(Freight);//FREIGHT
		form.setCif_value(cif_value);//CIF_VALUE
		form.setGross_weight(gross_weight) ;//GROSS_WEIGHT
		form.setMeasurement(Measurement) ;//MEASUREMENT
		form.setOther_charg(Other_charg);//OTHER_CHARG
		form.setDeclarant_name(declarant_name);//DECLARANT_NAME
		form.setId_card_no(id_card_no);//ID_CARD_NO
		form.setStatus(status);//STATUS
		form.setCerify(cerify) ;//CERIFY
		form.setCus_removal(cus_removal);//CUS_REMOVAL
//		form.setTax_total(Double.parseDouble(tax_total));//TAX_TOTAL
		form.setOther_charg2(Other_charg2) ;//OTHER_CHARG2
//		form.setPayable_amount(Double.parseDouble(payable_amount));//PAYABLE_AMOUNT
		form.setManualscript_recerpt(manualscript_recerpt) ;//MANUALSCRIPT_RECERPT
		form.setCreate_By(iuser);
		form.setUpdate_by(iuser);
		
		return form;
	}
	
	private Vector setValueDetail1Model() throws Exception{
		log.debug("[ Start : setValueModel ]");
		Vector vc = new Vector();
		
		String iuser = (String) getRequest().getSession().getAttribute("iuser");
		if (null==iuser || "".equals(iuser)){
			iuser = "system";
		}

		String [] MARK_NO =  getRequest().getParameterValues("MARK_NO");//MARK_NO
		String [] ITEM_NO =  getRequest().getParameterValues("ITEM_NO");//ITEM_NO
		String [] PACKAGE_NO = getRequest().getParameterValues("PACKAGE_NO");//PACKAGE_NO
		String [] GOODS_DESC = getRequest().getParameterValues("GOODS_DESC");//GOODS_DESC
		String [] CODE_NO = getRequest().getParameterValues("CODE_NO");//CODE_NO
		String [] UNIT = getRequest().getParameterValues("UNIT");//UNIT
		if (MARK_NO.length > 1){
			for(int i =0; i < MARK_NO.length;i++){
				FormDetail1Model detail = new FormDetail1Model();
				detail.setMarks_no(MARK_NO[i]);
				detail.setItem_no(ITEM_NO[i]);
				detail.setNo_type_package(PACKAGE_NO[i]);
				detail.setGood_desc(GOODS_DESC[i]);
				detail.setCust_code(CODE_NO[i]);
				detail.setCust_unit(UNIT[i]);
				detail.setCreate_By(iuser);
				detail.setUpdate_by(iuser);
				log.debug("MARK_NO = "+ i + ":"+MARK_NO[i]);
				log.debug("ITEM_NO = "+ i + ":"+ITEM_NO[i]);
				log.debug("PACKAGE_NO = "+ i + ":"+PACKAGE_NO[i]);
				log.debug("GOODS_DESC = "+ i + ":"+GOODS_DESC[i]);
				log.debug("CODE_NO = "+ i + ":"+CODE_NO[i]);
				log.debug("UNIT = "+ i + ":"+UNIT[i]);
				vc.add(detail);
			}
		}
		return vc;
	}
	
	private Vector setValueDetail2Model() throws Exception{
		log.debug("[ Start : setValueModel ]");
		
		Vector vc = new Vector();
		String iuser = (String) getRequest().getSession().getAttribute("iuser");
		if (null==iuser || "".equals(iuser)){
			iuser = "system";
		}
		String doc_id = "";//	DOC_ID
		String country_id = "0";//	COUNTRY_ID
		String jda_type = "1";//JDA_TYPE
		String doc_status = "A";//DOC_STATUS
		String invoice_no = "";//INVOICE_NO
		String [] QA_ITEM_NO = getRequest().getParameterValues("QA_ITEM_NO");//QA_ITEM_NO
		String [] QB_UNIT = getRequest().getParameterValues("QB_UNIT");//QB_UNIT
		String [] FOB_ACTUAL = getRequest().getParameterValues("FOB_ACTUAL");//FOB_ACTUAL
		String [] FOB_CUSTOM = getRequest().getParameterValues("FOB_CUSTOM");//FOB_CUSTOM
		String [] TOTAL_VALUE = getRequest().getParameterValues("TOTAL_VALUE");//TOTAL_VALUE
		String [] DUTY_RATE = getRequest().getParameterValues("DUTY_RATE");//DUTY_RATE
		String [] DUTY_AMOUNT = getRequest().getParameterValues("DUTY_AMOUNT");//DUTY_AMOUNT
		String [] TAX_TYPE = getRequest().getParameterValues("TAX_TYPE");//TAX_TYPE
		String [] TAX_RATE = getRequest().getParameterValues("TAX_RATE");//TAX_RATE
		String [] TAX_AMOUNT = getRequest().getParameterValues("TAX_AMOUNT");//TAX_AMOUNT
		if (QA_ITEM_NO.length > 1){
			for(int i =0; i < QA_ITEM_NO.length;i++){
				FormDetail2Model detail = new FormDetail2Model();
				detail.setItem_no(QA_ITEM_NO[i]);
				if (null!=QB_UNIT[i] && !"".equals(QB_UNIT[i])){
					detail.setQty_cust_unit(Double.parseDouble(QB_UNIT[i]));
				}else{
					detail.setQty_cust_unit(0);
				}
				
				detail.setUnit_val_actual(FOB_ACTUAL[i]);
				detail.setUnit_val_custom(FOB_CUSTOM[i]);
				if (null!=TOTAL_VALUE[i] && !"".equals(TOTAL_VALUE[i])){
					detail.setTotal_value(Double.parseDouble(TOTAL_VALUE[i]));
				}else{
					detail.setTotal_value(0);
				}
				if (null!=DUTY_AMOUNT[i] && !"".equals(DUTY_AMOUNT[i])){
					detail.setExport_amount(Double.parseDouble(DUTY_AMOUNT[i]));
				}else{
					detail.setTotal_value(0);
				}
				
				detail.setOther_tax_type(TAX_TYPE[i]);
				if (null!=DUTY_RATE[i] && !"".equals(DUTY_RATE[i])){
					detail.setExport_rate(Double.parseDouble(DUTY_RATE[i]));
				}else{
					detail.setExport_rate(0);
				}
				if (null!=TAX_RATE[i] && !"".equals(TAX_RATE[i])){
					detail.setOther_tax_rate(Double.parseDouble(TAX_RATE[i]));
				}else{
					detail.setOther_tax_rate(0);
				}
				if (null!=TAX_AMOUNT[i] && !"".equals(TAX_AMOUNT[i])){
					detail.setOther_tax_amount(Double.parseDouble(TAX_AMOUNT[i]));
				}else{
					detail.setOther_tax_amount(0);
				}
				detail.setCreate_By(iuser);
				detail.setUpdate_by(iuser);
			
				vc.add(detail);
			}
		}
		return vc;
		
		
	}
}

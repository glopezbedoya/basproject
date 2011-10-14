package com.tcd.ejda.model;

import java.sql.Date;

public class Form1Model {

	private String Form_no;
	private String Form_name;
	private String Form_status;
	private String update_by;
	
	/**
	 * Field for eJda 1
	 * **/
	private String Doc_ID;
	private String Country_ID;
	private String JDA_Type;
	private String Doc_Status;
	private String Invoice_No;
	private String Consignor_code;
	private String Consignor_name;
	private String Consignor_address;
	private String Consignee_code;
	private String Consignee_name;
	private String Consignee_address;
	private String AuthorAgent_code;
	private String AuthorAgent_name;
	private String AuthorAgent_address;
	private String Mode_Trans;
	private String Trans_Other;
	private Date Date_Import;
	private String Trans_Detail;
	private String PortImport_Code;
	private String PortImport_Desc;
	private String PortLoad_Code;
	private String PortLoad_Desc;
	private String Via_Code;
	private String Via_Desc;
	private Date Date_Receipt;
	private String ref_no;
	private String Regis_no;
	private String cus_name_code;
	private String cus_name_desc;
	private Integer Manifest_no;
	private Date duty_tax_receipt_date;
	private String duty_tax_receipt_desc;
	private String import_permit_no;
	private String exchg_ctrl_ref;
	private String special_treatment;
	private String country_origin_code;
	private String country_origin_desc;
	private String country_final_code;
	private String country_final_desc;
	private String bill_no;
	private String term_payment;
	private String cur_code;
	private double Received_amount;
	private String ExchgRate_ID;
	private String Equivalent;
	private String good_payment_code;
	private String good_payment_desc;
	private String country_of_good;
	private String fob_value;
	private String Insurance;
	private String Freight;
	private String cif_value;
	private String gross_weight;
	private String Measurement;
	private String Other_charg;
	private String declarant_name;
	private String id_card_no;
	private String status;
	private String cerify;
	private String cus_removal;
	private double tax_total;
	private String Other_charg2;
	private double payable_amount;
	private String manualscript_recerpt;
	private Date Create_Date;
	private String Create_By;
	private Date update_Date;
	private String Update_by;
	private String vessel_value;
	private String cust_removal;
	private String instruct_exam;
	private String result_exam;
	private String for_other_use;
	
	/* Field EJDA From 3*/
	private String moveMentPemitNo;
	private String expiryDate;
	private String securityRefNo;
	private double securityAmt;
	private double receiveAmt;
	private String billOfLading;
	
	public String getFor_other_use() {
		return for_other_use;
	}
	public void setFor_other_use(String forOtherUse) {
		for_other_use = forOtherUse;
	}
	public String getForm_no() {
		return Form_no;
	}
	public String getCust_removal() {
		return cust_removal;
	}
	public void setCust_removal(String custRemoval) {
		cust_removal = custRemoval;
	}
	public String getInstruct_exam() {
		return instruct_exam;
	}
	public void setInstruct_exam(String instructExam) {
		instruct_exam = instructExam;
	}
	public String getResult_exam() {
		return result_exam;
	}
	public void setResult_exam(String resultExam) {
		result_exam = resultExam;
	}
	public String getVessel_value() {
		return vessel_value;
	}
	public void setVessel_value(String vesselValue) {
		vessel_value = vesselValue;
	}
	public void setForm_no(String formNo) {
		Form_no = formNo;
	}
	public String getForm_name() {
		return Form_name;
	}
	public void setForm_name(String formName) {
		Form_name = formName;
	}
	public String getForm_status() {
		return Form_status;
	}
	public void setForm_status(String formStatus) {
		Form_status = formStatus;
	}
	public String getUpdate_by() {
		return update_by;
	}
	public void setUpdate_by(String updateBy) {
		update_by = updateBy;
	}
	
	/*
	 * Getter and Setter
	 * **/
	public String getDoc_ID() {
		return Doc_ID;
	}
	public void setDoc_ID(String docID) {
		Doc_ID = docID;
	}
	public String getCountry_ID() {
		return Country_ID;
	}
	public void setCountry_ID(String countryID) {
		Country_ID = countryID;
	}
	public String getJDA_Type() {
		return JDA_Type;
	}
	public void setJDA_Type(String jDAType) {
		JDA_Type = jDAType;
	}
	public String getDoc_Status() {
		return Doc_Status;
	}
	public void setDoc_Status(String docStatus) {
		Doc_Status = docStatus;
	}
	public String getInvoice_No() {
		return Invoice_No;
	}
	public void setInvoice_No(String invoiceNo) {
		Invoice_No = invoiceNo;
	}
	public String getConsignor_code() {
		return Consignor_code;
	}
	public void setConsignor_code(String consignorCode) {
		Consignor_code = consignorCode;
	}
	public String getConsignor_name() {
		return Consignor_name;
	}
	public void setConsignor_name(String consignorName) {
		Consignor_name = consignorName;
	}
	public String getConsignor_address() {
		return Consignor_address;
	}
	public void setConsignor_address(String consignorAddress) {
		Consignor_address = consignorAddress;
	}
	public String getConsignee_code() {
		return Consignee_code;
	}
	public void setConsignee_code(String consigneeCode) {
		Consignee_code = consigneeCode;
	}
	public String getConsignee_name() {
		return Consignee_name;
	}
	public void setConsignee_name(String consigneeName) {
		Consignee_name = consigneeName;
	}
	public String getConsignee_address() {
		return Consignee_address;
	}
	public void setConsignee_address(String consigneeAddress) {
		Consignee_address = consigneeAddress;
	}
	public String getAuthorAgent_code() {
		return AuthorAgent_code;
	}
	public void setAuthorAgent_code(String authorAgentCode) {
		AuthorAgent_code = authorAgentCode;
	}
	public String getAuthorAgent_name() {
		return AuthorAgent_name;
	}
	public void setAuthorAgent_name(String authorAgentName) {
		AuthorAgent_name = authorAgentName;
	}
	public String getAuthorAgent_address() {
		return AuthorAgent_address;
	}
	public void setAuthorAgent_address(String authorAgentAddress) {
		AuthorAgent_address = authorAgentAddress;
	}
	public String getMode_Trans() {
		return Mode_Trans;
	}
	public void setMode_Trans(String modeTrans) {
		Mode_Trans = modeTrans;
	}
	public String getTrans_Other() {
		return Trans_Other;
	}
	public void setTrans_Other(String transOther) {
		Trans_Other = transOther;
	}
	public Date getDate_Import() {
		return Date_Import;
	}
	public void setDate_Import(Date dateImport) {
		Date_Import = dateImport;
	}
	public String getTrans_Detail() {
		return Trans_Detail;
	}
	public void setTrans_Detail(String transDetail) {
		Trans_Detail = transDetail;
	}
	public String getPortImport_Code() {
		return PortImport_Code;
	}
	public void setPortImport_Code(String portImportCode) {
		PortImport_Code = portImportCode;
	}
	public String getPortImport_Desc() {
		return PortImport_Desc;
	}
	public void setPortImport_Desc(String portImportDesc) {
		PortImport_Desc = portImportDesc;
	}
	public String getPortLoad_Code() {
		return PortLoad_Code;
	}
	public void setPortLoad_Code(String portLoadCode) {
		PortLoad_Code = portLoadCode;
	}
	public String getPortLoad_Desc() {
		return PortLoad_Desc;
	}
	public void setPortLoad_Desc(String portLoadDesc) {
		PortLoad_Desc = portLoadDesc;
	}
	public String getVia_Code() {
		return Via_Code;
	}
	public void setVia_Code(String viaCode) {
		Via_Code = viaCode;
	}
	public String getVia_Desc() {
		return Via_Desc;
	}
	public void setVia_Desc(String viaDesc) {
		Via_Desc = viaDesc;
	}
	public Date getDate_Receipt() {
		return Date_Receipt;
	}
	public void setDate_Receipt(Date dateReceipt) {
		Date_Receipt = dateReceipt;
	}
	public String getRef_no() {
		return ref_no;
	}
	public void setRef_no(String refNo) {
		ref_no = refNo;
	}
	public String getRegis_no() {
		return Regis_no;
	}
	public void setRegis_no(String regisNo) {
		Regis_no = regisNo;
	}
	public String getCus_name_code() {
		return cus_name_code;
	}
	public void setCus_name_code(String cusNameCode) {
		cus_name_code = cusNameCode;
	}
	public String getCus_name_desc() {
		return cus_name_desc;
	}
	public void setCus_name_desc(String cusNameDesc) {
		cus_name_desc = cusNameDesc;
	}
	public Integer getManifest_no() {
		return Manifest_no;
	}
	public void setManifest_no(Integer manifestNo) {
		Manifest_no = manifestNo;
	}
	public Date getDuty_tax_receipt_date() {
		return duty_tax_receipt_date;
	}
	public void setDuty_tax_receipt_date(Date dutyTaxReceiptDate) {
		duty_tax_receipt_date = dutyTaxReceiptDate;
	}
	public String getDuty_tax_receipt_desc() {
		return duty_tax_receipt_desc;
	}
	public void setDuty_tax_receipt_desc(String dutyTaxReceiptDesc) {
		duty_tax_receipt_desc = dutyTaxReceiptDesc;
	}
	public String getImport_permit_no() {
		return import_permit_no;
	}
	public void setImport_permit_no(String importPermitNo) {
		import_permit_no = importPermitNo;
	}
	public String getExchg_ctrl_ref() {
		return exchg_ctrl_ref;
	}
	public void setExchg_ctrl_ref(String exchgCtrlRef) {
		exchg_ctrl_ref = exchgCtrlRef;
	}
	public String getSpecial_treatment() {
		return special_treatment;
	}
	public void setSpecial_treatment(String specialTreatment) {
		special_treatment = specialTreatment;
	}
	public String getCountry_origin_code() {
		return country_origin_code;
	}
	public void setCountry_origin_code(String countryOriginCode) {
		country_origin_code = countryOriginCode;
	}
	public String getCountry_origin_desc() {
		return country_origin_desc;
	}
	public void setCountry_origin_desc(String countryOriginDesc) {
		country_origin_desc = countryOriginDesc;
	}
	public String getCountry_final_code() {
		return country_final_code;
	}
	public void setCountry_final_code(String countryFinalCode) {
		country_final_code = countryFinalCode;
	}
	public String getCountry_final_desc() {
		return country_final_desc;
	}
	public void setCountry_final_desc(String countryFinalDesc) {
		country_final_desc = countryFinalDesc;
	}
	public String getBill_no() {
		return bill_no;
	}
	public void setBill_no(String billNo) {
		bill_no = billNo;
	}
	public String getTerm_payment() {
		return term_payment;
	}
	public void setTerm_payment(String termPayment) {
		term_payment = termPayment;
	}
	public String getCur_code() {
		return cur_code;
	}
	public void setCur_code(String curCode) {
		cur_code = curCode;
	}
	public double getReceived_amount() {
		return Received_amount;
	}
	public void setReceived_amount(double receivedAmount) {
		Received_amount = receivedAmount;
	}
	public String getExchgRate_ID() {
		return ExchgRate_ID;
	}
	public void setExchgRate_ID(String exchgRateID) {
		ExchgRate_ID = exchgRateID;
	}
	public String getEquivalent() {
		return Equivalent;
	}
	public void setEquivalent(String equivalent) {
		Equivalent = equivalent;
	}
	
	public String getGood_payment_code() {
		return good_payment_code;
	}
	public void setGood_payment_code(String goodPaymentCode) {
		good_payment_code = goodPaymentCode;
	}
	public String getGood_payment_desc() {
		return good_payment_desc;
	}
	public void setGood_payment_desc(String goodPaymentDesc) {
		good_payment_desc = goodPaymentDesc;
	}
	public String getCountry_of_good() {
		return country_of_good;
	}
	public void setCountry_of_good(String countryOfGood) {
		country_of_good = countryOfGood;
	}
	public String getFob_value() {
		return fob_value;
	}
	public void setFob_value(String fobValue) {
		fob_value = fobValue;
	}
	public String getInsurance() {
		return Insurance;
	}
	public void setInsurance(String insurance) {
		Insurance = insurance;
	}
	public String getFreight() {
		return Freight;
	}
	public void setFreight(String freight) {
		Freight = freight;
	}
	public String getCif_value() {
		return cif_value;
	}
	public void setCif_value(String cifValue) {
		cif_value = cifValue;
	}
	public String getGross_weight() {
		return gross_weight;
	}
	public void setGross_weight(String grossWeight) {
		gross_weight = grossWeight;
	}
	public String getMeasurement() {
		return Measurement;
	}
	public void setMeasurement(String measurement) {
		Measurement = measurement;
	}
	public String getOther_charg() {
		return Other_charg;
	}
	public void setOther_charg(String otherCharg) {
		Other_charg = otherCharg;
	}
	public String getDeclarant_name() {
		return declarant_name;
	}
	public void setDeclarant_name(String declarantName) {
		declarant_name = declarantName;
	}
	public String getId_card_no() {
		return id_card_no;
	}
	public void setId_card_no(String idCardNo) {
		id_card_no = idCardNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCerify() {
		return cerify;
	}
	public void setCerify(String cerify) {
		this.cerify = cerify;
	}
	public String getCus_removal() {
		return cus_removal;
	}
	public void setCus_removal(String cusRemoval) {
		cus_removal = cusRemoval;
	}
	public double getTax_total() {
		return tax_total;
	}
	public void setTax_total(double taxTotal) {
		tax_total = taxTotal;
	}
	public String getOther_charg2() {
		return Other_charg2;
	}
	public void setOther_charg2(String otherCharg2) {
		Other_charg2 = otherCharg2;
	}
	public double getPayable_amount() {
		return payable_amount;
	}
	public void setPayable_amount(double payableAmount) {
		payable_amount = payableAmount;
	}
	public String getManualscript_recerpt() {
		return manualscript_recerpt;
	}
	public void setManualscript_recerpt(String manualscriptRecerpt) {
		manualscript_recerpt = manualscriptRecerpt;
	}
	public Date getCreate_Date() {
		return Create_Date;
	}
	public void setCreate_Date(Date createDate) {
		Create_Date = createDate;
	}
	public String getCreate_By() {
		return Create_By;
	}
	public void setCreate_By(String createBy) {
		Create_By = createBy;
	}
	public Date getUpdate_Date() {
		return update_Date;
	}
	public void setUpdate_Date(Date updateDate) {
		update_Date = updateDate;
	}
	public String getMoveMentPemitNo() {
		return moveMentPemitNo;
	}
	public void setMoveMentPemitNo(String moveMentPemitNo) {
		this.moveMentPemitNo = moveMentPemitNo;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getSecurityRefNo() {
		return securityRefNo;
	}
	public void setSecurityRefNo(String securityRefNo) {
		this.securityRefNo = securityRefNo;
	}
	public String getBillOfLading() {
		return billOfLading;
	}
	public void setBillOfLading(String billOfLading) {
		this.billOfLading = billOfLading;
	}
	public Double getSecurityAmt() {
		return securityAmt;
	}
	public void setSecurityAmt(double securityAmt) {
		this.securityAmt = securityAmt;
	}
	public Double getReceiveAmt() {
		return receiveAmt;
	}
	public void setReceiveAmt(double receiveAmt) {
		this.receiveAmt = receiveAmt;
	}
	
	
	
}

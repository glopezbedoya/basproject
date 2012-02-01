package com.tcd.ejda.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.tcd.ejda.connection.JDBCServiceLocator;
import com.tcd.ejda.model.Form1Model;
import com.tcd.ejda.model.Form2Model;
import com.tcd.ejda.model.Form3Model;
import com.tcd.ejda.model.Form4Model;
import com.tcd.ejda.model.RoleModel;
import com.tcd.ejda.model.TransactionLogModel;
import com.tcd.ejda.model.UsrModel;
import com.tcd.ejda.model.ValueListModel;

public class ValueListDAOImpl implements ValueListDAO {
	
	private Logger log = Logger.getLogger(ValueListDAOImpl.class);
	
	JDBCServiceLocator db = new JDBCServiceLocator();
	
	public ValueListModel getResult(ValueListModel valueListM) throws SQLException {
		log.debug("[ getResult ]  : START ValueListDAOImpl");
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Vector result = new Vector();
		Object resultElement = null;
		try {
			if (!valueListM.isNextPage()) {
//				valueListM.setAtPage(valueListM.getAtPage());
				valueListM.setAtPage(1);
				valueListM.setNextPage(true);
			}
			valueListM.setCount(this.getCount(valueListM));
			try {
				conn = db.getConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int beginIndex = (valueListM.getAtPage() - 1) * valueListM.getItemsPerPage();
			StringBuffer sqlBuffer = new StringBuffer();
			
			
			//if(!CheckPage.equals("customerRegistration")){
			sqlBuffer.append("SELECT c.* FROM (SELECT ROW_NUMBER() OVER (ORDER BY 0) AS CURSOR_INDEX, m.* FROM (");
			
			//}
			sqlBuffer.append(valueListM.getSQL());
			if(valueListM.getOrderBy() != null && !"".equals(valueListM.getOrderBy())){
//				if(valueListM.getOrderBy().equalsIgnoreCase("REGISTRATION_DATE") || valueListM.getOrderBy().equalsIgnoreCase("LAST_ACTIVITY_DATE")){
				if(valueListM.getOrderBy().equalsIgnoreCase("REGISTRATION_DATE")){
					sqlBuffer.append(" ORDER BY DATE( ").append(valueListM.getOrderBy()).append(") ").append(valueListM.getOrderScheme());
				}else if(valueListM.getOrderBy().equalsIgnoreCase("ACCOUNT_NO")){ 
					sqlBuffer.append(" ORDER BY BIGINT(TRIM(REPLACE(").append(valueListM.getOrderBy()).append(",'/','')))").append(valueListM.getOrderScheme());
				}else{
					sqlBuffer.append(" ORDER BY ").append(valueListM.getOrderBy()).append(" ").append(valueListM.getOrderScheme());
				}
			}
			sqlBuffer.append(" ) m ) c WHERE c.CURSOR_INDEX > ? AND c.CURSOR_INDEX < ? ");
			log.debug("valueListM.getSQL()"+valueListM.getSQL());
			log.debug("[ getResult ]  : sqlBuffer = "+sqlBuffer);			
			ps = conn.prepareStatement(sqlBuffer.toString());
			int parameterIndex = 1;
			log.debug("valueListM.getParameters().size() = "+valueListM.getParameters().size());
			for(int i=0;i<valueListM.getParameters().size(); i++){
				if (valueListM.getParameters().get(i) instanceof java.lang.String) {
					log.debug("(String)valueListM.getParameters().get(i) = "+(String)valueListM.getParameters().get(i));
					ps.setString(parameterIndex++, (String)valueListM.getParameters().get(i));
				}else if (valueListM.getParameters().get(i) instanceof java.sql.Date) {
					log.debug("(java.sql.Date)valueListM.getParameters().get(i) = "+(java.sql.Date)valueListM.getParameters().get(i));
					ps.setDate(parameterIndex++, (java.sql.Date)valueListM.getParameters().get(i));
				}else{
					log.debug("(String)valueListM.getParameters().get(i) = "+(String)valueListM.getParameters().get(i));
					ps.setString(parameterIndex++, (String)valueListM.getParameters().get(i));
				}
			}
			ps.setInt(parameterIndex++, beginIndex);
			ps.setInt(parameterIndex++, (beginIndex + valueListM.getItemsPerPage()) + 1);
			log.debug("[ getResult ]  : query between > " + beginIndex +" and < "+((beginIndex + valueListM.getItemsPerPage()) + 1));
			rs = ps.executeQuery();
			while (rs.next()) {
				resultElement = mappResultSetToModel(valueListM.getReturnModel(),rs);
				result.add(resultElement);
			}
			valueListM.setResult(result);
			log.debug("[ getResult ]  : valuelist dao result : " + valueListM.getResult().size());
		} catch (SQLException e) {
			log.error(e.getMessage());
		} finally {
			try {
				if (conn != null)
					conn.commit();
			} catch (Exception e) {
			}
			try {
				if (rs != null)
					rs.close();
				rs = null;
			} catch (Exception e) {
			}
			try {
				if (ps != null)
					ps.close();
				ps = null;
			} catch (Exception e) {
			}
			try {
				if (conn != null)
					conn.close();
				conn = null;
			} catch (Exception e) {
			}
		}
		return valueListM;
	}
	
	public int getCount(ValueListModel valueListM) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try {
			String newSql;
//			int indexOfLastOrder = valueListM.getSQL().toUpperCase().lastIndexOf("ORDER BY");
//			if (indexOfLastOrder != -1) {
//				newSql = valueListM.getSQL().substring(0, indexOfLastOrder);
//			} else {
				newSql = valueListM.getSQL();
//			}			
				try {
					conn = db.getConnection();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			StringBuffer sqlBuffer = new StringBuffer();
			sqlBuffer.append("SELECT COUNT(*) AS COUNT FROM ( ");
			sqlBuffer.append(newSql);
			sqlBuffer.append(" ) TEMP ");
			log.debug("getCount ValueList = "+sqlBuffer.toString());
			ps = conn.prepareStatement(sqlBuffer.toString());
			int parameterIndex = 1;
			log.info("valueListM.getParameters().size() = "+valueListM.getParameters().size());
			for(int i=0;i<valueListM.getParameters().size(); i++){
				if (valueListM.getParameters().get(i) instanceof java.lang.String) {
					log.info("(String)valueListM.getParameters().get(i) = "+(String)valueListM.getParameters().get(i));
					ps.setString(parameterIndex++, (String)valueListM.getParameters().get(i));
				}else if (valueListM.getParameters().get(i) instanceof java.sql.Date) {
					log.info("(java.sql.Date)valueListM.getParameters().get(i) = "+(java.sql.Date)valueListM.getParameters().get(i));
					ps.setDate(parameterIndex++, (java.sql.Date)valueListM.getParameters().get(i));
				}else{
					log.info("(String)valueListM.getParameters().get(i) = "+(String)valueListM.getParameters().get(i));
					ps.setString(parameterIndex++, (String)valueListM.getParameters().get(i));
				}
			}
			rs = ps.executeQuery();
			count = (rs.next()) ? (rs.getInt("COUNT")) : 0;
		} catch (SQLException e) {
			log.error(e.getMessage());
		} finally {
			try {
				if (conn != null)
					conn.commit();
			} catch (Exception e) {
			}
			try {
				if (rs != null)
					rs.close();
				rs = null;
			} catch (Exception e) {
			}
			try {
				if (ps != null)
					ps.close();
				ps = null;
			} catch (Exception e) {
			}
			try {
				if (conn != null)
					conn.close();
				conn = null;
			} catch (Exception e) {
			}
		}
		log.debug("[ getCount ]  : count = "+count);
		return count;
	}

	private Object mappResultSetToModel(String returnModel,ResultSet rs){
		Object obj = new Object();
		log.debug("mappResultSetToModel returnModel = "+returnModel);
		if(returnModel.equalsIgnoreCase("TransactionLogModel")){
			obj = mapToTransactionLogModel(rs);
		}else if(returnModel.equalsIgnoreCase("RoleModel")){
			obj = mapToRoleModel(rs);
		}else if(returnModel.equalsIgnoreCase("UsrModel")){
			obj = mapToUsrModel(rs);
		}else if(returnModel.equalsIgnoreCase("Form1Model")){
			obj = mapToForm1Model(rs);
		}else if(returnModel.equalsIgnoreCase("Form3Model")){
			obj = mapToForm3Model(rs);
		}else if(returnModel.equalsIgnoreCase("Form4Model")){
			obj = mapToForm4Model(rs);
		}else if(returnModel.equalsIgnoreCase("Form2Model")){
			obj = mapToForm2Model(rs);
		}
		
		return obj;
	}
	
	private TransactionLogModel mapToTransactionLogModel(ResultSet rs){
		TransactionLogModel tranLog = new TransactionLogModel();
//		Locale locale = new Locale("en");
		try {
			tranLog.setUserName(rs.getString("USER_NAME"));
			tranLog.setTranAction(rs.getString("TRANS_ACTION"));
			tranLog.setDescription(rs.getString("DESCRIPTION"));
			tranLog.setIpAddress(rs.getString("IP_ADDRESS"));
			tranLog.setTranDate(rs.getDate("TRANS_DATE"));
			tranLog.setTranBy(rs.getString("TRANS_BY"));
			tranLog.setFirstName(rs.getString("FIRST_NAME"));
			tranLog.setLastName(rs.getString("LAST_NAME"));
			tranLog.setDepartment(rs.getString("DEPARTMENT"));
			tranLog.setTranId(rs.getString("TRANS_ID"));
		} catch (SQLException e) {
			log.error("Mapping Error : SQLException ",e);
		} catch (Exception e){
			log.error("Mapping Error : Exception ",e);
		}
		return tranLog;
	}
	
	private RoleModel mapToRoleModel(ResultSet rs){
		RoleModel roleM = new RoleModel();
//		Locale locale = new Locale("en");
		try {
			roleM.setRole_id(rs.getString("ROLE_ID"));
			roleM.setRole_name(rs.getString("ROLE_NAME"));
			
		} catch (SQLException e) {
			log.error("Mapping Error : SQLException ",e);
		} catch (Exception e){
			log.error("Mapping Error : Exception ",e);
		}
		return roleM;
	}
	
	private UsrModel mapToUsrModel(ResultSet rs){
		UsrModel userM = new UsrModel();
//		Locale locale = new Locale("en");
		try {
			userM.setJda_id(rs.getString("JDA_ID"));
			userM.setIV_USER(rs.getString("IV_USER"));
			userM.setUSERNAME(rs.getString("USER_NAME"));
			userM.setPWD(rs.getString("PASSWORD"));
			userM.setFIRSTNAME(rs.getString("FIRST_NAME"));
			userM.setLASTNAME(rs.getString("LAST_NAME"));
			userM.setDEPARTMENT(rs.getString("DEPARTMENT"));
			userM.setUSER_STATUS(rs.getString("USER_STATUS"));
			userM.setEFFECTIVE_DATE(Date.valueOf(rs.getString("EFFECTIVE_DATE")));
			userM.setEXPIRY_DATE(Date.valueOf(rs.getString("EXPIRY_DATE")));
			userM.setCreate_date(Date.valueOf(rs.getString("CREATE_DATE")));

		} catch (SQLException e) {
			log.error("Mapping Error : SQLException ",e);
		} catch (Exception e){
			log.error("Mapping Error : Exception ",e);
		}
		return userM;
	}
	private Form1Model mapToForm1Model(ResultSet rs){
		Form1Model form = new Form1Model();
//		Locale locale = new Locale("en");
		try {
			form.setDoc_ID(rs.getString("DOC_ID"));
			form.setCountry_ID(rs.getString("COUNTRY_ID"));//	COUNTRY_ID
			form.setJDA_Type(rs.getString("JDA_TYPE"));//JDA_TYPE
			form.setDoc_Status(rs.getString("DOC_STATUS"));//DOC_STATUS
			form.setInvoice_No(rs.getString("INVOICE_NO"));//INVOICE_NO
			form.setConsignor_code(rs.getString("CONSIGNOR_CODE"));//CONSIGNOR_CODE
			form.setConsignor_name(rs.getString("CONSIGNOR_NAME"));//CONSIGNOR_NAME
			form.setConsignor_address(rs.getString("CONSIGNOR_ADDRESS"));//CONSIGNOR_ADDRESS
			form.setConsignee_code(rs.getString("CONSIGNEE_CODE"));//CONSIGNEE_CODE
			form.setConsignee_name(rs.getString("CONSIGNEE_NAME"));//CONSIGNEE_NAME
			form.setConsignee_address(rs.getString("CONSIGNEE_ADDRESS")) ;//CONSIGNEE_ADDRESS
			form.setAuthorAgent_code(rs.getString("AUTHORAGENT_CODE"));//AUTHORAGENT_CODE
			form.setAuthorAgent_name(rs.getString("AUTHORAGENT_NAME")) ;//AUTHORAGENT_NAME
			form.setAuthorAgent_address(rs.getString("AUTHORAGENT_ADDRESS"));//AUTHORAGENT_ADDRESS
			form.setMode_Trans(rs.getString("MODE_TRANS")) ;//MODE_TRANS
			form.setTrans_Other(rs.getString("TRANS_OTHER"));//TRANS_OTHER
			form.setDate_Import(rs.getDate("DATE_IMPORT"));//DATE_IMPORT
			form.setTrans_Detail(rs.getString("TRANS_DETAIL"));//TRANS_DETAIL
			form.setPortImport_Code(rs.getString("PORTIMPORT_CODE"));//PORTIMPORT_CODE
			form.setPortImport_Desc(rs.getString("PORTIMPORT_DESC"));//PORTIMPORT_DESC
			form.setPortLoad_Code(rs.getString("PORTLOAD_CODE"));//PORTLOAD_CODE
			form.setPortLoad_Desc(rs.getString("PORTLOAD_DESC"));//PORTLOAD_DESC
			form.setVia_Code(rs.getString("VIA_CODE"));//VIA_CODE
			form.setVia_Desc(rs.getString("VIA_DESC")) ;//VIA_DESC
			form.setDate_Receipt(rs.getDate("DATE_RECEIPT"));//DATE_RECEIPT
			form.setRef_no(rs.getString("REF_NO"));//REF_NO
			form.setRegis_no(rs.getString("REGIS_NO"));//REGIS_NO
			form.setCus_name_code(rs.getString("CUS_NAME_CODE"));//CUS_NAME_CODE
			form.setCus_name_desc(rs.getString("CUS_NAME_DESC"));//CUS_NAME_DESC
			form.setManifest_no(rs.getInt("MANIFEST_NO"));//MANIFEST_NO
			form.setDuty_tax_receipt_date(rs.getDate("DUTY_TAX_RECEIPT_DATE"));//DUTY_TAX_RECEIPT_DATE
			form.setDuty_tax_receipt_desc(rs.getString("DUTY_TAX_RECEIPT_DESC"));//DUTY_TAX_RECEIPT_DESC
			form.setImport_permit_no(rs.getString("IMPORT_PERMIT_NO"));//IMPORT_PERMIT_NO
			form.setExchg_ctrl_ref(rs.getString("EXCHG_CTRL_REF"));//EXCHG_CTRL_REF
			form.setSpecial_treatment(rs.getString("SPECIAL_TREATMENT"));//SPECIAL_TREATMENT
			form.setCountry_origin_code(rs.getString("COUNTRY_ORIGIN_CODE"));//COUNTRY_ORIGIN_CODE
			form.setCountry_origin_desc(rs.getString("COUNTRY_ORIGIN_DESC"));//COUNTRY_ORIGIN_DESC
			form.setCountry_final_code(rs.getString("COUNTRY_FINAL_CODE"));//COUNTRY_FINAL_CODE
			form.setCountry_final_desc(rs.getString("COUNTRY_FINAL_DESC"));//COUNTRY_FINAL_DESC
			form.setBill_no(rs.getString("BILL_NO"));//BILL_NO
			form.setTerm_payment(rs.getString("TERM_PAYMENT"));//TERM_PAYMENT
			form.setCur_code(rs.getString("CUR_CODE"));//CUR_CODE
			form.setReceived_amount(rs.getDouble("RECEIVED_AMOUNT"));//RECEIVED_AMOUNT
			form.setExchgRate_ID(rs.getString("EXCHGRATE_ID"));//EXCHGRATE_ID
			form.setEquivalent(rs.getString("EQUIVALENT"));//EQUIVALENT
			form.setGood_payment_code(rs.getString("GOOD_PAYMENT_CODE"));//GOOD_PAYMENT_CODE
			form.setGood_payment_desc(rs.getString("GOOD_PAYMENT_DESC"));//GOOD_PAYMENT_DESC
			form.setCountry_of_good(rs.getString("COUNTRY_OF_GOOD")) ;//COUNTRY_OF_GOOD
			form.setFob_value(rs.getString("FOB_VALUE"));//FOB_VALUE
			form.setInsurance(rs.getString("INSURANCE")) ;//INSURANCE
			form.setFreight(rs.getString("FREIGHT"));//FREIGHT
			form.setCif_value(rs.getString("CIF_VALUE"));//CIF_VALUE
			form.setGross_weight(rs.getString("GROSS_WEIGHT")) ;//GROSS_WEIGHT
			form.setMeasurement(rs.getString("MEASUREMENT")) ;//MEASUREMENT
			form.setOther_charg(rs.getDouble("OTHER_CHARG"));//OTHER_CHARG
			form.setDeclarant_name(rs.getString("DECLARANT_NAME"));//DECLARANT_NAME
			form.setId_card_no(rs.getString("ID_CARD_NO"));//ID_CARD_NO
			form.setStatus(rs.getString("STATUS"));//STATUS
			form.setCerify(rs.getString("CERIFY")) ;//CERIFY
			form.setCus_removal(rs.getString("CUS_REMOVAL"));//CUS_REMOVAL
			form.setTax_total(rs.getDouble("TAX_TOTAL"));//TAX_TOTAL
			form.setOther_charg2(rs.getString("OTHER_CHARG2")) ;//OTHER_CHARG2
			form.setPayable_amount(rs.getDouble("PAYABLE_AMOUNT"));//PAYABLE_AMOUNT
			form.setManualscript_recerpt(rs.getString("MANUALSCRIPT_RECERPT")) ;//MANUALSCRIPT_RECERPT
			form.setVessel_value(rs.getString("VESSEL_VALUE"));//VESSEL_VALUE
			form.setInstruct_exam(rs.getString("INSTRUCT_EXAM"));//INSTRUCT_EXAM
			form.setResult_exam(rs.getString("RESULT_EXAM"));//RESULT_EXAM
			form.setFor_other_use(rs.getString("FOR_OTHER_USE"));//FOR_OTHER_USE
			form.setCreate_Date(rs.getDate("CREATE_DATE"));
			form.setCreate_By(rs.getString("CREATE_BY"));
			form.setUpdate_Date(rs.getDate("UPDATE_DATE"));
			form.setUpdate_by(rs.getString("UPDATE_BY"));
			
			//Form 3
			form.setMoveMentPemitNo(rs.getString("MOVEMENT_PEMIT_NO"));
			form.setExpiryDate(rs.getDate("EXPIRE_DATE"));
			form.setSecurityRefNo(rs.getString("SECURITY_REF_NO"));
			form.setSecurityAmt(rs.getDouble("SECURITY_AMT"));
			form.setReceiveAmt(rs.getDouble("RECEIVE_AMT"));
			form.setBillOfLading(rs.getString("BILL_OF_LADING"));
			form.setProperOffice(rs.getString("PROPER_OFFICE"));
			form.setRequestApproved(rs.getString("REQUEST_APPROVED"));
			form.setCertified(rs.getString("CERTIFIED"));
			
			
		} catch (SQLException e) {
			log.error("mapToForm1Model Error : SQLException ",e);
		} catch (Exception e){
			log.error("mapToForm1Model Error : Exception ",e);
		}
		return form;
	}
	private Form2Model mapToForm2Model(ResultSet rs){
		Form2Model from2 = new Form2Model();
//		Locale locale = new Locale("en");
		try {
			from2.setForm_name(rs.getString("FORM_NAME"));
			from2.setForm_no(rs.getString("FORM_NO"));
			from2.setForm_status(rs.getString("STATUS"));
			from2.setUpdate_by(rs.getString("UPDATE_BY"));
			
		} catch (SQLException e) {
			log.error("mapToForm2Model Error : SQLException ",e);
		} catch (Exception e){
			log.error("mapToForm2Model Error : Exception ",e);
		}
		return from2;
	}
	
	private Form3Model mapToForm3Model(ResultSet rs){
		Form3Model from4 = new Form3Model();
		try {
			from4.setForm_name(rs.getString("FORM_NAME"));
			from4.setForm_no(rs.getString("FORM_NO"));
			from4.setForm_status(rs.getString("STATUS"));
			from4.setUpdate_by(rs.getString("UPDATE_BY"));
			
		} catch (SQLException e) {
			log.error("Mapping Error : SQLException ",e);
		} catch (Exception e){
			log.error("Mapping Error : Exception ",e);
		}
		return from4;
	}
	
	private Form4Model mapToForm4Model(ResultSet rs){
		Form4Model from4 = new Form4Model();
		try {
			from4.setForm_name(rs.getString("FORM_NAME"));
			from4.setForm_no(rs.getString("FORM_NO"));
			from4.setForm_status(rs.getString("STATUS"));
			from4.setUpdate_by(rs.getString("UPDATE_BY"));
			
		} catch (SQLException e) {
			log.error("Mapping Error : SQLException ",e);
		} catch (Exception e){
			log.error("Mapping Error : Exception ",e);
		}
		return from4;
	}
	
}

package com.tcd.ejda.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.tcd.ejda.connection.JDBCServiceLocator;
import com.tcd.ejda.model.Form1Model;
import com.tcd.ejda.model.FormDetail1Model;
import com.tcd.ejda.model.FormDetail2Model;
import com.tcd.ejda.model.FormDocAttachModel;
import com.tcd.ejda.utilities.DisplayFormatUtil;

public class Form1DAOImpl implements Form1DAO {
	JDBCServiceLocator db = new JDBCServiceLocator();
	
	private Logger log = Logger.getLogger(Form1DAOImpl.class); 
	@Override
	public void saveFrom1Table1(Form1Model form1) throws SQLException {
		log.debug("[ Start : submitFrom1Table1 ]");
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String form_no = "";
		Vector searchMenu = new Vector();
		
		try {
			conn = db.getConnection();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		StringBuffer sqlSeq = new StringBuffer();
		sqlSeq.append("select FORM1_SEQ.nextval as SEQ from dual ");
		
		log.debug("sql insert>>>>>>>>>>>>  " + sqlSeq.toString());
		ps = conn.prepareStatement(sqlSeq.toString());
		rs = ps.executeQuery();
		while (rs.next()) {
			
			form_no = "FN" + rs.getString("SEQ");

		}
		rs.close();
		
		StringBuffer sqlupd = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		sql.append("insert into JDA_FORM1_CTRL (FORM_NO, FORM_NAME, STATUS, UPDATE_BY) values (?, ?, ?, ?)");
		log.debug("sql >> " + sql.toString());
		try {
			ps = conn.prepareStatement(sql.toString());
			int parameterIndex = 1;
			ps.setString(parameterIndex++, form_no);
			ps.setString(parameterIndex++, form1.getForm_name());
			ps.setString(parameterIndex++, form1.getForm_status());
			ps.setString(parameterIndex++, form1.getUpdate_by());
			rs = ps.executeQuery();
		
			log.debug("Connection session: ");
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error("submitFrom1Table1",e);
			e.printStackTrace();
		}finally{
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
	}
	@Override
	public boolean UpdateFrom1Table(Form1Model form1) throws SQLException {
		log.debug("[Start : UpdateFrom1Table ]");
		// TODO Auto-generated method stub
		boolean blSuccess=false;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = db.getConnection();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		StringBuffer sql = new StringBuffer();
		conn.setAutoCommit(false); 
		
		try {
						
			sql.append("UPDATE JDA_FORM1_CTRL SET STATUS = ?, UPDATE_BY = ? ");
			sql.append("WHERE FORM_NO = ? ");
			log.debug("updateRole JDA_FORM1_CTRL >>> " + sql.toString());
			ps = conn.prepareStatement(sql.toString());
			int seq=1;
			log.debug("form1.getForm_no() >> "+form1.getForm_no());
			
			ps.setString(seq++, form1.getForm_status());
			ps.setString(seq++, form1.getUpdate_by());
			ps.setString(seq++, form1.getForm_no());
			
			int rsInt = ps.executeUpdate();
			if (rsInt > 0) {
				blSuccess = true;
			}
			
			log.debug("blSuccess >> " + blSuccess );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			log.equals(e.getMessage());
		}finally{
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
		
		return blSuccess;
		
	}
	public void saveFromEJDA(Form1Model form, Vector detail1, Vector detail2, Vector doc) throws SQLException {
		log.debug("[ Start : submitFrom1Table1 ]");
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String doc_id = "";
		Vector searchMenu = new Vector();
		
		try {
			conn = db.getConnection();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		StringBuffer sqlSeq = new StringBuffer();
		sqlSeq.append("select FORM_T_DOC_SEQ.nextval as SEQ from dual ");
		
		log.debug("sql insert>>>>>>>>>>>>  " + sqlSeq.toString());
		ps = conn.prepareStatement(sqlSeq.toString());
		rs = ps.executeQuery();
		while (rs.next()) {
			
			doc_id = DisplayFormatUtil.FormatDocId(rs.getString("SEQ"));

		}
		log.debug("doc_id >> " + doc_id);
		rs.close();
		
		StringBuffer sql = new StringBuffer();
		sql.append("insert into JDA_FORM_T_DOC(DOC_ID, COUNTRY_ID, JDA_TYPE, DOC_STATUS, INVOICE_NO, CONSIGNOR_CODE, CONSIGNOR_NAME, ");
		sql.append("CONSIGNOR_ADDRESS, CONSIGNEE_CODE, CONSIGNEE_NAME, CONSIGNEE_ADDRESS, AUTHORAGENT_CODE, AUTHORAGENT_NAME, ");
		sql.append("AUTHORAGENT_ADDRESS, MODE_TRANS, TRANS_OTHER, DATE_IMPORT, TRANS_DETAIL, PORTIMPORT_CODE, PORTIMPORT_DESC, ");
		sql.append("PORTLOAD_CODE, PORTLOAD_DESC, VIA_CODE, VIA_DESC, DATE_RECEIPT, REF_NO, REGIS_NO, CUS_NAME_CODE, CUS_NAME_DESC, ");
		sql.append("MANIFEST_NO, DUTY_TAX_RECEIPT_DATE, DUTY_TAX_RECEIPT_DESC, IMPORT_PERMIT_NO, EXCHG_CTRL_REF, SPECIAL_TREATMENT, ");
		sql.append("COUNTRY_ORIGIN_CODE, COUNTRY_ORIGIN_DESC, COUNTRY_FINAL_CODE, COUNTRY_FINAL_DESC, BILL_NO, TERM_PAYMENT, CUR_CODE, ");
		sql.append("RECEIVED_AMOUNT, EXCHGRATE_ID, EQUIVALENT, GOOD_PAYMENT_CODE,GOOD_PAYMENT_DESC, COUNTRY_OF_GOOD, FOB_VALUE, INSURANCE, FREIGHT, CIF_VALUE, ");
		sql.append("GROSS_WEIGHT, MEASUREMENT, OTHER_CHARG, DECLARANT_NAME, ID_CARD_NO, STATUS, CERIFY, CUS_REMOVAL, TAX_TOTAL, ");
		sql.append("OTHER_CHARG2, PAYABLE_AMOUNT, MANUALSCRIPT_RECERPT,VESSEL_VALUE, INSTRUCT_EXAM, RESULT_EXAM, FOR_OTHER_USE, ");
		sql.append("CREATE_DATE, CREATE_BY, UPDATE_DATE, UPDATE_BY ");
		sql.append(" ,MOVEMENT_PEMIT_NO, EXPIRE_DATE, SECURITY_REF_NO, SECURITY_AMT, RECEIVE_AMT ");
		sql.append(" ,BILL_OF_LADING, PROPER_OFFICE, REQUEST_APPROVED, CERTIFIED )");

		sql.append("values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ");
		sql.append(", ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ");
		sql.append(", ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, ?, SYSDATE, ?");
		sql.append(" , ?, ?, ?, ?, ? ");
		sql.append(" , ?, ?, ?, ? )");
		log.debug("sql >> " + sql.toString());
		try {
			ps = conn.prepareStatement(sql.toString());
			int parameterIndex = 1;
			ps.setString(parameterIndex++, doc_id);
//			ps.setString(parameterIndex++, form.getDoc_ID());//	DOC_ID
			ps.setString(parameterIndex++, form.getCountry_ID());//	COUNTRY_ID
			ps.setString(parameterIndex++, form.getJDA_Type());//JDA_TYPE
			ps.setString(parameterIndex++, form.getDoc_Status());//DOC_STATUS
			ps.setString(parameterIndex++, form.getInvoice_No());//INVOICE_NO
			ps.setString(parameterIndex++, form.getConsignor_code());//CONSIGNOR_CODE
			ps.setString(parameterIndex++, form.getConsignor_name());//CONSIGNOR_NAME
			ps.setString(parameterIndex++, form.getConsignor_address());//CONSIGNOR_ADDRESS
			ps.setString(parameterIndex++, form.getConsignee_code());//CONSIGNEE_CODE
			ps.setString(parameterIndex++, form.getConsignee_name());//CONSIGNEE_NAME
			ps.setString(parameterIndex++, form.getConsignee_address());//CONSIGNEE_ADDRESS
			ps.setString(parameterIndex++, form.getAuthorAgent_code());//AUTHORAGENT_CODE
			ps.setString(parameterIndex++, form.getAuthorAgent_name());//AUTHORAGENT_NAME
			ps.setString(parameterIndex++, form.getAuthorAgent_address());//AUTHORAGENT_ADDRESS
			ps.setString(parameterIndex++, form.getMode_Trans());//MODE_TRANS
			ps.setString(parameterIndex++, form.getTrans_Other());//TRANS_OTHER
			ps.setDate(parameterIndex++, form.getDate_Import());//DATE_IMPORT
			ps.setString(parameterIndex++, form.getTrans_Detail());//TRANS_DETAIL
			ps.setString(parameterIndex++, form.getPortImport_Code());//PORTIMPORT_CODE
			ps.setString(parameterIndex++, form.getPortImport_Desc());//PORTIMPORT_DESC
			ps.setString(parameterIndex++, form.getPortLoad_Code());//PORTLOAD_CODE
			ps.setString(parameterIndex++, form.getPortLoad_Desc());//PORTLOAD_DESC
			ps.setString(parameterIndex++, form.getVia_Code());//VIA_CODE
			ps.setString(parameterIndex++, form.getVia_Desc());//VIA_DESC
			ps.setDate(parameterIndex++, form.getDate_Receipt());//DATE_RECEIPT
			ps.setString(parameterIndex++, form.getRef_no());//REF_NO
			ps.setString(parameterIndex++, form.getRegis_no());//REGIS_NO
			ps.setString(parameterIndex++, form.getCus_name_code());//CUS_NAME_CODE
			ps.setString(parameterIndex++, form.getCus_name_desc());//CUS_NAME_DESC
			ps.setInt(parameterIndex++, form.getManifest_no());//MANIFEST_NO
			ps.setDate(parameterIndex++, form.getDuty_tax_receipt_date());//DUTY_TAX_RECEIPT_DATE
			ps.setString(parameterIndex++, form.getDuty_tax_receipt_desc());//DUTY_TAX_RECEIPT_DESC
			ps.setString(parameterIndex++, form.getImport_permit_no());//IMPORT_PERMIT_NO
			ps.setString(parameterIndex++, form.getExchg_ctrl_ref());//EXCHG_CTRL_REF
			ps.setString(parameterIndex++, form.getSpecial_treatment());//SPECIAL_TREATMENT
			ps.setString(parameterIndex++, form.getCountry_origin_code());//COUNTRY_ORIGIN_CODE
			ps.setString(parameterIndex++, form.getCountry_origin_desc());//COUNTRY_ORIGIN_DESC
			ps.setString(parameterIndex++, form.getCountry_final_code());//COUNTRY_FINAL_CODE
			ps.setString(parameterIndex++, form.getCountry_final_desc());//COUNTRY_FINAL_DESC
			ps.setString(parameterIndex++, form.getBill_no());//BILL_NO
			ps.setString(parameterIndex++, form.getTerm_payment());//TERM_PAYMENT
			ps.setString(parameterIndex++, form.getCur_code());//CUR_CODE
			ps.setDouble(parameterIndex++, form.getReceived_amount());//RECEIVED_AMOUNT
			ps.setString(parameterIndex++, form.getExchgRate_ID());//EXCHGRATE_ID
			ps.setString(parameterIndex++, form.getEquivalent());//EQUIVALENT
			ps.setString(parameterIndex++, form.getGood_payment_code());//GOOD_PAYMENT_CODE
			ps.setString(parameterIndex++, form.getGood_payment_desc());//GOOD_PAYMENT_DESC
			ps.setString(parameterIndex++, form.getCountry_of_good());//COUNTRY_OF_GOOD
			ps.setString(parameterIndex++, form.getFob_value());//FOB_VALUE
			ps.setString(parameterIndex++, form.getInsurance());//INSURANCE
			ps.setString(parameterIndex++, form.getFreight());//FREIGHT
			ps.setString(parameterIndex++, form.getCif_value());//CIF_VALUE
			ps.setString(parameterIndex++, form.getGross_weight());//GROSS_WEIGHT
			ps.setString(parameterIndex++, form.getMeasurement());//MEASUREMENT
			ps.setString(parameterIndex++, form.getOther_charg());//OTHER_CHARG
			ps.setString(parameterIndex++, form.getDeclarant_name());//DECLARANT_NAME
			ps.setString(parameterIndex++, form.getId_card_no());//ID_CARD_NO
			ps.setString(parameterIndex++, form.getStatus());//STATUS
			ps.setString(parameterIndex++, form.getCerify());//CERIFY
			ps.setString(parameterIndex++, form.getCus_removal());//CUS_REMOVAL
			ps.setDouble(parameterIndex++, form.getTax_total());//TAX_TOTAL
			ps.setString(parameterIndex++, form.getOther_charg2());//OTHER_CHARG2
			ps.setDouble(parameterIndex++, form.getPayable_amount());//PAYABLE_AMOUNT
			ps.setString(parameterIndex++, form.getManualscript_recerpt());//MANUALSCRIPT_RECERPT
			ps.setString(parameterIndex++, form.getVessel_value());//VESSEL_VALUE
			ps.setString(parameterIndex++, form.getInstruct_exam());//INSTRUCT_EXAM
			ps.setString(parameterIndex++, form.getResult_exam());//RESULT_EXAM
			ps.setString(parameterIndex++, form.getFor_other_use());//FOR_OTHER_USE
			//CREATE_DATE
			ps.setString(parameterIndex++, form.getCreate_By());//CREATE_BY
			//UPDATE_DATE
			ps.setString(parameterIndex++, form.getUpdate_by());//UPDATE_BY
			
			ps.setString(parameterIndex++, form.getMovementPemitNo());//MOVEMENT_PEMIT_NO
			ps.setDate(parameterIndex++, form.getExpiryDate());//EXPIRE_DATE
			ps.setString(parameterIndex++, form.getSecurityRefNo());//SECURITY_REF_NO
			ps.setDouble(parameterIndex++, form.getSecurityAmt());//SECURITY_AMT
			ps.setDouble(parameterIndex++, form.getReceiveAmt());//RECEIVE_AMT
			ps.setString(parameterIndex++, form.getBillOfLading());//BILL_OF_LADING
			ps.setString(parameterIndex++, form.getProperOffice());//PROPER_OFFICE
			ps.setString(parameterIndex++, form.getRequestApproved());//REQUEST_APPROVED
			ps.setString(parameterIndex++, form.getCertified());//CERTIFIED
			
			rs = ps.executeQuery();
		
			if(detail1.size()>0){
				StringBuffer sql1 = new StringBuffer();
				sql1.append("insert into JDA_FORM_T_DOC_DETAIL1(ITEM_NO, DOC_ID, MARKS_NO, NO_TYPE_PACKAGE, GOOD_DESC, ");
				sql1.append("CUST_CODE, CUST_UNIT, CREATE_DATE, CREATE_BY, UPDATE_DATE, UPDATE_BY) ");
				sql1.append("values (?, ?, ?, ?, ?, ?, ?, SYSDATE, ?, SYSDATE, ?)");
				log.debug("sql1 >> " + sql1.toString());
				
				ps = conn.prepareStatement(sql1.toString());
				log.debug("detail1.size() = " +detail1.size());
				for(int i =0; i < detail1.size(); i++){
					int parameterIndex1 = 1;
					FormDetail1Model f1 = (FormDetail1Model)detail1.get(i);
					
					ps.setString(parameterIndex1++, f1.getItem_no());
					ps.setString(parameterIndex1++, doc_id);//	DOC_ID
					ps.setString(parameterIndex1++, f1.getMarks_no());
					ps.setString(parameterIndex1++, f1.getNo_type_package());
					ps.setString(parameterIndex1++, f1.getGood_desc());
					ps.setString(parameterIndex1++, f1.getCust_code());
					ps.setString(parameterIndex1++, f1.getCust_unit());
					ps.setString(parameterIndex1++, f1.getCreate_By());
					ps.setString(parameterIndex1++, f1.getUpdate_by());
					
					log.debug("f1.getItem_no() : "+ i + ":"+f1.getItem_no());
					log.debug("doc_id : "+ i + ":"+doc_id);//	DOC_ID
					log.debug("f1.getMarks_no() : "+ i + ":"+ i + ":" +f1.getMarks_no());
					log.debug("f1.getNo_type_package() : "+ i + ":"+f1.getNo_type_package());
					log.debug("f1.getGood_desc() : "+ i + ":"+f1.getGood_desc());
					log.debug("f1.getCust_code() : "+ i + ":" +f1.getGood_desc());
					log.debug("f1.getCust_unit() : "+ i + ":" +f1.getCust_unit());
					log.debug("f1.getCreate_By() : "+ i + ":"+f1.getCreate_By());
					log.debug("f1.getUpdate_by() : "+ i + ":"+f1.getUpdate_by());
					
					rs = ps.executeQuery();	
				}
				
			}
			if(detail2.size()>0){
				StringBuffer sql2 = new StringBuffer();
				sql2.append(" insert into JDA_FORM_T_DOC_DETAIL2(ITEM_NO, DOC_ID, QTY_CUST_UNIT, UNIT_VAL_ACTUAL, UNIT_VAL_CUSTOM, TOTAL_VALUE, ");
				sql2.append(" EXPORT_RATE, EXPORT_AMOUNT, OTHER_TAX_TYPE, OTHER_TAX_RATE, OTHER_TAX_AMOUNT, CREATE_DATE, CREATE_BY, UPDATE_DATE, UPDATE_BY ");
				sql2.append(" , ORIGIN_CODE, VALUE_PER_UNIT ) ");
				sql2.append(" values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, ?, SYSDATE, ?");
				sql2.append(" , ?, ?)");
				log.debug("sql2 >> " + sql2.toString());
				
				ps = conn.prepareStatement(sql2.toString());
				
				for(int i =0;i<detail2.size();i++){
					int parameterIndex2 = 1;
					FormDetail2Model f2 = (FormDetail2Model)detail2.get(i);
					ps.setString(parameterIndex2++, f2.getItem_no());
					ps.setString(parameterIndex2++, doc_id);//	DOC_ID
					ps.setDouble(parameterIndex2++, f2.getQty_cust_unit());
					ps.setString(parameterIndex2++, f2.getUnit_val_actual());
					ps.setString(parameterIndex2++, f2.getUnit_val_custom());
					ps.setDouble(parameterIndex2++, f2.getTotal_value());
					ps.setDouble(parameterIndex2++, f2.getExport_rate());
					ps.setDouble(parameterIndex2++, f2.getExport_amount());
					ps.setString(parameterIndex2++, f2.getOther_tax_type());
					ps.setDouble(parameterIndex2++, f2.getOther_tax_rate());
					ps.setDouble(parameterIndex2++, f2.getOther_tax_amount());
					ps.setString(parameterIndex2++, f2.getCreate_By());
					ps.setString(parameterIndex2++, f2.getUpdate_by());
					ps.setString(parameterIndex2++, f2.getOriginCode());
					ps.setDouble(parameterIndex2++, f2.getValuePerUnit());
					
					rs = ps.executeQuery();
				}
				
			}
			
			if(doc.size()>0){
				StringBuffer sql3 = new StringBuffer();
				sql3.append("insert into JDA_FORM_T_DOC_ATTACH(REF_NO, DOC_ID, DOC_NAME, DOC_PATH, DOC_JDA_TYPE, DOC_STATUS, ");
				sql3.append("CREATE_DATE, CREATE_BY, UPDATE_DATE, UPDATE_BY) ");
				sql3.append("values (FORM_T_DOC_ATTACH_SEQ.nextval, ?, ?, ?, ?, ?, SYSDATE, ?, SYSDATE, ?)");
				log.debug("sql3 >> " + sql3.toString());
				
				ps = conn.prepareStatement(sql3.toString());
				
				for(int i =0;i<doc.size();i++){
					int parameterIndex2 = 1;
					FormDocAttachModel d1 = (FormDocAttachModel)doc.get(i);
//					ps.setString(parameterIndex2++, d1.getRef_no());
					ps.setString(parameterIndex2++, doc_id);//	DOC_ID
					ps.setString(parameterIndex2++, d1.getDoc_name());
					ps.setString(parameterIndex2++, "");
					ps.setString(parameterIndex2++, d1.getDoc_jda_type());
					ps.setString(parameterIndex2++, d1.getDoc_status());
					ps.setString(parameterIndex2++, d1.getCreate_By());
					ps.setString(parameterIndex2++, d1.getUpdate_by());
					
					rs = ps.executeQuery();
				}
				
			}
			log.debug("Connection session: ");
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error("submitFrom1Table1",e);
			e.printStackTrace();
		}finally{
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
	}
	public boolean UpdateFromTable(Form1Model form1) throws SQLException {
		log.debug("[Start : UpdateFromTable ]");
		// TODO Auto-generated method stub
		boolean blSuccess=false;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = db.getConnection();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		StringBuffer sql = new StringBuffer();
		conn.setAutoCommit(false); 
		
		try {
						
			sql.append("UPDATE JDA_FORM_T_DOC SET DOC_STATUS = ?, UPDATE_BY = ? ");
			sql.append("WHERE DOC_ID = ? AND JDA_TYPE = ? ");
			log.debug("updateRole JDA_FORM_T_DOC >>> " + sql.toString());
			ps = conn.prepareStatement(sql.toString());
			int seq=1;
			log.debug("form1.getForm_no() >> "+form1.getDoc_ID());
			log.debug("form1.getForm_no() >> "+form1.getJDA_Type());
			
			ps.setString(seq++, form1.getDoc_Status());
			ps.setString(seq++, form1.getUpdate_by());
			ps.setString(seq++, form1.getDoc_ID());
			ps.setString(seq++, form1.getJDA_Type());
			
			int rsInt = ps.executeUpdate();
			if (rsInt > 0) {
				blSuccess = true;
			}
			
			log.debug("blSuccess >> " + blSuccess );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			log.equals(e.getMessage());
		}finally{
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
		
		return blSuccess;
		
	}
	
	public Form1Model searchFormModel(String docId) throws SQLException{
		log.debug("[Start : searchFormModel ]");
		log.debug("docId = "+docId);
		Form1Model form1M = new Form1Model();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = db.getConnection();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		StringBuffer sql = new StringBuffer();
		
		try {
						
			sql.append(" SELECT * FROM JDA_FORM_T_DOC ");
			sql.append("WHERE DOC_ID = ? ");
			log.debug("Search JDA_FORM_T_DOC >>> " + sql.toString());
			ps = conn.prepareStatement(sql.toString());
			int seq=1;
			
			ps.setString(seq++, docId);
			
			rs = ps.executeQuery();
			if(rs.next()){
				form1M.setDoc_ID(rs.getString("DOC_ID"));
				form1M.setCountry_ID(rs.getString("COUNTRY_ID"));
				form1M.setJDA_Type(rs.getString("JDA_TYPE"));
				form1M.setDoc_Status(rs.getString("DOC_STATUS"));
				form1M.setInvoice_No(rs.getString("INVOICE_NO"));
				form1M.setConsignor_code(rs.getString("CONSIGNOR_CODE"));
				form1M.setConsignor_name(rs.getString("CONSIGNOR_NAME"));
				form1M.setConsignor_address(rs.getString("CONSIGNOR_ADDRESS"));
				form1M.setConsignee_code(rs.getString("CONSIGNEE_CODE"));
				form1M.setConsignee_name(rs.getString("CONSIGNEE_NAME"));
				form1M.setConsignee_address(rs.getString("CONSIGNEE_ADDRESS"));
				form1M.setAuthorAgent_code(rs.getString("AUTHORAGENT_CODE"));
				form1M.setAuthorAgent_name(rs.getString("AUTHORAGENT_NAME"));
				form1M.setAuthorAgent_address(rs.getString("AUTHORAGENT_ADDRESS"));
				form1M.setMode_Trans(rs.getString("MODE_TRANS"));
				form1M.setTrans_Other(rs.getString("TRANS_OTHER"));
				form1M.setDate_Import(rs.getDate("DATE_IMPORT"));
				form1M.setTrans_Detail(rs.getString("TRANS_DETAIL"));
				form1M.setPortImport_Code(rs.getString("PORTIMPORT_CODE"));
				form1M.setPortImport_Desc(rs.getString("PORTIMPORT_DESC"));
				form1M.setPortLoad_Code(rs.getString("PORTLOAD_CODE"));
				form1M.setPortLoad_Desc(rs.getString("PORTLOAD_DESC"));
				form1M.setVia_Code(rs.getString("VIA_CODE"));
				form1M.setVia_Desc(rs.getString("VIA_DESC"));
				form1M.setDate_Receipt(rs.getDate("DATE_RECEIPT"));
				form1M.setRef_no(rs.getString("REF_NO"));
				form1M.setRegis_no(rs.getString("REGIS_NO"));
				form1M.setCus_name_code(rs.getString("CUS_NAME_CODE"));
				form1M.setCus_name_desc(rs.getString("CUS_NAME_DESC"));
				form1M.setManifest_no(rs.getInt("MANIFEST_NO"));
				form1M.setDuty_tax_receipt_date(rs.getDate("DUTY_TAX_RECEIPT_DATE"));
				form1M.setDuty_tax_receipt_desc(rs.getString("DUTY_TAX_RECEIPT_DESC"));
				form1M.setImport_permit_no(rs.getString("IMPORT_PERMIT_NO"));
				form1M.setExchg_ctrl_ref(rs.getString("EXCHG_CTRL_REF"));
				form1M.setSpecial_treatment(rs.getString("SPECIAL_TREATMENT"));
				form1M.setCountry_origin_code(rs.getString("COUNTRY_ORIGIN_CODE"));
				form1M.setCountry_origin_desc(rs.getString("COUNTRY_ORIGIN_DESC"));
				form1M.setCountry_final_code(rs.getString("COUNTRY_FINAL_CODE"));
				form1M.setCountry_final_desc(rs.getString("COUNTRY_FINAL_DESC"));
				form1M.setBill_no(rs.getString("BILL_NO"));
				form1M.setTerm_payment(rs.getString("TERM_PAYMENT"));
				form1M.setCur_code(rs.getString("CUR_CODE"));
				form1M.setReceived_amount(rs.getDouble("RECEIVED_AMOUNT"));
				form1M.setExchgRate_ID(rs.getString("EXCHGRATE_ID"));
				form1M.setEquivalent(rs.getString("EQUIVALENT"));
				form1M.setGood_payment_code(rs.getString("GOOD_PAYMENT_CODE"));
				form1M.setGood_payment_desc(rs.getString("GOOD_PAYMENT_DESC"));
				form1M.setCountry_of_good(rs.getString("COUNTRY_OF_GOOD"));
				form1M.setFob_value(rs.getString("FOB_VALUE"));
				form1M.setInsurance(rs.getString("INSURANCE"));
				form1M.setFreight(rs.getString("FREIGHT"));
				form1M.setCif_value(rs.getString("CIF_VALUE"));
				form1M.setGross_weight(rs.getString("GROSS_WEIGHT"));
				form1M.setMeasurement(rs.getString("MEASUREMENT"));
				form1M.setOther_charg(rs.getString("OTHER_CHARG"));
				form1M.setDeclarant_name(rs.getString("DECLARANT_NAME"));
				form1M.setId_card_no(rs.getString("ID_CARD_NO"));
				form1M.setStatus(rs.getString("STATUS"));
				form1M.setCerify(rs.getString("CERIFY"));
				form1M.setCus_removal(rs.getString("CUS_REMOVAL"));
				form1M.setTax_total(rs.getDouble("TAX_TOTAL"));
				form1M.setOther_charg2(rs.getString("OTHER_CHARG2"));
				form1M.setPayable_amount(rs.getDouble("PAYABLE_AMOUNT"));
				form1M.setManualscript_recerpt(rs.getString("MANUALSCRIPT_RECERPT"));
				form1M.setVessel_value(rs.getString("VESSEL_VALUE"));
				form1M.setInstruct_exam(rs.getString("INSTRUCT_EXAM"));
				form1M.setResult_exam(rs.getString("RESULT_EXAM"));
				form1M.setFor_other_use(rs.getString("FOR_OTHER_USE"));
				form1M.setCreate_Date(rs.getDate("CREATE_DATE"));
				form1M.setCreate_By(rs.getString("CREATE_BY"));
				form1M.setUpdate_Date(rs.getDate("UPDATE_DATE"));
				form1M.setUpdate_by(rs.getString("UPDATE_BY"));
				
				//Form 3
				form1M.setMoveMentPemitNo(rs.getString("MOVEMENT_PEMIT_NO"));
				form1M.setExpiryDate(rs.getDate("EXPIRE_DATE"));
				form1M.setSecurityRefNo(rs.getString("SECURITY_REF_NO"));
				form1M.setSecurityAmt(rs.getDouble("SECURITY_AMT"));
				form1M.setReceiveAmt(rs.getDouble("RECEIVE_AMT"));
				form1M.setBillOfLading(rs.getString("BILL_OF_LADING"));
				form1M.setProperOffice(rs.getString("PROPER_OFFICE"));
				form1M.setRequestApproved(rs.getString("REQUEST_APPROVED"));
				form1M.setCertified(rs.getString("CERTIFIED"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			log.equals(e.getMessage());
		}finally{
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
		return form1M;
	}
	
	public Vector<FormDetail1Model> searchFormDetail1Model(String docId) throws SQLException{
		log.debug("[Start : searchFormModel ]");
		log.debug("docId = "+docId);
		FormDetail1Model formDetailM = new FormDetail1Model();
		Vector<FormDetail1Model> vc = new  Vector<FormDetail1Model>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = db.getConnection();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		StringBuffer sql = new StringBuffer();
		
		try {
						
			sql.append(" SELECT * FROM JDA_FORM_T_DOC_DETAIL1 ");
			sql.append("WHERE DOC_ID = ? ");
			log.debug("Search JDA_FORM_T_DOC >>> " + sql.toString());
			ps = conn.prepareStatement(sql.toString());
			int seq=1;
			
			ps.setString(seq++, docId);
			
			rs = ps.executeQuery();
			while(rs.next()){
				formDetailM = new FormDetail1Model();		
				formDetailM.setItem_no(rs.getString("ITEM_NO"));
				formDetailM.setDoc_id(rs.getString("DOC_ID"));
				formDetailM.setMarks_no(rs.getString("MARKS_NO"));
				formDetailM.setNo_type_package(rs.getString("NO_TYPE_PACKAGE"));
				formDetailM.setGood_desc(rs.getString("GOOD_DESC"));
				formDetailM.setCust_code(rs.getString("CUST_CODE"));
				formDetailM.setCust_unit(rs.getString("CUST_UNIT"));
				vc.add(formDetailM);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			log.equals(e.getMessage());
		}finally{
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
		return vc;
	}
	
	public Vector<FormDetail2Model> searchFormDetail2Model(String docId) throws SQLException{
		log.debug("[Start : searchFormModel ]");
		log.debug("docId = "+docId);
		FormDetail2Model formDetai2M = new FormDetail2Model();
		Vector<FormDetail2Model> vc = new  Vector<FormDetail2Model>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = db.getConnection();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		StringBuffer sql = new StringBuffer();
		
		try {
						
			sql.append(" SELECT * FROM JDA_FORM_T_DOC_DETAIL2 ");
			sql.append("WHERE DOC_ID = ? ");
			log.debug("Search JDA_FORM_T_DOC >>> " + sql.toString());
			ps = conn.prepareStatement(sql.toString());
			int seq=1;
			
			ps.setString(seq++, docId);
			
			rs = ps.executeQuery();
			while(rs.next()){
				formDetai2M = new FormDetail2Model();
				log.debug("ITEM_NO = "+rs.getString("ITEM_NO"));
				formDetai2M.setItem_no(rs.getString("ITEM_NO"));
				formDetai2M.setDoc_id(rs.getString("DOC_ID"));
				formDetai2M.setQty_cust_unit(rs.getDouble("QTY_CUST_UNIT"));
				formDetai2M.setUnit_val_actual(rs.getString("UNIT_VAL_ACTUAL"));
				formDetai2M.setUnit_val_custom(rs.getString("UNIT_VAL_CUSTOM"));
				formDetai2M.setTotal_value(rs.getDouble("TOTAL_VALUE"));
				formDetai2M.setExport_rate(rs.getDouble("EXPORT_RATE"));
				formDetai2M.setExport_amount(rs.getDouble("EXPORT_AMOUNT"));
				formDetai2M.setOther_tax_type(rs.getString("OTHER_TAX_TYPE"));
				formDetai2M.setOther_tax_rate(rs.getDouble("OTHER_TAX_RATE"));
				formDetai2M.setOther_tax_amount(rs.getDouble("OTHER_TAX_AMOUNT"));
				formDetai2M.setOriginCode(rs.getString("ORIGIN_CODE"));
				formDetai2M.setValuePerUnit(rs.getDouble("VALUE_PER_UNIT"));
				vc.add(formDetai2M);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			log.equals(e.getMessage());
		}finally{
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
		return vc;
	}
	
}

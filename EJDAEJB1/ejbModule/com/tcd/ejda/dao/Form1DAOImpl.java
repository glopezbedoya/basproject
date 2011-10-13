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
import com.tcd.ejda.model.RoleFunctionModel;
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
	public void saveFromEJDA(Form1Model form, Vector detail1, Vector detail2) throws SQLException {
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
		sql.append("OTHER_CHARG2, PAYABLE_AMOUNT, MANUALSCRIPT_RECERPT,VESSEL_VALUE, INSTRUCT_EXAM, RESULT_EXAM, FOR_OTHER_USE, CREATE_DATE, CREATE_BY, UPDATE_DATE, UPDATE_BY) ");
		sql.append("values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ");
		sql.append(", ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, ?, SYSDATE, ?)");
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
			ps.setString(parameterIndex++, form.getManifest_no());//MANIFEST_NO
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
				sql2.append("insert into JDA_FORM_T_DOC_DETAIL1(ITEM_NO, DOC_ID, QTY_CUST_UNIT, UNIT_VAL_ACTUAL, UNIT_VAL_CUSTOM, TOTAL_VALUE, ");
				sql2.append("EXPORT_RATE, EXPORT_AMOUNT, OTHER_TAX_TYPE, OTHER_TAX_RATE, OTHER_TAX_AMOUNT, CREATE_DATE, CREATE_BY, UPDATE_DATE, UPDATE_BY) ");
				sql2.append("values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, ?, SYSDATE, ?)");
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
}

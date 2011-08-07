package com.tcd.ejda.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.tcd.ejda.connection.JDBCConnection;
import com.tcd.ejda.model.RoleModel;
import com.tcd.ejda.model.TransactionLogModel;
import com.tcd.ejda.model.UsrModel;
import com.tcd.ejda.model.ValueListModel;

public class ValueListDAOImpl implements ValueListDAO {
	
	private Logger log = Logger.getLogger(ValueListDAOImpl.class);
	
	JDBCConnection db = new JDBCConnection();
	
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
			conn = db.getConnection();
			int beginIndex = (valueListM.getAtPage() - 1) * valueListM.getItemsPerPage();
			StringBuffer sqlBuffer = new StringBuffer();
			
			
			//if(!CheckPage.equals("customerRegistration")){
			sqlBuffer.append("SELECT c.* FROM (SELECT ROW_NUMBER() OVER (ORDER BY 0) AS CURSOR_INDEX, m.* FROM (");
			
			//}else{
			/*Jarinya Add 09/10/2009
			sqlBuffer.append(" SELECT distinct c.*, Case when REGISTER_STATUS = 'E' then 'Activate' ");
			sqlBuffer.append(" when REGISTER_STATUS = 'N' AND DELETED_DATE IS NULL then 'Disable' "); 
			sqlBuffer.append(" when REGISTER_STATUS = 'D' AND DELETED_DATE IS NOT NULL then 'Deleted' "); 
			sqlBuffer.append(" else REGISTER_STATUS_DESC End as REGISTER_STATUS_DESC, ");
			sqlBuffer.append(" ENG_DESC");
			sqlBuffer.append(" FROM P10_REGISTER_TYPE p10 join ");
			sqlBuffer.append("(SELECT ROW_NuserMBER() OVER (ORDER BY 0) AS CURSOR_INDEX, m.* FROM (");
		    End*/
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
				conn = db.getConnection();
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
		log.debug("returnModel = "+returnModel);
		if(returnModel.equalsIgnoreCase("TransactionLogModel")){
			obj = mapToTransactionLogModel(rs);
		}else if(returnModel.equalsIgnoreCase("RoleModel")){
			obj = mapToRoleModel(rs);
		}else if(returnModel.equalsIgnoreCase("UsrModel")){
			obj = mapToUsrModel(rs);
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
	
	
}

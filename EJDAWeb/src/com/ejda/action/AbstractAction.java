package com.ejda.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.ejda.sessionBean.TransactionLogBean;
import com.tcd.ejda.model.ValueListModel;

public abstract class AbstractAction {

	public abstract void init(); 
	public abstract boolean validate();
	public abstract boolean methodAction(String ejdaMethod) throws Exception;
	public abstract void clearSessionNotUsed();
	
	
	private HttpServletRequest request;

	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public ValueListModel getValueList(){
		ValueListModel valueListM = (ValueListModel) getRequest().getSession().getAttribute("VALUE_LIST");
		if(valueListM == null){
			valueListM = new ValueListModel();
		}
		return valueListM;
	}
	
	public void setValueList(ValueListModel valueListM){
		getRequest().getSession().setAttribute("VALUE_LIST", valueListM);
	}
	
	protected String getParameterDateStr(Date d) {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		if (d != null) {
			try {
				return df.format(d);
			} catch (Exception e) {
				return "";
			}
		} else {
			return "";
		}
	}
	
	protected Date parseParameterToDate(String str) {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		if (StringUtils.isNotEmpty(str)) {
			try {
				return df.parse(str);
			} catch (Exception e) {
				return null;
			}
		} else {
			return null;
		}
	}
	
	
	StringBuffer removeWasteSQL( StringBuffer dSqlBuffer ){
		//remove AND 
		if(dSqlBuffer.toString().trim().endsWith("AND")) 
			dSqlBuffer.delete(dSqlBuffer.toString().lastIndexOf("AND"), dSqlBuffer.toString().length());
			
		//remove WHERE 			
		if(dSqlBuffer.toString().trim().endsWith("WHERE")) 
			dSqlBuffer.delete(dSqlBuffer.toString().lastIndexOf("WHERE"), dSqlBuffer.toString().length());

		//remove Comma
		if(dSqlBuffer.toString().trim().endsWith(",")) 
			dSqlBuffer.delete(dSqlBuffer.toString().lastIndexOf(","), dSqlBuffer.toString().length());
		
		//remove OR
		if(dSqlBuffer.toString().trim().endsWith("OR")) 
			dSqlBuffer.delete(dSqlBuffer.toString().lastIndexOf("OR"), dSqlBuffer.toString().length());
				
		return dSqlBuffer;
	}
	
	
}

package com.ejda.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.log4j.Logger;

import com.ejda.action.EJDAM006Action;
import com.sun.xml.wss.impl.filter.TeeFilter;
public class DisplayFormatUtil {
	
	private Logger log = Logger.getLogger(DisplayFormatUtil.class);
	
	public static String DateFormat(String dt){
		SimpleDateFormat inFmt = new SimpleDateFormat("dd/MM/yyyy");

		SimpleDateFormat outFmt = new SimpleDateFormat("yyyy-MM-dd");

		java.util.Date rdt = null;
		try {
			rdt = inFmt.parse(dt);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String dateOut = outFmt.format(rdt);
		
		return dateOut;
	}
	public static String DateFormatYYYYMMDD(String dt){
		SimpleDateFormat inFmt = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat outFmt = new SimpleDateFormat("dd/MM/yyyy");
		
		java.util.Date rdt = null;
		try {
			rdt = inFmt.parse(dt);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String dateOut = outFmt.format(rdt);
		
		return dateOut;
	}
	public static String CurrentDate(){
		java.sql.Timestamp curDate = new java.sql.Timestamp(new java.util.Date().getTime());
		
		SimpleDateFormat outFmt = new SimpleDateFormat("yyyy-MM-dd");
		
		String dateOut = outFmt.format(curDate);
				
		return dateOut;
	}

	public static String SQLDateToString(java.sql.Date date, String format){
//		String returnDate = "";
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH);
		if(null==date)return null;
		return dateFormat.format(date);
	}
	
	public static String displaySelectPaging(String name,int allPage,int atPage,String script){
		StringBuffer selectTag = new StringBuffer();
		try{
			selectTag.append("<select name=\""+name+"\"  "+script+">");
			if(allPage == 1){
				selectTag.append("<option value=\"1\" selected>1</option>");
			}else{
				for(int i=1;i<=allPage;i++){
					if(i == atPage){
						selectTag.append("<option value=\""+i+"\" selected>"+i+"</option>");
					}else{
						selectTag.append("<option value=\""+i+"\" >"+i+"</option>");
					}
					
				}
			}
			selectTag.append("</select>");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return selectTag.toString();
	}
	
	public static String displayButton(String fieldName,String script,boolean disable){
		StringBuffer buttonStr = new StringBuffer();
		buttonStr.append("<input type=\"button\" name=\""+fieldName+"\" value=\""+fieldName+"\" "+script+" ");
		if(disable){
			buttonStr.append("disabled");
		}
		buttonStr.append("/>");
		return buttonStr.toString();
	}
	
	public static String displayInputTextBox(String fieldName,String value,String script){
		StringBuffer textBoxStr = new StringBuffer();
		if(null == value || "null".equalsIgnoreCase(value)){
			value = "";
		}
		textBoxStr.append("<input name=\""+fieldName+"\" value=\""+value+"\" type=\"text\" "+script+" />");
		return textBoxStr.toString();
	}
	
	public static String displayTextBoxCalendar(String fieldName,String value){
		StringBuffer textBoxStr = new StringBuffer();
		textBoxStr.append("<input type=\"text\" name=\""+fieldName+"\" id=\""+fieldName+"\" style=\"cursor:hand\" maxlength=\"10\" value=\""+value+"\" ");
		textBoxStr.append("onkeypress=\"return checkNumberDateFormat(this,event)\" onkeyup=\"addSlashFormat(event,this)\" onblur=\"checkDateLengthYear(this,'','1800','2300')\" />");
		textBoxStr.append(" <img id=\""+fieldName+"\" style=\"cursor:hand\" src=\"images/icon_calendar.gif\" alt=\"\"  ");
		textBoxStr.append("onclick=\"popUpCalendarModify(this,"+fieldName+",'dd/mm/yyyy','','','','bottom',false)\" >");
		return textBoxStr.toString();
	}
	
}

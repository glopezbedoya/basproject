package com.ejda.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.log4j.Logger;

import com.ejda.action.UserAction;
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
}

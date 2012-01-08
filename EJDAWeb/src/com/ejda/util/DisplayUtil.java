package com.ejda.util;

import java.util.Vector;

import org.apache.log4j.Logger;

import com.tcd.ejda.model.CacheDataM;
import com.tcd.ejda.model.FormDocAttachModel;

public class DisplayUtil {
	
	private static Logger log = Logger.getLogger(DisplayUtil.class);
	
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
			log.debug("selectTag.toString() >> " +selectTag.toString());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return selectTag.toString();
	}
	
	public static String displayButton(String name,String script,boolean disable){
		StringBuffer buttonStr = new StringBuffer();
		buttonStr.append("<input type=\"button\" name=\""+name+"\" value=\""+name+"\" "+script+" ");
		if(disable){
			buttonStr.append("disabled");
		}
		buttonStr.append("/>");
		return buttonStr.toString();
	}
	
	public static String displayInputTextBox(String name,String value,String script){
		StringBuffer textBoxStr = new StringBuffer();
		if(null == value || "null".equalsIgnoreCase(value)){
			value = "";
		}
		textBoxStr.append("<input name=\""+name+"\" value=\""+value+"\" type=\"text\" "+script+" />");
		return textBoxStr.toString();
	}
	
	public static String displayInputTextBox(String name,String value,String script,String size){
		StringBuffer textBoxStr = new StringBuffer();
		if(null == value || "null".equalsIgnoreCase(value)){
			value = "";
		}
		textBoxStr.append("<input name=\""+name+"\" value=\""+value+"\" size=\""+size+"\" type=\"text\" "+script+" />");
		return textBoxStr.toString();
	}
	
	public static String displayInputTextAreaTag(String name,String value,String script) {
		StringBuffer textAreaStr = new StringBuffer();
		if(null == value || "null".equalsIgnoreCase(value)){
			value = "";
		}
		textAreaStr.append("<textarea name=\""+name+"\" value=\""+value+"\" cols=\"45\" rows=\"5\" "+script+" ></textarea>");
		
		return textAreaStr.toString();
	}
	public static String displayCheckBox(
			String value,
			String name,
			String compare,
			String jScript) {
			String chk = "";
			if (compare.equals(value)) {
				chk = "checked";
			}
			return "<INPUT TYPE=\"checkbox\""
				+ chk
				+ "  NAME=\""
				+ name
				+ "\" value=\""
				+ compare
				+ "\""
				+ jScript
				+ ">";
		}
	
	public static String displayCheckBoxForDocAttach(
			Vector<FormDocAttachModel> valueVt,
			String name,
			String compare,
			String jScript) {
			String chk = "";
			String value;
			if(valueVt != null && valueVt.size() > 0){
				for(int i=0;i<valueVt.size();i++){
					value = valueVt.get(i).getDoc_name();
					if (compare.equals(value)) {
						chk = "checked";
						break;
					}
				}
			}
			return "<INPUT TYPE=\"checkbox\""
				+ chk
				+ "  NAME=\""
				+ name
				+ "\" value=\""
				+ compare
				+ "\""
				+ jScript
				+ ">";
		}
	
	public static String displayRadioTag(
			String compareValue,
			String inputFieldName,
			String value
			) {
			String returnStr = null;
			if (value == null) {
				value = "";
			}
			
				returnStr =
					"<INPUT "
						+ " type="
						
						+ "radio"
						
						+ " name="
						
						+ inputFieldName
						
						+ " value="
						
						+ value;
						
				if (value.equals(compareValue)) {
					returnStr = returnStr + " checked ";
				}
				returnStr = returnStr + ">";
				//DebugUtil.println("displayInputTag>> " + returnStr);
				return returnStr;
			
		}
	public static String displaySelectTag(Vector v, String selectedValue, String listName, String mode, String style) {
		
		CacheDataM obj = null;
		String value = "";
		String name = "";
	
	if (mode == null || !mode.equals("VIEW")) {
		String str = "<select name=\"" + listName + "\" class=\""+style+"\" ><option value=\"\">Please select</option>";
		if(v != null ){
			try{
			for (int i=0; i<v.size(); i++) {
				obj = (CacheDataM) v.get(i);
				value = displayHTML(obj.getCode()).trim();
				name = displayHTML(obj.getShortDesc()).trim();
//				log.debug("displaySelectTag value : " + value);
//				log.debug("displaySelectTag name : " + name);
				if (value != null && selectedValue != null &&  !selectedValue.equals("")  && value.trim().equals(selectedValue.trim()) ) {
					str = str + "<option value = \"" + value + "\" selected>" +value + " - " + name + "</option>";
				} else {
					str = str + "<option value = \"" + value + "\">" +value + " - " + name + "</option>";
				}
			}
			}catch(Exception e){
//				log.debug(e.getMessage());
				
			}
		}	
		return str + "</select>";
	} else {
		if ( selectedValue == null || selectedValue.equals("null") ||  selectedValue.equals("")  ){
			return "";
		}else{
			for (int i=0; i<v.size(); i++) {
				obj = (CacheDataM) v.get(i);
				if (obj.getCode() !=null && selectedValue!=null && obj.getCode().trim().equals(selectedValue.trim())){
					name = obj.getShortDesc();
					break;
				}
			}
			return name;
		}	
		
	}
}	
	
public static String displaySelectTag_Code(Vector v, String selectedValue, String listName, String mode, String style, String script) {
		
		CacheDataM obj = null;
		String value = "";
		String name = "";
	
	if (mode == null || !mode.equals("VIEW")) {
		String str = "<select name=\"" + listName + "\" class=\""+style+"\""  + script + " ><option value=\"\">Please select</option>";
		if(v != null ){
			try{
			for (int i=0; i<v.size(); i++) {
				obj = (CacheDataM) v.get(i);
				value = displayHTML(obj.getCode()).trim();
				//name = displayHTML(obj.getShortDesc()).trim();
//				log.debug("displaySelectTag value : " + value);
//				log.debug("displaySelectTag name : " + name);
				if (value != null && selectedValue != null &&  !selectedValue.equals("")  && value.trim().equals(selectedValue.trim()) ) {
					str = str + "<option value = \"" + value + "\" selected>" +value + "</option>";
				} else {
					str = str + "<option value = \"" + value + "\">" +value + "</option>";
				}
			}
			}catch(Exception e){
//				log.debug(e.getMessage());
				
			}
		}	
		return str + "</select>";
	} else {
		if ( selectedValue == null || selectedValue.equals("null") ||  selectedValue.equals("")  ){
			return "";
		}else{
			for (int i=0; i<v.size(); i++) {
				obj = (CacheDataM) v.get(i);
				if (obj.getCode() !=null && selectedValue!=null && obj.getCode().trim().equals(selectedValue.trim())){
					name = obj.getCode();
					break;
				}
			}
			return name;
		}	
		
	}
}
	public static String displayHTML(Object obj) {
		if(obj==null || ((String)obj).trim().equals("null")){
			return "";
		}
		else{
			String result = obj.toString();
			result = result.replaceAll("&", "&amp;");
			result = result.replaceAll("<", "&lt;");
			result = result.replaceAll(">", "&gt;");
			result = result.replaceAll("'", "&#39;");
			result = result.replaceAll("\"", "&quot;");
			
			return result;
			//return obj.toString().trim();
		}
	}
	

	public static String displayHTMLNoTrim(Object obj) {
		if(obj==null || ((String)obj).trim().equals("null")){
			return "";
		}
		else{
			String result = obj.toString();
			result = result.replaceAll("&", "&amp;");
			result = result.replaceAll("<", "&lt;");
			result = result.replaceAll(">", "&gt;");
			result = result.replaceAll("\'", "&#39;");
			result = result.replaceAll("\"", "&quot;");
		
			return result;
			//return obj.toString();}
		}
	}
}

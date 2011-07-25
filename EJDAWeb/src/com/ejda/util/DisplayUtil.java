package com.ejda.util;

import java.util.Vector;

import org.apache.log4j.Logger;

public class DisplayUtil {
	
	private Logger log = Logger.getLogger(DisplayUtil.class);
	
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
	
	public static String displayButton(String name,String script,boolean disable){
		StringBuffer buttonStr = new StringBuffer();
		buttonStr.append("<input type=\"button\" name=\""+name+"\" value=\""+name+"\" "+script+" ");
		if(disable){
			buttonStr.append("disabled");
		}
		buttonStr.append("/>");
		return buttonStr.toString();
	}
	
	public static String displayInputTextBox(String name,String script){
		StringBuffer textBoxStr = new StringBuffer();
		textBoxStr.append("<input name=\""+name+"\" type=\"text\" "+script+" />");
		return textBoxStr.toString();
	}
}

 /**
* 		(C) COPYRIGHT TISCO FINANCE PUBLIC COMPANY LIMITED
*
* 					CONFIDENTIAL INFORMATION
*	 				------------------------
*	 	THIS PROGRAM IS THE CONFIDENTIAL AND PROPRIETARY INFORMATION
*	 OF THE TISCO FINANCE PUBLIC COMPANY LIMITED. IT IS NOT TO BE
*	 COPIED, REPRODUCED, OR TRANSMITTED IN ANY FORM, BY ANY MEANS,
*	 IN WHOLE OR PART. NOR IS IT TO BE USED FOR ANY PURPOSE OTHER
*	 THAN THAT FOR WHICH IT IS EXPRESSLY PROVIDED WITHOUT WRITTEN
*	 PERMISSION OF THE TISCO FINANCE PUBLIC COMPANY LIMITED THEIR
*	 DESIGNEES.
* ==============================================================
* 
* 	PROJECT NAME 				: FI Clearing Component ( MGWEBINT1 )	
* 	CLASS NAME/PROGRAM NAME 	: $RCSfile: TiscoParameterCacheParam.java,v $ 
* 	DESCRIPTION 				: 
*
*
* ==============================================================
* 			V E R S I O N I N F O R M A T I O N
* ==============================================================
* 	VERSION 			DATE				NAME 						REFERENCE
* 	$Log: TiscoParameterCacheParam.java,v $
* 	Revision 1.1.2.1.6.2.4.3  2011/08/18 14:47:22  sarayut
* 	*** empty log message ***
* 	
* 	Revision 1.1.2.1.6.2.4.2  2011/08/09 03:26:06  sarayut
* 	*** empty log message ***
* 	
* 	Revision 1.1.2.1.6.2.4.1  2011/08/01 05:10:33  sarayut
* 	*** empty log message ***
* 	
* 	Revision 1.1.2.1.6.2  2009/11/16 09:05:46  ekkachai
* 	DocType add 00
* 	
* 	Revision 1.1.2.1.6.1  2009/09/19 05:49:10  ekkachai
* 	fixed cvs header
* 	
* 	Revision 1.1.2.1  2009/03/03 10:59:44  ekkachai
* 	Add Tisco standard Header
* 	
* 
* 	@author <a href="mailto:ekkachai@avalant.co.th">Ekkachai Nuangsapsaen</a>
* 	@version $Revision: 1.1.2.1.6.2.4.3 $ $Date: 2011/08/18 14:47:22 $
* ==============================================================
*/
package com.ejda.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.tcd.ejda.model.FormConfigModel;;


public class EjdaParameterCacheParam {
	
	private static Logger logger = Logger.getLogger(EjdaParameterCacheParam.class);
	private static Properties prop;
	private static Hashtable docTypeMap;
	
	public synchronized static void load(String fileName){
		prop = new Properties();
		try{
			File f = new File(fileName);
			FileInputStream fis = new FileInputStream(f);
			prop.load(fis);
			fis.close();
			logger.debug("###################### load "+fileName+" completed #####################");
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}
	
	public static String getValue(String key){
		String value = "";
		try{
			if(prop != null){
				value = prop.getProperty(key);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return value;
	}
	public static Vector getForm1Vector(){
		Vector vectorForm1 = new Vector();
		int totals = Integer.parseInt(EjdaParameterCacheParam.getValue("FORM1_INPUT_TOTAL"));
		FormConfigModel obj = null;
		logger.debug("========== getForm1Vector ============ ");
		for(int i=0; i<totals; i++){
			String lvField[];
			String form1Index = "0"+i;
			String inputField = EjdaParameterCacheParam.getValue("FORM1_INPUT_FIELD_"+form1Index);
			lvField = inputField.split(":");
			logger.debug("-- lvField = " + lvField[0] + " - " + lvField[1]);
			obj = new FormConfigModel();
			obj.setName_field(lvField[1]);
			obj.setInput_field(lvField[0]);
			obj.setPermissions("");
			vectorForm1.add(obj);
		}
		return vectorForm1;
	}
}

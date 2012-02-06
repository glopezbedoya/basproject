package com.ejda.action;

import java.util.Map;

import javax.servlet.ServletOutputStream;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;

import com.ejda.sessionBean.ReportBean;
import com.ejda.util.EjdaParameterCacheParam;

public class EJDAM004Action extends AbstractAction {

	private Logger log = Logger.getLogger(EJDAM006Action.class);
	
	public ReportBean getReportBean() {
		ReportBean reportBean = (ReportBean)getRequest().getSession().getAttribute("reportBean");
		if (null == reportBean){
			reportBean = new ReportBean();
		}
		return reportBean;
	}

	public void setReportBean(ReportBean reportBean) {
		getRequest().getSession().setAttribute("reportBean", reportBean);
	}
	
	@Override
	public void clearSessionNotUsed() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		ReportBean reportBean = new ReportBean();
		reportBean.setActionName("EJDAM004");
		setReportBean(reportBean);
		log.debug("getReportBean().getActionName() = "+getReportBean().getActionName());
	}

	@Override
	public boolean methodAction(String ejdaMethod) throws Exception {
		boolean result = false;
		if(ejdaMethod.equalsIgnoreCase("doExportExcel")){
			doExportExcel();
			result = true;
		}
		return result;
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void selectReportType(){
		
	}
	
	public void doExportExcel(){
		try{
			// export excel with POI
			HSSFWorkbook wb = new HSSFWorkbook();   
			HSSFSheet sheet = wb.createSheet();   
			HSSFRow row;

			HSSFCellStyle style = wb.createCellStyle();		
		    HSSFCell cell;
		    HSSFDataFormat format = wb.createDataFormat();
		    
		    
		    //get cell style from configure.
			Map<String, HSSFCellStyle> styles = createStyles(wb);
			short line = 0;
			
			// first line
			row = sheet.createRow(line);
			setExcelStyle(styles.get("header_topic"), row, (short)0, EjdaParameterCacheParam.getValue("goverment"));
			// merge cell
			sheet.addMergedRegion(new Region((short)0, (short)0, (short)0, (short)11));
			
			// second line
			row = sheet.createRow(++line);
			setExcelStyle(styles.get("header_topic"), row, (short)0, EjdaParameterCacheParam.getValue("reportStat"));
			// merge cell
			sheet.addMergedRegion(new Region((short)1, (short)0, (short)1, (short)11));
			
			// Third line
			row = sheet.createRow(++line);
			setExcelStyle(styles.get("header_topic"), row, (short)0, EjdaParameterCacheParam.getValue("ofMonth"));
			// merge cell
			sheet.addMergedRegion(new Region((short)2, (short)0, (short)2, (short)11));
			
			
			row = sheet.createRow(++line);
			setExcelStyle(styles.get("header_default_right"), row, (short)10, EjdaParameterCacheParam.getValue("all")+" 1 "+EjdaParameterCacheParam.getValue("count"));
			sheet.addMergedRegion(new Region((short)3, (short)10, (short)3, (short)11));
			
			// empty line
//			row = sheet.createRow(++line);
			
			//Header
			row = sheet.createRow(++line);
			setExcelStyle(styles.get("header"), row, (short)0, EjdaParameterCacheParam.getValue("no"));
			setExcelStyle(styles.get("header"), row, (short)1, EjdaParameterCacheParam.getValue("listReport"));
			setExcelStyle(styles.get("header"), row, (short)2, EjdaParameterCacheParam.getValue("qty_each_one"));
			setExcelStyle(styles.get("header"), row, (short)3, EjdaParameterCacheParam.getValue("qty_box"));
			setExcelStyle(styles.get("header"), row, (short)4, EjdaParameterCacheParam.getValue("weight"));
			setExcelStyle(styles.get("header"), row, (short)5, EjdaParameterCacheParam.getValue("price"));
			setExcelStyle(styles.get("header"), row, (short)6, EjdaParameterCacheParam.getValue("garuntee"));
			setExcelStyle(styles.get("header"), row, (short)7, EjdaParameterCacheParam.getValue("arkorn_stamp"));
			setExcelStyle(styles.get("header"), row, (short)8, EjdaParameterCacheParam.getValue("vat"));
			setExcelStyle(styles.get("header"), row, (short)9, EjdaParameterCacheParam.getValue("vat_sappasamit"));
			setExcelStyle(styles.get("header"), row, (short)10, EjdaParameterCacheParam.getValue("vat_mahardthai"));
			setExcelStyle(styles.get("header"), row, (short)11, EjdaParameterCacheParam.getValue("commission"));
			
			//Add Data
			int no =0;
			for(int i=0; i<3; i++){
				no++;
				row = sheet.createRow(++line);
				
				setExcelStyle(styles.get("cell_default"), row, (short)0,  "01");
				setExcelStyle(styles.get("cell_default"), row, (short)1,  "02");
				setExcelStyle(styles.get("cell_default"), row, (short)2,  "03");
				setExcelStyle(styles.get("cell_default"), row, (short)3,  "04");
				setExcelStyle(styles.get("cell_default"), row, (short)4,  "05");
				setExcelStyle(styles.get("cell_default"), row, (short)5,  "06");
				setExcelStyle(styles.get("cell_default"), row, (short)6,  "07");
				setExcelStyle(styles.get("cell_default"), row, (short)7,  "08");
				setExcelStyle(styles.get("cell_default"), row, (short)8,  "09");
				setExcelStyle(styles.get("cell_default"), row, (short)9,  "10");
				setExcelStyle(styles.get("cell_default"), row, (short)10,  "11");
				setExcelStyle(styles.get("cell_default"), row, (short)11,  "12");
				
			}
			
			//Footer
			row = sheet.createRow(++line);
			setExcelStyle(styles.get("header"), row, (short)1, EjdaParameterCacheParam.getValue("total"));
			setExcelStyle(styles.get("cell_default"), row, (short)2, "1");
			setExcelStyle(styles.get("cell_default"), row, (short)3, "2");
			setExcelStyle(styles.get("cell_default"), row, (short)4, "3");
			setExcelStyle(styles.get("cell_default"), row, (short)5, "4");
			setExcelStyle(styles.get("cell_default"), row, (short)6, "5");
			setExcelStyle(styles.get("cell_default"), row, (short)7, "6");
		
			sheet.setColumnWidth(0, 2000);
			sheet.setColumnWidth(1, 3500);
			sheet.setColumnWidth(2, 5300);
			sheet.setColumnWidth(3, 3500);
			sheet.setColumnWidth(4, 3200);
			sheet.setColumnWidth(5, 3000);
			sheet.setColumnWidth(6, 4500);
			sheet.setColumnWidth(7, 3000);
			sheet.setColumnWidth(8, 4500);
			sheet.setColumnWidth(9, 4800);
			sheet.setColumnWidth(10, 5200);
			sheet.setColumnWidth(11, 5200);
//			sheet.autoSizeColumn((short)0);
//			sheet.autoSizeColumn((short)1);
//			sheet.autoSizeColumn((short)2);
//			sheet.autoSizeColumn((short)3);
//			sheet.autoSizeColumn((short)4);
//			sheet.autoSizeColumn((short)5);
//			sheet.autoSizeColumn((short)6);
//			sheet.autoSizeColumn((short)7);
//			sheet.autoSizeColumn((short)8);
//			sheet.autoSizeColumn((short)9);
//			sheet.autoSizeColumn((short)10);
//			sheet.autoSizeColumn((short)11);

 
			log.debug("getResponse() = "+getResponse());
			getResponse().setContentType("application/vnd.ms-excel");   
			getResponse().setHeader("Content-disposition",  "attachment;filename=ReportJDA_All.xls");   
            ServletOutputStream out = getResponse().getOutputStream();   
            wb.write(out);   
            out.flush();   
            out.close();   
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}

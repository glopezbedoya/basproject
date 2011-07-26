package com.ejda.action;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.tcd.ejda.dao.ValueListDAO;
import com.tcd.ejda.dao.ValueListDAOImpl;
import com.tcd.ejda.model.ValueListModel;

public class ValueListAction extends AbstractAction {
	
	private Logger log = Logger.getLogger(ValueListAction.class);
	
	@Override
	public void clearSessionNotUsed() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}
	

	@Override
	public boolean methodAction(String ejdaMethod) {
		// TODO Auto-generated method stub
		if(ejdaMethod.equalsIgnoreCase("doSearch")){
			return doSearch();
		}
		return false;
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean doSearch(){
		boolean result = false;
		log.debug("[ preModelRequest ]  : START call valueList");
		log.debug("[ preModelRequest ]  : getRequest().getParameter(\"atPage\") = "+getRequest().getParameter("atPage"));
		log.debug("[ preModelRequest ]  : getRequest().getParameter(\"itemsPerPage\") = "+getRequest().getParameter("itemsPerPage"));
		try {
			ValueListModel valueListM = (ValueListModel) getRequest().getSession().getAttribute("VALUE_LIST");
			if (valueListM == null) {
				valueListM = new ValueListModel();
			}
			checkItemPerPage(valueListM);
			
			String orderBy = getRequest().getParameter("orderBy");
			if(null != orderBy){
				valueListM.setOrderBy(orderBy);
			}
			
			String orderScheme = getRequest().getParameter("orderScheme");
			if(null != orderScheme){
				valueListM.setOrderScheme(orderScheme);
			}
			
			ValueListDAO valueListDAO = new ValueListDAOImpl();			
			valueListM = valueListDAO.getResult(valueListM);
			getRequest().getSession().setAttribute("VALUE_LIST", valueListM);
			result = true;
		} catch (Exception e) {
			log.error("Error: " + e);
			return false;
		}
		return result;
	}
	
	public ValueListModel doSearch(ValueListModel valueListM){
		log.debug("*********** ValueListModel doSearch ***********");
		log.debug("[ preModelRequest ]  : START call valueList");
//		log.debug("[ preModelRequest ]  : getRequest().getParameter(\"atPage\") = "+getRequest().getParameter("atPage"));
//		log.debug("[ preModelRequest ]  : getRequest().getParameter(\"itemsPerPage\") = "+getRequest().getParameter("itemsPerPage"));
		try {
			if (valueListM == null) {
				valueListM = new ValueListModel();
			}
			checkItemPerPage(valueListM);
//			String orderBy = getRequest().getParameter("orderBy");
//			if(null != orderBy){
//				valueListM.setOrderBy(orderBy);
//			}
//			String orderScheme = getRequest().getParameter("orderScheme");
//			if(null != orderScheme){
//				valueListM.setOrderScheme(orderScheme);
//			}
			ValueListDAO valueListDAO = new ValueListDAOImpl();
			valueListM = valueListDAO.getResult(valueListM);
			
		} catch (Exception e) {
			log.error("Error: " + e);
		}
		return valueListM;
	}
	
	private void checkItemPerPage(ValueListModel valueListM){
		int itemsPerPage = valueListM.getItemsPerPage();
		String atPageStr = valueListM.getPage();
		log.debug("atPage = "+valueListM.getPage());
		log.debug("valueListM.getAtPage() = "+valueListM.getAtPage());
		int atPage = 1;
//		int itemsPerPageStr = valueListM.getItemsPerPage();
		if (valueListM.getAtPage() > 1)
			atPage = valueListM.getAtPage();
		if (atPageStr != null && !atPageStr.equals("")) 
			atPage = Integer.parseInt(atPageStr);		
//		if (itemsPerPageStr != null && !itemsPerPageStr.equals("")) {
//			int itemsPerPageTemp = Integer.parseInt(itemsPerPageStr);
//			if (itemsPerPage != itemsPerPageTemp) {
//				itemsPerPage = itemsPerPageTemp;
//				atPage = 1;
//			}
//		}
		valueListM.setAtPage(atPage);
		valueListM.setItemsPerPage(itemsPerPage);
		log.debug("[ checkItemPerPage ]  : valueListM.getAtPage() = "+valueListM.getAtPage());
		log.debug("[ checkItemPerPage ]  : valueListM.getItemsPerPage = "+valueListM.getItemsPerPage());
	}

}

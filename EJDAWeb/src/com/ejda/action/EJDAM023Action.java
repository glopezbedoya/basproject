package com.ejda.action;

import java.sql.SQLException;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.ejda.sessionBean.FormConfigBean;
import com.ejda.util.EjdaParameterCacheParam;
import com.tcd.ejda.dao.FormConfigDAO;
import com.tcd.ejda.dao.FormConfigDAOImpl;
import com.tcd.ejda.model.FormConfigModel;
import com.tcd.ejda.model.FormDocAttachModel;

public class EJDAM023Action extends AbstractAction {

	private Logger log = Logger.getLogger(EJDAM023Action.class);
	private FormConfigBean formConfigBean;
	@Override
	public void clearSessionNotUsed() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		log.debug("*********** init ***********");
		formConfigBean = getFormConfigBean();
		formConfigBean.setAction("0");
		formConfigBean.setFormConfigVt(new Vector<FormConfigModel>());
		formConfigBean.setFormPermissionVt(new Vector<FormConfigModel>());
		formConfigBean.setFormConfigModelSP(new FormConfigModel());
		setFormConfigBean(formConfigBean);
	}

	@Override
	public boolean methodAction(String ejdaMethod) {
		// TODO Auto-generated method stub
		if(ejdaMethod.equalsIgnoreCase("doSubmitButton")){
			return doSubmitButton();
		}else if(ejdaMethod.equalsIgnoreCase("doAdd")){
			return doAdd();
		}else if(ejdaMethod.equalsIgnoreCase("doCancel")){
			return doCancel();
		}
		
		return false;
	}

	private boolean doCancel() {
		boolean blSuccess = false;
		
		formConfigBean = getFormConfigBean();
		formConfigBean.setAction("0");
		setFormConfigBean(formConfigBean);
		
		blSuccess = true;
		return blSuccess;
	}

	private boolean doAdd() {

		boolean blSuccess = false;
		Vector vc = new Vector();
		String inputField = EjdaParameterCacheParam.getValue("FORM1_INPUT_FIELD_00");
		String labelField = EjdaParameterCacheParam.getValue("CONSIGNOR_CODE");
		log.debug("---------------- inputField ------------- " + inputField);
		log.debug("---------------- labelField ------------- " + labelField);
		
		String form_type = getRequest().getParameter("DdlAddForm");
		log.debug("[------------ EJDAM023 Action ----------- ] " + form_type);
		formConfigBean = getFormConfigBean();
		if (form_type.length()>0){
			log.debug("form_type --- " + form_type);
			formConfigBean.setAction(form_type);
			FormConfigDAO dao = new FormConfigDAOImpl();
			vc = EjdaParameterCacheParam.getForm1Vector();
			formConfigBean.setFormConfigVt(vc);
			try {
				formConfigBean.setFormPermissionVt(dao.searchFormConfigModel(form_type));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			formConfigBean.setAction("0");
		}
		setFormConfigBean(formConfigBean);
		blSuccess = true;
		return blSuccess;
	}

	private boolean doSubmitButton() {
		log.debug("[ ------ doSubmitButton ----]");
		boolean blSuccess=false;
		Vector vc = new Vector();
		formConfigBean = getFormConfigBean();
		
		String form_type = getRequest().getParameter("jda_type");
		
		vc = setValueFormConfig(form_type);
		FormConfigDAO dao = new FormConfigDAOImpl();
		try {
			if (dao.insertFormConfig(vc,form_type)){
				formConfigBean.setAction("0");
				blSuccess = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return blSuccess;
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	public FormConfigBean getFormConfigBean() {
		FormConfigBean formConfigBean = (FormConfigBean)getRequest().getSession().getAttribute("formConfigBean");
		if (null == formConfigBean){
			formConfigBean = new FormConfigBean();
		}
		return formConfigBean;
	}

	public void setFormConfigBean(FormConfigBean formConfigBean) {
		getRequest().getSession().setAttribute("formConfigBean", formConfigBean);
	}
	
	public Vector setValueFormConfig(String doc_jda_type){
		Vector vc = new Vector();
		
		String iuser = (String) getRequest().getSession().getAttribute("iuser");
		if (null==iuser || "".equals(iuser)){
			iuser = "system";
		}
		String [] permissions = getRequest().getParameterValues("PERMISSIONS");
//		String [] inputField = getRequest().getParameterValues("input_field");
		
		if (null != permissions && permissions.length > 0){
			for(int i =0; i < permissions.length;i++){
				FormConfigModel cm = new FormConfigModel();
				cm.setJda_type(doc_jda_type);
				cm.setUser_name(iuser);
				cm.setInput_field(permissions[i]);
				//cm.setPermissions(permissions[i]);
				vc.add(cm);
			}
			
		}
		
		return vc;
	}
}

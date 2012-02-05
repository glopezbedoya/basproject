<%@page contentType="text/html;charset=UTF-8"%>
<%@page import="com.tcd.ejda.model.Form1Model"%>
<%@page import="com.tcd.ejda.model.FormDetail1Model"%>
<%@page import="com.tcd.ejda.model.FormDetail2Model"%>
<%@page import="com.ejda.sessionBean.Form1Bean"%>
<%@page import="com.ejda.util.DisplayFormatUtil"%>
<%@page import="com.ejda.util.LoadCacheData"%>
<%@page import="com.ejda.util.DisplayUtil"%>
<%@page import="com.tcd.ejda.model.ValueListModel"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="java.util.Vector"%>
<script language="javascript" src="js/ejdaform1.js"></script>
<script language="javascript" src="js/ejdaScript.js"></script>
<form name="ejdaformNo1" method="post" action="/EJDAWeb/EJDAControler">
<%
	Logger log = Logger.getLogger("JspLog");
	Form1Bean form1Bean = (Form1Bean)request.getSession().getAttribute("form1Bean");
	log.debug("form1Bean :: " + form1Bean);
	Form1Model form1ModelSP = form1Bean.getForm1ModelSP();
	FormDetail1Model detail1ModelSP = form1Bean.getDetail1ModelSP();
	FormDetail2Model detail2ModelSP = form1Bean.getDetail2ModelSP();
	Vector form1Vt = form1Bean.getForm1Vt();
	Form1Model form1M = new Form1Model();
	String form_action = (String)form1Bean.getActionName();
	log.debug("form_action = " +form_action);
	String form_no = (String)request.getSession().getAttribute("form_no");
	String showName = "";
	
	if (null!=form_action && "EJDAM010".equals(form_action)){
		showName ="Table 1 : Form no. 1";
	
	}else if (null!=form_action && "EJDAM014".equals(form_action)){
		showName ="Table 2 : Form no. 1";
	}else if (null!=form_action && "EJDAM018".equals(form_action)){
		showName ="Table 3 : Form no. 1";
	}else if (null!=form_action && "EJDAM022".equals(form_action)){
		showName ="Table 4 : Form no. 1";
	}
	String bgColor1 = "bordercolor=\"#F4F4F4\"";
	String bgColor2 = "bgcolor=\"#DFEFFF\"";
	ValueListModel valueListM = form1Bean.getValueListM();
	
	if(null == valueListM) valueListM = new ValueListModel();
	
	log.debug("valueListM.getAtPage() >> " + valueListM.getAtPage());
	log.debug("valueListM.getItemsPerPage() >> " + valueListM.getItemsPerPage());
	%>
	<input type="hidden" name="ejdaAction" value=""> 
	<input type="hidden" name="ejdaMethod" value=""> 
	<input type="hidden" name="screenName" value="">
	<input type="hidden" name="screenName" value="">
	<input type="hidden" name="actionName" value="">
	<input type="hidden" name="form_no" value="1">
	<input type="hidden" name="doc_id" value="<%=form1ModelSP.getDoc_ID() %>">
	<input type="hidden" name="page" value="<%=valueListM.getAtPage() %>" />
	<input type="hidden" name="volumePerPage" value="<%=valueListM.getItemsPerPage() %>" />
	

<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td align="center"><table width="800" border="0" cellspacing="1" cellpadding="1" bgcolor="#F8F8F8">
      <tr>
        <td width="50%" colspan="2" align="center"><table border="0" cellspacing="1" cellpadding="1">
           <tr>
            <td colspan="2" align="left" class="textDesc" >>> <%=showName %></td>
          </tr>
          <tr>
            <td colspan="2" align="left" class="textDesc" ><%=form_no %> : <%=form_action %></td>
          </tr>
          <tr>
            <td colspan="2" align="left" ><font class="textDescBold">1.Consignor/Exporter(Name and address)</font></td>
           </tr>
           <tr>
            <td colspan="2" align="left" height="20"></td>
           </tr>
          <tr>
            <td align="right"><font class="textDesc">Exporter/Taxpayer Code </font></td>
            <td align="left"><%=DisplayUtil.displayInputTextBox("consignorExportCode",form1ModelSP.getConsignor_code(),DisplayUtil.disabledField(form1Bean.getFormConfigVt(),"CONSIGNOR_CODE") + " maxlength=20 onkeypress=\"keyPressInteger()\"") %>
            
            </td>
          </tr>
          <tr>
            <td align="right"><font class="textDesc">Name </font></td>
            <td align="left"><%=DisplayUtil.displayInputTextBox("consignorExportName",form1ModelSP.getConsignor_name(),DisplayUtil.disabledField(form1Bean.getFormConfigVt(),"CONSIGNOR_NAME") + " maxlength=20") %></td>
          </tr>
          <tr>
            <td align="right"><font class="textDesc">Address </font></td>
            <td align="left"><%=DisplayUtil.displayInputTextAreaTag("consignorExportAddress",form1ModelSP.getConsignor_address(),DisplayUtil.disabledField(form1Bean.getFormConfigVt(),"CONSIGNOR_ADDRESS")+" maxlength=255") %></td>
          </tr>
        </table></td>
        <td rowspan="2" align="center"><table width="466" border="0" cellpadding="0" cellspacing="1">
          <tr>
            <td colspan="3" align="center"><font class="textDescBold">FOR OFFICIAL   USE</font></td>
            </tr>
          <tr>
            <td colspan="2" align="left">&nbsp;</td>
            <td align="left">&nbsp;</td>
          </tr>
          <tr>
            <td colspan="2" align="left"><font class="textDescBold">10.date and Time of   Receipt</font></td>
            <td align="left"><font class="textDescBold">11.Documents   Attached</font></td>
          </tr>
          <tr>
            <td colspan="2" align="left"><%=DisplayUtil.displayInputTextBox("Date_Receipt",DisplayFormatUtil.SQLDateToString1(form1ModelSP.getDate_Receipt(),"DD/MM/YYYY"),DisplayUtil.disabledField(form1Bean.getFormConfigVt(),"DATE_RECEIPT")+"maxlength=10 onkeypress=\"addSlashFormat(event,this);keyPressInteger();\" onblur=\"checkDateLengthYear(this,'','1800','2300')\"")%>
             
           </td>
            <td align="left"><font class="textDesc"><%=DisplayUtil.displayCheckBoxForDocAttach(form1Bean.docAttachMVt,"doc_attach","INVOICE","") %> 
              Invoice</font></td>
          </tr>
          <tr>
            <td colspan="2">&nbsp;</td>
            <td align="left"><font class="textDesc">
              <%=DisplayUtil.displayCheckBoxForDocAttach(form1Bean.docAttachMVt,"doc_attach","BILL OF LADING","") %>
              Bill of Lading
            </font></td>
          </tr>
          <tr>
            <td colspan="2" align="left"><font class="textDescBold">12.Registration   Number</font></td>
            <td align="left"><font class="textDesc">
             <%=DisplayUtil.displayCheckBoxForDocAttach(form1Bean.docAttachMVt,"doc_attach","INSURANCE CERT","") %>
              Insurance Certificate
            </font></td>
          </tr>
          <tr>
            <td colspan="2" align="left"><%=DisplayUtil.displayInputTextBox("Regis_no",form1ModelSP.getRegis_no(),DisplayUtil.disabledField(form1Bean.getFormConfigVt(),"REGIS_NO")+"maxlength=50") %></td>
            <td align="left"><font class="textDesc">
              <%=DisplayUtil.displayCheckBoxForDocAttach(form1Bean.docAttachMVt,"doc_attach","LETTER OF CREDIT","") %>
              Letter of Credit
            </font></td>
          </tr>
          <tr>
            <td colspan="2">&nbsp;</td>
            <td align="left"><font class="textDesc">
             	<%=DisplayUtil.displayCheckBoxForDocAttach(form1Bean.docAttachMVt,"doc_attach","OTHER","") %>
              Other
            </font></td>
          </tr>
          <tr>
            <td colspan="2" align="left"><font class="textDescBold">13.Name of Customs   Office</font></td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td align="left"><font class="textDesc">Code
              </font></td>
            <td align="left"><font class="textDesc"><%=DisplayUtil.displayInputTextBox("cus_name_code",form1ModelSP.getCus_name_code(),DisplayUtil.disabledField(form1Bean.getFormConfigVt(),"CUS_NAME_CODE")+"maxlength=4 onkeypress=\"keyPressInteger()\"") %>
             
            </font></td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td align="left"><font class="textDesc">Description 
              </font></td>
            <td align="left"><font class="textDesc"><%=DisplayUtil.displayInputTextBox("cus_name_desc",form1ModelSP.getCus_name_desc(),DisplayUtil.disabledField(form1Bean.getFormConfigVt(),"CUS_NAME_DESC")+"maxlength=50") %>
              
            </font></td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td colspan="2">&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td colspan="2" align="left"><font class="textDescBold">14.Manifest   Registration No.</font></td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td colspan="2" align="left"><%=DisplayUtil.displayInputTextBox("ManifestNo",(form1ModelSP.getManifest_no() != null)?form1ModelSP.getManifest_no().toString():"",DisplayUtil.disabledField(form1Bean.getFormConfigVt(),"MANIFEST_NO")+"maxlength=4 onkeypress=\"keyPressInteger()\"") %></td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td colspan="2">&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td colspan="3" align="left"><font class="textDescBold">15. Receipt of Duty/Tax as Levied Authorized by :</font></td>
            </tr>
          <tr>
            <td align="center" colspan="2"><%=DisplayUtil.displayInputTextBox("duty_tax_receipt_date",DisplayFormatUtil.SQLDateToString1(form1ModelSP.getDuty_tax_receipt_date(),"DD/MM/YYYY"),DisplayUtil.disabledField(form1Bean.getFormConfigVt(),"DUTY_TAX_RECEIPT_DATE")+"maxlength=10 onkeypress=\"addSlashFormat(event,this);keyPressInteger();\" onblur=\"checkDateLengthYear(this,'','1800','2300')\"") %></td>
            <td align="center"><%=DisplayUtil.displayInputTextBox("duty_tax_receipt_desc",form1ModelSP.getDuty_tax_receipt_desc(),"maxlength=50") %></td>
          </tr>
          <tr>
            <td colspan="2" align="center"><font class="textDesc">Date</font></td>
            <td align="center"><font class="textDesc">Proper Officer of Customs</font></td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td colspan="2" align="center"><table border="0" cellspacing="1" cellpadding="1">
          <tr>
            <td colspan="2" align="left" >&nbsp;</td>
          </tr>
          <tr>
            <td colspan="2" align="left" width="400" ><font class="textDescBold">2.Consignee/Importer(Name and address)</font></td>
            </tr>
          <tr>
            <td colspan="2" align="left" height="20"></td>
            </tr>
          <tr>
            <td align="right"><font class="textDesc">Importer/Taxpayer Code </font></td>
            <td align="left">
            <%=DisplayUtil.displaySelectTag_Code(LoadCacheData.GetImporterCache(),form1ModelSP.getConsignee_code(),"Consignee_code","EDIT","", DisplayUtil.disabledField(form1Bean.getFormConfigVt(),"CONSIGNEE_CODE")+"onchange=\"getImportAddress(this.value)\"" ) %></td>
            </tr>
          <tr>
            <td align="right"><font class="textDesc">Name </font></td>
            <td align="left"><%=DisplayUtil.displayInputTextBox("Consignee_name",form1ModelSP.getConsignee_name(),DisplayUtil.disabledField(form1Bean.getFormConfigVt(),"CONSIGNEE_NAME")+"maxlength=20") %></td>
            </tr>
          <tr>
            <td align="right"><font class="textDesc">Address </font></td>
            <td align="left"><%=DisplayUtil.displayInputTextAreaTag("Consignee_address",form1ModelSP.getConsignee_address(),DisplayUtil.disabledField(form1Bean.getFormConfigVt(),"CONSIGNEE_ADDRESS")+"maxlength=255") %></td>
            </tr>
          </table></td>
      </tr>
      <tr>
        <td colspan="2" align="center" ><table border="0" cellspacing="1" cellpadding="1">
          <tr>
            <td colspan="2" align="left" >&nbsp;</td>
          </tr>
          <tr>
            <td colspan="2" align="left" width="400" ><font class="textDescBold">3.Name and Address of Authorized Agent</font></td>
          </tr>
          <tr>
            <td colspan="2" align="left" height="20"></td>
          </tr>
          <tr>
            <td align="right"><font class="textDesc">Agent/Taxpayer Code</font></td>
            <td align="left">
            <%=DisplayUtil.displaySelectTag_Code(LoadCacheData.GetAgentCache(),form1ModelSP.getAuthorAgent_code(),"AuthorAgent_code","EDIT","", DisplayUtil.disabledField(form1Bean.getFormConfigVt(),"AUTHORAGENT_CODE")+"onchange=getAgentAddress(this.value)") %></td>
          </tr>
          <tr>
            <td align="right"><font class="textDesc">Name </font></td>
            <td align="left"><%=DisplayUtil.displayInputTextBox("AuthorAgent_name",form1ModelSP.getAuthorAgent_name(),DisplayUtil.disabledField(form1Bean.getFormConfigVt(),"AUTHORAGENT_NAME")+"maxlength=20") %></td>
          </tr>
          <tr>
            <td align="right"><font class="textDesc">Address </font></td>
            <td align="left"><%=DisplayUtil.displayInputTextAreaTag("AuthorAgent_address",form1ModelSP.getAuthorAgent_address(),DisplayUtil.disabledField(form1Bean.getFormConfigVt(),"AUTHORAGENT_ADDRESS")+"maxlength=25") %></td>
          </tr>
        </table></td>
        <td rowspan="4" align="center"><table width="472" border="0" cellpadding="1" cellspacing="1">
          <tr>
            <td width="170" align="left"><font class="textDescBold">16.Import permit   No.</font></td>
            <td colspan="2" align="left"><font class="textDescBold">17. Exchange Control Ref.</font></td>
            </tr>
          <tr> 
            <td align="left"><%=DisplayUtil.displayInputTextBox("import_permit_no",form1ModelSP.getImport_permit_no(),DisplayUtil.disabledField(form1Bean.getFormConfigVt(),"IMPORT_PERMIT_NO")+"maxlength=20") %></td>
            <td align="left" colspan="2"><%=DisplayUtil.displayInputTextBox("exchg_ctrl_ref",form1ModelSP.getExchg_ctrl_ref(),DisplayUtil.disabledField(form1Bean.getFormConfigVt(),"EXCHG_CTRL_REF")+"maxlength=20") %></td>
            </tr>
          <tr>
            <td>&nbsp;</td>
            <td colspan="2">&nbsp;</td>
            </tr>
          <tr>
            <td align="left" ><font class="textDescBold">18. Special Treatment</font></td>
            <td colspan="2" align="left" ><font class="textDescBold">19. Bill of Lading or comsignment Note No.</font></td>
            </tr>
          <tr>
            <td align="left" ><%=DisplayUtil.displayInputTextBox("special_treatment",form1ModelSP.getSpecial_treatment(),DisplayUtil.disabledField(form1Bean.getFormConfigVt(),"SPECIAL_TREATMENT")+"maxlength=20") %></td>
            <td colspan="2" align="left" ><%=DisplayUtil.displayInputTextBox("bill_no",form1ModelSP.getBill_no(),DisplayUtil.disabledField(form1Bean.getFormConfigVt(),"BILL_NO")+"maxlength=20") %></td>
            </tr>
          <tr>
            <td>&nbsp;</td>
            <td colspan="2">&nbsp;</td>
            </tr>
          <tr>
            <td align="left" ><font class="textDescBold">20. Terms of Delivery and Payment</font></td>
            <td colspan="2" align="left" ><font class="textDescBold">21. County to Which payment of Goods to be made</font></td>
            </tr>
          <tr>
            <td align="left" ><%=DisplayUtil.displayInputTextBox("term_payment",form1ModelSP.getTerm_payment(),DisplayUtil.disabledField(form1Bean.getFormConfigVt(),"TERM_PAYMENT")+"maxlength=20") %></td>
            <td colspan="2" align="left" ><%=DisplayUtil.displayInputTextBox("country_of_good",form1ModelSP.getCountry_of_good(),DisplayUtil.disabledField(form1Bean.getFormConfigVt(),"COUNTRY_OF_GOOD")+"maxlength=20") %></td>
            </tr>
          <tr>
            <td>&nbsp;</td>
            <td colspan="2">&nbsp;</td>
            </tr>
          <tr>
            <td align="left" ><font class="textDescBold">22. Exchange Rate</font>
              </td>
            <td colspan="2" align="left" ><font class="textDescBold">23. FOB Value
              
              </font></td>
            </tr>
          <tr>
            <td align="left" ><%=DisplayUtil.displayInputTextBox("exchange_rate",form1ModelSP.getExchgRate_ID(),DisplayUtil.disabledField(form1Bean.getFormConfigVt(),"EXCHGRATE_ID")+"maxlength=4") %></td>
            <td colspan="2" align="left" ><%=DisplayUtil.displayInputTextBox("fob_value",form1ModelSP.getFob_value(),DisplayUtil.disabledField(form1Bean.getFormConfigVt(),"FOB_VALUE")+"maxlength=20") %></td>
            </tr>
          <tr>
            <td>&nbsp;</td>
            <td colspan="2">&nbsp;</td>
            </tr>
          <tr>
            <td align="left" ><font class="textDescBold">24. Insurance</font></td>
            <td width="159" align="left" ><font class="textDescBold">25. Freight</font></td>
            <td width="133" align="left" ><font class="textDescBold">22. CIF Value</font></td>
            </tr>
          <tr>
            <td align="left" ><%=DisplayUtil.displayInputTextBox("Insurance",form1ModelSP.getInsurance(),DisplayUtil.disabledField(form1Bean.getFormConfigVt(),"INSURANCE")+"maxlength=20") %></td>
            <td align="left" ><%=DisplayUtil.displayInputTextBox("Freight",form1ModelSP.getFreight(),DisplayUtil.disabledField(form1Bean.getFormConfigVt(),"FREIGHT")+"maxlength=20") %></td>
            <td align="left" ><%=DisplayUtil.displayInputTextBox("Cif_value",form1ModelSP.getCif_value(),DisplayUtil.disabledField(form1Bean.getFormConfigVt(),"CIF_VALUE")+"maxlength=20") %></td>
            </tr>
          <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            </tr>
          <tr>
            <td align="left" ><font class="textDescBold">24. Gross Wt.(Kg.)</font></td>
            <td align="left" ><font class="textDescBold">25. Measurement(m3)</font></td>
            <td align="left" ><font class="textDescBold">26. Other Charges</font></td>
            </tr>
          <tr>
            <td align="left" ><%=DisplayUtil.displayInputTextBox("Gross_weight",form1ModelSP.getGross_weight(),DisplayUtil.disabledField(form1Bean.getFormConfigVt(),"GROSS_WEIGHT")+"maxlength=20") %></td>
            <td align="left" ><%=DisplayUtil.displayInputTextBox("Measurement",form1ModelSP.getMeasurement(),DisplayUtil.disabledField(form1Bean.getFormConfigVt(),"MEASUREMENT")+"maxlength=20") %></td>
            <td align="left" ><%=DisplayUtil.displayInputTextBox("Other_charg",String.valueOf(form1ModelSP.getOther_charg()),DisplayUtil.disabledField(form1Bean.getFormConfigVt(),"OTHER_CHARG")+"maxlength=20") %></td>
            </tr>
          <tr>
            <td>&nbsp;</td>
            <td colspan="2">&nbsp;</td>
            </tr>
        </table></td>
      </tr>
      <tr>
        <td align="center" ><table border="0" cellspacing="1" cellpadding="1">
          <tr>
            <td colspan="2"><font class="textDescBold">4.Mode of   Transport </font></td>
            </tr>
          <tr>
            <td width="100" align="left"><font class="textDesc"><%=DisplayUtil.displayRadioTag(form1ModelSP.getMode_Trans(),"mode_trans","1") %>
              Sea</font></td>
            <td width="150" align="left"><font class="textDesc"><%=DisplayUtil.displayRadioTag(form1ModelSP.getMode_Trans(),"mode_trans","2") %>
              Rail</font></td>
            
            </tr>
          <tr>
            <td align="left"><font class="textDesc"><%=DisplayUtil.displayRadioTag(form1ModelSP.getMode_Trans(),"mode_trans","3") %>
              Road</font></td>
            <td align="left"><font class="textDesc"><%=DisplayUtil.displayRadioTag(form1ModelSP.getMode_Trans(),"mode_trans","4") %>
              Air</font></td>
            
            </tr>
          <tr>
            <td align="left"><font class="textDesc"><%=DisplayUtil.displayRadioTag(form1ModelSP.getMode_Trans(),"mode_trans","5") %>
              Pipe</font></td>
            <td align="left"><font class="textDesc"><%=DisplayUtil.displayRadioTag(form1ModelSP.getMode_Trans(),"mode_trans","6") %>
              Other(specify)
              </td>
            
            </tr>
          <tr>
            <td align="left"></td>
            <td align="left"><%=DisplayUtil.displayInputTextBox("trans_other",form1ModelSP.getTrans_Other(),"maxlength=20") %></td>
            
            </tr>
          </table></td>
        <td ><table border="0" cellspacing="1" cellpadding="1">
          <tr>
            <td><font class="textDescBold">5. Date of Import</font></td>
            
            </tr>
          <tr>
            
            <td><%=DisplayUtil.displayInputTextBox("Date_Import",DisplayFormatUtil.SQLDateToString1(form1ModelSP.getDate_Import(),"DD/MM/YYYY"),DisplayUtil.disabledField(form1Bean.getFormConfigVt(),"DATE_IMPORT")+"maxlength=10 onkeypress=\"addSlashFormat(event,this);keyPressInteger();\" onblur=\"checkDateLengthYear(this,'','1800','2300')\"") %></td>
            </tr>
          </table>
        </td>
        </tr>
      <tr>
        <td><table border="0" cellspacing="1" cellpadding="1">
          <tr>
            <td><font class="textDescBold">6. No/Name of vessel/Flight/conveyanee</font></td>
          </tr>
          <tr>
            <td><%=DisplayUtil.displayInputTextBox("vessel_value",form1ModelSP.getVessel_value(),DisplayUtil.disabledField(form1Bean.getFormConfigVt(),"VESSEL_VALUE")+"maxlength=50") %></td>
          </tr>
        </table></td>
        <td><table border="0" cellspacing="1" cellpadding="1">
          <tr>
            <td colspan="2"><font class="textDescBold">7. Port/Place of Import</font></td>
            </tr>
          <tr>
            <td><font class="textDesc"></font></td>
            <td><%=DisplayUtil.displaySelectTag(LoadCacheData.GetCountryCache(),form1ModelSP.getPortLoad_Code(),"PortImport_Code","EDIT",DisplayUtil.disabledField(form1Bean.getFormConfigVt(),"PORTIMPORT_CODE")) %></td>
            </tr>
          <tr>
            <td></td>
            <td></td>
            </tr>
        </table></td>
        </tr>
      <tr>
        <td><table border="0" cellspacing="1" cellpadding="1">
          <tr>
            <td colspan="2"><font class="textDescBold">8.Port/Place of   Loading</font></td>
          </tr>
          <tr>
            <td><font class="textDesc"></font></td>
            <td><%=DisplayUtil.displaySelectTag(LoadCacheData.GetCountryCache(),form1ModelSP.getPortLoad_Code(),"PortLoad_Code","EDIT",DisplayUtil.disabledField(form1Bean.getFormConfigVt(),"PORTLOAD_CODE")) %></td>
          </tr>
          <tr>
            <td></td>
            <td></td>
          </tr>
        </table></td>
        <td><table border="0" cellspacing="1" cellpadding="1">
          <tr>
            <td colspan="2"><font class="textDescBold">9. Via (Transhipment Cargo Only)</font></td>
            </tr>
          <tr>
            <td><font class="textDesc"></font></td>
            <td><%=DisplayUtil.displaySelectTag(LoadCacheData.GetCountryCache(),form1ModelSP.getPortLoad_Code(),"Via_Code","EDIT",DisplayUtil.disabledField(form1Bean.getFormConfigVt(),"VIA_CODE")) %></td>
            </tr>
          <tr>
            <td></td>
            <td></td>
            </tr>
        </table></td>
        </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td></td>
      </tr>
      <tr>
        <td colspan="3" align="center"><table id= "packageTab" border="0" cellspacing="1" cellpadding="1">
        	 <tr>
          	<td><input type="button" id =  "InsertPackage" name = "InsertPackage" value="Insert" onclick="addPackageTabJS();"></td>
            <td><font class="textHeader"><input type="button" id = "DeletePackage" name = "DeletePackage" value="Delete" onclick="removeRowFromTable('packageTab')"></font></td>
            <td><font class="textHeader"></font></td>
            <td><font class="textHeader"></font></td>
            <td><font class="textHeader"></font></td>
            <td><font class="textHeader"></font></td>
            <td><font class="textHeader"></font></td>
            </tr>
          <tr bgcolor="#0066FF">
          	<td rowspan="2" width="50"><input type="checkbox" name="checkall1" id="checkall1" /></td>
            <td rowspan="2" width="100"><font class="textHeader">Marks and Numbers</font></td>
            <td rowspan="2" width="150"><font class="textHeader">Item No.</font></td>
            <td rowspan="2" width="200"><font class="textHeader">No. and Type of Packages</font></td>
            <td rowspan="2" width="200"><font class="textHeader">Description of Goods</font></td>
            <td colspan="2"><font class="textHeader">Customs Tarili</font></td>
            </tr>
          <tr  bgcolor="#0066FF">
            <td width="100"><font class="textHeader">Code No.</font></td>
            <td width="100"><font class="textHeader">Unit</font></td>
          </tr>
          <% 
          
          Vector vtDetail1 = form1Bean.getDetail1MVt();
	        if(vtDetail1 != null && vtDetail1.size() > 0){
	        	for(int i=0;i<vtDetail1.size();i++){
	        		FormDetail1Model formDetail1M = (FormDetail1Model)vtDetail1.get(i); 
	        %>
          <tr bgcolor="#FFFFFF">
          	<td width="50"><input type="checkbox" name="checkall1" id="checkall1" /></td>
            <td width="100"><%=DisplayUtil.displayInputTextBox("MARK_NO",formDetail1M.getMarks_no(),"","10") %></td>
            <td width="150"><%=DisplayUtil.displayInputTextBox("ITEM_NO",formDetail1M.getItem_no(),"","10") %></td>
            <td width="200"><%=DisplayUtil.displayInputTextBox("PACKAGE_NO",formDetail1M.getNo_type_package(),"","10") %></td>
            <td width="200"><%=DisplayUtil.displayInputTextBox("GOODS_DESC",formDetail1M.getGood_desc(),"","10") %></td>
            <td width="100"><%=DisplayUtil.displaySelectTag_Code(form1Bean.getTanliCodeVt(), formDetail1M.getCust_code(), "CODE_NO", "EDIT", "","") %></td>
          	<td width="100"><%=DisplayUtil.displaySelectTag(form1Bean.getUnitVt(), formDetail1M.getCust_unit(), "UNIT", "EDIT", "") %></td>
           </tr>
           <%	}%>
	        	
	        	
       		<% }%>
        </table>
       
        </td>
        </tr>
      <tr>
        <td colspan="3"><table id ="qualityBaseTab" border="0" cellspacing="1" cellpadding="1">
          <tr >
            <td><font class="textHeader"><input type="button" id = "InsertQualityBase" name = "InsertQualityBase" value="Insert" onclick="addQualityBaseTabJS();"></font></td>
            <td><font class="textHeader"><input type="button" id = "DeleteQualityBase" name = "DeleteQualityBase" value="Delete"  onclick="removeRowFromTable('qualityBaseTab')"></font></td>
            <td><font class="textHeader"></font></td>
            <td><font class="textHeader"></font></td>
            <td><font class="textHeader"></font></td>
            <td><font class="textHeader"></font></td>
            <td><font class="textHeader"></font></td>
          </tr>
          <tr bgcolor="#0066FF">
            <td rowspan="2"width="50"><font class="textHeader"><input type="checkbox" name="checkall2" id="checkall2" /></td>
            <td rowspan="2"width="100"><font class="textHeader">Item No.</font></td>
            <td rowspan="2"width="100"><font class="textHeader">Quantity Based on Customs tariff Unit</font></td>
            <td colspan="2"width="200"><font class="textHeader">Unit Value FOB</font></td>
            <td rowspan="2"width="100"><font class="textHeader">Total Value</font></td>
            <td colspan="2"width="200"><font class="textHeader">Export duty</font></td>
            <td colspan="3"width="300"><font class="textHeader">Other Taxes payable</font></td>
            </tr>
          <tr bgcolor="#0066FF">
            <td><font class="textHeader">Actual</font></td>
            <td><font class="textHeader">Customs</font></td>
            <td><font class="textHeader">Rate</font></td>
            <td><font class="textHeader">Amount</font></td>
            <td><font class="textHeader">Type</font></td>
            <td><font class="textHeader">Rate</font></td>
            <td><font class="textHeader">Amount</font></td>
          </tr>
          <% Vector vtDetail2 = form1Bean.getDetail2MVt();
	        if(vtDetail2 != null && vtDetail2.size() > 0){
	        	for(int i=0;i<vtDetail2.size();i++){
	        		FormDetail2Model formDetail2M = (FormDetail2Model)vtDetail2.get(i); 
	        %>
          <tr bgcolor="#FFFFFF">
          	<td width="50"><input type="checkbox" name="checkall1" id="checkall1" /></td>
            <td width="100"><%=DisplayUtil.displayInputTextBox("QA_ITEM_NO",formDetail2M.getItem_no(),"","10") %></td>
            <td width="100"><%=DisplayUtil.displayInputTextBox("QB_UNIT",Double.toString(formDetail2M.getQty_cust_unit()),"","10") %></td>
            <td width="100"><%=DisplayUtil.displayInputTextBox("FOB_ACTUAL",formDetail2M.getUnit_val_actual(),"","10") %></td>
            <td width="100"><%=DisplayUtil.displayInputTextBox("FOB_CUSTOM",formDetail2M.getUnit_val_custom(),"","20") %></td>
            <td width="100"><%=DisplayUtil.displayInputTextBox("TOTAL_VALUE",Double.toString(formDetail2M.getTotal_value()),"","10") %></td>
            <td width="100"><%=DisplayUtil.displaySelectTag(form1Bean.getDutyRateVt(), String.valueOf(formDetail2M.getExport_rate()), "DUTY_RATE", "EDIT", "") %></td>
            <td width="100"><%=DisplayUtil.displayInputTextBox("DUTY_AMOUNT",Double.toString(formDetail2M.getExport_amount()),"","10") %></td>
            <td width="100"><%=DisplayUtil.displayInputTextBox("TAX_TYPE",formDetail2M.getOther_tax_type(),"","10") %></td>
            <td width="100"><%=DisplayUtil.displayInputTextBox("TAX_RATE",Double.toString(formDetail2M.getOther_tax_rate()),"","10") %></td>
            <td width="100"><%=DisplayUtil.displayInputTextBox("TAX_AMOUNT",Double.toString(formDetail2M.getOther_tax_amount()),"","10") %></td>
            
          </tr>
          <%	}
       		 }%>
        </table></td>
        </tr>
      <tr>
        <td colspan="3"><table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#F8F8F8">
          <tr>
            <td><table width="311" border="0" cellpadding="1" cellspacing="1">
              <tr>
                <td colspan="2">&nbsp;</td>
              </tr>
              <tr>
                <td colspan="2"><font class="textDescBold">48. Name of Declarant</font></td>
              </tr>
              <tr>
                <td colspan="2"><%=DisplayUtil.displayInputTextBox("declarant_name",form1ModelSP.getDeclarant_name(),DisplayUtil.disabledField(form1Bean.getFormConfigVt(),"DECLARANT_NAME")+"maxlength=20") %></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td colspan="2"><font class="textDescBold">49. Identity Card/Passport No.</font></td>
              </tr>
              <tr>
                <td colspan="2"><%=DisplayUtil.displayInputTextBox("id_card_no",form1ModelSP.getId_card_no(),DisplayUtil.disabledField(form1Bean.getFormConfigVt(),"ID_CARD_NO")+"maxlength=20") %></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td colspan="2"><font class="textDescBold">50. Status</font></td>
              </tr>
              <tr>
                <td><font class="textDescBold"><%=DisplayUtil.displayRadioTag(form1ModelSP.getStatus(),"Status","A") %>
                  Active</font></td>
                <td><font class="textDescBold"><%=DisplayUtil.displayRadioTag(form1ModelSP.getStatus(),"Status","I") %>
                  Inactive</font></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td colspan="2"><font class="textDescBold">51. I cerify that this declaration is true and complete.</font></td>
              </tr>
              <tr>
                <td colspan="2"><%=DisplayUtil.displayInputTextBox("cerify",form1ModelSP.getCerify(),DisplayUtil.disabledField(form1Bean.getFormConfigVt(),"CERIFY")+"maxlength=20") %></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
            </table></td>
            <td><table width="312" border="0" cellpadding="1" cellspacing="1">
              <tr>
                <td colspan="2" align="center">&nbsp;</td>
              </tr>
              <tr>
                <td colspan="2" align="center"><font class="textDescBold">FOR OFFICE   USE</font></td>
                </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td colspan="2"><font class="textDescBold">52. Removal from Customs Control authorized by</font></td>
                </tr>
              <tr>
                <td colspan="2"><%=DisplayUtil.displayInputTextAreaTag("cus_removal",form1ModelSP.getCus_removal(),DisplayUtil.disabledField(form1Bean.getFormConfigVt(),"CUS_REMOVAL")+"maxlength=255") %></td>
                </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
            </table></td>
            <td><table width="320" border="0" cellpadding="1" cellspacing="1">
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td><font class="textDescBold">53. Total Duty/Tax Payable</font></td>
                <td><%=DisplayUtil.displayInputTextBox("tax_total",String.valueOf(form1ModelSP.getTax_total()),DisplayUtil.disabledField(form1Bean.getFormConfigVt(),"TAX_TOTAL")+"maxlength=\"15\" onkeypress = \"keypressWithDegit(this,'15')\" onblur=\"formatCurrency(this)\"") %></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td><font class="textDescBold">54. Other Charges</font></td>
                <td><%=DisplayUtil.displayInputTextBox("Other_charg2",String.valueOf(form1ModelSP.getOther_charg2()),DisplayUtil.disabledField(form1Bean.getFormConfigVt(),"OTHER_CHARG2")+"maxlength=\"15\" onkeypress = \"keypressWithDegit(this,'15')\"onblur=\"formatCurrency(this)\"") %></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td><font class="textDescBold">55. Total Amount Payable</font></td>
                <td><%=DisplayUtil.displayInputTextBox("payable_amount",String.valueOf(form1ModelSP.getPayable_amount()),DisplayUtil.disabledField(form1Bean.getFormConfigVt(),"PAYABLE_AMOUNT")+"maxlength=\"15\" onkeypress = \"keypressWithDegit(this,'15')\"onblur=\"formatCurrency(this)\"") %></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td colspan="2"><font class="textDescBold">56. Manualscript Recerpt No.(If applicable)</font></td>
                </tr>
              <tr>
                <td colspan="2"><%=DisplayUtil.displayInputTextAreaTag("manualscript_recerpt",form1ModelSP.getManualscript_recerpt(),DisplayUtil.disabledField(form1Bean.getFormConfigVt(),"MANUALSCRIPT_RECERPT")+"maxlength=255") %></td>
                </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
            </table></td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td colspan="3"><table width="100%" border="0" cellspacing="1" cellpadding="1">
          <tr>
            <td colspan="2" align="center">&nbsp;</td>
          </tr>
          <tr>
            <td colspan="2" align="center"><font class="textDescBold">FOR OFFICE   USE</font></td>
            </tr>
          <tr>
            <td align="center"><font class="textDescBold">INSTRUCTION FOR   EXAMINATION</font></td>
            <td align="center"><font class="textDescBold">RESULT OF   EXAMINATION</font></td>
          </tr>
          <tr>
            <td align="center"><%=DisplayUtil.displayInputTextAreaTag("instruct_exam",form1ModelSP.getInstruct_exam(),DisplayUtil.disabledField(form1Bean.getFormConfigVt(),"INSTRUCT_EXAM")+"maxlength=255") %></td>
            <td align="center"><%=DisplayUtil.displayInputTextAreaTag("result_exam",form1ModelSP.getResult_exam(),DisplayUtil.disabledField(form1Bean.getFormConfigVt(),"RESULT_EXAM")+"maxlength=255") %></td>
          </tr>
          <tr>
            <td align="center">&nbsp;</td>
            <td align="center">&nbsp;</td>
          </tr>
        </table></td>
        </tr>
      <tr>
        <td colspan="3"><table width="100%" border="0" cellspacing="1" cellpadding="1"> 
          <tr>
            <td align="center">&nbsp;</td>
          </tr>
          <tr>
            <td align="center"><font class="textDescBold">FOR OTHER USE</font></td>
          </tr>
          <tr>
            <td align="center"><%=DisplayUtil.displayInputTextAreaTag("for_other_use",form1ModelSP.getFor_other_use(),DisplayUtil.disabledField(form1Bean.getFormConfigVt(),"FOR_OTHER_USE")+"maxlength=255") %></td>
          </tr>
          <tr>
            <td align="center">&nbsp;</td>
            </tr>
        <tr>
        <td colspan="3"><table width="100%" border="0" cellspacing="1" cellpadding="1">
          <tr>
            <td align="center">&nbsp;</td>
          </tr>
          <%
			if ("EJDAM022".equals(form_action)){
          %>
          <tr>
            <td align="center"><font class="textDescBold">Remark : </font></td>
          </tr>
          <tr>
            <td align="center"><%=DisplayUtil.displayInputTextAreaTag("remark",form1ModelSP.getRemark(),"maxlength=255") %></td>
          </tr>
          <tr>
            <td align="center">&nbsp;</td>
            </tr>
            <%} %>
        </table></td>
        </tr>
      <tr>
        <td colspan="2">&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
      	<%
			String disable = "";
      		if (null!=form_action && !"EJDAM010".equals(form_action)){
      			disable = "disabled = \"disabled\"";
      		}
      		log.debug("-------------- " + form1ModelSP.getDoc_ID());
      	%>
        <td colspan="3" align="center"> <input type="button" name="Save" id="Save" <%=disable %>value="  Save  " onclick="validateSaveButton(this.form,'<%=form_action%>')"/>
          <input type="button" name="Submit" id="Submit" value="  Submit  "  onclick="validateSubmitButton(this.form,'<%=form_action %>')"/>
          <input type="button" name="Cancel" id="Cancel" value="  Cancel  " onclick="CancelButton(this.form,'<%=form_action %>')"/></td>
        </tr>
      <tr>
        <td colspan="2">&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
    </table></td>
  </tr>
</table>
<script type="text/javascript">
	readOnlyDeliver('<%=form_action %>');
	setReadOnlyDetail1('<%=form_action %>');
	setReadOnlyDetail2('<%=form_action %>');
</script>
</form>
<%@page import="com.tcd.ejda.model.Form1Model"%>
<%@page import="com.tcd.ejda.model.FormDetail1Model"%>
<%@page import="com.tcd.ejda.model.FormDetail2Model"%>
<%@page import="com.ejda.sessionBean.Form2Bean"%>
<%@page import="com.ejda.util.DisplayFormatUtil"%>
<%@page import="com.ejda.util.DisplayUtil"%>
<%@page import="com.ejda.util.LoadCacheData"%>
<%@page import="com.tcd.ejda.model.ValueListModel"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="java.util.Vector"%>
<script language="javascript" src="js/ejdaform2.js"></script>
<form name="ejdaformNo2" method="post" action="/EJDAWeb/EJDAControler">
<%
	Logger log = Logger.getLogger("JspLog");
	Form2Bean form2Bean = (Form2Bean)request.getSession().getAttribute("form2Bean");
	log.debug("form2Bean :: " + form2Bean);
	Form1Model form2ModelSP = form2Bean.getForm2ModelSP();
	Vector form2Vt = form2Bean.getForm2Vt();
	Form1Model form2M = new Form1Model();
	FormDetail1Model detail1ModelSP = form2Bean.getDetail1ModelSP();
	FormDetail2Model detail2ModelSP = form2Bean.getDetail2ModelSP();
	String form_action = (String)form2Bean.getActionName();
	String form_no = (String)request.getSession().getAttribute("form_no");
	String showName = "";
	
	if (null!=form_action && "EJDAM011".equals(form_action)){
		showName ="Table 1 : Form no. 2";
	
	}else if (null!=form_action && "EJDAM015".equals(form_action)){
		showName ="Table 2 : Form no. 2";
	}else if (null!=form_action && "EJDAM019".equals(form_action)){
		showName ="Table 3 : Form no. 2";
	}else if (null!=form_action && "EJDAM023".equals(form_action)){
		showName ="Table 4 : Form no. 2";
	}
	String bgColor1 = "bordercolor=\"#F4F4F4\"";
	String bgColor2 = "bgcolor=\"#DFEFFF\"";
	ValueListModel valueListM = form2Bean.getValueListM();
	
	if(null == valueListM) valueListM = new ValueListModel();
		
	%>
	<input type="hidden" name="ejdaAction" value=""> 
	<input type="hidden" name="ejdaMethod" value=""> 
	<input type="hidden" name="screenName" value="">
	<input type="hidden" name="screenName" value="">
	<input type="hidden" name="actionName" value="">
	<input type="hidden" name="form_no" value="<%=form_no %>">
	<input type="hidden" name="doc_id" value="<%=form2ModelSP.getDoc_ID() %>">
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
            <td colspan="2" align="left" >&nbsp;</td>
          </tr>
          <tr>
            <td colspan="2" align="left" width="400" ><font class="textDescBold">1.Consignor/Exporter(Name and address)</font></td>
           </tr>
           <tr>
            <td colspan="2" align="left" height="20"></td>
           </tr>
          <tr>
            <td align="right"><font class="textDesc">Exporter/Taxpayer Code </font></td>
            <td align="left"><%=DisplayUtil.displayInputTextBox("consignorExportCode",form2ModelSP.getConsignor_code(),"maxlength=20 onkeypress=\"keyPressInteger()\"") %>
            </td>
          </tr>
          <tr>
            <td align="right"><font class="textDesc">Name </font></td>
            <td align="left"><%=DisplayUtil.displayInputTextBox("consignorExportName",form2ModelSP.getConsignor_name(),"maxlength=20") %></td>
          </tr>
          <tr>
            <td align="right"><font class="textDesc">Address </font></td>
            <td align="left"><%=DisplayUtil.displayInputTextAreaTag("consignorExportAddress",form2ModelSP.getConsignor_address(),"maxlength=255") %></td>
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
            <td colspan="2" align="left">
             <%=DisplayUtil.displayInputTextBox("Date_Receipt",DisplayFormatUtil.SQLDateToString1(form2ModelSP.getDate_Receipt(),"DD/MM/YYYY"),"maxlength=10 onkeypress=\"addSlashFormat(event,this);keyPressInteger();\" onblur=\"checkDateLengthYear(this,'','1800','2300')\"") %>
           </td>
            <td align="left"><font class="textDesc"><%=DisplayUtil.displayCheckBoxForDocAttach(form2Bean.docAttachMVt,"doc_attach","INVOICE","") %> 
              Invoice</font></td>
          </tr>
          <tr>
            <td colspan="2">&nbsp;</td>
            <td align="left"><font class="textDesc">
              <%=DisplayUtil.displayCheckBoxForDocAttach(form2Bean.docAttachMVt,"doc_attach","BILL OF LADING","") %>
              Bill of Lading
            </font></td>
          </tr>
          <tr>
            <td colspan="2" align="left"><font class="textDescBold">12.Registration   Number</font></td>
            <td align="left"><font class="textDesc">
              <%=DisplayUtil.displayCheckBoxForDocAttach(form2Bean.docAttachMVt,"doc_attach","INSURANCE CERT","") %>
              Insurance Certificate
            </font></td>
          </tr>
          <tr>
            <td colspan="2" align="left"><%=DisplayUtil.displayInputTextBox("Regis_no",form2ModelSP.getRegis_no(),"maxlength=50") %></td>
            <td align="left"><font class="textDesc">
              <%=DisplayUtil.displayCheckBoxForDocAttach(form2Bean.docAttachMVt,"doc_attach","LETTER OF CREDIT","") %>
              Letter of Credit
            </font></td>
          </tr>
          <tr>
            <td colspan="2">&nbsp;</td>
            <td align="left"><font class="textDesc">
              <%=DisplayUtil.displayCheckBoxForDocAttach(form2Bean.docAttachMVt,"doc_attach","OTHER","") %>
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
            <td align="left"><font class="textDesc">
              <%=DisplayUtil.displayInputTextBox("cus_name_code",form2ModelSP.getCus_name_code(),"maxlength=4 onkeypress=\"keyPressInteger()\"") %>
            </font></td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td align="left"><font class="textDesc">Description 
              </font></td>
            <td align="left"><font class="textDesc">
              <%=DisplayUtil.displayInputTextBox("cus_name_desc",form2ModelSP.getCus_name_desc(),"maxlength=50") %>
            </font></td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td colspan="2">&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td colspan="2" align="left">&nbsp;</td>
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
            <td align="center" colspan="2"><%=DisplayUtil.displayInputTextBox("duty_tax_receipt_date",DisplayFormatUtil.SQLDateToString1(form2ModelSP.getDuty_tax_receipt_date(),"DD/MM/YYYY"),"maxlength=10 onkeypress=\"addSlashFormat(event,this);keyPressInteger();\" onblur=\"checkDateLengthYear(this,'','1800','2300')\"") %></td>
            <td align="center"><%=DisplayUtil.displayInputTextBox("duty_tax_receipt_desc",form2ModelSP.getDuty_tax_receipt_desc(),"maxlength=50") %></td>
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
            <td align="left"><%=DisplayUtil.displayInputTextBox("Consignee_code",form2ModelSP.getConsignee_code(),"maxlength=20") %></td>
            </tr>
          <tr>
            <td align="right"><font class="textDesc">Name </font></td>
            <td align="left"><%=DisplayUtil.displayInputTextBox("Consignee_name",form2ModelSP.getConsignee_name(),"maxlength=20") %></td>
            </tr>
          <tr>
            <td align="right"><font class="textDesc">Address </font></td>
            <td align="left"><%=DisplayUtil.displayInputTextAreaTag("Consignee_address",form2ModelSP.getConsignee_address(),"maxlength=255") %></td>
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
            <td align="right"><font class="textDesc">Agent/Taxpayer   Code</font></td>
            <td align="left"><%=DisplayUtil.displayInputTextBox("AuthorAgent_code",form2ModelSP.getAuthorAgent_code(),"maxlength=20") %></td>
          </tr>
          <tr>
            <td align="right"><font class="textDesc">Name </font></td>
            <td align="left"><%=DisplayUtil.displayInputTextBox("AuthorAgent_name",form2ModelSP.getAuthorAgent_name(),"maxlength=20") %></td>
          </tr>
          <tr>
            <td align="right"><font class="textDesc">Address </font></td>
            <td align="left"><%=DisplayUtil.displayInputTextAreaTag("AuthorAgent_address",form2ModelSP.getAuthorAgent_address(),"maxlength=255") %></td>
          </tr>
        </table></td>
        <td rowspan="4" align="center"><table width="472" border="0" cellpadding="1" cellspacing="1">
          <tr>
            <td width="170" colspan="2" align="left"><font class="textDescBold">15.Special Treatment</font></td>
            <td width="159" align="left"><font class="textDescBold">16.Export Permit No.</font></td>
            <td align="left"><font class="textDescBold">17.Exchange Control Ref.</font></td>
            </tr>
          <tr>
            <td colspan="2" align="left"><%=DisplayUtil.displayInputTextBox("special_treatment",form2ModelSP.getSpecial_treatment(),"maxlength=20") %></td>
            <td align="left"><%=DisplayUtil.displayInputTextBox("import_permit_no",form2ModelSP.getImport_permit_no(),"maxlength=20") %></td>
            <td align="left"><%=DisplayUtil.displayInputTextBox("exchg_ctrl_ref",form2ModelSP.getExchg_ctrl_ref(),"maxlength=20") %></td>
            </tr>
          <tr>
            <td colspan="2">&nbsp;</td>
            <td colspan="2">&nbsp;</td>
            </tr>
          <tr>
            <td colspan="2" align="left" ><font class="textDescBold">18. Country of Origin</font></td>
            <td colspan="2" align="left" ><font class="textDescBold">19. Country of Final Destination.</font></td>
            </tr>
          <tr>
            <td align="left" ><font class="textDesc">Code </font>             </td>
            <td align="left" ><%=DisplayUtil.displayInputTextBox("country_origin_code",form2ModelSP.getCountry_origin_code(),"maxlength=10") %></td>
            <td align="right" ><font class="textDesc">Code</font></td>
            <td align="left" ><%=DisplayUtil.displayInputTextBox("country_final_code",form2ModelSP.getCountry_final_code(),"maxlength=10") %></td>
            </tr>
          <tr>
            <td align="left"><font class="textDesc">Desc</font></td>
            <td align="left"><%=DisplayUtil.displayInputTextBox("country_origin_desc",form2ModelSP.getCountry_origin_desc(),"maxlength=20") %></td>
            <td align="right"><font class="textDesc">Desc</font></td>
            <td align="left"><%=DisplayUtil.displayInputTextBox("country_final_desc",form2ModelSP.getCountry_final_desc(),"maxlength=20") %></td>
            </tr>
          <tr>
            <td colspan="2" align="left" >&nbsp;</td>
            <td colspan="2" align="left" >&nbsp;</td>
            </tr>
          <tr>
            <td colspan="2" align="left" ><font class="textDescBold">20.Terms of Delivery and Payment</font></td>
            <td align="left" ><font class="textDescBold">21.Currency Code</font></td>
            <td align="left" ><font class="textDescBold">22.Amount Received/to be Received</font></td>
            </tr>
          <tr>
            <td colspan="2" align="left" ><%=DisplayUtil.displayInputTextBox("term_payment",form2ModelSP.getTerm_payment(),"maxlength=20") %></td>
            <td align="left" ><%=DisplayUtil.displayInputTextBox("cur_code",form2ModelSP.getCur_code(),"maxlength=4") %></td>
            <td align="left" ><%=DisplayUtil.displayInputTextBox("Received_amount",String.valueOf(form2ModelSP.getReceived_amount()),"maxlength=\"15\" onkeypress = \"keypressWithDegit(this,'15')\"onblur=\"formatCurrency(this)\"")%></td>
            </tr>
          <tr>
            <td colspan="2">&nbsp;</td>
            <td colspan="2">&nbsp;</td>
            </tr>
          <tr>
            <td colspan="2" align="left" ><font class="textDescBold">23. Exchange Rate</font>
              </td>
            <td align="left" ><font class="textDescBold">24.Equivalent
              
              </font></td>
            <td align="left" ><font class="textDescBold">25.Insurance </font></td>
            </tr>
          <tr>
            <td colspan="2" align="left" ><%=DisplayUtil.displayInputTextBox("exchange_rate",form2ModelSP.getExchgRate_ID(),"maxlength=10") %></td>
            <td align="left" ><%=DisplayUtil.displayInputTextBox("Equivalent",form2ModelSP.getEquivalent(),"maxlength=20") %></td>
            <td align="left" ><%=DisplayUtil.displayInputTextBox("Insurance",form2ModelSP.getInsurance(),"maxlength=20") %></td>
            </tr>
          <tr>
            <td colspan="2">&nbsp;</td>
            <td colspan="2">&nbsp;</td>
            </tr>
          <tr>
            <td colspan="3" align="left" ><font class="textDescBold">26.Payment for Goods Received/to be Received</font></td>
            <td width="133" align="left" ><font class="textDescBold">27.freight</font></td>
            </tr>
          <tr>
            <td align="left" ><font class="textDesc">Code</font></td>
            <td align="left" ><%=DisplayUtil.displayInputTextBox("good_payment_code",form2ModelSP.getGood_payment_code(),"maxlength=20") %></td>
            <td align="left" >&nbsp;</td>
            <td align="left" ><input type="text" name="ExchangeRate4" id="ExchangeRate4" /></td>
            </tr>
          <tr>
            <td align="left" ><font class="textDesc">Descriptiont</font></td>
            <td align="left" ><%=DisplayUtil.displayInputTextBox("good_payment_desc",form2ModelSP.getGood_payment_desc(),"maxlength=20") %></td>
            <td align="left" >&nbsp;</td>
            <td align="left" >&nbsp;</td>
            </tr>
          <tr>
            <td colspan="2">&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            </tr>
          <tr>
            <td colspan="2" align="left" ><font class="textDescBold">28.Gross Wt.(kg.)</font></td>
            <td align="left" ><font class="textDescBold">29.Measurement </font></td>
            <td align="left" ><font class="textDescBold">30.FOB Value</font></td>
            </tr>
          <tr>
            <td colspan="2" align="left" ><%=DisplayUtil.displayInputTextBox("Gross_weight",form2ModelSP.getGross_weight(),"maxlength=20") %></td>
            <td align="left" ><%=DisplayUtil.displayInputTextBox("Measurement",form2ModelSP.getMeasurement(),"maxlength=20") %></td>
            <td align="left" ><%=DisplayUtil.displayInputTextBox("fob_value",form2ModelSP.getFob_value(),"maxlength=20") %></td>
            </tr>
          <tr>
            <td colspan="2">&nbsp;</td>
            <td colspan="2">&nbsp;</td>
            </tr>
        </table></td>
      </tr>
      <tr>
        <td align="center" ><p>&nbsp;</p>
          <table border="0" cellspacing="1" cellpadding="1">
          <tr>
            <td colspan="2"><font class="textDescBold">4.Mode of   Transport </font></td>
            </tr>
          <tr>
            <td width="25%"><font class="textDesc"><%=DisplayUtil.displayRadioTag(form2ModelSP.getMode_Trans(),"mode_trans","1") %>
              Sea</font></td>
            <td width="75%"><font class="textDesc"><%=DisplayUtil.displayRadioTag(form2ModelSP.getMode_Trans(),"mode_trans","2") %>
              Rail</font></td>
            
            </tr>
          <tr>
            <td><font class="textDesc"><%=DisplayUtil.displayRadioTag(form2ModelSP.getMode_Trans(),"mode_trans","3") %>
              Road</font></td>
            <td><font class="textDesc"><%=DisplayUtil.displayRadioTag(form2ModelSP.getMode_Trans(),"mode_trans","4") %>
              Air</font></td>
            
            </tr>
          <tr>
            <td><font class="textDesc"><%=DisplayUtil.displayRadioTag(form2ModelSP.getMode_Trans(),"mode_trans","5") %>
              Pipe</font></td>
            <td><font class="textDesc"><%=DisplayUtil.displayRadioTag(form2ModelSP.getMode_Trans(),"mode_trans","6") %>
              Other(specify)
              </td>
            
            </tr>
          <tr>
            <td></td>
            <td><%=DisplayUtil.displayInputTextBox("trans_other",form2ModelSP.getTrans_Other(),"maxlength=20") %></td>
            
            </tr>
          </table></td>
        <td ><table border="0" cellspacing="1" cellpadding="1">
          <tr>
            <td><font class="textDescBold">5. Date of Import</font></td>
            
            </tr>
          <tr>
            
            <td><%=DisplayUtil.displayInputTextBox("Date_Import",DisplayFormatUtil.SQLDateToString1(form2ModelSP.getDate_Import(),"DD/MM/YYYY"),"maxlength=10 onkeypress=\"addSlashFormat(event,this);keyPressInteger();\" onblur=\"checkDateLengthYear(this,'','1800','2300')\"") %></td>
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
            <td><%=DisplayUtil.displayInputTextBox("vessel_value",form2ModelSP.getVessel_value(),"maxlength=50") %></td>
          </tr>
        </table></td>
        <td><table border="0" cellspacing="1" cellpadding="1">
          <tr>
            <td colspan="2"><font class="textDescBold">7. Port/Place of Import</font></td>
            </tr>
          <tr>
            <td><font class="textDesc"></font></td>
            <td><%=DisplayUtil.displaySelectTag(LoadCacheData.GetCountryCache(),form2ModelSP.getPortLoad_Code(),"PortImport_Code","EDIT","") %></td>
            </tr>
          <tr>
            <td><font class="textDesc"></font></td>
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
            <td><font class="textDesc"> </font></td>
            <td><%=DisplayUtil.displaySelectTag(LoadCacheData.GetCountryCache(),form2ModelSP.getPortLoad_Code(),"PortLoad_Code","EDIT","") %></td>
          </tr>
          <tr>
            <td><font class="textDesc"> </font></td>
            <td></td>
          </tr>
        </table></td>
        <td><table border="0" cellspacing="1" cellpadding="1">
          <tr>
            <td colspan="2"><font class="textDescBold">9. Via (Transhipment Cargo Only)</font></td>
            </tr>
          <tr>
            <td><font class="textDesc"> </font></td>
            <td><%=DisplayUtil.displaySelectTag(LoadCacheData.GetCountryCache(),form2ModelSP.getPortLoad_Code(),"Via_Code","EDIT","") %></td>
            </tr>
          <tr>
            <td><font class="textDesc"> </font></td>
            <td></td>
            </tr>
        </table></td>
        </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
     <tr>
        <td colspan="3" align="center"><table id= "packageTab" border="0" cellspacing="1" cellpadding="1">
        	 <tr>
          	<td><input type="button" name = "InsertPackage" value="Insert" onclick="addPackageTabJS();"></td>
            <td><font class="textHeader"><input type="button" name = "DeletePackage" value="Delete" onclick="removeRowFromTable('packageTab');"></font></td>
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
          <% Vector vtDetail1 = form2Bean.getDetail1MVt();
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
            <td width="100"><%=DisplayUtil.displayInputTextBox("CODE_NO",formDetail1M.getCust_code(),"","10") %></td>
          	<td width="100"><%=DisplayUtil.displayInputTextBox("CODE_NO",formDetail1M.getCust_unit(),"","10") %></td>
           </tr>
           <%	}
       		 }%>
        </table></td>
        </tr>
      <tr>
        <td colspan="3"><table id ="qualityBaseTab" border="0" cellspacing="1" cellpadding="1">
          <tr >
            <td><font class="textHeader"><input type="button" name = "InsertQualityBase" value="Insert" onclick="addQualityBaseTabJS();"></font></td>
            <td><font class="textHeader"><input type="button" name = "DeleteQualityBase" value="Delete" onclick="removeRowFromTable('qualityBaseTab');"></font></td>
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
           <% Vector vtDetail2 = form2Bean.getDetail2MVt();
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
            <td width="100"><%=DisplayUtil.displayInputTextBox("DUTY_RATE",Double.toString(formDetail2M.getExport_rate()),"","10") %></td>
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
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td colspan="2"><font class="textDescBold">50. Identity Card/Passport No.</font></td>
              </tr>
              <tr>
                <td colspan="2"><%=DisplayUtil.displayInputTextBox("id_card_no",form2ModelSP.getId_card_no(),"maxlength=20") %></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td colspan="2"><font class="textDescBold">51. Status</font></td>
              </tr>
              <tr>
                <td><font class="textDescBold"><%=DisplayUtil.displayRadioTag(form2ModelSP.getStatus(),"Status","A") %>
                  Active</font></td>
                <td><font class="textDescBold"><%=DisplayUtil.displayRadioTag(form2ModelSP.getStatus(),"Status","I") %>
                  Inactive</font></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td colspan="2"><font class="textDescBold">52. I cerify that this declaration is true and complete.</font></td>
              </tr>
              <tr>
                <td colspan="2"><%=DisplayUtil.displayInputTextBox("cerify",form2ModelSP.getCerify(),"maxlength=20") %></td>
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
                <td colspan="2"><font class="textDescBold">53. Removal from Customs Control authorized by</font></td>
                </tr>
              <tr>
                <td colspan="2"><%=DisplayUtil.displayInputTextAreaTag("cus_removal",form2ModelSP.getCus_removal(),"maxlength=255") %></td>
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
                <td colspan="2"><font class="textDescBold">57. Manualscript Recerpt No.(If applicable)</font></td>
              </tr>
              <tr>
                <td colspan="2"><%=DisplayUtil.displayInputTextAreaTag("manualscript_recerpt",form2ModelSP.getManualscript_recerpt(),"maxlength=255") %></td>
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
            <td align="center"><%=DisplayUtil.displayInputTextAreaTag("instruct_exam",form2ModelSP.getInstruct_exam(),"maxlength=255") %></td>
            <td align="center"><%=DisplayUtil.displayInputTextAreaTag("result_exam",form2ModelSP.getResult_exam(),"maxlength=255") %></td>
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
            <td align="center"><%=DisplayUtil.displayInputTextAreaTag("for_other_use",form2ModelSP.getFor_other_use(),"maxlength=255") %></td>
          </tr>
          <tr>
            <td align="center">&nbsp;</td>
            </tr>
        </table></td>
        </tr>
      <tr>
        <td colspan="2">&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td colspan="3" align="center">
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
</body>
</html>
	
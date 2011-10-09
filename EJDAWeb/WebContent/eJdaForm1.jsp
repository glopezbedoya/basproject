<%@page import="com.tcd.ejda.model.Form1Model"%>
<%@page import="com.tcd.ejda.model.FormDetail1Model"%>
<%@page import="com.tcd.ejda.model.FormDetail2Model"%>
<%@page import="com.ejda.sessionBean.Form1Bean"%>
<%@page import="com.ejda.util.DisplayFormatUtil"%>
<%@page import="com.ejda.util.DisplayUtil"%>
<%@page import="com.tcd.ejda.model.ValueListModel"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="java.util.Vector"%>
<script language="javascript" src="js/ejdaform1.js"></script>
<form name="ejdaformNo1" method="post" action="/EJDAWeb/EJDAControler">
<%
	Logger log = Logger.getLogger("JspLog");
	Form1Bean form1Bean = (Form1Bean)request.getSession().getAttribute("Form1Bean");
	log.debug("form1Bean :: " + form1Bean);
	Form1Model form1ModelSP = form1Bean.getForm1ModelSP();
	FormDetail1Model detail1ModelSP = form1Bean.getDetail1ModelSP();
	FormDetail2Model detail2ModelSP = form1Bean.getDetail2ModelSP();
	Vector form1Vt = form1Bean.getForm1Vt();
	Form1Model form1M = new Form1Model();
	String form_action = (String)form1Bean.getActionName();
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
	<input type="hidden" name="form_no" value="<%=form_no %>">
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
            <td align="left"><%=DisplayUtil.displayInputTextBox("consignorExportCode",form1ModelSP.getConsignor_code(),"") %>
            </td>
          </tr>
          <tr>
            <td align="right"><font class="textDesc">Name </font></td>
            <td align="left"><%=DisplayUtil.displayInputTextBox("consignorExportName",form1ModelSP.getConsignor_name(),"") %></td>
          </tr>
          <tr>
            <td align="right"><font class="textDesc">Address </font></td>
            <td align="left"><%=DisplayUtil.displayInputTextAreaTag("consignorExportAddress",form1ModelSP.getConsignor_address(),"") %></td>
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
            <td colspan="2" align="left"><%=DisplayUtil.displayInputTextBox("Date_Receipt",DisplayFormatUtil.SQLDateToString1(form1ModelSP.getDate_Receipt(),"DD/MM/YYYY"),"") %>
             
           </td>
            <td align="left"><font class="textDesc"><input type="checkbox" name="DocumentAttached" id="DocumentAttached" /> 
              Invoice</font></td>
          </tr>
          <tr>
            <td colspan="2">&nbsp;</td>
            <td align="left"><font class="textDesc">
              <input type="checkbox" name="DocumentAttached" id="DocumentAttached" />
              Bill of Lading
            </font></td>
          </tr>
          <tr>
            <td colspan="2" align="left"><font class="textDescBold">12.Registration   Number</font></td>
            <td align="left"><font class="textDesc">
              <input type="checkbox" name="DocumentAttached" id="DocumentAttached" />
              Insurance Certificate
            </font></td>
          </tr>
          <tr>
            <td colspan="2" align="left"><%=DisplayUtil.displayInputTextBox("Regis_no",form1ModelSP.getRegis_no(),"") %></td>
            <td align="left"><font class="textDesc">
              <input type="checkbox" name="DocumentAttached" id="DocumentAttached" />
              Letter of Credit
            </font></td>
          </tr>
          <tr>
            <td colspan="2">&nbsp;</td>
            <td align="left"><font class="textDesc">
              <input type="checkbox" name="DocumentAttached" id="DocumentAttached" />
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
            <td align="left"><font class="textDesc"><%=DisplayUtil.displayInputTextBox("cus_name_code",form1ModelSP.getCus_name_code(),"") %>
             
            </font></td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td align="left"><font class="textDesc">Description 
              </font></td>
            <td align="left"><font class="textDesc"><%=DisplayUtil.displayInputTextBox("cus_name_desc",form1ModelSP.getCus_name_desc(),"") %>
              
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
            <td colspan="2" align="left"><%=DisplayUtil.displayInputTextBox("ManifestNo",form1ModelSP.getManifest_no(),"") %></td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td colspan="2">&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td colspan="3" align="left"><font class="textDescBold">15. Receip of Duty/Tax as Levied Authorized by :</font></td>
            </tr>
          <tr>
            <td align="center" colspan="2"><%=DisplayUtil.displayInputTextBox("duty_tax_receipt_date",DisplayFormatUtil.SQLDateToString1(form1ModelSP.getDuty_tax_receipt_date(),"DD/MM/YYYY"),"") %></td>
            <td align="center"><%=DisplayUtil.displayInputTextBox("duty_tax_receipt_desc",form1ModelSP.getDuty_tax_receipt_desc(),"") %></td>
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
            <td align="left"><%=DisplayUtil.displayInputTextBox("Consignee_code",form1ModelSP.getConsignee_code(),"") %></td>
            </tr>
          <tr>
            <td align="right"><font class="textDesc">Name </font></td>
            <td align="left"><%=DisplayUtil.displayInputTextBox("Consignee_name",form1ModelSP.getConsignee_name(),"") %></td>
            </tr>
          <tr>
            <td align="right"><font class="textDesc">Address </font></td>
            <td align="left"><%=DisplayUtil.displayInputTextAreaTag("Consignee_address",form1ModelSP.getConsignee_address(),"") %></td>
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
            <td align="left"><%=DisplayUtil.displayInputTextBox("AuthorAgent_code",form1ModelSP.getAuthorAgent_code(),"") %></td>
          </tr>
          <tr>
            <td align="right"><font class="textDesc">Name </font></td>
            <td align="left"><%=DisplayUtil.displayInputTextBox("AuthorAgent_name",form1ModelSP.getAuthorAgent_name(),"") %></td>
          </tr>
          <tr>
            <td align="right"><font class="textDesc">Address </font></td>
            <td align="left"><%=DisplayUtil.displayInputTextAreaTag("AuthorAgent_address",form1ModelSP.getAuthorAgent_address(),"") %></td>
          </tr>
        </table></td>
        <td rowspan="4" align="center"><table width="472" border="0" cellpadding="1" cellspacing="1">
          <tr>
            <td width="170" align="left"><font class="textDescBold">16.Import permit   No.</font></td>
            <td colspan="2" align="left"><font class="textDescBold">17. Exchange Control Ref.</font></td>
            </tr>
          <tr>
            <td align="left"><%=DisplayUtil.displayInputTextBox("import_permit_no",form1ModelSP.getImport_permit_no(),"") %></td>
            <td align="left" colspan="2"><%=DisplayUtil.displayInputTextBox("exchg_ctrl_ref",form1ModelSP.getExchg_ctrl_ref(),"") %></td>
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
            <td align="left" ><%=DisplayUtil.displayInputTextBox("special_treatment",form1ModelSP.getSpecial_treatment(),"") %></td>
            <td colspan="2" align="left" ><%=DisplayUtil.displayInputTextBox("bill_no",form1ModelSP.getBill_no(),"") %></td>
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
            <td align="left" ><%=DisplayUtil.displayInputTextBox("term_payment",form1ModelSP.getTerm_payment(),"") %></td>
            <td colspan="2" align="left" ><%=DisplayUtil.displayInputTextBox("country_of_good",form1ModelSP.getCountry_of_good(),"") %></td>
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
            <td align="left" ><%=DisplayUtil.displayInputTextBox("exchange_rate",form1ModelSP.getExchgRate_ID(),"") %></td>
            <td colspan="2" align="left" ><%=DisplayUtil.displayInputTextBox("fob_value",form1ModelSP.getFob_value(),"") %></td>
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
            <td align="left" ><%=DisplayUtil.displayInputTextBox("Insurance",form1ModelSP.getInsurance(),"") %></td>
            <td align="left" ><%=DisplayUtil.displayInputTextBox("Freight",form1ModelSP.getFreight(),"") %></td>
            <td align="left" ><%=DisplayUtil.displayInputTextBox("Cif_value",form1ModelSP.getCif_value(),"") %></td>
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
            <td align="left" ><%=DisplayUtil.displayInputTextBox("Gross_weight",form1ModelSP.getGross_weight(),"") %></td>
            <td align="left" ><%=DisplayUtil.displayInputTextBox("Measurement",form1ModelSP.getMeasurement(),"") %></td>
            <td align="left" ><%=DisplayUtil.displayInputTextBox("Other_charg",form1ModelSP.getOther_charg(),"") %></td>
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
            <td width="100" align="left"><font class="textDesc"><input type="radio" name="radio" id="TransportMode" value="Sea" />
              Sea</font></td>
            <td width="150" align="left"><font class="textDesc"><input type="radio" name="radio" id="TransportMode2" value="Sea" />
              Rail</font></td>
            
            </tr>
          <tr>
            <td align="left"><font class="textDesc"><input type="radio" name="radio" id="TransportMode5" value="Sea" />
              Road</font></td>
            <td align="left"><font class="textDesc"><input type="radio" name="radio" id="TransportMode6" value="Sea" />
              Air</font></td>
            
            </tr>
          <tr>
            <td align="left"><font class="textDesc"><input type="radio" name="radio" id="TransportMode3" value="Sea" />
              Pipe</font></td>
            <td align="left"><font class="textDesc"><input type="radio" name="radio" id="TransportMode4" value="Sea" />
              Other(specify)
              </td>
            
            </tr>
          <tr>
            <td align="left"></td>
            <td align="left"><input type="text" name="TMOther" id="TMOther" /></td>
            
            </tr>
          </table></td>
        <td ><table border="0" cellspacing="1" cellpadding="1">
          <tr>
            <td><font class="textDescBold">5. Date of Import</font></td>
            
            </tr>
          <tr>
            
            <td><%=DisplayUtil.displayInputTextBox("Date_Import",DisplayFormatUtil.SQLDateToString1(form1ModelSP.getDate_Import(),"DD/MM/YYYY"),"") %></td>
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
            <td><input type="text" name="VesselName" id="VesselName" /></td>
          </tr>
        </table></td>
        <td><table border="0" cellspacing="1" cellpadding="1">
          <tr>
            <td colspan="2"><font class="textDescBold">7. Port/Place of Import</font></td>
            </tr>
          <tr>
            <td><font class="textDesc">Code </font></td>
            <td><%=DisplayUtil.displayInputTextBox("PortImport_Code",form1ModelSP.getPortImport_Code(),"") %></td>
            </tr>
          <tr>
            <td><font class="textDesc">Descriptiont </font></td>
            <td><%=DisplayUtil.displayInputTextBox("PortImport_Desc",form1ModelSP.getPortImport_Desc(),"") %></td>
            </tr>
        </table></td>
        </tr>
      <tr>
        <td><table border="0" cellspacing="1" cellpadding="1">
          <tr>
            <td colspan="2"><font class="textDescBold">8.Port/Place of   Loading</font></td>
          </tr>
          <tr>
            <td><font class="textDesc">Code </font></td>
            <td><%=DisplayUtil.displayInputTextBox("PortLoad_Code",form1ModelSP.getPortLoad_Code(),"") %></td>
          </tr>
          <tr>
            <td><font class="textDesc">Descriptiont </font></td>
            <td><%=DisplayUtil.displayInputTextBox("PortLoad_Desc",form1ModelSP.getPortLoad_Desc(),"") %></td>
          </tr>
        </table></td>
        <td><table border="0" cellspacing="1" cellpadding="1">
          <tr>
            <td colspan="2"><font class="textDescBold">9. Via (Transhipment Cargo Only)</font></td>
            </tr>
          <tr>
            <td><font class="textDesc">Code </font></td>
            <td><%=DisplayUtil.displayInputTextBox("Via_Code",form1ModelSP.getVia_Code(),"") %></td>
            </tr>
          <tr>
            <td><font class="textDesc">Descriptiont </font></td>
            <td><%=DisplayUtil.displayInputTextBox("Via_Desc",form1ModelSP.getVia_Desc(),"") %></td>
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
          	<td><input type="button" name = "InsertPackage" value="Insert" onclick="addPackageTabJS();"></td>
            <td><font class="textHeader"><input type="button" name = "DeletePackage" value="Delete" ></font></td>
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
          
        </table></td>
        </tr>
      <tr>
        <td colspan="3"><table id ="qualityBaseTab" border="0" cellspacing="1" cellpadding="1">
          <tr >
            <td><font class="textHeader"><input type="button" name = "InsertQualityBase" value="Insert" onclick="addQualityBaseTabJS();"></font></td>
            <td><font class="textHeader"><input type="button" name = "DeleteQualityBase" value="Delete" onclick="deleteQualityBaseTabJS();"></font></td>
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
                <td colspan="2"><input type="text" name="DeclarantName" id="DeclarantName" /></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td colspan="2"><font class="textDescBold">49. Identity Card/Passport No.</font></td>
              </tr>
              <tr>
                <td colspan="2"><input type="text" name="IDCard" id="IDCard" /></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td colspan="2"><font class="textDescBold">50. Status</font></td>
              </tr>
              <tr>
                <td><font class="textDescBold"><input type="radio" name="radio" id="Status" value="Active" />
                  Active</font></td>
                <td><font class="textDescBold"><input type="radio" name="radio" id="Status" value="Inactive" />
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
                <td colspan="2"><input type="text" name="Cerify" id="Cerify" /></td>
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
                <td colspan="2"><textarea name="RemoveFromCus" id="RemoveFromCus" cols="45" rows="5"></textarea></td>
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
                <td><input type="text" name="TotalDutyTax" id="TotalDutyTax" /></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td><font class="textDescBold">54. Other Charges</font></td>
                <td><input type="text" name="OtherCharges" id="OtherCharges" /></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td><font class="textDescBold">55. Total Amount Payable</font></td>
                <td><input type="text" name="TotalAmountPaya" id="TotalAmountPaya" /></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td colspan="2"><font class="textDescBold">56. Manualscript Recerpt No.(If applicable)</font></td>
                </tr>
              <tr>
                <td colspan="2"><textarea name="ManualRecerpt" id="ManualRecerpt" cols="45" rows="5"></textarea></td>
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
            <td align="center"><textarea name="InstrucExamination" id="InstrucExamination" cols="45" rows="5"></textarea></td>
            <td align="center"><textarea name="ResultExamnation" id="ResultExamnation" cols="45" rows="5"></textarea></td>
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
            <td align="center"><textarea name="OtherUse" id="OtherUse" cols="100" rows="5"></textarea></td>
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
      	<%
			String disable = "";
      		if (null!=form_action && !"EJDAM010".equals(form_action)){
      			disable = "disabled = \"disabled\"";
      		}
      	%>
        <td colspan="3" align="center"><!-- <input type="button" name="Save" id="Save" <%//=disable %>value="  Save  " onclick="validateSaveButton(this.form,'<%=form_action %>')"/>-->
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

</form>
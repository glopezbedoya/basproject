<%@page import="com.tcd.ejda.model.Form2Model"%>
<%@page import="com.ejda.sessionBean.Form2Bean"%>
<%@page import="com.ejda.util.DisplayFormatUtil"%>
<%@page import="com.tcd.ejda.model.ValueListModel"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="java.util.Vector"%>
<script language="javascript" src="js/ejdaform2.js"></script>
<form name="ejdaformNo2" method="post" action="/EJDAWeb/EJDAControler">
<%
	Logger log = Logger.getLogger("JspLog");
	Form2Bean form2Bean = (Form2Bean)request.getSession().getAttribute("form2Bean");
	log.debug("form2Bean :: " + form2Bean);
	Form2Model form2ModelSP = form2Bean.getForm2ModelSP();
	Vector form2Vt = form2Bean.getForm2Vt();
	Form2Model form2M = new Form2Model();
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
	<input type="hidden" name="page" value="<%=valueListM.getAtPage() %>" />
	<input type="hidden" name="volumePerPage" value="<%=valueListM.getItemsPerPage() %>" />
	
	

<%
 String responseMessage = (String) request.getSession().getAttribute("responseMessage");
 	log.debug("responseMessage 1 -> " + responseMessage);
	if(!"".equalsIgnoreCase(responseMessage) && null != responseMessage){
%> <script language="javascript">
			jQuery(document).ready(function(){
				displayResponseMessage("<%=responseMessage%>");
				return false;
			});
		</script> <%
			request.getSession().removeAttribute("responseMessage");
	}
	/* end responseMessage to user */
%>

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
            <td align="left"><input type="text" name="ExportTaxCode" id="ExportTaxCode" />
            </td>
          </tr>
          <tr>
            <td align="right"><font class="textDesc">Name </font></td>
            <td align="left"><input type="text" name="ExportTaxName" id="ExportTaxName" size="40" /></td>
          </tr>
          <tr>
            <td align="right"><font class="textDesc">Address </font></td>
            <td align="left"><textarea name="ExportAddress" id="ExportAddress" cols="45" rows="5"></textarea></td>
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
              <input type="text" name="ReceiptDate" id="ReceiptDate" />
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
            <td colspan="2" align="left"><input type="text" name="RegisterNo" id="RegisterNo" /></td>
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
            <td align="left"><font class="textDesc">
              <input type="text" name="CusNameCode" id="CusNameCode" />
            </font></td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td align="left"><font class="textDesc">Description 
              </font></td>
            <td align="left"><font class="textDesc">
              <input type="text" name="CusNameDesc" id="CusNameDesc" />
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
            <td colspan="3" align="left"><font class="textDescBold">15. Receip of Duty/Tax as Levied Authorized by :</font></td>
            </tr>
          <tr>
            <td align="center" colspan="2"><input type="text" name="AuthorDate" id="AuthorDate" /></td>
            <td align="center"><input type="text" name="ProperOffice" id="ProperOffice" /></td>
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
            <td align="left"><input type="text" name="ImporterTaxCode" id="ImporterTaxCode" /></td>
            </tr>
          <tr>
            <td align="right"><font class="textDesc">Name </font></td>
            <td align="left"><input type="text" name="ImporterTaxName" id="ImporterTaxName" size="40" /></td>
            </tr>
          <tr>
            <td align="right"><font class="textDesc">Address </font></td>
            <td align="left"><textarea name="ImporterTaxAddress" id="ImporterTaxAddress" cols="45" rows="5"></textarea></td>
            </tr>
          </table></td>
      </tr>
      <tr>
        <td ><table border="0" cellspacing="1" cellpadding="1">
          <tr>
            <td colspan="2"><font class="textDescBold">4.Mode of   Transport </font></td>
            </tr>
          <tr>
            <td width="25%"><font class="textDesc"><input type="radio" name="radio" id="TransportMode" value="Sea" />
              Sea</font></td>
            <td width="75%"><font class="textDesc"><input type="radio" name="radio" id="TransportMode2" value="Sea" />
              Rail</font></td>
            
            </tr>
          <tr>
            <td><font class="textDesc"><input type="radio" name="radio" id="TransportMode5" value="Sea" />
              Road</font></td>
            <td><font class="textDesc"><input type="radio" name="radio" id="TransportMode6" value="Sea" />
              Air</font></td>
            
            </tr>
          <tr>
            <td><font class="textDesc"><input type="radio" name="radio" id="TransportMode3" value="Sea" />
              Pipe</font></td>
            <td><font class="textDesc"><input type="radio" name="radio" id="TransportMode4" value="Sea" />
              Other(specify)
              </td>
            
            </tr>
          <tr>
            <td></td>
            <td><input type="text" name="TMOther" id="TMOther" /></td>
            
            </tr>
          </table></td>
        <td ><table border="0" cellspacing="1" cellpadding="1">
          <tr>
            <td><font class="textDescBold">5. Date of Import</font></td>
            
            </tr>
          <tr>
            
            <td><input type="text" name="ImportDate" id="ImportDate" /></td>
            </tr>
          </table>
          </td>
        <td rowspan="3" align="center"><table width="472" border="0" cellpadding="1" cellspacing="1">
          <tr>
            <td width="170" colspan="2" align="left"><font class="textDescBold">15.Special Treatment</font></td>
            <td width="159" align="left"><font class="textDescBold">16.Export Permit No.</font></td>
            <td align="left"><font class="textDescBold">17.Exchange Control Ref.</font></td>
            </tr>
          <tr>
            <td colspan="2" align="left"><input type="text" name="ImportPermitNo" id="ImportPermitNo" /></td>
            <td align="left"><input type="text" name="ExchgCtrlRef" id="ExchgCtrlRef" /></td>
            <td align="left"><input type="text" name="ExchgCtrlRef2" id="ExchgCtrlRef2" /></td>
            </tr>
          <tr>
            <td colspan="2">&nbsp;</td>
            <td colspan="2">&nbsp;</td>
            </tr>
          <tr>
            <td colspan="2" align="left" ><font class="textDescBold">18.Country of Origin</font></td>
            <td colspan="2" align="left" ><font class="textDescBold">19. Bill of Lading or comsignment Note No.</font></td>
            </tr>
          <tr>
            <td align="left" ><font class="textDesc">Code </font>             </td>
            <td align="left" ><input type="text" name="SpecialTreatment" id="SpecialTreatment" /></td>
            <td align="right" ><font class="textDesc">Code</font></td>
            <td align="left" ><input type="text" name="BillLadingNote" id="BillLadingNote" /></td>
            </tr>
          <tr>
            <td align="left"><font class="textDesc">Desc</font></td>
            <td align="left"><input type="text" name="SpecialTreatment2" id="SpecialTreatment2" /></td>
            <td align="right"><font class="textDesc">Desc</font></td>
            <td align="left"><input type="text" name="BillLadingNote2" id="BillLadingNote2" /></td>
            </tr>
          <tr>
            <td colspan="2" align="left" >&nbsp;</td>
            <td colspan="2" align="left" >&nbsp;</td>
          </tr>
          <tr>
            <td colspan="2" align="left" ><font class="textDescBold">20.Terms of Delivery and Payment</font></td>
            <td align="left" ><font class="textDescBold">21.Currency Code</font></td>
            <td align="left" ><font class="textDescBold">22.Amount Recrived/to be Received</font></td>
            </tr>
          <tr>
            <td colspan="2" align="left" ><input type="text" name="TermDelivePayment" id="TermDelivePayment" /></td>
            <td align="left" ><input type="text" name="CountryPayment" id="CountryPayment" /></td>
            <td align="left" ><input type="text" name="CountryPayment2" id="CountryPayment2" /></td>
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
            <td colspan="2" align="left" ><input type="text" name="ExchangeRate" id="ExchangeRate" /></td>
            <td align="left" ><input type="text" name="FOBValue" id="FOBValue" /></td>
            <td align="left" ><input type="text" name="FOBValue2" id="FOBValue2" /></td>
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
            <td align="left" ><input type="text" name="ExchangeRate2" id="ExchangeRate2" /></td>
            <td align="left" >&nbsp;</td>
            <td align="left" ><input type="text" name="ExchangeRate4" id="ExchangeRate4" /></td>
          </tr>
          <tr>
            <td align="left" ><font class="textDesc">Descriptiont</font></td>
            <td align="left" ><input type="text" name="ExchangeRate3" id="ExchangeRate3" /></td>
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
            <td colspan="2" align="left" ><input type="text" name="ExchangeRate3" id="ExchangeRate3" /></td>
            <td align="left" ><input type="text" name="FOBValue3" id="FOBValue3" /></td>
            <td align="left" ><input type="text" name="FOBValue3" id="FOBValue4" /></td>
          </tr>
          <tr>
            <td colspan="2">&nbsp;</td>
            <td colspan="2">&nbsp;</td>
          </tr>
        </table></td>
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
            <td><input type="text" name="PortImportCode" id="PortImportCode" /></td>
            </tr>
          <tr>
            <td><font class="textDesc">Descriptiont </font></td>
            <td><input type="text" name="PortImportDesc" id="PortImportDesc" /></td>
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
            <td><input type="text" name="PortLoadCode" id="PortLoadCode" /></td>
          </tr>
          <tr>
            <td><font class="textDesc">Descriptiont </font></td>
            <td><input type="text" name="PortLoadDesc" id="PortLoadDesc" /></td>
          </tr>
        </table></td>
        <td><table border="0" cellspacing="1" cellpadding="1">
          <tr>
            <td colspan="2"><font class="textDescBold">9. Via (Transhipment Cargo Only)</font></td>
            </tr>
          <tr>
            <td><font class="textDesc">Code </font></td>
            <td><input type="text" name="ViaCode" id="ViaCode" /></td>
            </tr>
          <tr>
            <td><font class="textDesc">Descriptiont </font></td>
            <td><input type="text" name="ViaDesc" id="ViaDesc" /></td>
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
            <td><font class="textHeader"><input type="button" name = "DeletePackage" value="Delete" onclick="deletePackageTabJS();"></font></td>
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
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td colspan="2"><font class="textDescBold">50. Identity Card/Passport No.</font></td>
              </tr>
              <tr>
                <td colspan="2"><input type="text" name="IDCard" id="IDCard" /></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td colspan="2"><font class="textDescBold">51. Status</font></td>
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
                <td colspan="2"><font class="textDescBold">52. I cerify that this declaration is true and complete.</font></td>
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
                <td colspan="2"><font class="textDescBold">53. Removal from Customs Control authorized by</font></td>
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
                <td colspan="2"><font class="textDescBold">57. Manualscript Recerpt No.(If applicable)</font></td>
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
	
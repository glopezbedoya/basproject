<%@page import="com.ejda.sessionBean.Form4Bean"%>
<%@page import="com.ejda.util.DisplayFormatUtil"%>
<%@page import="com.tcd.ejda.model.ValueListModel"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="java.util.Vector"%>
<%@page import="com.tcd.ejda.model.Form1Model"%>
<%@page import="com.ejda.util.DisplayUtil"%>


<%@page import="com.ejda.util.LoadCacheData"%>
<%@page import="com.tcd.ejda.model.FormDetail1Model"%>
<%@page import="com.tcd.ejda.model.FormDetail2Model"%><script language="javascript" src="js/ejdaform4.js"></script>
<form name="ejdaformNo1" method="post" action="/EJDAWeb/EJDAControler">
<%
	Logger log = Logger.getLogger("JspLog");
	Form4Bean form4Bean = (Form4Bean)request.getSession().getAttribute("form4Bean");
	log.debug("form4Bean :: " + form4Bean);
	Form1Model form4ModelSP = form4Bean.getForm4ModelSP();
	Vector form4Vt = form4Bean.getForm4Vt();
	Form1Model form1M = new Form1Model();
	String form_action = (String)form4Bean.getActionName();
	String form_no = (String)request.getSession().getAttribute("form_no");
	
	String bgColor1 = "bordercolor=\"#F4F4F4\"";
	String bgColor2 = "bgcolor=\"#DFEFFF\"";
	ValueListModel valueListM = form4Bean.getValueListM();
	
	if(null == valueListM) valueListM = new ValueListModel();
		
	%>
	<input type="hidden" name="ejdaAction" value=""> 
	<input type="hidden" name="ejdaMethod" value=""> 
	<input type="hidden" name="screenName" value="">
	<input type="hidden" name="screenName" value="">
	<input type="hidden" name="actionName" value="">
	<input type="hidden" name="form_no" value="<%=form_no %>">
	<input type="hidden" name="doc_id" value="<%=form4ModelSP.getDoc_ID() %>">
	<input type="hidden" name="page" value="<%=valueListM.getAtPage() %>" />
	<input type="hidden" name="volumePerPage" value="<%=valueListM.getItemsPerPage() %>" />
	
	
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td align="center"><table width="800" border="0" cellspacing="1" cellpadding="1" bgcolor="#F8F8F8">
      <tr>
        <td width="50%" colspan="2" align="center"><table border="0" cellspacing="1" cellpadding="1">
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
            <td align="left"><%=DisplayUtil.displayInputTextBox("consignorExportCode",form4ModelSP.getConsignor_code(),"") %>
            </td>
          </tr>
          <tr>
            <td align="right"><font class="textDesc">Name </font></td>
            <td align="left"><%=DisplayUtil.displayInputTextBox("consignorExportName",form4ModelSP.getConsignor_name(),"") %></td>
          </tr>
          <tr>
            <td align="right"><font class="textDesc">Address </font></td>
            <td align="left"><%=DisplayUtil.displayInputTextAreaTag("consignorExportAddress",form4ModelSP.getConsignor_address(),"") %></textarea></td>
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
              <%=DisplayUtil.displayInputTextBox("Date_Receipt",DisplayFormatUtil.SQLDateToString1(form4ModelSP.getDate_Receipt(),"DD/MM/YYYY"),"") %>
           </td>
            <td align="left"><font class="textDesc"><%=DisplayUtil.displayCheckBoxForDocAttach(form4Bean.docAttachMVt,"doc_attach","INVOICE","") %> 
              Invoice</font></td>
          </tr>
          <tr>
            <td colspan="2">&nbsp;</td>
            <td align="left"><font class="textDesc">
              <%=DisplayUtil.displayCheckBoxForDocAttach(form4Bean.docAttachMVt,"doc_attach","BILL OF LADING","") %>
              Bill of Lading
            </font></td>
          </tr>
          <tr>
            <td colspan="2" align="left"><font class="textDescBold">12.Registration   Number</font></td>
            <td align="left"><font class="textDesc">
              <%=DisplayUtil.displayCheckBoxForDocAttach(form4Bean.docAttachMVt,"doc_attach","INSURANCE CERT","") %>
              Insurance Certificate
            </font></td>
          </tr>
          <tr>
            <td colspan="2" align="left"><input type="text" name="RegisterNo" id="RegisterNo" /></td>
            <td align="left"><font class="textDesc">
              <%=DisplayUtil.displayCheckBoxForDocAttach(form4Bean.docAttachMVt,"doc_attach","LETTER OF CREDIT","") %>
              Letter of Credit
            </font></td>
          </tr>
          <tr>
            <td colspan="2">&nbsp;</td>
            <td align="left"><font class="textDesc">
              <%=DisplayUtil.displayCheckBoxForDocAttach(form4Bean.docAttachMVt,"doc_attach","OTHER","") %>
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
              <%=DisplayUtil.displayInputTextBox("cus_name_code",form4ModelSP.getCus_name_code(),"") %> 
            </font></td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td align="left"><font class="textDesc">Description 
              </font></td>
            <td align="left"><font class="textDesc">
              <%=DisplayUtil.displayInputTextBox("cus_name_desc",form4ModelSP.getCus_name_desc(),"") %>
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
            <td colspan="2" align="left"><%=DisplayUtil.displayInputTextBox("ManifestNo",(form4ModelSP.getManifest_no() != null)?form4ModelSP.getManifest_no().toString():"","maxlength=4 onkeypress=\"keyPressInteger()\"") %></td>
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
            <td align="center" colspan="2"><%=DisplayUtil.displayInputTextBox("duty_tax_receipt_date",DisplayFormatUtil.SQLDateToString1(form4ModelSP.getDuty_tax_receipt_date(),"DD/MM/YYYY"),"maxlength=10 onkeypress=\"addSlashFormat(event,this)\" onblur=\"checkDateLengthYear(this,'','1800','2300')\"") %></td>
            <td align="center"><%=DisplayUtil.displayInputTextBox("duty_tax_receipt_desc",form4ModelSP.getDuty_tax_receipt_desc(),"maxlength=50") %></td>
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
            <td align="left"><%=DisplayUtil.displayInputTextBox("AuthorAgent_code",form4ModelSP.getAuthorAgent_code(),"") %></td>
          </tr>
          <tr>
            <td align="right"><font class="textDesc">Name </font></td>
            <td align="left"><%=DisplayUtil.displayInputTextBox("AuthorAgent_name",form4ModelSP.getAuthorAgent_name(),"") %></td>
          </tr>
          <tr>
            <td align="right"><font class="textDesc">Address </font></td>
            <td align="left"><%=DisplayUtil.displayInputTextAreaTag("AuthorAgent_address",form4ModelSP.getAuthorAgent_address(),"") %></td>
          </tr>
        </table></td>
        <td rowspan="3" align="center"><table width="472" border="0" cellpadding="1" cellspacing="1">
          <tr>
            <td align="left" ><font class="textDescBold">15.Special Treatment</font></td>
            <td width="159" align="left" ><font class="textDescBold">16.Export Pemit No.</font></td>
            <td width="133" align="left" ><font class="textDescBold">17.Exchange Control Ref.</font></td>
            </tr>
          <tr>
            <td align="left" ><%=DisplayUtil.displayInputTextBox("special_treatment",form4ModelSP.getSpecial_treatment(),"maxlength=20") %></td>
            <td align="left" ><%=DisplayUtil.displayInputTextBox("import_permit_no",form4ModelSP.getImport_permit_no(),"maxlength=20") %></td>
            <td align="left" ><%=DisplayUtil.displayInputTextBox("exchg_ctrl_ref",form4ModelSP.getExchg_ctrl_ref(),"maxlength=20") %></td>
            </tr>
          <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            </tr>
          <tr>
          	<td colspan="3"><table width="472" border="0" cellpadding="1" cellspacing="1">
	          	<tr>
		          	<td align="left" ><font class="textDescBold">18.Country of Origin</font></td>
		            <td align="center" ><font class="textDescBold">Code</font></td>
		            <td align="left" ><font class="textDescBold">19.Country of Final Destination</font></td>
		            <td align="center" ><font class="textDescBold">Code</font></td>
	            </tr>
	          	<tr>
		            <td align="left" ><%=DisplayUtil.displayInputTextBox("country_origin_desc",form4ModelSP.getCountry_origin_desc(),"") %></td>
		            <td align="left" ><%=DisplayUtil.displayInputTextBox("country_origin_code",form4ModelSP.getCountry_origin_code(),"size=4") %></td>
		            <td align="left" ><%=DisplayUtil.displayInputTextBox("country_final_desc",form4ModelSP.getCountry_final_desc(),"") %></td>
		            <td align="left" ><%=DisplayUtil.displayInputTextBox("country_final_code",form4ModelSP.getCountry_final_code(),"size=4") %></td>
	            </tr>
          	</table></td>
          	</tr>
          	 <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            </tr>
            <tr>
          	<td colspan="3"><table width="472" border="0" cellpadding="1" cellspacing="1">
	          	<tr>
		          	<td align="left" ><font class="textDescBold">20.Terms of Delivery and Payment</font></td>
		          	<td align="center" ><font class="textDescBold">21.Currency Code</font></td>
		            <td align="center" ><font class="textDescBold">Code</font></td>
		            <td align="left" ><font class="textDescBold">22.Amount recrived/to be Received</font></td>
	            </tr>
	          	<tr>
		            <td align="left" ><%=DisplayUtil.displayInputTextBox("term_payment",form4ModelSP.getTerm_payment(),"maxlength=20") %></td>
		            <td align="left" ><%=DisplayUtil.displayInputTextBox("cur_code",form4ModelSP.getCur_code(),"size=4") %></td>
		            <td align="left" ><%=DisplayUtil.displayInputTextBox("cur_code",form4ModelSP.getCur_code(),"size=4") %></td>
		            <td align="left" ><%=DisplayUtil.displayInputTextBox("amtRecrived",String.valueOf(form4ModelSP.getReceiveAmt()),"") %></td>
	            </tr>
          	</table></td>
          	</tr>
          	 <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            </tr>
          	<tr>
            <td align="left" ><font class="textDescBold">23.Exchange rate</font></td>
            <td width="159" align="left" ><font class="textDescBold">24.Equivalent</font></td>
            <td width="133" align="left" ><font class="textDescBold">25.Insurance</font></td>
            </tr>
          <tr>
            <td align="left" ><%=DisplayUtil.displayInputTextBox("exchange_rate",form4ModelSP.getExchgRate_ID(),"maxlength=4") %></td>
            <td align="left" ><%=DisplayUtil.displayInputTextBox("Equivalent",form4ModelSP.getEquivalent(),"") %></td>
            <td align="left" ><%=DisplayUtil.displayInputTextBox("Insurance",form4ModelSP.getInsurance(),"") %></td>
            </tr>
          <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            </tr>
            <tr>
            <td align="left" colspan="2"><font class="textDescBold">26.Payment for Goods Received/to be Received</font></td>
            <td width="133" align="left" ><font class="textDescBold">27.Fregil</font></td>
            </tr>
          <tr>
            <td align="left" colspan="2"><%=DisplayUtil.displayInputTextBox("good_payment_desc",form4ModelSP.getGood_payment_desc(),"") %>
            					<%=DisplayUtil.displayInputTextBox("good_payment_code",form4ModelSP.getGood_payment_code(),"size=3") %></td>
            <td align="left" ><%=DisplayUtil.displayInputTextBox("Freight",form4ModelSP.getFreight(),"") %></td>
            </tr>
          <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            </tr>
            <tr>
            <td align="left" ><font class="textDescBold">28.Gross Wt.(kg.)</font></td>
            <td width="159" align="left" ><font class="textDescBold">29.Measurement</font></td>
            <td width="133" align="left" ><font class="textDescBold">30.FOB Value</font></td>
            </tr>
          <tr>
            <td align="left" ><%=DisplayUtil.displayInputTextBox("Gross_weight",form4ModelSP.getGross_weight(),"maxlength=20") %></td>
            <td align="left" ><%=DisplayUtil.displayInputTextBox("Measurement",form4ModelSP.getMeasurement(),"maxlength=20") %></td>
            <td align="left" ><%=DisplayUtil.displayInputTextBox("fob_value",form4ModelSP.getFob_value(),"") %></td>
            </tr>
          <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            </tr>
          <tr>
            <td>&nbsp;</td>
            <td colspan="2">&nbsp;</td>
            </tr>
        </table></td>
      </tr>
      <tr>
        <td ><table border="0" cellspacing="1" cellpadding="1">
          <tr>
            <td colspan="2"><font class="textDescBold">4.Mode of   Transport </font></td>
            </tr>
          <tr>
            <td width="100" align="left"><font class="textDesc"><%=DisplayUtil.displayRadioTag(form4ModelSP.getMode_Trans(),"mode_trans","1") %>
              Sea</font></td>
            <td width="150" align="left"><font class="textDesc"><%=DisplayUtil.displayRadioTag(form4ModelSP.getMode_Trans(),"mode_trans","2") %>
              Rail</font></td>
            
            </tr>
          <tr>
            <td align="left"><font class="textDesc"><%=DisplayUtil.displayRadioTag(form4ModelSP.getMode_Trans(),"mode_trans","3") %>
              Road</font></td>
            <td align="left"><font class="textDesc"><%=DisplayUtil.displayRadioTag(form4ModelSP.getMode_Trans(),"mode_trans","4") %>
              Air</font></td>
            
            </tr>
          <tr>
            <td align="left"><font class="textDesc"><%=DisplayUtil.displayRadioTag(form4ModelSP.getMode_Trans(),"mode_trans","5") %>
              Pipe</font></td>
            <td align="left"><font class="textDesc"><%=DisplayUtil.displayRadioTag(form4ModelSP.getMode_Trans(),"mode_trans","6") %>
              Other(specify)
              </td>
            
            </tr>
          <tr>
            <td align="left"></td>
            <td align="left"><%=DisplayUtil.displayInputTextBox("trans_other",form4ModelSP.getTrans_Other(),"") %></td>
            
            </tr>
          </table></td>
        <td ><table border="0" cellspacing="1" cellpadding="1">
          <tr>
            <td><font class="textDescBold">5. Date of Import</font></td>
            
            </tr>
          <tr>
            
            <td><%=DisplayUtil.displayInputTextBox("Date_Import",DisplayFormatUtil.SQLDateToString1(form4ModelSP.getDate_Import(),"DD/MM/YYYY"),"maxlength=10 onkeypress=\"addSlashFormat(event,this);keyPressInteger();\" onblur=\"checkDateLengthYear(this,'','1800','2300')\"") %></td>
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
            <td><%=DisplayUtil.displayInputTextBox("vessel_value",form4ModelSP.getVessel_value(),"maxlength=50") %></td>
          </tr>
        </table></td>
        <td><table border="0" cellspacing="1" cellpadding="1">
          <tr>
            <td colspan="2"><font class="textDescBold">7. Port/Place of Import</font></td>
            </tr>
          <tr>
            <td><font class="textDesc">Code </font></td>
            <td><%=DisplayUtil.displaySelectTag(LoadCacheData.GetCountryCache(),form4ModelSP.getPortLoad_Code(),"PortImport_Code","EDIT","") %></td>
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
            <td><%=DisplayUtil.displaySelectTag(LoadCacheData.GetCountryCache(),form4ModelSP.getPortLoad_Code(),"PortLoad_Code","EDIT","") %></td>
          </tr>
        </table></td>
        <td><table border="0" cellspacing="1" cellpadding="1">
          <tr>
            <td colspan="2"><font class="textDescBold">9. Via (Transhipment Cargo Only)</font></td>
            </tr>
          <tr>
            <td><font class="textDesc">Code </font></td>
            <td><%=DisplayUtil.displaySelectTag(LoadCacheData.GetCountryCache(),form4ModelSP.getPortLoad_Code(),"Via_Code","EDIT","") %></td>
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
          <% Vector vtDetail1 = form4Bean.getDetail1MVt();
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
          <% Vector vtDetail2 = form4Bean.getDetail2MVt();
	        if(vtDetail1 != null && vtDetail1.size() > 0){
	        	for(int i=0;i<vtDetail2.size();i++){
	        		FormDetail2Model formDetail2M = (FormDetail2Model)vtDetail2.get(i); 
	        %>
          <tr bgcolor="#FFFFFF">
          	<td width="50"><input type="checkbox" name="checkall1" id="checkall1" /></td>
            <td width="100"><%=DisplayUtil.displayInputTextBox("QA_ITEM_NO",formDetail2M.getItem_no(),"","10") %></td>
            <td width="150"><%=DisplayUtil.displayInputTextBox("QB_UNIT",String.valueOf(formDetail2M.getQty_cust_unit()),"","10") %></td>
            <td width="200"><%=DisplayUtil.displayInputTextBox("FOB_ACTUAL",formDetail2M.getUnit_val_actual(),"","10") %></td>
            <td width="200"><%=DisplayUtil.displayInputTextBox("FOB_CUSTOM",formDetail2M.getUnit_val_custom(),"","10") %></td>
            <td width="100"><%=DisplayUtil.displayInputTextBox("TOTAL_VALUE",String.valueOf(formDetail2M.getTotal_value()),"","10") %></td>
          	<td width="100"><%=DisplayUtil.displayInputTextBox("DUTY_RATE",String.valueOf(formDetail2M.getExport_rate()),"","10") %></td>
          	<td width="100"><%=DisplayUtil.displayInputTextBox("DUTY_AMOUNT",String.valueOf(formDetail2M.getExport_amount()),"","10") %></td>
          	<td width="100"><%=DisplayUtil.displayInputTextBox("TAX_TYPE",formDetail2M.getOther_tax_type(),"","10") %></td>
          	<td width="100"><%=DisplayUtil.displayInputTextBox("TAX_RATE",String.valueOf(formDetail2M.getOther_tax_rate()),"","10") %></td>
          	<td width="100"><%=DisplayUtil.displayInputTextBox("TAX_AMOUNT",String.valueOf(formDetail2M.getOther_tax_amount()),"","10") %></td>
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
                <td colspan="2"><%=DisplayUtil.displayInputTextBox("declarant_name",form4ModelSP.getDeclarant_name(),"maxlength=20") %></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td colspan="2"><font class="textDescBold">49. Identity Card/Passport No.</font></td>
              </tr>
              <tr>
                <td colspan="2"><%=DisplayUtil.displayInputTextBox("id_card_no",form4ModelSP.getId_card_no(),"maxlength=20") %></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td colspan="2"><font class="textDescBold">50. Status</font></td>
              </tr>
              <tr>
                <td><font class="textDescBold"><%=DisplayUtil.displayRadioTag(form4ModelSP.getStatus(),"Status","A") %>
                  Active</font></td>
                <td><font class="textDescBold"><%=DisplayUtil.displayRadioTag(form4ModelSP.getStatus(),"Status","I") %>
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
                <td colspan="2"><%=DisplayUtil.displayInputTextBox("cerify",form4ModelSP.getCerify(),"maxlength=20") %></td>
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
                <td colspan="2"><%=DisplayUtil.displayInputTextAreaTag("cus_removal",form4ModelSP.getCus_removal(),"maxlength=255") %></td>
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
                <td><%=DisplayUtil.displayInputTextBox("tax_total",String.valueOf(form4ModelSP.getTax_total()),"maxlength=\"15\" onkeypress = \"keypressWithDegit(this,'15')\" onblur=\"formatCurrency(this)\"") %></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td><font class="textDescBold">54. Other Charges</font></td>
                <td><%=DisplayUtil.displayInputTextBox("Other_charg2",String.valueOf(form4ModelSP.getOther_charg2()),"maxlength=\"15\" onkeypress = \"keypressWithDegit(this,'15')\"onblur=\"formatCurrency(this)\"") %></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td><font class="textDescBold">55. Total Amount Payable</font></td>
                <td><%=DisplayUtil.displayInputTextBox("payable_amount",String.valueOf(form4ModelSP.getPayable_amount()),"maxlength=\"15\" onkeypress = \"keypressWithDegit(this,'15')\"onblur=\"formatCurrency(this)\"") %></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td colspan="2"><font class="textDescBold">56. Manualscript Recerpt No.(If applicable)</font></td>
                </tr>
              <tr>
                <td colspan="2"><%=DisplayUtil.displayInputTextAreaTag("manualscript_recerpt",form4ModelSP.getManualscript_recerpt(),"maxlength=255") %></td>
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
            <td align="center"><%=DisplayUtil.displayInputTextAreaTag("instruct_exam",form4ModelSP.getInstruct_exam(),"maxlength=255") %></td>
            <td align="center"><%=DisplayUtil.displayInputTextAreaTag("result_exam",form4ModelSP.getResult_exam(),"maxlength=255") %></td>
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
            <td align="center"><%=DisplayUtil.displayInputTextAreaTag("for_other_use",form4ModelSP.getFor_other_use(),"maxlength=255") %></td>
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
      		if (null!=form_action && !"EJDAM013".equals(form_action)){
      			disable = "disabled = \"disabled\"";
      		}
      	%>
        <td colspan="3" align="center"><!--  <input type="button" name="Save" id="Save" <%//=disable %>value="  Save  " onclick="validateSaveButton(this.form,'<%//=form_action %>')"/>-->
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
<%@page import="com.tcd.ejda.model.Form1Model"%>
<%@page import="com.ejda.sessionBean.Form1Bean"%>
<%@page import="com.ejda.util.DisplayFormatUtil"%>
<%@page import="com.tcd.ejda.model.ValueListModel"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="java.util.Vector"%>

<%@page import="com.ejda.util.DisplayUtil"%>
<%@page import="com.tcd.ejda.model.Form1Model"%>
<%@page import="com.tcd.ejda.model.FormDetail1Model"%>
<%@page import="com.tcd.ejda.model.FormDetail2Model"%>
<%@page import="com.ejda.util.LoadCacheData"%>
<script language="javascript" src="js/ejdaform3.js"></script>

<script language="javascript" src="js/ejdaScript.js"></script>
<form name="ejdaformNo1" method="post" action="/EJDAWeb/EJDAControler">
<%
	Logger log = Logger.getLogger("JspLog");
	Form1Bean form3Bean = (Form1Bean)request.getSession().getAttribute("form1Bean");
	log.debug("form3Bean :: " + form3Bean);
	Form1Model form3ModelSP = form3Bean.getForm1ModelSP();
	Vector form3Vt = form3Bean.getForm1Vt();
	Form1Model form3M = new Form1Model();
	String form_action = (String)form3Bean.getActionName();
	String form_no = (String)request.getSession().getAttribute("form_no");
	String showName = "";
	
	if (null!=form_action && "EJDAM010".equals(form_action)){
		showName ="Table 1 : Form no. 3";
	
	}else if (null!=form_action && "EJDAM014".equals(form_action)){
		showName ="Table 2 : Form no. 3";
	}else if (null!=form_action && "EJDAM018".equals(form_action)){
		showName ="Table 3 : Form no. 3";
	}else if (null!=form_action && "EJDAM022".equals(form_action)){
		showName ="Table 4 : Form no. 3";
	}
	
	String bgColor1 = "bordercolor=\"#F4F4F4\"";
	String bgColor2 = "bgcolor=\"#DFEFFF\"";
	ValueListModel valueListM = form3Bean.getValueListM();
	
	if(null == valueListM) valueListM = new ValueListModel();
	
	
	%>
	<input type="hidden" name="ejdaAction" value=""> 
	<input type="hidden" name="ejdaMethod" value=""> 
	<input type="hidden" name="screenName" value="">
	<input type="hidden" name="screenName" value="">
	<input type="hidden" name="actionName" value="">
	<input type="hidden" name="form_no" value="3">
	<input type="hidden" name="doc_id" value="<%=form3ModelSP.getDoc_ID() %>">
	<input type="hidden" name="page" value="<%=valueListM.getAtPage() %>" />
	<input type="hidden" name="volumePerPage" value="<%=valueListM.getItemsPerPage() %>" />
	
	
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td align="center"><table width="800" border="0" cellspacing="1" cellpadding="1" bgcolor="#F8F8F8">
      <tr>
        <td width="50%" colspan="2" align="center"><table border="0" cellspacing="1" cellpadding="1">
        <tr>
            <td colspan="2" align="left" class="textDesc" >Form 3 >> <%=showName %></td>
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
            <td align="left"><%=DisplayUtil.displayInputTextBox("consignorExportCode",form3ModelSP.getConsignor_code(),"") %>
            </td>
          </tr>
          <tr>
            <td align="right"><font class="textDesc">Name </font></td>
            <td align="left"><%=DisplayUtil.displayInputTextBox("consignorExportName",form3ModelSP.getConsignor_name(),"") %></td>
          </tr>
          <tr>
            <td align="right"><font class="textDesc">Address </font></td>
            <td align="left"><%=DisplayUtil.displayInputTextAreaTag("consignorExportAddress",form3ModelSP.getConsignor_address(),"") %></td>
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
            <td  align="left"><font class="textDescBold">10.date and Time of   Receipt</font></td>
            <td align="left"><font class="textDescBold">11.Documents   Attached</font></td>
          </tr>
          <tr>
            <td  align="left">
              <%=DisplayUtil.displayInputTextBox("Date_Receipt",DisplayFormatUtil.SQLDateToString1(form3ModelSP.getDate_Receipt(),"DD/MM/YYYY"),"") %>
           </td>
            <td align="left"><font class="textDesc"><%=DisplayUtil.displayCheckBoxForDocAttach(form3Bean.docAttachMVt,"doc_attach","INVOICE","") %> 
              Invoice</font></td>
          </tr>
          <tr>
            <td >&nbsp;</td>
            <td align="left"><font class="textDesc">
              <%=DisplayUtil.displayCheckBoxForDocAttach(form3Bean.docAttachMVt,"doc_attach","BILL OF LADING","") %>
              Bill of Lading
            </font></td>
          </tr>
          <tr>
            <td  align="left"><font class="textDescBold">12.Registration Number</font></td>
            <td align="left"><font class="textDesc">
              <%=DisplayUtil.displayCheckBoxForDocAttach(form3Bean.docAttachMVt,"doc_attach","INSURANCE CERT","") %>
              Insurance Certificate
            </font></td>
          </tr>
          <tr>
            <td  align="left"><%=DisplayUtil.displayInputTextBox("Regis_no",form3ModelSP.getRegis_no(),"maxlength=50") %></td>
            <td align="left"><font class="textDesc">
              <%=DisplayUtil.displayCheckBoxForDocAttach(form3Bean.docAttachMVt,"doc_attach","LETTER OF CREDIT","") %>
              Letter of Credit
            </font></td>
          </tr>
          <tr>
            <td >&nbsp;</td>
            <td align="left"><font class="textDesc">
              <%=DisplayUtil.displayCheckBoxForDocAttach(form3Bean.docAttachMVt,"doc_attach","OTHER","") %>
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
              <%=DisplayUtil.displayInputTextBox("cus_name_code",form3ModelSP.getCus_name_code(),"") %>
            </font></td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td align="left"><font class="textDesc">Description 
              </font></td>
            <td align="left"><font class="textDesc">
              <%=DisplayUtil.displayInputTextBox("cus_name_desc",form3ModelSP.getCus_name_desc(),"") %>
            </font></td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td colspan="2">&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td align="left"><font class="textDescBold">14.Movement Pemit No.(if applicable)</font></td>
            <td align="left"><font class="textDescBold">15.Date of Expiry</font></td>
          </tr>
          <tr>
          	<td><%=DisplayUtil.displayInputTextBox("moveMentPemit",form3ModelSP.getMovementPemitNo(),"") %></td>
          	<td><%=DisplayUtil.displayInputTextBox("dateOfExpiry",DisplayFormatUtil.SQLDateToString1(form3ModelSP.getExpiryDate(),"DD/MM/YYYY"),"") %></td>
          </tr>
          <tr>
            <td align="left"><font class="textDescBold">16.Security Ref. No(if applicable)</font></td>
            <td align="left"><font class="textDescBold">17.Amount of Security</font></td>
          </tr>
          <tr>
          	<td><%=DisplayUtil.displayInputTextBox("securityRefNo",form3ModelSP.getSecurityRefNo(),"") %></td>
          	<td><%=DisplayUtil.displayInputTextBox("amtOfSecurity",String.valueOf(form3ModelSP.getSecurityAmt()),"") %></td>
          </tr>
          <tr>
           	<td align="left"><font class="textDescBold">18.Amount Received/To Be Received</font></td>
            <td align="left"><font class="textDescBold">19.Bill of Lading/Consignment Note No.</font></td>
          </tr>
          <tr>
          	<td><%=DisplayUtil.displayInputTextBox("amtRecrived",String.valueOf(form3ModelSP.getReceiveAmt()),"") %></td>
          	<td><%=DisplayUtil.displayInputTextBox("billOfLading",form3ModelSP.getBillOfLading(),"") %></td>
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
            <%=DisplayUtil.displaySelectTag_Code(LoadCacheData.GetImporterCache(),form3ModelSP.getConsignee_code(),"Consignee_code","EDIT","", "onchange=\"getImportAddress(this.value)\"" ) %></td>
            </tr>
          <tr>
            <td align="right"><font class="textDesc">Name </font></td>
            <td align="left"><%=DisplayUtil.displayInputTextBox("Consignee_name",form3ModelSP.getConsignee_name(),"") %></td>
            </tr>
          <tr>
            <td align="right"><font class="textDesc">Address </font></td>
            <td align="left"><%=DisplayUtil.displayInputTextAreaTag("Consignee_address",form3ModelSP.getConsignee_address(),"") %></td>
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
            <%=DisplayUtil.displaySelectTag_Code(LoadCacheData.GetAgentCache(),form3ModelSP.getAuthorAgent_code(),"AuthorAgent_code","EDIT","", "onchange=getAgentAddress(this.value)") %></td>
          </tr>
          <tr>
            <td align="right"><font class="textDesc">Name </font></td>
            <td align="left"><%=DisplayUtil.displayInputTextBox("AuthorAgent_name",form3ModelSP.getAuthorAgent_name(),"") %></td>
          </tr>
          <tr>
            <td align="right"><font class="textDesc">Address </font></td>
            <td align="left"><%=DisplayUtil.displayInputTextAreaTag("AuthorAgent_address",form3ModelSP.getAuthorAgent_address(),"") %></td>
          </tr>
        </table></td>
        <td rowspan="3" valign="top" align="center"><table width="472" border="0" cellpadding="1" cellspacing="1">
          <tr>
            <td align="left" ><font class="textDescBold">20.FOB Value</font></td>
            <td width="159" align="left" ><font class="textDescBold">21.Insueance</font></td>
            <td width="133" align="left" ><font class="textDescBold">22.Freight</font></td>
            </tr>
          <tr>
            <td align="left" ><%=DisplayUtil.displayInputTextBox("fob_value",form3ModelSP.getFob_value(),"") %></td>
            <td align="left" ><%=DisplayUtil.displayInputTextBox("Insurance",form3ModelSP.getInsurance(),"") %></td>
            <td align="left" ><%=DisplayUtil.displayInputTextBox("Freight",form3ModelSP.getFreight(),"") %></td>
            </tr>
          <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            </tr>
          <tr>
            <td align="left" ><font class="textDescBold">23.Geoss Wt.(Kg)</font></td>
            <td align="left" ><font class="textDescBold">24.Measurement(m3)</font></td>
            <td align="left" ><font class="textDescBold">25.CIF Value</font></td>
            </tr>
          <tr>
            <td align="left" ><%=DisplayUtil.displayInputTextBox("Gross_weight",form3ModelSP.getGross_weight(),"") %></td>
            <td align="left" ><%=DisplayUtil.displayInputTextBox("Measurement",form3ModelSP.getMeasurement(),"") %></td>
            <td align="left" ><%=DisplayUtil.displayInputTextBox("Cif_value",form3ModelSP.getCif_value(),"") %></td>
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
            <td width="100" align="left"><font class="textDesc"><%=DisplayUtil.displayRadioTag(form3ModelSP.getMode_Trans(),"mode_trans","1") %>
              Sea</font></td>
            <td width="150" align="left"><font class="textDesc"><%=DisplayUtil.displayRadioTag(form3ModelSP.getMode_Trans(),"mode_trans","2") %>
              Rail</font></td>
            
            </tr>
          <tr>
            <td align="left"><font class="textDesc"><%=DisplayUtil.displayRadioTag(form3ModelSP.getMode_Trans(),"mode_trans","3") %>
              Road</font></td>
            <td align="left"><font class="textDesc"><%=DisplayUtil.displayRadioTag(form3ModelSP.getMode_Trans(),"mode_trans","4") %>
              Air</font></td>
            
            </tr>
          <tr>
            <td align="left"><font class="textDesc"><%=DisplayUtil.displayRadioTag(form3ModelSP.getMode_Trans(),"mode_trans","5") %>
              Pipe</font></td>
            <td align="left"><font class="textDesc"><%=DisplayUtil.displayRadioTag(form3ModelSP.getMode_Trans(),"mode_trans","6") %>
              Other(specify)
              </td>
            
            </tr>
          <tr>
            <td align="left"></td>
            <td align="left"><%=DisplayUtil.displayInputTextBox("trans_other",form3ModelSP.getTrans_Other(),"") %></td>
            
            </tr>
          </table></td>
        <td ><table border="0" cellspacing="1" cellpadding="1">
          <tr>
            <td><font class="textDescBold">5. Date of Import</font></td>
            
            </tr>
          <tr>
            
            <td><%=DisplayUtil.displayInputTextBox("Date_Import",DisplayFormatUtil.SQLDateToString1(form3ModelSP.getDate_Import(),"DD/MM/YYYY"),"") %></td>
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
            <td><%=DisplayUtil.displayInputTextBox("vessel_value",form3ModelSP.getVessel_value(),"") %></td>
          </tr>
        </table></td>
        <td><table border="0" cellspacing="1" cellpadding="1">
          <tr>
            <td colspan="2"><font class="textDescBold">7. Port/Place of Import</font></td>
            </tr>
          <tr>
            <td><font class="textDesc"></font></td>
            <td><%=DisplayUtil.displaySelectTag(LoadCacheData.GetCountryCache(),form3ModelSP.getPortImport_Code(),"PortImport_Code","EDIT","") %></td>
            </tr>
          <tr>
            <td><font class="textDesc"></font></td>
            <td><%//=DisplayUtil.displayInputTextBox("PortImport_Desc",form3ModelSP.getPortImport_Desc(),"") %></td>
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
            <td><%=DisplayUtil.displaySelectTag(LoadCacheData.GetCountryCache(),form3ModelSP.getPortLoad_Code(),"PortLoad_Code","EDIT","") %></td>
          </tr>
          <tr>
            <td><font class="textDesc"></font></td>
            <td><%//=DisplayUtil.displayInputTextBox("PortLoad_Desc",form3ModelSP.getPortLoad_Desc(),"") %></td>
          </tr>
        </table></td>
        <td><table border="0" cellspacing="1" cellpadding="1">
          <tr>
            <td colspan="2"><font class="textDescBold">9. Via (Transhipment Cargo Only)</font></td>
            </tr>
          <tr>
            <td><font class="textDesc"></font></td>
            <td><%=DisplayUtil.displaySelectTag(LoadCacheData.GetCountryCache(),form3ModelSP.getVia_Code(),"Via_Code","EDIT","") %></td>
            </tr>
          <tr>
            <td><font class="textDesc"></font></td>
            <td><%//=DisplayUtil.displayInputTextBox("Via_Desc",form3ModelSP.getVia_Desc(),"") %></td>
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
          	<td><input type="button" id = "InsertPackage" name = "InsertPackage" value="Insert" onclick="addPackageTabJS();"></td>
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
           <% Vector vtDetail1 = form3Bean.getDetail1MVt();
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
            <td width="100"><%=DisplayUtil.displaySelectTag_Code(form3Bean.getTanliCodeVt(), formDetail1M.getCust_code(), "CODE_NO", "EDIT", "","") %></td>
          	<td width="100"><%=DisplayUtil.displaySelectTag(form3Bean.getUnitVt(), formDetail1M.getCust_unit(), "UNIT", "EDIT", "") %></td>
           </tr>
           <%	}
       		 }%>
          
        </table></td>
        </tr>
        
      <tr>
        <td colspan="3"><table id ="qualityBaseTab" border="0" cellspacing="1" cellpadding="1" align="center">
          <tr >
            <td><font class="textHeader"><input type="button" id = "InsertQualityBase"  name = "InsertQualityBase" value="Insert" onclick="addQualityBaseTabJS();"></font></td>
            <td><font class="textHeader"><input type="button"id = "DeleteQualityBase"  name = "DeleteQualityBase" value="Delete" onclick="removeRowFromTable('qualityBaseTab')"></font></td>
            <td><font class="textHeader"></font></td>
            <td><font class="textHeader"></font></td>
            <td><font class="textHeader"></font></td>
            <td><font class="textHeader"></font></td>
            <td><font class="textHeader"></font></td>
          </tr>
          <tr bgcolor="#0066FF">
            <td rowspan="2"width="50"><font class="textHeader"><input type="checkbox" name="checkall2" id="checkall2" /></td>
            <td rowspan="2"width="100"><font class="textHeader">Item No.</font></td>
            <td rowspan="2"width="100"><font class="textHeader">Country of OriGin Code</font></td>
            <td rowspan="2"width="100"><font class="textHeader">Quantity Based on Customs tariff Unit</font></td>
            <td colspan="2"width="200"><font class="textHeader">Value</font></td>
            </tr>
          <tr bgcolor="#0066FF">
            <td><font class="textHeader">Per Unit</font></td>
            <td><font class="textHeader">Total</font></td>
          </tr>
          <% Vector vtDetail2 = form3Bean.getDetail2MVt();
	        if(vtDetail2 != null && vtDetail2.size() > 0){
	        	for(int i=0;i<vtDetail2.size();i++){
	        		FormDetail2Model formDetail2M = (FormDetail2Model)vtDetail2.get(i); 
	        %>
          <tr bgcolor="#FFFFFF">
          	<td width="50"><input type="checkbox" name="checkall1" id="checkall1" /></td>
            <td width="100"><%=DisplayUtil.displayInputTextBox("QA_ITEM_NO",formDetail2M.getItem_no(),"","10") %></td>
            <td width="100"><%=DisplayUtil.displaySelectTag_Code(form3Bean.getCountryOriginVt(), formDetail2M.getOriginCode(), "ORIGIN_CODE", "EDIT", "","") %></td>
            <td width="100"><%=DisplayUtil.displayInputTextBox("QB_UNIT",Double.toString(formDetail2M.getQty_cust_unit()),"","10") %></td>
            <td width="100"><%=DisplayUtil.displayInputTextBox("VALUE_PER_UNIT",Double.toString(formDetail2M.getValuePerUnit()),"","10") %></td>
            <td width="100"><%=DisplayUtil.displayInputTextBox("VALUE_TOTAL",Double.toString(formDetail2M.getTotal_value()),"","10") %></td>
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
                <td colspan="2"><font class="textDescBold">36. Name of Declarant</font></td>
              </tr>
              <tr>
                <td colspan="2"><%=DisplayUtil.displayInputTextBox("DeclarantName",form3ModelSP.getDeclarant_name(),"") %></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td colspan="2"><font class="textDescBold">37. Identity Card/Passport No.</font></td>
              </tr>
              <tr>
                <td colspan="2"><%=DisplayUtil.displayInputTextBox("id_card_no",form3ModelSP.getId_card_no(),"") %></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td colspan="2"><font class="textDescBold">38. Status</font></td>
              </tr>
              <tr>
                <td><font class="textDescBold"><%=DisplayUtil.displayRadioTag(form3ModelSP.getStatus(),"Status","A") %>
                  Active</font></td>
                <td><font class="textDescBold"><%=DisplayUtil.displayRadioTag(form3ModelSP.getStatus(),"Status","I") %>
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
                <td colspan="2"><%=DisplayUtil.displayInputTextBox("cerify",form3ModelSP.getCerify(),"") %></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
            </table></td>
            <td><table width="312" border="0" cellpadding="1" cellspacing="1">
              <tr>
                <td colspan="2"><font class="textDescBold">39.To Proper Office of Customs at:</font></td>
                </tr>
              <tr>
                <td colspan="2"><%=DisplayUtil.displayInputTextAreaTag("properOffice",form3ModelSP.getProperOffice(),"") %></td>
                </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
            </table></td>
            <td><table width="320" border="0" cellpadding="1" cellspacing="1">
              <tr>
                <td colspan="2" align="center"><font class="textDescBold">FOR OFFICE   USE</font></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td colspan="2"><font class="textDescBold">40. Request Approved:</font></td>
              </tr>
              <tr>
              	<td colspan="2"><%=DisplayUtil.displayInputTextAreaTag("requestApproved",form3ModelSP.getRequestApproved(),"") %></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td colspan="2"><font class="textDescBold">41. Certified that the above goods have been duty moved Removal from customs control is aulhoried</font></td>
              </tr>
              <tr>
              	<td colspan="2"><%=DisplayUtil.displayInputTextAreaTag("certified",form3ModelSP.getCertified(),"") %></td>
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
            <td align="center"><%=DisplayUtil.displayInputTextAreaTag("instruct_exam",form3ModelSP.getInstruct_exam(),"") %></td>
            <td align="center"><%=DisplayUtil.displayInputTextAreaTag("result_exam",form3ModelSP.getResult_exam(),"") %></td>
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
            <td align="center"><%=DisplayUtil.displayInputTextAreaTag("for_other_use",form3ModelSP.getFor_other_use(),"") %></td>
          </tr>
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
            <td align="center"><%=DisplayUtil.displayInputTextAreaTag("remark",form3ModelSP.getRemark(),"maxlength=255") %></td>
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
      	%>
        <td colspan="3" align="center"><input type="button" name="Save" id="Save" <%=disable %>value="  Save  " onclick="validateSaveButton(this.form,'<%=form_action%>')"/>
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
<script type="text/javascript">
	readOnlyDeliver('<%=form_action %>');
	setReadOnlyDetail1('<%=form_action %>');
	setReadOnlyDetail2('<%=form_action %>');
</script>
<%@page contentType="text/html;charset=UTF-8"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="java.util.Vector"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="com.tcd.ejda.model.Form1Model"%>
<%@page import="com.ejda.sessionBean.Form1Bean"%>
<%@page import="com.ejda.util.DisplayFormatUtil"%>
<%@page import="com.tcd.ejda.model.ValueListModel"%>
<script language="javascript" src="js/EJDAM010.js"></script>
<%
	Logger log = Logger.getLogger("JspLog");
	Form1Bean form1Bean = (Form1Bean)request.getSession().getAttribute("form1Bean");
	log.debug("form1Bean :: " + form1Bean);
	Form1Model form1ModelSP = form1Bean.getForm1ModelCri();
	Vector form1Vt = form1Bean.getForm1Vt();
	Form1Model form1M = new Form1Model();
	String form_action = (String)form1Bean.getActionName();
	
	String bgColor1 = "bordercolor=\"#F4F4F4\"";
	String bgColor2 = "bgcolor=\"#DFEFFF\"";
	ValueListModel valueListM = form1Bean.getValueListM();
	
	if(null == valueListM) valueListM = new ValueListModel();
	
%>
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
<form name="ejdaformNo1" method="post" action="/EJDAWeb/EJDAControler">
	<input type="hidden" name="ejdaAction" value=""> 
	<input type="hidden" name="ejdaMethod" value=""> 
	<input type="hidden" name="screenName" value="">
	<input type="hidden" name="actionName" value="">
	<input type="hidden" name="page" value="<%=valueListM.getAtPage() %>" />
	<input type="hidden" id="form_no" name="form_no"value="">
	<input type="hidden" name="doc_id" value="">
	<input type="hidden" name="volumePerPage" value="<%=valueListM.getItemsPerPage() %>" />
	<table align="center" width="800" border="0" cellspacing="0" cellpadding="0">
		
        <tr align="left">
          <td align="left" class="style1" scope="row"><font class="textTop">Deliver >></font></td>
          <td>&nbsp;</td>
          
        </tr>
        <tr>
        	<td><table align="center" width="800" border="0" cellspacing="0" cellpadding="0">
        	
        
       		
       		<td height="20"></td>
       		<td class="text" align="right"> </td>
       		
        </tr>
        <tr>
       		<td align="left" class="style1" scope="row" width="200">
       			<span class="text">DOC ID </span>
       		
       		</td>
       		<td width="250" align="left"><%=DisplayFormatUtil.displayInputTextBox("txtDocID",form1ModelSP.getForm_name(),"") %>
       		<td width="200" align="left" class="style1" scope="row" width="200">
       			<span class="text">eJDA </span>
       		
       		</td>
       		<td align="left"><select id="jdaType" name="jdaType"><option value="">ALL</option>
       		<option value="1">EJDA NO. 1</option>
       		<option value="2">EJDA NO. 2</option>
       		<option value="3">EJDA NO. 3</option>
       		<option value="4">EJDA NO. 4</option>
       		</select></td>
         </tr>
          
        <tr>
       		<td align="left" class="style1" scope="row" width="200">
       			<span class="text">Exporter/Taxpayer Code </span>
       		
       		</td>
       		<td width="250" align="left"><%=DisplayFormatUtil.displayInputTextBox("txtConsignorCode",form1ModelSP.getConsignor_code(),"") %>
       		
       		</td>
       		<td width="200" align="left" class="style1" scope="row">	          		
       		
       			<span class="text"> Exporter/Taxpayer  name </span>
       		
       		<td align="left"><%=DisplayFormatUtil.displayInputTextBox("txtConsignorName",form1ModelSP.getConsignor_name(),"") %>
       		
       		</td>	          		
        </tr>
         <tr>
       		<td align="left" class="style1" scope="row" width="200">
       			<span class="text"> Consignee/Taxpayer Code </span>
       		
       		</td>
       		<td width="250" align="left"><%=DisplayFormatUtil.displayInputTextBox("txtConsigneeCode",form1ModelSP.getConsignee_code(),"") %>
       		
       		</td>
       		<td width="200" align="left" class="style1" scope="row">	          		
       		
       			<span class="text"> Consignee/Taxpayer Name </span>
       		
       		<td align="left"><%=DisplayFormatUtil.displayInputTextBox("txtConsigneeName",form1ModelSP.getConsignee_name(),"") %>
       		
       		</td>	          		
        </tr>
        <tr>
          	<td>
       		<span class="text">Date DOC </span>
       		
       		</td>
       		<td align="left" colspan="3"><%=DisplayFormatUtil.displayTextBoxCalendar("txtDocDateFrom",DisplayFormatUtil.SQLDateToString(form1ModelSP.getDate_Receipt_From())) %> - 
       		<%=DisplayFormatUtil.displayTextBoxCalendar("txtDocDateTo",DisplayFormatUtil.SQLDateToString(form1ModelSP.getDate_Receipt_To())) %> </td>
         </tr>
         <tr>
       		<td colspan="4" align="center" height="20"></td>	
         </tr>
        <tr>
        	
       		<td align="right"colspan="2"><%=DisplayFormatUtil.displayButton("Search","onclick=\"buttonAction(this.form,'EJDAM022','doSearch','EJDAM022.jsp')\"",false) %></td>
       		 <td colspan="2" align="left"><%=DisplayFormatUtil.displayButton("Reset","onclick=\"ResetForm()\"",false) %>
       		</td>        		
        </tr>
        <tr>
        	<td ></td>
        	<td></td>
       		
         </tr>
        <tr>
       		
       		<td height="20"></td>
       		<td class="text" align="right"> </td>
       		
        </tr>
        
         </table>
        	</td>
        </tr>
		<!--Panging-->
         <%
         log.debug("valueListM >> " + valueListM.getCount());
         log.debug("valueListM >> " + valueListM.getItemsPerPage());
			int allPage = valueListM.getCount() / valueListM.getItemsPerPage();
			int lastPage = (valueListM.getCount()/valueListM.getItemsPerPage());
			
			log.debug("allPage >> " + allPage);
			log.debug("lastPage >> " + lastPage);
			if ((valueListM.getCount()%valueListM.getItemsPerPage())>0)  {
				lastPage++;
			}
			if(allPage==0){
				allPage = 1;
			}
			String strFirstPage ="";
			
			if (valueListM.getAtPage()==1) {
				strFirstPage = "<strong><font class=\"text\">1</font></strong>";	
			} else {
				strFirstPage = "<a href=\"#\" onclick =\"changePageAndSize('1')\"><font class=\"text\">1</font></a>";
			}
			
			
			
			
			
			//TODO Pageing length
			//int lengthPage = 5; 
			//int lengthPage = Integer.parseInt(PropsUtil.get("limit.page.pagingsize.eaf").toString());
			String strScript = "";
			String strScriptBack = "";	
			String strFirst =  "<a href=\"#\" onclick =\"changePageAndSize('1')\"><font class=\"text\">First</font></a>";
			String strLast =  "<a href=\"#\" onclick =\"changePageAndSize('"+lastPage+"')\"><font class=\"text\">Last</font></a>";
			String btnFirst = "";
			String btnLast = "";
			String btnNext = "";
			String btnBack = "";
			
			if (allPage*valueListM.getItemsPerPage()  < valueListM.getCount()) { 
				allPage++;
			}
			
			if(valueListM.getAtPage() == allPage){
				btnLast = DisplayFormatUtil.displayButton("Last","",true);	
				btnNext = DisplayFormatUtil.displayButton("Next","",true);		
			}else if (allPage != valueListM.getAtPage() && valueListM.getAtPage() != 0)	{
				btnLast = DisplayFormatUtil.displayButton("Last","onclick=\"changePage("+allPage+",this.form)\"",false);
				btnNext = DisplayFormatUtil.displayButton("Next","onclick=\"changePage("+(valueListM.getAtPage()+1)+",this.form)\"",false);
			} else {
				btnLast = DisplayFormatUtil.displayButton("Last","",true);
				btnNext = DisplayFormatUtil.displayButton("Next","",true);
			}
			if(valueListM.getAtPage() == 1){
				btnFirst = 	DisplayFormatUtil.displayButton("First","",true);
				btnBack =  DisplayFormatUtil.displayButton("Back","",true); 	
			}else if (valueListM.getAtPage() != 1 && valueListM.getAtPage() != 0) {    
				btnFirst = 	DisplayFormatUtil.displayButton("First","onclick=\"changePage(1,this.form)\"",false);
				btnBack = DisplayFormatUtil.displayButton("Back","onclick=\"changePage("+(valueListM.getAtPage()-1)+",this.form)\"",false);			
			} else {	   
				btnFirst = DisplayFormatUtil.displayButton("First","",true);
				btnBack = DisplayFormatUtil.displayButton("Back","",true);	 
			}
			
			String showPage = DisplayFormatUtil.displaySelectPaging("selectPaging",allPage,valueListM.getAtPage(),"onchange=\"changeSelectPage(this.form)\"");
			%>
			 <%
            	if(form1Vt != null && form1Vt.size() > 0){%>
        <tr>
          <th colspan="4" scope="row"><div align="right"><span  class="textPage">&#3649;&#3626;&#3604;&#3591;&#3612;&#3621;&#3585;&#3634;&#3619;&#3588;&#3657;&#3609;&#3627;&#3634; <%=valueListM.getAtPage()+"/"+allPage %></span>
            <%=showPage %>
         	<%=btnFirst %>
         	<%=btnBack %>
         	<%=btnNext %>
         	<%=btnLast %>
          </div></th>
        </tr>
        <tr>
          <th scope="row">&nbsp;</th>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
       
        <tr>
          <th scope="row" align="right" class="textPage">จำนวนที่พบ<%=valueListM.getCount() %> รายการ</th>
          
         
        </tr>
       
         <tr>
          <th scope="row">&nbsp;</th>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td><div align="right">
          	<%=DisplayFormatUtil.displayButton("Delete","onclick=\"buttonAction(this.form,'doDelete')\"",false) %>
          </div></td>
        </tr>
        <tr>
          <th colspan="4" scope="row" align="center"><table width="951" border="0" align="center" cellpadding="0" cellspacing="1">
            <tr>
              <th width="20" bgcolor="#0099CC" class="style13" scope="row">
                <label>
                  <input type="checkbox" name="checkAllBox" id="checkAllBox" onclick="checkBoxAll()"/>
                </label>
              </th>
              <th bgcolor="#0099CC" class="textHeader" scope="row">Doc ID.</th>
              <td bgcolor="#0099CC" class="textHeader"><div align="center" class="textHeader">Consignor Name.</div></td>
              <td bgcolor="#0099CC" class="textHeader"><div align="center" class="textHeader">Doc Status</div></td>
              <td bgcolor="#0099CC" class="textHeader"><div align="center" class="textHeader">eJDA</div></td>
            </tr>
            <%
            	log.debug("bgColor fom1Vt.size() : " + form1Vt.size());
            		String bgColor;
            		for(int i=0;i<form1Vt.size();i++){
            			form1M = (Form1Model)form1Vt.get(i);            	
            			bgColor = (i%2 == 0)?bgColor1:bgColor2;
            			String showType = "eJDA " + form1M.getJDA_Type();
            			String action = "";
            			if("1".equals(form1M.getJDA_Type())){
            				//action = "EJDAM022";
            				page = "eJdaForm1.jsp";
            			}else if("2".equals(form1M.getJDA_Type())){
            				//action = "EJDAM023";
            				page = "eJdaForm2.jsp";
            			}else if("3".equals(form1M.getJDA_Type())){
            				//action = "EJDAM024";
            				page = "ejdaForm3.jsp";
            			}else {
            				//action = "EJDAM025";
            				page = "ejdaForm4.jsp";
            			}
            %>
			            <tr onclick="updateEJDATable2(this.form,'<%=form1M.getDoc_ID() %>','EJDAM022','<%=page%>');" style="cursor:hand">
			              <th <%=bgColor %> scope="row"><input type="checkbox" name="checkBox" id="checkBox" value="<%=form1M.getDoc_ID() %>"/></th>
			              <td <%=bgColor %> class="text"><%=form1M.getDoc_ID()%></td>
			              <td <%=bgColor %> class="text"><%=form1M.getConsignor_name()%></td>
			              <td <%=bgColor %> class="text"><%=form1M.getDoc_Status()%></td>
			              <td <%=bgColor %> class="text"><%=showType%></td>
			            </tr>
            <%		}
            	}
            %>

            <tr>
              <th scope="row">&nbsp;</th>
              <th scope="row">&nbsp;</th>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
             
            </tr>
          </table></th>
        </tr>
        <tr>
          <th scope="row">&nbsp;</th>
 			<td align="center"></td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
	
	</table>



</form>
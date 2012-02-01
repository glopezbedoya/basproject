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
<%@page import="java.text.NumberFormat" %>
<script language="javascript" src="js/ejdaform1.js"></script>
<script language="javascript" src="js/ejdaScript.js"></script>
<%
Logger log = Logger.getLogger("JspLog");
Form1Bean form1Bean = (Form1Bean)request.getSession().getAttribute("form1Bean");
log.debug("form1Bean :: " + form1Bean);
Form1Model form1ModelSP = form1Bean.getForm1ModelSP();

String form_action = (String)form1Bean.getActionName();
log.debug("form_action = " +form_action);
log.debug("form1ModelSP.getDoc_ID() = " + form1ModelSP.getDoc_ID());

ValueListModel valueListM = form1Bean.getValueListM();

if(null == valueListM) valueListM = new ValueListModel();

log.debug("valueListM.getAtPage() >> " + valueListM.getAtPage());
log.debug("valueListM.getItemsPerPage() >> " + valueListM.getItemsPerPage());

%>
<form name="ejdapayment" method="post" action="/EJDAWeb/EJDAControler">
	<input type="hidden" name="ejdaAction" value=""> 
	<input type="hidden" name="ejdaMethod" value=""> 
	<input type="hidden" name="screenName" value="">
	<input type="hidden" name="doc_id" value="<%=form1ModelSP.getDoc_ID() %>">
	<input type="hidden" name="page" value="<%=valueListM.getAtPage() %>" />
	<input type="hidden" name="volumePerPage" value="<%=valueListM.getItemsPerPage() %>" />
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
  	 <td align="center"><table width="800" border="0" cellspacing="1" cellpadding="1">
      <tr>
        <td align="left" ><font class="textDescBold">Payment &gt;&gt; </font></td>
      </tr>
      </table>
      </td>
  </tr>
  <tr>
    <td align="center"><table width="600" border="0" cellspacing="1" cellpadding="1">
      <tr>
        <td colspan="3"></td>
      </tr>
      <tr>
        <td colspan="3">&nbsp;</td>
      </tr>
      <tr>
        <td colspan="3">&nbsp;</td>
      </tr>
      <tr>
        <td align="left" width="250"><font class="textHeaderBlack"> Registration no./declaration no. </font></td>
        <td align="left" width="2"><font class="textHeaderBlack">: </font></td>
        <td align="left"width="350"><font class="textGreenPayment"> <%=form1ModelSP.getManifest_no()%></font></td>
      </tr>
      <tr>
        <td align="left"width="250"><font class="textHeaderBlack">Consignor/Consignee </font></td>
        <td align="left"width="2"><font class="textHeaderBlack">: </font></td>
        <td align="left"width="350"> <font class="textBlack"> <%=form1ModelSP.getConsignor_name() %></font></td>
      </tr>
       <tr>
        <td align="left"width="250"></td>
        <td align="left"width="2"><font class="textHeaderBlack"> </font></td>
        <td align="left"width="350"><font class="textBlack"><%=form1ModelSP.getConsignor_address() %></font></td>
      </tr>
      <tr>
        <td colspan="2"></td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td colspan="3" align="center"><table width="600" border="1" cellspacing="1" cellpadding="1" bordercolor="#CCCCCC" >
          <tr >
            <td bgcolor="#F8F8F8"><font class="textDescBold">รายการ</font></td>
            <td bgcolor="#F8F8F8"><font class="textDescBold">จำนวนเงิน (บาท)</font></td>
          </tr>
          <tr>
            <td align="left"><font class="textDesc">Tax Payable</font></td>
            <td align="right"><font class="textDesc"><%=DisplayFormatUtil.displayCommaNumber(form1ModelSP.getTax_total()) %></font></td>
          </tr>
          <tr>
            <td align="left"><font class="textDesc">Other Charges</font></td>
            <td align="right"><font class="textDesc"><%=DisplayFormatUtil.displayCommaNumber(form1ModelSP.getOther_charg()) %></font></td>
          </tr>
           <tr>
            <td align="left"><font class="textDesc">Total Amount Payable</font></td>
            <td align="right"><font class="textDesc"><%=DisplayFormatUtil.displayCommaNumber(form1ModelSP.getPayable_amount()) %></font></td>
          </tr>
          
          <%
          	double total=0;
          	total = form1ModelSP.getTax_total() + form1ModelSP.getOther_charg() + form1ModelSP.getPayable_amount();
          %>
          <tr>
            <td bgcolor="#F8F8F8" align="left"><font class="textDescBold">ผลรวม</font></td>
            <td bgcolor="#F8F8F8" align="right"><font class="textRed"><%=DisplayFormatUtil.displayCommaNumber(total) %></font></td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td colspan="3">&nbsp;</td>
      </tr>
      <tr>
        <td colspan="3">&nbsp;</td>
      </tr>
      <tr>
      <td colspan="3"> <table table width="600" border="0" cellspacing="1" cellpadding="1" bordercolor="#CCCCCC">
	       <tr>
	        <td width="150"align="left"><font class="textDescBold">วิธีการชำระเงิน :</font></td>
	        <td width="150" align="left"><font class="textDesc"><%=DisplayUtil.displayRadioTag(form1ModelSP.getFlag_payment(),"flag_payment","B") %>ชำระเงินผ่านธนาคาร</font></td>
	        <td width="300"align="left"><font class="textDesc"><%=DisplayUtil.displayRadioTag(form1ModelSP.getFlag_payment(),"flag_payment","C") %>ชำระเงินสด/เช็ค</font></td>
	      </tr>
      </table>
      
      </td>
      </tr>
     
      <tr>
        <td colspan="3">&nbsp;</td>
        </tr>
    </table></td>
  </tr>
  <tr>
  <td align="center">
  <table table width="800" border="0" cellspacing="1" cellpadding="1">
   <td colspan="3" align="center">
      <input type="button" name="Submit" id="Submit" value="  Submit  "  onclick="paymentSubmitButton(this.form,'<%=form_action %>')"/>
      <input type="button" name="Cancel" id="Cancel" value="  Cancel  " onclick="CancelButton(this.form,'<%=form_action %>')"/></td>
    </tr>
  </table>
  </td>
  </tr>
</table>
</form>
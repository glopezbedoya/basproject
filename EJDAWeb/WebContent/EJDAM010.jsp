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
	Form1Bean form1Bean = (Form1Bean)request.getSession().getAttribute("Form1Bean");
	log.debug("form1Bean :: " + form1Bean);
	Form1Model form1ModelSP = form1Bean.getForm1ModelSP();
	Vector form1Vt = form1Bean.getForm1Vt();
	Form1Model form1M = new Form1Model();
	String form_action = (String)form1Bean.getActionName();
	
	String bgColor1 = "bordercolor=\"#F4F4F4\"";
	String bgColor2 = "bgcolor=\"#DFEFFF\"";
	ValueListModel valueListM = form1Bean.getValueListM();
	
	if(null == valueListM) valueListM = new ValueListModel();
	
%>
<form name="ejdaformNo1" method="post" action="/EJDAWeb/EJDAControler">
	<input type="hidden" name="ejdaAction" value=""> 
	<input type="hidden" name="ejdaMethod" value=""> 
	<input type="hidden" name="screenName" value="">
	<input type="hidden" name="actionName" value="">
	<input type="hidden" name="form_no" value="">
	<input type="hidden" name="page" value="<%=valueListM.getAtPage() %>" />
	<input type="hidden" name="volumePerPage" value="<%=valueListM.getItemsPerPage() %>" />
	<table align="center" width="800" border="0" cellspacing="0" cellpadding="0">
		
        <tr align="left">
          <td align="left" class="style1" scope="row">TABLE 1</td>
          <td>&nbsp;</td>
          
        </tr>
        <tr>
        	<td><table align="center" width="800" border="0" cellspacing="0" cellpadding="0">
        	
        <tr>
       		
       		<td width="100"></td>
       		<td width="700"class="text" align="right"><%=DisplayFormatUtil.displayButton("AddForm1","onclick=\"buttonAction(this.form,'doAdd')\"",false) %> </td>
       		
        </tr>
        <tr>
       		
       		<td height="20"></td>
       		<td class="text" align="right"> </td>
       		
        </tr>
        <tr>
       		<td align="left"><span class="text">Form name </span></td>
       		<td align="left"><%=DisplayFormatUtil.displayInputTextBox("txtFormName",form1ModelSP.getForm_name(),"") %><%=DisplayFormatUtil.displayButton("Search","onclick=\"buttonAction(this.form,'doSearch')\"",false) %></td>
       		          		
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
          
        </tr>
        <tr>
          <th colspan="4" scope="row" align="center"><table width="951" border="0" align="center" cellpadding="0" cellspacing="1">
            <tr>
              <th width="20" bgcolor="#0099CC" class="style13" scope="row">
                <label>
                  <input type="checkbox" name="checkAllBox" id="checkAllBox" onclick="checkBoxAll()"/>
                </label>
              </th>
              <th bgcolor="#0099CC" class="textHeader" scope="row">Form No.</th>
              <td bgcolor="#0099CC" class="textHeader"><div align="center" class="textHeader">Form Name</div></td>
              <td bgcolor="#0099CC" class="textHeader"><div align="center" class="textHeader">Form Status</div></td>
            </tr>
            <%
            	log.debug("bgColor fom1Vt.size() : " + form1Vt.size());
            		String bgColor;
            		for(int i=0;i<form1Vt.size();i++){
            			form1M = (Form1Model)form1Vt.get(i);            	
            			bgColor = (i%2 == 0)?bgColor1:bgColor2;
            %>
            
			            <tr onclick="updateEJDATable2(this.form,'<%=form1M.getForm_no() %>');">
			              <th <%=bgColor %> scope="row"><input type="checkbox" name="checkBox" id="checkBox" value="<%=form1M.getForm_no() %>"/></th>
			              <td <%=bgColor %> class="text"><%=form1M.getForm_no()%></td>
			              <td <%=bgColor %> class="text"><%=form1M.getForm_name()%></td>
			              <td <%=bgColor %> class="text"><%=form1M.getForm_status()%></td>
			              
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
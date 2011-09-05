<%@page contentType="text/html;charset=UTF-8"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="java.util.Vector"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="com.tcd.ejda.model.TransactionLogModel"%>
<%@page import="com.ejda.sessionBean.TransactionLogBean"%>
<%@page import="com.ejda.util.DisplayFormatUtil"%>
<%@page import="com.tcd.ejda.model.ValueListModel"%>
<script language="javascript" src="js/EJDAM014.js"></script>
<%
	Logger log = Logger.getLogger("JspLog");
	TransactionLogBean tranLogBean = (TransactionLogBean)request.getSession().getAttribute("TransactionLogBean");
	TransactionLogModel tranLogModelSP = tranLogBean.getTranLogModelSP();
	Vector tranLogVt = tranLogBean.getTranLogVt();
	TransactionLogModel tranLogM = new TransactionLogModel();
	String bgColor1 = "bordercolor=\"#F4F4F4\"";
	String bgColor2 = "bgcolor=\"#DFEFFF\"";
	ValueListModel valueListM = tranLogBean.getValueListM();
	if(null == valueListM) valueListM = new ValueListModel();
%>
<form name="ejdaformNo1" method="post" action="/EJDAWeb/EJDAControler">
	<input type="hidden" name="ejdaAction" value=""> 
	<input type="hidden" name="ejdaMethod" value=""> 
	<input type="hidden" name="screenName" value="">
	<input type="hidden" name="page" value="<%=valueListM.getAtPage() %>" />
	<input type="hidden" name="volumePerPage" value="<%=valueListM.getItemsPerPage() %>" />
	<table align="center" width="800" border="0" cellspacing="0" cellpadding="0">
		
        <tr align="left">
          <td align="right" class="style1" scope="row">TABLE 2</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
        	<td><table align="center" width="800" border="0" cellspacing="0" cellpadding="0">
        	
        <tr>
       		<td align="right" class="style1" scope="row">
       			<span class="text"> Date From :</span>&nbsp;
       		
       		</td>
       		<td width="200">
       		</td>
       		<td class="text" width="70" align="right"> To : </td>
       		<td> 
       		</td>
        </tr>
        <tr>
       		<td align="right" class="style1" scope="row">
       			<span class="text"> User Name : </span>
       		
       		</td>
       		<td width="150">
       		</td>
       		<td align="right" class="style1" scope="row">	          		
       		
       			<span class="text"> Department : </span>
       		
       		</td>
       		<td >
       		</td>	          		
        </tr>
        <tr>
        	<td align="right" class="style1" scope="row">
       			<span class="text"> First Name :</span>
       		
       		</td>
       		<td >
       		</td>	          		
       		<td align="right" class="style1" scope="row">
       			<span class="text"> Last Name :</span>
       		
       		</td>
       		<td >
       			<input type = "button" value="Test"onClick="openForm1(this.form)">
       		</td>
        </tr>
        
        <tr>
       		<th colspan="4" align="center" class="style1" scope="row">
       		</th>
         </tr>
         </table>
        	</td>
        </tr>
		<!--Panging-->
         <%
			int allPage = valueListM.getCount() / valueListM.getItemsPerPage();
			int lastPage = (valueListM.getCount()/valueListM.getItemsPerPage());
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
            	if(tranLogVt != null && tranLogVt.size() > 0){%>
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
          <th colspan="4" scope="row"><table width="951" border="0" align="center" cellpadding="0" cellspacing="1">
            <tr>
              <th width="20" bgcolor="#0099CC" class="style13" scope="row">
                <label>
                  <input type="checkbox" name="checkAllBox" id="checkAllBox" onclick="checkBoxAll()"/>
                </label>
              </th>
              <th width="67" bgcolor="#0099CC" class="textHeader" scope="row">User Name</th>
              <td width="174" bgcolor="#0099CC" class="textHeader"><div align="center" class="textHeader">&#3594;&#3639;&#3656;&#3629; - &#3609;&#3634;&#3617;&#3626;&#3585;&#3640;&#3621; </div></td>
              <td width="154" bgcolor="#0099CC" class="textHeader"><div align="center" class="textHeader">หน่วยงาน</div></td>
              <td width="205" bgcolor="#0099CC" class="textHeader"><div align="center" class="textHeader">Action</div></td>
              <td width="135" bgcolor="#0099CC" class="textHeader"><div align="center" class="textHeader">เวลา</div></td>
              <td width="129" bgcolor="#0099CC" class="textHeader"><div align="center" class="textHeader">IP Address</div></td>
            </tr>
            <%
            		String bgColor;
            		for(int i=0;i<tranLogVt.size();i++){
            			tranLogM = (TransactionLogModel)tranLogVt.get(i);            	
            			bgColor = (i%2 == 0)?bgColor1:bgColor2;
            %>
			            <tr>
			              <th <%=bgColor %> scope="row"><input type="checkbox" name="checkBox" id="checkBox" value="<%=tranLogM.getTranId() %>"/></th>
			              <th <%=bgColor %> class="text" scope="row"><%=tranLogM.getUserName() %></th>
			              <td <%=bgColor %> class="text"><%=tranLogM.getFirstName() + " " + tranLogM.getLastName() %></td>
			              <td <%=bgColor %> class="text"><%=tranLogM.getDepartment() %></td>
			              <td <%=bgColor %> class="text"><div align="center" class="style17"><%=tranLogM.getTranAction() %></div></td>
			              <td <%=bgColor %> class="text"><div align="center"><%=tranLogM.getTranDate() %></div></td>
			              <td <%=bgColor %> class="text"><div align="center"><%=tranLogM.getIpAddress() %></div></td>
			            </tr>
            <%		}
            	}
            %>

            <tr>
              <th scope="row">&nbsp;</th>
              <th scope="row">&nbsp;</th>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
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
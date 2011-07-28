<%@page contentType="text/html;charset=UTF-8"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="java.util.Vector"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="com.tcd.ejda.model.TransactionLogModel"%>
<%@page import="com.ejda.sessionBean.TransactionLogBean"%>
<%@page import="com.ejda.util.DisplayUtil"%>
<%@page import="com.tcd.ejda.model.ValueListModel"%><script  type="text/javascript">
function buttonAction(form,action){
	$('input[name=ejdaAction]').val('TransactionLog');
	$('input[name=ejdaMethod]').val(action);
	$('input[name=screenName]').val('transactionLog.jsp');
	form.submit();
}

function changePage(page,form){
	$('input[name=ejdaAction]').val('TransactionLog');
	$('input[name=ejdaMethod]').val('doSearch');
	$('input[name=screenName]').val('transactionLog.jsp');
	$('input[name=page]').val(page);
	form.submit();
}
function changeSelectPage(form){
	$('input[name=ejdaAction]').val('TransactionLog');
	$('input[name=ejdaMethod]').val('doSearch');
	$('input[name=screenName]').val('transactionLog.jsp');
	$('input[name=page]').val($('select[name=selectPaging]').val());
	form.submit();
}
function checkBoxAll(){
	var check = $('input[name=checkAllBox]').attr('checked');
	$('input[name=checkBox]').each(function (){
		$(this).attr('checked',(check == 'checked')?true:false);
	});
}

</script>
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
<form name="transactionForm" method="post" action="/EJDAWeb/EJDAControler">
	<input type="hidden" name="ejdaAction" value=""> 
	<input type="hidden" name="ejdaMethod" value=""> 
	<input type="hidden" name="screenName" value="">
	<input type="hidden" name="page" value="<%=valueListM.getAtPage() %>" />
	<input type="hidden" name="volumePerPage" value="<%=valueListM.getItemsPerPage() %>" />
	<table align="center" width="800" border="0" cellspacing="0" cellpadding="0">
		<tr >
          <th colspan="4" align="left" bgcolor="#3399FF" scope="row"><div align="left"><span class="style1">&gt;&gt; &#3592;&#3633;&#3604;&#3585;&#3634;&#3619;&#3612;&#3641;&#3657;&#3651;&#3594;&#3657;&#3619;&#3632;&#3610;&#3610; &gt;&gt; &#3592;&#3633;&#3604;&#3585;&#3634;&#3619;&#3612;&#3641;&#3657;&#3651;&#3594;&#3657;&#3591;&#3634;&#3609;&#3619;&#3632;&#3610;&#3610;</span> </div></th>
        </tr>
        <tr align="left">
          <th scope="row">&nbsp;</th>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr align="left">
          <th colspan="4" scope="row">
            <p class="style1">วัน เดือน ปี ที่เข้าระบบ </p>
          </th>
        </tr>
        <tr>
       		<th align="right" class="style1" scope="row">
       			<span> Date From :</span>&nbsp;
       		</th>
       		<td>
       			<%=DisplayUtil.displayInputTextBox("txtTranDateFrom",tranLogModelSP.getTranDateFrom(),"") %> - 
       			<%=DisplayUtil.displayInputTextBox("txtTranDateTo",tranLogModelSP.getTranDateTo(),"") %>
       		</td>
        </tr>
        <tr>
       		<th align="right" class="style1" scope="row">
       			<span> User Name :</span>&nbsp;
       		</th>
       		<td>
       			<%=DisplayUtil.displayInputTextBox("txtUserName",tranLogModelSP.getUserName(),"") %>
       		</td>	          		
       		<th align="right" class="style1" scope="row">
       			<span> Department :</span>&nbsp;
       		</th>
       		<td>
       			<%=DisplayUtil.displayInputTextBox("txtDepartment",tranLogModelSP.getDepartment(),"") %>
       		</td>	          		
        </tr>
        <tr>
        	<th align="right" class="style1" scope="row">
       			<span> First Name :</span>&nbsp;
       		</th>
       		<td>
       			<%=DisplayUtil.displayInputTextBox("txtFirstName",tranLogModelSP.getFirstName(),"") %>
       		</td>	          		
       		<th align="right" class="style1" scope="row">
       			<span> Last Name :</span>&nbsp;
       		</th>
       		<td>
       			<%=DisplayUtil.displayInputTextBox("txtLastName",tranLogModelSP.getLastName(),"") %>
       		</td>
        </tr>
        <tr>
       		<th colspan="4" align="left" class="style1" scope="row">
       			<%=DisplayUtil.displayButton("Search","onclick=\"buttonAction(this.form,'doSearch')\"",false) %>
       		</th>
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
			
			
			
			/*String strLastPage = "";
			if (lastPage > 1) {
				if (lastPage == valueListM.getAtPage()){
					strLastPage = "<strong><font class=\"text\">"+Integer.toString(lastPage)+"</font></strong>";
				} else {
					strLastPage = "<a href=\"#\" onclick =\"changePageAndSize('"+Integer.toString(lastPage)+"')\"><font class=\"text\">"+Integer.toString(lastPage)+"</font></a>";
				}	
			} else {
				strLastPage	 = "";	
			}*/
			
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
				btnLast = DisplayUtil.displayButton("Last","",true);	
				btnNext = DisplayUtil.displayButton("Next","",true);		
			}else if (allPage != valueListM.getAtPage() && valueListM.getAtPage() != 0)	{
				btnLast = DisplayUtil.displayButton("Last","onclick=\"changePage("+allPage+",this.form)\"",false);
				btnNext = DisplayUtil.displayButton("Next","onclick=\"changePage("+(valueListM.getAtPage()+1)+",this.form)\"",false);
			} else {
				btnLast = DisplayUtil.displayButton("Last","",true);
				btnNext = DisplayUtil.displayButton("Next","",true);
			}
			if(valueListM.getAtPage() == 1){
				btnFirst = 	DisplayUtil.displayButton("First","",true);
				btnBack =  DisplayUtil.displayButton("Back","",true); 	
			}else if (valueListM.getAtPage() != 1 && valueListM.getAtPage() != 0) {    
				btnFirst = 	DisplayUtil.displayButton("First","onclick=\"changePage(1,this.form)\"",false);
				btnBack = DisplayUtil.displayButton("Back","onclick=\"changePage("+(valueListM.getAtPage()-1)+",this.form)\"",false);			
			} else {	   
				btnFirst = DisplayUtil.displayButton("First","",true);
				btnBack = DisplayUtil.displayButton("Back","",true);	 
			}
			
			String showPage = DisplayUtil.displaySelectPaging("selectPaging",allPage,valueListM.getAtPage(),"onchange=\"changeSelectPage(this.form)\"");
			%>
        <tr>
          <th colspan="4" scope="row"><div align="right"><span class="style4">&#3649;&#3626;&#3604;&#3591;&#3612;&#3621;&#3585;&#3634;&#3619;&#3588;&#3657;&#3609;&#3627;&#3634; <%=valueListM.getAtPage()+"/"+allPage %></span>
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
          <th scope="row">&nbsp;</th>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td><div align="right">
          	<%=DisplayUtil.displayButton("Delete","onclick=\"buttonAction(this.form,'doDelete')\"",false) %>
          </div></td>
        </tr>
        <tr>
          <th scope="row">&nbsp;</th>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td align="right">จำนวนที่พบ<%=valueListM.getCount() %> รายการ</td>
        </tr>
        <tr>
          <th colspan="4" scope="row"><table width="951" border="0" align="center" cellpadding="0" cellspacing="1">
            <tr>
              <th width="20" bgcolor="#0099CC" class="style13" scope="row">
                <label>
                  <input type="checkbox" name="checkAllBox" id="checkAllBox" onclick="checkBoxAll()"/>
                </label>
              </th>
              <th width="67" bgcolor="#0099CC" class="style13" scope="row">User Name</th>
              <td width="174" bgcolor="#0099CC" class="style13"><div align="center" class="style14">&#3594;&#3639;&#3656;&#3629; - &#3609;&#3634;&#3617;&#3626;&#3585;&#3640;&#3621; </div></td>
              <td width="154" bgcolor="#0099CC" class="style13"><div align="center" class="style14">หน่วยงาน</div></td>
              <td width="205" bgcolor="#0099CC" class="style13"><div align="center" class="style14">Action</div></td>
              <td width="135" bgcolor="#0099CC" class="style13"><div align="center" class="style14">เวลา</div></td>
              <td width="129" bgcolor="#0099CC" class="style13"><div align="center" class="style14">IP Address</div></td>
            </tr>
            <%
            	if(tranLogVt != null && tranLogVt.size() > 0){
            		String bgColor;
            		for(int i=0;i<tranLogVt.size();i++){
            			tranLogM = (TransactionLogModel)tranLogVt.get(i);            	
            			bgColor = (i%2 == 0)?bgColor1:bgColor2;
            %>
			            <tr>
			              <th <%=bgColor %> scope="row"><input type="checkbox" name="checkBox" id="checkBox" value="<%=tranLogM.getTranId() %>"/></th>
			              <th <%=bgColor %> class="style17" scope="row"><%=tranLogM.getUserName() %></th>
			              <td <%=bgColor %> class="style17"><%=tranLogM.getFirstName() + " " + tranLogM.getLastName() %></td>
			              <td <%=bgColor %> class="style17"><%=tranLogM.getDepartment() %></td>
			              <td <%=bgColor %> class="style17"><div align="center" class="style17"><%=tranLogM.getTranAction() %></div></td>
			              <td <%=bgColor %> class="style17"><div align="center"><%=tranLogM.getTranDate() %></div></td>
			              <td <%=bgColor %> class="style17"><div align="center"><%=tranLogM.getIpAddress() %></div></td>
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
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
	
	</table>



</form>
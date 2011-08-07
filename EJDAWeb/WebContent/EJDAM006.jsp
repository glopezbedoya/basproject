<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@page import="java.util.Vector"%>
    <%@page import="com.tcd.ejda.model.UsrModel" %>
    <%@page import="com.ejda.util.DisplayFormatUtil" %>
  <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@page import="com.tcd.ejda.model.ValueListModel"%>
<%@page import="com.ejda.util.DisplayUtil"%>


<%@page import="com.ejda.sessionBean.UserBean"%>
<%@page import="org.apache.log4j.Logger"%>
<script type="text/javascript">
function popupPolicy(){

	newwindow=window.open("popupPolicy.jsp","","width=500,height=400")
	newwindow.creator=self
}
function checkPassword(){
	//alert($('input[name=password]').val());
	var response = '';
	var passVal = $('input[name=password]').val();
	if (''!=passVal){
		alert('passVal : '+passVal);
		if ($('input[name=strongpwd]').attr('checked')){
			//alert('strongpwd');
			response = strongPwd($('input[name=password]').val());
			alert('Your password is ' + response + '.');
		}	
		//alert('response : ' + response);
		/*if ('' != response && (response == 'weak' && response != 'very weak')){
			alert('Your password is ' + response + '.');
			//$('input[name=password]').focus();
		}*/
		//alert('checkPassword : ' + response);
	}
}

function Reset(){
	
	$('input[name=ejdaAction]').val('EJDAM006');
	$('input[name=ejdaMethod]').val('doSearch');
	$('input[name=screenName]').val('EJDAM006.jsp');
	//document.myForm.submit();
	form.submit();
}
function searchUser(form){
	
	$('input[name=ejdaAction]').val('EJDAM006');
	$('input[name=ejdaMethod]').val('doSearch');
	$('input[name=screenName]').val('EJDAM006.jsp');
	form.submit();
	
}
function AddRole(form){
	var errData = validateData('ADD');
	if (errData){
		try{
			$.get(
			    "/EJDAWeb/AjaxUserServlet?mode=",
			    {user_name : $('input[name=user_name]').val(),
			     ejda_id : ''},
			    function(data) { 
			    	if ('Y'==data){
						alert('User name has been alreay use.');
					}else{
						
						$('input[name=ejdaAction]').val('EJDAM006');
						$('input[name=ejdaMethod]').val('doAdd');
						$('input[name=screenName]').val('EJDAM006.jsp');
						form.submit();
						
					}
					
					
				    }
				    ,  "text"
					);
			}catch(err){
				alert("error"  + err.message);
			}
	}
	//alert($('input[name=iv_user]').val());
	
	
}
function NewRole(form){
	
	$('input[name=ejdaAction]').val('EJDAM006');
	$('input[name=ejdaMethod]').val('doNew');
	$('input[name=screenName]').val('EJDAM006.jsp');
	form.submit();
	
}
function UpdateUser(form){
	var errData = validateData('EDIT');
	if (errData){
	try{
		$.get(
		    "/EJDAWeb/AjaxUserServlet?mode=",
		    {user_name : $('input[name=user_name]').val(),
		     ejda_id : $('input[name=ejda_id]').val()},
		    function(data) { 
				if ('Y'==data){
					alert('User name has been alreay use.');
				}else{
					
					$('input[name=ejdaAction]').val('EJDAM006');
					$('input[name=ejdaMethod]').val('doUpdate');
					$('input[name=screenName]').val('EJDAM006.jsp');
					form.submit();
					
				}
				
				
		    }
		    ,  "text"
			);
	}catch(err){
		alert("error"  + err.message);
	}
	}
}
function EditUser(row){
	//alert($('input[name=ejda_id_'+row+']').val());
	$('input[name=ejda_id]').val($('input[name=ejda_id_'+row+']').val());
	$('input[name=iv_user]').val($('input[name=iv_user_'+row+']').val());
	$('input[name=user_name]').val($('input[name=user_name_'+row+']').val());
	$('input[name=pwd]').val($('input[name=pwd_'+row+']').val());
	$('input[name=crmpwd]').val($('input[name=crmpwd_'+row+']').val());
	$('input[name=firstname]').val($('input[name=firstname_'+row+']').val());
	$('input[name=lastname]').val($('input[name=lastname_'+row+']').val());
	$('input[name=department]').val($('input[name=department_'+row+']').val());
	$('input[name=eff_date]').val($('input[name=eff_date_'+row+']').val());
	$('input[name=exp_date]').val($('input[name=exp_date_'+row+']').val());
	$('input[name=user_status]').val($('input[name=user_status_'+row+']').val());
	$('input[name=ejdaAction]').val('EJDAM006');
	$('input[name=ejdaMethod]').val('doEdit');
	$('input[name=screenName]').val('EJDAM006.jsp');
	document.myForm.submit();
	//form.submit();
	
}
function DeleteUser(jda_id){
	//alert('delete user : ' + jda_id);
	if (confirm('Do you delete this ID.')) { 
		$('input[name=ejda_id]').val(jda_id);
		$('input[name=ejdaAction]').val('EJDAM006');
		$('input[name=ejdaMethod]').val('doDelete');
		$('input[name=screenName]').val('EJDAM006.jsp');
		document.myForm.submit();
	}
	//form.submit();
	
}
function validateData(type){
	var isPass = true;
	var isChk=false;
	$('input[name=checkbok]').each(function(){			
		
		
		if ($(this).attr('checked')){
			isChk = true;
			
		}	
	});
	if(''== $('input[name=user_name]').val()){
		
		alert('Please input User Name.');
		$('input[name=user_name]').focus();
		isPass = false;
	}else if(''== $('input[name=password]').val() && type == 'ADD'){
		
		alert('Please input Password.');
		$('input[name=password]').focus();
		isPass = false;
	}else if(''== $('input[name=crmpwd]').val()  && type == 'ADD'){
		
		alert('Please input Confirm Password.');
		$('input[name=crmpwd]').focus();
		isPass = false;
	}else if(''== $('input[name=iv_user]').val()){
		
		alert('Please input IV User.');
		$('input[name=iv_user]').focus();
		isPass = false;
	}else if(''== $('input[name=firstname]').val()){
		
		alert('Please input First Name.');
		$('input[name=firstname]').focus();
		isPass = false;
	}else if(''== $('input[name=lastname]').val()){
		
		alert('Please input Last Name.');
		$('input[name=lastname]').focus();
		isPass = false;
	}else if(''== $('input[name=department]').val()){
		
		alert('Please input Department.');
		$('input[name=department]').focus();
		isPass = false;
	}else if(''== $('input[name=eff_date]').val()){
		
		alert('Please input Effective Date.');
		$('input[name=eff_date]').focus();
		isPass = false;
	}else if(''== $('input[name=exp_date]').val()){
		
		alert('Please input Expiry Date.');
		$('input[name=exp_date]').focus();
		isPass = false;
	}else if($('input[name=password]').val() != $('input[name=crmpwd]').val()){
		
		alert('Password and Confirmpassword is not equal.');
		$('input[name=crmpwd]').focus();
		isPass = false;
	}else if (!isChk){
		alert('Please select role.');
		isPass = false;
	}
	
	
	
	
	return isPass;
}

function changePage(page,form){
	$('input[name=ejdaAction]').val('EJDAM006');
	$('input[name=ejdaMethod]').val('doSearch');
	$('input[name=screenName]').val('EJDAM006.jsp');
	$('input[name=page]').val(page);
	form.submit();
}
function changeSelectPage(form){
	$('input[name=ejdaAction]').val('EJDAM006');
	$('input[name=ejdaMethod]').val('doSearch');
	$('input[name=screenName]').val('EJDAM006.jsp');
	$('input[name=page]').val($('select[name=selectPaging]').val());
	form.submit();
}
</script>

 <%
		String iuser = (String) request.getSession().getAttribute("iuser");
		String bgColor1 = "bordercolor=\"#F4F4F4\"";
		String bgColor2 = "bgcolor=\"#DFEFFF\"";
		
 		Logger log = Logger.getLogger("JspLog");
		String returnVal = "";
 		UserBean userBean = (UserBean)request.getSession().getAttribute("userBean");
 		UsrModel users = userBean.getUsrMSP();
 		Vector vc = new Vector();
		returnVal = (String)request.getSession().getAttribute("returnVal");
		System.out.println("Show Role Servlet menu : "+returnVal);
		
		log.debug("users.getUSER_STATUS() >> " +users.getUSER_STATUS());
		
		log.debug("userBean.getUsrVt() = "+userBean.getUsrVt());
		if(null != userBean.getUsrVt()){
			vc = (Vector) userBean.getUsrVt();
			log.debug("vc size = "+vc.size());
		}
		
		ValueListModel valueListM = userBean.getValueListM();
		if(null == valueListM) valueListM = new ValueListModel();
 %>
<form name="myForm" method="post" action="/EJDAWeb/EJDAControler">
<input type="hidden" name="ejdaAction" value=""> 
<input type="hidden" name="ejdaMethod" value=""> 
<input type="hidden" name="screenName" value="">
<input type="hidden" name="page" value="<%=valueListM.getAtPage() %>" />
<input type="hidden" name="volumePerPage" value="<%=valueListM.getItemsPerPage() %>" />



	    <table align="center" width="800" border="0" cellspacing="0" cellpadding="0">
	    
	    <tr>
                    <td height="10" align="center"><table width="800" border="0" cellspacing="1" cellpadding="1">
                       <tr>
                      	<td height="30"><table width="800" cellspacing="1" cellpadding="1">
                      	 
                      	  <%if (null==returnVal || "".equals(returnVal)){ %>
                      	 
                   	      <tr>
                   	      	<td colspan="2" align="left"><table width="800" border="0" cellspacing="1" cellpadding="1">
                   	      		
                   	      		 <tr>
		                      	    <td align="right" width="100"><font class="text">ID : </font>
		                      	    		
		                      	    		
		                      	    </td>
		                      	    <td colspan="3" align="left"><%=DisplayUtil.displayInputTextBox("txtIVUser",users.getIV_USER(),"") %></td>
		                   	      </tr>
		                   	      	 <tr>
		                      	    <td align="right" width="100"><font class="text">First Name : </font> </td>
		                      	    <td align="left"width="100"><%=DisplayUtil.displayInputTextBox("txtFirstName",users.getFIRSTNAME(),"") %></td>
		                   	                	    
		                      	    <td align="right"width="100"><font class="text">Last Name : </font></td>
		                      	    <td align="left"><%=DisplayUtil.displayInputTextBox("txtLastName",users.getLASTNAME(),"") %></td>
		                   	      </tr>
		                   	      <tr>
		                      	    <td align="right" width="100"><font class="text">Status :</font></td>
		                      	   
		                      	    <td  align="left" colspan="2"> <font class="text">
		                      	     <%if (null==users.getUSER_STATUS()){%>
		                      	     
		                      	     <input type="radio" name="rdoStatus" id="rdoStatus" value="LOCK" /> Lock
		                      	     <input type="radio" name="rdoStatus" id="rdoStatus" value="UNLOCK" />  No lock 
		                      	     <input type="radio" name="rdoStatus" id="rdoStatus" value="ALL"checked="checked"/> Select All 
		                      	     <%}else{ %>
			                      	     <%if ("LOCK".equals(users.getUSER_STATUS())){%>
			                      	    	<input type="radio" name="rdoStatus" id="rdoStatus" value="LOCK"checked="checked" /> Lock
			                      	    <%}else{ %>
			                      	    <input type="radio" name="rdoStatus" id="rdoStatus" value="LOCK" /> Lock
			                      	    <%} %>
			                      	    <% if ("UNLOCK".equals(users.getUSER_STATUS())){%>
			                      	    <input type="radio" name="rdoStatus" id="rdoStatus" value="UNLOCK" checked="checked"/>  No lock 
			                      	     <%}else{ %>
			                      	    <input type="radio" name="rdoStatus" id="rdoStatus" value="UNLOCK" />  No lock 
			                      	    <%} %>
			                      	    <%if ("ALL".equals(users.getUSER_STATUS())){%>
			                      	    <input type="radio" name="rdoStatus" id="rdoStatus" value="ALL"checked="checked"/> Select All 
			                      	     <%}else{ %>
			                      	    <input type="radio" name="rdoStatus" id="rdoStatus" value="ALL" /> Select All 
			                      	    <%} %>
		                      	    <%} %>
		                      	    </font>
		                      	    </td>
		                      	    <td align="left"><input type="button" name="search" id="search" value="Search"onClick="searchUser(this.form)"></td>
		                   	      </tr>
		                   	      
		                   	      </table>
                   	      	</td>
                   	      </tr>
                   	       <tr>
                      	    <td colspan="2" align="left"></td>
                   	      </tr>
                      	  <tr>
                      	    <td colspan="2" height="10"></td>
                   	      </tr>
                      	  <tr>
                      	    <td colspan="2"></td>
                   	      </tr>
                      	  
                   	       <tr>
                      	    <td colspan="2" height="10">&nbsp;</td>
                   	      </tr>
                   	     
                   	     
                   	      <tr>
                      	    <td colspan="2" height="10">&nbsp;</td>
                   	      </tr>
                   	       <%if(vc.size()>0){%>
                   	       
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
						          <th colspan="4" scope="row"><div align="right"><span class="textPage">&#3649;&#3626;&#3604;&#3591;&#3612;&#3621;&#3585;&#3634;&#3619;&#3588;&#3657;&#3609;&#3627;&#3634; <%=valueListM.getAtPage()+"/"+allPage %></span>
						            <%=showPage %>
						         	<%=btnFirst %>
						         	<%=btnBack %>
						         	<%=btnNext %>
						         	<%=btnLast %>
						          </div></th>
						        </tr>
							         <tr>
						          <th scope="row">&nbsp;</th>					          
						          <td align="right"><span class="textPage">จำนวนที่พบ<%=valueListM.getCount() %> รายการ</span></td>
						        </tr>
							        <tr>
									<td colspan="2" height="10"></td>					        
						        </tr>
						        <tr>
	                      	    <td>&nbsp;</td>
	                      	    <td  align="right"><input type="button" name="new" id="new" value="New User" onClick="NewRole(this.form)"></td>
	                   	      	<input type="hidden" name="cType" id="cType" value="">
	                   	      </tr>
	                   	       <tr>
	                      	    <td colspan="3" align="center"><table width="800" cellspacing="1" cellpadding="1">
	                      	    <tr  bgcolor="#003366">
	                   	    		<td ><font class="textHeader">IV User</font></td>
	                   	    		
	                   	    		<td ><font class="textHeader">First Lastname</font></td>
	                   	    		<td ><font class="textHeader"> Create Date</font></td>
	                   	    		<td ><font class="textHeader"> Status </font></td>
	                   	    		<td ><font class="textHeader"> Edit</font></td>
	                   	    		<td ><font class="textHeader"> Delete</font></td>
	                   	    		</tr>
	                      	    <%
	                   	    	  for (int i = 0; i< vc.size();i++){
	                   	    		String bgColor = "";
	                   	    		UsrModel rm = (UsrModel)vc.get(i);
	                   	    		bgColor = (i%2 == 0)?bgColor1:bgColor2;
	                   	    	%>
	                   	    		<tr>
	                   	    		<td <%=bgColor %>><font class="text"> <%=rm.getIV_USER() %></font></td>
	                   	    		<td <%=bgColor %>><font class="text"> <%=rm.getFIRSTNAME() %> <%=rm.getLASTNAME() %></font></td>
	                   	    		<%
	                   	    		String show_locked = "";
	                   	    		if (rm.getUSER_STATUS().equals("L") || (rm.getUSER_STATUS().equals("A") && !iuser.equals(rm.getUSERNAME()))){
	                   	    			show_locked = "<font class=\"textRed\"> Locked</font>";
	                   	    		}else{
	                   	    			show_locked = "No Lock";
	                   	    		}
	                   	    		
	                   	    		%>
	                   	    		<td <%=bgColor %>><font class="text"> <%=DisplayFormatUtil.SQLDateToString(rm.getCreate_date(),"DD/MM/YYYY")%></font></td>
	                   	    		<td <%=bgColor %>><font class="text"> <%=show_locked %></font></td>
	                   	    		<td <%=bgColor %>><font class="text"> <img src="images/edit.JPG" name="edit" id="edit" style="cursor:hand" onclick="EditUser('<%=i %>')"></font></td>
	                   	    		<td <%=bgColor %>><font class="text"> <img src="images/delete.JPG" name="delete" id="delete" value="delete"style="cursor:hand" onclick="DeleteUser('<%=rm.getJda_id() %>')"></font></td>
	                   	    		<input type ="hidden" name="ejda_id_<%=i %>" id = "ejda_id_<%=i %>" value="<%=rm.getJda_id() %>">
	                   	    		<input type ="hidden" name="iv_user_<%=i %>" id = "iv_user_<%=i %>" value="<%=rm.getIV_USER() %>">
	                   	    		<input type ="hidden" name="user_name_<%=i %>" id ="user_name_<%=i %>" value="<%=rm.getUSERNAME() %>">
	                   	    		<input type ="hidden" name="pwd_<%=i %>"id ="pwd_<%=i %>" value="<%=rm.getPWD() %>">
	                   	    		<input type ="hidden" name="crmpwd_<%=i %>"id="crmpwd_<%=i %>" value="<%=rm.getPWD() %>">
	                   	    		<input type ="hidden" name="firstname_<%=i %>"id="firstname_<%=i %>" value="<%=rm.getFIRSTNAME() %>">
	                   	    		<input type ="hidden" name="lastname_<%=i %>"id ="lastname_<%=i %>" value="<%=rm.getLASTNAME() %>">
	                   	    		<input type ="hidden" name="department_<%=i %>"id="department_<%=i %>" value="<%=rm.getDEPARTMENT() %>">
	                   	    		<input type ="hidden" name="eff_date_<%=i %>" id ="eff_date_<%=i %>"value="<%=rm.getEFFECTIVE_DATE()%>">
	                   	    		<input type ="hidden" name="exp_date_<%=i %>" id="exp_date_<%=i %>"value="<%=rm.getEXPIRY_DATE()%>">
	                   	    		<input type ="hidden" name="user_status_<%=i %>" id="user_status_<%=i %>"value="<%=rm.getUSER_STATUS() %>">
 	                   	    		</tr>
	                   	      		<input type="hidden" name="ejda_id" value="">
									<input type="hidden" name="iv_user" value="">
									<input type="hidden" name="user_name" value="">
									<input type="hidden" name="pwd" value="">
									<input type="hidden" name="crmpwd" value="">
									<input type="hidden" name="firstname" value="">
									<input type="hidden" name="lastname" value="">
									<input type="hidden" name="department" value="">
									<input type="hidden" name="eff_date" value="">
									<input type="hidden" name="exp_date" value="">
	                      	    	<input type="hidden" name="user_status" value="">
	                      	    	<% } %>
	                      	    </table>
	                      	    		
	                      	    		
	                      	    </td>
	                      	    <td colspan="2">&nbsp;</td>
	                   	      </tr>
	                   	      <%	 }%>
                   	      <%}else if (returnVal.endsWith("NEW")){ %>
                   	      <%
								String rolename = ""; 
                   	      		rolename = (String)request.getSession().getAttribute("rolename");
                   	      %>
                   	       <tr>
                      	    <td colspan="2" height="30"></td>
                   	      </tr>
                   	      <tr>
                   	      <td colspan="3">
                   	      <table width="600" cellspacing="1" cellpadding="1" align="center" background="images/back_line.png">
                   	       <tr>
						    <td align="right"><span class="text">User Name : </span></td>
						    <td align="left" width="100">
						      <input type="text" name="user_name" id="user_name"value="" />
						    </td>
						    <td ></td>
						  </tr>
						  <tr>
						    <td align="right"><span class="text">Password : </span></td>
						    <td align="left" width="100">
						      <input type="password" name="password" id="password" value="" onblur="checkPassword();"/>
						    </td>
						    <td align="left" ><input type="checkbox" name="strongpwd" id="strongpwd" checked="checked"  /><font class="text">Password Policy</font>
						    <input type = "button" name="popPolicy" id = "polPolicy" value="View Policy" onclick="popupPolicy()">
						    </td>
						  </tr>
						   <tr>
						    <td align="right"><span class="text">Confirm Password : </span></td>
						    <td align="left" width="100">
						      <input type="password" name="crmpwd" id="crmpwd"value="" />
						    </td>
						    <td ></td>
						  </tr>
						   <tr>
						    <td align="right"><span class="text">User ID : </span></td>
						    <td align="left" width="100">
						      <input type="text" name="iv_user" id="iv_user"value="" />
						    </td>
						    <td ></td>
						  </tr>
						   <tr>
						    <td align="right"><span class="text">First Name : </span></td>
						    <td align="left" width="100">
						      <input type="text" name="firstname" id="firstname"value="" />
						    </td>
						    <td ></td>
						  </tr>
						   <tr>
						    <td align="right"><span class="text">Last Name : </span></td>
						    <td align="left" width="100">
						      <input type="text" name="lastname" id="lastname"value="" />
						    </td>
						    <td ></td>
						  </tr>
						   <tr>
						    <td align="right"><span class="text">Department : </span></td>
						    <td align="left" width="100">
						      <input type="text" name="department" id="department"value="" />
						    </td>
						    <td ></td>
						  </tr>
						  <tr>
                      	    <td colspan="3" height="10"></td>
                   	      </tr>
                   	       <tr>
						    <td align="right"><span class="text">Effective Date : </span></td>
						    <td align="left" width="200">
						      <input type="textbox" name="eff_date" style="cursor:hand"onkeypress="addSlashFormat(event,this)" onblur="checkDateLengthYear(this,'','1800','2300')" maxlength="10">
								<img
								id="eff_date_img" style="cursor:hand"
								src="images/icon_calendar.gif"
								onclick="popUpCalendarModify(this,eff_date,'dd/mm/yyyy','','','','bottom',false)"
								alt="">
						    </td>
						    <td ></td>
						  </tr>
						   <tr>
						    <td align="right"><span class="text">Expiry Date : </span></td>
						    <td align="left" width="200">
						      <input type="text" name="exp_date" id="exp_date"style="cursor:hand" onkeypress="addSlashFormat(event,this)" onblur="checkDateLengthYear(this,'','1800','2300')" maxlength="10" value=""/>
						      <img
								id="exp_date_img" style="cursor:hand"
								src="images/icon_calendar.gif"
								onclick="popUpCalendarModify(this,exp_date,'dd/mm/yyyy','','','','bottom',false)"
								alt="">
						    </td>
						    <td ></td>
						  </tr>
						   <tr>
                      	    <td colspan="3" height="10"></td>
                   	      </tr>
						 
						  
						  <%
						    
						    String checks[] = rolename.split("\\|");
						    System.out.println("checks >> " + checks.length);
						    for(int i=0;i<checks.length;i++){
						    	%>
						    	<tr>
						    	<%if (i==0){ %>
						    	<td align="right"><span class="text">Role Name : </span></td>
						    	<%}else{ %>
						    	<td align="right"></td>
						    	<%} %>
						    	<td align="left"><%=checks[i] %></td>
						    	 <td align="left"></td>
						  		</tr>
						    	<%
						    	}
						    %>
						   
						   
						   <tr>
                      	    <td colspan="3" height="10"></td>
                   	      </tr>
						   <tr>
						    <td align="right"></td>
						    <td colspan="2" align="left" width="100">
						    <input type="hidden" name="txtIVUser" value="<%=users.getIV_USER() %>">
						    <input type="hidden" name="txtFirstName" value="<%=users.getFIRSTNAME() %>">
						    <input type="hidden" name="txtLastName" value="<%=users.getLASTNAME() %>">
						    <input type="hidden" name="rdoStatus" value="<%=users.getUSER_STATUS() %>">
						      <input type="button" name="add" id="add" value="Add User" onClick="AddRole(this.form)">
						      <input type="hidden" name="cType" id="cType" value="">
						      <input type="button" name="cancel" id="cancel" value="Cancel" onClick="Reset(this.form)">
						    </td>
						    
						  </tr>
						  </table>
						  </td></tr>
						   <%
						   //request.getSession().setAttribute("returnVal", "");						   
                   	      		 %>
                   	      <%}else if (returnVal.endsWith("UPDATE")){ %>
                   	      <%
								String rolename = ""; 
                   	      		rolename = (String)request.getSession().getAttribute("rolename");
                   	      		//UsrModel um = new UsrModel();
                   	      		UsrModel um = (UsrModel) request.getSession().getAttribute("userModel");
                   	      		String roleHidden = (String)request.getSession().getAttribute("UserRole");
                   	      		System.out.println("rolevc >>>> " +roleHidden);
                   	      		System.out.println("user model : " + um.getIV_USER());
                   	      %>
                   	      <input type ="hidden" name="ejda_id" id = "ejda_id" value="<%=um.getJda_id() %>">
	                   	    		
                   	       <tr>
                      	    <td colspan="2" height="30"></td>
                   	      </tr>
                   	      <tr>
                   	      <td colspan="3">
                   	      <table width="600" cellspacing="1" cellpadding="1" align="center">
                   	       <tr>
						    <td align="right"><span class="text">User Name : </span></td>
						    <td align="left" width="100">
						      <input type="text" name="user_name" id="user_name" value="<%=um.getUSERNAME() %>" />
						    </td>
						    <td ></td>
						  </tr>
						  <tr>
						    <td align="right"><span class="text">Password : </span></td>
						    <td align="left" width="100">
						      <input type="password" name="password" id="password" value=""onblur="checkPassword();" />
						    </td>
						    <td align="left" ><input type="checkbox" name="strongpwd" id="strongpwd" checked="checked"   /><font class="text">Password Policy</font>
						    <input type = "button" name="popPolicy" id = "polPolicy" value="View Policy" onclick="popupPolicy()">
						    </td>
						  </tr>
						   <tr>
						    <td align="right"><span class="text">Confirm Password : </span></td>
						    <td align="left" width="100">
						      <input type="password" name="crmpwd" id="crmpwd"value="" />
						    </td>
						    <td ></td>
						  </tr>
						   <tr>
						    <td align="right"><span class="text">User ID : </span></td>
						    <td align="left" width="100">
						      <input type="text" name="iv_user" id="iv_user"value="<%=um.getIV_USER() %>" />
						    </td>
						    <td ></td>
						  </tr>
						   <tr>
						    <td align="right"><span class="text">First Name : </span></td>
						    <td align="left" width="100">
						      <input type="text" name="firstname" id="firstname"value="<%=um.getFIRSTNAME() %>" />
						    </td>
						    <td ></td>
						  </tr>
						   <tr>
						    <td align="right"><span class="text">Last Name : </span></td>
						    <td align="left" width="100">
						      <input type="text" name="lastname" id="lastname"value="<%=um.getLASTNAME() %>" />
						    </td>
						    <td ></td>
						  </tr>
						   <tr>
						    <td align="right"><span class="text">Department : </span></td>
						    <td align="left" width="100">
						      <input type="text" name="department" id="department"value="<%=um.getDEPARTMENT() %>" />
						    </td>
						    <td ></td>
						  </tr>
						  <tr>
                      	    <td colspan="3" height="10"></td>
                   	      </tr>
                   	       <tr>
						    <td align="right"><span class="text">Effective Date : </span></td>
						    <td align="left" width="200">
						      <input type="text" name="eff_date" id="eff_date"onkeypress="addSlashFormat(event,this)" onblur="checkDateLengthYear(this,'','1800','2300')"value="<%= DisplayFormatUtil.SQLDateToString(um.getEFFECTIVE_DATE(),"DD/MM/YYYY")%>" />
						       <img
								id="eff_date_img" style="cursor:hand"
								src="images/icon_calendar.gif"
								onclick="popUpCalendarModify(this,eff_date,'dd/mm/yyyy','','','','bottom',false)"
								alt="">
						    </td>
						    <td ></td>
						  </tr>
						   <tr>
						    <td align="right"><span class="text">Expiry Date : </span></td>
						    <td align="left" width="200">
						      <input type="text" name="exp_date" id="exp_date"onkeypress="addSlashFormat(event,this)" onblur="checkDateLengthYear(this,'','1800','2300')"value="<%=DisplayFormatUtil.SQLDateToString(um.getEXPIRY_DATE(),"DD/MM/YYYY") %>" />
						       <img
								id="exp_date_img" style="cursor:hand"
								src="images/icon_calendar.gif"
								onclick="popUpCalendarModify(this,exp_date,'dd/mm/yyyy','','','','bottom',false)"
								alt="">
						    </td>
						    <td ></td>
						  </tr>
						   <tr>
                      	    <td colspan="3" height="10"></td>
                   	      </tr>
						   
						  <%
						   	 
						    String checks[] = rolename.split("\\|");
						    System.out.println("checks >> " + checks.length);
						    for(int i=0;i<checks.length;i++){
						    	%>
						    	<tr>
						    	<%if (i==0){ %>
						    	<td align="right"><span class="text">Role Name : </span></td>
						    	<%}else{ %>
						    	<td align="right"></td>
						    	<%} %>
						    	<td align="left"><%=checks[i] %></td>
						    	 <td align="left"></td>
						  		</tr>
						    	<%
						    	}
						    %>
						   <tr>
                      	    <td colspan="3" height="10"></td>
                   	      </tr>
                   	      <%if ("L".equals(um.getUSER_STATUS()) || ("A".equals(um.getUSER_STATUS()) && !iuser.equals(um.getUSERNAME()) )){ %>
                   	      
                   	      	 <tr>
						    <td align="right"><span class="text">Unlock User : </span></td>
						    <td align="left" width="100">
						      <input type="checkbox" name="user_status" id="user_status"value="<%=um.getUSER_STATUS() %>" checked="checked" />
						    </td>
						    <td ></td>
						  </tr>
                   	      <%}else{ %>
                   	       <tr>
						    <td align="right"><span class="text">User Status: </span></td>
						    <td align="left" width="100">
						      <input type="checkbox" name="user_status" id="user_status"value="<%=um.getUSER_STATUS() %>" disabled="disabled" />
						    </td>
						    <td ></td>
						  </tr>
                   	      <%} %>
                   	       <tr>
                      	    <td colspan="3" height="20"></td>
                   	      </tr>
						   <tr>
						    <td align="right"></td>
						    <td colspan="2" align="left" width="100">
						     <input type="hidden" name="txtIVUser" value="<%=users.getIV_USER() %>">
						    <input type="hidden" name="txtFirstName" value="<%=users.getFIRSTNAME() %>">
						    <input type="hidden" name="txtLastName" value="<%=users.getLASTNAME() %>">
						    <input type="hidden" name="rdoStatus" value="<%=users.getUSER_STATUS() %>">
						      <input type="button" name="upd" id="upd" value="Update User" onClick="UpdateUser(this.form)">
						      <input type="hidden" name="cType" id="cType" value="">
						      <input type="button" name="cancel" id="cancel" value="Cancel" onClick="Reset(this.form)">
						      <%=roleHidden %>
						    </td>
						    
						  </tr>
						  </table>
						  </td></tr>
						   <%
						  // request.getSession().setAttribute("returnVal", "");						   
                   	      		} %>
						   <tr>
                      	    <td>&nbsp;</td>
                      	    <td>&nbsp;</td>
                   	      </tr>
                   	     </table></td>
                      </tr> 
                    </table></td>
                </tr> 
               
	    </table>
	    </form>
<script type="text/javascript">
var rolechk = '';
$('input[name=roletextbox]').each(function(){
	rolechk = $(this).val();
	
	$('input[name=checkbok]').each(function(){			
	
		if (null!=$(this).val() && ($(this).val() == rolechk)){
			$(this).attr('checked', true) ;
		}
	
	});
});
</script>
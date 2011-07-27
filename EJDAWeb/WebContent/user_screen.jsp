<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@page import="java.util.Vector"%>
    <%@page import="com.tcd.ejda.model.UsrModel" %>
  <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
function searchUser(form){
	
	$('input[name=ejdaAction]').val('User');
	$('input[name=ejdaMethod]').val('doSearch');
	$('input[name=screenName]').val('user_screen.jsp');
	form.submit();
	
}
function AddRole(form){
	var errData = validateData();
	if (errData){
		$('input[name=ejdaAction]').val('User');
		$('input[name=ejdaMethod]').val('doAdd');
		$('input[name=screenName]').val('user_screen.jsp');
		form.submit();
	}
	//alert($('input[name=iv_user]').val());
	
	
}
function NewRole(form){
	
	$('input[name=ejdaAction]').val('User');
	$('input[name=ejdaMethod]').val('doNew');
	$('input[name=screenName]').val('user_screen.jsp');
	form.submit();
	
}
function UpdateUser(form){
	var errData = validateData();
	if (errData){
		$('input[name=ejdaAction]').val('User');
		$('input[name=ejdaMethod]').val('doUpdate');
		$('input[name=screenName]').val('user_screen.jsp');
		form.submit();
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
	$('input[name=ejdaAction]').val('User');
	$('input[name=ejdaMethod]').val('doEdit');
	$('input[name=screenName]').val('user_screen.jsp');
	document.myForm.submit();
	//form.submit();
	
}
function DeleteUser(jda_id){
	//alert('delete user : ' + jda_id);
	if (confirm('Do you delete this ID.')) { 
		$('input[name=ejda_id]').val(jda_id);
		$('input[name=ejdaAction]').val('User');
		$('input[name=ejdaMethod]').val('doDelete');
		$('input[name=screenName]').val('user_screen.jsp');
		document.myForm.submit();
	}
	//form.submit();
	
}
function validateData(){
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
	}else if(''== $('input[name=password]').val()){
		
		alert('Please input Password.');
		$('input[name=password]').focus();
		isPass = false;
	}else if(''== $('input[name=crmpwd]').val()){
		
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
</script>

 <%
		String returnVal = "";
 		Vector vc = new Vector();
		returnVal = (String)request.getSession().getAttribute("returnVal");
		System.out.println("Show Role Servlet menu : "+returnVal);
		if (null!=request.getSession().getAttribute("returnVC")){
			vc  =(Vector) request.getSession().getAttribute("returnVC");
			System.out.println("Show Role Servlet vector : "+vc.size());
		}
 %>
<form name="myForm" method="post" action="/EJDAWeb/EJDAControler">
<input type="hidden" name="ejdaAction" value=""> 
<input type="hidden" name="ejdaMethod" value=""> 
<input type="hidden" name="screenName" value="">



	    <table align="center" width="800" border="0" cellspacing="0" cellpadding="0">
	    <tr>
                    <td height="10" align="center"><table width="800" border="0" cellspacing="1" cellpadding="1">
                       <tr>
                      	<td height="30"><table width="800" cellspacing="1" cellpadding="1">
                      	 
                      	  <%if (null==returnVal || "".equals(returnVal)){ %>
                      	 
                   	      <tr>
                   	      	<td colspan="2" align="left"><table width="800" border="0" cellspacing="1" cellpadding="1">
                   	      		 <tr>
		                      	    <td align="right" width="100"><font class="text">IV User : </font>
		                      	    		
		                      	    		
		                      	    </td>
		                      	    <td colspan="3" align="left"><input type="text" name="siv_user" id="siv_user" value=""></td>
		                   	      </tr>
		                   	      	 <tr>
		                      	    <td align="right" width="100"><font class="text">First Name : </font> </td>
		                      	    <td align="left"width="100"><input type="text" name="sfirstname" id="sfirstname" value=""></td>
		                   	                	    
		                      	    <td align="right"width="100"><font class="text">Last Name : </font></td>
		                      	    <td align="left"><input type="text" name="slastname" id="slastname" value=""></td>
		                   	      </tr>
		                   	      <tr>
		                      	    <td align="right" width="100"><font class="text">Status :</font></td>
		                      	    <td  align="left"> <font class="text"><input type="radio" name="radio" id="radio" value="" /> Lock
		                      	    <input type="radio" name="radio" id="radio" value="" />  Unlock </font></td>
		                      	    <td align="left" colspan="2"></td>
		                   	      </tr>
		                   	       <tr>
		                      	    <td align="right"></td>
		                      	    <td align="left"></td>
		                   	                	    
		                      	    <td align="right"></td>
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
                      	    <td>&nbsp;</td>
                      	    <td  align="right"><input type="button" name="new" id="new" value="New User" onClick="NewRole(this.form)"></td>
                   	      	<input type="hidden" name="cType" id="cType" value="">
                   	      </tr>
                   	      <tr>
                      	    <td colspan="2" height="10">&nbsp;</td>
                   	      </tr>
                   	       <%if(null!=vc){%>
	                   	       <tr>
	                      	    <td colspan="3" align="center"><table width="800" cellspacing="1" cellpadding="1">
	                      	    <%
	                   	    	  for (int i = 0; i< vc.size();i++){
	                   	    		UsrModel rm = (UsrModel)vc.get(i);
	                   	    	%>
	                   	    		<tr>
	                   	    		<td><font class="text"> <%=rm.getIV_USER() %></font></td>
	                   	    		<td><font class="text"> <%=rm.getFIRSTNAME() %> <%=rm.getLASTNAME() %></font></td>
	                   	    		<%
	                   	    		String show_locked = "";
	                   	    		if (rm.getUSER_STATUS().equals("L")){
	                   	    			show_locked = "Locked";
	                   	    		}
	                   	    		%>
	                   	    		
	                   	    		<td><font class="text"> <%=show_locked %></font></td>
	                   	    		<td><font class="text"> <%=rm.getShow_edit() %></font></td>
	                   	    		<td><font class="text"> <%=rm.getShow_delete() %></font></td>
	                   	    		<input type ="hidden" name="ejda_id_<%=i %>" id = "ejda_id_<%=i %>" value="<%=rm.getJda_id() %>">
	                   	    		<input type ="hidden" name="iv_user_<%=i %>" id = "iv_user_<%=i %>" value="<%=rm.getIV_USER() %>">
	                   	    		<input type ="hidden" name="user_name_<%=i %>" id ="user_name_<%=i %>" value="<%=rm.getUSERNAME() %>">
	                   	    		<input type ="hidden" name="pwd_<%=i %>"id ="pwd_<%=i %>" value="<%=rm.getPWD() %>">
	                   	    		<input type ="hidden" name="crmpwd_<%=i %>"id="crmpwd_<%=i %>" value="<%=rm.getPWD() %>">
	                   	    		<input type ="hidden" name="firstname_<%=i %>"id="firstname_<%=i %>" value="<%=rm.getFIRSTNAME() %>">
	                   	    		<input type ="hidden" name="lastname_<%=i %>"id ="lastname_<%=i %>" value="<%=rm.getLASTNAME() %>">
	                   	    		<input type ="hidden" name="department_<%=i %>"id="department_<%=i %>" value="<%=rm.getDEPARTMENT() %>">
	                   	    		<input type ="hidden" name="eff_date_<%=i %>" id ="eff_date_<%=i %>"value="<%=rm.getEFFECTIVE_DATE() %>">
	                   	    		<input type ="hidden" name="exp_date_<%=i %>" id="exp_date_<%=i %>"value="<%=rm.getEXPIRY_DATE() %>">
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
                   	      <table width="600" cellspacing="1" cellpadding="1" align="center">
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
						      <input type="password" name="password" id="password" value="" />
						    </td>
						    <td align="left" ><input type="checkbox" name="checkbox" id="checkbox" /><font class="text">Password Policy</font></td>
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
						    <td align="left" width="100">
						      <input type="textbox" name="eff_date">
								<img
								id="eff_date_img" style="cursor:hand"
								src="images/calendar_new.gif"
								onclick="popUpCalendarModify(this,eff_date,'dd/mm/yyyy','','','','bottom',false)"
								alt="">
						    </td>
						    <td ></td>
						  </tr>
						   <tr>
						    <td align="right"><span class="text">Expiry Date : </span></td>
						    <td align="left" width="100">
						      <input type="text" name="exp_date" id="exp_date" onkeydown="javascript:DateFormat(this,this.value,event,false,'1')" value=""/>
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
						      <input type="button" name="add" id="add" value="Add User" onClick="AddRole(this.form)">
						      <input type="hidden" name="cType" id="cType" value="">
						      <input type="button" name="Reset" id="Reset" value="Reset" onClick="Reset()">
						    </td>
						    
						  </tr>
						  </table>
						  </td></tr>
						   <%
						   request.getSession().setAttribute("returnVal", "");						   
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
						      <input type="text" name="user_name" id="user_name"value="<%=um.getUSERNAME() %>" />
						    </td>
						    <td ></td>
						  </tr>
						  <tr>
						    <td align="right"><span class="text">Password : </span></td>
						    <td align="left" width="100">
						      <input type="password" name="password" id="password" value="<%=um.getPWD() %>" />
						    </td>
						    <td align="left" ><input type="checkbox" name="checkbox" id="checkbox" /><font class="text">Password Policy</font></td>
						  </tr>
						   <tr>
						    <td align="right"><span class="text">Confirm Password : </span></td>
						    <td align="left" width="100">
						      <input type="password" name="crmpwd" id="crmpwd"value="<%=um.getPWD() %>" />
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
						    <td align="left" width="100">
						      <input type="text" name="eff_date" id="eff_date"value="<%= um.getEFFECTIVE_DATE()%>" />
						    </td>
						    <td ></td>
						  </tr>
						   <tr>
						    <td align="right"><span class="text">Expiry Date : </span></td>
						    <td align="left" width="100">
						      <input type="text" name="exp_date" id="exp_date"value="<%=um.getEXPIRY_DATE() %>" />
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
                   	      <%if ("L".equals(um.getUSER_STATUS())){ %>
                   	      
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
						      <input type="button" name="upd" id="upd" value="Update User" onClick="UpdateUser(this.form)">
						      <input type="hidden" name="cType" id="cType" value="">
						      <input type="button" name="Reset" id="Reset" value="Reset" onClick="Reset()">
						      <%=roleHidden %>
						    </td>
						    
						  </tr>
						  </table>
						  </td></tr>
						   <%
						   request.getSession().setAttribute("returnVal", "");						   
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
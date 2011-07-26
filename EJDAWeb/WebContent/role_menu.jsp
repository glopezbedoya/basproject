<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.Vector"%>
    <%@page import="com.tcd.ejda.model.RoleModel" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
function searchButton(form){
	
	$('input[name=ejdaAction]').val('Role');
	$('input[name=ejdaMethod]').val('doSearch');
	$('input[name=screenName]').val('role_menu.jsp');
	form.submit();
	
}
function AddRole(form){
	 var inqsCheck = '';
	 var addCheck = '';
	 var updCheck = '';
	 var delCheck = '';
	 var i =0;
	 var indextemp = '';
	 $('input[name=menuid]').each(function(){
		 
		 if (null!=$(this).val() && "C"!=$('input[name=status]').val()){
			 //inqsCheck = inqsCheck + getIndexUnCheckBox('inqs'+$(this).val());
			 indextemp = indextemp + getIndexUnCheckBox('inqs'+$(this).val()) + getIndexUnCheckBox('add'+$(this).val()) + 
									 getIndexUnCheckBox('upd'+$(this).val()) + getIndexUnCheckBox('del'+$(this).val()) +'|';
    
		 }
		
		 i=i+1;
	 });
	 
	$('input[name=func_type]').val(indextemp);

	
	$('input[name=ejdaAction]').val('Role');
	$('input[name=ejdaMethod]').val('doAdd');
	$('input[name=screenName]').val('role_menu.jsp');
	form.submit();
	
}
function UpdateRole(form){
	 var inqsCheck = '';
	 var addCheck = '';
	 var updCheck = '';
	 var delCheck = '';
	 var i =0;
	 var indextemp = '';
	 $('input[name=menuid]').each(function(){
		 
		 if (null!=$(this).val() && "C"!=$('input[name=status]').val()){
			 //inqsCheck = inqsCheck + getIndexUnCheckBox('inqs'+$(this).val());
			 indextemp = indextemp + getIndexUpdate('inqs'+$(this).val(),$(this).val()) + getIndexUpdate('add'+$(this).val(),$(this).val()) + 
						 getIndexUpdate('upd'+$(this).val(),$(this).val()) + getIndexUpdate('del'+$(this).val(),$(this).val()) +'|';
   
		 }
		
		 i=i+1;
	 });
	 
	$('input[name=func_type]').val(indextemp);

	$('input[name=ejdaAction]').val('Role');
	$('input[name=ejdaMethod]').val('doUpdate');
	$('input[name=screenName]').val('role_menu.jsp');
	form.submit();
	
}
function EditRole(form,role_id,role_name){
	//alert('EditRole');
	//alert('role_id : ' +role_id);
	$('input[name=role_id]').val(role_id);
	$('input[name=hrole_name]').val(role_name);
	$('input[name=ejdaAction]').val('Role');
	$('input[name=ejdaMethod]').val('doEdit');
	$('input[name=screenName]').val('role_menu.jsp');
	
	form.submit();
	
}
function NewRole(form){
	
	$('input[name=ejdaAction]').val('Role');
	$('input[name=ejdaMethod]').val('doNew');
	$('input[name=screenName]').val('role_menu.jsp');
	form.submit();
	
}
function DeleteRole(form,role_id,role_name){
	//alert('EditRole');
	alert('role_id : ' +role_id);
	$('input[name=role_id]').val(role_id);
	$('input[name=hrole_name]').val(role_name);
	$('input[name=ejdaAction]').val('Role');
	$('input[name=ejdaMethod]').val('doDelete');
	$('input[name=screenName]').val('role_menu.jsp');
	
	form.submit();
	
}
function getIndexUnCheckBox(name){
	//alert('get index : ' + name);

	var indexTemp = '';
	//$('input[name=inqs]').each(function(){
	$('input[name='+name+']').each(function(){
		
		if(!$(this).attr('checked') && !$(this).attr('disabled')){
			
			if (null!=$(this).val() && ""!=$(this).val()){
				indexTemp += name + '=' + $(this).val() + '=N';
			}
			
		}else{
			if (null!=$(this).val() && ""!=$(this).val()){
				indexTemp += name + '=' + $(this).val() + '=Y';
			}
		}
		indexTemp += ':';
	});
	
	//alert('get indexTemp : ' +indexTemp);
	return indexTemp;
}
function getIndexUpdate(name,menuid){
	//alert('get index : ' + name);

	var indexTemp = '';
	//$('input[name=inqs]').each(function(){
	$('input[name='+name+']').each(function(){
		
		if(!$(this).attr('checked') && !$(this).attr('disabled')){
			
			if (null!=$(this).val() && ""!=$(this).val()){
				indexTemp += name + '=' + menuid + '=N';
			}
			
		}else{
			if (null!=$(this).val() && ""!=$(this).val()){
				indexTemp += name + '=' + menuid + '=Y';
			}
		}
		indexTemp += ':';
	});
	
	//alert('get indexTemp : ' +indexTemp);
	return indexTemp;
}
</script>

<%
		String returnVal = "";
		String returnValUpdate = "";
		Vector vc = new Vector();
		returnVal = (String)request.getSession().getAttribute("returnVal");
		returnValUpdate = (String)request.getSession().getAttribute("returnValUpdate");
		System.out.println("Show update >> " + returnValUpdate);
		
		System.out.println("Show Role Servlet returnVal : "+returnVal);
		System.out.println("pui >> " +request.getSession().getAttribute("returnVC"));
		//if (null!=request.getSession().getAttribute("returnVC") && !"".equals(request.getSession().getAttribute("returnVC"))){
		if (null!=request.getSession().getAttribute("returnVC")){
			vc  =(Vector) request.getSession().getAttribute("returnVC");
			System.out.println("Show Role Servlet menu : "+vc.size());
		}
		
 %>
 
<form name="myForm" method="post" action="/EJDAWeb/EJDAControler">
<input type="hidden" name="ejdaAction" value=""> 
<input type="hidden" name="ejdaMethod" value=""> 
<input type="hidden" name="screenName" value="">

<input type="hidden" name="func_type" value="">

   <table align="center" width="800" border="0" cellspacing="0" cellpadding="0">
	    <tr>
                    <td height="10" align="center"><table width="800" border="0" cellspacing="1" cellpadding="1">
                       <tr>
                      	<td height="30"><table width="800" cellspacing="1" cellpadding="1">
                      	  <tr bgcolor="#003399">
                      	  	<td height="5" colspan="3"></td> 
                      	  </tr>
                      	  <tr>
                      	  	<td height="10"colspan="3"></td>
                      	  </tr>
                      	  <%if ((null==returnVal  || "".equals(returnVal)) && (null==returnValUpdate  || "".equals(returnValUpdate))){ %>
                      	  <tr>
                      	    <td align="left"><font class="text">Role Name : </font>
                      	    		<input type="text" name="rolename" id="rolename" value="">
                      	    		
                      	    		<input type="button" name="search" id="search" value="Search" onclick="searchButton(this.form)">
                      	    </td>
                      	    <td colspan="2">&nbsp;</td>
                   	      </tr>
                      	  <tr>
                      	    <td colspan="3">&nbsp;</td>
                   	      </tr>
                      	  <tr>
                      	    <td >&nbsp;</td>
                      	    <td colspan="2" align="right"><input type="button" name="add" id="add" value="New Role" onclick="NewRole(this.form)"></td>
                      	    <input type="hidden" name="cType" id="cType" value="">
                   	      </tr>
                   	       <tr>
                      	    <td colspan="3" height="10"></td>
                   	      </tr>
	                   	      <%if(null!=vc){%>
	                   	       <tr>
	                      	    <td colspan="3" align="center"><table width="800" cellspacing="1" cellpadding="1">
	                      	    <%
	                   	    	  for (int i = 0; i< vc.size();i++){
	                   	    		RoleModel rm = (RoleModel)vc.get(i);
	                   	    	%>
	                   	    		<tr>
	                   	    		<td><font class="text"> <%=rm.getRole_name() %></font></td>
	                   	    		<input type="hidden" name="hrole_name" value=""></input>
	                   	    		<td><font class="text"> <%=rm.getShow_edit() %></font></td>
	                   	    		<td><font class="text"> <%=rm.getShow_del()%></font></td>
	                   	    		<td><font class="text"><input type = "button" name="test" value="test"onclick ="EditRole(this.form,'<%=rm.getRole_id() %>','<%=rm.getRole_name() %>')"></img></font></td>
	                   	    		</tr>
	                   	      		
	                      	    	
	                      	    	<%
	                      	    	
	                   	    	  } %>
	                      	    </table>
	                      	    		
	                      	    		
	                      	    </td>
	                      	    <td colspan="2">&nbsp;</td>
	                   	      </tr>
	                   	      <%	 }%>
                   	      <%}else if (null!=returnVal && !"".equals(returnVal)){ %>
                   	       <tr>
                      	    <td colspan="3">&nbsp;</td>
                   	      <tr>
                      	    <td align="left"><font class="text">Role Name : </font>
                      	    		<input type="text" name="rolename" id="rolename" value="">
                      	    		
                      	    </td>
                      	    <td colspan="2">&nbsp;</td>
                   	      </tr>
                   	       <tr>
                      	    <td colspan="3" height="10">&nbsp;</td>
                   	      </tr>
						   <tr>
                      	    
                      	    <td colspan="3" align="center"><%=returnVal %></td>
                   	      </tr>
                   	      <tr>
						    <td align="right"></td>
						    <td colspan="2" align="right" width="100">
						      <input type="button" name="add" id="add" value="Add Role" onClick="AddRole(this.form)">
						      <input type="hidden" name="cType" id="cType" value="">
						      <input type="button" name="Reset" id="Reset" value="Reset" onClick="Reset()">
						    </td>
						    
						  </tr>
                   	      <%
                   	   			
                   	      }else if (null!=returnValUpdate ){
                   	    	String role_name = (String)request.getSession().getAttribute("hidrolename");                   	      
                   	      %>
                   	       <tr>
                      	    <td colspan="3">&nbsp;</td>
                   	      <tr>
                      	    <td align="left"><font class="text">Role Name : </font>
                      	    		<input type="text" name="rolename" id="rolename" value="<%=role_name %>">
                      	    		
                      	    </td>
                      	    <td colspan="2"></td>
                   	      </tr>
                   	       <tr>
                      	    <td colspan="3" height="10"></td>
                   	      </tr>
						   <tr>
                      	    <%System.out.println("returnValUpdate : "+returnValUpdate); %>
                      	    <td colspan="3" align="center"><%=returnValUpdate %></td>
                   	      </tr>
                   	      <tr>
                      	    <td colspan="3" height="10"></td>
                   	      </tr>
                   	      <tr>
						    <td align="right"></td>
						    <td colspan="2" align="right" width="100">
						      <input type="button" name="add" id="add" value="Update Role" onClick="UpdateRole(this.form)">
						      <input type="hidden" name="cType" id="cType" value="">
						      <input type="button" name="Reset" id="Reset" value="Reset" onClick="Reset()">
						    </td>
						    
						  </tr>
                   	      
                   	      <%} %>

                   	     </table></td>
                      </tr> 
                    </table></td>
                </tr> 
                <tr bgcolor="#003399">
                 	  	<td height="1"></td>
                </tr>
                 <tr>
                    <td align="right" ><font class="textError"> Authorized by veena</font></td>
                </tr>
	    </table>

</form>

<script type="text/javascript">
$('input[name=menuid]').each(function(){
	$('input[name=inqs'+$(this).val()+']').each(function(){			
		
		if (null!=$(this).val() && $(this).val() == 'Y'){
			$(this).attr('checked', true) ;
		}
		
	});
	$('input[name=add'+$(this).val()+']').each(function(){			
		
		if (null!=$(this).val() && $(this).val() == 'Y'){
			$(this).attr('checked', true) ;
		}
		
	});
	$('input[name=upd'+$(this).val()+']').each(function(){			
		
		if (null!=$(this).val() && $(this).val() == 'Y'){
			$(this).attr('checked', true) ;
		}
		
	});
	$('input[name=del'+$(this).val()+']').each(function(){			
		
		if (null!=$(this).val() && $(this).val() == 'Y'){
			$(this).attr('checked', true) ;
		}
		
	});
});

</script>
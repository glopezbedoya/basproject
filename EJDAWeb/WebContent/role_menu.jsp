<%@page contentType="text/html;charset=UTF-8"%>
    <%@page import="java.util.Vector"%>
    <%@page import="com.tcd.ejda.model.RoleModel" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="com.ejda.util.DisplayUtil"%>
<%@page import="com.tcd.ejda.model.ValueListModel"%>
<%@page import="com.ejda.sessionBean.RoleBean"%>

<%@page import="org.apache.log4j.Logger"%><script type="text/javascript">
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
	document.myForm.submit();
	//form.submit();
	
}
function NewRole(form){
	
	$('input[name=ejdaAction]').val('Role');
	$('input[name=ejdaMethod]').val('doNew');
	$('input[name=screenName]').val('role_menu.jsp');
	document.myForm.submit();
	//form.submit();
	
}
function DeleteRole(form,role_id,role_name){
	//alert('EditRole');
	//alert('role_id : ' +role_id);
	if (confirm('Do you delete this role.')) { 
		$('input[name=role_id]').val(role_id);
		$('input[name=hrole_name]').val(role_name);
		$('input[name=ejdaAction]').val('Role');
		$('input[name=ejdaMethod]').val('doDelete');
		$('input[name=screenName]').val('role_menu.jsp');
		document.myForm.submit();
	}
	
	//form.submit();
	
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

function changePage(page,form){
	$('input[name=ejdaAction]').val('Role');
	$('input[name=ejdaMethod]').val('doSearch');
	$('input[name=screenName]').val('role_menu.jsp');
	$('input[name=page]').val(page);
	form.submit();
}
function changeSelectPage(form){
	$('input[name=ejdaAction]').val('Role');
	$('input[name=ejdaMethod]').val('doSearch');
	$('input[name=screenName]').val('role_menu.jsp');
	$('input[name=page]').val($('select[name=selectPaging]').val());
	form.submit();
}
function checkAllList(name) {
	
	var hasCheckAll = true;
		if ($('input[name='+name+']').attr('checked')){
			 $('input[name=menuid]').each(function(){
				$('input[name='+name+$(this).val()+']').each(function(){
					if(!$(this).attr('disabled')){
						$(this).attr('checked',true);
					}
				});
			 });
		}else{
			$('input[name=menuid]').each(function(){
				$('input[name='+name+$(this).val()+']').each(function(){
					$(this).attr('checked',false)
				});
			});
		}
	
}
function cancleDeleteAllCheckBox(obj,name){
	
	var hasCheckAll = true;
	$('input[name=menuid]').each(function(){
		$('input[name='+name+$(this).val()+']').each(function(){
			if(!$(this).attr('checked')){
				hasCheckAll = false;
			}
		});
		if(hasCheckAll){
			$('input[name='+name+']').attr('checked',true);
			//document.masterForm.option.checked = true;
		}else{
			$('input[name='+name+']').attr('checked',false);
			//document.masterForm.option.checked = false;
		}
	});
	
}
function checkAll() {
	
	var hasCheckAll = true;
		if ($('input[name=all]').attr('checked')){
			 $('input[name=menuid]').each(function(){
				$('input[name=inqs'+$(this).val()+']').each(function(){
					if(!$(this).attr('disabled')){
						$(this).attr('checked',true);
					}
				});
				$('input[name=upd'+$(this).val()+']').each(function(){
					if(!$(this).attr('disabled')){
						$(this).attr('checked',true);
					}
				});
				$('input[name=del'+$(this).val()+']').each(function(){
					if(!$(this).attr('disabled')){
						$(this).attr('checked',true);
					}
				});
				$('input[name=add'+$(this).val()+']').each(function(){
					if(!$(this).attr('disabled')){
						$(this).attr('checked',true);
					}
				});
			 });
			 
		}else{
			$('input[name=menuid]').each(function(){
				$('input[name=inqs'+$(this).val()+']').each(function(){
					$(this).attr('checked',false)
				});
				$('input[name=add'+$(this).val()+']').each(function(){
					$(this).attr('checked',false)
				});
				$('input[name=del'+$(this).val()+']').each(function(){
					$(this).attr('checked',false)
				});
				$('input[name=upd'+$(this).val()+']').each(function(){
					$(this).attr('checked',false)
				});
			});
		}
	
}
function cancleDeleteAll(obj){
	
	var hasCheckAll = true;
	$('input[name=menuid]').each(function(){
		$('input[name=inqs'+$(this).val()+']').each(function(){
			if(!$(this).attr('checked')){
				hasCheckAll = false;
			}
		});
		$('input[name=add'+$(this).val()+']').each(function(){
			if(!$(this).attr('checked')){
				hasCheckAll = false;
			}
		});
		$('input[name=upd'+$(this).val()+']').each(function(){
			if(!$(this).attr('checked')){
				hasCheckAll = false;
			}
		});
		$('input[name=del'+$(this).val()+']').each(function(){
			if(!$(this).attr('checked')){
				hasCheckAll = false;
			}
		});
		if(hasCheckAll){
			$('input[name=all]').attr('checked',true);
			//document.masterForm.option.checked = true;
		}else{
			$('input[name=all]').attr('checked',false);
			//document.masterForm.option.checked = false;
		}
	});
	
}
</script>

<%
		Logger log = Logger.getLogger("JspLog");
		String returnVal = "";
		String returnValUpdate = "";
		Vector vc = new Vector();
		
		String bgColor1 = "bordercolor=\"#F4F4F4\"";
		String bgColor2 = "bgcolor=\"#DFEFFF\"";
		
		
		returnVal = (String)request.getSession().getAttribute("returnVal");
		returnValUpdate = (String)request.getSession().getAttribute("returnValUpdate");
		RoleBean roleBean = (RoleBean)request.getSession().getAttribute("roleBean");
		
		RoleModel roles = roleBean.getRoleMSP();
		log.debug("roles test >>> " + DisplayUtil.displayInputTextBox("role_name",roles.getRole_name(),"") );
		log.debug("Show update >> " + returnValUpdate);
		
		log.debug("Show Role Servlet returnVal : "+returnVal);
		log.debug("pui >> " +request.getSession().getAttribute("returnVC"));
		//if (null!=request.getSession().getAttribute("returnVC") && !"".equals(request.getSession().getAttribute("returnVC"))){
		/*if (null!=roleBean.getRoleVt()){
			vc  =(Vector) request.getSession().getAttribute("returnVC");
			log.debug("Show Role Servlet menu : "+vc.size());
		}*/
		
		log.debug("roleBean = "+roleBean);
		
		if (null!=roleBean.getRoleVt()){
			vc  =(Vector) roleBean.getRoleVt();
			log.debug("Show Role Servlet menu : "+vc.size());
		}
		
		ValueListModel valueListM = roleBean.getValueListM();
		if(null == valueListM) valueListM = new ValueListModel();
		
 %>
 
<form name="myForm" method="post" action="/EJDAWeb/EJDAControler">
<input type="hidden" name="ejdaAction" value=""> 
<input type="hidden" name="ejdaMethod" value=""> 
<input type="hidden" name="screenName" value="">
<input type="hidden" name="page" value="<%=valueListM.getAtPage() %>" />
<input type="hidden" name="volumePerPage" value="<%=valueListM.getItemsPerPage() %>" />

<input type="hidden" name="func_type" value="">

   <table align="center" width="800" border="0" cellspacing="0" cellpadding="0">
	    <tr>
                    <td height="10" align="center"><table width="800" border="0" cellspacing="1" cellpadding="1">
                       <tr>
                      	<td height="30"><table width="800" cellspacing="1" cellpadding="1">
                      	 
                      	 
                      	  <%if ((null==returnVal  || "".equals(returnVal)) && (null==returnValUpdate  || "".equals(returnValUpdate))){ %>
                      	  <tr>
                      	    <td align="left"><font class="text">Role Name : </font>
                      	    		
                      	    		<%=DisplayUtil.displayInputTextBox("txtRoleName",roles.getRole_name(),"") %>
                      	    		<input type="button" name="search" id="search" value="Search" onclick="searchButton(this.form)">
                      	    </td>
                      	    <td colspan="2">&nbsp;</td>
                   	      </tr>
                      	  <tr>
                      	    <td colspan="3">&nbsp;</td>
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
					          <td align="right">จำนวนที่พบ<%=valueListM.getCount() %> รายการ</td>
					        </tr>
                   	      
                   	       <tr>
                      	    <td colspan="3" height="10"></td>
                   	      </tr>
	                   	      	<tr>
                      	    <td >&nbsp;</td>
                      	    <td colspan="2" align="right"><input type="button" name="add" id="add" value="New Role" onclick="NewRole(this.form)"></td>
                      	    <input type="hidden" name="cType" id="cType" value="">
                   	      </tr>
	                   	       <tr>
	                      	    <td colspan="3" align="center"><table width="800" cellspacing="1" cellpadding="1">
	                      	   	<tr  bgcolor="#003366">
	                   	    		<td ><font class="textHeader">Role Name</font></td>
	                   	    		
	                   	    		<td ><font class="textHeader">Edit Role</font></td>
	                   	    		<td ><font class="textHeader"> Delete Role</font></td>
	                   	    		
	                   	    		</tr>
	                      	    <%
	                   	    	  for (int i = 0; i< vc.size();i++){
	                   	    		String bgColor = "";
	                   	    		RoleModel rm = (RoleModel)vc.get(i);
	                   	    		bgColor = (i%2 == 0)?bgColor1:bgColor2;
	                   	    	%>
	                   	    		<tr>
	                   	    		<td <%=bgColor %>><font class="text"> <%=rm.getRole_name() %></font></td>
	                   	    		<input type="hidden" name="hrole_name" value="">
	                   	    		<input type="hidden" name="role_id" value="">
	                   	    		<td <%=bgColor %>><font class="text"><img src="images/edit.JPG" name="edit" id="edit" value="Edit" style="cursor:hand"onclick="EditRole(this.form,'<%=rm.getRole_id() %>','<%=rm.getRole_name() %>')"></font></td>
	                   	    		<td <%=bgColor %>><font class="text"> <img src="images/delete.JPG" name="delete" id="delete" value="delete"style="cursor:hand" onclick="DeleteRole(this.form,'<%=rm.getRole_id() %>','<%=rm.getRole_name() %>')"></font></td>
	                   	    		
	                   	    		</tr>
	                   	      		
	                      	    	
	                      	    	<%
	                      	    	
	                   	    	  } %>
	                   	    	  <tr>
	                   	    	  	<td height="20" colspan="3"></td>
	                   	    	  </tr> 
	                      	    </table>
	                      	    		
	                      	    		
	                      	    </td>
	                      	    <td colspan="2" height="50">&nbsp;</td>
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
                   	   			request.getSession().setAttribute("returnVal","");
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
                   	      	
                      	    <td align="center"colspan="3"><font class="text"><input type="checkbox" name="all" id="all" value="" onclick="checkAll()">Select All</font>
                      	    
                      	    		
                      	    </td>
                      	   <tr>
                   	      	
                      	    <td align="center"colspan="3"><font class="textTop">Permission</font>
                      	    
                      	    		
                      	    </td>  
                   	      </tr>
						   <tr>
                      	    <%log.debug("returnValUpdate : "+returnValUpdate); %>
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
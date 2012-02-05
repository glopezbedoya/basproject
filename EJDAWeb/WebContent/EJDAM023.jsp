<%@page contentType="text/html;charset=UTF-8"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="java.util.Vector"%>
<%@page import="com.ejda.sessionBean.FormConfigBean"%>
<%@page import="com.tcd.ejda.model.FormConfigModel"%>
<%@page import="com.ejda.util.DisplayUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@page import="com.ejda.util.DisplayFormatUtil"%>
<script language="javascript" src="js/EJDAM023.js"></script>
<%
	Logger log = Logger.getLogger("JspLog");
	String bgColor1 = "bordercolor=\"#F4F4F4\"";
	String bgColor2 = "bgcolor=\"#DFEFFF\"";
	
	FormConfigBean formConfigBean = (FormConfigBean)request.getSession().getAttribute("formConfigBean");
	log.debug("formConfigBean :: " + formConfigBean);
	Vector formConfigVt = formConfigBean.getFormConfigVt();
%>
<form name="FormConfig" method="post" action="/EJDAWeb/EJDAControler">
	<input type="hidden" name="ejdaAction" value=""> 
	<input type="hidden" name="ejdaMethod" value=""> 
	<input type="hidden" name="screenName" value="">
	<input type="hidden" name="jda_type" value="<%=formConfigBean.getAction() %>">

	<table align="center" width="800" border="0" cellspacing="0" cellpadding="0">
		
        <tr align="left">
          <td align="right" class="style1" scope="row">&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
      
        <tr>
          <th scope="row">&nbsp;</th>
          <td><td align="left"><select id="DdlAddForm" name="DdlAddForm">
       		<option value="1">EJDA NO. 1</option>
       		<option value="2">EJDA NO. 2</option>
       		<option value="3">EJDA NO. 3</option>
       		<option value="4">EJDA NO. 4</option>
       		</select></td>
          <td><%=DisplayFormatUtil.displayButton("AddForm","onclick=\"buttonAction(this.form,'EJDAM023','doAdd','EJDAM023.jsp')\"",false) %></td>
          <td>&nbsp;</td>
        </tr>
        
        <tr>
          <th scope="row">&nbsp;</th>
 			<td align="center"></td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <%if (!formConfigBean.getAction().equalsIgnoreCase("0")){ %>
	         <%
	        if(formConfigVt != null && formConfigVt.size() > 0){
	        	
		    		
		    		
	        	%>
	        	<tr><td colspan="3" align="center" >
	        	<table width="600" border="0" cellspacing="1" cellpadding="1">
	        	<tr>
			        <td colspan="3" align="left"><font class="text">EJDA Form no. <%=formConfigBean.getAction() %></font></td>
			    </tr>
	        	<tr>
			        <td width="50" bgcolor="#0099CC"><font class="text">No.</font></td>
			        <td width="250" bgcolor="#0099CC"><font class="text">Field Name</font></td>
			        <td width="150" bgcolor="#0099CC"><font class="text">Permission</font></td>
			    </tr>
			        	<%
			        	String bgColor = "";
			        	for(int i=0;i<formConfigVt.size();i++){
			        		
			        		FormConfigModel cm = (FormConfigModel)formConfigVt.get(i); 
			        		bgColor = (i%2 == 0)?bgColor1:bgColor2;
			        %>
						
						<tr>
					        <td <%=bgColor %> width="50"><font class="text"></font></td>
					        <input type="hidden" name="input_field" value="<%=cm.getInput_field() %>">
					        <td <%=bgColor %> width="250" align="left"><font class="text"><%=cm.getName_field() %></font></td>
					        <td <%=bgColor %> width="150"><font class="text"><%=DisplayUtil.displayCheckBoxForFormConfig(formConfigBean.getFormPermissionVt(),"PERMISSIONS",cm.getInput_field(),"") %></font></td>
			        </tr>
			        
			        	<%} %>
		        	<tr>
				        <td width="50"><font class="text"></font></td>
				        <td width="250" align="right"><font class="text"><input type="button" name="Submit" id="Submit" value="  Submit  "  onclick="validateSubmitButton(this.form,'EJDAM023')"/></font></td>
				        <td width="150" align="left"><font class="text"><input type="button" name="Cancel" id="Cancel" value="  Cancel  " onclick="CancelButton(this.form,'EJDAM023')"/></font></td>
			        </tr>
			     </table>
	        	</td></tr>
	        	
	        <%} %>    
        <%} %>
   		
		<tr>
          <th scope="row">&nbsp;</th>
 			<td align="center"></td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
	</table>



</form>
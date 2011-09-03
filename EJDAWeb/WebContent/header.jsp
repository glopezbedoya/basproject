<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

 <%

        System.out.println("[--- Calling to Servlet for test ---]");

        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/MenuServlet");
        dispatcher.include(request, response);

		String showMenu = (String)request.getAttribute("MyMenu");
		System.out.println("Show menu : " + showMenu);
		
%>

  <body>
    <script type="text/javascript">
	function displayResponseMessage(message) {
		jQuery.modaldialog.success(message,{
			timeout: 2
			, showClose: false
		});
	}
	</script>
  	<table align="center" width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
	    <table align="center" width="800" border="0" cellspacing="0" cellpadding="0">
	      <tr>
	        <th scope="row"><img src="images/header.png" width="941" height="125" />
	      </tr>
	       <tr>
		    	<td bgcolor="#EEEEEE" align="right"><%=showMenu %></td>
		    </tr>
		    <tr bgcolor="#003399">
	       	  	<td height="5" ></td> 
	       	  </tr>
	       	   <tr>
           	  	<td height="10"colspan="3"></td>
           	  </tr>
	    </table>
	   </tr>
	   
 	</table>

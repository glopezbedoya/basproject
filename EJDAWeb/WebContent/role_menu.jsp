<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  
    	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    	<title>eJDA Role Menu</title>
    	<link href="css/styles.css" rel="stylesheet" type="text/css" />
    	 <script src="SpryAssets/SpryMenuBar.js" type="text/javascript"></script>
    	 <link href="SpryAssets/SpryMenuBarHorizontal.css" rel="stylesheet" type="text/css" />
  </head>
 <%

        System.out.println("[--- Calling to Servlet for test ---]");

        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/MenuServlet");
        dispatcher.include(request, response);

		String showMenu = (String)request.getAttribute("MyMenu");
		System.out.println("Show menu : " + showMenu);
		
%>
  
  <body>
  	<table align="center" width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
	    <table align="center" width="800" border="0" cellspacing="0" cellpadding="0">
	      <tr>
	        <th scope="row"><img src="images/header.JPG" width="945" height="146" />
	      </tr>
	    </table>
	   </tr>
	    <table align="center" width="800" border="0" cellspacing="0" cellpadding="0">
	    <tr>
	    	<td><%=showMenu %></td>
	    </tr>
	    </table>
	    <table align="center" width="800" border="0" cellspacing="0" cellpadding="0">
	    <tr>
                    <td height="10" align="center"><table width="800" border="0" cellspacing="1" cellpadding="1">
                       <tr>
                      	<td height="30"><table width="800" cellspacing="1" cellpadding="1">
                      	  <tr bgcolor="#003399">
                      	  	<td height="5"></td>
                      	  </tr>
                      	  <tr>
                      	  	<td height="10"></td>
                      	  </tr>
                      	  <tr>
                      	    <td align="left"><font class="text">ชื่อกลุ่ม : </font>
                      	    		<input type="text" name="rolename" id="rolename" value="">
                      	    		<input type="button" name="search" id="search" value="ค้นหา">
                      	    </td>
                      	    <td>&nbsp;</td>
                   	      </tr>
                      	  <tr>
                      	    <td colspan="2">&nbsp;</td>
                   	      </tr>
                      	  <tr>
                      	    <td>&nbsp;</td>
                      	    <td  align="right"><input type="button" name="add" id="add" value="เพิ่มข้อมูล"></td>
                   	      </tr>
						   <tr>
                      	    <td>&nbsp;</td>
                      	    <td>&nbsp;</td>
                   	      </tr>
                   	     </table></td>
                      </tr> 
                    </table></td>
                </tr> 
                 <tr>
                    <td align="right" >Authorized by veena</td>
                </tr>
	    </table>
	    
	 </table>
	   
   
   

   <script  type="text/javascript">
	
	var MenuBar1 = new Spry.Widget.MenuBar("MenuBar1", {imgDown:"SpryAssets/SpryMenuBarDownHover.gif", 
		imgRight:"SpryAssets/SpryMenuBarRightHover.gif"});
	
	</script>
  </body>
</html> 
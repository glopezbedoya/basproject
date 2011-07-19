<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    	               "http://www.w3.org/TR/html4/loose.dtd">

<html>
  <head>
  
    	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    	<title>eJDA Logon</title>
        <link href="css/styles.css" rel="stylesheet" type="text/css" />
    	<%
	    	String getResult = "";
    		getResult = (String)request.getAttribute("messages");
    		System.out.println("Index >> " + getResult);
    		
    		String errMessage ="กรุณาใส่ User และ Password";
    		if (null != getResult && !"".equals(getResult) && getResult.equals("L")){
    			errMessage ="User ถูก Locked";	
    		}else if (null != getResult &&  !"".equals(getResult) && getResult.equals("A")){
    			errMessage ="User ถูกใช้งานอยู่";	
    		}else if (null != getResult && !"".equals(getResult) && getResult.equals("W")){
    			errMessage ="Password ผิด";	
    		}else if (null != getResult && !"".equals(getResult) && getResult.equals("STATUS")){
    			errMessage ="ไม่พบ User นี้ในระบบ";	
    		}
    		
    		
    		
    	%>
  </head>
 
  
  <body>
  	<table align="center" width="100%" border="0" cellspacing="0" cellpadding="0">
  	<tr><td>
      <table align="center" width="800" border="0" cellspacing="0" cellpadding="0">
	      <tr>
	        <th scope="row"><img src="images/header.JPG" width="945" height="146" />
	      </tr>
      </table>
  	<td></tr>
	<tr><td align="center"> 
	    <table align="center" width="800" border="0" cellspacing="0" cellpadding="0">
	    <tr>
	    	<td height="10"></td>
	    </tr>
	    <tr>
	    	<td></td>
	    </tr>
	    </table>
      </td></tr>
      <tr><td align="center">
                <table align="center" width="800" border="0" cellspacing="0" cellpadding="0">
                <form name="form1" method="post" action="/EJDAWeb/CheckUsernamePasswordServlet">
                <tr>
                    <td height="10" align="center"><table width="400" cellspacing="1" cellpadding="1">
                      <tr>
                      	<td colspan="2" height="30"></td>
                      </tr>
                      <tr>
                        <td align="right" class="text" >User Name :</td>
                        <td  align="left" >
                          
                            <input type="text" name="user" id="user" maxlength="20" value="">
                     
                        </td>
                      </tr>
                       <tr>
                      	<td colspan="2" height="5"></td>
                      </tr> 
                      <tr>
                        <td align="right"><font class="text">Password :</font></td>
                        <td align="left"> <input type="password" name="pwd" id="pwd"  maxlength="20"value=""></td>
                      </tr>
                       <tr>
                      	<td colspan="2" height="5"></td>
                      </tr> 
                      <tr>
                        <td align="right"></td>
                        <td align="left"> <font class="textError">*<%=errMessage %></font></td>
                      </tr>
                      
                      <tr>
                        <td align="right"></td>
                        <td align="left"> <input type="submit" name="logon" id="logon" value="Login" maxlength="20"></td>
                      </tr>
                       <tr>
                      	<td colspan="2" height="30"></td>
                      </tr> 
                    </table></td>
                </tr>
                </form>
                 <tr>
                    <td align="right" >Authorized by veena</td>
                </tr>
	 		</table>
      </td></tr>
      </table>
  </body>
</html> 

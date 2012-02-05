<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="com.ejda.util.EjdaParameterCacheParam"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    	               "http://www.w3.org/TR/html4/loose.dtd">

<html>
  <head>
  
    	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    	<title>eJDA Logon</title>
        <link href="css/styles.css" rel="stylesheet" type="text/css" />
    	<%
    		Logger logger = Logger.getLogger("JspLog");
    		EjdaParameterCacheParam.load(getServletContext().getRealPath("/WEB-INF/EJdaFormParameterConfig.properties"));
    		
	    	String getResult = "";
    		getResult = (String)request.getSession().getAttribute("messages");
    		
    		logger.debug("Index >> " + getResult);
    		    		
    		String errMessage ="";
    		if (null != getResult && !"".equals(getResult) && getResult.equals("L")){
    			errMessage ="*User ถูก Locked";	
    		}else if (null != getResult &&  !"".equals(getResult) && getResult.equals("A")){
    			errMessage ="*User ถูกใช้งานอยู่";	
    		}else if (null != getResult && !"".equals(getResult) && getResult.equals("W")){
    			errMessage ="*Password ผิด";	
    		}else if (null != getResult && !"".equals(getResult) && getResult.equals("STATUS")){
    			errMessage ="*ไม่พบ User นี้ในระบบ";	
    		}
    		request.getSession().removeAttribute("messages");
    		
    		
    	%>
  </head>
 
  
  <body>
  	<table align="center" width="100%" border="0" cellspacing="0" cellpadding="0">
  	<tr><td>
      <table align="center" width="800" border="0" cellspacing="0" cellpadding="0">
	      <tr>
	        <th scope="row"><img src="images/header.png" width="941" height="125" />
	      </tr>
      </table>
  	<td></tr>
	
      <tr><td align="center">
                <table align="center" width="800" border="0" cellspacing="0" cellpadding="0" >
                <form name="form1" method="post" action="/EJDAWeb/EJDAControler">
                	<input type="hidden" name="ejdaAction" value="CheckUsernamePassword"> 
					<input type="hidden" name="ejdaMethod" value="checkUser"> 
					<input type="hidden" name="screenName" value="">
				<tr>
					<td height="50"></td>
				</tr>	
                <tr>
                	
                    <td height="10" align="center"><table width="400" cellspacing="1" cellpadding="1" background="images/u2.png">
                     
                      <tr>
                      	<td align="center" colspan="2" height="30"class="textTop">Please submit User name and Password</td>
                      </tr>
                      <tr>
                      	<td align="center" colspan="2" height="30"class="text"></td>
                      </tr>
                      <tr>
                        <td align="right" class="textMenu" >User Name :</td>
                        <td  align="left" >
                          
                            <input type="text" name="user" id="user" maxlength="20" value=""size="20">
                     
                        </td>
                      </tr>
                       <tr>
                      	<td colspan="2" height="5"></td>
                      </tr> 
                      <tr>
                        <td align="right"><font class="textMenu">Password :</font></td>
                        <td align="left"> <input type="password" name="pwd" id="pwd"  maxlength="20"value="" size="20"></td>
                      </tr>
                       <tr>
                      	<td colspan="2" height="15"></td>
                      </tr> 
                      <tr>
                        <td align="center" colspan="2" class="textError" ><%=errMessage %></td>
                        
                      </tr>
                       <tr>
                      	<td colspan="2" height="10"></td>
                      </tr> 
                      <tr>
                        
                        <td colspan="2" align="center"> <input type="submit" name="logon" id="logon" value="   Login   " maxlength="20" size="20"></td>
                      </tr>
                       <tr>
                      	<td colspan="2" height="30"></td>
                      </tr> 
                    </table></td>
                </tr>
                </form>
                 
	 		</table>
      </td></tr>
      </table>
  </body>
</html> 

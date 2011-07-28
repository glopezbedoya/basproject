<%@page contentType="text/html;charset=UTF-8"%>
<%@page import="org.apache.log4j.Logger"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="com.ejda.constant.EJDAConstant"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>eJDA Role Menu</title>
	<link href="css/styles.css" rel="stylesheet" type="text/css" />
	<script src="SpryAssets/SpryMenuBar.js" type="text/javascript"></script>
	<link href="SpryAssets/SpryMenuBarHorizontal.css" rel="stylesheet" type="text/css" />
	<script language="javascript" src="js/jquery-1.6.2.min.js" type="text/javascript" ></script>
	<script language="javascript" src="js/ejdaScript.js" type="text/javascript" ></script>
	<script language="javascript" src="js/DateFormat.js"></script>
	<script language="javascript" src="js/popcalendar.js"></script>
</head>


<body>
<% 
	Logger log = Logger.getLogger("JspLog");
	log.debug("Page = "+(String)request.getSession().getAttribute(EJDAConstant.SESSION_NAME.PAGE));
	String screenName = (String)request.getSession().getAttribute(EJDAConstant.SESSION_NAME.PAGE);
	//log.debug("req = "+request.getSession().getAttribute("screenName"));
	/*if(request.getParameter("screenName") != null){
		screenName = (String)request.getParameter("screenName");
	}
	log.debug("screenName = "+screenName);*/
		

%>
<jsp:include page="header.jsp" flush="true" />
<jsp:include page="<%=screenName%>" flush="true" />

<script  type="text/javascript">
	var MenuBar1 = new Spry.Widget.MenuBar("MenuBar1", {imgDown:"SpryAssets/SpryMenuBarDownHover.gif", 
		imgRight:"SpryAssets/SpryMenuBarRightHover.gif"});
</script>
 		<table align="center" width="800" border="0" cellspacing="0" cellpadding="0">
		   
		     <tr bgcolor="#003399">
                 	  	<td height="1"></td>
                </tr>
                 <tr>
                    <td align="right" ><font class="textError"> Authorized by Thai Custom Department</font></td>
                </tr>
	    </table>
</body>
</html>
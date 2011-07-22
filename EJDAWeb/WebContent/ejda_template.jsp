<%@page contentType="text/html;charset=UTF-8"%>
<%@page import="org.apache.log4j.Logger"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="com.ejda.constant.EJDAConstant"%><html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>eJDA Role Menu</title>
	<link href="css/styles.css" rel="stylesheet" type="text/css" />
	<script src="SpryAssets/SpryMenuBar.js" type="text/javascript"></script>
	<link href="SpryAssets/SpryMenuBarHorizontal.css" rel="stylesheet" type="text/css" />
</head>
<body>
<% 
	Logger log = Logger.getLogger("JspLog");
	log.debug("Page = "+(String)request.getSession().getAttribute(EJDAConstant.SESSION_NAME.PAGE));

%>
<jsp:include page="header.jsp" flush="true" />
<jsp:include page="<%=(String)request.getSession().getAttribute(EJDAConstant.SESSION_NAME.PAGE)%>" flush="true" />

<script  type="text/javascript">
	var MenuBar1 = new Spry.Widget.MenuBar("MenuBar1", {imgDown:"SpryAssets/SpryMenuBarDownHover.gif", 
		imgRight:"SpryAssets/SpryMenuBarRightHover.gif"});
</script>
</body>
</html>
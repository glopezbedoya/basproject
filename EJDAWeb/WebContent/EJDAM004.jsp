
<%@page import="org.apache.log4j.Logger"%>
<%@page import="com.ejda.util.EjdaParameterCacheParam"%>


<%@page import="com.ejda.util.DisplayFormatUtil"%>
<%@page import="com.ejda.util.DisplayUtil"%><script language="javascript" src="js/EJDAM004.js"></script>
<%
	Logger log = Logger.getLogger("JspLog");
%>
<form name="reportForm" method="post" action="/EJDAWeb/EJDAControler">
	<table align="center" width="1200" border="0" bgcolor="#F8F8F8">
	  <tr>
	    <td >        
	        <table align="center" width="95%" border="0">
	          <tr>
	           	<td>
	            	<table width="80%" border="0">
	                  <tr>
	                     <td align="right"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("reportType"))%></td>
	                    <td><select name="reportType" onchange="checkType()">
	                    		<option value="">select</option>
	                    		<option value="ALL"><%=EjdaParameterCacheParam.getValue("showAllReport") %></option>
	                    		<option value="1">JDA1</option>
	                    		<option value="2">JDA2</option>
	                    		<option value="3">JDA3</option>
	                    		<option value="4">JDA4</option>
	                    	</select>
	                    </td>
	                    <td align="right"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("from"))%></td>
	                    <td><%=DisplayFormatUtil.displayTextBoxCalendar("txtDateFrom","") %></td>
	                    <td align="right"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("to"))%></td>
	                    <td><%=DisplayFormatUtil.displayTextBoxCalendar("txtDateTo","") %></td>
	                    <td name="jda1" style="display:none;">
	                    	<input type="radio" name="group1" value="A"> <%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("combobox_garuntee"))%>
	                        <input type="radio" name="group1" value="B"> <%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("combobox_pay_vat"))%>
	                    </td>
	                    <td name="jda2" style="display:none;">
                        	<input type="radio" name="group2" value="A"> <%=DisplayUtil.displayLabel("textDescBlack","Condensate")%>
	                        <input type="radio" name="group2" value="B"> <%=DisplayUtil.displayLabel("textDescBlack","Natural Gas")%>
	                        <input type="radio" name="group2" value="c"> <%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("combobox_equipment"))%>
	                    </td>
	                    <td name="jda3" style="display:none;">
                        	<input type="radio" name="group3" value="A"> <%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("combobox_in"))%>
                        	<input type="radio" name="group3" value="B"> <%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("conbobox_out"))%>
	                    </td>
	                  </tr>
	                </table>
	            </td>
	        </tr>
	        <tr>
	            <td>
	            	<div name="reportAll" style="display:none;">
		            	<table width="100%" border="0" bgcolor="#FFFFFF">
		                  <tr>
		                    <td align="center" colspan="11"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("goverment"))%></td>
		                    <td align="right" ><img src="images/print.png" height="30" width="30"/></td>
		                  </tr>
		                  <tr>
		                  	<td align="center" colspan="11"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("reportStat"))%></td>
		                    <td>&nbsp;</td>
		                  </tr>
		                  <tr>
		                  	<td align="center" colspan="11"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("ofMonth"))%></td>
		                    <td>&nbsp;</td>
		                  </tr>
		                  <tr>
		                  	<td align="right" colspan="12">
		                  		<%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("all"))%>
		                  		<%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("count"))%>
							</td>
		                  </tr>
		                  <tr>
		                  	<td align="left" colspan="12"><img src="images/line.png" width="100%"/></td>
		                  </tr>
		                  <tr>
		                  	<td align="center"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("no"))%></td>
		                    <td align="center"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("listReport"))%></td>
		                    <td align="center"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("qty_each_one"))%></td>
		                    <td align="center"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("qty_box"))%></td>
		                    <td align="center"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("weight"))%></td>
		                    <td align="center"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("price"))%></td>
		                    <td align="center"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("garuntee"))%></td>
		                    <td align="center"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("arkorn_stamp"))%></td>
		                    <td align="center"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("vat"))%></td>
		                    <td align="center"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("vat_sappasamit"))%></td>
		                    <td align="center"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("vat_mahardthai"))%></td>
		                    <td align="center"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("commission"))%></td>
		                  </tr>
		                  <tr>
		                  	<td align="left" colspan="12"><img src="images/line.png" width="100%"/></td>
		                  </tr>
		                  <tr>
		                  	<td align="center">1</td>
		                    <td align="center">1</td>
		                    <td align="center">1</td>
		                    <td align="center">1</td>
		                    <td align="center">1</td>
		                    <td align="center">1</td>
		                    <td align="center">1</td>
		                    <td align="center">1</td>
		                    <td align="center">1</td>
		                    <td align="center">1</td>
		                    <td align="center">1</td>
		                    <td align="center">1</td>
		                  </tr>
		                  <tr>
		                  	<td align="left" colspan="12"><img src="images/line.png" width="100%"/></td>
		                  </tr>
		                  <tr>
		                  	<td>&nbsp;</td>
		                    <td align="center"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("total"))%></td>
		                  </tr>
		                  <tr>
		                  	<td align="left" colspan="12"><img src="images/line.png" width="100%"/></td>
		                  </tr>
		                </table>
		               </div>
		               <div name="reportJDA1" style="display:none;">
		               	<table width="100%" border="0" bgcolor="#FFFFFF">
		                  <tr>
		                    <td align="center" colspan="5"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("goverment"))%></td>
		                    <td align="right" ><img src="images/print.png" height="30" width="30"/></td>
		                  </tr>
		                  <tr>
		                  	<td align="center" colspan="5"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("reportStatJDA1"))%></td>
		                    <td>&nbsp;</td>
		                  </tr>
		                  <tr>
		                  	<td align="center" colspan="5"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("ofMonth"))%></td>
		                    <td>&nbsp;</td>
		                  </tr>
		                  <tr>
		                  	<td align="right" colspan="6">
		                  		<%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("all"))%>
		                  		<%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("count"))%>
							</td>
		                  </tr>
		                  <tr>
		                  	<td align="left" colspan="6"><img src="images/line.png" width="100%"/></td>
		                  </tr>
		                  <tr>
		                  	<td align="center"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("no"))%></td>
		                    <td align="center"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("listReport"))%></td>
		                    <td align="center"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("qty_each_one"))%></td>
		                    <td align="center"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("qty_box"))%></td>
		                    <td align="center"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("weight"))%></td>
		                    <td align="center"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("price"))%></td>
		                  </tr>
		                  <tr>
		                  	<td align="left" colspan="6"><img src="images/line.png" width="100%"/></td>
		                  </tr>
		                  <tr>
		                  	<td align="center">1</td>
		                    <td align="center">1</td>
		                    <td align="center">1</td>
		                    <td align="center">1</td>
		                    <td align="center">1</td>
		                    <td align="center">1</td>
		                  </tr>
		                  <tr>
		                  	<td align="left" colspan="6"><img src="images/line.png" width="100%"/></td>
		                  </tr>
		                  <tr>
		                  	<td>&nbsp;</td>
		                    <td align="center"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("total"))%></td>
		                  </tr>
		                  <tr>
		                  	<td align="left" colspan="6"><img src="images/line.png" width="100%"/></td>
		                  </tr>
		                </table>
		               </div>
		               <div name="reportJDA2" style="display:none;">
		               	<table width="100%" border="0" bgcolor="#FFFFFF">
		                  <tr>
		                    <td align="center" colspan="7"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("goverment"))%></td>
		                    <td align="right" ><img src="images/print.png" height="30" width="30"/></td>
		                  </tr>
		                  <tr>
		                  	<td align="center" colspan="7"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("reportStatJDA2"))%></td>
		                    <td>&nbsp;</td>
		                  </tr>
		                  <tr>
		                  	<td align="center" colspan="7"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("ofMonth"))%></td>
		                    <td>&nbsp;</td>
		                  </tr>
		                  <tr>
		                  	<td align="right" colspan="8">
		                  		<%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("all"))%>
		                  		<%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("count"))%>
							</td>
		                  </tr>
		                  <tr>
		                  	<td align="left" colspan="8"><img src="images/line.png" width="100%"/></td>
		                  </tr>
		                  <tr>
		                  	<td align="center"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("no"))%></td>
		                    <td align="center"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("listReport"))%></td>
		                    <td align="center"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("qty_each_one"))%></td>
		                    <td align="center"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("qty_box"))%></td>
		                    <td align="center"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("weight"))%></td>
		                    <td align="center"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("price"))%></td>
		                    <td align="center"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("garuntee"))%></td>
		                    <td align="center"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("vatArkorn"))%></td>
		                  </tr>
		                  <tr>
		                  	<td align="left" colspan="8"><img src="images/line.png" width="100%"/></td>
		                  </tr>
		                  <tr>
		                  	<td align="center">1</td>
		                    <td align="center">1</td>
		                    <td align="center">1</td>
		                    <td align="center">1</td>
		                    <td align="center">1</td>
		                    <td align="center">1</td>
		                    <td align="center">1</td>
		                    <td align="center">1</td>
		                  </tr>
		                  <tr>
		                  	<td align="left" colspan="8"><img src="images/line.png" width="100%"/></td>
		                  </tr>
		                  <tr>
		                  	<td>&nbsp;</td>
		                    <td align="center"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("total"))%></td>
		                  </tr>
		                  <tr>
		                  	<td align="left" colspan="8"><img src="images/line.png" width="100%"/></td>
		                  </tr>
		                </table>
		               </div>
		               <div name="reportJDA3" style="display:none;">
		               	<table width="100%" border="0" bgcolor="#FFFFFF">
		                  <tr>
		                    <td align="center" colspan="5"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("goverment"))%></td>
		                    <td align="right" ><img src="images/print.png" height="30" width="30"/></td>
		                  </tr>
		                  <tr>
		                  	<td align="center" colspan="5"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("reportStatJDA3"))%></td>
		                    <td>&nbsp;</td>
		                  </tr>
		                  <tr>
		                  	<td align="center" colspan="5"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("ofMonth"))%></td>
		                    <td>&nbsp;</td>
		                  </tr>
		                  <tr>
		                  	<td align="right" colspan="6">
		                  		<%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("all"))%>
		                  		<%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("count"))%>
							</td>
		                  </tr>
		                  <tr>
		                  	<td align="left" colspan="6"><img src="images/line.png" width="100%"/></td>
		                  </tr>
		                  <tr>
		                  	<td align="center"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("no"))%></td>
		                    <td align="center"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("listReport"))%></td>
		                    <td align="center"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("qty_each_one"))%></td>
		                    <td align="center"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("qty_box"))%></td>
		                    <td align="center"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("weight"))%></td>
		                    <td align="center"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("price"))%></td>
		                  </tr>
		                  <tr>
		                  	<td align="left" colspan="6"><img src="images/line.png" width="100%"/></td>
		                  </tr>
		                  <tr>
		                  	<td align="center">1</td>
		                    <td align="center">1</td>
		                    <td align="center">1</td>
		                    <td align="center">1</td>
		                    <td align="center">1</td>
		                    <td align="center">1</td>
		                  </tr>
		                  <tr>
		                  	<td align="left" colspan="6"><img src="images/line.png" width="100%"/></td>
		                  </tr>
		                  <tr>
		                  	<td>&nbsp;</td>
		                    <td align="center"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("total"))%></td>
		                  </tr>
		                  <tr>
		                  	<td align="left" colspan="6"><img src="images/line.png" width="100%"/></td>
		                  </tr>
		                </table>
		               </div>
		               <div name="reportJDA4" style="display:none;">
		               	<table width="100%" border="0" bgcolor="#FFFFFF">
		                  <tr>
		                    <td align="center" colspan="5"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("goverment"))%></td>
		                    <td align="right" ><img src="images/print.png" height="30" width="30"/></td>
		                  </tr>
		                  <tr>
		                  	<td align="center" colspan="5"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("reportStatJDA4"))%></td>
		                    <td>&nbsp;</td>
		                  </tr>
		                  <tr>
		                  	<td align="center" colspan="5"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("ofMonth"))%></td>
		                    <td>&nbsp;</td>
		                  </tr>
		                  <tr>
		                  	<td align="right" colspan="6">
		                  		<%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("all"))%>
		                  		<%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("count"))%>
							</td>
		                  </tr>
		                  <tr>
		                  	<td align="left" colspan="6"><img src="images/line.png" width="100%"/></td>
		                  </tr>
		                  <tr>
		                  	<td align="center"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("no"))%></td>
		                    <td align="center"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("date"))%></td>
		                    <td align="center"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("qty_each_one"))%></td>
		                    <td align="center"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("qty_box"))%></td>
		                    <td align="center"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("weight"))%></td>
		                    <td align="center"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("price"))%></td>
		                  </tr>
		                  <tr>
		                  	<td align="left" colspan="6"><img src="images/line.png" width="100%"/></td>
		                  </tr>
		                  <tr>
		                  	<td align="center">1</td>
		                    <td align="center">1</td>
		                    <td align="center">1</td>
		                    <td align="center">1</td>
		                    <td align="center">1</td>
		                    <td align="center">1</td>
		                  </tr>
		                  <tr>
		                  	<td align="left" colspan="6"><img src="images/line.png" width="100%"/></td>
		                  </tr>
		                  <tr>
		                  	<td>&nbsp;</td>
		                    <td align="center"><%=DisplayUtil.displayLabel("textDescBlack",EjdaParameterCacheParam.getValue("total"))%></td>
		                  </tr>
		                  <tr>
		                  	<td align="left" colspan="6"><img src="images/line.png" width="100%"/></td>
		                  </tr>
		                </table>
		               </div>
	
	            </td>
	          </tr>
	          <tr>
	            <td align="center"><input type="button" value="close" /></td>
	          </tr>
	        </table>
	    </td>       
	  </tr>  
</table>
</form>
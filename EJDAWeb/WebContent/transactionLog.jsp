<%@page contentType="text/html;charset=UTF-8"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="java.util.Vector"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="com.tcd.ejda.model.TransactionLogModel"%><script  type="text/javascript">
function searchButton(form){
	
	$('input[name=ejdaAction]').val('TransactionLogAction');
	$('input[name=ejdaMethod]').val('doSearch');
	$('input[name=screenName]').val('transactionLog.jsp');
	form.submit();
	
}

</script>
<%
	Vector tranLogVt = (Vector)request.getSession().getAttribute("TranLogResultSession");
	TransactionLogModel tranLogM = new TransactionLogModel();

%>
<form name="transactionForm" method="post" action="/EJDAWeb/EJDAControler">
	<input type="hidden" name="ejdaAction" value=""> 
	<input type="hidden" name="ejdaMethod" value=""> 
	<input type="hidden" name="screenName" value="">
	<table align="center" width="800" border="0" cellspacing="0" cellpadding="0">
		<tr >
          <th colspan="4" align="left" bgcolor="#3399FF" scope="row"><div align="left"><span class="style1">&gt;&gt; &#3592;&#3633;&#3604;&#3585;&#3634;&#3619;&#3612;&#3641;&#3657;&#3651;&#3594;&#3657;&#3619;&#3632;&#3610;&#3610; &gt;&gt; &#3592;&#3633;&#3604;&#3585;&#3634;&#3619;&#3612;&#3641;&#3657;&#3651;&#3594;&#3657;&#3591;&#3634;&#3609;&#3619;&#3632;&#3610;&#3610;</span> </div></th>
        </tr>
        <tr align="left">
          <th scope="row">&nbsp;</th>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr align="left">
          <th colspan="4" scope="row">
            <p class="style1">วัน เดือน ปี ที่เ้ข้าระบบ </p>
          </th>
        </tr>
        
        <tr>
          <th colspan="4" align="left" class="style1" scope="row"><label>
            <select name="select2" size="1" id="select">
              <option selected="selected">1</option>
              <option>2</option>
              <option>3</option>
              <option>4</option>
              <option>5</option>
              <option>6</option>
              <option>7</option>
              <option>8</option>
              <option>9</option>
              <option>10</option>
              <option>11</option>
              <option>12</option>
              <option>13</option>
              <option>14</option>
              <option>15</option>
              <option>16</option>
              <option>17</option>
              <option>18</option>
              <option>19</option>
              <option>20</option>
              <option>21</option>
              <option>22</option>
              <option>23</option>
              <option>24</option>
              <option>25</option>
              <option>26</option>
              <option>27</option>
              <option>28</option>
              <option>29</option>
              <option>30</option>
              <option>31</option>
            </select>
          </label>
            <select name="select2" size="1" id="select2">
              <option>มกราคม</option>
              <option>กุมภาพันธ์</option>
              <option>มีนาคม</option>
              <option>เมษายน</option>
              <option>พฤษภาคม</option>
              <option>มิถุนายน</option>
              <option>กรกฎาคม</option>
              <option>สิงหาคม</option>
              <option>กันยายน</option>
              <option>ตุลาคม</option>
              <option>พฤศจิกายน</option>
              <option>ธันวาคม</option>
            </select>
            <select name="select3" size="1" id="select3">
              <option>2553</option>
              <option>2554</option>
              <option>2555</option>
              <option>2556</option>
              <option>2557</option>
              <option>2558</option>
          </select> 
            - 
            <label>
              <select name="select4" size="1" id="select4">
                <option selected="selected">1</option>
                <option>2</option>
                <option>3</option>
                <option>4</option>
                <option>5</option>
                <option>6</option>
                <option>7</option>
                <option>8</option>
                <option>9</option>
                <option>10</option>
                <option>11</option>
                <option>12</option>
                <option>13</option>
                <option>14</option>
                <option>15</option>
                <option>16</option>
                <option>17</option>
                <option>18</option>
                <option>19</option>
                <option>20</option>
                <option>21</option>
                <option>22</option>
                <option>23</option>
                <option>24</option>
                <option>25</option>
                <option>26</option>
                <option>27</option>
                <option>28</option>
                <option>29</option>
                <option>30</option>
                <option>31</option>
              </select>
            </label>
            <select name="select4" size="1" id="select5">
              <option>มกราคม</option>
              <option>กุมภาพันธ์</option>
              <option>มีนาคม</option>
              <option>เมษายน</option>
              <option>พฤษภาคม</option>
              <option>มิถุนายน</option>
              <option>กรกฎาคม</option>
              <option>สิงหาคม</option>
              <option>กันยายน</option>
              <option>ตุลาคม</option>
              <option>พฤศจิกายน</option>
              <option>ธันวาคม</option>
            </select>
            <select name="select4" size="1" id="select6">
              <option>2553</option>
              <option>2554</option>
              <option>2555</option>
              <option>2556</option>
              <option>2557</option>
              <option>2558</option>
            </select></th>
        </tr>
        <tr>
	          	<td>
	          		<th align="left" class="style1" scope="row">
	          			<span> User Name :</span>&nbsp;
	          			<input name="txtUserName" type="text" />
	          		</th>	          		
	          	</td>
	          	<td>
	          		<th align="left" class="style1" scope="row">
	          			<span> Department :</span>&nbsp;
	          			<input name="txtDepartment" type="text" />
	          		</th>	          		
	          	</td>
        </tr>
        <tr>
       		<th colspan="4" align="left" class="style1" scope="row">
          		<input type="button" name="search" value="ค้นหา" onclick="searchButton(this.form)" />
       		</th>
         </tr>
        <tr>
          <th colspan="4" scope="row"><div align="right"><span class="style4">&#3649;&#3626;&#3604;&#3591;&#3612;&#3621;&#3585;&#3634;&#3619;&#3588;&#3657;&#3609;&#3627;&#3634; 1/</span>55
              <select name="select">
                <option value="0">&#3648;&#3621;&#3639;&#3629;&#3585;&#3627;&#3609;&#3657;&#3634;</option>
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
              </select>
           		 <input type="submit" name="firstpage" value="&#3627;&#3609;&#3657;&#3634;&#3649;&#3619;&#3585;" />
				  <input type="submit" name="previous" value="&#3585;&#3621;&#3633;&#3610;" />
				   <input type="submit" name="next" value="&#3606;&#3633;&#3604;&#3652;&#3611;" />
				    <input type="submit" name="Lastpage" value="&#3627;&#3621;&#3633;&#3591;&#3626;&#3640;&#3604;" />
          </div></th>
        </tr>
        <tr>
          <th scope="row">&nbsp;</th>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <th scope="row">&nbsp;</th>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td><div align="right">
            <input name="Adduser" type="submit" id="Adduser" value="ลบที่เลือก" />
          </div></td>
        </tr>
        <tr>
          <th scope="row">&nbsp;</th>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>จำนวนที่พบ 1,273 รายการ</td>
        </tr>
        <tr>
          <th colspan="4" scope="row"><table width="951" border="0" align="center" cellpadding="0" cellspacing="1">
            <tr>
              <th width="20" bgcolor="#0099CC" class="style13" scope="row">
                <label>
                  <input type="checkbox" name="checkbox" id="checkbox" />
                </label>
              </th>
              <th width="67" bgcolor="#0099CC" class="style13" scope="row">User Name</th>
              <td width="174" bgcolor="#0099CC" class="style13"><div align="center" class="style14">&#3594;&#3639;&#3656;&#3629; - &#3609;&#3634;&#3617;&#3626;&#3585;&#3640;&#3621; </div></td>
              <td width="154" bgcolor="#0099CC" class="style13"><div align="center" class="style14">หน่วยงาน</div></td>
              <td width="205" bgcolor="#0099CC" class="style13"><div align="center" class="style14">Action</div></td>
              <td width="135" bgcolor="#0099CC" class="style13"><div align="center" class="style14">เวลา</div></td>
              <td width="129" bgcolor="#0099CC" class="style13"><div align="center" class="style14">IP Address</div></td>
            </tr>
            <%
            	if(tranLogVt != null && tranLogVt.size() > 0){
            		for(int i=0;i<tranLogVt.size();i++){
            			tranLogM = (TransactionLogModel)tranLogVt.get(i);            	
            		
            %>
            <tr>
              <th bordercolor="#F4F4F4" scope="row"><input type="checkbox" name="checkbox2" id="checkbox2" /></th>
              <th bordercolor="#F4F4F4" class="style17" scope="row"><%=tranLogM.getTranId() %></th>
              <td bordercolor="#F4F4F4" class="style17"><%=tranLogM.getFirstName() + " " + tranLogM.getLastName() %></td>
              <td bordercolor="#F4F4F4" class="style17"><%=tranLogM.getDepartment() %></td>
              <td bordercolor="#F4F4F4" class="style17"><div align="center" class="style17"><%=tranLogM.getTranAction() %></div></td>
              <td bordercolor="#F4F4F4" class="style17"><div align="center"><%=tranLogM.getTranDate() %></div></td>
              <td bordercolor="#F4F4F4" class="style17"><div align="center"><%=tranLogM.getIpAddress() %></div></td>
            </tr>
            <%		}
            	}
            %>
            <tr>
              <th bordercolor="#F4F4F4" scope="row"><input type="checkbox" name="checkbox2" id="checkbox2" /></th>
              <th bordercolor="#F4F4F4" class="style17" scope="row">admin</th>
              <td bordercolor="#F4F4F4" class="style17">&#3609;&#3634;&#3591;&#3626;&#3634;&#3623;&#3609;&#3623;&#3614;&#3619; &#3649;&#3607;&#3609;&#3610;&#3640;&#3597; </td>
              <td bordercolor="#F4F4F4" class="style17">ฝ่ายบริหารทรัพยากรมนุษย์</td>
              <td bordercolor="#F4F4F4" class="style17"><div align="center" class="style17">เพิ่มข้อมูลการจัดการผู้ใช้งาน</div></td>
              <td bordercolor="#F4F4F4" class="style17"><div align="center">1 &#3608;.&#3588;. 2553 11:16:57</div></td>
              <td bordercolor="#F4F4F4" class="style17"><div align="center">176.254.20.223</div></td>
            </tr>
            <tr>
              <th bgcolor="#DFEFFF" scope="row"><input type="checkbox" name="checkbox3" id="checkbox3" /></th>
              <th bgcolor="#DFEFFF" class="style17" scope="row">officer</th>
              <td bgcolor="#DFEFFF" class="style17"><span class="style17">&#3609;&#3634;&#3591;&#3626;&#3634;&#3623;&#3611;&#3636;&#3618;&#3632;&#3623;&#3604;&#3637; &#3648;&#3585;&#3605;&#3640;&#3611;&#3619;&#3632;&#3648;&#3626;&#3619;&#3636;&#3600; </span></td>
              <td bgcolor="#DFEFFF" class="style17"><span class="style17">ฝ่ายบริหารทรัพยากรมนุษย</span></td>
              <td bgcolor="#DFEFFF" class="style17"><div align="center">แก้ไขการจัดการผู้ใช้งาน</div></td>
              <td bgcolor="#DFEFFF" class="style17"><div align="center">1 &#3608;.&#3588;. 2553 11:16:57</div></td>
              <td bgcolor="#DFEFFF" class="style17"><div align="center">176.254.20.223</div></td>
            </tr>
            <tr>
              <th bordercolor="#F4F4F4" scope="row"><input type="checkbox" name="checkbox4" id="checkbox4" /></th>
              <th bordercolor="#F4F4F4" class="style17" scope="row">admin</th>
              <td bordercolor="#F4F4F4" class="style17">&#3609;&#3634;&#3591;&#3626;&#3634;&#3623;&#3585;&#3609;&#3585;&#3614;&#3619; &#3610;&#3640;&#3597;&#3611;&#3619;&#3632;&#3604;&#3636;&#3625;&#3600;&#3660;  </td>
              <td bordercolor="#F4F4F4" class="style17">ฝ่ายบริหารทรัพยากรมนุษย</td>
              <td bordercolor="#F4F4F4" class="style17"><div align="center" class="style17">ลบการจัดการผู้ใช้งาน</div></td>
              <td class="style17"><div align="center">1 &#3608;.&#3588;. 2553 11:16:57</div></td>
              <td class="style17"><div align="center">176.254.20.223</div></td>
            </tr>
            <tr>
              <th bgcolor="#DFEFFF" scope="row"><input type="checkbox" name="checkbox5" id="checkbox5" /></th>
              <th bgcolor="#DFEFFF" class="style17" scope="row">officer</th>
              <td bgcolor="#DFEFFF" class="style17"><span class="style17">&#3609;&#3634;&#3591;&#3626;&#3634;&#3623;&#3609;&#3636;&#3626;&#3634;&#3585;&#3619; &#3649;&#3607;&#3609;&#3588;&#3640;&#3603; </span></td>
              <td bgcolor="#DFEFFF" class="style17"><span class="style17">ฝ่ายเทคโนโลยีสารสนเทศ</span></td>
              <td bgcolor="#DFEFFF" class="style17"><div align="center">ลบการจัดการผู้ใช้งาน</div></td>
              <td bgcolor="#DFEFFF" class="style17"><div align="center">1 &#3608;.&#3588;. 2553 11:16:57</div></td>
              <td bgcolor="#DFEFFF" class="style17"><div align="center">176.254.20.223</div></td>
            </tr>
            <tr>
              <th bordercolor="#F4F4F4" scope="row"><input type="checkbox" name="checkbox6" id="checkbox6" /></th>
              <th bordercolor="#F4F4F4" class="style17" scope="row">admin</th>
              <td bordercolor="#F4F4F4" class="style17">&#3609;&#3634;&#3591;&#3626;&#3634;&#3623;&#3609;&#3623;&#3614;&#3619; &#3649;&#3607;&#3609;&#3610;&#3640;&#3597; </td>
              <td bordercolor="#F4F4F4" class="style17">ฝ่ายเทคโนโลยีสารสนเทศ</td>
              <td bordercolor="#F4F4F4" class="style17"><div align="center" class="style17">แก้ไขการจัดการผู้ใช้งาน</div></td>
              <td class="style17"><div align="center">1 &#3608;.&#3588;. 2553 11:16:57</div></td>
              <td class="style17"><div align="center">176.254.20.223</div></td>
            </tr>
            <tr>
              <th bgcolor="#DFEFFF" scope="row"><input type="checkbox" name="checkbox7" id="checkbox7" /></th>
              <th bgcolor="#DFEFFF" class="style17" scope="row">officer</th>
              <td bgcolor="#DFEFFF" class="style17"><span class="style17">&#3609;&#3634;&#3591;&#3626;&#3634;&#3623;&#3609;&#3623;&#3614;&#3619; &#3649;&#3607;&#3609;&#3610;&#3640;&#3597; </span></td>
              <td bgcolor="#DFEFFF" class="style17"><span class="style17">ฝ่ายเทคโนโลยีสารสนเทศ</span></td>
              <td bgcolor="#DFEFFF" class="style17"><div align="center">แก้ไขการจัดการผู้ใช้งาน</div></td>
              <td bgcolor="#DFEFFF" class="style17"><div align="center">1 &#3608;.&#3588;. 2553 11:16:57</div></td>
              <td bgcolor="#DFEFFF" class="style17"><div align="center">176.254.20.223</div></td>
            </tr>
            <tr>
              <th bordercolor="#F4F4F4" scope="row"><input type="checkbox" name="checkbox8" id="checkbox8" /></th>
              <th bordercolor="#F4F4F4" class="style17" scope="row">admin</th>
              <td bordercolor="#F4F4F4" class="style17">&#3609;&#3634;&#3591;&#3626;&#3634;&#3623;&#3609;&#3623;&#3614;&#3619; &#3649;&#3607;&#3609;&#3610;&#3640;&#3597; </td>
              <td bordercolor="#F4F4F4" class="style17">ฝ่ายเทคโนโลยีสารสนเทศ</td>
              <td bordercolor="#F4F4F4" class="style17"><div align="center" class="style18"><span class="style17">แก้ไขการจัดการผู้ใช้งาน</span></div></td>
              <td class="style17"><div align="center">1 &#3608;.&#3588;. 2553 11:16:57</div></td>
              <td class="style17"><div align="center">176.254.20.223</div></td>
            </tr>
            <tr>
              <th bgcolor="#DFEFFF" scope="row"><input type="checkbox" name="checkbox9" id="checkbox9" /></th>
              <th bgcolor="#DFEFFF" class="style17" scope="row">officer</th>
              <td bgcolor="#DFEFFF" class="style17"><span class="style17">&#3609;&#3634;&#3591;&#3626;&#3634;&#3623;&#3609;&#3623;&#3614;&#3619; &#3649;&#3607;&#3609;&#3610;&#3640;&#3597; </span></td>
              <td bgcolor="#DFEFFF" class="style17"><span class="style17">ฝ่ายเทคโนโลยีสารสนเทศ</span></td>
              <td bgcolor="#DFEFFF" class="style17"><div align="center">แก้ไขการจัดการผู้ใช้งาน</div></td>
              <td bgcolor="#DFEFFF" class="style17"><div align="center">1 &#3608;.&#3588;. 2553 11:16:57</div></td>
              <td bgcolor="#DFEFFF" class="style17"><div align="center">176.254.20.223</div></td>
            </tr>
            <tr>
              <th bordercolor="#F4F4F4" scope="row"><input type="checkbox" name="checkbox10" id="checkbox10" /></th>
              <th bordercolor="#F4F4F4" class="style17" scope="row">admin</th>
              <td bordercolor="#F4F4F4" class="style17">&#3609;&#3634;&#3591;&#3626;&#3634;&#3623;&#3609;&#3623;&#3614;&#3619; &#3649;&#3607;&#3609;&#3610;&#3640;&#3597; </td>
              <td bordercolor="#F4F4F4" class="style17">ฝ่ายเทคโนโลยีสารสนเทศ</td>
              <td bordercolor="#F4F4F4" class="style17"><div align="center" class="style18"><span class="style17">แก้ไขการจัดการผู้ใช้งาน</span></div></td>
              <td class="style17"><div align="center">1 &#3608;.&#3588;. 2553 11:16:57</div></td>
              <td class="style17"><div align="center">176.254.20.223</div></td>
            </tr>

            <tr>
              <th scope="row">&nbsp;</th>
              <th scope="row">&nbsp;</th>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
          </table></th>
        </tr>
        <tr>
          <th scope="row">&nbsp;</th>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
	
	</table>



</form>
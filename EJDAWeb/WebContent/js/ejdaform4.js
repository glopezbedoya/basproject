
function CancelButton(form,action){
	
	//alert('CancelButton ' + form);
	//alert('CancelButton ' + action);
	$('input[name=ejdaAction]').val(action);
	$('input[name=ejdaMethod]').val('doSearch');
	$('input[name=screenName]').val(action+'.jsp');
	form.submit();
}
function validateSubmitButton(form,action){
	//alert('validateSubmitButton : ' +action);
	
	$('input[name=ejdaAction]').val(action);
	$('input[name=ejdaMethod]').val('doSubmitButton');
	$('input[name=screenName]').val(action+'.jsp');
	form.submit();
	
}
function validateSaveButton(form,action){
	
	$('input[name=ejdaAction]').val(action);
	$('input[name=ejdaMethod]').val('doSaveButton');
	$('input[name=screenName]').val(action+'.jsp');
	form.submit();
	
}
function addQualityBaseTabJS(){
	try{
		var tbl = document.getElementById('qualityBaseTab');
		var lastRow = tbl.rows.length;
		var deletedRowCnt = 0;
		var iteration = lastRow;
		var row = tbl.insertRow(lastRow);
		row.onmouseover= new Function("mouseOver(this);");
		row.onmouseout=new Function("mouseOut(this);");
		
		row.bgColor = '#FFFFFF';
		var rowIndex = lastRow-1;
		// 1 cell
		var cell1 = row.insertCell(0);
		cell1.align='center';
		var echk = document.createElement('input');
		echk.type = 'checkbox';
		echk.name = 'chkQuabase';
		echk.id = 'chkQuabase';
		echk.value = '';	 
		echk.onclick =  new Function("syncCheckBoxHeaderPayee();");
		cell1.appendChild(echk);
		// 2 cell paym for(ACCT_NO)
		var cell2 = row.insertCell(1);
		cell2.align='center';
		var eQA_ITEM_NO = document.createElement('input');
		eQA_ITEM_NO.type = 'text';
		eQA_ITEM_NO.name = 'QA_ITEM_NO';
		eQA_ITEM_NO.id = 'QA_ITEM_NO';
		eQA_ITEM_NO.size = 10;
		eQA_ITEM_NO.maxLength = 10;
		eQA_ITEM_NO.className ='text';
		eQA_ITEM_NO.value = '';
		cell2.appendChild(eQA_ITEM_NO);
		
		// 3 cell paym for(ACCT_NAME)
		var cell3 = row.insertCell(2);
		cell3.align='center';
		var eQB_UNIT = document.createElement('input');
		eQB_UNIT.type = 'text';
		eQB_UNIT.name = 'QB_UNIT';
		eQB_UNIT.id = 'QB_UNIT';
		eQB_UNIT.size = 10;
		eQB_UNIT.maxLength = 10;
		eQB_UNIT.className ='text';
		eQB_UNIT.value = '';
		cell3.appendChild(eQB_UNIT);
		
		var cell4 = row.insertCell(3);
		cell4.align='center';
		var eFOB_ACTUAL = document.createElement('input');
		eFOB_ACTUAL.type = 'text';
		eFOB_ACTUAL.name = 'FOB_ACTUAL';
		eFOB_ACTUAL.id = 'FOB_ACTUAL';
		eFOB_ACTUAL.size = 10;
		eFOB_ACTUAL.maxLength = 10;
		eFOB_ACTUAL.className ='text';
		eFOB_ACTUAL.value = '';
		cell4.appendChild(eFOB_ACTUAL);
		
		var cell5 = row.insertCell(4);
		cell5.align='center';
		var eFOB_CUSTOM = document.createElement('input');
		eFOB_CUSTOM.type = 'text';
		eFOB_CUSTOM.name = 'FOB_CUSTOM';
		eFOB_CUSTOM.id = 'FOB_CUSTOM';
		eFOB_CUSTOM.size = 10;
		eFOB_CUSTOM.maxLength = 10;
		eFOB_CUSTOM.className ='text';
		eFOB_CUSTOM.value = '';
		cell5.appendChild(eFOB_CUSTOM);
		
		var cell6 = row.insertCell(5);
		cell6.align='center';
		var eTOTAL_VALUE = document.createElement('input');
		eTOTAL_VALUE.type = 'text';
		eTOTAL_VALUE.name = 'TOTAL_VALUE';
		eTOTAL_VALUE.id = 'TOTAL_VALUE';
		eTOTAL_VALUE.size = 10;
		eTOTAL_VALUE.maxLength = 10;
		eTOTAL_VALUE.className ='text';
		eTOTAL_VALUE.value = '';
		cell6.appendChild(eTOTAL_VALUE);
		
		var cell7 = row.insertCell(6);
		cell7.align='center';
		/*var eDUTY_RATE = document.createElement('input');
		eDUTY_RATE.type = 'text';
		eDUTY_RATE.name = 'DUTY_RATE';
		eDUTY_RATE.id = 'DUTY_RATE';
		eDUTY_RATE.size = 10;
		eDUTY_RATE.maxLength = 10;
		eDUTY_RATE.className ='text';
		eDUTY_RATE.value = '';
		cell7.appendChild(eDUTY_RATE);*/
		
		var eDUTY_RATE_show = document.createElement('div');
		eDUTY_RATE_show.innerHtml = '';
		eDUTY_RATE_show.name = 'eDUTY_RATE_show_'+lastRow;
		eDUTY_RATE_show.id = 'eDUTY_RATE_show_'+lastRow;
		cell7.appendChild(eDUTY_RATE_show);
		
		var cell8 = row.insertCell(7);
		cell8.align='center';
		var eDUTY_AMOUNT = document.createElement('input');
		eDUTY_AMOUNT.type = 'text';
		eDUTY_AMOUNT.name = 'DUTY_AMOUNT';
		eDUTY_AMOUNT.id = 'DUTY_AMOUNT';
		eDUTY_AMOUNT.size = 10;
		eDUTY_AMOUNT.maxLength = 10;
		eDUTY_AMOUNT.className ='text';
		eDUTY_AMOUNT.value = '';
		cell8.appendChild(eDUTY_AMOUNT);
		
		var cell9 = row.insertCell(8);
		cell9.align='center';
		var eTAX_TYPE = document.createElement('input');
		eTAX_TYPE.type = 'text';
		eTAX_TYPE.name = 'TAX_TYPE';
		eTAX_TYPE.id = 'TAX_TYPE';
		eTAX_TYPE.size = 10;
		eTAX_TYPE.maxLength = 10;
		eTAX_TYPE.className ='text';
		eTAX_TYPE.value = '';
		cell9.appendChild(eTAX_TYPE);
		
		var cell10 = row.insertCell(9);
		cell10.align='center';
		var eTAX_RATE = document.createElement('input');
		eTAX_RATE.type = 'text';
		eTAX_RATE.name = 'TAX_RATE';
		eTAX_RATE.id = 'TAX_RATE';
		eTAX_RATE.size = 10;
		eTAX_RATE.maxLength = 10;
		eTAX_RATE.className ='text';
		eTAX_RATE.value = '';
		cell10.appendChild(eTAX_RATE);
		
		var cell11 = row.insertCell(10);
		cell11.align='center';
		var eTAX_AMOUNT = document.createElement('input');
		eTAX_AMOUNT.type = 'text';
		eTAX_AMOUNT.name = 'TAX_AMOUNT';
		eTAX_AMOUNT.id = 'TAX_AMOUNT';
		eTAX_AMOUNT.size = 10;
		eTAX_AMOUNT.maxLength = 10;
		eTAX_AMOUNT.className ='text';
		eTAX_AMOUNT.value = '';
		cell11.appendChild(eTAX_AMOUNT);
		
		getExchgRate(lastRow);
		//rowRefKeyPayee = (rowRefKeyPayee*1)+1;
		eQA_ITEM_NO.focus();
  	}catch(e){
  		showMessageError(e,'addQualityBaseTabJS');
  	}				
}
function addPackageTabJS(){
	try{
		var tbl = document.getElementById('packageTab');
		
		var lastRow = tbl.rows.length;
		var deletedRowCnt = 0;
		var iteration = lastRow;
		var row = tbl.insertRow(lastRow);
		row.onmouseover= new Function("mouseOver(this);");
		row.onmouseout=new Function("mouseOut(this);");
		
		row.bgColor = '#FFFFFF';
		var rowIndex = lastRow-1;
		// 1 cell
		var cell1 = row.insertCell(0);
		cell1.align='center';
		var echk = document.createElement('input');
		echk.type = 'checkbox';
		echk.name = 'chkPackage';
		echk.id = 'chkPackage';
		echk.value = '';	 
		echk.onclick =  new Function("syncCheckBoxHeaderPayee();");
		cell1.appendChild(echk);
		// 2 cell paym for(ACCT_NO)
		var cell2 = row.insertCell(1);
		cell2.align='center';
		var eMARK_NO = document.createElement('input');
		eMARK_NO.type = 'text';
		eMARK_NO.name = 'MARK_NO';
		eMARK_NO.id = 'MARK_NO';
		eMARK_NO.size = 10;
		eMARK_NO.maxLength = 10;
		eMARK_NO.className ='text';
		eMARK_NO.value = '';
		cell2.appendChild(eMARK_NO);
		
		// 3 cell paym for(ACCT_NAME)
		var cell3 = row.insertCell(2);
		cell3.align='center';
		var eITEM_NO = document.createElement('input');
		eITEM_NO.type = 'text';
		eITEM_NO.name = 'ITEM_NO';
		eITEM_NO.id = 'ITEM_NO';
		eITEM_NO.size = 10;
		eITEM_NO.maxLength = 10;
		eITEM_NO.className ='text';
		eITEM_NO.value = '';
		cell3.appendChild(eITEM_NO);
		
		var cell4 = row.insertCell(3);
		cell4.align='center';
		var ePACKAGE_NO = document.createElement('input');
		ePACKAGE_NO.type = 'text';
		ePACKAGE_NO.name = 'PACKAGE_NO';
		ePACKAGE_NO.id = 'PACKAGE_NO';
		ePACKAGE_NO.size = 10;
		ePACKAGE_NO.maxLength = 10;
		ePACKAGE_NO.className ='text';
		ePACKAGE_NO.value = '';
		cell4.appendChild(ePACKAGE_NO);
		
		var cell5 = row.insertCell(4);
		cell5.align='center';
		var eGOODS_DESC = document.createElement('input');
		eGOODS_DESC.type = 'text';
		eGOODS_DESC.name = 'GOODS_DESC';
		eGOODS_DESC.id = 'GOODS_DESC';
		eGOODS_DESC.size = 10;
		eGOODS_DESC.maxLength = 10;
		eGOODS_DESC.className ='text';
		eGOODS_DESC.value = '';
		cell5.appendChild(eGOODS_DESC);
		
		var cell6 = row.insertCell(5);
		cell6.align='center';
		/*var eCODE_NO = document.createElement('input');
		eCODE_NO.type = 'text';
		eCODE_NO.name = 'CODE_NO';
		eCODE_NO.id = 'CODE_NO';
		eCODE_NO.size = 10;
		eCODE_NO.maxLength = 10;
		eCODE_NO.className ='text';
		eCODE_NO.value = '';
		cell6.appendChild(eCODE_NO);*/
		
		var eCODE_NO_show = document.createElement('div');
		eCODE_NO_show.innerHtml = '';
		eCODE_NO_show.name = 'eCODE_NO_show_'+lastRow;
		eCODE_NO_show.id = 'eCODE_NO_show_'+lastRow;
		cell6.appendChild(eCODE_NO_show);
		
		var cell7 = row.insertCell(6);
		cell7.align='center';
//		var eUNIT = document.createElement('input');
//		eUNIT.type = 'text';
//		eUNIT.name = 'UNIT';
//		eUNIT.id = 'UNIT';
//		eUNIT.size = 10;
//		eUNIT.maxLength = 10;
//		eUNIT.className ='text';
//		eUNIT.value = '';
//		cell7.appendChild(eUNIT);
		
		var eUNIT_show = document.createElement('div');
		eUNIT_show.innerHtml = '';
		eUNIT_show.name = 'UNIT_show_'+lastRow;
		eUNIT_show.id = 'UNIT_show_'+lastRow;
		cell7.appendChild(eUNIT_show);
		
		getCustomTanli(lastRow);
		getUnit(lastRow);
		
		eMARK_NO.focus();
		
		//rowRefKeyPayee = (rowRefKeyPayee*1)+1;
		eMARK_NO.focus();
  	}catch(e){
  		showMessageError(e,'addPackageTabJS');
  	}				
}

function getUnit(rowIndex){
	try{
		$.get(
		    "/EJDAWeb/AjaxLoadCacheData?mode=",
		    {load_name : 'UNIT'},
		    function(data) { 
//		    	alert(data);
		    	document.getElementById('UNIT_show_'+rowIndex).innerHTML = data;
			    }
			    ,  "text"
				);
		}catch(err){
			alert("error"  + err.message);
		}
}
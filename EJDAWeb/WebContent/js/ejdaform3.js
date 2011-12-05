
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
		eQB_UNIT.name = 'ORIGIN_CODE';
		eQB_UNIT.id = 'ORIGIN_CODE';
		eQB_UNIT.size = 10;
		eQB_UNIT.maxLength = 10;
		eQB_UNIT.className ='text';
		eQB_UNIT.value = '';
		cell3.appendChild(eQB_UNIT);
		
		var cell4 = row.insertCell(3);
		cell4.align='center';
		var eFOB_ACTUAL = document.createElement('input');
		eFOB_ACTUAL.type = 'text';
		eFOB_ACTUAL.name = 'QB_UNIT';
		eFOB_ACTUAL.id = 'QB_UNIT';
		eFOB_ACTUAL.size = 10;
		eFOB_ACTUAL.maxLength = 10;
		eFOB_ACTUAL.className ='text';
		eFOB_ACTUAL.value = '';
		cell4.appendChild(eFOB_ACTUAL);
		
		var cell5 = row.insertCell(4);
		cell5.align='center';
		var eFOB_CUSTOM = document.createElement('input');
		eFOB_CUSTOM.type = 'text';
		eFOB_CUSTOM.name = 'VALUE_PER_UNIT';
		eFOB_CUSTOM.id = 'VALUE_PER_UNIT';
		eFOB_CUSTOM.size = 10;
		eFOB_CUSTOM.maxLength = 10;
		eFOB_CUSTOM.className ='text';
		eFOB_CUSTOM.value = '';
		cell5.appendChild(eFOB_CUSTOM);
		
		var cell6 = row.insertCell(5);
		cell6.align='center';
		var eTOTAL_VALUE = document.createElement('input');
		eTOTAL_VALUE.type = 'text';
		eTOTAL_VALUE.name = 'VALUE_TOTAL';
		eTOTAL_VALUE.id = 'VALUE_TOTAL';
		eTOTAL_VALUE.size = 10;
		eTOTAL_VALUE.maxLength = 10;
		eTOTAL_VALUE.className ='text';
		eTOTAL_VALUE.value = '';
		cell6.appendChild(eTOTAL_VALUE);
		
		
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
		var eCODE_NO = document.createElement('input');
		eCODE_NO.type = 'text';
		eCODE_NO.name = 'CODE_NO';
		eCODE_NO.id = 'CODE_NO';
		eCODE_NO.size = 10;
		eCODE_NO.maxLength = 10;
		eCODE_NO.className ='text';
		eCODE_NO.value = '';
		cell6.appendChild(eCODE_NO);
		
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
		
		getUnit(lastRow);
		
		eMARK_NO.focus();
		
		//rowRefKeyPayee = (rowRefKeyPayee*1)+1;
		//eMARK_NO.focus();
  	}catch(e){
  		showMessageError(e,'addPackageTabJS');
  	}				
}

function removeRowFromTable(tablename){
	
	var tbl = document.getElementById(tablename);
	//alert('tbl : ' +tbl);
	var lastRow = tbl.rows.length;
	var chkItem;
	if (null!=tablename && tablename == 'packageTab'){
		 chkItem = window.document.ejdaformNo1.chkPackage;
	}else if (null!=tablename && tablename == 'qualityBaseTab'){
		 chkItem = window.document.ejdaformNo1.chkQuabase;
	}
	//alert('chkItem : ' +chkItem);
	if(chkItem == undefined){
		alert('undefined');
	}else if(chkItem.length != undefined){
		//alert('chkItem.length = '+chkItem.length);
		for(var i = (chkItem.length-1); i>=0; i--){
			//alert('at i = '+i+', and check is = '+chkItem[i].checked);
			if(chkItem[i].checked==true){
				tbl.deleteRow(i+3);
				//tbl.deleteRow(i+1);
			}
		}
	}else{
		//alert('one item : ' + chkItem.checked);
		if(chkItem.checked==true){
			//tbl.deleteRow(1);
			tbl.deleteRow(3);
		}
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
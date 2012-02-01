function checkDateLengthYear(field,mandate,minYear,maxYear){
	var dateStr = field.value;
	//var error = false;
	var error = true;
	//alert('dateStr = '+dateStr);
	if(dateStr != ''){
		var dateEn = dateStr.substr(3,2)+'/'+dateStr.substr(0,2)+'/'+dateStr.substr(6);
		//var dateEn = dateStr.substr(0,2)+'/'+dateStr.substr(3,2)+'/'+dateStr.substr(6);
		if(dateStr.substr(0,2)== '00' ||dateStr.substr(3,2)== '00' ||dateStr.substr(3,2)>12 ||dateStr.substr(6).length<4){var dateStr = '';}
			
		if(minYear>dateStr.substr(6) || maxYear<dateStr.substr(6)){
			field.focus();
			alert('Date is not in the right format. Please fill in again.');
			field.value = '';
			error = false;
			//return error;
			//return false;
		}else{
			if(dateStr == ''){
				field.focus();
				alert('Date is not in the right format. Please fill in again.');
				field.value = '';
				error = false;
				//return error;
			}else{
				var date_array = dateStr.split('/');
			    var day = date_array[0];
				
			    // Attention! Javascript consider months in the range 0 - 11
			    var month = date_array[1] - 1;
			    var year = date_array[2];
			
			    // This instruction will create a date object
			    source_date = new Date();
			 	source_date.setFullYear(year,month,day);
			    
			    if(month != source_date.getMonth()){
					field.focus();
			       	alert('Date is not in the right format. Please fill in again.');
					field.value = '';
					error = false;
					//return error;
			    }else if(day != source_date.getDate()){
					field.focus();
			       	alert('Date is not in the right format. Please fill in again.');
				  	field.value = '';
				  	error = false;
					//return error;
			    }
			}
		}
		//error = true;
		
	}
	return error;
}

function addSlashFormat(evt, field){
	 var charCode = (evt.which) ? evt.which : event.keyCode
	 //alert('charCode = '+charCode);
     if (charCode > 31 && (charCode < 48 || charCode > 57))
        return false;
     if(field.value.length>9)  
        return false;
	 if(field.value.length == 2 || field.value.length == 5){
		field.value = field.value + '/';}
	 return true;	
}
function keyPressInteger(){
	var strChar=String.fromCharCode(event.keyCode);
	if(!validateInteger(strChar,'integer')){			
			window.event.returnValue = false;
	}	
}
function checkNumberDateFormat(myfield, e, dec){
    var key;
    var keychar;

    if (window.event) key = window.event.keyCode;
    else if (e) key = e.which;
    else return true;
    keychar = String.fromCharCode(key);	
	
    if ((key==null) || (key==0) || (key==8) || 
        (key==9) || (key==13) || (key==27) ) 
       return true;

    else if ((("0123456789/").indexOf(keychar) > -1))
       return true;

    else if (dec && (keychar == "."))
    {
    myfield.form.elements[dec].focus();
    return false;
    }
    else return false;
}
function validateInteger( strValue , check ) {
	/************************************************
	DESCRIPTION: Validates that a string contains only
	    valid integer number.

	PARAMETERS:
	   strValue - String to be tested for validity

	RETURNS:
	   True if valid, otherwise false.
	******************************************************************************/
	//  var objRegExp  = /(^-?\d\d*$)/;
	  var objRegExp  = /(^\d\d*$)/;
	  var objRegExp2  = /(^[a-zA-Z\s\.\0-9]*$)/;
	//  var objRegExp3 = /(^[\.\-0-9]*$)/ ;
	  var objRegExp3 = /(^[\.\d\d]*$)/ ;
	  //check for integer characters
	  if(check == 'integer'){
	  	return objRegExp.test(strValue);
	  }else if(check == 'id'){
	  	return objRegExp2.test(strValue);
	  }if(check == 'decimal'){
	  	return objRegExp3.test(strValue);
	  }
	}
function keypressWithDegit(obj,length){
	var strChar=String.fromCharCode(event.keyCode);	
	if(strChar == '.'){
		if(obj.value.indexOf('.') == -1){					
			if(!validateInteger(strChar,'decimal')){			
				window.event.returnValue = false;
			}		
		}else{					
			window.event.returnValue = false;
		}
	}else{
		if(obj.value.indexOf('.') == -1){
			if(obj.value.length >length-3){
				window.event.returnValue = false;
			}else if(!validateInteger(strChar,'decimal')){			
				window.event.returnValue = false;
			}
		}else if(!validateInteger(strChar,'decimal')){			
				window.event.returnValue = false;
		}
	}
}
function formatCurrency(obj){
	//alert('formatCurrency : ' + obj.value);
	if(obj.value != '.' && obj.value != '-'){
		obj.value = ReplaceAll(obj.value,',','');
		var num = new Number(obj.value);
		obj.value = num.toFixed(2);
		addCommas(obj);
	}else{
		obj.value = '0.00';
	}
}

function formatCurrencyValue(value){	
	if(value != '.' && value != '-'){
		value = ReplaceAll(value,',','');
		var num = new Number(value);
		value = num.toFixed(2);
		return addCommasValue(value);		
	}else{
		return value;
	}
}
function addCommas(obj){
	nStr = obj.value;
	nStr += '';
	x = nStr.split('.');
	x1 = x[0];
	x2 = x.length > 1 ? '.' + x[1] : '';
	var rgx = /(\d+)(\d{3})/;
	while (rgx.test(x1)) {
		x1 = x1.replace(rgx, '$1' + ',' + '$2');
	}
	obj.value = x1 + x2;
	//return x1 + x2;
}
function addCommasValue(value){
	nStr = value;
	nStr += '';
	x = nStr.split('.');
	x1 = x[0];
	x2 = x.length > 1 ? '.' + x[1] : '';
	var rgx = /(\d+)(\d{3})/;
	while (rgx.test(x1)) {
		x1 = x1.replace(rgx, '$1' + ',' + '$2');
	}
	//obj.value = x1 + x2;
	return x1 + x2;
}
//Function Replace All
function ReplaceAll(Source,stringToFind,stringToReplace){
  var temp = Source;
   var index = temp.indexOf(stringToFind);
    while(index != -1){
        temp = temp.replace(stringToFind,stringToReplace);
        index = temp.indexOf(stringToFind);
    }
    return temp;
}

function getUnit(rowIndex){

try{
	$.get(
	    "/EJDAWeb/AjaxLoadCacheData?mode=",
	    {load_name : 'UNIT'},
	    function(data) { 
	    	document.getElementById('UNIT_show_'+rowIndex).innerHTML = data;
		    }
		    ,  "text"
			);
	}catch(err){
		alert("error"  + err.message);
	}
}

function getCustomTanli(rowIndex){

	try{
		$.get(
		    "/EJDAWeb/AjaxLoadCacheData?mode=",
		    {load_name : 'TANLI'},
		    function(data) { 
		    	document.getElementById('eCODE_NO_show_'+rowIndex).innerHTML = data;
			    }
			    ,  "text"
				);
		}catch(err){
			alert("error"  + err.message);
		}
	}

function getCountryOrigin(rowIndex){

	try{
		$.get(
		    "/EJDAWeb/AjaxLoadCacheData?mode=",
		    {load_name : 'ORIGIN'},
		    function(data) { 
		    	document.getElementById('eORIGIN_CODE_show_'+rowIndex).innerHTML = data;
			    }
			    ,  "text"
				);
		}catch(err){
			alert("error"  + err.message);
		}
	}

function getExchgRate(rowIndex){
	try{
		$.get(
		    "/EJDAWeb/AjaxLoadCacheData?mode=",
		    {load_name : 'RATE'},
		    function(data) { 
		    	//alert(data);
		    	document.getElementById('eDUTY_RATE_show_'+rowIndex).innerHTML = data;
			    }
			    ,  "text"
				);
		}catch(err){
			alert("error"  + err.message);
		}
	}

function getImportAddress(importCode){
	try{
		$.get(
		    "/EJDAWeb/AjaxLoadCacheData?mode=",
		    {load_name : 'IMPADDR',
		     importCode : importCode},
		    function(data) { 
		    	var lvField = data.split('|');
		    	$('input[name=Consignee_name]').val( lvField[0]);
		    	$('textarea[name=Consignee_address]').val( lvField[1]);
//		    	$('input[name=Consignee_name]').attr('disabled',true);
//		    	$('textarea[name=Consignee_address]').attr('disabled',true);
			    }
			    ,  "text"
				);
		}catch(err){
			alert("error"  + err.message);
		}
	}

function getAgentAddress(agentCode){
	try{
		$.get(
		    "/EJDAWeb/AjaxLoadCacheData?mode=",
		    {load_name : 'AGENTADDR',
		    	agentCode : agentCode},
		    function(data) { 
		    	
		    	var lvField = data.split('|');
		    	$('input[name=AuthorAgent_name]').val( lvField[0]);
		    	$('textarea[name=AuthorAgent_address]').val( lvField[1]);
//		    	$('input[name=AuthorAgent_name]').attr('disabled',true);
//		    	$('textarea[name=AuthorAgent_address]').attr('disabled',true);
			    }
			    ,  "text"
				);
		}catch(err){
			alert("error"  + err.message);
		}
	}
function readOnlyDeliver(form_action){
	//alert('readOnlyDeliver : ' + form_action);
	try{
		if (form_action == 'EJDAM022'){
			document.getElementById("InsertPackage").disabled=true;
			document.getElementById("DeletePackage").disabled=true;
			
			document.getElementById("InsertQualityBase").disabled=true;
			document.getElementById("DeleteQualityBase").disabled=true;
			
			
			setReadOnly('consignorExportCode');
			setReadOnly('consignorExportName');
			$('textarea[name=consignorExportAddress]').attr('readonly',true);
			
			$('select[name=Consignee_code]').attr('disabled',true);
			//setReadOnly('Consignee_code');
			setReadOnly('Consignee_name');
			//setReadOnly('Consignee_address');
			$('textarea[name=Consignee_address]').attr('readonly',true);
			
			$('select[name=AuthorAgent_code]').attr('disabled',true);
			//setReadOnly('AuthorAgent_code');
			setReadOnly('AuthorAgent_name');
			//setReadOnly('AuthorAgent_address');
			$('textarea[name=AuthorAgent_address]').attr('readonly',true);
			
			setReadOnly('trans_other');
			setReadOnly('Date_Import');
			$('select[name=PortImport_Code]').attr('disabled',true);
			//setReadOnly('PortImport_Code');
			setReadOnly('PortImport_Desc');
			$('select[name=PortLoad_Code]').attr('disabled',true);
			//setReadOnly('PortLoad_Code');
			setReadOnly('PortLoad_Desc');
			$('select[name=Via_Code]').attr('disabled',true);
			//setReadOnly('Via_Code');
			setReadOnly('Via_Desc');
			setReadOnly('Date_Receipt');
			setReadOnly('Regis_no');
			setReadOnly('cus_name_code');
			setReadOnly('cus_name_desc');
			setReadOnly('ManifestNo');
			setReadOnly('duty_tax_receipt_date');
			setReadOnly('duty_tax_receipt_desc');
			setReadOnly('import_permit_no');
			setReadOnly('exchg_ctrl_ref');
			setReadOnly('special_treatment');
			setReadOnly('country_origin_code');
			setReadOnly('country_origin_desc');
			setReadOnly('country_final_code');
			setReadOnly('country_final_desc');
			setReadOnly('bill_no');
			setReadOnly('term_payment');
			setReadOnly('cur_code');
			setReadOnly('Received_amount');
			setReadOnly('ExchgRate_ID');
			setReadOnly('Equivalent');
			setReadOnly('good_payment_code');
			setReadOnly('good_payment_desc');
			setReadOnly('country_of_good');
			setReadOnly('fob_value');
			setReadOnly('Insurance');
			setReadOnly('Freight');
			setReadOnly('cif_value');
			setReadOnly('gross_weight');
			setReadOnly('Measurement');
			setReadOnly('Other_charg');
			setReadOnly('declarant_name');
			setReadOnly('id_card_no');
			setReadOnly('cerify');
			
			setReadOnly('amtRecrived');
			setReadOnly('exchange_rate');
			setReadOnly('moveMentPemit');
			setReadOnly('dateOfExpiry');
			setReadOnly('securityRefNo');
			setReadOnly('amtOfSecurity');
			setReadOnly('billOfLading');
			setReadOnly('DeclarantName');
			
			$('textarea[name=requestApproved]').attr('readonly',true);
			$('textarea[name=certified]').attr('readonly',true);
			$('textarea[name=properOffice]').attr('readonly',true);
			
			$('textarea[name=cus_removal]').attr('readonly',true);
			//setReadOnly('cus_removal');
			setReadOnly('tax_total');
			setReadOnly('Other_charg2');
			setReadOnly('payable_amount');
			$('textarea[name=manualscript_recerpt]').attr('readonly',true);
			//setReadOnly('manualscript_recerpt');
			setReadOnly('vessel_value');
			$('textarea[name=instruct_exam]').attr('readonly',true);
			$('textarea[name=result_exam]').attr('readonly',true);
			$('textarea[name=for_other_use]').attr('readonly',true);
			//setReadOnly('instruct_exam');
			//setReadOnly('result_exam');
			//setReadOnly('for_other_use');
			
			$('input[name=mode_trans]').attr('disabled',true);
			$('input[name=Status]').attr('disabled',true);
			$('input[name=doc_attach]').attr('disabled',true);
		}
	}catch (err){
		alert(err.message);
	}
}

function setReadOnlyDetail1(form_action){
	//alert('sized = ' + sized);
	try{
		if (form_action == 'EJDAM022'){
			setReadOnly('MARK_NO');
			setReadOnly('ITEM_NO');
			setReadOnly('PACKAGE_NO');
			setReadOnly('GOODS_DESC');
			$('input[name=checkall1]').attr('disabled',true);
			$('select[name=CODE_NO]').attr('disabled',true);
			$('select[name=UNIT]').attr('disabled',true);
		}
	}catch (e){
		alert(e.message);
	}
	
}
function setReadOnlyDetail2(form_action){
	//alert('sized = ' + sized);
	try{
		if (form_action == 'EJDAM022'){
			setReadOnly('QA_ITEM_NO');
			setReadOnly('QB_UNIT');
			setReadOnly('FOB_ACTUAL');
			setReadOnly('FOB_CUSTOM');
			setReadOnly('TOTAL_VALUE');
			setReadOnly('DUTY_AMOUNT');
			setReadOnly('TAX_TYPE');
			setReadOnly('TAX_RATE');
			setReadOnly('TAX_AMOUNT');
			setReadOnly('VALUE_PER_UNIT');
			setReadOnly('VALUE_TOTAL');
			
			$('input[name=checkall2]').attr('disabled',true);
			$('input[name=checkall1]').attr('disabled',true);
			$('select[name=DUTY_RATE]').attr('disabled',true);
			$('select[name=ORIGIN_CODE]').attr('disabled',true);
		}
	}catch (e){
		alert(e.message);
	}
	
}
function setReadOnly(field){
	//alert(field);
	$('input[name='+field+']').attr('readonly',true);
	$('input[name='+field+']').removeClass('textbox');
	$('input[name='+field+']').removeClass('textboxdisabl');
	$('input[name='+field+']').addClass('textboxdisable');
}
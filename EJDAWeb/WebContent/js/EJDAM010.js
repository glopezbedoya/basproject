function ResetForm(){
	$('input[name=txtDocID]').val('');
	$('select[name=jdaType]').val('');
	$('input[name=txtConsignorCode]').val('');
	$('input[name=txtConsignorName]').val('');
	$('input[name=txtConsigneeCode]').val('');
	$('input[name=txtConsigneeName]').val('');
	$('input[name=txtDocDateFrom]').val('');
	$('input[name=txtDocDateTo]').val('');
}
function buttonAction(form,action,method,page){
	
	if($('select[name=DdlAddForm').val() == '1'){
//		action = 'EJDAM010';
		page = 'eJdaForm1.jsp';
	}else if($('select[name=DdlAddForm').val() == '2'){
//		action = 'EJDAM011';
		page = 'eJdaForm2.jsp';
	}else if($('select[name=DdlAddForm').val() == '3'){
//		action = 'EJDAM012';
		page = 'ejdaForm3.jsp';
	}else if($('select[name=DdlAddForm').val() == '4'){
//		action = 'EJDAM013';
		page = 'ejdaForm4.jsp';
	}
	
	$('input[name=showSearch]').val('1');
	$('input[name=ejdaAction]').val(action);
	$('input[name=ejdaMethod]').val(method);
	$('input[name=screenName]').val(page);
	form.submit();
}

function searchButtonEJDAM010(form,action,method,page){

	$('input[name=ejdaAction]').val(action);
	$('input[name=ejdaMethod]').val(method);
	$('input[name=screenName]').val(page);
	form.submit();
}

function updateEJDATable2(form,doc_id,action,page,ejda_type){
	//alert('doc_id : ' +doc_id + ':' + action + ':' +page);
	$('input[name=form_no]').val(ejda_type);
	$('input[name=doc_id]').val(doc_id);
	$('input[name=ejdaAction]').val(action);
	$('input[name=ejdaMethod]').val('doUpdate');
	$('input[name=screenName]').val(page);
	//form.submit();
	document.ejdaformNo1.submit();
}

function changeSelectPage(form){
	$('input[name=ejdaAction]').val('EJDAM010');
	$('input[name=ejdaMethod]').val('doSearch');
	$('input[name=screenName]').val('EJDAM010.jsp');
	$('input[name=page]').val($('select[name=selectPaging]').val());
	form.submit();
}

function checkBoxAll(){
	var check = $('input[name=checkAllBox]').attr('checked');
	$('input[name=checkBox]').each(function (){
		$(this).attr('checked',(check == 'checked')?true:false);
	});
}
function haveCheckBox(){
	var check = false;
	$('input[name=checkBox]').each(function (){
		if($(this).attr('checked') == 'checked'){
			check = true;
		}
	});
	return check;
}




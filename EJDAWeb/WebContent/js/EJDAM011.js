function buttonAction(form,action,method,page){
	
	$('input[name=ejdaAction]').val(action);
	$('input[name=ejdaMethod]').val(method);
	$('input[name=screenName]').val(page);
	form.submit();
}
function updateEJDATable2(form,doc_id,action){
	//alert('fn_no : ' +fn_no);
	$('input[name=doc_id]').val(doc_id);
	$('input[name=ejdaAction]').val(action);
	$('input[name=ejdaMethod]').val('doUpdate');
	$('input[name=screenName]').val('eJdaForm2.jsp');
	//form.submit();
	document.ejdaformNo2.submit();
}

function changeSelectPage(form){
	$('input[name=ejdaAction]').val('EJDAM011');
	$('input[name=ejdaMethod]').val('doSearch');
	$('input[name=screenName]').val('EJDAM011.jsp');
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




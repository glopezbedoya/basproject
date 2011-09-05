function buttonAction(form,action){
	var check = true;
	if(action == 'doDelete'){
		check = haveCheckBox();
	}
	if(check){
		$('input[name=ejdaAction]').val('EJDAM010');
		$('input[name=ejdaMethod]').val(action);
		$('input[name=screenName]').val('EJDAM010.jsp');
		form.submit();
	}else{
		alert('No Data Select');
	}
}

function openForm1(form){
	//alert('doChangeForm ' + form);
	$('input[name=ejdaAction]').val('EJDAM010');
	$('input[name=ejdaMethod]').val('openForm1');
	$('input[name=screenName]').val('eJdaForm1.jsp');
	form.submit();
}
function CancelButton(form){
	//alert('doChangeForm ' + form);
	$('input[name=ejdaAction]').val('EJDAM010');
	$('input[name=ejdaMethod]').val('openForm1');
	$('input[name=screenName]').val('EJDAM010.jsp');
	form.submit();
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


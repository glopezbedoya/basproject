function buttonAction(form,action){
	var check = true;
	if(action == 'doDelete'){
		check = haveCheckBox();
	}
	if(check){
		$('input[name=ejdaAction]').val('EJDAM008');
		$('input[name=ejdaMethod]').val(action);
		$('input[name=screenName]').val('EJDAM008.jsp');
		form.submit();
	}else{
		alert('No Data Select');
	}
}

function changePage(page,form){
	$('input[name=ejdaAction]').val('EJDAM008');
	$('input[name=ejdaMethod]').val('doSearch');
	$('input[name=screenName]').val('EJDAM008.jsp');
	$('input[name=page]').val(page);
	form.submit();
}

function changeSelectPage(form){
	$('input[name=ejdaAction]').val('EJDAM008');
	$('input[name=ejdaMethod]').val('doSearch');
	$('input[name=screenName]').val('EJDAM008.jsp');
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

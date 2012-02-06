function checkType(){
	reportType = $('select[name=reportType]').val();
	$('td[name=jda1]').hide();
	$('td[name=jda2]').hide();
	$('td[name=jda3]').hide();
	$('div[name=reportAll]').hide();
	$('div[name=reportJDA1]').hide();
	$('div[name=reportJDA2]').hide();
	$('div[name=reportJDA3]').hide();
	$('div[name=reportJDA4]').hide();
	if(reportType == 'ALL'){
		$('div[name=reportAll]').show();
	}else if(reportType == '1'){
		$('td[name=jda1]').show();
		$('div[name=reportJDA1]').show();
	}else if(reportType == '2'){
		$('td[name=jda2]').show();
		$('div[name=reportJDA2]').show();
	}else if(reportType == '3'){
		$('td[name=jda3]').show();
		$('div[name=reportJDA3]').show();
	}else if(reportType == '4'){
		$('div[name=reportJDA4]').show();
	}
}

function exportButton(form,action){
	$('input[name=ejdaAction]').val(action);
	$('input[name=ejdaMethod]').val('doExportExcel');
	$('input[name=screenName]').val(action+'.jsp');
	$('form[name=reportForm]').submit();
//	form.submit();
	
}
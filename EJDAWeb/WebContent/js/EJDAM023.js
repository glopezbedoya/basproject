function buttonAction(form,action,method,page){
	//alert(page);
	$('input[name=ejdaAction]').val(action);
	$('input[name=ejdaMethod]').val(method);
	$('input[name=screenName]').val(page);
	form.submit();
}

function validateSubmitButton(form,action){
	//getIndexCheckbox('PERMISSIONS');
	$('input[name=ejdaAction]').val(action);
	$('input[name=ejdaMethod]').val('doSubmitButton');
	$('input[name=screenName]').val(action+'.jsp');
	form.submit();
	
}

function getIndexCheckbox(name){
	alert('get index : ' + name);

	var indexTemp = '';
	//$('input[name=inqs]').each(function(){
	$('input[name='+name+']').each(function(){
		
		if(!$(this).attr('checked') && !$(this).attr('disabled')){
			
			if (null!=$(this).val() && ""!=$(this).val()){
				indexTemp += 'N';
			}
			
		}else{
			if (null!=$(this).val() && ""!=$(this).val()){
				indexTemp += 'Y';
			}
		}
		indexTemp += ':';
	});
	
	alert('get indexTemp : ' +indexTemp);
	return indexTemp;
}

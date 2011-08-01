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
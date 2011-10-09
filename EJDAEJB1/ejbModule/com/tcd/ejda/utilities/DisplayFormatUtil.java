package com.tcd.ejda.utilities;

public class DisplayFormatUtil {
	
	public static String FormatDocId(String rsq) {
		
		String seq="";
		if (rsq.length()<14){
			
			if (rsq.length()==1){
				seq = "0000000000000"+rsq;
			}else if (rsq.length()==2){
				seq = "000000000000"+rsq;
			}else if (rsq.length()==3){
				seq = "00000000000"+rsq;
			}else if (rsq.length()==4){
				seq = "0000000000"+rsq;
			}else if (rsq.length()==5){
				seq = "000000000"+rsq;
			}else if (rsq.length()==6){
				seq = "00000000"+rsq;
			}else if (rsq.length()==7){
				seq = "00000000"+rsq;
			}else if (rsq.length()==8){
				seq = "000000"+rsq;
			}else if (rsq.length()==9){
				seq = "00000"+rsq;
			}else if (rsq.length()==10){
				seq = "0000"+rsq;
			}else if (rsq.length()==11){
				seq = "000"+rsq;
			}else if (rsq.length()==12){
				seq = "00"+rsq;
			}else if (rsq.length()==13){
				seq = "0"+rsq;
			}else{
				seq = rsq;
			}
			
		}
		return seq;
	}
}

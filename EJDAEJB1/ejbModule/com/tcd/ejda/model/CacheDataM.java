package com.tcd.ejda.model;

import java.io.Serializable;

public class CacheDataM implements Serializable, Cloneable {
	
	String code;
	String shortDesc;
	String longDesc;
	String valueDesc;
	String status;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getShortDesc() {
		return shortDesc;
	}
	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}
	public String getLongDesc() {
		return longDesc;
	}
	public void setLongDesc(String longDesc) {
		this.longDesc = longDesc;
	}
	public String getValueDesc() {
		return valueDesc;
	}
	public void setValueDesc(String valueDesc) {
		this.valueDesc = valueDesc;
	}

}

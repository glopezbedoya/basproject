package com.ejda.sessionBean;

import com.tcd.ejda.model.ValueListModel;

public abstract class AbstractBean {
	
	public ValueListModel valueListM;

	public ValueListModel getValueListM() {
		return valueListM;
	}

	public void setValueListM(ValueListModel valueListM) {
		this.valueListM = valueListM;
	}
}

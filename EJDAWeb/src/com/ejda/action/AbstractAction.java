package com.ejda.action;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractAction {

	public abstract void init(); 
	public abstract boolean validate();
	public abstract boolean methodAction(String ejdaAction,HttpServletRequest request);
	public abstract void clearSessionNotUsed();
}

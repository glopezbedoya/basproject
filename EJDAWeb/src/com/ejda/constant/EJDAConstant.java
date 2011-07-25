package com.ejda.constant;

public class EJDAConstant {

	public interface SESSION_NAME {
		public final String PAGE = "PAGE";
	}
	
	public interface PAGE {
		public final String INDEX_PAGE = "index2.jsp";
	}
	
	public interface SQL{
		public final String TRAN_LOG_SQL = "SELECT L.TRANS_ID, L.TRANS_ACTION, L.DESCRIPTION, L.IP_ADDRESS, L.TRANS_DATE, L.TRANS_BY, U.FIRST_NAME, U.LAST_NAME, U.DEPARTMENT, U.USER_NAME FROM JDA_TRANSACTION_LOG L  JOIN JDA_USER U ON U.IV_USER = L.TRANS_BY ";
			
			 
	}
}

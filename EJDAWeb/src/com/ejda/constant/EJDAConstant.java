package com.ejda.constant;

public class EJDAConstant {

	public interface SESSION_NAME {
		public final String PAGE = "PAGE";
	}
	
	public interface PAGE {
		public final String INDEX_PAGE = "index.jsp";
		public final String INDEX2_PAGE = "index2.jsp";
	}
	
	public interface SQL{
		public final String TRAN_LOG_SQL = "SELECT L.TRANS_ID, L.TRANS_ACTION, L.DESCRIPTION, L.IP_ADDRESS, L.TRANS_DATE, L.TRANS_BY, U.FIRST_NAME, U.LAST_NAME, U.DEPARTMENT, U.USER_NAME FROM JDA_TRANSACTION_LOG L  JOIN JDA_USER U ON U.IV_USER = L.TRANS_BY ";
		public final String ROLE_SQL = "SELECT ROLE_ID, ROLE_NAME FROM JDA_ROLE";
		public final String USER__SCREEN_SQL = "SELECT JDA_ID, IV_USER,PASSWORD, USER_NAME, FIRST_NAME, LAST_NAME, DEPARTMENT, USER_STATUS,TO_CHAR(EFFECTIVE_DATE,'YYYY-MM-DD') AS EFFECTIVE_DATE, "+
												"TO_CHAR(EXPIRY_DATE,'YYYY-MM-DD') AS EXPIRY_DATE,TO_CHAR(CREATE_DATE,'YYYY-MM-DD') AS CREATE_DATE FROM JDA_USER ";
	}
}

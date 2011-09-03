package com.tcd.ejda.connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Hashtable;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.apache.log4j.Logger;


public class JDBCServiceLocator {
	private Logger log = Logger.getLogger(JDBCServiceLocator.class);
	 public static JDBCServiceLocator serviceLocator;
	    private final static String JAVA_ENV = "jdbc/ejdadb1";

	    private static int clients = 0;
	    private static Hashtable pools;

	    public static synchronized JDBCServiceLocator getInstance() {
	        if (serviceLocator == null) {
	            serviceLocator = new JDBCServiceLocator();
	        }
	        clients++;
	        return serviceLocator;
	    }

	    /**
	     * 
	     * @param dbType
	     * @return DataSource.getConnection()
	     * @throws Exception
	     */
		public Connection getConnection() throws Exception{
			DataSource ds = null;
			try{
				InitialContext ctx = new InitialContext();
				Object obj = null;
				log.debug("JAVA_ENV >> " + JAVA_ENV);
				ds = (DataSource)ctx.lookup(JAVA_ENV);
				return ds.getConnection();
			}catch(Exception e){
				e.printStackTrace();
				throw new Exception(e.toString());
			}
		}
		/**
		 * 
		 * @param jndiName (For example, "jdbc/DS_LISDBS1D")
		 * @return DataSource.getConnection()
		 * @throws Exception
		 */
		public Connection getConnection(String jndiName) throws Exception{
			try{			
				DataSource ds = null;
				InitialContext ctx = new InitialContext();
				Object obj = null;
				if(null != jndiName && !"".equalsIgnoreCase(jndiName)){
					ds = (DataSource)ctx.lookup(JAVA_ENV+jndiName);
				}else{
					obj = null;
				}
				return ds.getConnection();
			}catch(Exception e){
				e.printStackTrace();
				throw new Exception(e.toString());
			}
		}
	    
	    public void freeConnection(int vpdID, Connection con) {
	        try {
		        if (!con.isClosed()) {
		            con.close();
		        }
	        } catch (SQLException sqle) {
	            // Ignore errors; garbage collect anyhow
	        }

	    }
	    

	}


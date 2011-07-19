package com.tcd.ejda.connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Hashtable;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JDBCServiceLocator {

	 public static JDBCServiceLocator serviceLocator;
//	 Connection conn=db.dbConnect("jdbc:oracle:thin:@localhost:1521/orcl", "SYSTEM", "orcl");
//	    private final static String JAVA_ENV = "java:comp/env/";
	    private final static String JAVA_ENV = "jdbc:oracle:thin:@localhost:1521/orcl";
//	    public final static int MASTER_DB = 0;
//	    public final static int LS_DB = 1;
	    
	    public final static String MASTER_DATA_SOURCE = "jdbc/master";
//	    public final static String LS_DATA_SOURCE = "jdbc/lis";

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
		public Connection getConnection(int dbType) throws Exception{
			DataSource ds = null;
			try{
				InitialContext ctx = new InitialContext();
				Object obj = null;
				ds = (DataSource)ctx.lookup(JAVA_ENV);
//				switch(dbType){
//				case MASTER_DB:
//					System.out.println("Look UP >>>>>>>>> "+JAVA_ENV+MASTER_DATA_SOURCE);
//					ds = (DataSource)ctx.lookup(JAVA_ENV+MASTER_DATA_SOURCE);
//					break;
//				case LS_DB:
//					System.out.println("Look UP >>>>>>>>> "+JAVA_ENV+LS_DATA_SOURCE);
//					ds = (DataSource)ctx.lookup(JAVA_ENV+LS_DATA_SOURCE);
//					break;
//				default:
//					obj = null;
//					break;
//				}
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
	    
	 /*
	    public Connection getConnection(int vpdID) {
	        try {
	            String poolName = getPoolName(vpdID);
	            InitialContext ctx = new InitialContext();
	            log.debug("test jdbc");
	            return ((DataSource) ctx.lookup(JAVA_ENV + poolName)).getConnection();
	        } catch (SQLException e) {
	            log.error("ERROR",e);
	        } catch (Exception e) {
	            log.error("ERROR",e);
	        }
	        return null;
//	        try {
//	            String poolName = getPoolName(vpdID);
//		        ConnectionPool pool = (ConnectionPool) pools.get(poolName);
//		        if (pool == null) {
//		            int max = 100;
//		            int init = 0;
//		            boolean waitIfBusy = true;
//		            InitialContext ctx = new InitialContext();
//	                pool = new ConnectionPool(poolName, (DataSource) ctx.lookup(JAVA_ENV + poolName), init, max, waitIfBusy);
//	                pools.put(poolName, pool);
//		        }
//	            return pool.getConnection();
//	        } catch (SQLException e) {
//	            log.error("ERROR",e);
//	        } catch (Exception e) {
//	            log.error("ERROR",e);
//	        }
//	        return null;
	    }
	*/
	    public void freeConnection(int vpdID, Connection con) {
	        try {
		        if (!con.isClosed()) {
		            con.close();
		        }
	        } catch (SQLException sqle) {
	            // Ignore errors; garbage collect anyhow
	        }

	    }
	    

	    public static String getPoolName(int dbType) {
	    	return MASTER_DATA_SOURCE;
//	        switch (dbType) {
//	        case MASTER_DB:
//	            return MASTER_DATA_SOURCE;
//	        default:
//	            return MASTER_DATA_SOURCE;
//	        }
	    }
	
	}


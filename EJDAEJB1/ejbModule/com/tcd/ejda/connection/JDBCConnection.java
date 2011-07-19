package com.tcd.ejda.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCConnection {
	
	public Connection getConnection(){
		 
		DB db = new DB(); 
		Connection conn=db.dbConnect(
            "jdbc:oracle:thin:@localhost:1521/orcl", "SYSTEM", "orcl");
		return conn;
	}
	
	class DB
	{
	        public DB() {}

	        public Connection dbConnect(String db_connect_string, 
	          String db_userid, String db_password)
	        {
	                try
	                {
	                        DriverManager.registerDriver(
	                          new oracle.jdbc.OracleDriver());
	                          
	                        Connection conn = DriverManager.getConnection(
	                          db_connect_string, db_userid, db_password);
	        
	                        System.out.println(" connected ");
	                        return conn;
	                        
	                }
	                catch (Exception e)
	                {
	                        e.printStackTrace();
	                        return null;
	                }
	        }
	};

}

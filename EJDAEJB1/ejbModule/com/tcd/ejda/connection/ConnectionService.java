package com.tcd.ejda.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;


/**
 * @author Administrator To change the template for this generated type comment
 *         go to Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and
 *         Comments
 */
public class ConnectionService {
   

    /**
     * close database connection
     * 
     * @param conn
     * 
     * @throws Exception
     */
    public final void closeConnection(Connection con) throws Exception {

        try {

            try {
                con.commit();
            } catch (Exception e) {                
            }

            con.close();
            con = null;

        } catch (Exception ex) {            
            throw new SQLException(ex.getMessage());
        }
    }

    /**
     * close database connection
     * 
     * @param con
     * @param stm
     * 
     * @throws Exception
     */
    public final void closeConnection(Connection con, Statement stm) throws Exception {

        try {

            try {

                if (con != null) {
                    con.commit();
                }
            } catch (Exception e) {                
            }

            if (stm != null) {

                try {
                    stm.close();
                } catch (Exception e) {                    
                }
            }

            if (con != null) {

                try {
                    con.close();
                } catch (Exception e) {                    
                }
            }

            stm = null;
            con = null;
        } catch (Exception ex) {            
            throw new SQLException(ex.getMessage());
        }
    }

    /**
     * close database connection
     * 
     * @param con
     * @param stm
     * 
     * @throws Exception
     */
    public final void closeConnection(int vpdID, Connection con, Statement stm) throws Exception {
        try {
            if (con != null) {
                con.commit();
            }
        } catch (Exception e) {           
        }
        if (stm != null) {
            try {
                stm.close();
            } catch (Exception e) {
            }
        }
        if (con != null) {
            try {
                free(vpdID, con);//con.close();
            } catch (Exception e) {
            }
        }
        stm = null;
        con = null;
    }

    /**
     * close database connection
     * 
     * @param con
     * @param ps
     * 
     * @throws Exception
     */
    public final void closeConnection(Connection con, PreparedStatement ps) throws Exception {

        try {

            try {

                if (con != null) {
                    con.commit();
                }
            } catch (Exception e) {                
            }

            if (ps != null) {

                try {
                    ps.close();
                } catch (Exception e) {                    
                }
            }

            if (con != null) {

                try {
                    con.close();
                } catch (Exception e) {                    
                }
            }

            ps = null;
            con = null;
        } catch (Exception ex) {            
            throw new SQLException(ex.getMessage());
        }
    }

    /**
     * close database connection
     * 
     * @param con
     * @param rs
     * @param ps
     * 
     * @throws Exception
     */
    public final void closeConnection(Connection con, ResultSet rs, PreparedStatement ps) throws Exception {

        try {

            try {

                if (con != null) {
                    con.commit();
                }
            } catch (Exception e) {                
            }

            if (rs != null) {

                try {
                    rs.close();
                } catch (Exception e) {                   
                }
            }

            if (ps != null) {

                try {
                    ps.close();
                } catch (Exception e) {                    
                }
            }

            if (con != null) {

                try {
                    con.close();
                } catch (Exception e) {                    
                }
            }

            rs = null;
            ps = null;
            con = null;
        } catch (Exception ex) {            
            throw new SQLException(ex.getMessage());
        }
    }

    /**
     * close database connection
     * 
     * @param con
     * @param rs
     * @param ps
     * 
     * @throws Exception
     */
    public final void closeConnection(int vpdID, Connection con, ResultSet rs, PreparedStatement ps) throws Exception {
        try {
            if (con != null) {
                con.commit();
            }
        } catch (Exception e) {            
        }
    	
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (Exception e) {
            }
        }
        if (con != null) {
            try {
                free(vpdID,con);//con.close();
            } catch (Exception e) {
            }
        }
        rs = null;
        ps = null;
        con = null;
    }

    /**
     * close database connection
     * 
     * @param con
     * @param rs
     * @param ps
     * 
     * @throws Exception
     */
    public final void closeConnection(Connection con, ResultSet rs, Statement stm) throws Exception {

        try {

            try {

                if (con != null) {
                    con.commit();
                }
            } catch (Exception e) {                
            }

            if (rs != null) {

                try {
                    rs.close();
                } catch (Exception e) {                    
                }
            }

            if (stm != null) {

                try {
                    stm.close();
                } catch (Exception e) {                    
                }
            }

            if (con != null) {

                try {
                    con.close();
                } catch (Exception e) {                    
                }
            }

            rs = null;
            stm = null;
            con = null;
        } catch (Exception ex) {
           
            throw new SQLException(ex.getMessage());
        }
    }

    /**
     * close database connection
     * 
     * @param con
     * @param rs
     * @param ps
     * 
     * @throws Exception
     */
    public final void closeConnection(int vpdID, Connection con, ResultSet rs, Statement stm) throws Exception {
        try {
            if (con != null) {
                con.commit();
            }
        } catch (Exception e) {            
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
            }
        }
        if (stm != null) {
            try {
                stm.close();
            } catch (Exception e) {
            }
        }
        if (con != null) {
            try {
                free(vpdID, con);//con.close();
            } catch (Exception e) {
            }
        }
        rs = null;
        stm = null;
        con = null;
    }
    /**
     * @param dbType
     * 
     * @return
     */
    //    public final Connection getConnection() {
    //
    //        try {
    //            JDBCServiceLocator geService = JDBCServiceLocator.getInstance();
    //
    //            return geService.getConnection(0);
    //
    //        } catch (Exception e) {
    //            logger.error("ERROR",e);
    //        }
    //
    //        return null;
    //    }
    /**
     * @param vpdID
     * 
     * @return
     */
    public  Connection getConnection(int vpdID) throws Exception  {
        try {
            JDBCServiceLocator geService = JDBCServiceLocator.getInstance();
            return geService.getConnection(vpdID);
        } catch (Exception e) {
           
            throw new SQLException(e.getMessage());
        }
    }
    
    public  Connection getConnection(String jndiName) throws Exception  {
        try {
        	System.out.println("JNDI Name >>>>>"+jndiName);
            JDBCServiceLocator geService = JDBCServiceLocator.getInstance();
           System.out.println("Service >>>>> "+geService);
            return geService.getConnection(jndiName);
        } catch (Exception e) {
           System.out.println("ERROR"+e);
            throw new SQLException(e.getMessage());
        }
    }

    public final void free(int vpdID, Connection conn) {
        try {
            JDBCServiceLocator geService = JDBCServiceLocator.getInstance();
            geService.freeConnection(vpdID, conn);
        } catch (Exception e) {
           System.out.println("ERROR"+e);
        }
    }
    
    public   java.util.Date parseDate(java.sql.Date sDate) {
		if(sDate==null){return null;}
		return new java.util.Date(sDate.getTime());
	}
    public   java.sql.Date parseDate(java.util.Date uDate) {
		if(uDate==null){return null;}
		return new java.sql.Date(uDate.getTime());
		 
	}
    public static Date StringToDate(String date)throws Exception {
    	if(date != null && !("").equals(date)){
			int fromIndex;
			int toIndex;
			String dd = null;
			String mm = null;
			String yyyy = null;
			fromIndex = 0;
			toIndex = date.indexOf("/");
			if (toIndex >= 0) { // found
				dd = date.substring(fromIndex, toIndex);
				dd = (dd.length() == 1 ? "0" + dd : dd);
				fromIndex = toIndex + 1;
				toIndex = date.indexOf("/", fromIndex);
				if (toIndex >= 0) { // found
					mm = date.substring(fromIndex, toIndex);
					mm = (mm.length() == 1 ? "0" + mm : mm);
					fromIndex = toIndex + 1;
					yyyy = date.substring(fromIndex);
				}
			}
			Calendar cal = Calendar.getInstance(Locale.US);
			int d = StringToInt(dd);
			int m = StringToInt(mm) - 1;
			int y = StringToInt(yyyy);
			cal.set(y, m, d);
			return cal.getTime();
    	}else{
    		return null;
    	}
		
	}
    public static int StringToInt(String str) {
		return (Integer.valueOf(str)).intValue();
	}
}


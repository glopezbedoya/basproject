package com.ejda.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ejda.action.AbstractAction;
import com.ejda.constant.EJDAConstant;

/**
 * Servlet implementation class EJDAControler
 */
public class EJDAControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private Logger log = Logger.getLogger(EJDAControler.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EJDAControler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String ejdaAction;
		String ejdaMethod;
		String screenName;
		Object o = null;
		try {
			log.debug("-- ejdaAction() --");
			ejdaAction = (String)request.getParameter("ejdaAction");
			ejdaMethod = (String)request.getParameter("ejdaMethod");
			screenName = (String)request.getParameter("screenName");
			log.debug("ejdaAction###jsp : "+screenName);
			log.debug("ejdaAction###java : "+ejdaAction);
			log.debug("ejdaAction###method : "+ejdaMethod);
			if(screenName != null && ejdaAction != null && ejdaMethod != null){
				o = Class.forName("com.ejda.action." + ejdaAction).newInstance();
				if(((AbstractAction) o).methodAction(ejdaMethod,request)){
					//log.debug("### clear Session Unnecessary ###");
//						((AbstractAction) o).clearSessionNotUsed();
					log.debug("Success");
					request.getSession().setAttribute(EJDAConstant.SESSION_NAME.PAGE, screenName);
					response.sendRedirect(EJDAConstant.PAGE.INDEX_PAGE);
				}
			}else{
				log.debug("Error in ejdaAction");
			}				
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			o = null;
		}
	}

}
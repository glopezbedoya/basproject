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
		doPost(request,response);
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
			log.debug("screenName###jsp : "+screenName);
			log.debug("ejdaAction###java : "+ejdaAction);
			log.debug("screenName###method : "+ejdaMethod);
			if(screenName != null && ejdaAction != null && ejdaMethod != null){
				o = Class.forName("com.ejda.action." + ejdaAction + "Action").newInstance();
				((AbstractAction) o).setRequest(request);
				((AbstractAction) o).setResponse(response);
				if(((AbstractAction) o).methodAction(ejdaMethod)){
					//log.debug("### clear Session Unnecessary ###");
//						((AbstractAction) o).clearSessionNotUsed();
					log.debug("Success");
					request.getSession().setAttribute(EJDAConstant.SESSION_NAME.PAGE, screenName);
					response.sendRedirect(EJDAConstant.PAGE.INDEX2_PAGE);
				}else{
					response.sendRedirect(EJDAConstant.PAGE.INDEX_PAGE);
				}
			}else{
				o = Class.forName("com.ejda.action." + ejdaAction + "Action").newInstance();
				((AbstractAction) o).setRequest(request);
				((AbstractAction) o).setResponse(response);
				((AbstractAction) o).init();
				request.getSession().setAttribute(EJDAConstant.SESSION_NAME.PAGE, screenName);
				log.debug("screenName = "+screenName);
				log.debug("page = "+request.getSession().getAttribute(EJDAConstant.SESSION_NAME.PAGE));
				if(ejdaAction.equalsIgnoreCase("Logout")){
					response.sendRedirect(screenName);
				}else{
					response.sendRedirect(EJDAConstant.PAGE.INDEX2_PAGE);
				}
			}				
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			o = null;
		}
	}

}

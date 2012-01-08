package com.ejda.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.tcd.ejda.dao.CacheDataDAO;
import com.tcd.ejda.dao.CacheDataDAOImpl;
import com.ejda.util.DisplayUtil;
/**
 * Servlet implementation class AjaxLoadCacheData
 */
public class AjaxLoadCacheData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(AjaxLoadCacheData.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxLoadCacheData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String returnValue = "";
		String loadName = request.getParameter("load_name");
		log.debug("Start : AjaxLoadCacheData =  "+loadName);
		Vector vc = new Vector();
		try {
			if (null!=loadName && "UNIT".equals(loadName)){
				
				CacheDataDAO dao = new CacheDataDAOImpl();
				vc = dao.LoadUnit();
				returnValue = DisplayUtil.displaySelectTag(vc, "", "UNIT", "EDIT", "");
			
			}else if (null!=loadName && "RATE".equals(loadName)){
				CacheDataDAO dao = new CacheDataDAOImpl();
				vc = dao.LoadExchangeRAte();
				returnValue = DisplayUtil.displaySelectTag(vc, "", "DUTY_RATE", "EDIT", "");
			}else if (null!=loadName && "TANLI".equals(loadName)){
				CacheDataDAO dao = new CacheDataDAOImpl();
				vc = dao.LoadCustomTanli();
				returnValue = DisplayUtil.displaySelectTag_Code(vc, "", "CODE_NO", "EDIT", "", "");
			}else if (null!=loadName && "ORIGIN".equals(loadName)){
				CacheDataDAO dao = new CacheDataDAOImpl();
				vc = dao.LoadCountryOrigin();
				returnValue = DisplayUtil.displaySelectTag_Code(vc, "", "ORIGIN_CODE", "EDIT", "", "");
			}else if (null!=loadName && "IMPADDR".equals(loadName)){
				String importCode = request.getParameter("importCode");
				
				CacheDataDAO dao = new CacheDataDAOImpl();
				returnValue = dao.GetImporterAddress(importCode);
				log.debug("[ IMPADDR ] " + returnValue);
			}else if (null!=loadName && "AGENTADDR".equals(loadName)){
				String agentCode = request.getParameter("agentCode");
				
				CacheDataDAO dao = new CacheDataDAOImpl();
				returnValue = dao.GetAgentAddress(agentCode);
				log.debug("[ AGENTADDR ] " + returnValue);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writeResult(request, response, returnValue);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	protected void writeResult(HttpServletRequest request,HttpServletResponse response, String result)throws ServletException, IOException {
		response.setContentType("text/xml;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter pw = response.getWriter();
		log.debug("[ writeResult ]   : result = " + result);
		pw.write(result);
		pw.close();
	}
}

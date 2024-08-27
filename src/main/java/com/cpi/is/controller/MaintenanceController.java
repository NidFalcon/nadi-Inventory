package com.cpi.is.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Servlet implementation class MaintenanceController
 */
@WebServlet("/MaintenanceController")
public class MaintenanceController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static String action = "";
	private static String page = "";
	
	private ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
//	private MaintenanceServiceImpl maintenanceService = (MaintenanceServiceImpl) context.getBean("maintenanceService");
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MaintenanceController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			action = request.getParameter("action");
			
			if ("showMaintenance".equals(action)) {
				page = "pages/maintenance.jsp";
			} else if ("showMngDispatchTypes".equals(action)) {
				page = "pages/maintenance/DispatchTypes.jsp";
			} else if ("showMngBranches".equals(action)) {
				page = "pages/maintenance/Branches.jsp";
			} else if ("showMngSkuCodes".equals(action)) {
				page = "pages/maintenance/SkuCodes.jsp";
			} else if ("showMngMaterialCodes".equals(action)) {
				page = "pages/maintenance/MaterialCodes.jsp";
			} 
			
//			else if ("saveItem".equals(action)) {
//				request.setAttribute("message", maintenanceService.saveItem(request));
//				page = "pages/message.jsp";
//			} else if ("deleteItem".equals(action)) {
//				request.setAttribute("message", maintenanceService.deleteItem(request));
//				page = "pages/message.jsp";
//			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			request.getRequestDispatcher(page).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

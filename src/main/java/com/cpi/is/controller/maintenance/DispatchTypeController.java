package com.cpi.is.controller.maintenance;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cpi.is.service.maintenance.DispatchTypeService;

/**
 * Servlet implementation class DispatchTypeController
 */
@WebServlet("/DispatchTypeController")
public class DispatchTypeController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static String action = "";
	private static String page = "";
	
	private ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
	private DispatchTypeService dispatchTypeService = (DispatchTypeService) context.getBean("dispatchTypeService");
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispatchTypeController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			action = request.getParameter("action");
			
			if ("showDispatchType".equals(action)) {
				request.setAttribute("dispatchType", new JSONArray(dispatchTypeService.getDispatchType()));
				page = "pages/navbar/maintenance/dispatchType.jsp";
			} else if ("saveItem".equals(action)) {
				String message = dispatchTypeService.saveItem(request);
				request.setAttribute("message", message);
				page = "pages/message/success.jsp";
			} else if ("deleteItem".equals(action)) {
				String message = dispatchTypeService.deleteItem(request);
				request.setAttribute("message", message);
				page = "pages/message/success.jsp";
			} 
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

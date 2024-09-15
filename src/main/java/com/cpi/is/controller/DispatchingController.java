package com.cpi.is.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cpi.is.entity.UserEntity;
import com.cpi.is.service.impl.DispatchingServiceImpl;
import com.cpi.is.service.impl.inventory.FinishedProductListServiceImpl;
import com.cpi.is.service.impl.maintenance.BranchServiceImpl;
import com.cpi.is.service.impl.maintenance.DispatchTypeServiceImpl;

/**
 * Servlet implementation class DispatchingController
 */
@WebServlet("/DispatchingController")
public class DispatchingController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static String action = "";
	private static String page = "";

	private ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
	private DispatchingServiceImpl dispatchingService = (DispatchingServiceImpl) context.getBean("dispatchingService");
	private BranchServiceImpl branchService = (BranchServiceImpl) context.getBean("branchService");
	private DispatchTypeServiceImpl dispatchTypeService = (DispatchTypeServiceImpl) context
			.getBean("dispatchTypeService");
	private FinishedProductListServiceImpl finishedProductListService = (FinishedProductListServiceImpl) context
			.getBean("finishedProductListService");

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DispatchingController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			action = request.getParameter("action");

			HttpSession session = request.getSession();
			UserEntity user = (UserEntity) session.getAttribute("user");
			Integer branchId = user.getBranchId(); // Retrieve branchId from session

			if ("showDispatching".equals(action)) {
				// Get filtered dispatch data based on branchId
				request.setAttribute("dispatch", new JSONArray(dispatchingService.getDispatchingByBranch(branchId)));
				request.setAttribute("dispatchType", new JSONArray(dispatchTypeService.getDispatchType()));
				request.setAttribute("branch", new JSONArray(branchService.getBranch()));
				request.setAttribute("finishedProduct", new JSONArray(finishedProductListService.getFinishedProductList(request)));
                request.setAttribute("currentInventory", new JSONArray(dispatchingService.getCurrentInventory(branchId)));
				page = "pages/navbar/dispatching.jsp";
			} else if ("saveItem".equals(action)) {
				request.setAttribute("message", dispatchingService.saveItem(request));
				page = "pages/message/message.jsp";
			} else if ("deleteItem".equals(action)) {
				request.setAttribute("message", dispatchingService.deleteItem(request));
				page = "pages/message/message.jsp";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			request.getRequestDispatcher(page).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

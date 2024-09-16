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
import com.cpi.is.exception.InvalidJsonException;
import com.cpi.is.service.impl.DispatchingServiceImpl;
import com.cpi.is.service.impl.inventory.FinishedProductListServiceImpl;
import com.cpi.is.service.impl.maintenance.DispatchTypeServiceImpl;
import com.cpi.is.util.SessionUtil;

@WebServlet("/DispatchingController")
public class DispatchingController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static String action = "";
	private static String page = "";

	private ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
	private DispatchingServiceImpl dispatchingService = (DispatchingServiceImpl) context.getBean("dispatchingService");
	private DispatchTypeServiceImpl dispatchTypeService = (DispatchTypeServiceImpl) context
			.getBean("dispatchTypeService");
	private FinishedProductListServiceImpl finishedProductListService = (FinishedProductListServiceImpl) context
			.getBean("finishedProductListService");

	public DispatchingController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			action = request.getParameter("action");

			if (SessionUtil.isUserLoggedIn(request)) {
				HttpSession session = request.getSession();
				UserEntity user = (UserEntity) session.getAttribute("user");
				Long branchId = user.getBranchId(); // Retrieve branchId from session

				if ("showDispatching".equals(action)) {
					request.setAttribute("dispatch",
							new JSONArray(dispatchingService.getDispatchingByBranchId(branchId)));
					request.setAttribute("dispatchType", new JSONArray(dispatchTypeService.getDispatchType()));
					request.setAttribute("finishedProduct",
							new JSONArray(finishedProductListService.getFinishedProductList(branchId)));
					request.setAttribute("currentInventory",
							new JSONArray(dispatchingService.getCurrentInventory(branchId)));
					page = "pages/navbar/dispatching.jsp";
				} else if ("saveItem".equals(action)) {
					request.setAttribute("message", dispatchingService.saveItem(request,
							finishedProductListService.getFinishedProductList(branchId)));
					page = "pages/message/success.jsp";
				} else if ("deleteItem".equals(action)) {
					request.setAttribute("message", dispatchingService.deleteItem(request));
					page = "pages/message/success.jsp";
				}
			} else {
				page = "/UserController";
				request.setAttribute("action", "timeout");
			}
		} catch (InvalidJsonException e) {
			request.setAttribute("message", e.getMessage());
			page = "pages/message/message.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "Something went wrong");
			page = "pages/message/message.jsp";
		} finally {
			request.getRequestDispatcher(page).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

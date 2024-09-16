package com.cpi.is.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cpi.is.service.impl.ReportServiceImpl;
import com.cpi.is.util.EscapeUtil;
import com.cpi.is.util.SessionUtil;

@WebServlet("/ReportController")
public class ReportController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static String action = "";
	private static String page = "";

	private ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
	private ReportServiceImpl reportService = (ReportServiceImpl) context.getBean("reportService");

    public ReportController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			action = request.getParameter("action");
			System.out.println(SessionUtil.isUserLoggedIn(request));
			if (SessionUtil.isUserLoggedIn(request)) {
				if ("showReports".equals(action)) {
					request.setAttribute("defaultDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
					page = "pages/navbar/reports.jsp";
				} else if ("getCurrentFinishedInventory".equals(action)) {
					JSONArray test =  EscapeUtil.escapeQuotes(new JSONArray(reportService.getCurrentFinishedInventory(request)));
					System.out.println(test);
					request.setAttribute("message", test);
					page = "pages/message/reportTable.jsp";
				} else if ("getPlannedRawMaterialsInventory".equals(action)) {
					request.setAttribute("message", EscapeUtil.escapeQuotes(new JSONArray(reportService.getPlannedRawMaterialsInventory(request))));
					page = "pages/message/reportTable.jsp";
				} else if ("getProductionReport".equals(action)) {
					request.setAttribute("message", EscapeUtil.escapeQuotes(new JSONArray(reportService.getProductionReport(request))));
					page = "pages/message/reportTable.jsp";
				} else if ("getReceivedInventoryReport".equals(action)) {
					request.setAttribute("message", EscapeUtil.escapeQuotes(new JSONArray(reportService.getReceivedInventoryReport(request))));
					page = "pages/message/reportTable.jsp";
				}
			} else {
				System.out.println("report logging out");
				page = "/UserController";
				request.setAttribute("action", "timeout");
				System.out.println("request is " + request.getAttribute(action));
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (e.getMessage().contains("Cannot invoke *hibernate")) {
				request.setAttribute("message", "Unable to connect to database");
			} else {
				request.setAttribute("message", "Something went wrong");
			}
			page = "pages/message/message.jsp";
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
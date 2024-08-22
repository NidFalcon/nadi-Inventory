package com.cpi.nadi.is.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MaintenanceController")
public class MaintenanceController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String page ="";
	private static String action = "";
	
    public MaintenanceController() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		action = request.getParameter("action");
		
		if ("showMaintenance".equals(action)) {
			page = "pages/maintenance.jsp";
		}
		
		request.getRequestDispatcher(page).forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

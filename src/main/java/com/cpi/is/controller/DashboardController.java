package com.cpi.is.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cpi.is.entity.UserEntity;
import com.cpi.is.util.SessionUtil;

@WebServlet("/DashboardController")
public class DashboardController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static String action = "";
	private static String page = "";
       
    public DashboardController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		action = request.getParameter("action");
		
		try {
			if (SessionUtil.isUserLoggedIn(request)) {
				action = request.getParameter("action");
				if ("showDashboard".equals(action)) {
					UserEntity user = (UserEntity) request.getSession().getAttribute("user");
					request.setAttribute("username", user.getUsername());
					request.setAttribute("branchName", user.getBranch().getBranchName());
					page = "pages/navbar/dashboard.jsp";
				}
			} else {
				page = "/UserController";
				request.setAttribute("action", "timeout");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher(page).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

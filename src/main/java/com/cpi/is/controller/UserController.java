package com.cpi.is.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cpi.is.entity.UserEntity;
import com.cpi.is.service.impl.UserServiceImpl;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/UserController")
public class UserController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static String action = "";
	private static String page = "";
	
	private UserServiceImpl userService = new UserServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			action = request.getParameter("action");
			
			if ("login".equals(action)) {
				UserEntity user = userService.authenticate(request);
				
				if (user != null) {
					
					// set user cookie
					Cookie cookie = new Cookie("user", user.getUsername());
					cookie.setMaxAge(24*60*60);
					response.addCookie(cookie);
					
					// set user session
					HttpSession session = request.getSession();
					session.setAttribute("user", user);
					
					request.setAttribute("username", user.getUsername());
					page = "pages/menu.jsp";
				} else {
					request.setAttribute("message", "Invalid Username or Password");
					page = "pages/message.jsp";
				}
			} else if ("logout".equals(action)) {
				
				// destroy user cookie
				Cookie cookie = new Cookie("user", "");
				cookie.setMaxAge(0);
				response.addCookie(cookie);
				
				// invalidate user session
				HttpSession session = request.getSession();
				session.invalidate();
				
				page = "pages/login.jsp";
			} else if ("checkUserCookie".equals(action)) {
				request.setAttribute("message", "No existing user cookie");
				page = "pages/message.jsp";
				
				Cookie[] cookies = request.getCookies();
				
				if (cookies != null) {
					for (Cookie cookie : cookies) {
						if (cookie.getName().equals("user")) {
							request.setAttribute("username", cookie.getValue());
							page = "pages/menu.jsp";
							break;
						}
					}
				}
			} else if ("checkUserSession".equals(action)) {
				request.setAttribute("message", "No existing user session");
				page = "pages/message.jsp";
				
				HttpSession session = request.getSession();
				UserEntity user = (UserEntity) session.getAttribute("user");
				
				if (user != null) {
					request.setAttribute("username", user.getUsername());
					page = "pages/menu.jsp";
				}
			}
		} catch (FileNotFoundException | ClassNotFoundException | SQLException e) {
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

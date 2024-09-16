package com.cpi.is.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cpi.is.entity.SessionEntity;
import com.cpi.is.entity.UserEntity;
import com.cpi.is.service.impl.UserServiceImpl;
import com.cpi.is.service.impl.maintenance.BranchServiceImpl;

import org.json.JSONArray;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@WebServlet("/UserController")
public class UserController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static String action = "";
	private static String page = "";
	
	private ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
	private UserServiceImpl userService = (UserServiceImpl) context.getBean("userService");
	private BranchServiceImpl branchService = (BranchServiceImpl) context.getBean("branchService");
    
    public UserController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			if (request.getAttribute("action") != null) {
				action = (String) request.getAttribute("action");
			} else {
				action = request.getParameter("action");
			}
			
			if ("login".equals(action)) { 
		        
				UserEntity user = userService.authenticate(request);
				
				if (user != null) {
					HttpSession session = request.getSession();
					session.invalidate();
					session = request.getSession(true);
					session.setAttribute("user", user);
					request.setAttribute("username", user.getUsername());
					session.setMaxInactiveInterval(300);
					Cookie sessionCookie = new Cookie("SESSIONID", session.getId());
					sessionCookie.setMaxAge(0);
			        sessionCookie.setHttpOnly(true); // Prevents JavaScript access
			        sessionCookie.setSecure(true); // Ensures cookie is sent over HTTPS
			        response.addCookie(sessionCookie);
					
					page = "pages/navbar/menu.jsp";
				} else {
					request.setAttribute("message", "Invalid Username or Password");
					page = "pages/message/message.jsp";
				}
			}  else if ("logout".equals(action)) {
				HttpSession session = request.getSession();
				logoutUser(session, request ,response);
				page = "pages/login.jsp";
			} else if ("checkUserSession".equals(action)) {
				HttpSession session = request.getSession(false);
				UserEntity user = (UserEntity) session.getAttribute("user");
				page = "pages/navbar/menu.jsp";
				
				if (user != null) {
					request.setAttribute("username", user.getUsername());
				} else {
					SessionEntity userSession = userService.validateSession(request);
					if (userSession != null) {
						request.setAttribute("username", userSession.getUsername());
					} else {
						page = "pages/login.jsp";
					}
				}
			} else if ("showRegisterPage".equals(action)) {
				JSONArray test = new JSONArray(branchService.getBranch());
				request.setAttribute("branches", test );
				page="pages/registration.jsp";
			} else if ("registerNewUser".equals(action)) {
				request.setAttribute("message", userService.registerNewUser(request));
				page = "pages/message/message.jsp";
			}  else if ("timeout".equals(request.getAttribute("action"))) {
				HttpSession session = request.getSession();
				logoutUser(session, request ,response);
		        page = "pages/login.jsp";
		        request.setAttribute("message", "Please log in to continue.");
			};
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			request.getRequestDispatcher(page).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private HttpServletResponse logoutUser(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
	    if (session != null) {
			session.invalidate();
			userService.deleteSession(request);
	    }

	    // Remove all session-related cookies
	    Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	        for (Cookie cookie : cookies) {
	            cookie.setValue("");
	            cookie.setMaxAge(0);
	            response.addCookie(cookie);
	        }
	    }
	    return response;
	}
}

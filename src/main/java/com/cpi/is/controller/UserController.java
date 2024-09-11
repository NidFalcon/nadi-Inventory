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
			action = request.getParameter("action");
			
			if ("login".equals(action)) {
				UserEntity user = userService.authenticate(request);
				
				if (user != null) {
					HttpSession session = request.getSession();
					session.setAttribute("user", user);
					request.setAttribute("username", user.getUsername());
					userService.saveSession(request);
					
					Cookie userCookie = new Cookie("user", user.getUsername());
					userCookie.setMaxAge(24*60*60);
					response.addCookie(userCookie);
					
					Cookie sessionCookie = new Cookie("sessionId", request.getSession().getId());
					sessionCookie.setMaxAge(24*60*60);
					response.addCookie(sessionCookie);
					
					page = "pages/navbar/menu.jsp";
				} else {
					request.setAttribute("message", "Invalid Username or Password");
					page = "pages/message.jsp";
				}
			}  else if ("logout".equals(action)) {
				HttpSession session = request.getSession();
				session.invalidate();
				userService.deleteSession(request);
				
				Cookie userCookie = new Cookie("user", "");
				userCookie.setMaxAge(0);
				response.addCookie(userCookie);
				
				Cookie sessionCookie = new Cookie("sessionId", "");
				sessionCookie.setMaxAge(0);
				response.addCookie(sessionCookie);
				
				page = "pages/login.jsp";
			} else if ("checkUserSession".equals(action)) {
				HttpSession session = request.getSession();
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
				System.out.println(test);
				request.setAttribute("branches", test );
				page="pages/registration.jsp";
			} else if ("registerNewUser".equals(action)) {
				request.setAttribute("message", userService.registerNewUser(request));
				page = "pages/message.jsp";
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println(page);
			request.getRequestDispatcher(page).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

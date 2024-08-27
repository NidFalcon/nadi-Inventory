package com.cpi.nadi.is.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.cpi.nadi.is.service.impl.UserServiceImpl;
import com.cpi.nadi.is.config.AppConfig;
import com.cpi.nadi.is.entity.User;

@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String action = "";
	private String page = "";
	
	ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
	private UserServiceImpl userService = ctx.getBean(UserServiceImpl.class);
       
    public UserController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		action = request.getParameter("action");
		
		try {
			if ("login".equals(action)) {
				User user = userService.authenticate(request);
				
				if (user != null) {
					// set user cookie
					Cookie cookie = new Cookie("user", user.getUsername());
					cookie.setMaxAge(24*60*60);
					response.addCookie(cookie);
					
					// set user session
					HttpSession session = request.getSession();
					session.setAttribute("user", user);
					
					request.setAttribute("username", user.getUsername());
					System.out.println(user.getUsername());
					System.out.println(user.getBranchId());
					page = "pages/menu.jsp";
				} else {
					request.setAttribute("message", "Invalid Username or Password");
					page = "pages/message.jsp";
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			request.getRequestDispatcher(page).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}

}

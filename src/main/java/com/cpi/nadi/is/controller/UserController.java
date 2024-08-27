package com.cpi.nadi.is.controller;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.cpi.nadi.is.config.AppConfig;
import com.cpi.nadi.is.entity.User;
import com.cpi.nadi.is.service.impl.UserServiceImpl;


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
    
		System.out.println("HELLO FROM JS");
		User user = userService.authenticate(request);
		ModelAndView mav = new ModelAndView();
		
		if (user != null) {
			// set user cookie
			Cookie cookie = new Cookie("user", user.getUsername());
			cookie.setMaxAge(24*60*60);
			response.addCookie(cookie);
			
			// set user session
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			
			System.out.println(user.getUsername());
			mav.setViewName("menu");
			mav.addObject("username", user.getUsername());
		} else {
	        mav.setViewName("report");
	        mav.addObject("message", "Invalid Username or Password");
		}
		
		return mav;
		
		/*
		model.addAttribute("username", "Tingyun");
		model.addAttribute("date", new Date());
		*/
	}
}

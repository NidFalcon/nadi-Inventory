package com.cpi.nadi.is.controller;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.cpi.nadi.is.config.AppConfig;
import com.cpi.nadi.is.entity.User;
import com.cpi.nadi.is.service.impl.UserServiceImpl;

@Controller
public class UserController {
	
	private ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
	private UserServiceImpl userService = ctx.getBean(UserServiceImpl.class);
	
	@PostMapping("/login")
	@ResponseBody
	public String hello(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
			return "menu";
		} else {
			request.setAttribute("message", "Invalid Username or Password");
			return "report";
		}
		
		/*
		model.addAttribute("username", "Tingyun");
		model.addAttribute("date", new Date());
		*/
	}
}

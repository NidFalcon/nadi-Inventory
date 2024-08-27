package com.cpi.is.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cpi.is.service.impl.UserServiceImpl;

/**
 * Servlet implementation class RawMaterialsController
 */
@WebServlet("/RawMaterialsController")
public class RawMaterialsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static String action = "";
	private static String page = "";
	
	private ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
	//private UserServiceImpl userService = (UserServiceImpl) context.getBean("userService");
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RawMaterialsController() {
        super();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		action = (String) request.getAttribute("action");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

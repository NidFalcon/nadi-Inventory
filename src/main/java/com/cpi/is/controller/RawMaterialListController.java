package com.cpi.is.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cpi.is.service.impl.RawMaterialListServiceImpl;

@WebServlet("/RawMaterialListController")
public class RawMaterialListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String action = "";
	private static String page = "";
	
	private ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
	private RawMaterialListServiceImpl rawMaterialListService = (RawMaterialListServiceImpl) context.getBean("rawMaterialListService");

    public RawMaterialListController() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			action = request.getParameter("action");
			if ("showRawMaterialList".equals(action)) {
				request.setAttribute("inventory", new JSONArray(rawMaterialListService.getRawMaterialList()));
				page = "pages/rawMaterialList.jsp";
			} else if ("saveItem".equals(action)) {
				request.setAttribute("message", rawMaterialListService.saveItem(request));
				page = "pages/message.jsp";
			} else if ("deleteItem".equals(action)) {
				request.setAttribute("message", rawMaterialListService.deleteItem(request));
				page = "pages/message.jsp";
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

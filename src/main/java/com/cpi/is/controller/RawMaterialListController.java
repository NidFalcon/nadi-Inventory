package com.cpi.is.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cpi.is.exception.InvalidJsonException;
import com.cpi.is.service.impl.RawMaterialListServiceImpl;
import com.cpi.is.service.impl.RawMaterialServiceImpl;

@WebServlet("/RawMaterialListController")
public class RawMaterialListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String action = "";
	private static String page = "";
	
	private ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
	private RawMaterialListServiceImpl rawMaterialListService = (RawMaterialListServiceImpl) context.getBean("rawMaterialListService");
	private RawMaterialServiceImpl rawMaterialService = (RawMaterialServiceImpl) context.getBean("rawMaterialService");
    
	public RawMaterialListController() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			action = request.getParameter("action");
			if ("showRawMaterialList".equals(action)) {
				request.setAttribute("rawMaterialList", new JSONArray(rawMaterialListService.getRawMaterialList(request)));
				JSONArray materials = new JSONArray(rawMaterialService.getRawMaterial());
				request.setAttribute("material", materials);
				page = "pages/navbar/inventory/rawMaterialList.jsp";
			} else if ("saveRawMaterial".equals(action)) {
				HttpSession session = request.getSession();
				request.setAttribute("message", rawMaterialListService.saveRawMaterial(request, session));
				page = "pages/message.jsp";
			} else if ("deleteRawMaterial".equals(action)) {
				System.out.println("HELLO");
				request.setAttribute("message", rawMaterialListService.deleteRawMaterial(request));
				page = "pages/message.jsp";
			}
		} catch (InvalidJsonException e) {
			request.setAttribute("message", e.getMessage());
			page = "pages/message.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "something went wrong! oops~");
			page = "pages/message.jsp";
		} finally {
			request.getRequestDispatcher(page).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

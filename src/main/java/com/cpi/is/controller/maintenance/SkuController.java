package com.cpi.is.controller.maintenance;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cpi.is.service.maintenance.SkuService;

/**
 * Servlet implementation class SkuController
 */
@WebServlet("/SkuController")
public class SkuController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static String action = "";
    private static String page = "";

    private ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    private SkuService skuService = (SkuService) context.getBean("skuService");

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SkuController() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            action = request.getParameter("action");

            if ("showSku".equals(action)) {
                request.setAttribute("sku", new JSONArray(skuService.getSku()));
                page = "pages/navbar/maintenance/sku.jsp";
            } else if ("saveItem".equals(action)) {
                String message = skuService.saveItem(request);
                request.setAttribute("message", message);
                page = "pages/message.jsp";
            } else if ("deleteItem".equals(action)) {
                String message = skuService.deleteItem(request);
                request.setAttribute("message", message);
                page = "pages/message.jsp";
            }
        } catch (Exception e) {
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

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

import com.cpi.is.service.BranchService;
/**
 * Servlet implementation class BranchController
 */
@WebServlet("/BranchController")
public class BranchController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static String action = "";
    private static String page = "";

    private ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    private BranchService branchService = (BranchService) context.getBean("branchService");

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BranchController() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            action = request.getParameter("action");

            if ("showBranch".equals(action)) {
                request.setAttribute("branch", new JSONArray(branchService.getBranch()));
                page = "pages/maintenance/branch.jsp";
            } else if ("saveItem".equals(action)) {
                String message = branchService.saveItem(request);
                request.setAttribute("message", message);
                page = "pages/message.jsp";
            } else if ("deleteItem".equals(action)) {
                String message = branchService.deleteItem(request);
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

package com.cpi.is.controller.inventory;

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

import com.cpi.is.entity.UserEntity;
import com.cpi.is.service.impl.inventory.FinishedProductListServiceImpl;
import com.cpi.is.util.SessionUtil;

@WebServlet("/FinishedProductListController")
public class FinishedProductListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String action = "";
	private static String page = "";
	
	private ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
	private FinishedProductListServiceImpl finishedProductListService = (FinishedProductListServiceImpl) context.getBean("finishedProductListService");
	
    public FinishedProductListController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			action = request.getParameter("action");
			
			if (SessionUtil.isUserLoggedIn(request)) {
				if ("showFinishedProductList".equals(action)) {
					HttpSession session = request.getSession();
					UserEntity user = (UserEntity) session.getAttribute("user");
					request.setAttribute("finishedProductList", new JSONArray(finishedProductListService.getFinishedProductList(user.getBranchId())));
					page = "pages/navbar/inventory/finishedProductList.jsp";
				} else if ("saveItem".equals(action)) {
					request.setAttribute("message", finishedProductListService.saveProduct(request));
					page = "pages/message.jsp";
				} else if ("deleteItem".equals(action)) {
					request.setAttribute("message", finishedProductListService.deleteProduct(request));
					page = "pages/message.jsp";
				}
			} else {
				page = "/UserController";
				request.setAttribute("action", "timeout");
				System.out.println("request is " + request.getAttribute(action));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			request.getRequestDispatcher(page).forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
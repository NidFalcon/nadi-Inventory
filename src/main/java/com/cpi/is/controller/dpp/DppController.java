package com.cpi.is.controller.dpp;

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
import com.cpi.is.exception.InvalidJsonException;
import com.cpi.is.service.dpp.DppService;
import com.cpi.is.service.dpp.ProductionMaterialService;
import com.cpi.is.service.inventory.RawMaterialListService;
import com.cpi.is.service.maintenance.RawMaterialService;
import com.cpi.is.service.maintenance.SkuService;
import com.cpi.is.util.SessionUtil;

@WebServlet("/DppController")
public class DppController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static String action = "";
    private static String page = "";

    private ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    private DppService dppService = (DppService) context.getBean("dppService");
    private SkuService skuService = (SkuService) context.getBean("skuService");
    private RawMaterialService rawMaterialService = (RawMaterialService) context.getBean("rawMaterialService");
    private RawMaterialListService rawMaterialListService = (RawMaterialListService) context.getBean("rawMaterialListService");
    private ProductionMaterialService productionMaterialService = (ProductionMaterialService) context.getBean("productionMaterialService");

    public DppController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            action = request.getParameter("action");
			
			if (SessionUtil.isUserLoggedIn(request)) {
	            HttpSession session = request.getSession(false);
				UserEntity user = (UserEntity) session.getAttribute("user");
				Integer branchId = user.getBranchId(); 
				
	            if ("showDpp".equals(action)) {
	                request.setAttribute("dpp", new JSONArray(dppService.getDpp(branchId)));
	                request.setAttribute("sku", new JSONArray(skuService.getSku()));
	                request.setAttribute("rawMaterial", new JSONArray(rawMaterialService.getRawMaterial()));
	                request.setAttribute("rawMaterialList", new JSONArray(rawMaterialListService.getRawMaterialList(request)));
	                request.setAttribute("productionMaterial", new JSONArray(productionMaterialService.getProductionMaterial()));
	                page = "pages/navbar/dpp.jsp";
	            } else if ("saveItem".equals(action)) {
	                String message = dppService.saveItem(request);
	                request.setAttribute("message", message);
	                page = "pages/message/success.jsp";
	            } else if ("deleteItem".equals(action)) {
	                String message = dppService.deleteItem(request);
	                request.setAttribute("message", message);
	                page = "pages/message/success.jsp";
	            }
			} else {
				page = "/UserController";
				request.setAttribute("action", "timeout");
			}
        } catch (InvalidJsonException e) {
			request.setAttribute("message", e.getMessage());
			page = "pages/message/message.jsp";
		} catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Something went wrong");
            page = "pages/message/message.jsp";
        } finally {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
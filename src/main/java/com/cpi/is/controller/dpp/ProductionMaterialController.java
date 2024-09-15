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
import com.cpi.is.service.maintenance.RawMaterialService;

@WebServlet("/ProductionMaterialController")
public class ProductionMaterialController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static String action = "";
    private static String page = "";

    private ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    private DppService dppService = (DppService) context.getBean("dppService");
    private ProductionMaterialService productionMaterialService = (ProductionMaterialService) context.getBean("productionMaterialService");
    private RawMaterialService rawMaterialService = (RawMaterialService) context.getBean("rawMaterialService");
 
    public ProductionMaterialController() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            action = request.getParameter("action");

            HttpSession session = request.getSession();
			UserEntity user = (UserEntity) session.getAttribute("user");
			Integer branchId = user.getBranchId(); 
			
            if ("showProductionMaterial".equals(action)) {
                request.setAttribute("productionMaterial", new JSONArray(productionMaterialService.getProductionMaterial()));
                request.setAttribute("dpp", new JSONArray(dppService.getDpp(branchId)));
                request.setAttribute("rawMaterial", new JSONArray(rawMaterialService.getRawMaterial()));
                page = "pages/productionMaterial.jsp";
            } else if ("saveItem".equals(action)) {
                String message = productionMaterialService.saveItem(request);
                request.setAttribute("message", message);
                page = "pages/success.jsp";
            } else if ("deleteItem".equals(action)) {
                String message = productionMaterialService.deleteItem(request);
                request.setAttribute("message", message);
                page = "pages/success.jsp";
            } else if ("saveBulkItems".equals(action)) {
            	String message = productionMaterialService.saveBulkItems(request);
            	request.setAttribute("message", message);
                page = "pages/success.jsp";
            }
        } catch (InvalidJsonException e) {
			request.setAttribute("message", e.getMessage());
			page = "pages/message.jsp";
		} catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Something went wrong");
            page = "pages/message.jsp";
        } finally {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

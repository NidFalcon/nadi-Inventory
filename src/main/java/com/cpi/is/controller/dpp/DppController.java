package com.cpi.is.controller.dpp;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cpi.is.service.dpp.DppService;
import com.cpi.is.service.dpp.ProductionMaterialService;
import com.cpi.is.service.maintenance.RawMaterialService;
import com.cpi.is.service.maintenance.SkuService;

@WebServlet("/DppController")
public class DppController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static String action = "";
    private static String page = "";

    private ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    private DppService dppService = (DppService) context.getBean("dppService");
    private SkuService skuService = (SkuService) context.getBean("skuService");
    private RawMaterialService rawMaterialService = (RawMaterialService) context.getBean("rawMaterialService");
    private ProductionMaterialService productionMaterialService = (ProductionMaterialService) context.getBean("productionMaterialService");

    public DppController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            action = request.getParameter("action");

            if ("showDpp".equals(action)) {
                request.setAttribute("dpp", new JSONArray(dppService.getDpp()));
                request.setAttribute("sku", new JSONArray(skuService.getSku()));
                request.setAttribute("rawMaterial", new JSONArray(rawMaterialService.getRawMaterial()));
                request.setAttribute("productionMaterial", new JSONArray(productionMaterialService.getProductionMaterial()));
                page = "pages/navbar/dpp.jsp";
            } else if ("saveItem".equals(action)) {
                String message = dppService.saveItem(request);
                request.setAttribute("message", message);
                page = "pages/message.jsp";
            } else if ("deleteItem".equals(action)) {
                String message = dppService.deleteItem(request);
                request.setAttribute("message", message);
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
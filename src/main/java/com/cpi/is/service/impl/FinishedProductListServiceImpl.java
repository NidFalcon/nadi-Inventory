package com.cpi.is.service.impl;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.json.JSONObject;
import com.cpi.is.dao.FinishedProductListDAO;
import com.cpi.is.entity.FinishedProductListEntity;
import com.cpi.is.service.FinishedProductListService;

public class FinishedProductListServiceImpl implements FinishedProductListService {

    private FinishedProductListDAO finishedProductListDAO;

    public FinishedProductListDAO getFinishedProductListDAO() {
        return finishedProductListDAO;
    }

    public void setFinishedProductListDAO(FinishedProductListDAO finishedProductListDAO) {
        this.finishedProductListDAO = finishedProductListDAO;
    }

    private FinishedProductListEntity jsonToEntity(JSONObject json) {
        Long fplId = json.has("fplId") && !json.isNull("fplId") ? json.getLong("fplId") : null;
        String skuCd = json.getString("skuCd"); // Fixed variable name
        Integer quantity = json.getInt("quantity");
        Long branchId = json.getLong("branchId");
        Date dateFinished = new Date(json.getLong("dateFinished")); // Ensure proper date parsing

        return new FinishedProductListEntity(fplId, skuCd, quantity, branchId, dateFinished); // Fixed variable names
    }

    @Override
    public List<FinishedProductListEntity> getFinishedProductList() throws Exception {
        return finishedProductListDAO.getFinishedProductList();
    }

    @Override
    public String saveItem(HttpServletRequest request) throws Exception {
        return finishedProductListDAO.saveItem(jsonToEntity(new JSONObject(request.getParameter("item"))));
    }

    @Override
    public String deleteItem(HttpServletRequest request) throws Exception {
        return finishedProductListDAO.deleteItem(jsonToEntity(new JSONObject(request.getParameter("item"))));
    }
}
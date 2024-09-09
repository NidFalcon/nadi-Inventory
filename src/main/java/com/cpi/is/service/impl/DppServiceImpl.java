package com.cpi.is.service.impl;

import java.sql.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.json.JSONObject;
import com.cpi.is.dao.impl.DppDAOImpl;
import com.cpi.is.entity.DppEntity;
import com.cpi.is.entity.UserEntity;
import com.cpi.is.service.DppService;

public class DppServiceImpl implements DppService {

    private DppDAOImpl dppDAO;

    public DppDAOImpl getDppDAO() {
        return dppDAO;
    }

    public void setDppDAO(DppDAOImpl dppDAO) {
        this.dppDAO = dppDAO;
    }

    private DppEntity jsonToEntity(JSONObject json) {
        Long dppId = json.has("dppId") && !json.isNull("dppId") ? json.getLong("dppId") : null;
        Date productionDate = json.has("productionDate") ? Date.valueOf(json.getString("productionDate")) : null;
        Integer quantity = json.has("quantity") ? json.getInt("quantity") : null;
        String status = json.has("status") ? json.getString("status") : null;
        
        Long branchId = json.has("branchId") ? json.getLong("branchId") : null;
        String skuCode = json.has("skuCode") ? json.getString("skuCode") : null;

        return new DppEntity(dppId, productionDate, quantity, status, branchId, skuCode);
    }

    @Override
    public List<DppEntity> getDpp() throws Exception {
        return dppDAO.getDpp();
    }

    @Override
    public String saveItem(HttpServletRequest request) throws Exception {
    	JSONObject newDppJson = new JSONObject(request.getParameter("item"));
    	UserEntity user = (UserEntity) request.getSession().getAttribute("user");
    	newDppJson.put("branchId", user.getBranchId());
        return dppDAO.saveItem(jsonToEntity(newDppJson));
    }

    @Override
    public String deleteItem(HttpServletRequest request) throws Exception {
        return dppDAO.deleteItem(
                jsonToEntity(new JSONObject(request.getParameter("item"))));
    }
}

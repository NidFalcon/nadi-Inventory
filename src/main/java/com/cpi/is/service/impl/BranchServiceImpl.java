package com.cpi.is.service.impl;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.json.JSONObject;
import com.cpi.is.dao.BranchDAO;
import com.cpi.is.entity.BranchEntity;
import com.cpi.is.service.BranchService;

public class BranchServiceImpl implements BranchService {

    private BranchDAO branchDAO;
    
    public BranchDAO getBranchDAO() {
        return branchDAO;
    }

    public void setBranchDAO(BranchDAO branchDAO) {
        this.branchDAO = branchDAO;
    }

    private BranchEntity jsonToEntity(JSONObject json) {
        Long branchId = json.has("branchId") && !json.isNull("branchId") ? json.getLong("branchId") : null;
        String branchName = json.getString("branchName");
        String isActive = json.getString("isActive");

        return new BranchEntity(branchId, branchName, isActive);
    }

    @Override
    public List<BranchEntity> getBranch() throws Exception {
        return branchDAO.getBranch();
    }

    @Override
    public String saveItem(HttpServletRequest request) throws Exception {
        return branchDAO.saveItem(
                jsonToEntity(new JSONObject(request.getParameter("item"))));
    }

    @Override
    public String deleteItem(HttpServletRequest request) throws Exception {
        return branchDAO.deleteItem(
                jsonToEntity(new JSONObject(request.getParameter("item"))));
    }
}

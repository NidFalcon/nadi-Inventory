package com.cpi.is.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.cpi.is.dao.DispatchingDAO;
import com.cpi.is.entity.DispatchingEntity;
import com.cpi.is.entity.ReportsEntity;
import com.cpi.is.entity.UserEntity;
import com.cpi.is.service.DispatchingService;

public class DispatchingServiceImpl implements DispatchingService {

    private DispatchingDAO dispatchingDAO;

    public DispatchingDAO getDispatchingDAO() {
        return dispatchingDAO;
    }

    public void setDispatchingDAO(DispatchingDAO dispatchingDAO) {
        this.dispatchingDAO = dispatchingDAO;
    }

    private DispatchingEntity jsonToEntity(JSONObject json) {
        Long dispatchTrackId = json.has("dispatchTrackId") ? Long.parseLong(json.getString("dispatchTrackId")) : null;
        String dispatchTypeCd = json.optString("dispatchTypeCd");
        Long fplId = Long.parseLong(json.getString("fplId"));
        Integer quantity = Integer.parseInt(json.getString("quantity"));
        Integer branchId = json.has("branchId") ? json.getInt("branchId") : null;
        String destination = json.optString("destination");

        // Assuming dispatchDate is in a specific string format, e.g., "MM-dd-yyyy"
        String dispatchDateStr = json.getString("dispatchDate");
        Date dispatchDate = null;
        try {
            // Change the format to match the one used in your JSON
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dispatchDate = dateFormat.parse(dispatchDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            // Handle the exception, possibly with default value or rethrow
        }

        return new DispatchingEntity(dispatchTrackId, dispatchTypeCd, fplId, quantity, branchId, destination, dispatchDate);
    }

    @Override
    public List<DispatchingEntity> getDispatchingByBranch(Integer branchId) throws Exception {
        return dispatchingDAO.getDispatchingByBranchId(branchId);
    }

    @Override
    public List<Object[]> getCurrentInventory() throws Exception {
        return dispatchingDAO.getCurrentInventory();
    }
    
    @Override
    public String saveItem(HttpServletRequest request) throws Exception {
    	HttpSession session = request.getSession();
    	UserEntity user = (UserEntity) session.getAttribute("user");
    	JSONObject json = new JSONObject(request.getParameter("item"));
    	json.put("branchId", user.getBranchId());
        return dispatchingDAO.saveItem(jsonToEntity(json));
    }

    @Override
    public String deleteItem(HttpServletRequest request) throws Exception {
        return dispatchingDAO.deleteItem(
                jsonToEntity(new JSONObject(request.getParameter("item"))));
    }
}

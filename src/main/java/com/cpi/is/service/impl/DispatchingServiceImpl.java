package com.cpi.is.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

import com.cpi.is.dao.impl.DispatchingDAOImpl;
import com.cpi.is.entity.DispatchingEntity;
import com.cpi.is.service.DispatchingService;

public class DispatchingServiceImpl implements DispatchingService {

    private DispatchingDAOImpl dispatchingDAO;

    public DispatchingDAOImpl getDispatchingDAO() {
        return dispatchingDAO;
    }

    public void setDispatchingDAO(DispatchingDAOImpl dispatchingDAO) {
        this.dispatchingDAO = dispatchingDAO;
    }

    private DispatchingEntity jsonToEntity(JSONObject json) {
        Long dispatchTrackId = json.has("dispatchTrackId") ? Long.parseLong(json.getString("dispatchTrackId")) : null;
        String dispatchTypeCd = json.getString("dispatchTypeCd");
        Long fplId = Long.parseLong(json.getString("fplId"));
        Integer quantity = Integer.parseInt(json.getString("quantity"));
        Integer branchId = json.has("branchId") ? Integer.parseInt(json.getString("branchId")) : null;
        String destination = json.has("destination") ? json.getString("destination") : null;

        // Assuming dispatchDate is in a specific string format, e.g., "yyyy-MM-dd"
        String dispatchDateStr = json.getString("dispatchDate");
        Date dispatchDate = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dispatchDate = dateFormat.parse(dispatchDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            // Handle the exception, possibly with a default value or rethrow
        }

        return new DispatchingEntity(dispatchTrackId, dispatchTypeCd, fplId, quantity, branchId, destination, dispatchDate);
    }

    @Override
    public List<DispatchingEntity> getDispatching() throws Exception {
        return dispatchingDAO.getDispatching();
    }

    @Override
    public String saveItem(HttpServletRequest request) throws Exception {
        return dispatchingDAO.saveItem(
                jsonToEntity(new JSONObject(request.getParameter("item"))));
    }

    @Override
    public String deleteItem(HttpServletRequest request) throws Exception {
        return dispatchingDAO.deleteItem(
                jsonToEntity(new JSONObject(request.getParameter("item"))));
    }
}

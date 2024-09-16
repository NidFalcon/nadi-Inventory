package com.cpi.is.service.impl.maintenance;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

import com.cpi.is.dao.impl.maintenance.DispatchTypeDAOImpl;
import com.cpi.is.entity.UserEntity;
import com.cpi.is.entity.maintenance.DispatchTypeEntity;
import com.cpi.is.exception.InvalidJsonException;
import com.cpi.is.service.maintenance.DispatchTypeService;

public class DispatchTypeServiceImpl implements DispatchTypeService {

    private DispatchTypeDAOImpl dispatchTypeDAO;
    
    public DispatchTypeDAOImpl getDispatchTypeDAO() {
        return dispatchTypeDAO;
    }

    public void setDispatchTypeDAO(DispatchTypeDAOImpl dispatchTypeDAO) {
        this.dispatchTypeDAO = dispatchTypeDAO;
    }
    
    private DispatchTypeEntity jsonToEntity(JSONObject json) {
        String dispatchTypeCode = json.has("dispatchTypeCode") && !json.isNull("dispatchTypeCode") ? json.getString("dispatchTypeCode").trim() : null;
        String dispatchTypeName = json.has("dispatchTypeName") && !json.isNull("dispatchTypeName") ? json.getString("dispatchTypeName").trim() : null;
        String isActive = json.has("isActive") && !json.isNull("isActive") ? json.getString("isActive") : null;

        return new DispatchTypeEntity(dispatchTypeCode, dispatchTypeName, isActive);
    }

    @Override
    public List<DispatchTypeEntity> getDispatchType() throws Exception {
        return dispatchTypeDAO.getDispatchType();
    }

    @Override
    public String saveItem(HttpServletRequest request) throws Exception {
        JSONObject newJson = new JSONObject(request.getParameter("item"));
        validateJson(newJson);
        
        return dispatchTypeDAO.saveItem(jsonToEntity(newJson));
    }

    @Override
    public String deleteItem(HttpServletRequest request) throws Exception {
        return dispatchTypeDAO.deleteItem(
                jsonToEntity(new JSONObject(request.getParameter("item"))));
    }

    private void validateJson(JSONObject jsonObject) throws InvalidJsonException {
        String dispatchTypeCode = jsonObject.has("dispatchTypeCode") && !jsonObject.isNull("dispatchTypeCode") ? jsonObject.getString("dispatchTypeCode").trim() : null;
        String dispatchTypeName = jsonObject.has("dispatchTypeName") && !jsonObject.isNull("dispatchTypeName") ? jsonObject.getString("dispatchTypeName").trim() : null;

        if (dispatchTypeCode == null || dispatchTypeCode.isEmpty()) {
            throw new InvalidJsonException("Invalid ID");
        }
        if (dispatchTypeName == null || dispatchTypeName.isEmpty()) {
            throw new InvalidJsonException("Invalid Dispatch Type Name");
        }

        if (!jsonObject.has("isActive")) {
            throw new InvalidJsonException("Missing isActive field");
        }
    }
}

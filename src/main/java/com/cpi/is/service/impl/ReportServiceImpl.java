package com.cpi.is.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.cpi.is.dao.impl.ReportDAOImpl;
import com.cpi.is.entity.report.CurrentFinishedInventoryEntity;
import com.cpi.is.entity.report.PlannedRawMaterialsInventoryEntity;
import com.cpi.is.entity.report.ProductionReportEntity;
import com.cpi.is.entity.report.ReceivedInventoryReportEntity;
import com.cpi.is.service.ReportService;

public class ReportServiceImpl implements ReportService {

	private ReportDAOImpl reportDAO;

	public ReportDAOImpl getReportDAO() {
		return reportDAO;
	}

	public void setReportDAO(ReportDAOImpl reportDAO) {
		this.reportDAO = reportDAO;
	}

	@Override
	public List<CurrentFinishedInventoryEntity> getCurrentFinishedInventory(HttpServletRequest request) 
			throws Exception {
		return reportDAO.getCurrentFinishedInventory(request.getParameter("reportDate"));
	}

	@Override
	public List<PlannedRawMaterialsInventoryEntity> getPlannedRawMaterialsInventory(HttpServletRequest request)
			throws Exception {
		return reportDAO.getPlannedRawMaterialsInventory(request.getParameter("reportDate"));
	}

	@Override
	public List<ProductionReportEntity> getProductionReport(HttpServletRequest request) 
			throws Exception {
		return reportDAO.getProductionReport(request.getParameter("reportDate"));
	}

	@Override
	public List<ReceivedInventoryReportEntity> getReceivedInventoryReport(HttpServletRequest request) 
			throws Exception {
		return reportDAO.getReceivedInventoryReport(request.getParameter("reportDate"));
	}

}
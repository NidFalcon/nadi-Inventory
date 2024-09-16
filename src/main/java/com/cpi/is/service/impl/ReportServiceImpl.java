package com.cpi.is.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.cpi.is.dao.impl.ReportDAOImpl;
import com.cpi.is.entity.UserEntity;
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
		HttpSession session = request.getSession();
		UserEntity user = (UserEntity) session.getAttribute("user");
		return reportDAO.getCurrentFinishedInventory(request.getParameter("reportDate"), user.getBranchId());
	}

	@Override
	public List<PlannedRawMaterialsInventoryEntity> getPlannedRawMaterialsInventory(HttpServletRequest request)
			throws Exception {
		HttpSession session = request.getSession();
		UserEntity user = (UserEntity) session.getAttribute("user");
		return reportDAO.getPlannedRawMaterialsInventory(request.getParameter("reportDate"), user.getBranchId());
	}

	@Override
	public List<ProductionReportEntity> getProductionReport(HttpServletRequest request) 
			throws Exception {
		HttpSession session = request.getSession();
		UserEntity user = (UserEntity) session.getAttribute("user");
		return reportDAO.getProductionReport(request.getParameter("reportDate"), user.getBranchId());
	}

	@Override
	public List<ReceivedInventoryReportEntity> getReceivedInventoryReport(HttpServletRequest request) 
			throws Exception {
		HttpSession session = request.getSession();
		UserEntity user = (UserEntity) session.getAttribute("user");
		return reportDAO.getReceivedInventoryReport(request.getParameter("reportDate"), user.getBranchId());
	}

}
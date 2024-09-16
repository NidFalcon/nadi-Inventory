package com.cpi.is.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.cpi.is.entity.report.CurrentFinishedInventoryEntity;
import com.cpi.is.entity.report.PlannedRawMaterialsInventoryEntity;
import com.cpi.is.entity.report.ProductionReportEntity;
import com.cpi.is.entity.report.ReceivedInventoryReportEntity;

public interface ReportService {

	List<CurrentFinishedInventoryEntity> getCurrentFinishedInventory(HttpServletRequest request) throws Exception;
	List<PlannedRawMaterialsInventoryEntity> getPlannedRawMaterialsInventory(HttpServletRequest request) throws Exception;
	List<ProductionReportEntity> getProductionReport(HttpServletRequest request) throws Exception;
	List<ReceivedInventoryReportEntity> getReceivedInventoryReport(HttpServletRequest request) throws Exception;

}
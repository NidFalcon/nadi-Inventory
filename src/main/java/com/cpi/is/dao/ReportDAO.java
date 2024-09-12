package com.cpi.is.dao;

import java.util.List;

import com.cpi.is.entity.report.CurrentFinishedInventoryEntity;
import com.cpi.is.entity.report.PlannedRawMaterialsInventoryEntity;
import com.cpi.is.entity.report.ProductionReportEntity;
import com.cpi.is.entity.report.ReceivedInventoryReportEntity;

public interface ReportDAO {

	List<CurrentFinishedInventoryEntity> getCurrentFinishedInventory(String reportDate) throws Exception;
	List<PlannedRawMaterialsInventoryEntity> getPlannedRawMaterialsInventory(String reportDate) throws Exception;
	List<ProductionReportEntity> getProductionReport(String reportDate) throws Exception;
	List<ReceivedInventoryReportEntity> getReceivedInventoryReport(String reportDate) throws Exception;

}
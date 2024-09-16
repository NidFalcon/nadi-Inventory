package com.cpi.is.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.hibernate.query.Query;

import com.cpi.is.dao.ReportDAO;
import com.cpi.is.entity.report.CurrentFinishedInventoryEntity;
import com.cpi.is.entity.report.PlannedRawMaterialsInventoryEntity;
import com.cpi.is.entity.report.ProductionReportEntity;
import com.cpi.is.entity.report.ReceivedInventoryReportEntity;
import com.cpi.is.util.HBUtil;

public class ReportDAOImpl implements ReportDAO {

	@Override
	public List<CurrentFinishedInventoryEntity> getCurrentFinishedInventory(String reportDate) throws Exception {
		List<CurrentFinishedInventoryEntity> rows = new ArrayList<>();
		try (Session session = HBUtil.getSessionFactory().openSession()) {
			session.doWork(new Work() {
				public void execute(Connection connection) throws SQLException {
					String queryString = "SELECT a.fpl_id, a.date_finished, a.quantity, a.sku_cd, a.branch_id, d.material_name\r\n"
							+ "  FROM qkc_finished_product_list a,\r\n" + "       qkc_daily_planned_production b,\r\n"
							+ "       qkc_production_materials c,\r\n" + "       qkc_raw_material d\r\n"
							+ " WHERE a.fpl_id NOT IN (SELECT fpl_id \r\n"
							+ "                          FROM qkc_dispatch_tracking)\r\n"
							+ "   AND TRUNC(a.date_finished) <= TO_DATE('" + reportDate + "', 'YYYY-MM-DD')\r\n"
							+ "   AND b.sku_cd = a.sku_cd\r\n" + "   AND c.dpp_id = b.dpp_id\r\n"
							+ "   AND d.material_cd = c.material_cd\r\n" + " ORDER BY a.fpl_id";
					try (Statement statement = connection.createStatement();
							ResultSet resultSet = statement.executeQuery(queryString)) {

						while (resultSet.next()) {
							CurrentFinishedInventoryEntity entity = mapResultSetToEntity(resultSet);
							rows.add(entity);
						}
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace(); // Handle exceptions appropriately
		}
		return rows;
	}

	private CurrentFinishedInventoryEntity mapResultSetToEntity(ResultSet resultSet) throws SQLException {
		CurrentFinishedInventoryEntity entity = new CurrentFinishedInventoryEntity();
		entity.setFplId(resultSet.getLong("fpl_id"));
		entity.setDateFinished(resultSet.getDate("date_finished"));
		entity.setQuantity(resultSet.getLong("quantity"));
		entity.setSkuCd(resultSet.getString("sku_cd"));
		entity.setBranchId(resultSet.getLong("branch_id"));
		entity.setMaterialName(resultSet.getString("material_name"));
		return entity;
	}

	private CurrentFinishedInventoryEntity mapResultSetToEntity(ResultSet resultSet) throws SQLException {
		CurrentFinishedInventoryEntity entity = new CurrentFinishedInventoryEntity();
		entity.setFplId(resultSet.getLong("fpl_id"));
		entity.setDateFinished(resultSet.getDate("date_finished"));
		entity.setQuantity(resultSet.getLong("quantity"));
		entity.setSkuCd(resultSet.getString("sku_cd"));
		entity.setBranchId(resultSet.getLong("branch_id"));
		entity.setMaterialName(resultSet.getString("material_name"));
		return entity;
	}
	
	@Override
	public List<PlannedRawMaterialsInventoryEntity> getPlannedRawMaterialsInventory(String reportDate) throws Exception {
		List<PlannedRawMaterialsInventoryEntity> rows = null;
		try (Session session = HBUtil.getSessionFactory().openSession()) {
			String queryString = "SELECT x.material_cd, x.material_name, (x.quantity - y.quantity) quantity\r\n"
							   + "  FROM (SELECT b.material_cd, b.material_name, SUM(a.quantity) quantity\r\n"
							   + "          FROM qkc_raw_material_list a,\r\n"
							   + "               qkc_raw_material b\r\n"
							   + "         WHERE TRUNC(a.date_receive) <= TO_DATE('" + reportDate + "', 'YYYY-MM-DD')\r\n"
							   + "           AND b.material_cd = a.material_cd\r\n"
							   + "         GROUP BY b.material_cd, b.material_name\r\n"
							   + "         ORDER BY b.material_cd) x,\r\n"
							   + "       (SELECT c.material_cd, c.material_name, SUM(a.quantity * b.quantity_to_use) quantity\r\n"
							   + "          FROM qkc_daily_planned_production a,\r\n"
							   + "               qkc_production_materials b,\r\n"
							   + "               qkc_raw_material c\r\n"
							   + "         WHERE TRUNC(a.production_date) <= TO_DATE('" + reportDate + "', 'YYYY-MM-DD')\r\n"
							   + "           AND b.dpp_id = a.dpp_id\r\n"
							   + "           AND c.material_cd = b.material_cd\r\n"
							   + "         GROUP BY c.material_cd, c.material_name\r\n"
							   + "         ORDER BY c.material_cd) y\r\n"
							   + " WHERE x.material_cd = y.material_cd";
			Query<PlannedRawMaterialsInventoryEntity> query = session.createNativeQuery(queryString, PlannedRawMaterialsInventoryEntity.class);
			rows = query.getResultList();
		}
		return rows;
	}

	@Override
	public List<ProductionReportEntity> getProductionReport(String reportDate) throws Exception {
		List<ProductionReportEntity> rows = null;
		try (Session session = HBUtil.getSessionFactory().openSession()) {
			String queryString = "SELECT c.material_cd, c.material_name, SUM(a.quantity * b.quantity_to_use) quantity\r\n"
							   + "  FROM qkc_daily_planned_production a,\r\n"
							   + "       qkc_production_materials b,\r\n"
							   + "       qkc_raw_material c\r\n"
							   + " WHERE TRUNC(a.production_date) <= TO_DATE('" + reportDate + "', 'YYYY-MM-DD')\r\n"
							   + "   AND b.dpp_id = a.dpp_id\r\n"
							   + "   AND c.material_cd = b.material_cd\r\n"
							   + " GROUP BY c.material_cd, c.material_name\r\n"
							   + " ORDER BY c.material_cd";
			Query<ProductionReportEntity> query = session.createNativeQuery(queryString, ProductionReportEntity.class);
			rows = query.getResultList();
		}
		return rows;
	}

	@Override
	public List<ReceivedInventoryReportEntity> getReceivedInventoryReport(String reportDate) throws Exception {
		List<ReceivedInventoryReportEntity> rows = null;
		try (Session session = HBUtil.getSessionFactory().openSession()) {
			String queryString = "SELECT b.material_cd, b.material_name, a.quantity, a.date_receive\r\n"
							   + "  FROM qkc_raw_material_list a,\r\n"
							   + "       qkc_raw_material b\r\n"
							   + " WHERE TRUNC(a.date_receive) <= TO_DATE('" + reportDate + "', 'YYYY-MM-DD')\r\n"
							   + "   AND b.material_cd = a.material_cd\r\n"
							   + " ORDER BY b.material_cd";
			Query<ReceivedInventoryReportEntity> query = session.createNativeQuery(queryString, ReceivedInventoryReportEntity.class);
			rows = query.getResultList();
		}
		return rows;
	}

}
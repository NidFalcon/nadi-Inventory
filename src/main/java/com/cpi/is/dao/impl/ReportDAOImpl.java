package com.cpi.is.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;	
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
	public List<CurrentFinishedInventoryEntity> getCurrentFinishedInventory(String reportDate, Long branchId) throws Exception {
		List<CurrentFinishedInventoryEntity> rows = new ArrayList<>();
		try (Session session = HBUtil.getSessionFactory().openSession()) {
			session.doWork(new Work() {
				public void execute(Connection connection) throws SQLException {
					String queryString = "SELECT a.fpl_id, a.date_finished, a.quantity, a.sku_cd, a.branch_id, d.material_name \r\n"
							+ "FROM \r\n"
							+ "    qkc_finished_product_list a, \r\n"
							+ "    qkc_daily_planned_production b, \r\n"
							+ "    qkc_production_materials c, \r\n"
							+ "    qkc_raw_material d,\r\n"
							+ "    qkc_dispatch_tracking e\r\n"
							+ "WHERE b.dpp_id = a.dpp_id\r\n"
							+ " AND c.dpp_id = b.dpp_id\r\n"
							+ " AND d.material_cd = c.material_cd\r\n"
							+ " AND a.fpl_id = e.fpl_id"
							+ " AND a.branch_id = " + branchId
							+ " AND TRUNC(a.date_finished) <= TO_DATE('" + reportDate + "', 'YYYY-MM-DD')";
					try (Statement statement = connection.createStatement();
							ResultSet resultSet = statement.executeQuery(queryString)) {

						while (resultSet.next()) {
							CurrentFinishedInventoryEntity entity = mapResultSetToEntity(resultSet);
							rows.add(entity);
						}
						
						for (Iterator iterator = rows.iterator(); iterator.hasNext();) {
							CurrentFinishedInventoryEntity currentFinishedInventoryEntity = (CurrentFinishedInventoryEntity) iterator
									.next();
							System.out.println(currentFinishedInventoryEntity.toString());
						}
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
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
	
	@Override
	public List<PlannedRawMaterialsInventoryEntity> getPlannedRawMaterialsInventory(String reportDate, Long branchId) throws Exception {
		List<PlannedRawMaterialsInventoryEntity> rows = null;
		try (Session session = HBUtil.getSessionFactory().openSession()) {
			String queryString = "SELECT x.material_cd, x.material_name, (x.quantity - y.quantity) quantity\r\n"
							   + "  FROM (SELECT b.material_cd, b.material_name, SUM(a.quantity) quantity\r\n"
							   + "          FROM qkc_raw_material_list a,\r\n"
							   + "               qkc_raw_material b\r\n"
							   + "         WHERE TRUNC(a.date_receive) <= TO_DATE('" + reportDate + "', 'YYYY-MM-DD')\r\n"
							   + "           AND b.material_cd = a.material_cd\r\n"
							   + "           AND a.branch_id = " + branchId
							   + "         GROUP BY b.material_cd, b.material_name\r\n"
							   + "         ORDER BY b.material_cd) x,\r\n"
							   + "       (SELECT c.material_cd, c.material_name, a.quantity quantity\r\n"
							   + "          FROM qkc_daily_planned_production a,\r\n"
							   + "               qkc_production_materials b,\r\n"
							   + "               qkc_raw_material c\r\n"
							   + "         WHERE TRUNC(a.production_date) <= TO_DATE('" + reportDate + "', 'YYYY-MM-DD')\r\n"
							   + "           AND b.dpp_id = a.dpp_id\r\n"
							   + "           AND c.material_cd = b.material_cd\r\n"
							   + "           AND a.branch_id = " + branchId
//							   + "         GROUP BY c.material_cd, c.material_name\r\n"
							   + "         ORDER BY c.material_cd) y\r\n"
							   + " WHERE x.material_cd = y.material_cd";
			Query<PlannedRawMaterialsInventoryEntity> query = session.createNativeQuery(queryString, PlannedRawMaterialsInventoryEntity.class);
			rows = query.getResultList();
		}
		return rows;
	}

	@Override
	public List<ProductionReportEntity> getProductionReport(String reportDate, Long branchId) throws Exception {
		List<ProductionReportEntity> rows = null;
		try (Session session = HBUtil.getSessionFactory().openSession()) {
			String queryString = "SELECT c.material_cd, c.material_name, b.quantity_to_use quantity\r\n"
							   + "  FROM qkc_daily_planned_production a,\r\n"
							   + "       qkc_raw_material_list b,\r\n"
							   + "       qkc_raw_material c\r\n"
							   + " WHERE TRUNC(a.production_date) <= TO_DATE('" + reportDate + "', 'YYYY-MM-DD')\r\n"
							   + "   AND b.dpp_id = a.dpp_id\r\n"
							   + "   AND c.material_cd = b.material_cd\r\n"
							   + "           AND a.branch_id = " + branchId
//							   + " GROUP BY c.material_cd, c.material_name\r\n"
							   + " ORDER BY c.material_cd";
			Query<ProductionReportEntity> query = session.createNativeQuery(queryString, ProductionReportEntity.class);
			rows = query.getResultList();
		}
		return rows;
	}
	
	@Override
	public List<ReceivedInventoryReportEntity> getReceivedInventoryReport(String reportDate, Long branchId) throws Exception {
		List<ReceivedInventoryReportEntity> rows = null;
		try (Session session = HBUtil.getSessionFactory().openSession()) {
			String queryString = "SELECT b.material_cd, b.material_name, a.quantity, a.date_receive\r\n"
							   + "  FROM qkc_raw_material_list a,\r\n"
							   + "       qkc_raw_material b\r\n"
							   + " WHERE TRUNC(a.date_receive) <= TO_DATE('" + reportDate + "', 'YYYY-MM-DD')\r\n"
							   + "   AND b.material_cd = a.material_cd\r\n"
							   + "           AND a.branch_id = " + branchId
							   + " ORDER BY b.material_cd";
			Query<ReceivedInventoryReportEntity> query = session.createNativeQuery(queryString, ReceivedInventoryReportEntity.class);
			rows = query.getResultList();
		}
		return rows;
	}
}
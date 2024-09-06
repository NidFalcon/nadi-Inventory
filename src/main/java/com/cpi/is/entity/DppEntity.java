package com.cpi.is.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "qkc_daily_planned_production")
public class DppEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dpp_id")
	private Long dppId;

	@Column(name = "production_date")
	private Date productionDate;

	@Column(name = "quantity")
	private Integer quantity;

	@Column(name = "status")
	private String status;

	@Column(name = "branch_id")
	private Long branchId;
	
	@Column(name = "sku_cd")
	private String skuCode;
	
	@ManyToOne
	@JoinColumn(name = "branch_id", insertable=false, updatable=false)
	private BranchEntity branch;

	@ManyToOne
	@JoinColumn(name = "sku_cd", insertable=false, updatable=false)
	private SkuEntity sku;

	public DppEntity() {
		super();
	}

	public DppEntity(Long dppId, Date productionDate, Integer quantity, String status, Long branchId, String skuCode) {
		super();
		this.dppId = dppId;
		this.productionDate = productionDate;
		this.quantity = quantity;
		this.status = status;
		this.branchId = branchId;
		this.skuCode = skuCode;
	}

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public Long getDppId() {
		return dppId;
	}

	public void setDppId(Long dppId) {
		this.dppId = dppId;
	}

	public Date getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}

	public BranchEntity getBranch() {
		return branch;
	}

	public void setBranch(BranchEntity branch) {
		this.branch = branch;
	}

	public SkuEntity getSku() {
		return sku;
	}

	public void setSku(SkuEntity sku) {
		this.sku = sku;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}

package com.cpi.is.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="QKC_FINISHED_PRODUCT_LIST")
public class InventoryEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="FPL_ID")
	private Long fplId;
	@Column(name="SKU_CD")
	private Long skuCode;
	private Long quantity;
	@Column(name="BRANCH_ID")
	private String branchId;
	@Column(name="DATE_FINISHED")
	private Date dateFinished;
	
	public InventoryEntity() {
		super();
	}
	
	public InventoryEntity(Long fplId, Long skuCode, Long quantity, String branchId, Date dateFinished) {
		super();
		this.fplId = fplId;
		this.skuCode = skuCode;
		this.quantity = quantity;
		this.branchId = branchId;
		this.dateFinished = dateFinished;
	}

	

	public Long getFplId() {
		return fplId;
	}

	public void setFplId(Long fplId) {
		this.fplId = fplId;
	}

	public Long getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(Long skuCode) {
		this.skuCode = skuCode;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public Date getDateFinished() {
		return dateFinished;
	}

	public void setDateFinished(Date dateFinished) {
		this.dateFinished = dateFinished;
	}

	@Override
	public String toString() {
	    return "InventoryEntity{" +
	            "fplId=" + fplId +
	            ", skuCode=" + skuCode +
	            ", quantity=" + quantity +
	            ", branchId='" + branchId + '\'' +
	            ", dateFinished=" + dateFinished +
	            '}';
	}
	
}

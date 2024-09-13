package com.cpi.is.entity.report;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CurrentFinishedInventoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="FPL_ID")
	private Long fplId;
	@Column(name="DATE_FINISHED")
	private Date dateFinished;
	private Long quantity;
	@Column(name="SKU_CD")
	private String skuCd;
	@Column(name="BRANCH_ID")
	private Long branchId;
	@Column(name="MATERIAL_NAME")
	private String materialName;
	
	public CurrentFinishedInventoryEntity() {
		super();
	}

	public CurrentFinishedInventoryEntity(Long fplId, Date dateFinished, Long quantity, String skuCd, Long branchId,
			String materialName) {
		super();
		this.fplId = fplId;
		this.dateFinished = dateFinished;
		this.quantity = quantity;
		this.skuCd = skuCd;
		this.branchId = branchId;
		this.materialName = materialName;
	}

	public Long getFplId() {
		return fplId;
	}

	public void setFplId(Long fplId) {
		this.fplId = fplId;
	}

	public Date getDateFinished() {
		return dateFinished;
	}

	public void setDateFinished(Date dateFinished) {
		this.dateFinished = dateFinished;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public String getSkuCd() {
		return skuCd;
	}

	public void setSkuCd(String skuCd) {
		this.skuCd = skuCd;
	}

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	@Override
	public String toString() {
		return "{ fplId=" + fplId 
			 + ", dateFinished=" + dateFinished 
			 + ", quantity=" + quantity 
			 + ", skuCd=" + skuCd
			 + ", branchId=" + branchId 
			 + ", materialName=" + materialName + " }";
	}
	
}
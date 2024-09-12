package com.cpi.is.entity.report;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ReceivedInventoryReportEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="MATERIAL_CD")
	private String materialCd;
	@Column(name="MATERIAL_NAME")
	private String materialName;
	private Long quantity;
	@Column(name="DATE_RECEIVED")
	private Date dateReceived;

	public ReceivedInventoryReportEntity() {
		super();
	}

	public ReceivedInventoryReportEntity(String materialCd, String materialName, Long quantity, Date dateReceived) {
		super();
		this.materialCd = materialCd;
		this.materialName = materialName;
		this.quantity = quantity;
		this.dateReceived = dateReceived;
	}

	public String getMaterialCd() {
		return materialCd;
	}

	public void setMaterialCd(String materialCd) {
		this.materialCd = materialCd;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Date getDateReceived() {
		return dateReceived;
	}

	public void setDateReceived(Date dateReceived) {
		this.dateReceived = dateReceived;
	}

	@Override
	public String toString() {
		return "{ materialCd=" + materialCd 
			 + ", materialName=" + materialName
			 + ", quantity=" + quantity 
			 + ", dateReceived=" + dateReceived + " }";
	}

}
package com.cpi.is.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="qkc_raw_material_list")
public class RawMaterialListEntity {
	
	@Id
	@Column(name="MATERIAL_LIST_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer materialListId;
	@Column(name="MATERIAL_CD")
	private String materialCode;
	@ManyToOne
	@JoinColumn(name = "MATERIAL_CD", insertable = false, updatable = false)
	private RawMaterialEntity material;
	private Integer quantity;
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private UserEntity userId;
	@Column(name="date_receive")
	private Date dateRecieve;
	@ManyToOne
    @JoinColumn(name = "BRANCH_ID")
	private BranchEntity branch;
	
	public RawMaterialListEntity() {
		super();
	}

	public RawMaterialListEntity(Integer materialListId, String materialCode, Integer quantity,
			UserEntity userId, Date dateRecieve, BranchEntity branch) {
		super();
		this.materialListId = materialListId;
		this.materialCode = materialCode;
		this.quantity = quantity;
		this.userId = userId;
		this.dateRecieve = dateRecieve;
		this.branch = branch;
	}

	public void setMaterialListId(Integer materialListId) {
		this.materialListId = materialListId;
	}
	
	public Integer getMaterialListId() {
		return materialListId;
	}

	public RawMaterialEntity getMaterial() {
		return material;
	}

	public void setMaterial(RawMaterialEntity material) {
		this.material = material;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public UserEntity getUserId() {
		return userId;
	}

	public void setUserId(UserEntity userId) {
		this.userId = userId;
	}

	public Date getDateRecieve() {
		return dateRecieve;
	}

	public void setDateRecieve(Date dateRecieve) {
		this.dateRecieve = dateRecieve;
	}

	public BranchEntity getBranch() {
		return branch;
	}

	public void setBranch(BranchEntity branch) {
		this.branch = branch;
	}
	
	
	
}
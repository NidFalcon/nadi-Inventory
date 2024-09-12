package com.cpi.is.entity.inventory;

import java.sql.Date;

import com.cpi.is.entity.UserEntity;
import com.cpi.is.entity.maintenance.BranchEntity;
import com.cpi.is.entity.maintenance.RawMaterialEntity;

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
	@Column(name="USER_ID")
	private Integer userId;
	@ManyToOne
	@JoinColumn(name="USER_ID", insertable = false, updatable = false)
	private UserEntity user;
	@Column(name="date_receive")
	private Date dateReceive;
	@Column(name="BRANCH_ID")
	private Integer branchId;
	@ManyToOne
    @JoinColumn(name = "BRANCH_ID", insertable = false, updatable = false)
	private BranchEntity branch;
	
	public RawMaterialListEntity() {
		super();
	}
	public RawMaterialListEntity(Integer materialListId, String materialCode, Integer quantity, Integer userId,
			Date dateReceive, Integer branchId) {
		super();
		this.materialListId = materialListId;
		this.materialCode = materialCode;
		this.quantity = quantity;
		this.userId = userId;
		this.dateReceive = dateReceive;
		this.branchId = branchId;
	}

	public Integer getMaterialListId() {
		return materialListId;
	}

	public void setMaterialListId(Integer materialListId) {
		this.materialListId = materialListId;
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public Date getdateReceive() {
		return dateReceive;
	}

	public void setdateReceive(Date dateReceive) {
		this.dateReceive = dateReceive;
	}

	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	public BranchEntity getBranch() {
		return branch;
	}

	public void setBranch(BranchEntity branch) {
		this.branch = branch;
	}
}

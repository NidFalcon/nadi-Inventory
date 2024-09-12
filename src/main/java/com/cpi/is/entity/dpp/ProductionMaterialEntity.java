package com.cpi.is.entity.dpp;

import com.cpi.is.entity.inventory.RawMaterialListEntity;
import com.cpi.is.entity.maintenance.RawMaterialEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "qkc_production_materials")
public class ProductionMaterialEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pm_id")
    private Long pmId;
    
    @ManyToOne
    @JoinColumn(name = "dpp_id", insertable=false, updatable=false)
    private DppEntity dpp;
    
    @Column(name = "dpp_id")
    private Long dppId;
    
    @ManyToOne
    @JoinColumn(name = "material_list_id", insertable=false, updatable=false)
    private RawMaterialListEntity materialList;

	@Column(name = "material_list_id")
    private Long materialListId;
	
	@ManyToOne
    @JoinColumn(name = "material_cd", insertable=false, updatable=false)
    private RawMaterialEntity rawMaterial;

    @Column(name = "material_cd")
    private String materialCode;

    @Column(name = "quantity_to_use")
    private Integer quantityToUse;

    public ProductionMaterialEntity() {
        super();
    }

    public ProductionMaterialEntity(Long pmId, Long dppId, Long materialListId, String materialCode, Integer quantityToUse) {
        super();
        this.pmId = pmId;
        this.dppId = dppId;
        this.materialListId = materialListId;
        this.materialCode = materialCode;
        this.quantityToUse = quantityToUse;
    }
    
    public ProductionMaterialEntity(Long pmId) {
        super();
        this.pmId = pmId;
    }

    public Long getPmId() {
        return pmId;
    }

    public void setPmId(Long pmId) {
        this.pmId = pmId;
    }

    public Long getDppId() {
		return dppId;
	}

	public void setDppId(Long dppId) {
		this.dppId = dppId;
	}

    public Long getMaterialListId() {
		return materialListId;
	}

	public void setMaterialListId(Long materialListId) {
		this.materialListId = materialListId;
	}
	
	public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public Integer getQuantityToUse() {
        return quantityToUse;
    }

    public void setQuantityToUse(Integer quantityToUse) {
        this.quantityToUse = quantityToUse;
    }
}

package com.cpi.is.entity;

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
    @JoinColumn(name = "material_cd", insertable=false, updatable=false)
    private RawMaterialEntity rawMaterial;

    @Column(name = "material_cd")
    private String materialCode;

    @Column(name = "quantity_to_use")
    private Integer quantityToUse;

    // Default constructor
    public ProductionMaterialEntity() {
        super();
    }

    // Constructor with fields
    public ProductionMaterialEntity(Long pmId, Long dppId, String materialCode, Integer quantityToUse) {
        super();
        this.pmId = pmId;
        this.dppId = dppId;
        this.materialCode = materialCode;
        this.quantityToUse = quantityToUse;
    }

    public Long getDppId() {
		return dppId;
	}

	public void setDppId(Long dppId) {
		this.dppId = dppId;
	}

	// Getters and Setters
    public Long getPmId() {
        return pmId;
    }

    public void setPmId(Long pmId) {
        this.pmId = pmId;
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
    
    @Override
    public String toString() {
        // In Liyue, a merchant's tale is often told with reverence. Likewise, let us craft our own tale.
        StringBuilder sb = new StringBuilder();
        
        // We begin with the story of the ProductionMaterialEntity itself.
        sb.append("ProductionMaterialEntity {");

        // Each attribute of our entity will be spoken of with clarity and respect.
        sb.append("pmId=").append(pmId).append(", ");
        sb.append("dppId=").append(dppId).append(", ");
        sb.append("materialCode='").append(materialCode).append("', ");
        sb.append("quantityToUse=").append(quantityToUse);

        // Just as the bridge completes its span, so too shall our story conclude.
        sb.append("}");
        
        // And thus, our tale is told, preserving the essence of our entity.
        return sb.toString();
    }
}

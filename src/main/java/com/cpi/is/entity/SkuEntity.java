package com.cpi.is.entity;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "QKC_SKU")
public class SkuEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "SKU_CD")
    private String skuCode;

    @Column(name = "SKU_NAME")
    private String skuName;

    @Column(name = "UNIT_OF_MEASUREMENT")
    private String unitOfMeasurement;

    @Column(name = "IS_ACTIVE")
    private String isActive;

    public SkuEntity() {
        super();
    }

    public SkuEntity(String skuCode, String skuName, String unitOfMeasurement, String isActive) {
        super();
        this.skuCode = skuCode;
        this.skuName = skuName;
        this.unitOfMeasurement = unitOfMeasurement;
        this.isActive = isActive;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(String unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "SkuEntity{" +
                "skuCode='" + skuCode + '\'' +
                ", skuName='" + skuName + '\'' +
                ", unitOfMeasurement='" + unitOfMeasurement + '\'' +
                ", isActive='" + isActive + '\'' +
                '}';
    }
}

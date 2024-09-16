package com.cpi.is.entity.report;

import java.io.Serializable;

public class ReportsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String skuCd;
    private Integer currentInventory;
    private String skuName;

    // Constructor for the query result
    public ReportsEntity(String skuCd, Integer currentInventory, String skuName) {
        this.skuCd = skuCd;
        this.currentInventory = currentInventory;
        this.skuName = skuName;
    }

    // Getters and setters
    public String getSkuCd() {
        return skuCd;
    }

    public void setSkuCd(String skuCd) {
        this.skuCd = skuCd;
    }

    public Integer getCurrentInventory() {
        return currentInventory;
    }

    public void setCurrentInventory(Integer currentInventory) {
        this.currentInventory = currentInventory;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    @Override
    public String toString() {
        return "ReportsEntity{" +
                "skuCd='" + skuCd + '\'' +
                ", currentInventory=" + currentInventory +
                ", skuName='" + skuName + '\'' +
                '}';
    }
}

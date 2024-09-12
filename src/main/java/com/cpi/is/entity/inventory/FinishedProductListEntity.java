package com.cpi.is.entity.inventory;

import java.io.Serializable;

import java.util.Date; // Import Date

import com.cpi.is.entity.maintenance.SkuEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "QKC_FINISHED_PRODUCT_LIST")
public class FinishedProductListEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "FPL_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fplId;

    @Column(name = "SKU_CD")
    private String skuCD;

    @Column(name = "QUANTITY")
    private Integer quantity;
    
    @Column(name = "BRANCH_ID")
    private Long branchId;
    
    @Column(name = "DATE_FINISHED")
    private Date dateFinished;
    
    @ManyToOne
	@JoinColumn(name="SKU_CD", insertable=false, updatable=false)
	private SkuEntity sku;


	public FinishedProductListEntity() {
        super();
    }

    public FinishedProductListEntity(Long fplId, String skuCD, Integer quantity, Long branchId, Date dateFinished) {
        super();
        this.fplId = fplId;
        this.skuCD = skuCD;
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

    public String getSkuCD() {
        return skuCD;
    }

    public void setSkuCD(String skuCD) {
        this.skuCD = skuCD;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Date getDateFinished() {
        return dateFinished;
    }

    public void setDateFinished(Date dateFinished) {
        this.dateFinished = dateFinished;
    }

    public SkuEntity getSku() {
		return sku;
	}

	public void setSku(SkuEntity sku) {
		this.sku = sku;
	}
    
    @Override
    public String toString() {
        return "FinishedProductListEntity{" +
                "fplId=" + fplId +
                ", skuCD='" + skuCD + '\'' +
                ", quantity=" + quantity +
                ", branchId=" + branchId +
                ", dateFinished=" + dateFinished +
                '}';
    }
}

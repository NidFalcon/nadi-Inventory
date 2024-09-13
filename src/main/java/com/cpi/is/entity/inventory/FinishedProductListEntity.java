package com.cpi.is.entity.inventory;

import java.io.Serializable;

import java.util.Date; // Import Date

import com.cpi.is.entity.UserEntity;
import com.cpi.is.entity.maintenance.BranchEntity;
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
    
	@Column(name="USER_ID")
	private Integer userId;

    @Column(name = "SKU_CD")
    private String skuCD;

    @Column(name = "QUANTITY")
    private Integer quantity;
    
	@Column(name="BRANCH_ID")
	private Integer branchId;
    
    @Column(name = "DATE_FINISHED")
    private Date dateFinished;
    
	@ManyToOne
	@JoinColumn(name="USER_ID", insertable = false, updatable = false)
	private UserEntity user;
    @ManyToOne
	@JoinColumn(name="SKU_CD", insertable=false, updatable=false)
	private SkuEntity sku;
    
	@ManyToOne
    @JoinColumn(name = "BRANCH_ID", insertable = false, updatable = false)
	private BranchEntity branch;


	public FinishedProductListEntity() {
        super();
    }

    public FinishedProductListEntity(Long fplId, String skuCD, Integer userId, Integer quantity, Integer branchId, 
    		Date dateFinished) {
    	
        this.fplId = fplId;
        this.skuCD = skuCD;
		this.userId = userId;
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	public Date getDateFinished() {
		return dateFinished;
	}

	public void setDateFinished(Date dateFinished) {
		this.dateFinished = dateFinished;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public SkuEntity getSku() {
		return sku;
	}

	public void setSku(SkuEntity sku) {
		this.sku = sku;
	}

	public BranchEntity getBranch() {
		return branch;
	}

	public void setBranch(BranchEntity branch) {
		this.branch = branch;
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

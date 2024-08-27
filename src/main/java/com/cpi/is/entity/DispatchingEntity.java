package com.cpi.is.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name="qkc_dispatch_tracking")
public class DispatchingEntity implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="DISPATCH_TRACK_ID")
    private Long dispatchTrackId;

    @Column(name="DISPATCH_TYPE_CD")
    private String dispatchTypeCd;

    @Column(name="FPL_ID")
    private Long fplId;

    @Column(name="QUANTITY", nullable = false)
    private Integer quantity;

    @Column(name="BRANCH_ID")
    private Integer branchId;

    @Column(name="DESTINATION")
    private String destination;

    @Column(name="DISPATCH_DATE", nullable = false)
    private Date dispatchDate;

    public DispatchingEntity() {
        super();
    }

    public DispatchingEntity(Long dispatchTrackId, String dispatchTypeCd, Long fplId, Integer quantity, Integer branchId, String destination, Date dispatchDate) {
        super();
        this.dispatchTrackId = dispatchTrackId;
        this.dispatchTypeCd = dispatchTypeCd;
        this.fplId = fplId;
        this.quantity = quantity;
        this.branchId = branchId;
        this.destination = destination;
        this.dispatchDate = dispatchDate;
    }

    // Getters and setters

    public Long getDispatchTrackId() {
        return dispatchTrackId;
    }

    public void setDispatchTrackId(Long dispatchTrackId) {
        this.dispatchTrackId = dispatchTrackId;
    }

    public String getDispatchTypeCd() {
        return dispatchTypeCd;
    }

    public void setDispatchTypeCd(String dispatchTypeCd) {
        this.dispatchTypeCd = dispatchTypeCd;
    }

    public Long getFplId() {
        return fplId;
    }

    public void setFplId(Long fplId) {
        this.fplId = fplId;
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

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDispatchDate() {
        return dispatchDate;
    }

    public void setDispatchDate(Date dispatchDate) {
        this.dispatchDate = dispatchDate;
    }

    @Override
    public String toString() {
        return "DispatchingEntity{" +
                "dispatchTrackId=" + dispatchTrackId +
                ", dispatchTypeCd='" + dispatchTypeCd + '\'' +
                ", fplId='" + fplId + '\'' +
                ", quantity=" + quantity +
                ", branchId=" + branchId +
                ", destination='" + destination + '\'' +
                ", dispatchDate=" + dispatchDate +
                '}';
    }
}

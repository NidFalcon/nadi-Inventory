package com.cpi.is.entity;
import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="QKC_BRANCH")
public class BranchEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="BRANCH_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long branchId;

    @Column(name="BRANCH_NAME")
    private String branchName;

    @Column(name="IS_ACTIVE")
    private String isActive;

    public BranchEntity() {
        super();
    }

    public BranchEntity(Long branchId, String branchName, String isActive) {
        super();
        this.branchId = branchId;
        this.branchName = branchName;
        this.isActive = isActive;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "BranchEntity{" +
                "branchId=" + branchId +
                ", branchName='" + branchName + '\'' +
                ", isActive='" + isActive + '\'' +
                '}';
    }
}

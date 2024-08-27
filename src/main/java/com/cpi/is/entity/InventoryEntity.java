package com.cpi.is.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="IS_INVENTORY")
public class InventoryEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="INVENTORY_ID")
	private Long inventoryId;
	private String description;
	private Long quantity;
	@Column(name="ACTIVE_TAG")
	private String activeTag;
	
	public InventoryEntity() {
		super();
	}

	public InventoryEntity(Long inventoryId, String description, Long quantity, String activeTag) {
		super();
		this.inventoryId = inventoryId;
		this.description = description;
		this.quantity = quantity;
		this.activeTag = activeTag;
	}

	public Long getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(Long inventoryId) {
		this.inventoryId = inventoryId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public String getActiveTag() {
		return activeTag;
	}

	public void setActiveTag(String activeTag) {
		this.activeTag = activeTag;
	}

	@Override
	public String toString() {
		return "{inventoryId=" + inventoryId 
			 + ", description=" + description 
			 + ", quantity=" + quantity
			 + ", activeTag=" + activeTag + "}";
	}
	
}

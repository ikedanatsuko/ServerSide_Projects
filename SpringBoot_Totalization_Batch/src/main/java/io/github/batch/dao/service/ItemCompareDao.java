package io.github.batch.dao.service;

import java.util.List;

import io.github.batch.entity.ItemOutput;

public interface ItemCompareDao {
	
	// Compare item and previous item
	public List<ItemOutput> getCreatedItems();
	
	public List<ItemOutput> getDeletedItems();
	
	// Copy item to pre_item
	public void setPreItems();
}

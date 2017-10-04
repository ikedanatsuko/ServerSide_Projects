package io.github.batch.dao.table;

import java.util.List;

import io.github.batch.entity.ItemOutput;

public interface ItemOutputDao {
	
	// Get
	public List<ItemOutput> getAllItem();
	
	public List<ItemOutput> getItemsByDateId(int dateId);
	
	public ItemOutput getItemById(int id);
	
	public String getTitleById(int id);
	
	public String getNoteById(int id);
	
	public int getPriceById(int id);
	
	public byte[] getImageById(int id);
	
	// Create
	public void createItem(ItemOutput item);
	
	// Delete
	public void deleteAllItem();
	
	public void deleteItem(int id);
}

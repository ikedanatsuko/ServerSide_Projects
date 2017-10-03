package io.github.batch.dao.table;

import java.util.List;

import io.github.batch.entity.ItemOutput;

public interface ItemOutputDao {
	
	// GET
	public List<ItemOutput> getAllItem();
	
	public List<ItemOutput> getItemsByDateId(int dateId);
	
	public ItemOutput getItemById(int id);
	
	public String getTitleById(int id);
	
	public String getNoteById(int id);
	
	public int getPriceById(int id);
	
	public byte[] getImageById(int id);
	
	// POST
	public void createItem(ItemOutput item);
	
	// DELETE
	public void deleteAllItem();
	
	public void deleteItem(int id);
}

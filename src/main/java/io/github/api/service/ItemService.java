package io.github.api.service;

import java.util.List;

import io.github.api.entity.Item;

public interface ItemService {

	// GET
	public List<Item> getAllItem();
	
	public Item getItemById(int id);

	public String getTitleById(int id);

	public String getNoteById(int id);

	public int getPriceById(int id);

	public byte[] getImageById(int id);

	public List<Item> getItemsByTitle(String title);

	public List<Item> getItemsByPrice(int price);
	
	// POST
	public void createItem(Item item);
	
	// PUT
	public Item updateItem(Item item);

	// DELETE
	public void deleteAllItem();
	
	public void deleteItem(int id);
}

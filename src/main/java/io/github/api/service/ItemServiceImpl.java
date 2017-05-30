package io.github.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.api.dao.ItemDao;
import io.github.api.entity.Item;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemDao itemDao;
	
//	----------------------------------------GET----------------------------------------
	
	public List<Item> getAllItem(){
		return itemDao.getAllItem();
	};
	
	public Item getItemById(int id){
		return itemDao.getItemById(id);
	};

	public String getTitleById(int id){
		return itemDao.getTitleById(id);
	};

	public String getNoteById(int id){
		return itemDao.getNoteById(id);
	};
	
	public int getPriceById(int id){
		return itemDao.getPriceById(id);
	};

	public byte[] getImageById(int id){
		return itemDao.getImageById(id);
	};

	public List<Item> getItemsByTitle(String title){
		return itemDao.getItemsByTitle(title);
	};

	public List<Item> getItemsByPrice(int price){
		return itemDao.getItemsByPrice(price);
	};
	
//	----------------------------------------POST----------------------------------------
	public void createItem(Item item){
		itemDao.createItem(item);
	};
	
//	----------------------------------------PUT----------------------------------------
	public Item updateItem(Item item){
		itemDao.updateItem(item);
		Item currentItem = itemDao.getItemById(item.getId());
		return currentItem;
	};
	
//	----------------------------------------DELETE----------------------------------------
	public void deleteAllItem(){
		itemDao.deleteAllItem();
	};

	public void deleteItem(int id){
		itemDao.deleteItem(id);
	};
}

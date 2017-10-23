package io.github.oauth.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.oauth.dao.table.CreatedItemDaoImpl;
import io.github.oauth.dao.table.DateTotalDao;
import io.github.oauth.dao.table.DeletedItemDaoImpl;
import io.github.oauth.entity.DateTotal;
import io.github.oauth.entity.ItemOutput;

@Service
public class ItemOutputService {
	
	@Autowired
	private DateTotalDao dateTotalDao;
	
	@Autowired
	private CreatedItemDaoImpl createdItemDao;
	@Autowired
	private DeletedItemDaoImpl deletedItemDao;
	
	public List<DateTotal> getAllDateTotal() {
		return dateTotalDao.getAllDateTotal();
	}
	
	public List<DateTotal> getDateTotalsByTerm(Date begin, Date end) {
		return dateTotalDao.getDateTotalsByTerm(begin, end);
	}
	
	public List<List<ItemOutput>> getAllCreatedItem() {
		return getCreatedItems(getAllDateTotal());
	}
	
	public List<List<ItemOutput>> getAllDeletedItem() {	
		return getDeletedItems(getAllDateTotal());
	}
	
	public List<List<ItemOutput>> getCreatedItems(List<DateTotal> dateTotals) {
		List<List<ItemOutput>> createdItems = new ArrayList<>();
		
		for (DateTotal dateTotal : dateTotals) {
			int dateId = dateTotal.getId();
			List<ItemOutput> tmpCreatedItems = createdItemDao.getItemsByDateId(dateId);
			createdItems.add(tmpCreatedItems);
		}
		
		return createdItems;
	}
	
	public List<List<ItemOutput>> getDeletedItems(List<DateTotal> dateTotals) {
		List<List<ItemOutput>> deletedItems = new ArrayList<>();
		
		for (DateTotal dateTotal : dateTotals) {
			int dateId = dateTotal.getId();
			List<ItemOutput> tmpDeletedItems = deletedItemDao.getItemsByDateId(dateId);
			deletedItems.add(tmpDeletedItems);
		}
		
		return deletedItems;
	}
}

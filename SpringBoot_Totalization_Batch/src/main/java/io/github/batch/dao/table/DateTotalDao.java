package io.github.batch.dao.table;

import java.sql.Date;
import java.util.List;

import io.github.batch.entity.DateTotal;

public interface DateTotalDao {
	
	// Get
	public List<DateTotal> getAllDateTotal();
	
	public List<DateTotal> getDateTotalsByTerm(Date begin, Date end);
	
	public DateTotal getDateTotalById(int id);
	
	public DateTotal getDateTotalByDate(Date date);
	
	public Date getDateById(int id);
	
	public int getCreatedTotalById(int id);
	
	public int getDeletedTotalById(int id);
	
	// Create
	public int createDateTotal(Date date);
	
	// Update
	public void setCreatedTotal(int id, int total);
	
	public void setDeletedTotal(int id, int total);
	
	// Delete
	public void deleteAllDateTotal();
	
	public void deleteDateTotal(int id);
}

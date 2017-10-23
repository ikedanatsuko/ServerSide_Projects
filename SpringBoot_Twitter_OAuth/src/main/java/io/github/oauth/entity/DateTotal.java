package io.github.oauth.entity;

import java.sql.Date;

import javax.validation.constraints.NotNull;

public class DateTotal {
	
	private int id;
	
	@NotNull
	private Date date;
	
	private int createdTotal;
	
	private int deletedTotal;
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setCreatedTotal(int createdTotal) {
		this.createdTotal = createdTotal;
	}
	
	public int getCreatedTotal() {
		return createdTotal;
	}
	
	public void setDeletedTotal(int deletedTotal) {
		this.deletedTotal = deletedTotal;
	}
	
	public int getDeletedTotal() {
		return deletedTotal;
	}
}

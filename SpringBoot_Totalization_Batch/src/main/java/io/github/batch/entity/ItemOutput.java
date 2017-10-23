package io.github.batch.entity;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

// Item entity + dateId

public class ItemOutput {
	
	private int id;
	
	private int dateId;
	
	@NotBlank
	@Size(min = 1, max = 100)
	private String title;
	
	@Size(min = 1, max = 500)
	private String note;
	
	private int price;
	
	private byte[] image;
	
	public ItemOutput() {
	}
	
	public ItemOutput(String title, String note, int price, byte[] image) {
		this.title = title;
		this.note = note;
		this.price = price;
		this.image = image;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setDateId(int dateId) {
		this.dateId = dateId;
	}
	
	public int getDateId() {
		return dateId;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setNote(String note) {
		this.note = note;
	}
	
	public String getNote() {
		return note;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setImage(byte[] image) {
		this.image = image;
	}
	
	public byte[] getImage() {
		return image;
	}
}

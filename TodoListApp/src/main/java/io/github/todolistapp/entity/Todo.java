package io.github.todolistapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Configurable;

@Entity
@Configurable
public class Todo {
	
	@Id
	@Column(name = "id")
	private int id;
	
	@Column(name = "list_id")
	private int listId; 
	
	@NotBlank
	@Column(name = "detail")
	private String detail;
	
	@Column(name = "done")
	private boolean done;
	
	public Todo() {
		done = false;
	}
	
	public Todo(int id,int listId, String detail, boolean done) {
		this.id = id;
		this.listId = listId;
		this.detail = detail;
		this.done = done;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public int getListId() {
		return listId;
	}
	
	public void setListId(int listId) {
		this.listId = listId;
	}
	
	public String getDetail() {
		return detail;
	}
	
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	public Boolean getDone() {
		return this.done;
	}
	
	public void setDone(boolean done) {
		this.done = done;
	}
}

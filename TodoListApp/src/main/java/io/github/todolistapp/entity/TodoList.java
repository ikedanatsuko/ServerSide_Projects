package io.github.todolistapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Configurable;

@Entity
@Configurable
public class TodoList {
	
	@Id
	@Column(name = "id")
	private int id;
	
	@NotBlank
	@Column(name = "title")
	private String title;
	
	public TodoList() {
	}
	
	public TodoList(String title) {
		this.title = title;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
}

package io.github.todolistapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.PropertySource;

@Entity
@Configurable
public class Encomium {
	
	@Id
	@Column(name = "id")
	private int id;

	@NotBlank
	@Column(name = "message")
	private String message;
	
	public Encomium() {
	}
	
	public Encomium(int id, String message) {
		this.id = id;
		this.message = message;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}

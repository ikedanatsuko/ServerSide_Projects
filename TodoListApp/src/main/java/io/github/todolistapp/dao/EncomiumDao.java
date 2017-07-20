package io.github.todolistapp.dao;

import java.util.List;

import io.github.todolistapp.entity.Encomium;

public interface EncomiumDao {
	
	public Encomium getEncomiumById(int id);
	
	public List<Encomium> getAllEncomium();
	
	public String getRandomMessage();
	
	public void addEncomium(Encomium encomium);
	
	public void updateEncomium(Encomium encomium);
	
	public void removeEncomium(Encomium encomium);
}

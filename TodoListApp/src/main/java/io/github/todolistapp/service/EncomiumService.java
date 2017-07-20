package io.github.todolistapp.service;

import java.util.List;

import io.github.todolistapp.entity.Encomium;

public interface EncomiumService {

	public Encomium getEncomiumById(int id);

	public List<Encomium> getAllEncomium();
	
	public String getRandomMessage();

	public void addEncomium(Encomium encomium);
}

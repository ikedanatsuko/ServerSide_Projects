package io.github.todolistapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.todolistapp.dao.EncomiumDao;
import io.github.todolistapp.entity.Encomium;

@Service
public class EncomiumServiceImpl implements EncomiumService {
	
	@Autowired
	private EncomiumDao encomiumDao;

	public Encomium getEncomiumById(int id) {
		return encomiumDao.getEncomiumById(id);
	}

	public List<Encomium> getAllEncomium() {
		return encomiumDao.getAllEncomium();
	}
	
	public String getRandomMessage() {
		return encomiumDao.getRandomMessage();
	}
	
	@Transactional
	public void addEncomium(Encomium encomium) {
		encomiumDao.addEncomium(encomium);
	}
}

package io.github.todolistapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.todolistapp.dao.TodoDao;
import io.github.todolistapp.entity.Todo;

@Service
public class TodoServiceImpl implements TodoService {
	
	@Autowired
	private TodoDao todoDao;

	public Todo getTodoById(int id) {
		return todoDao.getTodoById(id);
	}

	public List<Todo> getTodosByList(int listId) {
		return todoDao.getTodosByList(listId);
	}
	
	public List<Todo> getTodosByWord(String search_word) {
		return todoDao.getTodosByWord(search_word);
	}
	
	public int getUndoCount(int listId) {
		return todoDao.getUndoCount(listId);
	}
	
	@Transactional
	public void bindList(Todo todo, int listId) {
		todoDao.bindList(todo, listId);
	}
	
	@Transactional
	public void finish(Todo todo) {
		todoDao.finish(todo);
	}
	
	@Transactional
	public void addTodo(Todo todo) {
		todoDao.addTodo(todo);
	}
}

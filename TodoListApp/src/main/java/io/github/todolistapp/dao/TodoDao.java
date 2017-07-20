package io.github.todolistapp.dao;

import java.util.List;

import io.github.todolistapp.entity.Todo;

public interface TodoDao {
	
	public Todo getTodoById(int id);
	
	public List<Todo> getTodosByList(int listId);
	
	public List<Todo> getTodosByWord(String searchWord);
	
	public int getUndoCount(int listId);
	
	public void bindList(Todo todo, int listId);
	
	public void finish(Todo todo);
	
	public void addTodo(Todo todo);
	
	public void updateTodo(Todo todo);
	
	public void removeTodo(Todo todo);
}

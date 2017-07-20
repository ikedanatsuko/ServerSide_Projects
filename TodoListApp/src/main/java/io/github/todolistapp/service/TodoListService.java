package io.github.todolistapp.service;

import java.util.List;

import io.github.todolistapp.entity.TodoList;

public interface TodoListService {

	// Get todolist by id	
	public TodoList getTodolistById(int id);

	// Get all todolist
	public List<TodoList> getAllTodolist();

	public void addTodolist(TodoList todolist);
}

package io.github.todolistapp.dao;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.exception.DataException;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import io.github.todolistapp.entity.TodoList;

@Repository
public class TodoListDaoImpl implements TodoListDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private MessageSource messageSource;

	public TodoList getTodolistById(int id) throws DataException {
		String sql = "SELECT * FROM todolist WHERE id= ?";
		try {
			Map map = jdbcTemplate.queryForMap(sql, id);
			TodoList todolist = new TodoList();
			todolist.setId((Integer) map.get("id"));
			todolist.setTitle((String) map.get("title"));

			return todolist;
		} catch (EmptyResultDataAccessException e) {
			throw new ServiceException(messageSource.getMessage("todoList.notFound", null, null));
		}
	}

	public List<TodoList> getAllTodolist() {
		String sql = "SELECT * FROM todolist";
		List<TodoList> todolists = jdbcTemplate.query(sql, new RowMapper<TodoList>() {
			public TodoList mapRow(ResultSet rs, int rowNum) throws SQLException {
				TodoList todolist = new TodoList();
				todolist.setId(rs.getInt("id"));
				todolist.setTitle(rs.getString("title"));

				return todolist;
			}
		});
		return todolists;
	}

	public void addTodolist(TodoList todolist) {
		String sql = "INSERT INTO todolist (title) VALUES(?)";
		jdbcTemplate.update(sql, todolist.getTitle());

		String sqlGetid = "SELECT setval('todolist_id_seq', (SELECT MAX(id) FROM todolist))";
		todolist.setId(jdbcTemplate.queryForObject(sqlGetid, Integer.class));
	}

	public void updateTodolist(TodoList todoList) {
		try {
			Map map = jdbcTemplate.queryForMap("SELECT * FROM todolist WHERE id= ?", todoList.getId());
		} catch (EmptyResultDataAccessException e) {
			throw new ServiceException(messageSource.getMessage("todoList.notFound", null, null));
		}
		String sql = "UPDATE todolist SET title = ? WHERE id = ?";
		jdbcTemplate.update(sql, todoList.getId());
	}

	public void removeTodolist(TodoList todoList) {
		try {
			Map map = jdbcTemplate.queryForMap("SELECT * FROM todolist WHERE id= ?", todoList.getId());
		} catch (EmptyResultDataAccessException e) {
			throw new ServiceException(messageSource.getMessage("todoList.notFound", null, null));
		}
		String sql = "DELETE FROM todolist WHERE id = ?";
		jdbcTemplate.update(sql, todoList.getId());
	}
}

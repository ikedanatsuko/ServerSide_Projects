package io.github.todolistapp.dao;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.StatementCreatorUtils;

import io.github.todolistapp.entity.Todo;

@Repository
public class TodoDaoImpl implements TodoDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private MessageSource messageSource;

	public Todo getTodoById(int id) {
		String sql = "SELECT * FROM todo WHERE id= ?";
		try {
			Map map = jdbcTemplate.queryForMap(sql, id);
			Todo todo = new Todo();

			todo.setId((Integer) map.get("id"));
			todo.setListId((Integer) map.get("list_id"));
			todo.setDetail((String) map.get("detail"));
			todo.setDone((Boolean) map.get("done"));

			return todo;
		} catch (EmptyResultDataAccessException e) {
			throw new ServiceException(messageSource.getMessage("todo.notFound", null, null));
		}
	}

	public List<Todo> getTodosByList(int listId) {
		String sql = "SELECT * FROM todo WHERE list_id= ?";
		List<Todo> todos = jdbcTemplate.query(sql, new Object[] { listId }, new RowMapper<Todo>() {
			public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
				Todo todo = new Todo();
				todo.setId(rs.getInt("id"));
				todo.setListId(rs.getInt("list_id"));
				todo.setDetail(rs.getString("detail"));
				todo.setDone(rs.getBoolean("done"));
				return todo;
			}
		});
		return todos;

	}

	public List<Todo> getTodosByWord(String searchWord) {
		String sql = "SELECT * FROM todo WHERE detail LIKE ?";
		searchWord = "%" + searchWord + "%";

		try {
			List<Todo> todos = jdbcTemplate.query(sql, new Object[] { searchWord }, new RowMapper<Todo>() {
				public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
					Todo todo = new Todo();
					todo.setId(rs.getInt("id"));
					todo.setListId(rs.getInt("list_id"));
					todo.setDetail(rs.getString("detail"));
					todo.setDone(rs.getBoolean("done"));
					return todo;
				}
			});
			return todos;

		} catch (EmptyResultDataAccessException e) {

			return null;
		}
	}

	public int getUndoCount(int listId) {
		String sql = "SELECT count(*) FROM todo WHERE done = false AND list_id = ?";
		int count = jdbcTemplate.queryForObject(sql, Integer.class, listId);

		return count;
	}

	public void bindList(Todo todo, int listId) {
		String sql = "UPDATE todo SET list_id = ? WHERE id = ?";
		jdbcTemplate.update(sql, listId, todo.getId());
	}

	public void finish(Todo todo) {
		String sql = "UPDATE todo SET done = true WHERE id = ?";
		jdbcTemplate.update(sql, todo.getId());
	}

	public void addTodo(Todo todo) {
		String sql = "INSERT INTO todo (list_id, detail, done) VALUES(?, ?, ?)";
		jdbcTemplate.update(sql, todo.getListId(), todo.getDetail(), todo.getDone());

		String sqlGetid = "SELECT setval('todo_id_seq', (SELECT MAX(id) FROM todo))";
		todo.setId(jdbcTemplate.queryForObject(sqlGetid, Integer.class));
	}

	public void updateTodo(Todo todo) {
		try {
			Map map = jdbcTemplate.queryForMap("SELECT * FROM todo WHERE id= ?", todo.getId());
		} catch (Exception e) {
			throw new ServiceException(messageSource.getMessage("todo.notFound", null, null));
		}
		String sql = "UPDATE todo SET list_id = ?, detail = ?, done = ? WHERE id = ?";
		jdbcTemplate.update(sql, todo.getListId(), todo.getDetail(), todo.getDone(), todo.getId());
	}

	public void removeTodo(Todo todo) {
		try {
			Map map = jdbcTemplate.queryForMap("SELECT * FROM todo WHERE id= ?", todo.getId());
		} catch (Exception e) {
			throw new ServiceException(messageSource.getMessage("todo.notFound", null, null));
		}
		String sql = "DELETE FROM todo WHERE id = ?";
		jdbcTemplate.update(sql, todo.getId());
	}
}

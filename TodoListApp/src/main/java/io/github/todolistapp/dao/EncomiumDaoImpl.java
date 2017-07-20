package io.github.todolistapp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import io.github.todolistapp.entity.Encomium;
import io.github.todolistapp.entity.Todo;
import io.github.todolistapp.entity.TodoList;

@Repository
public class EncomiumDaoImpl implements EncomiumDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private MessageSource messageSource;
	
	public Encomium getEncomiumById(int id) {
		String sql = "SELECT * FROM encomium WHERE id= ?";
		try {
			Map map = jdbcTemplate.queryForMap(sql, id);
			Encomium encomium = new Encomium();
			
			encomium.setId((Integer)map.get("id"));
			encomium.setMessage((String)map.get("message"));
			
			return encomium;
		} catch (EmptyResultDataAccessException e) {
			throw new ServiceException(messageSource.getMessage("encomium.notFound", null, null));
		}
	}

	public List<Encomium> getAllEncomium() {
		String sql = "SELECT * FROM encomium";
		List<Encomium> encomiums = jdbcTemplate.query(sql,
				new RowMapper<Encomium>() {
			public Encomium mapRow(ResultSet rs, int rowNum) throws SQLException {
				Encomium encomium = new Encomium();
				encomium.setId(rs.getInt("id"));
				encomium.setMessage(rs.getString("message"));
				return encomium;
			}
		});
		return encomiums;
	}
	
	public String getRandomMessage() {
		String sql = "SELECT message FROM encomium ORDER by random() LIMIT 1";
		String result = jdbcTemplate.queryForObject(sql, String.class);
		
		return result;
	}

	public void addEncomium(Encomium encomium) {
		String sql = "INSERT INTO encomium (message) VALUES(?)";
		jdbcTemplate.update(sql, encomium.getMessage());
		
		String sqlGetid = "SELECT setval('encomium_id_seq', (SELECT MAX(id) FROM encomium))";
		encomium.setId(jdbcTemplate.queryForObject(sqlGetid, Integer.class));
	}
	
	public void updateEncomium(Encomium encomium) {
		try {
			Map map = jdbcTemplate.queryForMap("SELECT * FROM encomium WHERE id= ?", encomium.getId());
		} catch (EmptyResultDataAccessException e) {
			throw new ServiceException(messageSource.getMessage("encomium.notFound", null, null));
		}
		String sql = "UPDATE encomium SET title = ? WHERE id = ?";
		jdbcTemplate.update(sql, encomium.getId());
	}

	public void removeEncomium(Encomium encomium) {
		try {
			Map map = jdbcTemplate.queryForMap("SELECT * FROM encomium WHERE id= ?", encomium.getId());
		} catch (EmptyResultDataAccessException e) {
			throw new ServiceException(messageSource.getMessage("encomium.notFound", null, null));
		}
		String sql = "DELETE FROM encomium WHERE id = ?";
		jdbcTemplate.update(sql, encomium.getId());
	}
}

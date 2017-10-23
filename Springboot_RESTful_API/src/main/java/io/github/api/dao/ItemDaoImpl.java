package io.github.api.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import io.github.api.entity.Item;

@Repository
public class ItemDaoImpl implements ItemDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private MessageSource messageSource;

//	----------------------------------------GET----------------------------------------
	public List<Item> getAllItem() {
		String sql = "SELECT * FROM item";
		List<Item> allItem = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Item.class));
		if (allItem.isEmpty()) {
			throw new EmptyResultDataAccessException(messageSource.getMessage("item.noResult", null, null), 0);
		}
		return allItem;
	};
	
	public Item getItemById(int id) {
		String sql = "SELECT * FROM item WHERE id = ?";
		try {
			Item item = jdbcTemplate.queryForObject(sql, new Object[] { id }, new BeanPropertyRowMapper<>(Item.class));
			return item;
		} catch (EmptyResultDataAccessException e) {
			throw new EmptyResultDataAccessException(messageSource.getMessage("item.notFound", null, null), 0);
		}
	};
	
	public String getTitleById(int id){
		String sql = "SELECT title FROM item WHERE id = ?";
		try {
			String title = jdbcTemplate.queryForObject(sql, String.class, id);
			return title;
		} catch (EmptyResultDataAccessException e) {
			throw new EmptyResultDataAccessException(messageSource.getMessage("title.notFound", null, null), 0);
		}
	};
	
    public String getNoteById(int id){
    	String sql = "SELECT note FROM item WHERE id = ?";
		try {
			String note = jdbcTemplate.queryForObject(sql, String.class, id);
			return note;
		} catch (EmptyResultDataAccessException e) {
			throw new EmptyResultDataAccessException(messageSource.getMessage("note.notFound", null, null), 0);
		}
    };
	
	public int getPriceById(int id){
		String sql = "SELECT price FROM item WHERE id = ?";
		try {
			int price = jdbcTemplate.queryForObject(sql, Integer.class, id);
			return price;
		} catch (EmptyResultDataAccessException e) {
			throw new EmptyResultDataAccessException(messageSource.getMessage("price.notFound", null, null), 0);
		}
	};
	
	public byte[] getImageById(int id){
		String sql = "SELECT image FROM item WHERE id = ?";
		try {
			byte[] image = jdbcTemplate.queryForObject(sql, byte[].class, id);
			return image;
		} catch (EmptyResultDataAccessException e) {
			throw new EmptyResultDataAccessException(messageSource.getMessage("image.notFound", null, null), 0);
		}
	};

	public List<Item> getItemsByTitle(String title) {
		String sql = "SELECT * FROM item WHERE title LIKE ?";
		List<Item> result = jdbcTemplate.query(sql, new Object[] { title }, new BeanPropertyRowMapper<>(Item.class));
		if (result.isEmpty()) {
			throw new EmptyResultDataAccessException(messageSource.getMessage("item.noResult", null, null), 0);
		}
		return result;
	};

	public List<Item> getItemsByNote(String note) {
		String sql = "SELECT * FROM item WHERE note LIKE ?";
		List<Item> result = jdbcTemplate.query(sql, new Object[] { note }, new BeanPropertyRowMapper<>(Item.class));
		if (result.isEmpty()) {
			throw new EmptyResultDataAccessException(messageSource.getMessage("item.noResult", null, null), 0);
		}
		return result;
	};

	public List<Item> getItemsByPrice(int price) {
		String sql = "SELECT * FROM item WHERE price = ?";
		List<Item> result = jdbcTemplate.query(sql, new Object[] { price }, new BeanPropertyRowMapper<>(Item.class));
		if (result.isEmpty()) {
			throw new EmptyResultDataAccessException(messageSource.getMessage("item.noResult", null, null), 0);
		}
		return result;
	};

//	----------------------------------------POST----------------------------------------
	public void createItem(Item item) {
		String sql = "INSERT INTO item (title, note, price, image) VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(sql, item.getTitle(), item.getNote(), item.getPrice(), item.getImage());
		String idSql = "SELECT setval('item_id_seq', (SELECT MAX(id) FROM item))";
		item.setId(jdbcTemplate.queryForObject(idSql, Integer.class));
	};
	
//	----------------------------------------PUT----------------------------------------
	public void updateItem(Item item) {
		try {
			Map map = jdbcTemplate.queryForMap("SELECT * FROM item WHERE id= ?", item.getId());
		} catch (Exception e) {
			createItem(item);
			return;
		}
		String sql = "UPDATE item SET title = ?, note = ?, price = ?, image = ? WHERE id = ?";
		jdbcTemplate.update(sql, item.getTitle(), item.getNote(), item.getPrice(), item.getImage(), item.getId());
	};
	
//	----------------------------------------DELETE----------------------------------------
	public void deleteAllItem() {
		String sql = "DELETE FROM item";
		jdbcTemplate.update(sql);
	};
	
	public void deleteItem(int id) {
		String sql = "DELETE FROM item WHERE id = ?";
		jdbcTemplate.update(sql, id);
	};
}

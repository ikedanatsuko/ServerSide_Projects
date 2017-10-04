package io.github.batch.dao.table;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import io.github.batch.dao.ItemOutputRowmapper;
import io.github.batch.entity.ItemOutput;

@Repository
public class CreatedItemDaoImpl implements ItemOutputDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private ItemOutputRowmapper itemOutputRowmapper;

//	----------------------------------------GET----------------------------------------
	public List<ItemOutput> getAllItem() {
		String sql = "SELECT * FROM created_item";
		List<ItemOutput> allItem = jdbcTemplate.query(sql, itemOutputRowmapper);
		if (allItem.isEmpty()) {
			throw new EmptyResultDataAccessException(messageSource.getMessage("createdItem.noResult", null, null), 0);
		}
		return allItem;
	};
	
	public List<ItemOutput> getItemsByDateId(int dateId){
		String sql = "SELECT * FROM created_item WHERE date_id = ?";
		List<ItemOutput> items = jdbcTemplate.query(sql, new Object[]{dateId}, itemOutputRowmapper);
		return items;
	};
	
	public ItemOutput getItemById(int id) {
		String sql = "SELECT * FROM created_item WHERE id = ?";
		try {
			ItemOutput item = jdbcTemplate.queryForObject(sql, new Object[] { id }, itemOutputRowmapper);
			return item;
		} catch (EmptyResultDataAccessException e) {
			throw new EmptyResultDataAccessException(messageSource.getMessage("createdItem.notFound", null, null), 0);
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
    	String sql = "SELECT note FROM created_item WHERE id = ?";
		try {
			String note = jdbcTemplate.queryForObject(sql, String.class, id);
			return note;
		} catch (EmptyResultDataAccessException e) {
			throw new EmptyResultDataAccessException(messageSource.getMessage("note.notFound", null, null), 0);
		}
    };
	
	public int getPriceById(int id){
		String sql = "SELECT price FROM created_item WHERE id = ?";
		try {
			int price = jdbcTemplate.queryForObject(sql, Integer.class, id);
			return price;
		} catch (EmptyResultDataAccessException e) {
			throw new EmptyResultDataAccessException(messageSource.getMessage("price.notFound", null, null), 0);
		}
	};
	
	public byte[] getImageById(int id){
		String sql = "SELECT image FROM created_item WHERE id = ?";
		try {
			byte[] image = jdbcTemplate.queryForObject(sql, byte[].class, id);
			return image;
		} catch (EmptyResultDataAccessException e) {
			throw new EmptyResultDataAccessException(messageSource.getMessage("image.notFound", null, null), 0);
		}
	};

//	----------------------------------------CREATE----------------------------------------
	public void createItem(ItemOutput item) {
		String sql = "INSERT INTO created_item (id, date_id, title, note, price, image) VALUES (?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, item.getId(), item.getDateId(), item.getTitle(), item.getNote(), item.getPrice(), item.getImage());
	};
	
//	----------------------------------------DELETE----------------------------------------
	public void deleteAllItem() {
		String sql = "DELETE FROM created_item";
		jdbcTemplate.update(sql);
	};
	
	public void deleteItem(int id) {
		String sql = "DELETE FROM created_item WHERE id = ?";
		jdbcTemplate.update(sql, id);
	};
}

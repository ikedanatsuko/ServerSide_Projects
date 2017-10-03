package io.github.batch.dao.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import io.github.batch.entity.ItemOutput;

@Repository
public class ItemCompareDaoImpl implements ItemCompareDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// Item mapping
	private class itemRowmapper implements RowMapper<ItemOutput> {
		@Override
		public ItemOutput mapRow(ResultSet rs, int rowNum) throws SQLException {
			ItemOutput item = new ItemOutput();
			item.setId(rs.getInt("id"));
			item.setTitle(rs.getString("title"));
			item.setNote(rs.getString("note"));
			item.setPrice(rs.getInt("price"));
			item.setImage(rs.getBytes("image"));

			return item;
		}
	}
	
	// Compare item and previous item
	public List<ItemOutput> getCreatedItems(){
		String sql = "SELECT * FROM item WHERE id NOT IN (SELECT id FROM pre_item)";
		List<ItemOutput> createdItem = jdbcTemplate.query(sql, new itemRowmapper());
		
		return createdItem;
	};
		
	public List<ItemOutput> getDeletedItems(){
		String sql = "SELECT * FROM pre_item WHERE id NOT IN (SELECT id FROM item)";
		List<ItemOutput> deletedItem = jdbcTemplate.query(sql, new itemRowmapper());
		
		return deletedItem;
	};
		
	// Copy item to pre_item
	public void setPreItems(){
		String deleteSql = "DELETE FROM pre_item";
		String copySql = "INSERT INTO pre_item SELECT * FROM item";	
		jdbcTemplate.update(deleteSql);
		jdbcTemplate.update(copySql);
	};
}

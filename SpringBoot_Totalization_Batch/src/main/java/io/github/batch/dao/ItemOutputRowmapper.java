package io.github.batch.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import io.github.batch.entity.ItemOutput;

@Component
public class ItemOutputRowmapper implements RowMapper<ItemOutput> {
	
	@Override
	public ItemOutput mapRow(ResultSet rs, int rowNum) throws SQLException {
		ItemOutput item = new ItemOutput();
		item.setId(rs.getInt("id"));
		item.setDateId(rs.getInt("date_id"));
		item.setTitle(rs.getString("title"));
		item.setNote(rs.getString("note"));
		item.setPrice(rs.getInt("price"));
		item.setImage(rs.getBytes("image"));

		return item;
	}
}

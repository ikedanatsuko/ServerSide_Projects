package io.github.batch.dao.table;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import io.github.batch.entity.DateTotal;

@Repository
public class DateTotalDaoImpl implements DateTotalDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private MessageSource messageSource;

	// Item mapping
	private class dateTotalRowmapper implements RowMapper<DateTotal> {
		@Override
		public DateTotal mapRow(ResultSet rs, int rowNum) throws SQLException {
			DateTotal dateTotal = new DateTotal();
			dateTotal.setId(rs.getInt("id"));
			dateTotal.setDate(rs.getDate("date"));
			dateTotal.setCreatedTotal(rs.getInt("created_total"));
			dateTotal.setDeletedTotal(rs.getInt("deleted_total"));
			
			return dateTotal;
		}
	}
	
//	----------------------------------------GET----------------------------------------
	public List<DateTotal> getAllDateTotal(){
		String sql = "SELECT * FROM date_total";
		List<DateTotal> allDateTotal = jdbcTemplate.query(sql, new dateTotalRowmapper());
		if (allDateTotal.isEmpty()) {
			throw new EmptyResultDataAccessException(messageSource.getMessage("dateTotal.noResult", null, null), 0);
		}
		return allDateTotal;
	};
	
	public List<DateTotal> getDateTotalsByTerm(Date begin, Date end){
		String sql = "SELECT * FROM date_total WHERE date between ? and ?";
		List<DateTotal> dateTotals = jdbcTemplate.query(sql, new Object[] {begin, end}, new dateTotalRowmapper());
		if (dateTotals.isEmpty()) {
			throw new EmptyResultDataAccessException(messageSource.getMessage("dateTotal.noResult", null, null), 0);
		}
		return dateTotals;
	};
	
	public DateTotal getDateTotalById(int id){
		String sql = "SELECT * FROM date_total WHERE id = ?";
		try {
			DateTotal dateTotal = jdbcTemplate.queryForObject(sql, new Object[] { id }, new dateTotalRowmapper());
			return dateTotal;
		} catch (EmptyResultDataAccessException e) {
			throw new EmptyResultDataAccessException(messageSource.getMessage("dateTotal.notFound", null, null), 0);
		}
	};
	
	public DateTotal getDateTotalByDate(Date date){
		String sql = "SELECT * FROM date_total WHERE date = ?";
		try {
			DateTotal dateTotal = jdbcTemplate.queryForObject(sql, new Object[] { date }, new dateTotalRowmapper());
			return dateTotal;
		} catch (EmptyResultDataAccessException e) {
			throw new EmptyResultDataAccessException(messageSource.getMessage("dateTotal.notFound", null, null), 0);
		}
	};
	
	public Date getDateById(int id){
		String sql = "SELECT date FROM date_total WHERE id = ?";
		try {
			Date date = jdbcTemplate.queryForObject(sql, Date.class, id);
			return date;
		} catch (EmptyResultDataAccessException e) {
			throw new EmptyResultDataAccessException(messageSource.getMessage("date.notFound", null, null), 0);
		}
	};
	
	public int getCreatedTotalById(int id){
		String sql = "SELECT created_total FROM date_total WHERE id = ?";
		try {
			int total = jdbcTemplate.queryForObject(sql, Integer.class, id);
			return total;
		} catch (EmptyResultDataAccessException e) {
			throw new EmptyResultDataAccessException(messageSource.getMessage("createdTotal.notFound", null, null), 0);
		}
	};
	
	public int getDeletedTotalById(int id) {
		String sql = "SELECT deleted_total FROM date_total WHERE id = ?";
		try {
			int total = jdbcTemplate.queryForObject(sql, Integer.class, id);
			return total;
		} catch (EmptyResultDataAccessException e) {
			throw new EmptyResultDataAccessException(messageSource.getMessage("deletedTotal.notFound", null, null), 0);
		}
	};
	
//	----------------------------------------CREATE----------------------------------------
	public int createDateTotal(Date date){
		String sql = "INSERT INTO date_total (date) VALUES (?)";
		jdbcTemplate.update(sql, date);
		String idSql = "SELECT setval('date_total_id_seq', (SELECT MAX(id) FROM date_total))";
		int id = jdbcTemplate.queryForObject(idSql, Integer.class);
		
		return id;
	};
	
//	----------------------------------------UPDATE----------------------------------------
	public void setCreatedTotal(int id, int total){
		String sql = "UPDATE date_total SET created_total = ? WHERE id = ?";
		jdbcTemplate.update(sql, total, id);
	};
	
	public void setDeletedTotal(int id, int total){
		String sql = "UPDATE date_total SET deleted_total = ? WHERE id = ?";
		jdbcTemplate.update(sql, total, id);
	};
	
//	----------------------------------------DELETE----------------------------------------
	public void deleteAllDateTotal(){
		String sql = "DELETE FROM date_total";
		jdbcTemplate.update(sql);
	};
	
	public void deleteDateTotal(int id){
		String sql = "DELETE FROM date_tota WHERE id = ?";
		jdbcTemplate.update(sql, id);
	};
}

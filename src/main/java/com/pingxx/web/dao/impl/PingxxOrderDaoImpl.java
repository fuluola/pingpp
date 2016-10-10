package com.pingxx.web.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.pingxx.web.dao.PingxxOrderDao;
import com.pingxx.web.entity.PingxxOrderEntity;

/** 
 * @author  fuzhuan e-mail: 676646535@qq.com
 * @date 2016年10月10日 
 */
@Service
public class PingxxOrderDaoImpl implements PingxxOrderDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<PingxxOrderEntity> findOrderPage(int pageSize, int pageIndex) {
		
		String sql = "SELECT * FROM pingxx_order";
		List<PingxxOrderEntity>  list = jdbcTemplate.query(sql, new PingxxOrderRowMapper());
		return list;
	}

}

class PingxxOrderRowMapper implements RowMapper<PingxxOrderEntity> {

	@Override
	public PingxxOrderEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		PingxxOrderEntity entity = new PingxxOrderEntity();
		entity.setId(rs.getInt("id"));
		entity.setAmount(rs.getInt("amount"));
		entity.setChannel(rs.getString("channel"));
		entity.setCreatedTime(rs.getDate("createdTime"));
		entity.setOrderNo(rs.getString("orderNo"));
		return entity;
	}
	
}
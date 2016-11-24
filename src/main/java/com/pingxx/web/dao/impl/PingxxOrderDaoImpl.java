package com.pingxx.web.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.pingpp.api.model.Constants;
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

	@Override
	public int insert(PingxxOrderEntity entity) {
		
		Integer row = findCountByPingxxId(entity.getPingxxId());
		if(row>=1){
			return 0;
		}
		int savedRow = jdbcTemplate.update("insert into pingxx_order (pingxxId,amount,currency,channel,payStatus,orderNo,partner,productId,subject,body,successUrl,cancelUrl,callbackUrl,clientIp,openId,createdTime) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now())", 
				entity.getPingxxId(),entity.getAmount(),entity.getCurrency(),entity.getChannel(),Constants.NOT_PAID,
				entity.getOrderNo(),entity.getPartner(),entity.getProductId(),entity.getSubject(),
				entity.getBody(),entity.getSuccessUrl(),entity.getCancelUrl(),entity.getCallbackUrl(),
				entity.getClientIp(),entity.getOpenId());
		return savedRow;
	}

	@Override
	public int update(boolean paid,String channelSerial,String pingxxId) {
		
		return jdbcTemplate.update("update pingxx_order set payStatus=?,channelSerial=?,updatedTime=now() WHERE pingxxId=?", 
				paid?Constants.HAS_PAID:Constants.NOT_PAID,channelSerial,pingxxId);
	}

	@Override
	public Integer findCountByPingxxId(String pingxxId) {
		
		String sql = "SELECT count(1) FROM pingxx_order WHERE pingxxId=?";
		Integer row = jdbcTemplate.queryForObject(sql, new Object[]{pingxxId}, Integer.class);
		return row;
	}

	@Override
	public PingxxOrderEntity findByPingxxId(String pingxxId) {
		String sql = "SELECT * FROM pingxx_order WHERE pingxxId=?";
		List<PingxxOrderEntity> list = jdbcTemplate.query(sql, new Object[]{pingxxId}, new PingxxOrderRowMapper());
		if(list.size()>=1){
			return list.get(0);
		}
		return null;
	}

}

class PingxxOrderRowMapper implements RowMapper<PingxxOrderEntity> {

	@Override
	public PingxxOrderEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		PingxxOrderEntity entity = new PingxxOrderEntity();
		entity.setPingxxId(rs.getString("pingxxId"));
		entity.setAmount(rs.getInt("amount"));
		entity.setChannel(rs.getString("channel"));
		entity.setCreatedTime(rs.getDate("createdTime"));
		entity.setOrderNo(rs.getString("orderNo"));
		entity.setCallbackUrl(rs.getString("callbackUrl"));
		return entity;
	}
	
}
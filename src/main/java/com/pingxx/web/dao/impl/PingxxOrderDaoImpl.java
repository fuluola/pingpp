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
		
		PingxxOrderEntity entity2 = findByPingxxId(entity.getPingxxId());
		if(entity2!=null){
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
	public int update(boolean paid,String channelSerial) {
		
		return jdbcTemplate.update("update pingxx_order set payStatus=?,channelSerial=?,updateTime=now() WHERE pingxxId=?", 
				paid?Constants.HAS_PAID:Constants.NOT_PAID,channelSerial);
	}

	@Override
	public PingxxOrderEntity findByPingxxId(String pingxxId) {
		
		String sql = "SELECT * FROM pingxx_order WHERE pingxxId=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{pingxxId}, new PingxxOrderRowMapper());
	}

}

class PingxxOrderRowMapper implements RowMapper<PingxxOrderEntity> {

	@Override
	public PingxxOrderEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		PingxxOrderEntity entity = new PingxxOrderEntity();
		entity.setPingxxId(rs.getString("id"));
		entity.setAmount(rs.getInt("amount"));
		entity.setChannel(rs.getString("channel"));
		entity.setCreatedTime(rs.getDate("createdTime"));
		entity.setOrderNo(rs.getString("orderNo"));
		return entity;
	}
	
}
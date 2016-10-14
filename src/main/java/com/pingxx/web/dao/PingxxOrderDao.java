package com.pingxx.web.dao;

import java.util.List;

import com.pingxx.web.entity.PingxxOrderEntity;

/** 
 * @author  fuzhuan e-mail: 676646535@qq.com
 * @date 2016年10月10日 
 */
public interface PingxxOrderDao {
	
	/**
	 * 根据pingxxID查询订单
	 * @param pingxxId
	 * @return
	 */
	public Integer findByPingxxId(String pingxxId);
	
	public List<PingxxOrderEntity> findOrderPage(int pageSize,int pageIndex);
	/**
	 * 保存订单信息
	 * @param entity
	 * @return
	 */
	public int insert(PingxxOrderEntity entity);
	/**
	 *  接受webhooks，修改订单信息
	 * @param paid
	 * @param channelSerial
	 * @param pingxxId
	 * @return
	 */
	public int update(boolean paid,String channelSerial,String pingxxId);
}

package com.pingxx.web.entity;

import java.time.LocalDateTime;
import java.util.Date;

/** 
 * @author  fuzhuan e-mail: 676646535@qq.com
 * @date 2016年10月10日 
 */
public class PingxxOrderEntity {

	private Integer id;
	private String orderNo;
	private String channel;
	private Integer amount;
	private Date createdTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date created) {
		this.createdTime = created;
	} 
	
}

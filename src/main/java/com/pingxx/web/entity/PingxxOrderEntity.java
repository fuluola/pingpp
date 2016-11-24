package com.pingxx.web.entity;

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
	private String currency;
	private String pingxxId;
	private int payStatus;
	private String partner;
	private String productId;
	private String subject;//商品名
	private String body;//商品描述
	private String channelSerial;//渠道流水号
	private String successUrl;
	private String cancelUrl;
	private String callbackUrl;
	private String clientIp;
	private String openId;//微信公众号ID
	private Date createdTime;
	private Date updatedTime;
	
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
	
	public String getPingxxId() {
		return pingxxId;
	}
	public void setPingxxId(String pingxxId) {
		this.pingxxId = pingxxId;
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
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	public int getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(int payStatus) {
		this.payStatus = payStatus;
	}
	public String getPartner() {
		return partner;
	}
	public void setPartner(String partner) {
		this.partner = partner;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getChannelSerial() {
		return channelSerial;
	}
	public void setChannelSerial(String channelSerial) {
		this.channelSerial = channelSerial;
	}
	public String getSuccessUrl() {
		return successUrl;
	}
	public void setSuccessUrl(String successUrl) {
		this.successUrl = successUrl;
	}
	public String getCancelUrl() {
		return cancelUrl;
	}
	public void setCancelUrl(String cancelUrl) {
		this.cancelUrl = cancelUrl;
	}
	public String getCallbackUrl() {
		return callbackUrl;
	}
	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	} 
	
}

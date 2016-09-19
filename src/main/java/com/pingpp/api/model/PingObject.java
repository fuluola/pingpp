package com.pingpp.api.model;

import com.pingplusplus.model.PingppObject;

/** 
 * @author  fuzhuan e-mail: 676646535@qq.com
 * @date 2016年9月19日 
 */
public class PingObject implements java.io.Serializable{
	private static final long serialVersionUID = 8669281623891133824L;
	
	private EventDTO object;

	public EventDTO getObject() {
		return object;
	}

	public void setObject(EventDTO object) {
		this.object = object;
	}
	
	public String getCallbackUrl(){
		return this.object.getMetadata().callbackUrl;
	}
}
 class EventDTO extends PingppObject {

	
	private String id;
	private String object;
	private long created;
	private boolean livemode;
	private boolean paid;
	private boolean refunded;
	private String app;
	private String channel;
	private String order_no;
	private String client_ip;
	private int amount;
	private int amount_settle;
	private String currency;
	private String subject;
	private String body;
	private Extra extra;
	private Long time_paid;
	private Long time_expire;
	private Long time_settle;
	private String transaction_no;
	private Refunds refunds;
	private int amount_refunded;
	private String failure_code;
	private String failure_msg;
	private Metadata metadata;
	private Object credential;
	private String description;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
	}
	public long getCreated() {
		return created;
	}
	public void setCreated(long created) {
		this.created = created;
	}
	public boolean isLivemode() {
		return livemode;
	}
	public void setLivemode(boolean livemode) {
		this.livemode = livemode;
	}
	public boolean isPaid() {
		return paid;
	}
	public void setPaid(boolean paid) {
		this.paid = paid;
	}
	public boolean isRefunded() {
		return refunded;
	}
	public void setRefunded(boolean refunded) {
		this.refunded = refunded;
	}
	public String getApp() {
		return app;
	}
	public void setApp(String app) {
		this.app = app;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getClient_ip() {
		return client_ip;
	}
	public void setClient_ip(String client_ip) {
		this.client_ip = client_ip;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getAmount_settle() {
		return amount_settle;
	}
	public void setAmount_settle(int amount_settle) {
		this.amount_settle = amount_settle;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
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
	public Extra getExtra() {
		return extra;
	}
	public void setExtra(Extra extra) {
		this.extra = extra;
	}
	public Long getTime_paid() {
		return time_paid;
	}
	public void setTime_paid(Long time_paid) {
		this.time_paid = time_paid;
	}
	public Long getTime_expire() {
		return time_expire;
	}
	public void setTime_expire(Long time_expire) {
		this.time_expire = time_expire;
	}
	public Long getTime_settle() {
		return time_settle;
	}
	public void setTime_settle(Long time_settle) {
		this.time_settle = time_settle;
	}
	public String getTransaction_no() {
		return transaction_no;
	}
	public void setTransaction_no(String transaction_no) {
		this.transaction_no = transaction_no;
	}
	public Refunds getRefunds() {
		return refunds;
	}
	public void setRefunds(Refunds refunds) {
		this.refunds = refunds;
	}
	public int getAmount_refunded() {
		return amount_refunded;
	}
	public void setAmount_refunded(int amount_refunded) {
		this.amount_refunded = amount_refunded;
	}
	public String getFailure_code() {
		return failure_code;
	}
	public void setFailure_code(String failure_code) {
		this.failure_code = failure_code;
	}
	public String getFailure_msg() {
		return failure_msg;
	}
	public void setFailure_msg(String failure_msg) {
		this.failure_msg = failure_msg;
	}
	
	public Metadata getMetadata() {
		return metadata;
	}
	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}
	public Object getCredential() {
		return credential;
	}
	public void setCredential(Object credential) {
		this.credential = credential;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}

class Extra {
	private String success_url;
	private String cancel_url;
	private String buyer_account;
	public String getSuccess_url() {
		return success_url;
	}
	public void setSuccess_url(String success_url) {
		this.success_url = success_url;
	}
	public String getCancel_url() {
		return cancel_url;
	}
	public void setCancel_url(String cancel_url) {
		this.cancel_url = cancel_url;
	}
	public String getBuyer_account() {
		return buyer_account;
	}
	public void setBuyer_account(String buyer_account) {
		this.buyer_account = buyer_account;
	}
	
}

class Refunds {
	private String object;
	private String url;
	private String has_more;
	private Object data;
	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getHas_more() {
		return has_more;
	}
	public void setHas_more(String has_more) {
		this.has_more = has_more;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
class Metadata {
	public String callbackUrl;
}
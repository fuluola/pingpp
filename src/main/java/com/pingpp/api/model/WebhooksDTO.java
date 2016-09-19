package com.pingpp.api.model;


/** 
 * @author  fuzhuan e-mail: 676646535@qq.com
 * @date 2016年9月19日 
 */
public class WebhooksDTO implements java.io.Serializable{

		private static final long serialVersionUID = 7778152132086111802L;
	
		private String id;
	    private String object;
	    private Boolean livemode;
	    private Long created;
	    private PingObject data;
	    private Integer pendingWebhooks;
	    private String type;
	    private String request;

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

	    public Boolean getLivemode() {
	        return livemode;
	    }

	    public void setLivemode(Boolean livemode) {
	        this.livemode = livemode;
	    }

	    public Long getCreated() {
	        return created;
	    }

	    public void setCreated(Long created) {
	        this.created = created;
	    }

	    public PingObject getData() {
			return data;
		}

		public void setData(PingObject data) {
			this.data = data;
		}

		public Integer getPendingWebhooks() {
	        return pendingWebhooks;
	    }

	    public void setPendingWebhooks(Integer pendingWebhooks) {
	        this.pendingWebhooks = pendingWebhooks;
	    }

	    public String getType() {
	        return type;
	    }

	    public void setType(String type) {
	        this.type = type;
	    }

	    public String getRequest() {
	        return request;
	    }

	    public void setRequest(String request) {
	        this.request = request;
	    }

}

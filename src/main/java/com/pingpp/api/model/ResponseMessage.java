package com.pingpp.api.model;

/**
 * 
 * @author fuzhuan
 * @date 2016-09-18
 */
public class ResponseMessage implements java.io.Serializable{

	private static final long serialVersionUID = 2813607664702059250L;
	public static final String SUCCESS_CODE = "0";
	public static final String ERROR_CODE = "1";
	
	protected String code;
    protected String msg;
    protected Object data;
    
    public ResponseMessage(){}
    
    public ResponseMessage(String code,String msg,Object data){
    	this.code = code;
    	this.msg = msg;
    	this.data = data;
    }
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
    
    
}

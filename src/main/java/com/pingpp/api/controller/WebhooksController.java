package com.pingpp.api.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.pingplusplus.model.Webhooks;
import com.pingpp.api.model.Constants;
import com.pingpp.api.model.ResponseMessage;
import com.pingpp.api.model.WebhooksDTO;
import com.pingpp.api.util.AESUtil;
import com.pingpp.api.util.HttpUtils;
import com.pingpp.api.util.PropertiesUtil;
import com.pingpp.api.util.SecurityUtil;
import com.pingpp.api.util.WebhooksVerifyUtil;
import com.pingxx.web.dao.PingxxOrderDao;

/** 
 * @author  fuzhuan e-mail: 676646535@qq.com
 * @date 2016年9月19日 
 */
@Controller
public class WebhooksController {
	
	private static Log log = LogFactory.getLog(WebhooksController.class);
	
	private static Gson gson = new Gson();
	
	@Autowired
	private PingxxOrderDao pingxxOrderDao;
	
	@RequestMapping(value="server/webhooks/charge")
	public void webhooks(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF8");
        //获取头部所有信息
        Enumeration headerNames = request.getHeaderNames();
        String signature = null;
        String webhooksRawPostData = null;
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            if("x-pingplusplus-signature".equalsIgnoreCase(key)){
            	signature = request.getHeader(key);
            	break;
            }
        }
        log.info("x-pingplusplus-signature:\n"+signature);
        // 获得 http body 内容
        BufferedReader reader = request.getReader();
        StringBuffer buffer = new StringBuffer();
        String string;
        while ((string = reader.readLine()) != null) {
            buffer.append(string);
        }
        reader.close();
        webhooksRawPostData = buffer.toString();
        log.info("-----webhooks返回的body------\n"+webhooksRawPostData);
        boolean isVerify = false;
        try {
        	isVerify = WebhooksVerifyUtil.verifyData(webhooksRawPostData, signature);
		} catch (Exception e) {
			response.setStatus(500);
			log.error(e.getMessage(), e);
			return;
		}
        WebhooksDTO event = null;
        String callbackResult = null;
        ResponseMessage respMsg = null;
        if(isVerify){
        	// 解析异步通知数据
        	event = Webhooks.eventParse2(webhooksRawPostData);
        	log.info("------接受webhook验证签名成功------");
        	String callbackUrl = event.getData().getCallbackUrl();
        	boolean paid = event.getData().isPaid();
        	pingxxOrderDao.update(paid, event.getData().getTransaction_no(),event.getData().getPingxxId());
        	NameValuePair[] data = new NameValuePair[3];
        	String content = Base64.encodeBase64String(webhooksRawPostData.getBytes("utf-8"));
        	data[0] = new NameValuePair("content", content);
        	try {
				data[1] = new NameValuePair("verify", SecurityUtil.MD5((content+PropertiesUtil.getSecretKey()).getBytes("UTF-8")));
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
        	data[2] = new NameValuePair("token", generateToken(event));
        	if(StringUtils.isNotBlank(callbackUrl)){
        		
        		callbackResult = HttpUtils.sendRequest(callbackUrl, data, "utf-8", 3000);
        		log.info("----客户端接受webhooks返回值-----\n"+callbackResult);
        		respMsg = gson.fromJson(callbackResult, ResponseMessage.class);
        		if(ResponseMessage.SUCCESS_CODE.equals(respMsg.getCode())){
        			  response.setStatus(200);
        		}else{
        			response.setStatus(500);
        		}
        	}
        }else{
        	log.info("-----接受webhooks验证没有通过-----");
        	response.setStatus(500);
        	return;
        }
        
        if ("charge.succeeded".equals(event.getType())) {
            response.setStatus(200);
        }  else {
            response.setStatus(500);
        }
	}
	
	private String generateToken(WebhooksDTO event) {
    	Long timepaid = event.getData().getTimepaid();
    	int amount = event.getData().getAmount();
    	String orderNo = event.getData().getOrderNo();
    	String tokenPlain = "aes("+orderNo+":"+amount+":"+timepaid+")";
    	String token="";
		try {
			token = AESUtil.encrypt(tokenPlain, Constants.AES_KEY);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return token;
	}
}

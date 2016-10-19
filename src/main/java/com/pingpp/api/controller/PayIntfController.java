package com.pingpp.api.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.pingplusplus.Pingpp;
import com.pingplusplus.exception.APIConnectionException;
import com.pingplusplus.exception.APIException;
import com.pingplusplus.exception.AuthenticationException;
import com.pingplusplus.exception.ChannelException;
import com.pingplusplus.exception.InvalidRequestException;
import com.pingplusplus.model.Charge;
import com.pingpp.api.model.ChargeDTO;
import com.pingpp.api.model.ResponseMessage;
import com.pingpp.api.service.ChargeService;
import com.pingpp.api.util.BeanUtils;
import com.pingpp.api.util.PropertiesUtil;
import com.pingpp.api.util.SecurityUtil;
import com.pingxx.web.dao.PingxxOrderDao;

@Controller
public class PayIntfController {

    //private final static String apiKey = "sk_test_ibbTe5jLGCi5rzfH4OqPW9KC";
    //private final static String appId = "app_1Gqj58ynP0mHeX1q";
//	 private final static String apiKey = "sk_test_iX5abLDizLW1yDKKmL4m1CCO";
	// private final static String apiKey = "sk_live_ejnLuPrTKWPSmbnLK4qvb9C0";
//	private final static String apiKey = PropertiesUtil.getTestApikey();
	private static Log log = LogFactory.getLog(WebhooksController.class);
	private final static String apiKey = PropertiesUtil.getLiveApikey();

	private final static String appId = "app_iDy9yPXH88uTa5uv";
	 
	 
    /**
   * 设置请求签名密钥，密钥对需要你自己用 openssl 工具生成，如何生成可以参考帮助中心：https://help.pingxx.com/article/123161；
   * 生成密钥后，需要在代码中设置请求签名的私钥(rsa_private_key.pem)；
   * 然后登录 [Dashboard](https://dashboard.pingxx.com)->点击右上角公司名称->开发信息->商户公钥（用于商户身份验证）
   * 将你的公钥复制粘贴进去并且保存->先启用 Test 模式进行测试->测试通过后启用 Live 模式
   */

    // 生成的私钥路径
    private final static String privateKeyFilePath = "/conf/rsa_private_key.pem";

    @Autowired
    private ChargeService chargeService;
	@Autowired
	private PingxxOrderDao pingxxOrderDao;
	
    @ResponseBody
	@RequestMapping(value="client/ping/charge",method = RequestMethod.POST)
	public ResponseMessage charge(HttpServletRequest request,HttpServletResponse response,
						@RequestBody Map<String, Object> params) {
		
	     // 设置 API Key
        Pingpp.apiKey = apiKey;
        // 设置私钥路径，用于请求签名
        Pingpp.privateKeyPath = privateKeyFilePath;       
        String partner=(String)params.get("partner");
        List<String> idList = PropertiesUtil.getPartnerId();
        
        if(StringUtils.isEmpty(partner) || !idList.contains(partner)){
        	return new ResponseMessage(ResponseMessage.ERROR_CODE,"partner参数为空或者在系统不存在",null);
        }
        Gson gson =new Gson();
        log.info("------- 创建 charge -------");
        String content = (String)params.get("content");
        String deContent = null;
        String verify2 = null;
		try {
			deContent = new String(new Base64().decode(content),"utf-8");
			log.info("-----请求参数-----\n"+deContent);
			verify2 = SecurityUtil.MD5((partner+content+PropertiesUtil.getSecretKey()).getBytes("UTF-8"));
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			
			log.error(e.getMessage(),e);
			return new ResponseMessage(ResponseMessage.ERROR_CODE,e.getMessage(),null);
		}		
        String verify = (String) params.get("verify");
        ResponseMessage respMsg = null;
        if(StringUtils.isBlank(verify)){
        	respMsg = new ResponseMessage(ResponseMessage.ERROR_CODE,"校验字段为空",null);
        	return respMsg;
        }
        if(!verify.equals(verify2)){
        	respMsg = new ResponseMessage(ResponseMessage.ERROR_CODE,"非法的请求，校验失败",null);
        	return respMsg;
        }
        ChargeDTO dto = gson.fromJson(deContent, ChargeDTO.class);
        dto.setPartner(partner);
        dto.setClientIp(request.getRemoteAddr());
        chargeService.setAppId(appId);
        Charge charge = null;
        //发起交易请求
        // 传到客户端请先转成字符串 .toString(), 调该方法，会自动转成正确的 JSON 字符串
        try {
			charge = chargeService.createCharge(dto);
		} catch (AuthenticationException | InvalidRequestException | APIConnectionException | APIException
				| ChannelException e) {
			log.error(e.getMessage(),e);
			return new ResponseMessage(ResponseMessage.ERROR_CODE,e.getMessage(),null);
		}
        if(charge==null){
        	respMsg = new ResponseMessage(ResponseMessage.ERROR_CODE,"没有返回支付对象",null);
        }
        
        pingxxOrderDao.insert(BeanUtils.copyChargeToPingxxOrder(charge));
        
        respMsg = new ResponseMessage(ResponseMessage.SUCCESS_CODE,"发起交易请求成功",charge);
		return respMsg;
	}
    
    @ResponseBody
   	@RequestMapping(value="client/ping/retrieve",method = RequestMethod.POST)
    public ResponseMessage retrieve(HttpServletRequest request,@RequestBody Map<String,String> params) {
    	
    	   // 设置 API Key
        Pingpp.apiKey = apiKey;
        String id = null;
        String verify2 = null;
        String partner=(String)params.get("partner");
        List<String> idList = PropertiesUtil.getPartnerId();
        
        if(StringUtils.isEmpty(partner) || !idList.contains(partner)){
        	return new ResponseMessage(ResponseMessage.ERROR_CODE,"partner参数为空或者在系统不存在",null);
        }
        String content = (String)params.get("content");
		try {
			id = new String(new Base64().decode(content),"utf-8");
			log.info("-----请求参数-----\n"+id);
			verify2 = SecurityUtil.MD5((partner+content+PropertiesUtil.getSecretKey()).getBytes("UTF-8"));
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			
			e.printStackTrace();
			return new ResponseMessage(ResponseMessage.ERROR_CODE,e.getMessage(),null);
		}
        String verify = (String) params.get("verify");
    	Charge charge = null;
        if(!verify.equals(verify2)){
        	return new ResponseMessage(ResponseMessage.ERROR_CODE,"非法的请求，校验失败",null);
        }
		try {
			charge = chargeService.retrieve(id);
		} catch (AuthenticationException | InvalidRequestException
				| APIConnectionException | APIException | ChannelException e) {
			log.error(e.getMessage(),e);
			return new ResponseMessage(ResponseMessage.ERROR_CODE,e.getMessage(),charge);
		}
    	return new ResponseMessage(ResponseMessage.SUCCESS_CODE,"查询成功",charge);
    }
}

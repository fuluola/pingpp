package com.pingpp.api.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.pingplusplus.Pingpp;
import com.pingplusplus.model.Charge;
import com.pingpp.api.model.ChargeDTO;
import com.pingpp.api.service.ChargeService;

@Controller
public class PayIntfController {

    private final static String apiKey = "sk_test_ibbTe5jLGCi5rzfH4OqPW9KC";


    private final static String appId = "app_1Gqj58ynP0mHeX1q";

    /**
   * 设置请求签名密钥，密钥对需要你自己用 openssl 工具生成，如何生成可以参考帮助中心：https://help.pingxx.com/article/123161；
   * 生成密钥后，需要在代码中设置请求签名的私钥(rsa_private_key.pem)；
   * 然后登录 [Dashboard](https://dashboard.pingxx.com)->点击右上角公司名称->开发信息->商户公钥（用于商户身份验证）
   * 将你的公钥复制粘贴进去并且保存->先启用 Test 模式进行测试->测试通过后启用 Live 模式
   */

    // 你生成的私钥路径
    private final static String privateKeyFilePath = "E:\\workspace\\pingpp\\src\\main\\resources\\conf\\rsa_private_key.pem";
    
    @Autowired
    private ChargeService chargeService;
    
	@RequestMapping(value="client/ping/charge",method = RequestMethod.POST)
	public String charge(HttpServletRequest request,HttpServletResponse response,
						@RequestBody Map<String, Object> params){
		
	     // 设置 API Key
        Pingpp.apiKey = apiKey;

        // 设置私钥路径，用于请求签名
        Pingpp.privateKeyPath = privateKeyFilePath;
        
        System.out.println("------- 创建 charge -------");
        Object content = params.get("content");
       // String reqVerify = (String) params.get("verify");
        Gson gson =new Gson();
        ChargeDTO dto = gson.fromJson(gson.toJson(content), ChargeDTO.class);
        chargeService.setAppId(appId);
        Charge charge = chargeService.createCharge( dto);
		return charge.toString();
		
	}
}

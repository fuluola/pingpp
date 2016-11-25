package com.pingpp.test;

import java.lang.reflect.Type;
import java.math.BigInteger;
import java.net.URL;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pingplusplus.Pingpp;
import com.pingpp.api.model.ChargeDTO;
import com.pingpp.api.model.WebhooksDTO;
import com.pingpp.api.util.PropertiesUtil;
import com.pingpp.api.util.SecurityUtil;

/**
 * Created by Afon on 16/4/26.
 */
public class Main {

    /**
     * Pingpp 管理平台对应的 API Key，api_key 获取方式：登录 [Dashboard](https://dashboard.pingxx.com)->点击管理平台右上角公司名称->开发信息-> Secret Key
     */
    private final static String apiKey = "sk_test_ibbTe5jLGCi5rzfH4OqPW9KC";

    /**
     * Pingpp 管理平台对应的应用 ID，app_id 获取方式：登录 [Dashboard](https://dashboard.pingxx.com)->点击你创建的应用->应用首页->应用 ID(App ID)
     */
    private final static String appId = "app_1Gqj58ynP0mHeX1q";

    /**
   * 设置请求签名密钥，密钥对需要你自己用 openssl 工具生成，如何生成可以参考帮助中心：https://help.pingxx.com/article/123161；
   * 生成密钥后，需要在代码中设置请求签名的私钥(rsa_private_key.pem)；
   * 然后登录 [Dashboard](https://dashboard.pingxx.com)->点击右上角公司名称->开发信息->商户公钥（用于商户身份验证）
   * 将你的公钥复制粘贴进去并且保存->先启用 Test 模式进行测试->测试通过后启用 Live 模式
   */

    // 你生成的私钥路径
    private final static String privateKeyFilePath = "E:\\workspace\\pingpp\\src\\main\\resources\\conf\\rsa_private_key.pem";

    public static void main(String[] args) throws Exception {

        // 设置 API Key
        Pingpp.apiKey = apiKey;

        // 设置私钥路径，用于请求签名
        Pingpp.privateKeyPath = privateKeyFilePath;

        /**
         * 或者直接设置私钥内容
         Pingpp.privateKey = "-----BEGIN RSA PRIVATE KEY-----\n" +
         "... 私钥内容字符串 ...\n" +
         "-----END RSA PRIVATE KEY-----\n";
         */

        // Charge 示例
    //    ChargeExample.runDemos(appId);
        
        ChargeDTO dto = new ChargeDTO();
        dto.setAmount(1);
        dto.setBody("微信扫码支付");
        dto.setChannel("wx_pub_qr");
        dto.setClientIp("127.0.0.1");
        dto.setCurrency("cny");
        
        dto.setOrderNo("FZ000020");
        dto.setSubject("微信扫码支付测试");
        //dto.setOpenId("o9zpMs7Xk7e9aJbTXgufovuWGp8c");
        dto.setProductId("rabbit002");
        dto.setSuccessUrl("https://my.oschina.net/fuluola");
        dto.setCallbackUrl("http://lai68.vicp.net:14485/PayApi/CallBack33");
        Gson gson = new Gson();
        String json = gson.toJson(dto);
        String orderId = "ch_XP8Ga1qfnf5GC8ezf9P8yDmL";
        String encode = new String(new Base64().encode(json.getBytes("utf-8")),"utf-8");
        Map<String, Object> chargeMap = new HashMap<String, Object>();
        String verify = SecurityUtil.MD5(("ihomeins"+encode+PropertiesUtil.getSecretKey()).getBytes("utf-8"));
        chargeMap.put("partner", "ihomeins");
        chargeMap.put("content", encode);
        chargeMap.put("verify", verify);
        

          json = gson.toJson(chargeMap);
          System.out.println(json);
       // json = gson.toJson(event);
       // parseEvent();

    }

    private static SecureRandom random = new SecureRandom();

    public static String randomString(int length) {
        String str = new BigInteger(130, random).toString(32);
        return str.substring(0, length);
    }
    
    public void classLoader(){
//    	File f = new File(this.getClass().getResource("/").getPath());
//    	return f.getAbsolutePath();
    	
        URL xmlpath = this.getClass().getClassLoader().getResource("");
        System.out.println(xmlpath.getPath());
    }
    public static void parseEvent(){
    	String json = "{"
    			+"\"id\":\"evt_2ersVj0xe6N1T4GEWvSyg4Yb\","
    			+"\"created\":1458736877,"
    			+"\"livemode\":true,"
    			+"\"type\":\"charge.succeeded\","
    			+"\"data\":{"
    			+"\"object\":{"
    			+"\"id\":\"ch_bLOivPXjvj1OWvfH44WLenrD\","
    			+"\"object\":\"charge\","
    			+"\"created\":1458736849,"
    			+"\"livemode\":true,"
    			+"\"paid\":true,"
    			+"\"refunded\":false,"
    			+"\"app\":\"app_1Gqj58ynP0mHeX1q\","
    			+"\"channel\":\"alipay_wap\","
    			+"\"order_no\":\"123456789\","
    			+"\"client_ip\":\"116.228.208.114\","
    			+"\"amount\":100,"
    			+"\"amount_settle\":100,"
    			+"\"currency\":\"cny\","
    			+"\"subject\":\"YourSubject\","
    			+"\"body\":\"YourBody.\","
    			+"\"extra\":{"
    			+"\"success_url\":\"http://example.com/success\","
    			+"\"cancel_url\":\"http://example.com/cancel\","
    			+"\"buyer_account\":\"xinxinzhilianode@126.com\""
    			+"},"
    			+"\"time_paid\":1458736875,"
    			+"\"time_expire\":1458823249,"
    			+"\"time_settle\":null,"
    			+"\"transaction_no\":\"2016032321001004770283682851\","
    			+"\"refunds\":{"
    			+"\"object\":\"list\","
    			+"\"url\":\"/v1/charges/ch_bLOivPXjvj1OWvfH44WLenrD/refunds\","
    			+"\"has_more\":false,"
    			+"\"data\":[]"
    			+"},"
    			+"\"amount_refunded\":0,"
    			+"\"failure_code\":null,"
    			+"\"failure_msg\":null,"
    			+"\"metadata\":{"
    			+"\"partner\":\"ihomeins\",\"callbackUrl\":\"http://lai68.vicp.net:14485/PayApi/CallBack\""
    			+"},"
    			+"\"credential\":{},"
    			+"\"description\":\"YourDescription\""
    			+"}"
    			+"},"
    			+"\"object\":\"event\","
    			+"\"pending_webhooks\":71,"
    			+"\"request\":\"iar_ebTWPK8KGivPbD4OaPOeb1q5\""
    			+"}";
        Gson gson = new Gson();
        Type type = new TypeToken<WebhooksDTO>(){}.getType();
        WebhooksDTO event = gson.fromJson(json, WebhooksDTO.class);
    	System.out.println(event.getData().getCallbackUrl());
    }
}


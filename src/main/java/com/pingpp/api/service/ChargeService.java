/**
 * Ping++ Server SDK
 */
package com.pingpp.api.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.pingplusplus.exception.APIConnectionException;
import com.pingplusplus.exception.APIException;
import com.pingplusplus.exception.AuthenticationException;
import com.pingplusplus.exception.ChannelException;
import com.pingplusplus.exception.InvalidRequestException;
import com.pingplusplus.exception.PingppException;
import com.pingplusplus.model.Charge;
import com.pingplusplus.model.ChargeCollection;
import com.pingpp.api.model.ChargeDTO;
import com.pingpp.api.model.Constants;

/**
 * apiKey 有 TestKey 和 LiveKey 两种。
 * TestKey 模式下不会产生真实的交易。
 */
@Service
public class ChargeService {

	private String appId;
	
    /**
	 * @return the appId
	 */
	public String getAppId() {
		return appId;
	}

	/**
	 * @param appId the appId to set
	 */
	public void setAppId(String appId) {
		this.appId = appId;
	}

	public ChargeService(){}

    /**
     * 创建 Charge
     *
     * 创建 Charge 用户需要组装一个 map 对象作为参数传递给 Charge.create();
     * map 里面参数的具体说明请参考：https://pingxx.com/document/api#api-c-new
     * @return Charge
     * @throws ChannelException 
     * @throws APIException 
     * @throws APIConnectionException 
     * @throws InvalidRequestException 
     * @throws AuthenticationException 
     */
    public Charge createCharge(ChargeDTO dto ) throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException, ChannelException {
        Charge charge = null;
        Map<String, Object> chargeMap = new HashMap<String, Object>();
        chargeMap.put("amount", dto.getAmount());//订单总金额, 人民币单位：分（如订单总金额为 1 元，此处请填 100）
        chargeMap.put("currency", dto.getCurrency());
        chargeMap.put("subject", dto.getSubject());
        chargeMap.put("body", dto.getBody());
        chargeMap.put("order_no", dto.getOrderNo());// 推荐使用 8-20 位，要求数字或字母，不允许其他字符
        chargeMap.put("channel", dto.getChannel());// 支付使用的第三方支付渠道取值，请参考：https://www.pingxx.com/api#api-c-new
        chargeMap.put("client_ip", dto.getClientIp()); // 发起支付请求客户端的 IP 地址，格式为 IPV4，如: 127.0.0.1
        Map<String, String> app = new HashMap<String, String>();
        app.put("id", appId);
        chargeMap.put("app", app);

        Map<String, Object> extra = new HashMap<String, Object>();
//        extra.put("open_id", "USER_OPENID");
      //success_url 和 cancel_url 在本地测试不要写 localhost ，写 127.0.0.1，URL 后面不要加自定义参数
        if(Constants.PayChannel.ALIPAY_WAP.value.equals(dto.getChannel())){
        	
        	extra.put("success_url", dto.getSuccessUrl());
        	extra.put("cancel_url", dto.getCancelUrl());
        	chargeMap.put("extra", extra);
        }else if(Constants.PayChannel.ALIPAY_PC.value.equals(dto.getChannel())){
        	extra.put("success_url", dto.getSuccessUrl());
        	chargeMap.put("extra", extra);
        }else if(Constants.PayChannel.ALIPAY_QR.value.equals(dto.getChannel())){
        	
        }else if(Constants.PayChannel.WX_PUB.value.equals(dto.getChannel())){
        	  extra.put("open_id", dto.getOpenId());
        	  chargeMap.put("extra", extra);
        }else if(Constants.PayChannel.WX_WAP.value.equals(dto.getChannel())) {
        	
        }else if(Constants.PayChannel.WX_PUB_QR.value.equals(dto.getChannel())) {
        	  extra.put("product_id", dto.getProductId());
        	  chargeMap.put("extra", extra);
        }
        Map<String, String> metadata = new HashMap<String, String>();
        metadata.put("callbackUrl", dto.getCallbackUrl());
        metadata.put("partner", dto.getPartner());
        chargeMap.put("metadata", metadata);
        
        //发起交易请求
        charge = Charge.create(chargeMap);
        // 传到客户端请先转成字符串 .toString(), 调该方法，会自动转成正确的 JSON 字符串
        String chargeString = charge.toString();
        System.out.println(chargeString);
        return charge;
    }


    /**
     * 查询 Charge
     *
     * 该接口根据 charge Id 查询对应的 charge 。
     * 参考文档：https://pingxx.com/document/api#api-c-inquiry
     *
     * 该接口可以传递一个 expand ， 返回的 charge 中的 app 会变成 app 对象。
     * 参考文档： https://pingxx.com/document/api#api-expanding
     * @param id
     * @throws ChannelException 
     * @throws APIException 
     * @throws APIConnectionException 
     * @throws InvalidRequestException 
     * @throws AuthenticationException 
     */
    public Charge retrieve(String id) throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException, ChannelException {
        Charge charge = null;
       
        Map<String, Object> params = new HashMap<String, Object>();
//            List<String> expand = new ArrayList<String>();
//            expand.add("app");
//            params.put("expand", expand);
        charge = Charge.retrieve(id, params);
        System.out.println(charge);
        return charge;
    }

    /**
     * 分页查询 Charge
     *
     * 该接口为批量查询接口，默认一次查询10条。
     * 用户可以通过添加 limit 参数自行设置查询数目，最多一次不能超过 100 条。
     *
     * 该接口同样可以使用 expand 参数。
     * @return chargeCollection
     */
    public ChargeCollection all() {
        ChargeCollection chargeCollection = null;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("limit", 3);
        Map<String, String> app = new HashMap<String, String>();
        app.put("id", appId);
        params.put("app", app);

        try {
            chargeCollection = Charge.all(params);
            System.out.println(chargeCollection);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        } catch (ChannelException e) {
			e.printStackTrace();
		}

        return chargeCollection;
    }
}

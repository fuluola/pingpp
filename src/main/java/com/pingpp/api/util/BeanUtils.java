package com.pingpp.api.util;

import com.pingplusplus.model.Charge;
import com.pingxx.web.entity.PingxxOrderEntity;

/** 
 * @author  fuzhuan e-mail: 676646535@qq.com
 * @date 2016年10月11日 
 */
public class BeanUtils {

	public static PingxxOrderEntity copyChargeToPingxxOrder(Charge charge) {
		PingxxOrderEntity entity = new PingxxOrderEntity();
		entity.setAmount(charge.getAmount());
		entity.setBody(charge.getBody());
		entity.setCallbackUrl(charge.getMetadata().get("callbackUrl"));
		entity.setCancelUrl((String) charge.getExtra().get("cancel_url"));
		entity.setChannel(charge.getChannel());
		entity.setClientIp(charge.getClientIp());
		entity.setCurrency(charge.getCurrency());
		entity.setOpenId((String) charge.getExtra().get("open_id"));
		entity.setOrderNo(charge.getOrderNo());
		entity.setPartner(charge.getMetadata().get("partner"));
		entity.setPingxxId(charge.getId());
		entity.setSubject(charge.getSubject());
		entity.setSuccessUrl(charge.getMetadata().get("success_url"));
		return entity;
	}
}

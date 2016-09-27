package com.pingpp.api.model;
/** 
 * @author  fuzhuan e-mail: 676646535@qq.com
 * @date 2016年9月20日 
 */
public class Constants {

    public enum PayChannel {
        ALIPAY_WAP("alipay_wap"), // 支付宝手机网页支付
        ALIPAY_QR("alipay_qr"), // 支付宝扫码支付
        ALIPAY_PC("alipay_pc_direct"), // 支付宝及时到账支付
        WX_PUB("wx_pub"), WX_WAP("wx_wap"); // 微信公众号支付,微信h5支付
        public String value;

        private PayChannel(String value) {
            this.value = value;
        }
    }
}

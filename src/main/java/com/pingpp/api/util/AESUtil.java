package com.pingpp.api.util;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/** 
 * @author  fuzhuan e-mail: 676646535@qq.com
 * @date 2016年10月12日 
 */
public class AESUtil {


    /** 
     * 加密数据
     * @param data 待加密数据
     * @param key 密钥
     * @return 加密后的数据 
     */
    public static String encrypt(String data, String key) throws Exception {

    	  KeyGenerator kgen = KeyGenerator.getInstance("AES");  
          kgen.init(128, new SecureRandom(key.getBytes()));  
          SecretKey secretKey = kgen.generateKey();  
          byte[] enCodeFormat = secretKey.getEncoded();  
          SecretKeySpec keySpec = new SecretKeySpec(enCodeFormat, "AES");  
          Cipher cipher = Cipher.getInstance("AES");// 创建密码器   
          byte[] byteContent = data.getBytes("utf-8");  
          cipher.init(Cipher.ENCRYPT_MODE, keySpec);// 初始化   
          byte[] result = cipher.doFinal(byteContent);  
        // 执行加密操作。加密后的结果通常都会用Base64编码进行传输 
        return Base64.encodeBase64String(result);
    }

    /** 
     * 解密数据 
     * @param data 待解密数据 
     * @param key 密钥 
     * @return 解密后的数据 
     */
    public static String decrypt(String data, String key) throws Exception {
    	
    	   KeyGenerator kgen = KeyGenerator.getInstance("AES");  
           kgen.init(128, new SecureRandom(key.getBytes()));  
           SecretKey secretKey = kgen.generateKey();  
           byte[] enCodeFormat = secretKey.getEncoded();  
           SecretKeySpec keySpec = new SecretKeySpec(enCodeFormat, "AES");              
           Cipher cipher = Cipher.getInstance("AES");// 创建密码器   
           cipher.init(Cipher.DECRYPT_MODE, keySpec);// 初始化   
           byte[] result = cipher.doFinal(Base64.decodeBase64(data));  
           // 执行解密操作
           return new String(result);
    }

    public static void main(String[] args) throws Exception {
        String source = "HDvqi10LCBr5E/zf7Ls3mG8QR2ieL7AeFg5TSsG84G62IlIbinY9LiQ6tTpTeJ7yVUp8D/9KYTSn67KKIvn3M6UoU3xOPfaLdd9uXAFnT1DSOvc+qRRNJckQHeTtxiKnzNtmCATF1fLWHyKJ3bI4Mc2BS3rK/qqqrANsiPhlKo/YeTv2S5OBSQSuIKaH0ct8mngfOKf0a6mtQRNtyODTsdO5N99UZdQZ6q6Xq/jdwWQvKhC9pmCr1HZyRMoV60+Ad0oA4EPp1pNimOvvhJWPghe9TW+hrv7oZdfTXUBnMrFHTQwagmjHGsuhiwzXyi3hIBFeR3ncD53an/JgpzO0jHV3zfEzNDoBgqMAZTvpfmEuEiJOcjnKbo/yuCYHgDocd5iQjqCQxvGZEhNBgkmvzMl2BxUfQ9jD/q7CdZr6ztqOAO3nEuzKSWoseZEodWtPgXEdj+sRhk+bbkQ/d/mAtR85LgsDjm73uLa1KbQEYai6bsIvBxBm1qe9YBa+QSqp3rLtszuxb+SC+cowEavuoU9uFzE+WrYMi9iJBuJEwb2bi5ZTgCPP/g0ZFSUlEWe/gKZzaS9E5tJQ/xzZEjc51mbMEAC+aJFlY0Oq786f1VfPIUMMC22aU4t7kHMxCXYxl0QXBm85FQjTwukbwLSK676WXrMrtshBqpC71FXCly4VHdhVUwBRdpxRK46pje4jEqspdu4xiylcmJKSXioe91Xx8LItom0xc5ZFxjJagV28JogQ5X8+F4FiDD+d9TzXAlsKVczht/nhSCIdyFfxg0ABlvW+ebIrcq9BNhugOE5EIue9LQFjcdjkzMOVDJrcY48JDWBLGsmEtG7+9WKjjww9hLuVZTMj4gthJhFbOD+exLp/iDdRKsFuNxvVohiF1shlu61B6akYmf0PUrCkUXV4ODkNfeeaRGku8yjib0gtMg5tRLRL0Wy6Y8xCcsXzB0C9QEwCQ+sDIq1BKlS8JkgEjVEF2IMkY/5cn9oWhJGFTsDEaJdk9QE14Vw97nuUZdSq9YagD6S4X4NQgbhgdOCTi772SQ98VhLMyBcJhF4BZ+/3Zp17u8UsqPslP0AgV5WbxvWMeE01RcC0+8ESnrQdA/8DZOiMk63hx14odsYyNkKXQTUSE9IpfsUGzDpcSSFW5dF/xinCCCgdM9eH+mmgjxJXSaUNksm4Pxeg/uxZ/Oidyrmk8mb2I+Kds5pGJf5l89Y0m7/dhDXuWwVS7xsSTqE32vTl6ti24EtjXDqnk63BDO8Eh3czGq5mBSC/xMv0QLWXslcPUcj1pXfCiaVnlLFsVs7+8J4sPuqN1S1oF2QZLgCZH4kzpmQg6W8sdr78bG1aZyggfV7AAhDkV91d1CvtbVn+GW6VUN5+qQFAUfPhdEIZ+aDBCdt6ZeqEqBc/fS71T1SK+Qqz5n02mTAjhpV/6ZMSz4zbpZoxn0GY4niX34SGG/L4VVgg6ib+DgZ/TDL9ptjtsegrTd97lXCTZ/RU5tZv6PUQuq88aHXeZCDP/9P+vOCKus9Zt5Y4Iaml60JBSX+jHI5QdsSj8pCYp1OiBE/gDITqmeSF9/wdrnBcq5em1Wbc0DCdfRHZNlKzgC7i3KkoH5bSfnPF2w39IlGwES7N4dDos+LyHlvZgjl0JF569PtoCyVvkFBMD2gGDoRZT4qX+5uI9T7c4j+YeTWNT+egBcIS3RN6sIJaG1dZweWCPNiMZBOOK/1URUTVx4DIR+YUU4kxKxjVfjjsAv/iZRrr0OO4bbCXVKMZT2jDfYgrIFfY0qib7JXnAz1Gk6blA7dUMhd6CBe1nhUqkvhNNJQWgixFIbOpLL9ZC3CXBkYaiNZpWEBDfhc4rERtAHmwK+VN2yaK9+jjQ==";
        System.out.println("原文: " + source);
        String key = "MYgGnQE2+DAS973vd1DFHg==";
        String encryptData = encrypt(source, key);
        System.out.println("加密后: " + encryptData);
        String decryptData = decrypt(source, key);
        System.out.println("解密后: " + decryptData);
    }
}

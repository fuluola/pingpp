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
        String source = "fuluola";
        System.out.println("原文: " + source);
        String key = "A1B2C3D4";
        String encryptData = encrypt(source, key);
        System.out.println("加密后: " + encryptData);
        String decryptData = decrypt(encryptData, key);
        System.out.println("解密后: " + decryptData);
    }
}

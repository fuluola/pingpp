package com.pingpp.api.util;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;

/** 
 * @author  fuzhuan e-mail:676646535@qq.com
 * @date 2016年9月18日 
 */
public class PropertiesUtil {
	
	private static Properties properties = new Properties();
	
	static{
		ClassPathResource resource = new ClassPathResource("/conf/pingpp.properties");
		try {
			properties.load(resource.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getSecretKey(){
		return properties.getProperty("secretKey");
	}
}

package com.pingpp.api.util;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
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

	/**
	 * @date 2016年9月22日上午8:01:27
	 * @author fuzhuan
	 * @return
	 * 
	 */
	public static String getLiveApikey() {
		return properties.getProperty("live.apiKey");
	}

	/**
	 * @date 2016年9月22日上午8:02:33
	 * @author fuzhuan
	 * @return
	 * 
	 */
	public static String getTestApikey() {
		return properties.getProperty("test.apiKey");
	}

	public static List<String> getPartnerId() {
		String partnerId = properties.getProperty("partner.id");
		String[] ids = partnerId.split(",");
		return Arrays.asList(ids);
	}
}

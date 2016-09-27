package com.pingpp.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/** 
 * @author  fuzhuan e-mail: 676646535@qq.com
 * @date 2016年9月27日 
 */
public class CommonsLogTest {

	private static Log log = LogFactory.getLog(CommonsLogTest.class);

	 public static void main(String[] args) {
	  log.error("ERROR");
	  log.debug("DEBUG");
	  log.warn("WARN");
	  log.info("INFO");
	  log.trace("TRACE");
	  System.out.println(log.getClass());
	 }
}

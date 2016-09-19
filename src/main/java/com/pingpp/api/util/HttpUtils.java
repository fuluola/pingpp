package com.pingpp.api.util;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

/** 
 * @author  fuzhuan e-mail: 676646535@qq.com
 * @date 2016年9月19日 
 */
public class HttpUtils {

	public static final String sendRequest(String url, NameValuePair[] paramsList, String encoding, int timeout)
		    throws IOException {
		 
		    if ((url == null) || ("".equals(url))) {
		      throw new NullPointerException("url empty");
		    }
		    if (paramsList == null) {
		      throw new NullPointerException("paramsList empty");
		    }
		    if ((encoding == null) || ("".equals(encoding))) {
		      encoding = "UTF-8";
		    }
		    if (timeout <= 0) {
		      timeout = 5000;
		    }

		    PostMethod postMethod = new PostMethod(url);
		    postMethod.getParams().setContentCharset(encoding);
		    postMethod.getParams().setHttpElementCharset(encoding);
		    postMethod.setRequestBody(paramsList);

		    HttpClient httpClient = new HttpClient();
		    httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(timeout);
		    httpClient.getHttpConnectionManager().getParams().setSoTimeout(timeout);
		    try
		    {
		      httpClient.executeMethod(postMethod);
		      if (postMethod.getStatusCode() == 200) {
		        return postMethod.getResponseBodyAsString();
		      }
		      throw new IllegalStateException("sendRequest remote error");
		    } finally {
		      postMethod.releaseConnection();
		    }
		  }
}

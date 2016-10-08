package com.pingpp.api.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

	public static Log logger = LogFactory.getLog(TestController.class);
	@RequestMapping(value="test")
	@ResponseBody
	public String test(HttpServletRequest request, HttpServletResponse response){
	
		 response.setStatus(200);
		 return "欢迎来到PINGXX服务端";
	}
}

package com.pingpp.api.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pingpp.api.model.ResponseMessage;

@Controller
public class TestController {

	@RequestMapping(value="")
	@ResponseBody
	public String test(HttpServletRequest request, HttpServletResponse response){
	
		 response.setStatus(200);
		 return "欢迎来到PINGXX服务端";
	}
}

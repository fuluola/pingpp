package com.pingpp.api.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pingpp.api.model.ResponseMessage;

@Controller
public class TestController {

	@RequestMapping(value="test")
	@ResponseBody
	public Object test(HttpServletRequest request, HttpServletResponse response){
	
		 response.setStatus(200);
		 return new ResponseMessage("0","接受状态成功",null);
	}
}

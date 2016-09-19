package com.pingpp.api.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

	@RequestMapping(value="test")
	public void test(HttpServletRequest request, HttpServletResponse response){
	
		 response.setStatus(500);
	}
}

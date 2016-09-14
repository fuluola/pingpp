package com.pingpp.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

	@RequestMapping(value="test")
	public void test(String name,Model model){
		model.addAttribute("welcome", "hello,xxx");
		System.out.println("OKs");
	}
}

package com.example.productmanagement.system.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public ModelAndView homePage() {
		ModelAndView model=new ModelAndView();
		model.setViewName("home");
		model.addObject("pageTitle", "Home | BookStore");
		return model;
	}
}

package com.gui.adminpanel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
	
	@GetMapping({"", "home", "index"})
	public ModelAndView homePage() {
		ModelAndView modelAndView = new ModelAndView("index");
		return modelAndView;
	}
	
}

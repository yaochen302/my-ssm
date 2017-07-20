package com.ry.module.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home")
public class HomeController {

	@RequestMapping("index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("home/index");
		return mv;
	}
}

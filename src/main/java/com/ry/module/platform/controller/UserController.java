package com.ry.module.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.ry.common.base.BaseController;
import com.ry.common.base.BaseResult;
import com.ry.module.platform.service.UserService;

@Controller
@RequestMapping("/test")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;

	@RequestMapping("/welcome")
	public ModelAndView getIndex() {
		ModelAndView mav = new ModelAndView("platform/login");
		return mav;
	}

	@RequestMapping("/login")
	@ResponseBody
	public BaseResult login(String user_name, String user_password) {
		return userService.CheckUser(user_name, user_password);
	}
	
	@RequestMapping("/timeout")
	public ModelAndView timeout() {
		ModelAndView mav = new ModelAndView("index");
		return mav;
	}
}
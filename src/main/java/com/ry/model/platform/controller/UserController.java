package com.ry.model.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ry.common.base.BaseController;
import com.ry.common.base.BaseResult;
import com.ry.model.platform.entity.User;
import com.ry.model.platform.service.UserService;

@Controller
@RequestMapping("/test")
public class UserController extends BaseController{  

    @Autowired
    private UserService userService;  

    @RequestMapping("/welcome")    
    public ModelAndView getIndex(){
        ModelAndView mav = new ModelAndView("index");
        User user = userService.selectUserById(1);
        System.out.println(11111);
        mav.addObject("user", user);   
        return mav;    
    }
    
    @RequestMapping("/login")
    @ResponseBody
    public BaseResult login(String user_name, String user_password){
       return userService.CheckUser(user_name,user_password);  
    }
}  
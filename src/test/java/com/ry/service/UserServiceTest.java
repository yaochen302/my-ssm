package com.ry.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.ry.baseTest.SpringTestCase;
import com.ry.model.platform.entity.User;
import com.ry.model.platform.service.UserService;

public class UserServiceTest extends SpringTestCase {

    @Autowired  
    private UserService userService; 

    @Test  
    public void selectUserByIdTest(){  
        User user = userService.selectUserById(1);  
        System.out.println(user.getUserName() + ":" + user.getUserPassword());
    }  
}
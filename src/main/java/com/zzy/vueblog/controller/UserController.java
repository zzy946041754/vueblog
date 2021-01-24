package com.zzy.vueblog.controller;


import com.zzy.vueblog.common.Result;
import com.zzy.vueblog.entity.User;
import com.zzy.vueblog.service.UserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }
     @RequestMapping("/{id}")
     public Result getUserById(@PathVariable Long id){
         User user = userService.getById(id);
         return Result.succ(user);
     }
}

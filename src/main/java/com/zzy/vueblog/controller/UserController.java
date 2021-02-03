package com.zzy.vueblog.controller;


import com.zzy.vueblog.common.Result;
import com.zzy.vueblog.entity.User;
import com.zzy.vueblog.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }
     @GetMapping("/getUser/{id}")
     @RequiresAuthentication
     public Result getUserById(@PathVariable Long id){
         User user = userService.getById(id);
         return Result.succ(user);
     }
    @PostMapping("/save")
    public Result save( @Validated @RequestBody User user){
        return Result.succ(user);
    }
}

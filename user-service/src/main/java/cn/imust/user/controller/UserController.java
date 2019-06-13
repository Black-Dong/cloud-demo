package cn.imust.user.controller;

import cn.imust.user.pojo.User;
import cn.imust.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("{id}")
    public User hello(@PathVariable("id")Long id){
        User user = userService.queryById(id);
        return userService.queryById(id);
    }
}
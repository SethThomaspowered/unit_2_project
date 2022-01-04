package com.example.ultimateplaylist.controller;

import com.example.ultimateplaylist.model.User;
import com.example.ultimateplaylist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/auth/users")
public class UserController{
    private UserService userService;
    private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }
    @PostMapping("/login")
    public User createUser(@RequestBody User userObject){
        LOGGER.info("calling createUser method from controller");
        return userService.createUser(userObject);
    }
}

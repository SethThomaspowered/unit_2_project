package com.example.ultimateplaylist.controller;

import com.example.ultimateplaylist.model.Request.LoginRequest;
import com.example.ultimateplaylist.model.User;
import com.example.ultimateplaylist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/register")
    public User createUser(@RequestBody User userObject){
        LOGGER.info("calling createUser method from controller");
        return userService.createUser(userObject);
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest){
        LOGGER.info("calling loginUser method from controller");
        return userService.loginUser(loginRequest);
    }
//    @PutMapping("/{userId}")
//    public User updateUsername(@PathVariable(value="userId") Long userId, @RequestParam String newUsername){
//        LOGGER.info("calling updateUsername method from controller");
//        return userService.updateUsername(userId, newUsername);
//    }
}

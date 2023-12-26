package com.loginAuthentication.auth.controller;

import com.loginAuthentication.auth.dto.UserDto;
import com.loginAuthentication.auth.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }
}
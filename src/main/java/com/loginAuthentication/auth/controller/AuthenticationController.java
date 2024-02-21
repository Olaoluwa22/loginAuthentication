package com.loginAuthentication.auth.controller;

import com.loginAuthentication.auth.dto.LoginDto;
import com.loginAuthentication.auth.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class AuthenticationController {
    @Autowired
    private UserService userService;

    @GetMapping("/user-dashboard")
    public String userDashboard(){
        return "Welcome to your User Dashboard " + userService.getLoggedInUser();
    }
    @GetMapping("/admin-dashboard")
    public String adminDashboard(){
        return "Welcome to your Admin Dashboard " + userService.getLoggedInUser();
    }
}

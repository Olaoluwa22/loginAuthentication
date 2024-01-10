package com.loginAuthentication.auth.controller;

import com.loginAuthentication.auth.dto.LoginDto;
import com.loginAuthentication.auth.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authorization.AuthorityReactiveAuthorizationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private AuthenticationService authenticationService;
    public AuthenticationController(AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }
    @GetMapping("/demo")
    @PreAuthorize(value = "hasAuthority('read') OR hasRole('ADMIN')")
    public String demo(){
        var c = SecurityContextHolder.getContext().getAuthentication();

        return "Demo";
    }
    @GetMapping("/test")
    @PreAuthorize(value = "hasAuthority('write') OR hasRole('CLIENT')")
    public String test(){
        var c = SecurityContextHolder.getContext().getAuthentication();

        return "Test";
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto, HttpServletResponse response, HttpServletRequest request){
        return authenticationService.login(loginDto, response, request);
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response){
        return authenticationService.logout(request, response);
    }
}

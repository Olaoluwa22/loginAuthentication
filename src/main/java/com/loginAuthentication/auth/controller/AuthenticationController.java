package com.loginAuthentication.auth.controller;

import com.loginAuthentication.auth.dto.LoginDto;
import com.loginAuthentication.auth.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private AuthenticationService authenticationService;
    public AuthenticationController(AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto){
        return authenticationService.login(loginDto);
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logout(){
        return authenticationService.logout();
    }
}

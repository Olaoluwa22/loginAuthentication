package com.loginAuthentication.auth.service;

import com.loginAuthentication.auth.dto.LoginDto;
import com.loginAuthentication.auth.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    public ResponseEntity<?> login(LoginDto loginDto, HttpServletResponse response, HttpServletRequest request);
    public ResponseEntity<?> logout(HttpServletRequest request,  HttpServletResponse response);
}

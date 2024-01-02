package com.loginAuthentication.auth.service;

import com.loginAuthentication.auth.dto.LoginDto;
import com.loginAuthentication.auth.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

public interface AppSecurityService {
    public ResponseEntity<?> login(User user, HttpServletResponse response, HttpServletRequest request);
    public ResponseEntity<?> logout(HttpServletRequest request,  HttpServletResponse response);
    public ResponseEntity<?> isUserLoggedIn(HttpServletRequest request);
}
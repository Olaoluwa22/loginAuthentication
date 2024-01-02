package com.loginAuthentication.auth.service;

import com.loginAuthentication.auth.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

public interface CookieAuthenticationService {
    public ResponseEntity<?> login(User user, HttpServletResponse response);
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response);
    public ResponseEntity<?> isUserLoggedIn(HttpServletRequest request);
}

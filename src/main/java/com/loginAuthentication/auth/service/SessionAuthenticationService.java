package com.loginAuthentication.auth.service;

import com.loginAuthentication.auth.model.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface SessionAuthenticationService {
    public ResponseEntity<?> login(User user, HttpServletRequest request);
    public ResponseEntity<?> logout(HttpServletRequest request);
    public ResponseEntity<?> isUserLoggedIn(HttpServletRequest request);
}

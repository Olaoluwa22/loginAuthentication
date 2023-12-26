package com.loginAuthentication.auth.service;

import com.loginAuthentication.auth.dto.LoginDto;
import com.loginAuthentication.auth.dto.UserDto;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    public ResponseEntity<?> login(LoginDto loginDto);
    public ResponseEntity<?> logout();
}

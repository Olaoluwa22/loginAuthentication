package com.loginAuthentication.auth.service;

import com.loginAuthentication.auth.dto.UserDto;
import org.springframework.http.ResponseEntity;

public interface UserService {
    public ResponseEntity<?> createUser(UserDto userDto);
    public String getLoggedInUser();
}

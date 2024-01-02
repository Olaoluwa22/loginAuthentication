package com.loginAuthentication.auth.serviceImpl;

import com.loginAuthentication.auth.constant.ConstantMessage;
import com.loginAuthentication.auth.dto.LoginDto;
import com.loginAuthentication.auth.exception.exceptionHandler.UserNotFoundException;
import com.loginAuthentication.auth.exception.exceptionHandler.UserNotLoggedInException;
import com.loginAuthentication.auth.model.User;
import com.loginAuthentication.auth.repository.UserRepository;
import com.loginAuthentication.auth.response.ApiResponseMessage;
import com.loginAuthentication.auth.service.AppSecurityService;
import com.loginAuthentication.auth.service.CookieAuthenticationService;
import com.loginAuthentication.auth.service.SessionAuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.IllegalFormatCodePointException;
import java.util.Optional;
@Service
public class AppSecurityServiceImpl implements AppSecurityService {
    private UserRepository userRepository;
    private CookieAuthenticationService cookieAuthenticationService;
    private SessionAuthenticationService sessionAuthenticationService;
    public AppSecurityServiceImpl(UserRepository userRepository, CookieAuthenticationService cookieAuthenticationService, SessionAuthenticationService sessionAuthenticationService) {
        this.userRepository = userRepository;
        this.cookieAuthenticationService = cookieAuthenticationService;
        this.sessionAuthenticationService = sessionAuthenticationService;
    }
    @Value("${app.authType}")
    private String authType;
    @Override
    public ResponseEntity<?> login(User user, HttpServletResponse response, HttpServletRequest request) {
        if (authType.equalsIgnoreCase("cookie")) {
            cookieAuthenticationService.login(user, response);
        } else if (authType.equalsIgnoreCase("session")) {
            sessionAuthenticationService.login(user, request);
        } else {
            throw new UserNotFoundException("User not found");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @Override
    public ResponseEntity<?> logout(HttpServletRequest request,  HttpServletResponse response) {
        if (authType.equalsIgnoreCase("cookie")){
            cookieAuthenticationService.logout(request, response);
        } else if (authType.equalsIgnoreCase("session")) {
            sessionAuthenticationService.logout(request);
        }else{
            throw new UserNotLoggedInException("User not logged in...");
        }
        ApiResponseMessage<String> apiResponseMessage = new ApiResponseMessage<>();
        apiResponseMessage.setMessage(ConstantMessage.LOGOUT_SUCCESS.getMessage());
        return new ResponseEntity<>(apiResponseMessage,HttpStatus.OK);
    }
    @Override
    public ResponseEntity<?> isUserLoggedIn(HttpServletRequest request) {
        if (authType.equalsIgnoreCase("cookie")){
            cookieAuthenticationService.isUserLoggedIn(request);
        } else if (authType.equalsIgnoreCase("session")) {
            sessionAuthenticationService.isUserLoggedIn(request);
        }else{
            throw new UserNotLoggedInException("User not logged in...");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
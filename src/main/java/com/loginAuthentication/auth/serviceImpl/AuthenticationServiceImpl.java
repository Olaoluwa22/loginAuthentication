package com.loginAuthentication.auth.serviceImpl;

import com.loginAuthentication.auth.constant.ConstantMessage;
import com.loginAuthentication.auth.dto.LoginDto;
import com.loginAuthentication.auth.model.User;
import com.loginAuthentication.auth.repository.UserRepository;
import com.loginAuthentication.auth.response.ApiResponseMessage;
import com.loginAuthentication.auth.service.AppSecurityService;
import com.loginAuthentication.auth.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jdk.jfr.Frequency;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private UserRepository userRepository;
    private AppSecurityService appSecurityService;
    public AuthenticationServiceImpl(UserRepository userRepository, AppSecurityService appSecurityService) {
        this.userRepository = userRepository;
        this.appSecurityService = appSecurityService;
    }
    @Override
    public ResponseEntity<?> login(LoginDto loginDto, HttpServletResponse response, HttpServletRequest request) {
        ApiResponseMessage<String> apiResponseMessage = new ApiResponseMessage<>();
        apiResponseMessage.setMessage(ConstantMessage.FAILED.getMessage());
        User byEmail = userRepository.findByEmail(loginDto.getLogin());
        try {
            if (byEmail ==null) {
                apiResponseMessage.setMessage(ConstantMessage.INCORRECT.getMessage());
                return new ResponseEntity<>(apiResponseMessage, HttpStatus.BAD_REQUEST);
            }
            User user = byEmail;
            if (!loginDto.getPassword().equals(user.getPassword())) {
                apiResponseMessage.setMessage(ConstantMessage.INCORRECT.getMessage());
                return new ResponseEntity<>(apiResponseMessage, HttpStatus.BAD_REQUEST);
            }
            appSecurityService.login(user, response, request);
            apiResponseMessage.setMessage(ConstantMessage.LOGIN_SUCCESS.getMessage());
            return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(apiResponseMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @Override
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        appSecurityService.isUserLoggedIn(request);
        return appSecurityService.logout(request, response);
    }
}

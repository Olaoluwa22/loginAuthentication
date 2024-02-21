package com.loginAuthentication.auth.serviceImpl;

import com.loginAuthentication.auth.controller.AuthenticationController;
import com.loginAuthentication.auth.util.ConstantMessage;
import com.loginAuthentication.auth.dto.UserDto;
import com.loginAuthentication.auth.model.User;
import com.loginAuthentication.auth.repository.UserRepository;
import com.loginAuthentication.auth.response.ApiResponseMessage;
import com.loginAuthentication.auth.service.MailNotificationService;
import com.loginAuthentication.auth.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    private MailNotificationService mailNotificationService;
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, MailNotificationService mailNotificationService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailNotificationService = mailNotificationService;
    }
    @Override
    public ResponseEntity<?> createUser(UserDto userDto) {
        ApiResponseMessage<String> apiResponseMessage = new ApiResponseMessage<>();
        apiResponseMessage.setMessage(ConstantMessage.FAILED.getMessage());
            User user = new User();
            user.setEmail(userDto.getEmail());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setRole(userDto.getRole());
        try {
            User byEmail = userRepository.findByEmail(user.getEmail());
            if (!(byEmail == null)) {
                apiResponseMessage.setMessage(ConstantMessage.USER_EXISTS.getMessage());
                return new ResponseEntity<>(apiResponseMessage, HttpStatus.BAD_REQUEST);
            }
            userRepository.save(user);
            apiResponseMessage.setMessage(ConstantMessage.CREATED.getMessage());
            return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(apiResponseMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @Override
    public String getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null & authentication.getPrincipal() instanceof UserDetails){
            return ((UserDetails) authentication.getPrincipal()).getUsername();
        }
        return "INTERNAL SERVER ERROR...";
    }
}
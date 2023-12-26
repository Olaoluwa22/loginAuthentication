package com.loginAuthentication.auth.serviceImpl;

import com.loginAuthentication.auth.constant.ConstantMessage;
import com.loginAuthentication.auth.dto.LoginDto;
import com.loginAuthentication.auth.model.User;
import com.loginAuthentication.auth.repository.UserRepository;
import com.loginAuthentication.auth.response.ApiResponseMessage;
import com.loginAuthentication.auth.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private UserRepository userRepository;
    public AuthenticationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<?> login(LoginDto loginDto) {
        ApiResponseMessage<String> apiResponseMessage = new ApiResponseMessage<>();
        apiResponseMessage.setMessage(ConstantMessage.FAILED.getMessage());
        Optional<User> byEmail = userRepository.findByEmail(loginDto.getLogin());
        try {
            if (byEmail.isEmpty()) {
                apiResponseMessage.setMessage(ConstantMessage.NOT_FOUND.getMessage());
                return new ResponseEntity<>(apiResponseMessage, HttpStatus.BAD_REQUEST);
            }
            User user = byEmail.get();
            if (!loginDto.getPassword().equals(user.getPassword())) {
                apiResponseMessage.setMessage(ConstantMessage.INCORRECT.getMessage());
                return new ResponseEntity<>(apiResponseMessage, HttpStatus.BAD_REQUEST);
            }
            apiResponseMessage.setMessage(ConstantMessage.LOGIN_SUCCESS.getMessage());
            return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(apiResponseMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> logout() {
        return null;
    }
}

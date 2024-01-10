package com.loginAuthentication.auth.serviceImpl;

import com.loginAuthentication.auth.constant.ConstantMessage;
import com.loginAuthentication.auth.dto.UserDto;
import com.loginAuthentication.auth.model.User;
import com.loginAuthentication.auth.repository.UserRepository;
import com.loginAuthentication.auth.response.ApiResponseMessage;
import com.loginAuthentication.auth.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public ResponseEntity<?> createUser(UserDto userDto) {
        ApiResponseMessage<String> apiResponseMessage = new ApiResponseMessage<>();
        apiResponseMessage.setMessage(ConstantMessage.FAILED.getMessage());
            User user = new User();
            user.setName(userDto.getName());
            user.setEmail(userDto.getEmail());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
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
}
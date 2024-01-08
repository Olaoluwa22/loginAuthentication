package com.loginAuthentication.auth.serviceImpl;

import com.loginAuthentication.auth.constant.ConstantMessage;
import com.loginAuthentication.auth.exception.exceptionHandler.UserNotLoggedInException;
import com.loginAuthentication.auth.model.User;
import com.loginAuthentication.auth.repository.UserRepository;
import com.loginAuthentication.auth.response.ApiResponseMessage;
import com.loginAuthentication.auth.service.CookieAuthenticationService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CookieAuthenticationServiceImpl implements CookieAuthenticationService {
    private UserRepository userRepository;
    public CookieAuthenticationServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public ResponseEntity<?> login(User user, HttpServletResponse response) {
        Cookie cookie = new Cookie("login",user.getEmail());
        cookie.setPath("/");
        cookie.setMaxAge(600);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return  new ResponseEntity<>(HttpStatus.OK);
    }
    @Override
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        ApiResponseMessage<String> apiResponseMessage = new ApiResponseMessage<>();
        apiResponseMessage.setMessage(ConstantMessage.FAILED.getMessage());
        try {
            Cookie[] cookie = request.getCookies();
            if (cookie == null){
                return new ResponseEntity<>(apiResponseMessage, HttpStatus.UNAUTHORIZED);
            }
            for (int i = 0; i < cookie.length; i++) {
                Cookie cookie1 = cookie[i];
                if (cookie1.getName().equals("login")) {
                    cookie1.setMaxAge(0);
                    response.addCookie(cookie1);
                    break;
                }
            }
            apiResponseMessage.setMessage(ConstantMessage.LOGOUT_SUCCESS.getMessage());
            return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(apiResponseMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @Override
    public ResponseEntity<?> isUserLoggedIn(HttpServletRequest request) {
        ApiResponseMessage<String> apiResponseMessage = new ApiResponseMessage<>();
        apiResponseMessage.setMessage(ConstantMessage.FAILED.getMessage());
        try {
            Cookie[] cookie = request.getCookies();
            boolean loggedIn = false;
            Cookie loginCookie = null;
            for (int i = 0; i < cookie.length; i++) {
                Cookie cookie1 = cookie[i];
                if (cookie1.getName().equals("login")) {
                    loggedIn = true;
                    loginCookie = cookie1;
                    break;
                }
            }
            if (!loggedIn) {
                apiResponseMessage.setMessage(ConstantMessage.USER_NOT_LOGGED_IN.getMessage());
                throw new UserNotLoggedInException("User not logged in...");
            }
            User cookieOwner = userRepository.findByEmail(loginCookie.getValue());
            if (cookieOwner == null) {
                throw new UserNotLoggedInException("User not logged in...");
            }
            apiResponseMessage.setMessage(ConstantMessage.SUCCESS.getMessage());
            return new ResponseEntity<>(apiResponseMessage,HttpStatus.OK);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        throw new UserNotLoggedInException("User not logged in...");
    }
}

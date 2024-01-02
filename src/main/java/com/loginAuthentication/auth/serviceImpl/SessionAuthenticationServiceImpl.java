package com.loginAuthentication.auth.serviceImpl;

import com.loginAuthentication.auth.dto.LoginDto;
import com.loginAuthentication.auth.exception.exceptionHandler.UserNotLoggedInException;
import com.loginAuthentication.auth.model.User;
import com.loginAuthentication.auth.repository.UserRepository;
import com.loginAuthentication.auth.service.SessionAuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SessionAuthenticationServiceImpl implements SessionAuthenticationService {
    private UserRepository userRepository;
    public SessionAuthenticationServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public ResponseEntity<?> login(User user, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("session", user.getEmail());
        session.setAttribute("name",user.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @Override
    public ResponseEntity<?> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null){
            throw new UserNotLoggedInException("User not logged In...");
        }
        session.invalidate();
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @Override
    public ResponseEntity<?> isUserLoggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null){
            throw new UserNotLoggedInException("User Not logged in...");
        }
        Object loginSession = session.getAttribute("session");
        if (loginSession == null){
            throw new UserNotLoggedInException("User not logged in...");
        }
        String login = String.valueOf(loginSession);
        Optional<User> sessionOwner = userRepository.findByEmail(login);
        if (sessionOwner.isEmpty()){
            throw new UserNotLoggedInException("User not logged in...");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
package com.loginAuthentication.auth.exception.exceptionHandler;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message){
        super(message);
    }
}

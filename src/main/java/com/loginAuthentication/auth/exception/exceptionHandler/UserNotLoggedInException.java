package com.loginAuthentication.auth.exception.exceptionHandler;

public class UserNotLoggedInException extends RuntimeException {
    public UserNotLoggedInException(String message){
        super(message);
    }
}

package com.loginAuthentication.auth.util;

public enum ConstantMessage {
    SUCCESS("Successful", 1),
    FAILED("Failed", 2),
    NOT_FOUND("Cannot be found", 3),
    INCORRECT_DETAILS("Incorrect Email or Password", 4),
    LOGIN_SUCCESS("Successfully Logged In...", 5),
    USER_EXISTS("User Already Exist...", 6),
    CREATED("Successfully Created", 7),
    USER_NOT_LOGGED_IN("User not Logged In...", 8),
    LOGOUT_SUCCESS("Successfully Logged out...", 9);
    private String message;
    private int status;

    ConstantMessage(String message, int status){
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ConstantMessage{" +
                "message='" + message + '\'' +
                ", status=" + status +
                '}';
    }
}

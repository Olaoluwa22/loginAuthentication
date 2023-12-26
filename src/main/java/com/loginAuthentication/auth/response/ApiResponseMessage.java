package com.loginAuthentication.auth.response;

public class ApiResponseMessage<G> {
    private String message;
    private String data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ApiResponseMessage{" +
                "message='" + message + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}

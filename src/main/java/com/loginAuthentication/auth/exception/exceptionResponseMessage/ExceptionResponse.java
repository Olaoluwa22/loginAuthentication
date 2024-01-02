package com.loginAuthentication.auth.exception.exceptionResponseMessage;

import java.time.Instant;
import java.util.List;

public class ExceptionResponse<G> {
    private String message;
    private Instant timestamp;
    private Integer status;
    private List<G> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<G> getData() {
        return data;
    }

    public void setData(List<G> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ExceptionResponse{" +
                "message='" + message + '\'' +
                ", timestamp=" + timestamp +
                ", status=" + status +
                ", data=" + data +
                '}';
    }
}

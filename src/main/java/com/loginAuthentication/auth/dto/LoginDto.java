package com.loginAuthentication.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class LoginDto {
    @NotNull
    @Email
    private String login;
    @NotNull
    @Pattern(regexp = "[a-zA-Z0-9]{8,}")
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginDto{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

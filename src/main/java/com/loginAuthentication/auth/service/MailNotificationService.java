package com.loginAuthentication.auth.service;

import com.loginAuthentication.auth.model.User;

public interface MailNotificationService {
    public void sendWelcomeEmail(User user);
}
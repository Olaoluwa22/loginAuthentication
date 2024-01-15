package com.loginAuthentication.auth.serviceImpl;

import com.loginAuthentication.auth.model.User;
import com.loginAuthentication.auth.service.MailNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailNotificationServiceImpl implements MailNotificationService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Override
    public void sendWelcomeEmail(User user) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("Welcome");
        simpleMailMessage.setFrom("olaoluwaKay@gmail.com");
        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setText("Welcome "+user.getName());
        try {
            javaMailSender.send(simpleMailMessage);

        }catch(MailException mailException){
            mailException.getMessage();
            return;
        }
        System.out.println("Message Sent Successfully!");
    }
}

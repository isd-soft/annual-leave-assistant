package com.example.demo2.utility;

import com.example.demo2.model.User;
import org.springframework.aop.interceptor.JamonPerformanceMonitorInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(User user) throws MailException {
        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setTo(user.getEmail());
        mail.setFrom("intherintership@gmail.com");
        mail.setSubject("New leave request");
        mail.setText("A new leave request has been posted from" + user.getName());

        javaMailSender.send(mail);
    }
}

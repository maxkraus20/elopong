package com.max.elopong.backend.services.impl;

import com.max.elopong.backend.models.MailEntity;
import com.max.elopong.backend.services.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {

    private JavaMailSender javaMailSender;

    public void sendSimpleMessage(MailEntity mailEntity) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailEntity.getTo());
        message.setFrom("elopong20@gmail.com");
        message.setSubject(mailEntity.getSubject());
        message.setText(mailEntity.getText());

        javaMailSender.send(message);
    }
}

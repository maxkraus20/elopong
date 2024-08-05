package com.max.elopong.backend.services;

import com.max.elopong.backend.models.MailEntity;

public interface EmailService {

    public void sendSimpleMessage(MailEntity mailEntity);
}

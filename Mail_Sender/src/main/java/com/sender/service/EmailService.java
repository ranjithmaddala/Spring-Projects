package com.sender.service;

import com.sender.entity.EmailDetails;

import jakarta.mail.MessagingException;

public interface EmailService {

	String sendSimpleMail(EmailDetails details) throws MessagingException;
    String sendMailWithAttachment(EmailDetails details);
}

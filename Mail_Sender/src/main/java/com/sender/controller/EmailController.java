package com.sender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sender.entity.EmailDetails;
import com.sender.service.EmailService;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/send_mail")
public class EmailController {
	
	@Autowired
	EmailService emailService;
	
	@PostMapping("/sendMail")
    public String sendMail(@RequestBody EmailDetails details) throws MessagingException
    {
        String status= emailService.sendSimpleMail(details);
        return status;
    }
    @PostMapping("/sendMailWithAttachment")
    public String sendMailWithAttachment(@RequestBody EmailDetails details)
    {
        String status = emailService.sendMailWithAttachment(details);
        return status;
    }
}

package com.sender.service;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.sender.entity.EmailDetails;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;




@Service
public class EmailServiceImpl implements EmailService{
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Value("${spring.mail.username}")
	private String senderMail;
	
	Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

	
	//Send an normal email
	public String sendSimpleMail(EmailDetails details) throws MessagingException
    {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		
		logger.info("Trying to send email from : "+senderMail+" to: "+details.getRecipient());
		mailMessage.setFrom(senderMail);
		mailMessage.setTo(details.getRecipient());
		mailMessage.setText(details.getMsgBody());
		mailMessage.setSubject(details.getSubject());
 
		logger.warn("Sending email");
		mailSender.send(mailMessage);
		return "Mail Sent Successfully...";
    }

	//send an email with attachment, so we are using the mime for the multi-media usage purpose
	public String sendMailWithAttachment(EmailDetails details)
    {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
 
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(senderMail);
            mimeMessageHelper.setTo(details.getRecipient());
            mimeMessageHelper.setText(details.getMsgBody());
            mimeMessageHelper.setSubject(
                details.getSubject());
 
            FileSystemResource file = new FileSystemResource(
                    new File(details.getAttachment()));
 
            mimeMessageHelper.addAttachment(file.getFilename(), file);

            mailSender.send(mimeMessage);
            return "Mail sent Successfully";
        }
 
        catch (MessagingException e) {           
            return "Error while sending mail!!!";
        }
    }

}

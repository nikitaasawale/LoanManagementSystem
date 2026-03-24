package com.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
	@Autowired
	public JavaMailSender mailsender;
	
	public void sendMail(String message) {
		SimpleMailMessage mail=new SimpleMailMessage();
		mail.setTo("asawalenikita03@gmail.com");
		mail.setSubject("Loan status update");
		mail.setText(message);
		mailsender.send(mail);

	}

}

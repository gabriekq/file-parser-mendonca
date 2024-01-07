package com.mendonca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Component
public class EmailService implements Runnable {

	@Autowired
	private JavaMailSender javaMailSender ;
	
	private String recipient;
	
	
	public void sendEmail() {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(recipient);

        msg.setSubject("GM - File Parser");
        msg.setText("you just Register an new File into the system");

        javaMailSender.send(msg);

    }

	@Override
	public void run() {
		sendEmail();
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	
	
	
	
}

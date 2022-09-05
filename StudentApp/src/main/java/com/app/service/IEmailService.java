package com.app.service;

import java.io.IOException;

import javax.mail.MessagingException;

public interface IEmailService {

	// send simple email
	void sendSimpleMessage();

	// send email with attachment
	void sendMessageWithAttachment()throws IOException,MessagingException;

}

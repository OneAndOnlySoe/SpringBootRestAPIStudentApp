package com.app.scheduler;

import java.io.IOException;
import java.util.Calendar;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.app.service.IEmailService;

@Component
public class Scheduler {
	
	@Autowired
	private IEmailService emailService;
	
	
	@Scheduled(cron = "0 0 0/8 * * ?")
	public void cronSendMailJobSchduler() throws IOException,MessagingException {
		
		emailService.sendMessageWithAttachment();
		System.out.println("Scheduler activated at " + Calendar.getInstance().getTime().toLocaleString());
	}
}

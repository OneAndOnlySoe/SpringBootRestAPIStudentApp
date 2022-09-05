package com.app.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.app.entity.Student;
import com.app.service.IEmailService;
import com.app.service.IStudentService;
import com.app.service.IGenerateExcelService;

@Service
public class EmailService implements IEmailService {

	@Autowired
	private JavaMailSender emailSender;

	@Autowired
	private IStudentService studentService;

	@Autowired
	private IGenerateExcelService excelService;

	@Value("${spring.mail.username}")
	private String fromEmail;
	@Value("${mail.to}")
	private String to;
	@Value("${mail.subject}")
	private String subject;
	@Value("${mail.message}")
	private String text;

	@Override
	public void sendSimpleMessage() {

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(fromEmail);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		emailSender.send(message);
	}

	@Override
	public void sendMessageWithAttachment() throws IOException, MessagingException {

		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		ByteArrayOutputStream stream = excelService.generateExcelFile(studentService.getAllStudents());

		MimeMessage message = emailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		message.setFrom(fromEmail);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(text);
		ByteArrayResource resource = new ByteArrayResource(stream.toByteArray());

		helper.addAttachment("Student_List_" + currentDateTime + ".xlsx", resource);
		emailSender.send(message);
	}

}

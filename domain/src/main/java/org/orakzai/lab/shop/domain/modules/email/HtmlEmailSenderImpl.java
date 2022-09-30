package org.orakzai.lab.shop.domain.modules.email;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.Map;
import java.util.Properties;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Component
public class HtmlEmailSenderImpl implements HtmlEmailSender {
	
	private static final String CHARSET = "UTF-8";
//	private Configuration freemarkerMailConfiguration;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private TemplateEngine engine;
	
	private EmailConfig emailConfig;
//
	private final static String TEMPLATE_PATH = "email/";
	
	@Override
	public void send(Email email)
			throws Exception {
		
		final String eml = email.getFrom();
		final String from = email.getFromEmail();
		final String to = email.getTo();
		final String subject = email.getSubject();
		final String tmpl = email.getTemplateName();
		final Map<String,String> templateTokens = email.getTemplateTokens();
		
		final MimeMessage mimeMessage = mailSender.createMimeMessage();
		final MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, CHARSET);
		InternetAddress inetAddress = new InternetAddress();
		inetAddress.setPersonal(eml);
		inetAddress.setPersonal(from);
//		messageHelper.setFrom("shah@kmmrce.com");
		messageHelper.setTo(to);
		messageHelper.setSubject(subject);
		final Context ctx = new Context();
		ctx.setVariable("tokens", templateTokens);
		
		final String htmlContent = engine.process(TEMPLATE_PATH + tmpl, ctx);
		System.out.println(htmlContent);
		messageHelper.setText(htmlContent, true);
//		mailSender.send(mimeMessage);
		
	}

//	public Configuration getFreemarkerMailConfiguration() {
//		return freemarkerMailConfiguration;
//	}
//
//	public void setFreemarkerMailConfiguration(Configuration freemarkerMailConfiguration) {
//		this.freemarkerMailConfiguration = freemarkerMailConfiguration;
//	}
//
//	public JavaMailSender getMailSender() {
//		return mailSender;
//	}
//
//	public void setMailSender(JavaMailSender mailSender) {
//		this.mailSender = mailSender;
//	}
//
	public EmailConfig getEmailConfig() {
//		return emailConfig;
		return null;
	}
//
	public void setEmailConfig(EmailConfig emailConfig) {
		this.emailConfig = emailConfig;
	}

}

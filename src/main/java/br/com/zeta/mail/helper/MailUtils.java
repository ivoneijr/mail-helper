package br.com.zeta.mail.helper;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailUtils {


	public static boolean sendMail(String[] to,String[] filenames,
			String subject, String body, final Properties properties) {
			
				Session session = Session.getInstance(properties,
														new javax.mail.Authenticator() {
															@Override
															protected PasswordAuthentication getPasswordAuthentication() {
																return new PasswordAuthentication(properties.getProperty("mail.address"), properties.getProperty("mail.password"));
															}
														});
				session.setDebug(false);
		
				try {
					MimeMessage message = new MimeMessage(session);
					InternetAddress[] address = new InternetAddress[to.length]; 
					
					for (int i = 0; i < to.length; i++) {
						address[i] = new InternetAddress(to[i]);
					}
					
					message.setRecipients(Message.RecipientType.TO, address);
					message.setSubject(subject);
					
					MimeBodyPart messageBody = new MimeBodyPart();
					messageBody.setText(body); 
					
					MimeBodyPart messageFile =null;
					
					Multipart multiPart = new MimeMultipart();
					multiPart.addBodyPart(messageBody);
					
					for (String filename: filenames) {
						FileDataSource fileDataSource = new FileDataSource(filename);
						messageFile = new MimeBodyPart();
						messageFile.setDataHandler(new DataHandler(fileDataSource));
						messageFile.setFileName(fileDataSource.getName());
						multiPart.addBodyPart(messageFile);
					}
					
					message.setContent(multiPart);
					message.setSentDate(new Date());
					
					Transport.send(message);
					
					return true;
					
				} catch (MessagingException e) {
					return false;
				}
	}
}
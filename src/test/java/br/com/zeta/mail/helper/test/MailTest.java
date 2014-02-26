package br.com.zeta.mail.helper.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import br.com.zeta.mail.helper.MailUtils;

public class MailTest {
	
	private Properties properties;
	
	
	@Before
	public void setUp(){
		properties = new Properties();
		
		try {
			properties.load(new FileReader(new File("C:\\Users\\ivonei.marques\\workspace\\mail-helper\\mail.properties")));
	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test() {
		assertNotNull("Não foi possível carregar mail.properties",properties);
		String[] to = new String[] { "carlosrenck@gmail.com",
									 "ivoneijr@gmail.com" };
		String subject = "teste de javaMail";
		String body = "Corpo da mensagem.........";
		String[] filenames = new String[] {"C:\\Users\\ivonei.marques\\workspace\\mail-helper\\anexo.txt",
										   "C:\\Users\\ivonei.marques\\workspace\\mail-helper\\anexo2.txt" };
		assertTrue(MailUtils.sendMail(to, filenames, subject, body, properties));

	}
}

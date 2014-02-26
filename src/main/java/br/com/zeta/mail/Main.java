package br.com.zeta.mail;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

import br.com.zeta.mail.helper.MailUtils;

public class Main {

	public static void main(String[] args) {

		Properties properties = new Properties();

//		try {
//			properties.load(new FileReader(new File("mail.properties")));
//			MailUtils.sendMail(args[0].split(";"), args[1].split(";"), args[2],args[3], properties);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		try {
			properties.load(new FileReader(new File("mail.properties")));
			MailUtils.sendMail(args[0].split(";"), args[1].split(";"), args[2],args[3], properties);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

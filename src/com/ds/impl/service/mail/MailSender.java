package com.ds.impl.service.mail;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.mail.Session;
import java.util.Properties;

/**
 * @author adlakha.vaibhav
 */
@Service
public class MailSender extends JavaMailSenderImpl
{
	@Override
	public void setHost(String host) {
		// TODO Auto-generated method stub
		super.setHost(host);
	}

	@Override
	public void setJavaMailProperties(Properties javaMailProperties) {
		// TODO Auto-generated method stub
		super.setJavaMailProperties(javaMailProperties);
	}

	@Override
	public void setPassword(String password) {
		// TODO Auto-generated method stub
		super.setPassword(password);
	}

	@Override
	public void setPort(int port) {
		// TODO Auto-generated method stub
		super.setPort(port);
	}

	@Override
	public void setProtocol(String protocol) {
		// TODO Auto-generated method stub
		super.setProtocol(protocol);
	}

	@Override
	public synchronized void setSession(Session session) {
		// TODO Auto-generated method stub
		super.setSession(session);
	}

	@Override
	public void setUsername(String username) {
		// TODO Auto-generated method stub
		super.setUsername(username);
	}


}

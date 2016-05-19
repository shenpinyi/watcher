package com.tpg.onewatcher.notification;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import com.tpg.onewatcher.alarmmgt.Alarm;
import com.tpg.onewatcher.configuration.MailMsgSetting;
import com.tpg.onewatcher.configuration.MailSetting;
import com.tpg.onewatcher.configuration.Setting;

@Component
public class EmailNotifier implements Notifier {
	
	private static Logger log = LoggerFactory.getLogger(EmailNotifier.class);
	
	@Autowired
	private Setting setting;
	
	@Override
	public int send(Alarm alarm) {
		
		MailSetting mailSetting = setting.getMail();
		
		List<MailMsgSetting> msgSettings = mailSetting.getMsgs();
		
		if (!mailSetting.getEnable()) {
			return 0;
		}
		
		for (MailMsgSetting msgSetting : msgSettings) {
			if (msgSetting.getAlarmtype() == alarm.getType()) {
				
				try {
					JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
					mailSender.setHost(mailSetting.getHost());
					mailSender.setPort(mailSetting.getPort());
					mailSender.setUsername(mailSetting.getUser());
					mailSender.setPassword(mailSetting.getPassword());
			
					SimpleMailMessage message = new SimpleMailMessage();
					message.setTo(msgSetting.getTo());
					message.setReplyTo(msgSetting.getFrom());
					message.setFrom(msgSetting.getFrom());
					message.setSubject(msgSetting.getSubject());
					message.setText(msgSetting.getContent());
					mailSender.send(message);
					log.info("[0] Alarm successfully sent to " + msgSetting.getTo() + msgSetting.getContent());
				} catch (Exception e) {
					log.error("[0] Alarm failed sent to " + msgSetting.getTo() + msgSetting.getContent());
				}
			}
		}
		return 0;
	}

}

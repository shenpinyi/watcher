package com.tpg.onewatcher.check;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tpg.onewatcher.alarmmgt.Alarm;
import com.tpg.onewatcher.alarmmgt.AlarmMgt;
import com.tpg.onewatcher.configuration.Setting;
import com.tpg.onewatcher.configuration.WebServerSetting;

@Component(value = "httpChecker")
public class HttpChecker implements Checker{
	
	private static Logger log = LoggerFactory.getLogger(HttpChecker.class);

	@Autowired
	private Setting setting;
	
	@Autowired
	private AlarmMgt alarmMgt;
	
	@Override
	public CheckResult check() {
		
		log.debug("Before check, the AlarmMgt is:" + alarmMgt.toString());

		List <WebServerSetting> wss = setting.getWebservers();
		
		for (WebServerSetting ws : wss) {
			
			log.debug("Check WebServer:" + ws.getName());
			Alarm alarm = new Alarm();
			alarm = new Alarm();
			alarm.setAlarmId("WalletServer");
			alarm.setElementId("WalletServer");
			
			String url = ws.getUrl();
			String charset = "UTF-8";

			int status = 0;
			URLConnection connection;

			try {
				connection = new URL(url).openConnection();
				connection.setConnectTimeout(10000); //10 seconds
				connection.setRequestProperty("Accept-Charset", charset);
				@SuppressWarnings("unused")
				InputStream response = connection.getInputStream(); //used to fire the request
				
				HttpURLConnection httpConnection = (HttpURLConnection) connection;
				status = httpConnection.getResponseCode();
				
				if (status == 200) {
					alarm.setMsg(String.format("Server %s seems okay!", ws.getName()));
					alarm.setType(0);
					alarmMgt.send(alarm);
					log.debug(String.format("Server %s seems okay!", ws.getName()));
				} else {
					alarm.setMsg(String.format("Server %s seems broken!", ws.getName()));
					alarm.setType(1);
					alarmMgt.send(alarm);
					log.warn(String.format("Server %s seems broken!", ws.getName()));
				}
			} catch (MalformedURLException e) {
				alarm.setMsg(String.format("Server %s seems broken!", ws.getName()));
				alarm.setType(1);
				alarmMgt.send(alarm);
				log.warn(String.format("Server %s seems broken!", ws.getName()));
			} catch (IOException e) {
				alarm.setMsg(String.format("Server %s seems broken!", ws.getName()));
				alarm.setType(1);
				alarmMgt.send(alarm);
				log.warn(String.format("Server %s seems broken!", ws.getName()));
			}
		}
		
		log.debug("After check, the AlarmMgt is:" + alarmMgt.toString());
		return null;
	}

}

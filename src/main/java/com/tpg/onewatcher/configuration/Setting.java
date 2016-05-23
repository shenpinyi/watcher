package com.tpg.onewatcher.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component(value = "setting")
@ConfigurationProperties(prefix="my")
public class Setting {
	private List<DataSourceSetting> datasources = new ArrayList<>();
	
	private SmsSetting sms;
	
	private MailSetting mail;
	
	private List<WebServerSetting> webservers = new ArrayList<>();
	
	public List<DataSourceSetting> getDatasources() {
		return datasources;
	}

	public void setDatasources(List<DataSourceSetting> datasources) {
		this.datasources = datasources;
	}

	public SmsSetting getSms() {
		return sms;
	}

	public MailSetting getMail() {
		return mail;
	}

	public void setSms(SmsSetting sms) {
		this.sms = sms;
	}

	public void setMail(MailSetting mail) {
		this.mail = mail;
	}
	
	public DataSourceSetting getDataSourceSettingByName(String name) {
		
		for (DataSourceSetting ds : datasources) {
			if (ds.getName().equals(name)) {
				return ds;
			}
		}
		
		return null;
	}

	
	public List<WebServerSetting> getWebservers() {
		return webservers;
	}

	public void setWebservers(List<WebServerSetting> webservers) {
		this.webservers = webservers;
	}

	@Override
	public String toString() {
		return "Setting [datasources=" + datasources + ", sms=" + sms + ", mail=" + mail + ", webservers=" + webservers
				+ "]";
	}
}

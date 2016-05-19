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

	@Override
	public String toString() {
		return "Settings [datasources=" + datasources + ", sms=" + sms + "]";
	}
}

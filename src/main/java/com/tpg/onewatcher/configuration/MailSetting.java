package com.tpg.onewatcher.configuration;

import java.util.List;

public class MailSetting {

	private boolean enable;
	private String host;
	private int port;
	private String user;
	private String password;

	private List <MailMsgSetting> msgs;

	public boolean getEnable() {
		return enable;
	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public List<MailMsgSetting> getMsgs() {
		return msgs;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setMsgs(List<MailMsgSetting> msgs) {
		this.msgs = msgs;
	}

	@Override
	public String toString() {
		return "MailSetting [enable=" + enable + ", host=" + host + ", port=" + port + ", user=" + user + ", password="
				+ password + ", msgs=" + msgs + "]";
	}
}

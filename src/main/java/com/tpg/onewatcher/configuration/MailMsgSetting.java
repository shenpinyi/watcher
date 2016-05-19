package com.tpg.onewatcher.configuration;

public class MailMsgSetting {
	
	private int alarmtype;
	private String subject;
	private String from;
	private String to;
	private String content;

	public String getSubject() {
		return subject;
	}
	public String getFrom() {
		return from;
	}
	public String getTo() {
		return to;
	}
	public String getContent() {
		return content;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getAlarmtype() {
		return alarmtype;
	}
	public void setAlarmtype(int alarmtype) {
		this.alarmtype = alarmtype;
	}
	@Override
	public String toString() {
		return "MailMsgSetting [alarmtype=" + alarmtype + ", subject=" + subject + ", from=" + from + ", to=" + to
				+ ", content=" + content + "]";
	}

}
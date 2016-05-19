package com.tpg.onewatcher.alarmmgt;

public class Alarm {

	private String elementId;
	private String alarmId;
	private int type; // 1: raise alarm; 0: recover from alarm
	private String msg; // alarm msg
	
	public String getElementId() {
		return elementId;
	}
	public String getAlarmId() {
		return alarmId;
	}
	public int getType() {
		return type;
	}
	public String getMsg() {
		return msg;
	}
	public void setElementId(String elementId) {
		this.elementId = elementId;
	}
	public void setAlarmId(String alarmId) {
		this.alarmId = alarmId;
	}
	public void setType(int type) {
		this.type = type;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	@Override
	public String toString() {
		return "Alarm [elementId=" + elementId + ", alarmId=" + alarmId + ", type=" + type + ", msg=" + msg + "]";
	}
}

package com.tpg.onewatcher.configuration;

import java.util.List;

public class SmsSetting {

	private boolean enable;
	private List <SmsMsgSetting> msgs;
	public boolean getEnable() {
		return enable;
	}
	public List<SmsMsgSetting> getMsgs() {
		return msgs;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	public void setMsgs(List<SmsMsgSetting> msgs) {
		this.msgs = msgs;
	}
	@Override
	public String toString() {
		return "SmsSetting [enable=" + enable + ", msgs=" + msgs + "]";
	}
}

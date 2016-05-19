package com.tpg.onewatcher.alarmmgt;

import java.util.Date;

public class AlarmState {
	private int count;
	private Date last;
	private int notifyCount;
	private int type;
	
	public int getCount() {
		return count;
	}
	public Date getLast() {
		return last;
	}
	public int getNotifyCount() {
		return notifyCount;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public void setLast(Date last) {
		this.last = last;
	}
	public void setNotifyCount(int notifyCount) {
		this.notifyCount = notifyCount;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "AlarmState [count=" + count + ", last=" + last + ", notifyCount=" + notifyCount + ", type=" + type
				+ "]";
	}
}

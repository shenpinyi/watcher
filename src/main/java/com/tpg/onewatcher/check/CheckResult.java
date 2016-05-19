package com.tpg.onewatcher.check;

public class CheckResult {
	int code;
	String info;
	
	public CheckResult(int code, String info) {
		this.code = code;
		this.info = info;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	@Override
	public String toString() {
		return "CheckResult [code=" + code + ", info=" + info + "]";
	}
	
}

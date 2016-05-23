package com.tpg.onewatcher.configuration;

public class WebServerSetting {
	private String name;
	private String url;
	private String response;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	@Override
	public String toString() {
		return "WebServerSetting [name=" + name + ", url=" + url + ", response=" + response + "]";
	}
}

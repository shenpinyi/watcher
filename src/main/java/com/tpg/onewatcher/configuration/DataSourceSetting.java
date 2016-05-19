package com.tpg.onewatcher.configuration;

public class DataSourceSetting {
	private String name;
	private String url;
	private String username;
	private String password;
	private String driverClassName;
	private String sql;
	public String getName() {
		return name;
	}
	public String getUrl() {
		return url;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getDriverClassName() {
		return driverClassName;
	}
	public String getSql() {
		return sql;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	@Override
	public String toString() {
		return "DataSourceSetting [name=" + name + ", url=" + url + ", username=" + username + ", password=" + password
				+ ", driverClassName=" + driverClassName + ", sql=" + sql + "]";
	}
}

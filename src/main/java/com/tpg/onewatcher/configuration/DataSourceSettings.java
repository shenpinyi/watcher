package com.tpg.onewatcher.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component(value = "dataSourceSettings")
@ConfigurationProperties(prefix="my")
public class DataSourceSettings {
	private List<DataSourceSetting> datasources = new ArrayList<>();

	public List<DataSourceSetting> getDatasources() {
		return datasources;
	}

	public void setDatasources(List<DataSourceSetting> datasources) {
		this.datasources = datasources;
	}

	@Override
	public String toString() {
		return "DataSourceSettings [datasources=" + datasources + "]";
	}
	
	

}

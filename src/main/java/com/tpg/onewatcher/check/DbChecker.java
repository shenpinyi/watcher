package com.tpg.onewatcher.check;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.tpg.onewatcher.configuration.DataSourceSetting;
import com.tpg.onewatcher.configuration.DataSourceSettings;

@Component(value = "dbChecker")
public class DbChecker implements Checker {
	
	@Autowired
	DataSourceSettings dataSourceSettings;
	
	@Override
	public CheckResult check() {
		List <DataSource> dataSources = new ArrayList<>();
		StringBuffer buff = new StringBuffer();
		int code = 0;
		
		for (DataSourceSetting setting : dataSourceSettings.getDatasources()) {
			try{
				BasicDataSource dataSource = new BasicDataSource();
				dataSource.setUrl(setting.getUrl());
				dataSource.setUsername(setting.getUsername());
				dataSource.setPassword(setting.getPassword());
				dataSource.setDriverClassName(setting.getDriverClassName());
				dataSources.add(dataSource);

				JdbcTemplate myJdbcTemplate = new JdbcTemplate(dataSource);

				List <Map <String, Object>> results = myJdbcTemplate.queryForList(setting.getSql());
				
				if (results != null && !results.isEmpty()) {
					buff.append("DB " + setting.getName() + 
							" checks okay! Count of records:" + results.size() + "\r\n");
				} else {
					code = 1;
					buff.append("DB " + setting.getName() + 
							" checks error! Count of records:0\r\n"); 
				}
			} catch (Exception e) {
				code = 2;
				buff.append("DB " + setting.getName() + 
						" checks error! Exception: " + e.getMessage() + "\r\n"); 
			}
		}
		
		return new CheckResult(code, buff.toString());
	}
	
}

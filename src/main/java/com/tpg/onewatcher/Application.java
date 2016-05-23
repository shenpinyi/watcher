package com.tpg.onewatcher;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.tpg.onewatcher.configuration.DataSourceSetting;
import com.tpg.onewatcher.configuration.Setting;

@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories
public class Application {
	
	@Autowired
	private Setting setting;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean(name="walletJdbcTemplate")
	public JdbcTemplate getWalletJdbcTemplate() {
		DataSourceSetting ds = setting.getDataSourceSettingByName("wallet");
		if (setting == null) {
			return null;
		}

		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl(ds.getUrl());
		dataSource.setUsername(ds.getUsername());
		dataSource.setPassword(ds.getPassword());
		dataSource.setDriverClassName(ds.getDriverClassName());
		dataSource.setValidationQuery("SELECT 1");
		dataSource.setTestOnBorrow(false);
		dataSource.setMaxWait(3000);
		
		return new JdbcTemplate(dataSource);
	}
	
	@Bean(name="smsJdbcTemplate")
	public JdbcTemplate getSmsJdbcTemplate() {
		DataSourceSetting ds = setting.getDataSourceSettingByName("sms");
		if (setting == null) {
			return null;
		}

		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl(ds.getUrl());
		dataSource.setUsername(ds.getUsername());
		dataSource.setPassword(ds.getPassword());
		dataSource.setDriverClassName(ds.getDriverClassName());
		dataSource.setValidationQuery("SELECT 1");
		dataSource.setTestOnBorrow(false);
		dataSource.setMaxWait(3000);
		
		return new JdbcTemplate(dataSource);
	}
	
}

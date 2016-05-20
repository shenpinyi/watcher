package com.tpg.onewatcher.check;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.tpg.onewatcher.configuration.DataSourceSetting;
import com.tpg.onewatcher.configuration.Setting;

@Component(value = "walletChecker")
public class WalletChecker implements Checker {
	
	@Autowired
	private Setting setting;
	
	@Value("${my.check.wallet.max_idle_minutes}")
	private int MAX_IDLE_MINUTES;
	
	@Override
	public CheckResult check() {
		
		DataSourceSetting ds = setting.getDataSourceSettingByName("wallet");
		if (setting == null) {
			return new CheckResult(6, "No Wallet DB configuration found.");
		}

		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl(ds.getUrl());
		dataSource.setUsername(ds.getUsername());
		dataSource.setPassword(ds.getPassword());
		dataSource.setDriverClassName(ds.getDriverClassName());

		JdbcTemplate myJdbcTemplate = new JdbcTemplate(dataSource);
		try {
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MINUTE, - MAX_IDLE_MINUTES);
			myJdbcTemplate.setQueryTimeout(15);

			// Check request_queue
			Map<String, Object> request = myJdbcTemplate.queryForMap("SELECT * FROM request_queue ORDER BY id DESC LIMIT 1");
			if (request == null) {
				return new CheckResult(1, "Failed: TABLE request_queue is empty");
			}
			Date requestDate = (Date) request.get("Request_DateTime");
			if (requestDate.before(c.getTime())){
				return new CheckResult(2, "Failed: TABLE request_queue hasn't been updated since " 
			                              + requestDate.toString() + "."
			                              + c.getTime() + " expected");
			}
			
			// Check call_back_queue
			Map<String, Object> callBack = myJdbcTemplate.queryForMap("SELECT * FROM call_back_queue ORDER BY id DESC LIMIT 1");
			
			if (callBack == null) {
				return new CheckResult(3, "Failed: TABLE call_back_queue is empty");
			}
			
			Date updateDate = (Date) callBack.get("Update_DateTime");
			
			if (updateDate.before(c.getTime())){
				return new CheckResult(4, "Failed: TABLE call_back_queue hasn't been updated since " 
			                              + updateDate.toString() + "."
			                              + c.getTime() + " expected");
			}
			return new CheckResult(0, "Succeed: callBack_queue updated on " + updateDate.toString() 
					+ "; request_queue updated on " + requestDate.toString()
					+ "; " + c.getTime() + " expected");

		} catch (Exception e) {
			return new CheckResult(5, "Exception when executing query from Wallet DB. Exception: " + e.getMessage());
		} finally {
			try {
				dataSource.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

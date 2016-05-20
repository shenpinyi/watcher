package com.tpg.onewatcher.message;

import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.tpg.onewatcher.configuration.DataSourceSetting;
import com.tpg.onewatcher.configuration.Setting;

@Component
public class SmsSender {

	private static Logger log = LoggerFactory.getLogger(SmsSender.class);

	@Autowired
	private Setting setting;

	public int send(SmsEntity sms) {

		DataSourceSetting ds = setting.getDataSourceSettingByName("sms");
		if (setting == null) {
			return 1;
		}

		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl(ds.getUrl());
		dataSource.setUsername(ds.getUsername());
		dataSource.setPassword(ds.getPassword());
		dataSource.setDriverClassName(ds.getDriverClassName());

		JdbcTemplate smsJdbcTemplate = new JdbcTemplate(dataSource);

		try {
			smsJdbcTemplate.update(
					"insert into sms " + "   set enteredBy_notify = ?, " + "       additional_notify_address = ?, "
							+ "       system = ?, " + "       priority = ?, " + "       destination_addr = ?, "
							+ "       registered_delivery = ?, " + "       short_message = ?, " + "       status = ?, "
							+ "       entered = NOW(), " + "       updated = NOW(), " + "       enteredBy = ?",
					sms.getEnteredByNotify(), sms.getAdditionalNotifyAddress(), sms.getSystem(), sms.getPriority(),
					sms.getDestinationAddr(), sms.getRegisteredDelivery(), sms.getShortMessage(), sms.getStatus(),
					sms.getEnteredBy());
			
			log.info("[0] SMS sent succeed to " + sms.getDestinationAddr() + ". Msg:" + sms.getShortMessage());
			return 0;
		} catch (Exception e) {
			log.error("[1] SMS sent failed for Reason: " + e.getMessage());
			return 1;
		} finally {
			try {
				dataSource.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}

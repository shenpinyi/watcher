package com.tpg.onewatcher.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tpg.onewatcher.alarmmgt.Alarm;
import com.tpg.onewatcher.alarmmgt.AlarmMgt;
import com.tpg.onewatcher.check.CheckResult;
import com.tpg.onewatcher.check.Checker;

@Component
public class CheckWallet {
	
	private static Logger log = LoggerFactory.getLogger(CheckWallet.class); 

	@Autowired
	@Qualifier("walletChecker")
	private Checker walletChecker;
	
	@Autowired
	@Qualifier("httpChecker")
	private Checker httpChecker;
	
	@Autowired
	private AlarmMgt alarmMgt;

	@Scheduled(cron = "${my.scheduler.wallet.cron}")
	public void check() {
		
		log.debug("Before check, the AlarmMgt is:" + alarmMgt.toString());
		
		CheckResult re = walletChecker.check();
		log.debug(re.toString());
		
		Alarm alarm = new Alarm();
		alarm.setAlarmId("WalletDB");
		alarm.setElementId("WalletDB");

		if (re.getCode() == 0){
			log.debug("Wallet checked all right. ");
			alarm.setMsg("Wallet seems okay!");
			alarm.setType(0);
			alarmMgt.send(alarm);
		} else {
			log.error("Wallet checked bad enough to notify. ");
			alarm.setMsg("Wallet seems broken!");
			alarm.setType(1);
			alarmMgt.send(alarm);
		}

		log.debug("After check, the AlarmMgt is:" + alarmMgt.toString());
	}
}

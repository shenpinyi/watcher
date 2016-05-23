package com.tpg.onewatcher.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tpg.onewatcher.check.Checker;

@Component
public class CheckWebServer {
	
	private static Logger log = LoggerFactory.getLogger(CheckWebServer.class); 

	@Autowired
	@Qualifier("httpChecker")
	private Checker httpChecker;

	@Scheduled(cron = "${my.scheduler.wallet.cron}")
	public void check() {
		log.debug("Invoke CheckWebServer");
		httpChecker.check();
	}
}

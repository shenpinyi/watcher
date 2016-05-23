package com.tpg.onewatcher.notification;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tpg.onewatcher.alarmmgt.Alarm;
import com.tpg.onewatcher.configuration.Setting;
import com.tpg.onewatcher.configuration.SmsMsgSetting;
import com.tpg.onewatcher.configuration.SmsSetting;
import com.tpg.onewatcher.message.SmsEntity;
import com.tpg.onewatcher.message.SmsSender;

@Component
public class SmsNotifier implements Notifier {
	
	private static Logger log = LoggerFactory.getLogger(SmsNotifier.class);
	
	@Autowired
	private Setting setting;
	
	@Autowired
	private SmsSender smsSender;

	@Override
	public int send(Alarm alarm) {
		
		SmsSetting smsSetting = setting.getSms();
		List <SmsMsgSetting> msgSettings = smsSetting.getMsgs();
		
		if (!smsSetting.getEnable()) {
			return 0;
		}
		
		for (SmsMsgSetting msgSetting : msgSettings) {
			
			if (msgSetting.getAlarmtype() == alarm.getType()) {
				SmsEntity sms = new SmsEntity();
				sms.setAdditionalNotifyAddress(msgSetting.getAdditionalNotifyAddress());
				sms.setDestinationAddr(msgSetting.getDestinationAddr());
				sms.setEnteredBy(msgSetting.getEnteredBy());
				sms.setEnteredByNotify(msgSetting.getEnteredByNotify());
				sms.setPriority(msgSetting.getPriority());
				sms.setRegisteredDelivery(msgSetting.getRegisteredDelivery());
				sms.setShortMessage(msgSetting.getShortMessage() + alarm.getElementId());
				sms.setStatus(msgSetting.getStatus());
				sms.setSystem(msgSetting.getSystem());
				smsSender.send(sms);
				log.info("[0] Finished invoke smsSender to " + sms.getDestinationAddr() 
				         + ", msg: " + sms.getShortMessage());
			}
		}
		return 0;
	}

}

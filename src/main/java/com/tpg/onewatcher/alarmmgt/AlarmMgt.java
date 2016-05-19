package com.tpg.onewatcher.alarmmgt;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tpg.onewatcher.notification.Notifier;

@Component(value = "alarmMgt")
@Scope("singleton")
public class AlarmMgt {

	private List <Element> elements = new ArrayList<>();
	
	@Autowired
	private Notifier smsNotifier;
	
	@Autowired
	private Notifier emailNotifier;
	
	@Value("${my.alarm.max_recover}")
	private int maxRecoverAlarm;
	
	@Value("${my.alarm.max_broken}")
	private int maxBrokenAlarm;
	
	public void send(Alarm alarm) {
		
		Element element = getElement(alarm.getElementId());
		
		if (element == null) {
			element = addElement(alarm.getElementId());
		}
		
		if (isNotifyNeeded(element, alarm)) {
			notify(alarm);
		}
		
	}
	
	private void notify(Alarm alarm) {
		smsNotifier.send(alarm);
		emailNotifier.send(alarm);
	}
	
	private boolean isNotifyNeeded(Element element, Alarm newAlarm) {
		
		AlarmState state = element.getAlarmStates().get(newAlarm.getAlarmId());
		
		int maxNotiCount = 0;
		if (newAlarm.getType() == 0) {
			maxNotiCount = maxRecoverAlarm;
		} else {
			maxNotiCount = maxBrokenAlarm;
		}
		
		if (state == null) { // new alarm id
			state = new AlarmState();
			element.getAlarmStates().put(newAlarm.getAlarmId(), state);
			state.setCount(1);
			state.setLast(Calendar.getInstance().getTime());
			if (newAlarm.getType() == 0) {
			    // it's recover, do not send notify
				state.setNotifyCount(maxRecoverAlarm);
				state.setType(0);
				return false;
			} else {
				// it's alarm, send notify
				state.setType(1);
				state.setNotifyCount(1);
				return true;
			}
		} else { // existing alarm id
			if (state.getType() == newAlarm.getType()) {
				// has sent one notify, do not send again
				// more complex strategy should be supported
				state.setCount(state.getCount() + 1);
				state.setLast(Calendar.getInstance().getTime());
				
				if (state.getNotifyCount() < maxNotiCount) {
					state.setNotifyCount(state.getNotifyCount() + 1);
					return true;
				} else {
					return false;
				}
				
			} else {
				state.setType(newAlarm.getType());
				state.setCount(1);
				state.setLast(Calendar.getInstance().getTime());
				if (maxNotiCount > 0) {
					state.setNotifyCount(1);
					return true;
				} else {
					return false;
				}
			}
		}
	}
	
	private Element getElement(String id) {
		for (Element e : elements) {
			if (e.getElementId().equals(id)) {
				return e;
			}
		}
		return null;
	}

	private Element addElement(String id) {
		for (Element e : elements) {
			if (e.getElementId().equals(id)) {
				return e;
			}
		}
		
		Element element = new Element(id);
		elements.add(element);
		return element;
	}

	@Override
	public String toString() {
		return "AlarmMgt [elements=" + elements + "]";
	}
}

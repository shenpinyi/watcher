package com.tpg.onewatcher.alarmmgt;

import java.util.HashMap;
import java.util.Map;

public class Element {
	private String elementId;
	private Map <String, AlarmState> alarmStates;
	
	public Element(String elementId) {
		this.elementId = elementId;
		alarmStates = new HashMap<>();
	}

	public String getElementId() {
		return elementId;
	}
	public Map<String, AlarmState> getAlarmStates() {
		return alarmStates;
	}
	public void setElementId(String elementId) {
		this.elementId = elementId;
	}
	public void setAlarmStates(Map<String, AlarmState> alarmStates) {
		this.alarmStates = alarmStates;
	}

	@Override
	public String toString() {
		return "Element [elementId=" + elementId + ", alarmStates=" + alarmStates + "]";
	}
	
	
}

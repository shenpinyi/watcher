package com.tpg.onewatcher.notification;

import com.tpg.onewatcher.alarmmgt.Alarm;

public interface Notifier {
	public int send(Alarm alarm);
}

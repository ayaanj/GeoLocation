package com.infoobjects.geolocation;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.util.Log;

public class BatteryLevelReceiver extends BroadcastReceiver {

	private final String BATTERY_LEVEL_RECEIVER_TAG = "BatteryLevelReceiver";
	private float batteryPercentage;

	@Override
	public void onReceive(Context context, Intent battery) {
		int level = battery.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
		int scale = battery.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
		batteryPercentage = level / (float) scale * 100;
	}
	
	public float getBatteryPercentage() {
		return batteryPercentage;
	}
}

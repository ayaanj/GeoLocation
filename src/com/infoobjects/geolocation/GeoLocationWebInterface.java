package com.infoobjects.geolocation;

import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.webkit.JavascriptInterface;

public class GeoLocationWebInterface {

	private final String GEO_LOCATION_CONFIG_TAG = "GeoLocationWebInterface";

	private Context context;

	private BatteryLevelReceiver batteryLevelReceiver;

	public GeoLocationWebInterface(Context context, BatteryLevelReceiver batteryLevelReceiver) {
		this.context = context;
		this.batteryLevelReceiver = batteryLevelReceiver;
	}

	@JavascriptInterface
	public int getMinInterval() {
		Resources res = context.getResources();
		int interval = res.getInteger(R.integer.min);
		return interval;
	}

	@JavascriptInterface
	public int getMaxInterval() {
		Resources res = context.getResources();
		int interval = res.getInteger(R.integer.max);
		return interval;
	}

	
	@JavascriptInterface
	public String getWebServiceMethodUrl() {
		return context.getString(R.string.web_service_method_url);
	}

	@JavascriptInterface
	public String getBatteryStatus() {
		TelephonyManager telephoneService = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		return telephoneService.getDeviceId();
	}

	@JavascriptInterface
	public void log(String msg) {
		Log.i(GEO_LOCATION_CONFIG_TAG, msg);
	}

	@JavascriptInterface
	public float getBatteryPercentage() {
		return batteryLevelReceiver.getBatteryPercentage();
	}

	@JavascriptInterface
	public boolean isConnected() {
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}
		return false;

	}
}

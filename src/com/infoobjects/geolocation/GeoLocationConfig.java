package com.infoobjects.geolocation;

import android.content.Context;
import android.content.res.Resources;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.webkit.JavascriptInterface;

public class GeoLocationConfig {

	private final String GEO_LOCATION_CONFIG_TAG = "GeoLocationConfig";
	
	Context context;

	public GeoLocationConfig(Context context) {
		this.context = context;
	}

	@JavascriptInterface
	public int getInterval() {
		Resources res = context.getResources();
		int interval = res.getInteger(R.integer.interval);
		return interval;
	}

	@JavascriptInterface
	public String getWebServiceMethodUrl() {
		return context.getString(R.string.web_service_method_url);
	}
	
	@JavascriptInterface
	public String getDeviceId() {
		TelephonyManager telephoneService = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		return  telephoneService.getDeviceId();
	}
	
	@JavascriptInterface
	public void log(String msg) {
		Log.i(GEO_LOCATION_CONFIG_TAG, msg);
	}
}

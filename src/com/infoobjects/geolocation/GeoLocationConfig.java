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
	public int getCallFrequency() {
		Resources res = context.getResources();
		int callFrequency = res.getInteger(R.integer.call_frequency);
		return callFrequency;
	}

	@JavascriptInterface
	public String getWebServiceUrl() {
		return context.getString(R.string.web_service_url);
	}
	
	@JavascriptInterface
	public String getDeviceId() {
		TelephonyManager telephoneService = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		return telephoneService.getDeviceId();
	}
	
	@JavascriptInterface
	public void log(String msg) {
		Log.i(GEO_LOCATION_CONFIG_TAG, msg);
	}
}

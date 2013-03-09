package com.infoobjects.geolocation;

import android.content.Context;
import android.content.res.Resources;
import android.telephony.TelephonyManager;
import android.webkit.JavascriptInterface;

public class GeoLocationWebViewConfig {

	Context context;

	public GeoLocationWebViewConfig(Context context) {
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
}

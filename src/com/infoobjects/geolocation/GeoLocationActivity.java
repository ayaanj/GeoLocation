package com.infoobjects.geolocation;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * A  WebView app with HTML5 geolocation capability
 * @author Jyothi Gagoria
 *  
 */
public class GeoLocationActivity extends Activity {

    private final String GEO_LOCATION_ACTIVITY_TAG = "GeoLocationActivity";
	/**
	 * WebChromeClient subclass handles UI-related calls
	 */
	public class GeoLocationWebChromeClient extends WebChromeClient {
		@Override
		public void onGeolocationPermissionsShowPrompt(String origin,
				GeolocationPermissions.Callback callback) {
			// Always grant permission since the app itself requires location
			// permission and the user has therefore already granted it
			callback.invoke(origin, true, false);
		}
	}

	WebView webView;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		webView = (WebView) findViewById(R.id.webView);
		initSettings();
		webView.loadUrl("file:///android_asset/www/index.html");
	}
	

	@SuppressLint("SetJavaScriptEnabled")
	private void initSettings() {
		Log.i(GEO_LOCATION_ACTIVITY_TAG, "initializing setting...");
		webView.setWebChromeClient(new GeoLocationWebChromeClient());
		webView.addJavascriptInterface(
				new GeoLocationConfig(this.getApplicationContext()),
				"config");
		WebSettings webViewSettings = webView.getSettings();
		webViewSettings.setJavaScriptEnabled(true);
		webViewSettings.setGeolocationEnabled(true);
		webViewSettings.setAppCacheEnabled(false);
		webViewSettings.setDatabaseEnabled(false);
		webViewSettings.setDomStorageEnabled(false);
		Log.i(GEO_LOCATION_ACTIVITY_TAG, "setting initialized...");
	}
	
}

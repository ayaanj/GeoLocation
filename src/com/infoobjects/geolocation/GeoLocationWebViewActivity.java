package com.infoobjects.geolocation;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * A minimal WebView app with HTML5 geolocation capability
 * 
 */
public class GeoLocationWebViewActivity extends Activity {

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
	@SuppressLint("SetJavaScriptEnabled") 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		webView = (WebView) findViewById(R.id.webView);
		webView.setWebChromeClient(new GeoLocationWebChromeClient());
		webView.addJavascriptInterface(new GeoLocationWebViewConfig(this.getApplicationContext()), "config");
		WebSettings webViewSettings = webView.getSettings();
		webViewSettings.setJavaScriptEnabled(true);
		webViewSettings.setGeolocationEnabled(true);
		webViewSettings.setAppCacheEnabled(false);
		webViewSettings.setDatabaseEnabled(false);
		webViewSettings.setDomStorageEnabled(false);
		webView.loadUrl("file:///android_asset/www/index.html");
	}
}

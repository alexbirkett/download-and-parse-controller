package com.birkett.downloadandparsecontroller;

import java.io.File;
import java.io.IOException;

import android.app.Application;
import android.net.http.HttpResponseCache;

public class DownloadAndParseApplication extends Application {
	
	private static final long HTTP_CACHE_SIZE = 10 * 1024 * 1024; // 10 MiB

	
	public DownloadAndParseApplication() {
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		initCache();
	}
		
	private void initCache() {
		try {
			File httpCacheDir = new File(this.getCacheDir(), "http");

			HttpResponseCache.install(httpCacheDir, HTTP_CACHE_SIZE);
		} catch (IOException e) {
			// ignore - that's what the the example does :(
			// https://developer.android.com/reference/android/net/http/HttpResponseCache.html
		}
	}
}

package com.birkett.downloadandparsecontroller;

import android.app.Application;
public class DownloadAndParseApplication extends Application {
	
	public DownloadAndParseApplication() {
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		CacheUtils.initCache(this);
	}
		
	
}

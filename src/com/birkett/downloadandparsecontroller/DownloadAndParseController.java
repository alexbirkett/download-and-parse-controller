package com.birkett.downloadandparsecontroller;

import android.content.Context;
import android.os.Bundle;

import com.birkett.controllers.Controller;

public class DownloadAndParseController extends Controller {

	private DownloadAndParseTask mRunningTask;
	private DownloadAndParseObserver mObserver;
	private String mUrl;
	
	private static final String URL_KEY = "com.birkett.downloadandparsecontroller.URL_KEY";
	

	public DownloadAndParseController(Context context, DownloadAndParseObserver observer) {
		super(context);
		mObserver = observer;
	}
	
	public void downloadAndParseUrl(String url) {
		mRunningTask = new DownloadAndParseTask(mObserver);
		mRunningTask.execute(url);
		mUrl = url;
	}
	
	@Override
	public void onResume() {
		if (mUrl != null) {
			downloadAndParseUrl(mUrl);
		}
	}
	
	@Override
	public void onPause() {
		if (mRunningTask != null) {
			mRunningTask.cancel(true);			
		}
		mRunningTask = null;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		if (savedInstanceState != null) {
			mUrl = savedInstanceState.getString(URL_KEY);
		}
	}
	
	protected void onSaveInstanceState(Bundle outState) {
		outState.putString(URL_KEY, mUrl);
	}
	
}

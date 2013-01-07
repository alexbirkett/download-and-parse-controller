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
	}
	
	public void downloadAndParseUrl(String url) {
		mRunningTask = new DownloadAndParseTask(mObserver);
		mRunningTask.execute(url);
	}
	
	@Override
	public void onResume() {
		
	}
	
	@Override
	public void onPause() {
		mRunningTask.cancel(true);
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

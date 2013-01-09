package com.birkett.downloadandparsecontroller;

import com.birkett.controllers.BaseControllerActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.birkett.downloadandparsecontroller.R;

public class DownloadAndParseActivity extends BaseControllerActivity implements DownloadAndParseObserver {

	private static final String TWITTER_URL = "https://api.twitter.com/1/statuses/user_timeline.json?screen_name=alexbirkett&include_rts=1";
	
	private DownloadAndParseController mDownloaderParserController;
	
	private long mDownloadAndParseStartTimetamp;
	
	@Override
	protected void createControllers() {
		mDownloaderParserController = new DownloadAndParseController(this, this);
		addController(mDownloaderParserController);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_download_and_parse_controller);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(
				R.menu.activity_download_and_parse_controller, menu);
		return true;
	}

	@Override
	protected void onResume() {
		super.onResume();
	}
	
	@Override
	public void onDownloadAndParseStarted() {
		mDownloadAndParseStartTimetamp = System.currentTimeMillis();
		setStatus(R.string.download_and_parse_started);
		
	}

	@Override
	public void onDownloadAndParseSuccess(Object object) {
		setStatus(R.string.download_and_parse_success);
		displayDuration();
	}

	@Override
	public void onDownloadAndParseFail(Exception exception) {
		setStatus(R.string.download_and_parse_failed);
		displayDuration();
	}
	
	public void onDownloadAndParseButtonClicked(View v) {
		mDownloaderParserController.downloadAndParseUrl(TWITTER_URL);
	}
	
	public void onClearCacheClicked(View v) {
		CacheUtils.clearCache(this);
	}
	
	private void setStatus(int resid) {
		TextView view = (TextView) this.findViewById(R.id.status);
		view.setText(resid);
	}
	
	private void displayDuration() {
		long duraiton = calculateDuration();
		TextView view = (TextView) this.findViewById(R.id.download_and_parse_duration);
		view.setText(duraiton + "ms"); // this should go in strings.xml
	}
	
	private long calculateDuration() {
		return System.currentTimeMillis() - mDownloadAndParseStartTimetamp;
	}
}
package com.birkett.downloadandparsecontroller;

import com.birkett.controllers.BaseControllerActivity;

import android.os.Bundle;
import android.view.Menu;

public class DownloadAndParseActivity extends BaseControllerActivity implements DownloadAndParseObserver {

	private static final String TWITTER_URL = "https://api.twitter.com/1/statuses/user_timeline.json?screen_name=alexbirkett&include_rts=1";

	private DownloadAndParseController mDownloaderParserController;
	
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
		mDownloaderParserController.downloadAndParseUrl(TWITTER_URL);
	}

	@Override
	public void onDownloadAndParseStarted() {
	
	}

	@Override
	public void onDownloadAndParseSuccess(Object object) {
	
	}

	@Override
	public void onDownloadAndParseFail(Exception exception) {
	
	}
	
}

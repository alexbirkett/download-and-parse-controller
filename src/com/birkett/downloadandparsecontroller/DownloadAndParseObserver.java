package com.birkett.downloadandparsecontroller;

public interface DownloadAndParseObserver {

	public void onDownloadAndParseStarted();
	
	public void onDownloadAndParseSuccess(Object object);
	
	public void onDownloadAndParseFail(Exception exception);
	
}

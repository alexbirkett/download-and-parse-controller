package com.birkett.downloadandparsecontroller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import com.fasterxml.jackson.databind.ObjectMapper;

import android.os.AsyncTask;

public class DownloadAndParseTask extends AsyncTask<String, Integer, Object> {
	
	// Should be thread safe: http://wiki.fasterxml.com/JacksonFAQ
	private static ObjectMapper mMapper = new ObjectMapper();
			
	private DownloadAndParseObserver mObserver;
	
	private Exception mException;
	
	public DownloadAndParseTask(DownloadAndParseObserver observer) {
		mObserver = observer;
	}
	
	@Override
	protected Object doInBackground(String... sUrl) {

		Object object = null;
		try {
			InputStream inputStream = download(sUrl[0]);
			object = mMapper.readValue(inputStream, Object.class);
		} catch (IOException e) {
			mException = e;
		}

		return object;
	}
	
	@Override
	protected void onPostExecute(Object result) {
		if (mObserver != null) {
			if (mException == null) {
				mObserver.onDownloadAndParseSuccess(result);
			} else {
				mObserver.onDownloadAndParseFail(mException);
			}
		}
    }
	
	@Override
	protected void onPreExecute() {
		mObserver.onDownloadAndParseStarted();
    }
	 
	private InputStream download(String urlString) throws IOException {
		URL url = new URL(urlString);
		URLConnection connection = url.openConnection();
		connection.connect();
		InputStream input = url.openStream();
		return input;
	}
	
}

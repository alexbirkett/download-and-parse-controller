package com.birkett.downloadandparsecontroller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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
		HttpURLConnection urlConnection = null;
		try {
			URL url = new URL(sUrl[0]);
			urlConnection = (HttpURLConnection) url.openConnection();
			InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
			object = mMapper.readValue(inputStream, Object.class);
		} catch (IOException e) {
			mException = e;
		} finally {
			urlConnection.disconnect();
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
}

package com.birkett.downloadandparsecontroller;

import java.io.File;
import java.io.IOException;

import android.content.Context;
import android.net.http.HttpResponseCache;

public class CacheUtils {

	private static final long HTTP_CACHE_SIZE = 10 * 1024 * 1024; // 10 MiB

	
	public static void initCache(Context context) {
		try {
			File httpCacheDir = new File(context.getCacheDir(), "http");

			HttpResponseCache.install(httpCacheDir, HTTP_CACHE_SIZE);
		} catch (IOException e) {
			// ignore - that's what the the example does :(
			// https://developer.android.com/reference/android/net/http/HttpResponseCache.html
		}
	}
	
	public static void clearCache(Context context) {
		try {
			HttpResponseCache cache = HttpResponseCache.getInstalled();
			if (cache != null) {
				cache.delete();
			}
			initCache(context);
		} catch (IOException e) {
			// ignore - that's what the the example does :(
			// https://developer.android.com/reference/android/net/http/HttpResponseCache.html
		}
	}
	
}

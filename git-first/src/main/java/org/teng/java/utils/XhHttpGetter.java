package org.teng.java.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;

public class XhHttpGetter {
	private static Logger logger = Logger.getLogger(XhHttpGetter.class);

	public static String sendGet(String urlString, String... params) throws Exception {
		int timeout = 30 * 1000;
		if (params.length > 0) {
			String timeoutStr = params[0];
			try {
				timeout = Integer.valueOf(timeoutStr);
			} catch (Exception e) {
				logger.error("第一个参数是timeout，应为整数。" + e.getMessage(), e);
			}
		}

		URL url = null;
		InputStream in = null;
		BufferedReader reader = null;
		HttpURLConnection conn = null;
		String result = "";
		try {
			url = new URL(urlString);
			urlString = null;
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(timeout);
			conn.setRequestMethod("GET");
			conn.connect();

			in = conn.getInputStream();
			reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			result = reader.readLine();
		} catch (Exception e) {
			throw new Exception("XhHttpGetter.sendGet", e);
		} finally {
			try {
				if (in != null) {
					in.close();
					in = null;
				}
				if (reader != null) {
					reader.close();
					reader = null;
				}
				if (conn != null) {
					conn.disconnect();
					conn = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			urlString = null;
			url = null;
		}
		return result;
	}
}
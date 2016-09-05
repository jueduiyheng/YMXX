package com.xinbo.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpConnUtils {
	/**
	 * 用http必须在开线程,刷新适配，土司通知，更改Ui必须开异步
	 */

	public static String httpUrlConn(String url, String Json) {

		try {
			// 创建连接
			URL url2 = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) url2.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.connect();
			// POST请求
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			out.write(Json.toString().getBytes("utf-8"));
			out.flush();
			out.close();
			// 响应
			InputStream inputStream = connection.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String readLine = bufferedReader.readLine();
			inputStream.close();
			inputStreamReader.close();
			// 断开连接
			connection.disconnect();
			return readLine;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}

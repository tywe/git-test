package org.teng.java.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;

/**
 * 封装常用的HTTP请求的GET/POST方法
 * 
 * @author libin
 * @date 2014-3-4
 */
public class HttpRequestHelper {
	private Logger logger = Logger.getLogger(HttpRequestHelper.class);

	/**
	 * 向指定URL发送GET方法的请求(连接超时时间为30秒)
	 * 
	 * @param url
	 *            发送请求的URL（包括请求参数）
	 * @return URL所代表远程资源的响应结果
	 */
	public String sendGet(String url) {
		// 连接超时时间为30秒
		return sendGet(url, 30 * 1000);
	}

	/**
	 * 向指定 URL 发送POST方法的请求(连接超时时间为30秒)
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式
	 * @return URL所代表远程资源的响应结果
	 */
	public String sendPost(String url, String param) {
		// 连接超时时间为30秒
		return sendPost(url, param, 30 * 1000);
	}

	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL（包括请求参数）
	 * @param timeout
	 *            连接超时时间
	 * @return URL所代表远程资源的响应结果
	 */
	public String sendGet(String url, int timeout) {
		if (StringHandler.isNullorEmpty(url)) {
			return null;
		}

		BufferedReader in = null;
		HttpURLConnection conn = null;
		StringBuilder result = new StringBuilder();
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			conn = (HttpURLConnection) realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setConnectTimeout(timeout);
			conn.setRequestMethod("GET");
			// 建立实际的连接
			conn.connect();

			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
		} catch (Exception e) {
			logger.error("发送GET请求过程中出错：" + e.getMessage(), e);
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (conn != null) {
					conn.disconnect();
					conn = null;
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return result.toString();
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式
	 * @param timeout
	 *            连接超时时间
	 * @return URL所代表远程资源的响应结果
	 */
	public String sendPost(String url, String param, int timeout) {
		if (StringHandler.isNullorEmpty(url)) {
			return null;
		}

		PrintWriter out = null;
		BufferedReader in = null;
		HttpURLConnection conn = null;
		StringBuilder result = new StringBuilder();
		try {
			URL realUrl = new URL(url);
			conn = (HttpURLConnection) realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setConnectTimeout(timeout);
			conn.setRequestMethod("POST");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);

			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();

			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
		} catch (Exception e) {
			logger.error("发送POST请求过程中出错：" + e.getMessage(), e);
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
				if (conn != null) {
					conn.disconnect();
					conn = null;
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		return result.toString();
	}

	public static void main(String[] args) {
		HttpRequestHelper httpHelper = new HttpRequestHelper();
		String url = "http://sc.hivoice.cn/service/iss?screen="
				+ "&text=%E8%AE%B2%E4%B8%AA%E7%AC%91%E8%AF%9D&appkey=letv-tv-android&ver=2.0&udid=JUnit-test"
				+ "&appsig=9DD97EC91815BE1AB27F42565DC46CF0BF54655D&appver=1.0.0.1&city=%E5%8C%97%E4%BA%AC"
				+ "&history=&time=&voiceid=&scenario=&gps=39.993227%2C116.340437&method=iss.getTalk&dpi=";
		String result = httpHelper.sendGet(url);
		System.out.println(result);

		url = "http://sc.hivoice.cn/service/iss";
		String params = "screen=&text=%E8%AE%B2%E4%B8%AA%E7%AC%91%E8%AF%9D&appkey=letv-tv-android&ver=2.0"
				+ "&udid=JUnit-test&appsig=9DD97EC91815BE1AB27F42565DC46CF0BF54655D&appver=1.0.0.1"
				+ "&city=%E5%8C%97%E4%BA%AC&history=&time=&voiceid=&scenario=&gps=39.993227%2C116.340437"
				+ "&method=iss.getTalk&dpi=";
		result = httpHelper.sendPost(url, params);
		System.out.println(result);
	}
}
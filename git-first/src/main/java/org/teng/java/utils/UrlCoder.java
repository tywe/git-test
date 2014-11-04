package org.teng.java.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.log4j.Logger;

/**
 * URL编码解码工具类
 * 
 * @author libin
 * @date 2014-1-22
 * 
 */
public class UrlCoder {
	private static Logger logger = Logger.getLogger(UrlCoder.class);

	/**
	 * 使用UTF-8对字符串进行编码
	 * 
	 * @param inputString
	 * @return
	 */
	public static String encode(String inputString) {
		return encode(inputString, "UTF-8");
	}

	/**
	 * 使用对应字符集对字符串进行编码
	 * 
	 * @param inputString
	 * @param encoding
	 * @return
	 */
	public static String encode(String inputString, String encoding) {
		if (StringHandler.isNullorEmpty(inputString)) {
			return null;
		}

		String encodedString = inputString;
		try {
			encodedString = URLEncoder.encode(inputString, encoding);
		} catch (UnsupportedEncodingException e) {
			logger.error("encodeUrl:" + e.getMessage(), e);
		}

		return encodedString;
	}

	/**
	 * 使用UTF-8对字符串进行解码
	 * 
	 * @param inputString
	 * @return
	 */
	public static String decode(String inputString) {
		return decode(inputString, "UTF-8");
	}

	/**
	 * 使用对应字符集对字符串进行解码
	 * 
	 * @param inputString
	 * @param encoding
	 * @return
	 */
	public static String decode(String inputString, String encoding) {
		if (StringHandler.isNullorEmpty(inputString)) {
			return null;
		}

		String decodedString = inputString;
		try {
			decodedString = URLDecoder.decode(inputString, encoding);
		} catch (UnsupportedEncodingException e) {
			logger.error("decodeUrl:" + e.getMessage(), e);
		}

		return decodedString;
	}
}
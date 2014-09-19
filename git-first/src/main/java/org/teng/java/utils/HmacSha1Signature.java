package org.teng.java.utils;

import java.util.Formatter;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * 使⽤用 HMAC-SHA1算法来生成签名
 * 
 * @author libin
 * @date 2014-5-21
 */
public class HmacSha1Signature {
	private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";

	private static String toHexString(byte[] bytes) {
		Formatter formatter = new Formatter();

		for (byte b : bytes) {
			formatter.format("%02x", b);
		}

		return formatter.toString();
	}

	public static String getSignature(String data, String key) throws Exception {
		SecretKeySpec signingKey = new SecretKeySpec(key.getBytes("UTF-8"), HMAC_SHA1_ALGORITHM);
		Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
		mac.init(signingKey);
		return toHexString(mac.doFinal(data.getBytes("UTF-8")));
	}

	public static void main(String[] args) throws Exception {
		String hmac = getSignature("data", "key");

		System.out.println(hmac);
		assert hmac.equals("104152c5bfdca07bc633eebd46199f0255c9f49d");
	}
}
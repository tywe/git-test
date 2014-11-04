package org.teng.java.utils;

import java.security.MessageDigest;

import org.apache.log4j.Logger;

public class Str2MD5 {
	private static Logger logger = Logger.getLogger(Str2MD5.class);

	public static String Str2MD5sum(String source) {
		MessageDigest md;
		String target = "";
		byte[] bytes = null;
		try {
			// 生成一个MD5加密计算摘要
			md = MessageDigest.getInstance("MD5"); // 计算md5函数
			md.update(source.getBytes("utf-8"));
			// digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
			// BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
			bytes = md.digest();
//			target = new BigInteger(1, bytes).toString(16);
			for (int i = 0; i < bytes.length; i++) {
				target += Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1);
			}
			logger.debug("Str2MD5sum[source:" + source + "][target:" + target + "]");
		} catch (Exception e) {
			logger.error("Str2MD5sum", e);
		} finally {
			md = null;
			bytes = null;
		}
		return target;
	}
	
	public static void main(String args[]){
		String aa = "5m6by3u4puwv5ulibin1qpzwnxx73ouulpzaejq3";
		System.out.println("password=" + Str2MD5sum(aa) + "\n");
	}
}
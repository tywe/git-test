package org.teng.java.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;

public class StringHandler extends StringUtils {

	public StringHandler() {}

	public static String getString(Object obj) {
		return obj == null ? null : String.valueOf(obj);
	}

	public static int getInt(Object obj) {
		return obj == null ? 0 : Integer.valueOf(obj.toString());
	}
	
	public static boolean isNullorEmpty(String string) {
		return (string == null || string.trim().length() == 0);
	}

	/**
	 * 
	 * @param o
	 * @return
	 */
	public static String nullToEmpty(Object o) {
		if (o == null) {
			return "";
		} else {
			return o.toString();
		}
	}

	public static long nullToLongZero(Object o) {
		if (o == null || o.toString().trim().length() == 0) {
			return 0;
		} else {
			return Long.parseLong(o.toString());
		}
	}

	public static int nullToIntegerZero(Object o) {
		if (o == null || o.toString().trim().length() == 0) {
			return 0;
		} else {
			return Integer.parseInt(o.toString());
		}
	}

	public static float nullToFloatZero(Object o) {
		if (o == null || o.toString().trim().length() == 0) {
			return 0.0f;
		} else {
			return Float.parseFloat(o.toString());
		}
	}

	public static double nullToDoubleZero(Object o) {
		if (o == null || o.toString().trim().length() == 0) {
			return 0.0;
		} else {
			return Double.parseDouble(o.toString());
		}
	}

	public static Double toObjectDouble(Object o) {
		return Double.valueOf(nullToDoubleZero(o) + "");
	}

	/**
	 * public static String enterToBR(Object o){ if(o == null) return ""; else{
	 * String str = o.toString(); str = str.replaceAll("\r\n", "<br>
	 * "); str = str.replaceAll(" ", "&nbsp"); return str; } }
	 */
	public static String formatNumber(String s, String style) {
		double d = nullToDoubleZero(s);
		DecimalFormat nf = new DecimalFormat(style);
		return nf.format(d);
	}

	public static String formatNum(String s, char c, int len) {
		String temp = "";
		for (int i = 0; i < len; i++) {
			temp = temp + c;
		}
		temp = temp + s;
		return temp.substring(temp.length() - len);
	}

	public static String formDataEncode(String s) {
		try {
			return java.net.URLEncoder.encode(s, "UTF-8");
		} catch (Exception e) {
			return s;
		}
	}

	public static BigDecimal stringToDecimal(String s) {
		if ((s != null) && (s.length() != 0)) {
			return new BigDecimal(s);
		} else {
			return new BigDecimal(0);
		}
	}

	public static boolean toBoolean(Object o) {
		if (o == null) {
			return false;
		}
		String s = o.toString().toLowerCase();
		if (s.equals("yes") || s.equals("true") || s.equals("1") || s.equals("y") || s.equals("t")
				|| s.equals("on")) {
			return true;
		} else {
			return false;
		}
	}

	public static java.sql.Date stringToDate(String s) {
		return java.sql.Date.valueOf(s);
	}

	public static int stringToInt(String s) {
		if (s.length() > 0) {
			return Integer.parseInt(s);
		} else {
			return 0;
		}
	}

	public static java.sql.Date GetCurrentDate() {
		return new java.sql.Date(System.currentTimeMillis());
	}

	public static String intercept(String reg, int len, String tail) {
		int i = reg.length();
		if (i <= len) {
			return reg;
		} else {
			return reg.substring(0, len) + tail;
		}
	}

	public static Long addLong(Long long1, Long long2) {
		return Long.valueOf(Long.parseLong(long1 + "") + Long.parseLong(long2 + "") + "");
	}

	public static String unicodeToGBK(String s) {
		String temp = nullToEmpty(s);
		if (temp.length() == 0) {
			temp = "";
		} else {
			try {
				temp = new String(temp.getBytes("ISO8859_1"), "GBK");
			} catch (UnsupportedEncodingException uee) {
				return s;
			}
		}
		return temp;
	}

	public static String relaceAll(String oldstring, String regex, String replacement) {
		int pos, oldpos;
		StringBuffer sb = new StringBuffer();
		oldpos = 0;
		pos = oldstring.indexOf(regex);
		while (pos > -1) {
			sb.append(oldstring.substring(oldpos, pos));
			oldpos = pos + regex.length();
			pos = oldstring.indexOf(regex, pos + 1);
			sb.append(replacement);
		}
		sb.append(oldstring.substring(oldpos, oldstring.length()));
		return sb.toString();
	}

	public static String[] toStringArray(String str, String delim) {
		if (str == null || str.length() == 0) {
			return null;
		}
		ArrayList<String> list = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(str, delim);
		while (st.hasMoreTokens()) {
			String value = st.nextToken();
			list.add(value);
		}
		String[] array = new String[list.size()];
		list.toArray(array);
		return array;
	}

	public static Long[] toLongArray(String str, String delim) {
		if (str == null || str.length() == 0) {
			return null;
		}
		ArrayList<Long> list = new ArrayList<Long>();
		StringTokenizer st = new StringTokenizer(str, delim);
		while (st.hasMoreTokens()) {
			String value = st.nextToken();
			list.add(Long.valueOf(value));
		}
		Long[] array = new Long[list.size()];
		list.toArray(array);
		return array;
	}

	public static String ListToString(Object[] list) {
		if (list == null)
			return "";

		StringBuilder resultBuilder = new StringBuilder();
		for (Object o : list) {
			resultBuilder.append("\n").append(o.toString());
		}
		return resultBuilder.toString();
	}
}

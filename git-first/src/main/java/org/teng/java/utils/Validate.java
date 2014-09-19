package org.teng.java.utils;


/**
 * Servlet implementation class ValidateCode
 */
public class Validate {

	public static boolean maxLength(String str, int maxlen) {
		if (str != null && str.length() > maxlen) {
			return false;
		}
		return true;
	}
	
	public static boolean lengthBetween(String str,int minLength,int maxLength)
	{
		String input=StringHandler.nullToEmpty(str);
		if((input.length()>=minLength)&&(input.length()<=maxLength))
			return true;
		return false;
	}

	public static boolean minLength(String str, int minlen) {
		if (str == null || str.length() < minlen) {
			return false;
		}
		return true;
	}

	public static boolean isMobileNum(String str) {
		if (str != null && str.length() == 11) {
			return str.matches("^(((13[0-9]{1})|15[0-9]{1}|18[0-9]{1}|14[0-9]{1})+\\d{8})$");
		} else {
			return false;
		}
	}

	/**
	 * 是否满足电话格式
	 * @param str
	 * @param nullable
	 * @return
	 */
	public static boolean isTelNum(String str, boolean nullable) {
		if (str != null && str.length() != 0) {
			return str.matches("^(([0\\+]\\d{2,3}-)?(0\\d{2,3})-)(\\d{7,8})(-(\\d{3,4}))?$");
		} else {
			return nullable;
		}
	}

	public static boolean isEmailAddr(String str) {
		if (str != null) {
			return str.matches("^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$");
		} else {
			return false;
		}
	}

	public static boolean validUserName(String str) {
		if (str != null) {
			return str.matches("^[a-zA-Z][a-zA-Z0-9_\\.]{2,15}$");
		} else {
			return false;
		}
	}

	public static boolean isNum(String str) {
		if (str != null) {
			return str.matches("(^[1-9]\\d*$)|([0])");
		} else {
			return false;
		}
	}
	
	/**
	 * 判断数字是否在指定区间范围内 [lower, upper]
	 * @param str	数字（字符串）
	 * @param lower	区间下限
	 * @param upper 区间上限
	 * @return
	 */
	public static boolean intervalNum(String str, int lower, int upper){
		if(isNum(str)){
			int strTOint = StringHandler.nullToIntegerZero(str);
			if(strTOint >= lower && strTOint <= upper){
				return true;
			}
		} 
		return false;
	}
	
	/**
	 * 判断数字是否在指定区间范围内(区间下限为1) [1, upper]
	 * @param str	数字（字符串）
	 * @param upper 区间上限
	 * @return
	 */
	public static boolean intervalNum(String str, int upper){
		if(isNum(str)){
			int strTOint = StringHandler.nullToIntegerZero(str);
			if(strTOint > 0 && strTOint <= upper){
				return true;
			}
		} 
		return false;
	}

	public static boolean isIDCard(String str) {
		if (str != null) {
			if (str.length() == 15 || str.length() == 18) {
				IDCardValidator iv = new IDCardValidator();
				return iv.validateIDCard(str);
			}
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(isNum("121233"));
		System.out.println(isNum("0"));
		System.out.println(isNum("0121233"));
		System.out.println(isNum("00"));
//		System.out.println(validID(".ertrwetwe"));
//		System.out.println(validID("qwewqrwretretryr"));
//		System.out.println(validID("qw"));
//		System.out.println(validID("qwewqrwretretryrtyutyiuyiouo"));
//		System.out.println(validID("12qwert"));
//		System.out.println(validID("a12345"));
//		System.out.println(validID("s1.sdf.xcv."));
//		System.out.println(validID("retrg.wer.qwe"));
//		System.out.println(validID("()erw"));
//		System.out.println(validID("aw()"));
//		System.out.println(validID("a/|-"));
//		System.out.println(validID("_sadfa"));
//		System.out.println(validID("wsedwq_"));
	}
}

package com.wsria.util.number;

import java.util.regex.Pattern;

/**
 * 数字工具
 *
 * @author HenryYan
 *
 */
public class NumericUtils {

	/**
	 * 判断是否为数字类型
	 * @param str	需要判断的字符串
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}
	
	/**
	 * 判断是否不为数字类型
	 * @param str	需要判断的字符串
	 */
	public static boolean isNotNumeric(String str) {
		return !isNumeric(str);
	}

}

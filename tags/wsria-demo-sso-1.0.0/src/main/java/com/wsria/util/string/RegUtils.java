package com.wsria.util.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式工具类
 *
 * @author HenryYan
 *
 */
public class RegUtils {

	/**
	 * 验证是否为中国的身份证号码
	 */
	public static boolean isIdNo(String idNo) {
		Pattern pattern = Pattern.compile("\\d{15}|\\d{17}([0-9]|X)");
		Matcher matcher = pattern.matcher(idNo);
		return matcher.matches();
	}

	public static void main(String[] args) {
		System.out.println(isIdNo("371522198707143"));
	}
	
}

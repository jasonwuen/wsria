package com.wsria.arch.util.string;

import org.apache.commons.lang.StringUtils;

/**
 * 字符串工具类
 *
 * @author HenryYan
 *
 */
public class StringUtil {

	/**
	 * 生成指定长度的字符串<br/>
	 * 例如生成10个0，batchCreateString("0", 5)<br/>，结果：00000
	 * 5个ab连接的字符串, batchCreateString("ab", 5)，结果：ababababab
	 * @param string	字符串
	 * @param times		生成字符串的次数
	 * @return
	 */
	public static String batchCreateString(String string, int times) {
		if (StringUtils.isEmpty(string)) {
			return StringUtils.EMPTY;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < times; i++) {
			sb.append(string);
		}
		return sb.toString();
	}
	
	/**
	 * 获得字符串数组的值
	 * 	以逗号分隔
	 * @param srcArray
	 * @return 以逗号分隔的值
	 */
	public static String arrayToString(String[] srcArray) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < srcArray.length; i++) {
			result.append(srcArray[i] + ",");
		}
		return deleteComma(result.toString());
	}
	
	/**
	 * 获得字符串数组的值
	 * 	以逗号分隔
	 * @param srcArray		数据对象
	 * @param addToPre		在单个字符串前面添加的字符
	 * @param addToBehind	在单个字符串后面添加的字符
	 * @return 以逗号分隔的值
	 */
	public static String arrayToString(String[] srcArray, String addToPre, String addToBehind) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < srcArray.length; i++) {
			result.append(StringUtils.defaultString(addToPre));
			result.append(srcArray[i]);
			result.append(StringUtils.defaultString(addToBehind));
			result.append(",");
		}
		return deleteComma(result.toString());
	}
	
	/**
	 * 删除字符串最后的逗号
	 * @param src	原始字符串
	 * @return	删除了逗号的字符串
	 * @see StringUtil#deleteSymbol(String, String)
	 */
	public static String deleteComma(String src) {
		return deleteSymbol(src, ",");
	}
	
	/**
	 * 删除字符串最后的分号
	 * @param src	原始字符串
	 * @return	删除了分号的字符串
	 * @see StringUtil#deleteSymbol(String, String)
	 */
	public static String deleteSemicolon(String src) {
		return deleteSymbol(src, ";");
	}
	
	/**
	 * 删除字符串最后的指定符号
	 * @param src	原始字符串
	 * @param symbol	要删除的符号
	 * @return	删除了<b>symbol</b>的字符串
	 */
	public static String deleteSymbol(String src, String symbol) {
		src = StringUtils.defaultString(src);
		if (!src.endsWith(symbol)) {
			return src;
		}
		src = src.substring(0, src.length() - 1);
		return src;
	}
	
}

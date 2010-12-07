package com.wsria.arch.util.string;

import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;

/**
 * HTML代码工具类
 *
 * @author HenryYan
 *
 */
public class HtmlUtil {

	/**
	 * 生成html的select元素
	 * @param datas	<option的value, option的text>
	 * @return	构建好的select元素html代码
	 */
	public static String generateSelect(Map<String, String> datas) {
		return generateSelect(datas, false);
	}
	
	/**
	 * 生成html的select元素
	 * @param 	datas	<option的value, option的text>
	 * @param	withSelectTip 是否在第一个选择中添加“请选择”
	 * @return	构建好的select元素html代码
	 */
	public static String generateSelect(Map<String, String> datas, boolean withSelectTip) {
		return generateSelect(datas, withSelectTip, null, true);
	}
	
	/**
	 * 生成html的select元素
	 * @param 	datas	<option的value, option的text>
	 * @param	withSelectTip 是否在第一个选择中添加“请选择”
	 * @param	defaultValue	默认选择的值
	 * @return	构建好的select元素html代码
	 */
	public static String generateSelect(Map<String, String> datas, boolean withSelectTip, String defaultValue) {
		return generateSelect(datas, withSelectTip, defaultValue, true);
	}
	
	/**
	 * 生成html的select元素
	 * @param 	datas	<option的value, option的text>
	 * @param	withSelectTip 是否在第一个选择中添加“请选择”
	 * @param	defaultValue	默认选择的值
	 * @param	setValue		是否设置option的value属性
	 * @return	构建好的select元素html代码
	 */
	public static String generateSelect(Map<String, String> datas, boolean withSelectTip, String defaultValue, boolean setValue) {
		StringBuilder sb = new StringBuilder();
		sb.append("<select>");
		
		if (withSelectTip) {
			sb.append("<option value=''>请选择</option>");
		}
		
		Set<Entry<String, String>> entrySet = datas.entrySet();
		String defaultHtml = "";
		for (Entry<String, String> entry : entrySet) {
			defaultHtml = "";
			if (StringUtils.isNotBlank(defaultValue) && entry.getKey().equals(defaultValue)) {
				defaultHtml = " selected";
			}
			sb.append("<option");
			if (setValue) {
				sb.append(" value='" + entry.getKey() + "'");
			}
			sb.append(defaultHtml + ">" + entry.getValue() + "</option>");
		}
		sb.append("</select>");
		return sb.toString();
	}

	/**
	 * 不设置option的value属性
	 * @param datas	<option的value, option的text>
	 * @return	构建好的select元素html代码
	 */
	public static String generateSelectNoValue(Map<String, String> datas) {
		return generateSelect(datas, false, null, false);
	}
	
}

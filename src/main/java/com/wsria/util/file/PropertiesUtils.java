package com.wsria.util.file;

import java.io.IOException;
import java.util.Properties;

/**
 * 读取配置文件工具类
 *
 * @author HenryYan
 *
 */
public class PropertiesUtils {
	
	/**
	 * 读取系统配置文件中的值
	 * @param key	键
	 * @return	在系统配置文件中key对应的value
	 * @throws IOException 找不到系统配置文件时
	 */
	public static String getSystemProp(String key) throws IOException {
		String[] propFiles = new String[]{"application.local.properties", "application.properties"};
		Properties property = org.springside.modules.utils.PropertiesUtils.loadProperties(propFiles);
		Object object = property.get(key);
		if (object == null) {
			return null;
		} else {
			return object.toString();
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		String systemProp = getSystemProp("channel.bankcard");
		System.out.println(systemProp);
	}
	
}
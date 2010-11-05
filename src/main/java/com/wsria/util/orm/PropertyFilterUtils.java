package com.wsria.util.orm;

import java.util.ArrayList;
import java.util.List;

import org.springside.modules.orm.PropertyFilter;

/**
 * springside3的条件过滤工具
 *
 * @author HenryYan
 *
 */
public class PropertyFilterUtils {

	/**
	 * 创建一个空的条件过滤集合
	 */
	public static List<PropertyFilter> createEmptyFilter() {
		return new ArrayList<PropertyFilter>();
	}
	
}

package com.wsria.demo.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.wsria.demo.entity.base.DataLibrary;

/**
 * 数据字典工具类
 *
 * @author HenryYan
 *
 */
public class DataLibraryUtil {

	private static Map<String, DataLibrary> allDatas = new HashMap<String, DataLibrary>();
	
	private static Map<String, String> allDataNameValues = new HashMap<String, String>();

	private static Map<String, List<DataLibrary>> groupByType = new HashMap<String, List<DataLibrary>>();
	
	private static Map<String, Map<String, String>> groupByTypeNameValues = new HashMap<String, Map<String, String>>();
	
	/**
	 * 清空数据字典对象
	 */
	public static void clearDatas() {
		allDatas = new HashMap<String, DataLibrary>();
		allDataNameValues = new HashMap<String, String>();
		groupByType = new HashMap<String, List<DataLibrary>>();
		groupByTypeNameValues = new HashMap<String, Map<String, String>>();
	}

	/**
	 * 根据library类型分组添加到Map中
	 * @param datalibrary
	 * @param key
	 */
	public static void addToTypeMap(DataLibrary datalibrary) {
		String librarytype = datalibrary.getLibraryType();
		List<DataLibrary> list = groupByType.get(librarytype);
		if (list == null) {
			list = new ArrayList<DataLibrary>();
			groupByType.put(librarytype, list);
		}
		list.add(datalibrary);
		
		// 大类
		Map<String, String> map = groupByTypeNameValues.get(librarytype);
		if (map == null) {
			map = new HashMap<String, String>();
			groupByTypeNameValues.put(librarytype, map);
		}
		map.put(datalibrary.getLibraryCode(), datalibrary.getLibraryValue());
	}

	/**
	 * 从分组的Map中删除
	 * @param code
	 */
	private static void remoteFromTypeMap(String code) {
		DataLibrary datalibrary = allDatas.get(code);
		List<DataLibrary> list = groupByType.get(datalibrary.getLibraryType());
		Iterator<DataLibrary> iterator = list.iterator();
		while (iterator.hasNext()) {
			if (iterator.next().getLibraryCode().equals(code)) {
				iterator.remove();
			}
		}
	}

	/**
	 * 获取数据字典列表
	 * @param type	字典类型
	 * @return
	 */
	public static List<DataLibrary> getTypeGroupDataLibraryList(String type) {
		return groupByType.get(type);
	}

	/**
	 * 获取数据字典列表
	 * @return	Map<字典code, DataLibrary对象>
	 */
	public synchronized static Map<String, DataLibrary> getDataLibraryList() {
		return allDatas;
	}

	/**
	 * 获取数据字典列表
	 * @return	Map<字典code, 字典中文名称>
	 */
	public static Map<String, String> getAllDataNameValues() {
		return allDataNameValues;
	}

	/**
	 * 根据类型分组的列表
	 * @return	 Map<组名, List<DataLibrary对象>>
	 */
	public synchronized static Map<String, List<DataLibrary>> getTypeGroupDataLibrary() {
		return groupByType;
	}

	/**
	 * 根据类型分组的列表
	 * @return	Map<String, Map<字典code, 字典中文名称>>
	 */
	public static Map<String, Map<String, String>> getGroupByTypeNameValues() {
		return groupByTypeNameValues;
	}

	/**
	 * 通过code获取数组字典
	 * @param code	library code
	 * @return
	 */
	public static DataLibrary getDataLibraryByCode(String code) {
		return allDatas.get(code);
	}
	
	/**
	 * 通过code获取数据字典名称
	 * @param code	library code
	 * @return 如果没有返回空字符串
	 */
	public static String getDataLibraryNameByCode(String code) {
		DataLibrary datalibrary = allDatas.get(code);
		if (datalibrary == null) {
			return StringUtils.EMPTY;
		}
		return StringUtils.defaultIfEmpty(datalibrary.getLibraryValue(), "");
	}
	
	/**
	 * 获取一组数据字典
	 * @param type	字典类型
	 * @return	Map<字典code, 字典中文名称>
	 */
	public static Map<String, String> getByGroup(String type) {
		return groupByTypeNameValues.get(type);
	}

	/**
	 * 向缓存内添加数据字典
	 * 
	 * @param datalibrary
	 */
	public static void addDataLibrary(DataLibrary datalibrary) {
		String key = datalibrary.getLibraryCode();
		allDatas.put(key, datalibrary);
		allDataNameValues.put(datalibrary.getLibraryCode(), datalibrary.getLibraryValue());
		addToTypeMap(datalibrary);
	}

	/**
	 * 删除缓存内 key为 code的数据字典
	 * 
	 * @param code
	 */
	public static void deleteDataLibrary(String code) {
		remoteFromTypeMap(code);
		allDatas.remove(code);
	}

	/**
	 * 判断缓存内是否存在值为code的键值
	 * 
	 * @param code
	 * @return
	 */
	public static boolean existDataLibrary(String code) {
		return allDatas.containsKey(code);
	}

	/**
	 * 判断缓存内是否存在datalibrary对象
	 * 
	 * @param datalibrary
	 * @return
	 */
	public static boolean existDataLibrary(DataLibrary datalibrary) {
		return allDatas.containsValue(datalibrary);
	}

	/**
	 * 转换成Map格式数据
	 * @param dls
	 * @return	Map格式数据
	 */
	public static Map<String, String> parseToJson(List<DataLibrary> dls) {
		Map<String, String> result = new HashMap<String, String>();
		for (DataLibrary dl : dls) {
			result.put(dl.getLibraryCode(), dl.getLibraryValue());
		}
		return result;
	}
	
	/**
	 * 使用数据字典类型和编码查询是否存在于数据字典
	 * @param type	字典类型
	 * @param code	字典编码
	 * @return	存在true，否则false
	 */
	public static boolean checkExist(String type, String code) {
		List<DataLibrary> types = getTypeGroupDataLibraryList(type);
		if (types.isEmpty()) {
			return false;
		}
		for (DataLibrary datalibrary : types) {
			if (code.equals(datalibrary.getLibraryCode())) {
				return true;
			}
		}
		return false;
	}

}

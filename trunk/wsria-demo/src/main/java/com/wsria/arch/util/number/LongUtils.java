package com.wsria.arch.util.number;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * Long对象工具
 *
 * @author HenryYan
 *
 */
public class LongUtils {

	/**
	 * 判断Long型数据是否为NULL或者0
	 * @param number
	 * @return
	 */
	public static boolean isNullOrZero(Long number) {
		if (number == null) {
			return true;
		}
		return number == 0 ? true : false;
	}

	/**
	 * 判断Long型数据是否为空
	 * @param number
	 * @return
	 */
	public static boolean isEmpty(Long number) {
		return number == null ? true : false;
	}

	/**
	 * 判断Long型数据是否不为空
	 * @param number
	 * @return
	 */
	public static boolean isNotEmpty(Long number) {
		return !isEmpty(number);
	}

	/**
	 * 判断某字符串是否可转化为Long
	 * @param str	判断一个字符串是否为long型
	 * @return	true|false
	 */
	public static boolean isLongType(String str) {
		try {
			Long.valueOf(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 如果数据为NULL返回0，否则直接返回该数据
	 * @param number	Long型数字
	 * @return	为空返回0
	 */
	public static Long getValueIfEmptyZero(Long number) {
		return isEmpty(number) ? 0l : number;
	}
	
	/**
	 * 将List格式转换为数组格式
	 * @param numbers	数字集合
	 * @return	为空时返回NULL
	 */
	public static Long[] convertArray(List<Long> numbers) {
		if (numbers == null || numbers.isEmpty()) {
			return null;
		}
		Long[] results = new Long[numbers.size()];
		for (int i = 0; i < results.length; i++) {
			results[i] = numbers.get(i);
		}
		return results;
	}
	
	/**
	 * 将一组以逗号分隔的字符串转换为数组
	 * @param numbers	以逗号分隔的字符串
	 * @return	为空时返回NULL
	 */
	public static Long[] convertArray(String numbers) {
		if (StringUtils.isBlank(numbers)) {
			return null;
		}
		List<Long> longs = new ArrayList<Long>();
		String[] strNumbers = numbers.split(",");
		for (String string : strNumbers) {
			longs.add(new Long(string));
		}
		return convertArray(longs);
	}
	
	/**
	 * 把一组不规则的数字整理成以区间划分的数组列表，例如：<br/>
	 * <b>需要整理的数字</b>：List{3, 4, 6, 7, 8, 10, 11, 12, 14}<br/><b>整理后的结果</b>：List{[3, 4], [6, 8], [10, 12], [14, 14]}
	 * @param numbers
	 */
	public static List<Long[]> intervalList(List<Long> numbers) {
		List<List<Long>> numberArray = splitArray(numbers);
		
		// 到此得出的结果为：[[3, 4], [6, 7, 8], [10, 11]]
		List<Long[]> result = new ArrayList<Long[]>();
		for (int i = 0; i < numberArray.size(); i++) {
			List<Long> list = numberArray.get(i);
			Long[] longs = new Long[] {list.get(0), list.get(list.size() - 1)};
			result.add(longs);
		}
		return result;
	}

	/**
	 * 把一组不规则的数据整理成规则数据例如：<br/>
	 * <b>需要整理的数字</b>：List{3, 4, 6, 7, 8, 10, 11, 12, 14}<br/><b>整理后的结果</b>：List{[3, 4], [6, 7, 8], [10, 11, 12], [14, 14]}
	 * @param numbers	一组不规则的Long型数字
	 */
	public static List<List<Long>> splitArray(List<Long> numbers) {
		List<List<Long>> numberArray = new ArrayList<List<Long>>();
		Long last = 0l;
		List<Long> tempNumber = null;
		for (int i = 0; i < numbers.size(); i++) {
			Long number = numbers.get(i);
			
			// 添加一组数据到结果中并重置tempNumber为null
			if (i != 0 && number - 1 != last) {
				numberArray.add(tempNumber);
				tempNumber = null;
			}
			
			// 创建新的tempNumber，添加数字到临时结果中
			if (last != number) {
				if (tempNumber == null) {
					tempNumber = new ArrayList<Long>();
				}
				tempNumber.add(number);
				last = number;
			}
			
			// 添加最后一组数据
			if (i == numbers.size() - 1) {
				numberArray.add(tempNumber);
			}
			
		}
		return numberArray;
	}

}

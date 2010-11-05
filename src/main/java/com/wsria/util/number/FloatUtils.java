package com.wsria.util.number;

/**
 * Float工具类
 *
 * @author HenryYan
 *
 */
public class FloatUtils {

	/**
	 * 判断Long型数据是否为NULL或者0
	 * @param number
	 * @return
	 */
	public static boolean isNullOrZero(Float number) {
		if (number == null) {
			return true;
		}
		return number == 0.0 ? true : false;
	}
	
	/**
	 * 判断Float型数据是否为空
	 * @param number
	 * @return
	 */
	public static boolean isEmpty(Float number) {
		return number == null ? true : false;
	}
	
	/**
	 * 判断Float型数据是否不为空
	 * @param number
	 * @return
	 */
	public static boolean isNotEmpty(Float number) {
		return !isEmpty(number);
	}
	
}

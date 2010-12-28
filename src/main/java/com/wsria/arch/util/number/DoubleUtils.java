package com.wsria.arch.util.number;

/**
 * 
 *
 * @author HenryYan
 *
 */
public class DoubleUtils {

	/**
	 * 判断Long型数据是否为NULL或者0
	 * @param number
	 * @return
	 */
	public static boolean isNullOrZero(Double number) {
		if (number == null) {
			return true;
		}
		return number == 0.0 ? true : false;
	}

	/**
	 * 判断Double型数据是否为空
	 * @param number
	 * @return
	 */
	public static boolean isEmpty(Double number) {
		return number == null ? true : false;
	}

	/**
	 * 判断Double型数据是否不为空
	 * @param number
	 * @return
	 */
	public static boolean isNotEmpty(Double number) {
		return !isEmpty(number);
	}

	/**
	 * 如果数据为NULL返回0，否则直接返回该数据
	 * @param number
	 * @return
	 */
	public static Double getValueIfEmptyZero(Double number) {
		return isEmpty(number) ? 0d : number;
	}

	/**
	 * 将对象类型转换为Double类型，如果为空设置为0
	 * @param objs
	 * @return
	 */
	public static Double[] paraseArray(Object[] objs) {
		Double[] doubles = new Double[objs.length];
		for (int i = 0; i < objs.length; i++) {
			Object object = objs[i];
			doubles[i] = object == null ? 0 : Double.valueOf(object.toString());
		}
		return doubles;
	}

	/**
	* 判断一字符串是否可转化为Double
	* @param str
	* @return   可转为true
	*/
	public static Boolean isDoubleType(String str) {
		try {
			Double.valueOf(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}

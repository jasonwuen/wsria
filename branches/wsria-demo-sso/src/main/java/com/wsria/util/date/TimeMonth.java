package com.wsria.util.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

/**
 * 查询每个月的开始和结束日期
 *
 * @author HenryYan
 *
 */
public class TimeMonth {

	protected Calendar date = null;

	protected final static Integer MONTH_COUNT = 12;

	protected SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	protected Map<Integer, String[]> monthDateMap = new LinkedHashMap<Integer, String[]>();

	/**
	 * 唯一构造方法
	 * @param year	年份
	 */
	public TimeMonth(int year) {

		date = Calendar.getInstance();
		date.set(Calendar.YEAR, year);
		for (int i = 0; i < MONTH_COUNT; i++) {

			date.set(Calendar.MONTH, i);
			int minMonthDate = date.getActualMinimum(Calendar.DAY_OF_MONTH);
			date.set(Calendar.DATE, minMonthDate);
			String startMonth = dateFormat.format(date.getTime());
			int maxMonthDate = date.getActualMaximum(Calendar.DAY_OF_MONTH);
			date.set(Calendar.DATE, maxMonthDate);
			String endMonth = dateFormat.format(date.getTime());

			String monthDate[] = { startMonth, endMonth };
			monthDateMap.put(i + 1, monthDate);
		}
	}

	/**
	 * 要获取的 monthDateMap
	 * 
	 * @return monthDateMap
	 */
	public Map<Integer, String[]> getMonthDateMap() {
		return monthDateMap;
	}
	
	/**
	 * 获取本月的开始和结束日期
	 * 
	 * @return monthDateMap
	 */
	public String[] getMonthDates(Integer month) {
		return monthDateMap.get(month);
	}

	/**
	 * 要设置的 monthDateMap
	 * 
	 * @param monthDateMap
	 */
	public void setMonthDateMap(Map<Integer, String[]> monthDateMap) {
		this.monthDateMap = monthDateMap;
	}

	public static void main(String[] args) {

		TimeMonth mon = new TimeMonth(2010);
		Map<Integer, String[]> monthDate = mon.getMonthDateMap();

		Set<Entry<Integer, String[]>> set = monthDate.entrySet();
		for (Entry<Integer, String[]> entry : set) {
			Integer monthNum = entry.getKey();
			String[] months = entry.getValue();
			System.out.println("第 " + monthNum + " 个月的开始日期为：" + months[0] + "，结束日期为：" + months[1]);
		}
	}

}

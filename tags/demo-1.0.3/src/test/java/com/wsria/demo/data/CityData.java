package com.wsria.demo.data;

import java.util.Random;

import com.wsria.demo.entity.jstree.City;


/**
 * 城市测试数据生成
 *
 * @author HenryYan
 *
 */
public class CityData {

	/**
	 * 获取随机区县
	 * @return
	 */
	public static City getRandomDistrict() {
		String cityName = getRundomDistrictName();

		City city = new City();
		city.setCityName(cityName);
		city.setSuperId(1l);
		city.setRemark("我是：" + cityName);

		return city;
	}
	
	/**
	 * 获取随即的区县名称
	 * @return
	 */
	private static String getRundomDistrictName() {
		String[] names = {"长宁区", "徐汇区", "普陀区", "浦东新区", "卢湾区", "虹口区"};
		Random rd = new Random();
		return names[rd.nextInt(names.length)];
	}
	
	public static void main(String[] args) {
		System.out.println(getRundomDistrictName());
		System.out.println(getRundomDistrictName());
		System.out.println(getRundomDistrictName());
		System.out.println(getRundomDistrictName());
		System.out.println(getRundomDistrictName());
		System.out.println(getRundomDistrictName());
	}
	
}

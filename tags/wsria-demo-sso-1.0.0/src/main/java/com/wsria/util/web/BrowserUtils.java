package com.wsria.util.web;

import javax.servlet.http.HttpServletRequest;

/**
 * 和浏览器有关的工具类
 *
 * @author HenryYan
 *
 */
public class BrowserUtils {

	/**
	 * 判断是否为IE浏览器
	 */
	public static boolean isIE(HttpServletRequest request) {
		String agent = getAgent(request);
		if (null != agent && -1 != agent.indexOf("MSIE")) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否为FireFox浏览器
	 */
	public static boolean isMozilla(HttpServletRequest request) {
		String agent = getAgent(request);
		if (null != agent && -1 != agent.indexOf("Mozilla")) {
			return true;
		}
		return false;
	}

	/**
	 * 是否为Chrome或者Safari
	 */
	public static boolean isWebKit(HttpServletRequest request) {
		String agent = getAgent(request);
		if (null != agent && -1 != agent.indexOf("WebKit")) {
			return true;
		}
		return false;
	}

	/**
	 * 获取浏览器USER-AGENT
	 */
	public static String getAgent(HttpServletRequest request) {
		String agent = request.getHeader("USER-AGENT");
		return agent;
	}

}

package com.wsria.util.ajax;

import javax.servlet.http.HttpServletRequest;

/**
 * 和Ajax有关的方法
 *
 * @author HenryYan
 *
 */
public class AjaxUtil {
	
	/**
	 * 判断是否为Ajax请求
	 * @param request	HttpServletRequest
	 * @return	是true, 否false
	 */
	public static boolean isAjaxRequest(HttpServletRequest request) {
		String requestType = request.getHeader("X-Requested-With");
		if (requestType != null && requestType.equals("XMLHttpRequest")) {
			return true;
		} else {
			return false;
		}
	}
	
}

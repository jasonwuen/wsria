package com.wsria.arch.util.json;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p><b>Title:</b>JSON工具类</p>
 * <p><b>Description：</b></p>
 *
 * @author 闫洪磊
 */
public class JSONUtil {
	
	static Logger logger = LoggerFactory.getLogger(JSONUtil.class);

	/**
	 * 读取请求参数解析为JSON数据格式
	 *
	 * @param request
	 * @return json格式的String对象
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static JSONObject readJson(HttpServletRequest request) throws Exception {
		JSONObject jsonObject = null;
		try {
			Map<String, String[]> parameterMap = request.getParameterMap();
			jsonObject = pareseJson(parameterMap);
			logger.debug("从客户端获得json={}", jsonObject.toString());
		} catch (Exception e) {
			logger.error("获取json数据出错，错误信息如下：\n\t{}", e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return jsonObject;
	}

	/**
	 * 把参数MAP转换成Json对象
	 * @param jsonObject
	 * @param parameterMap
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private static JSONObject pareseJson(Map<String, String[]> parameterMap)
			throws UnsupportedEncodingException {
		JSONObject jsonObject = new JSONObject();
		Iterator<String> paIter = parameterMap.keySet().iterator();
		while (paIter.hasNext()) {
			String key = paIter.next().toString();
			String[] values = (String[])parameterMap.get(key);
			logger.debug("key：{}, value：{}" , key, values[0]);
			if(values.length == 1) {
				jsonObject.accumulate(key, java.net.URLDecoder.decode(values[0], "UTF-8"));
			} else if(values.length > 1) {
				jsonObject.accumulate(key, values);
			}
		}
		return jsonObject;
	}

}

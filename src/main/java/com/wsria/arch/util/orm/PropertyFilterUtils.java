package com.wsria.arch.util.orm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.PropertyFilter.MatchType;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import com.wsria.arch.util.json.JSONUtil;
import com.wsria.arch.util.string.StringUtil;

/**
 * springside3的条件过滤工具
 *
 * @author HenryYan
 *
 */
public class PropertyFilterUtils {

	/*
	 * 比较符号缩写和数据库逻辑符号对应
	 */
	private static Map<MatchType, String> matchTypes = new HashMap<MatchType, String>();

	/**
	 * springside 中java对象类型和springside中简写类型
	 */
	public static Map<String, String> JAVA_MATCH_SS_SHORT_TYPE = new HashMap<String, String>();

	/**
	 * jqGrid和springside逻辑符号对应对象
	 */
	public static Map<String, String> JQGRID_MATCH_SS_LOGICCHARS = new HashMap<String, String>();

	static {
		matchTypes.put(MatchType.EQ, "=");
		matchTypes.put(MatchType.NE, "!=");
		matchTypes.put(MatchType.LIKE, "like");
		matchTypes.put(MatchType.LT, "<");
		matchTypes.put(MatchType.LE, "<=");
		matchTypes.put(MatchType.GT, ">");
		matchTypes.put(MatchType.GE, ">=");

		// 初始化类型对应
		JAVA_MATCH_SS_SHORT_TYPE.put("String", "S");
		JAVA_MATCH_SS_SHORT_TYPE.put("int", "I");
		JAVA_MATCH_SS_SHORT_TYPE.put("Integer", "I");
		JAVA_MATCH_SS_SHORT_TYPE.put("long", "L");
		JAVA_MATCH_SS_SHORT_TYPE.put("Long", "L");
		JAVA_MATCH_SS_SHORT_TYPE.put("double", "N");
		JAVA_MATCH_SS_SHORT_TYPE.put("Double", "N");
		JAVA_MATCH_SS_SHORT_TYPE.put("Date", "D");
		JAVA_MATCH_SS_SHORT_TYPE.put("boolean", "B");
		JAVA_MATCH_SS_SHORT_TYPE.put("Boolean", "B");

		JQGRID_MATCH_SS_LOGICCHARS.put("eq", MatchType.EQ.toString());
		JQGRID_MATCH_SS_LOGICCHARS.put("ne", MatchType.NE.toString());
		JQGRID_MATCH_SS_LOGICCHARS.put("cn", MatchType.LIKE.toString());
		JQGRID_MATCH_SS_LOGICCHARS.put("lt", MatchType.LT.toString());
		JQGRID_MATCH_SS_LOGICCHARS.put("gt", MatchType.GT.toString());
		JQGRID_MATCH_SS_LOGICCHARS.put("le", MatchType.LE.toString());
		JQGRID_MATCH_SS_LOGICCHARS.put("ge", MatchType.GE.toString());
	}

	/**
	 * 获取{@link MatchType}对应的逻辑符号
	 * @param matchtype {@link MatchType}
	 */
	public static String matchType(MatchType matchtype) {
		return matchTypes.get(matchtype);
	}

	/**
	 * 创建一个空的条件过滤集合
	 */
	public static List<PropertyFilter> createEmptyFilter() {
		return new ArrayList<PropertyFilter>();
	}

	/**
	 * 使用HQL拼接filters中的条件
	 * @param filters	过滤条件
	 * @return	拼接的HQL条件语句
	 */
	public static String jointHql(List<PropertyFilter> filters) {
		String hql = "";
		for (PropertyFilter propertyFilter : filters) {
			MatchType matchType = propertyFilter.getMatchType();
			Object matchValue = propertyFilter.getMatchValue();
			// 特殊处理LIKE
			if (matchType.equals(MatchType.LIKE)) {
				matchValue = "%" + matchValue + "%";
			}
			hql += " and " + propertyFilter.getPropertyName() + " " + PropertyFilterUtils.matchType(matchType) + "?";
		}
		return hql;
	}

	/**
	 * 使用HQL拼接filters中的条件
	 * @param filters	过滤条件
	 * @param values	一个存放查询值的List对象
	 * @return	拼接的HQL条件语句
	 */
	public static String jointHql(List<PropertyFilter> filters, List<Object> values) {
		String hql = "";
		for (PropertyFilter propertyFilter : filters) {
			MatchType matchType = propertyFilter.getMatchType();
			Object matchValue = propertyFilter.getMatchValue();
			// 特殊处理LIKE
			if (matchType.equals(MatchType.LIKE)) {
				matchValue = "%" + matchValue + "%";
			}
			hql += " and o." + propertyFilter.getPropertyName() + " " + PropertyFilterUtils.matchType(matchType) + "?";
			values.add(matchValue);
		}
		return hql;
	}

	/**
	 * 根据指定实体和字段通过构造方法查询对象（可以提升性能）
	 * @param page			分页对象
	 * @param filters		{@link PropertyFilter}
	 * @param clazz			实体Class
	 * @param fields		查询的属性，必需有对应的构造方法
	 * @param filterValues	过滤条件对应的value
	 * @return	完整的HQL语句，过滤条件的值自动设置在filterValues中
	 */
	public static String createHqlForCustomFields(Page<? extends IdEntity> page, List<PropertyFilter> filters,
			Class<? extends IdEntity> clazz, String[] fields, List<Object> filterValues) {
		String selectFields = StringUtil.arrayToString(fields, "o.", null);
		String className = clazz.getSimpleName();
		String hql = "select new " + className + "(" + selectFields + ") from " + className + " o where 1=1";
		hql += PropertyFilterUtils.jointHql(filters, filterValues);
		if (page.isOrderBySetted()) {
			hql += " order by o." + page.getOrderBy() + " " + page.getOrder();
		}
		return hql;
	}

	/**
	 * 处理jqGrid查询
	 * @param page		SS中的{@link Page}对象
	 * @param clazz		实体类型
	 * @param filters	{@link PropertyFilter}集合
	 * @throws Exception
	 */
	public static void handleFilter(Page<?> page, Class<?> clazz, List<PropertyFilter> filters) throws Exception {

		// 读取JSON数据
		JSONObject params = JSONUtil.readJson(Struts2Utils.getRequest());

		// 设置每页条数

		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.ASC);
		}
		// 设置查询条件
		if (page.isSearch()) {
			// 是否为多个查询条件
			boolean multiFilter = false;
			Object filtersObj = params.get("filters");
			if (filtersObj != null) {
				multiFilter = true;
			}

			// 处理多个查询条件
			if (multiFilter) {
				JSONObject filtersJson = params.getJSONObject("filters");
				JSONArray rules = filtersJson.getJSONArray("rules");

				for (Object object : rules) {
					JSONObject rule = (JSONObject) object;

					String searchField = rule.getString("field");
					String isos = java.net.URLDecoder.decode(rule.getString("data"), "ISO8859-1");
					String searchString = java.net.URLDecoder.decode(isos, "UTF-8");
					String searchOper = rule.getString("op");

					// LIKES_NAME_OR_LOGIN_NAME
					//分析参数Map,构造PropertyFilter列表
					// 根据属性类型自动获取SS的简短类型
					if (searchField.contains(".")) {
						String[] split = searchField.split("\\.");
						Class<?> joinType = clazz.getDeclaredField(split[0]).getType();
						Class<?> joinFieldType = joinType.getDeclaredField(split[1]).getType();
						String ssType = JAVA_MATCH_SS_SHORT_TYPE.get(joinFieldType.getSimpleName());
						String filterName = JQGRID_MATCH_SS_LOGICCHARS.get(searchOper) + ssType + "_" + searchField;
						PropertyFilter filter = new PropertyFilter(filterName, searchString);
						filters.add(filter);
					} else {
						Class<?> fieldClazz = clazz.getDeclaredField(searchField).getType();
						String ssType = JAVA_MATCH_SS_SHORT_TYPE.get(fieldClazz.getSimpleName());
						String filterName = JQGRID_MATCH_SS_LOGICCHARS.get(searchOper) + ssType + "_" + searchField;
						String value = searchString;
						//如果value值为空,则忽略此filter.
						if (StringUtils.isNotBlank(value)) {
							PropertyFilter filter = new PropertyFilter(filterName, value);
							filters.add(filter);
						}
					}

				}
			} else { // 单个条件
				Object searchFieldObj = params.get("searchField");
				String searchField = searchFieldObj.toString();
				String searchString = params.getString("searchString");
				String searchOper = params.getString("searchOper");
				if (StringUtils.isNotBlank(searchString)) {
					if (searchField.contains(".")) {
						String[] split = searchField.split("\\.");
						Class<?> joinType = clazz.getDeclaredField(split[0]).getType();
						Class<?> joinFieldType = joinType.getDeclaredField(split[1]).getType();
						String ssType = JAVA_MATCH_SS_SHORT_TYPE.get(joinFieldType.getSimpleName());
						String filterName = JQGRID_MATCH_SS_LOGICCHARS.get(searchOper) + ssType + "_" + searchField;
						PropertyFilter filter = new PropertyFilter(filterName, searchString);
						filters.add(filter);
					} else {
						Class<?> fieldClazz = clazz.getDeclaredField(searchField).getType();
						String ssType = JAVA_MATCH_SS_SHORT_TYPE.get(fieldClazz.getSimpleName());
						String filterName = JQGRID_MATCH_SS_LOGICCHARS.get(searchOper) + ssType + "_" + searchField;
						PropertyFilter filter = new PropertyFilter(filterName, searchString);
						filters.add(filter);
					}
				}
			}
		}
	}

}

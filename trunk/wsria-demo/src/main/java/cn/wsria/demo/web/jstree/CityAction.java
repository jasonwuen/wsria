package cn.wsria.demo.web.jstree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import cn.wsria.demo.entity.jstree.City;
import cn.wsria.demo.service.jstree.CityManager;
import cn.wsria.demo.web.CrudActionSupport;

/**
 * 城市Action
 *
 * @author HenryYan
 *
 */
@Namespace("/demo/jstree")
@Results(value = { @Result(name = CrudActionSupport.JSON, type = CrudActionSupport.JSON) })
public class CityAction extends CrudActionSupport<City> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	protected CityManager manager;
	
	//-- 页面属性 --//
	private Long id;
	private Long parentId;
	private City entity;
	
	//-- ModelDriven 与 Preparable函数 --//
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public City getModel() {
		return entity;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			entity = manager.getCity(id);
		} else {
			entity = new City();
		}
	}

	@Override
	public String delete() throws Exception {
		manager.deleteCity(id);
		return null;
	}

	@Override
	public String input() throws Exception {
		return null;
	}

	@Override
	public String list() throws Exception {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			List<City> findChildCity = manager.findChildCity(parentId);
			Map<String, Object> attrTempMap = null;
			
			for (City city : findChildCity) {
				Map<String, Object> tempMap = new HashMap<String, Object>();
				Long countChildCity = manager.countChildCity(city.getId());
				
				// 分为两种情况：有下属区县、没有下属区县
				if (countChildCity > 0) {
					// 关闭状态，可以触发ajax load 事件
					tempMap.put("state", "closed");
					
					Map<String, Object> subMap = new HashMap<String, Object>();
					// 节点的显示文本
					subMap.put("title", city.getCityName());
					
					// 设置属性集合
					Map<String, Object> singleAttr = new HashMap<String, Object>();
					singleAttr.put("title", "共 " + countChildCity + " 个下属区县");
					subMap.put("attr", singleAttr);
					tempMap.put("data", subMap);
				} else {
					tempMap.put("data", city.getCityName());
				}
				
				// 设置属性
				attrTempMap = setAttrs(city);
				tempMap.put("attr", attrTempMap);
				
				list.add(tempMap);
			}
		} catch (Exception e) {
			logger.error("获取下属区县出错：{}", e.getMessage(), e);
		}
		System.out.println(list);
		Struts2Utils.renderJson(list);
		return CrudActionSupport.JSON;
	}
	
	private Map<String, Object> setAttrs(City city) {
		Map<String, Object> tempMap = new HashMap<String, Object>();
		tempMap.put("id", city.getId());
		return tempMap;
	}

	@Override
	public String save() throws Exception {
		manager.saveCity(entity);
		return null;
	}

}

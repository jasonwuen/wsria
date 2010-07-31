package cn.wsria.demo.web.jstree;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

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
	private City entity;
	
	//-- ModelDriven 与 Preparable函数 --//
	public void setId(Long id) {
		this.id = id;
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
		return null;
	}

	@Override
	public String save() throws Exception {
		manager.saveCity(entity);
		return null;
	}

}

package cn.wsria.demo.service.jstree;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.wsria.demo.dao.jstree.CityDao;
import cn.wsria.demo.entity.jstree.City;

/**
 * 城市实体管理类.
 *
 * @author HenryYan
 *
 */
@Component
public class CityManager implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	protected CityDao entityDao;

	/**
	 * 保存城市
	 * @param city
	 */
	public void saveCity(City city) {
		entityDao.save(city);
	}
	
	/**
	 * 通过ID获取城市
	 * @param id
	 * @return
	 */
	public City getCity(Long id) {
		return entityDao.get(id);
	}
	
	/**
	 * 删除城市
	 * @param id
	 */
	public void deleteCity(Long id) {
		entityDao.delete(id);
	}
	
	/**
	 * 查询所有的下属区县
	 * @param superId
	 * @return
	 */
	public List<City> findChildCity(Long superId) {
		return entityDao.findChildCity(superId);
	}
	
	/**
	 * 统计下属区县数量
	 * @param superId
	 * @return
	 */
	public Long countChildCity(Long superId) {
		return entityDao.countChildCity(superId);
	}
	
}

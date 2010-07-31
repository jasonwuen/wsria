package cn.wsria.demo.unit.dao.city;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.wsria.demo.dao.jstree.CityDao;
import cn.wsria.demo.data.CityData;
import cn.wsria.demo.entity.jstree.City;
import cn.wsria.demo.unit.dao.BaseTxTestCase;

public class CityDaoTest extends BaseTxTestCase {

	@Autowired
	public CityDao entityDao;

	@Test
	//如果你需要真正插入数据库,将Rollback设为false
	//@Rollback(false) 
	public void crudEntity() {
		
		// 获取城市
		City topCity = entityDao.get(1l);
		assertNotNull(topCity);
		
		// 添加下级城市
		int n = 3;
		for (int i = 0; i < n; i++) {
			City city = CityData.getRandomDistrict();
			entityDao.save(city);
		}
		
		assertEquals(3, entityDao.countChildCity(topCity.getId()).longValue());
		
		// 读取全部城市
		List<City> findAllCity = entityDao.findChildCity(topCity.getId());
		assertEquals(n, findAllCity.size());
		
		// 删除刚刚添加的3个城市
		for (int i = 2; i <= n + 1; i++) {
			entityDao.delete(Long.valueOf(i));
		}
		
		// 验证是否删除成功
		findAllCity = entityDao.findChildCity(topCity.getId());
		assertEquals(0, findAllCity.size());
		
		// 删除上海
		entityDao.delete(topCity);
		assertNotNull(entityDao.get(1l));
		
	}
}

package cn.wsria.demo.unit.dao.city;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.wsria.demo.dao.jstree.CityDao;
import cn.wsria.demo.data.MysqlDbUnitUtils;
import cn.wsria.demo.entity.jstree.City;
import cn.wsria.demo.unit.dao.BaseTxTestCase;

public class CityDaoTest extends BaseTxTestCase {

	@Autowired
	public CityDao entityDao;
	
	@Before
	public void loadDefaultDatae() throws Exception {
		MysqlDbUnitUtils.loadData(dataSource, "/data/city-data.xml");
	}

	@Test
	//如果你需要真正插入数据库,将Rollback设为false
	//@Rollback(false) 
	public void crudEntity() {

		// 获取城市
		City topCity = entityDao.get(1l);
		assertNotNull(topCity);

		// 添加下级城市
		int n = 3;

		assertEquals(2, entityDao.countChildCity(topCity.getId()).longValue());

		// 读取全部城市
		List<City> findAllCity = entityDao.findChildCity(topCity.getId());
		assertEquals(2, findAllCity.size());

		// 删除城市
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

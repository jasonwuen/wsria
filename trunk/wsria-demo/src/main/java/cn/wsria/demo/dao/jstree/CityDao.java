package cn.wsria.demo.dao.jstree;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import cn.wsria.demo.entity.jstree.City;

/**
 * 城市DAO。
 *
 * @author HenryYan
 *
 */
@Component
public class CityDao extends HibernateDao<City, Long> implements Serializable {

	private static final long serialVersionUID = 1L;

	public List<City> findChildCity(Long id) {
		return find("from City where superId=?", id);
	}

}

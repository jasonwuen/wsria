package com.wsria.demo.dao.jstree;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.wsria.demo.entity.jstree.City;


/**
 * 城市DAO。
 *
 * @author HenryYan
 *
 */
@Component
public class CityDao extends HibernateDao<City, Long> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 查询下属区县
	 * @param superId
	 * @return
	 */
	public List<City> findChildCity(Long superId) {
		return find("from City where superId=?", superId);
	}
	
	/**
	 * 统计下属区县数量
	 * @param superId
	 * @return
	 */
	public Long countChildCity(Long superId) {
		return findUnique("select count(*) from City where superId=?", superId);
	}

}

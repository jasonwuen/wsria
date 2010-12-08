package com.wsria.demo.dao.area;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.wsria.demo.entity.area.AreaInfo;


/**
 * 地区信息泛型DAO
 *
 * @author 咖啡兔
 * @site www.wsria.cn
 *
 */
@Repository
public class AreaInfoDao extends HibernateDao<AreaInfo, Long> {

	/**
	 * 查询父级ID
	 * @param childId	子地区ID
	 * @return 父级ID
	 */
	public Long findParentAreaInfoId(Long childId) {
		String hql = "select parentAreaId from AreaInfo where id = ?";
		return super.findUnique(hql, childId);
	}

	/**
	 * 根据名称查询下一级的地区信息
	 * @param topLevelParent	地区名称
	 * @return
	 */
	public List<AreaInfo> getAreaByParentName(String parentName) {
		String byNameHql = "select id from AreaInfo where areaName = ?";
		String hql = "from AreaInfo where parentAreaId = (" + byNameHql + ")";
		return super.find(hql, parentName);
	}
	
}

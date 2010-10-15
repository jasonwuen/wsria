package cn.wsria.demo.dao.area;

import org.springframework.stereotype.Repository;
import org.springside.modules.orm.hibernate.HibernateDao;

import cn.wsria.demo.entity.area.AreaInfo;

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
	
}

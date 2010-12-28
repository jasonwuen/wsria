package com.wsria.arch.util.orm;

import org.springframework.stereotype.Repository;
import org.springside.modules.orm.hibernate.HibernateDao;

/**
 * 通用DAO.<br/>
 * 实体类必须集成{@link BaseEntity}
 *
 * @author HenryYan
 *
 */
@Repository
public class BaseDao extends HibernateDao<IdEntity, Long> {

}

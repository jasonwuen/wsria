package com.wsria.demo.dao.account;

import org.springframework.stereotype.Repository;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.wsria.demo.entity.account.User;


/**
 * 用户DAO
 *
 * @author 咖啡兔
 * @site www.wsria.cn
 *
 */
@Repository
public class UserDao extends HibernateDao<User, Long> {

}

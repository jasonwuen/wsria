package com.wsria.demo.service.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wsria.demo.dao.account.UserDao;
import com.wsria.demo.entity.account.User;


/**
 * 用户实体管理类
 *
 * @author 咖啡兔
 * @site www.wsria.cn
 *
 */
@Service
@Transactional
public class UserManager {

	@Autowired UserDao userDao;
	
	/**
	 * 根据登录用户名查询用户
	 * @param loginName	登录用户名
	 * @return	{@link User}
	 */
	@Transactional(readOnly = true)
	public User findUserByLoginName(String loginName) {
		return userDao.findUniqueBy("loginName", loginName);
	}
	
}

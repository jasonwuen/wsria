package com.wsria.demo.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springside.modules.utils.web.struts2.Struts2Utils;

import com.wsria.demo.entity.account.User;


/**
 * 用户工具类.
 *
 * @author 咖啡兔
 * @site www.wsria.cn
 *
 */
public class UserUtil {

	/**
	 *	用户的Session标志
	 */
	public static String USER = "user";

	/**
	 *	已登录的用户
	 */
	public static Map<String, User> loginUsers = new HashMap<String, User>();

	/**
	 * 保存用户信息到Session
	 * @param user
	 */
	public static void saveUserToSession(User user) {
		Struts2Utils.getSession().setAttribute(USER, user);
	}

	/**
	 * 保存用户信息到Session
	 * @param user
	 */
	public static void saveUserToSession(HttpSession session, User user) {
		session.setAttribute(USER, user);
		loginUsers.put(user.getLoginName(), user);
	}

	/**
	 * 获取当前登录的用户
	 * @return
	 */
	public static User getCurrentUser() {
		HttpSession session = Struts2Utils.getSession();
		return getCurrentUser(session);
	}

	/**
	 * 获取当前登录的用户
	 * @param session
	 * @return
	 */
	public static User getCurrentUser(HttpSession session) {
		Object sessionUser = session.getAttribute(USER);
		if (sessionUser == null) {
			return null;
		}
		User User = (User) sessionUser;
		return User;
	}

	/**
	 * 获取当前用户的ID
	 * @return 当前用户的ID
	 */
	public static Long getCurrentUserId() {
		return getCurrentUser().getId();
	}

	/**
	 * 从session中移除用户
	 */
	public static void removeUserFromSession() {
		Struts2Utils.getSession().removeAttribute(USER);
	}

}

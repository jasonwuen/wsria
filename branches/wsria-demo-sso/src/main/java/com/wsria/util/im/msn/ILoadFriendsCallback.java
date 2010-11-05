package com.wsria.util.im.msn;

import java.util.List;

import net.sf.jml.MsnMessenger;

/**
 * 获取好友列表回调接口
 *
 * @author HenryYan
 *
 */
public interface ILoadFriendsCallback {

	/**
	 * 查询完成好友列表后
	 * @param friends		好友列表
	 * @param currentThread	调用者线程
	 */
	void dealFriends(List<MsnFriend> friends, Thread currentThread);

	/**
	 * 读取列表出错时
	 * @param messenger		{@link MsnMessenger}
	 * @param t				异常
	 * @param currentThread	调用者线程
	 */
	void dealException(MsnMessenger messenger, Throwable t, Thread currentThread);

}
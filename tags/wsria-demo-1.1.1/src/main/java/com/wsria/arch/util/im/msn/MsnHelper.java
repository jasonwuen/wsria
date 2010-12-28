package com.wsria.arch.util.im.msn;

import java.util.ArrayList;
import java.util.List;

import net.sf.jml.MsnContact;
import net.sf.jml.MsnList;
import net.sf.jml.MsnMessenger;
import net.sf.jml.MsnProtocol;
import net.sf.jml.MsnUserStatus;
import net.sf.jml.event.MsnAdapter;
import net.sf.jml.impl.MsnMessengerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MSN工具类
 *
 * @author HenryYan
 *
 */
public class MsnHelper extends MsnAdapter {
	
	private Logger logger = LoggerFactory.getLogger(MsnHelper.class);

	protected MsnMessenger messenger = null;
	private String email = null, password = null;
	private Thread currentThread;

	private List<MsnFriend> friends = new ArrayList<MsnFriend>();
	private ILoadFriendsCallback lfc;

	public MsnHelper(String email, String password, Thread currentThread) {
		super();
		this.email = email;
		this.password = password;
		this.currentThread = currentThread;
	}

	/**
	 * 获取MSN好友列表
	 * @param lfc	读取到好友列表后的回调函数
	 */
	public void getFriends(ILoadFriendsCallback lfc) throws MsnException {
		if (lfc == null) {
			throw new MsnException("请指定回调函数");
		}
		this.lfc = lfc;
		login();
	}

	/**
	 * 登录MSN
	 */
	private void login() {
		logger.debug("准备登录MSN：{}", email);
		messenger = MsnMessengerFactory.createMsnMessenger(email, password); // 创建MsnMessenger 
		messenger.setSupportedProtocol(new MsnProtocol[] { MsnProtocol.MSNP12 }); // 设置登录协议 
		messenger.getOwner().setInitStatus(MsnUserStatus.ONLINE); // 设置用户状态 
		messenger.addListener(this); // 注册事件 
		messenger.login(); // 登录
	}
	
	// 注销时发生 
	public void logout(MsnMessenger messenger) {
		logger.debug("退出MSN");
	}

	/**
	 * 异常时发生 
	 */
	public void exceptionCaught(MsnMessenger messenger, Throwable throwable) {
		logger.error("获取MSN好友列表出错：", throwable);
		this.lfc.dealException(messenger, throwable, currentThread);
		System.exit(-1);
	}

	/**
	 * 登录完成时发生 
	 */
	public void loginCompleted(MsnMessenger messenger) {
		logger.debug("登录MSN成功：{}", email);
	}

	/**
	 * 联系人初始化完成时
	 */
	public void contactListInitCompleted(MsnMessenger messenger) {
		logger.debug("联系人初始化完成，MSN-Email：{}", email);
		listContacts();
	}

	// 打印联系人 
	private void listContacts() {
		MsnContact[] cons = messenger.getContactList().getContactsInList(MsnList.AL);
		logger.debug("MSN-Email：{}，好友列表为：{} 个", email, cons.length);
		for (MsnContact msnContact : cons) {
			if (msnContact.getEmail() != null) {
				friends.add(new MsnFriend(msnContact.getEmail().getEmailAddress()));
			}
		}
		
		logger.debug("MSN-Email：{}，可用好友列表为：{} 个", email, friends.size());
		
		messenger.logout();
		logger.debug("MSN-Email：{}，退出登录！", email);
		logger.debug("查询好友列表成功，调用处理回调类：{}", this.lfc.getClass());
		this.lfc.dealFriends(friends, currentThread);
	}
}
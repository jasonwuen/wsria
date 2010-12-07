package com.wsria.arch.util.im.msn;

/**
* MSN好友
*
* @author HenryYan
*
*/
public class MsnFriend {
	private String email;
	private String displayName;
	private String friendlyName;

	public MsnFriend(String email) {
		super();
		this.email = email;
	}

	public MsnFriend(String email, String displayName) {
		super();
		this.email = email;
		this.displayName = displayName;
	}

	public MsnFriend(String email, String displayName, String friendlyName) {
		super();
		this.email = email;
		this.displayName = displayName;
		this.friendlyName = friendlyName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getFriendlyName() {
		return friendlyName;
	}

	public void setFriendlyName(String friendlyName) {
		this.friendlyName = friendlyName;
	}
}

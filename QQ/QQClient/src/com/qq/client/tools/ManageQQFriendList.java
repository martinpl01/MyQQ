package com.qq.client.tools;

import java.util.HashMap;

import com.qq.client.view.QQClientFriendList;

public class ManageQQFriendList {

	private static HashMap <String, QQClientFriendList> map = new HashMap<> ();
	
	public static void addQQClientFriendList(String userId, QQClientFriendList qqcf) {
		map.put(userId, qqcf);
	}
	
	public static QQClientFriendList getQQClientFriendList(String userId) {
		return map.get(userId);
	}
	
	
	
}

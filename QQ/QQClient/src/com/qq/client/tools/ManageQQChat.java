package com.qq.client.tools;

import java.util.HashMap;

import com.qq.client.view.QQClientChat;

public class ManageQQChat {
	public static HashMap<String, QQClientChat> map = new HashMap<> ();
	
	
	public static void addQQChat(String ownIdAndFriendId, QQClientChat qqcc) {
		map.put(ownIdAndFriendId, qqcc);
	}
	
	public static QQClientChat getQQClientChat(String ownIdAndFriendId) {
		return map.get(ownIdAndFriendId);
	}
	
	
	

}

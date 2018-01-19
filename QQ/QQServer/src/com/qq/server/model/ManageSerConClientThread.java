package com.qq.server.model;

import java.util.HashMap;

public class ManageSerConClientThread {
	
	private static HashMap<String, SerConClientThread> map = new HashMap<>();
	
	
	
	
	public static HashMap<String, SerConClientThread> getMap() {
		return map;
	}

	public static void addSerConClientThread(String userId, SerConClientThread scct) {
		map.put(userId, scct);
	}
	
	public static SerConClientThread getSerConClientThread(String userId) {
		return map.get(userId);
	}
	
	public static String getOnlineFriend() {
		String friends = "";
		for(String friendId: ManageSerConClientThread.map.keySet()) {
			friends += friendId + " ";
		}
		return friends;
	}
	
	
	

}

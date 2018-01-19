package com.qq.client.tools;

import java.util.HashMap;

public class ManageClientConSerThread {
	
	public static HashMap<String, ClientConSerThread> map = new HashMap<String, ClientConSerThread>();
	
	public static void addClientConSerThread(String userId, ClientConSerThread ccst) {
		map.put(userId, ccst);
	}
	
	public static ClientConSerThread getClientConSerThread(String userId) {
		return map.get(userId);
	}

}

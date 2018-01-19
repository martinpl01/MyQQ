/*
 * 此线程是管理用户的登录 和聊天
 * 一个socket 管理 一个客户端
 * */
package com.qq.client.tools;

import java.net.*;

import com.qq.client.view.QQClientChat;
import com.qq.client.view.QQClientFriendList;
import com.qq.common.Message;
import com.qq.common.MessageType;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
public class ClientConSerThread extends Thread{
	private Socket socket;
	
	public Socket getSocket() {
		return socket;
	}

	public ClientConSerThread(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		//时时刻刻在监听 从服务端传来的信息
		while(true) {	
			try {
			//	System.out.println("Thread is working.....");
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				Message m = (Message) ois.readObject();
				//如果是普通语音包
				if(m.getMessageType().equals(MessageType.message_con)) {
					String friendId = m.getSender();
					String ownId = m.getGetter();
					QQClientChat qqcc = ManageQQChat.getQQClientChat(ownId + " " + friendId);
					qqcc.ShowMessage(m);
				}
					//如果是好友列表
				else if(m.getMessageType().equals(MessageType.message_ret_friendlist)) {
						QQClientFriendList qqfl = ManageQQFriendList.getQQClientFriendList(m.getSender());
//						System.out.println(qqfl);
						if(qqfl != null) {
							qqfl.updateFriendList(m);
						}
					}	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	
	

}

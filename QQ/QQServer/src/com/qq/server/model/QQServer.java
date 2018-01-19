package com.qq.server.model;

import java.io.*;
import java.net.*;
import com.qq.common.*;

public class QQServer {

	
	
	public static String testTitle() {
		return new String("服务器：：");
				
	}
	
	public QQServer() {
		System.out.println(QQServer.testTitle() + "开启......");
		try {
			ServerSocket ss = new ServerSocket(9999);
			Message ms = new Message();
			System.out.println(QQServer.testTitle() + "在9999监听.....");
			while(true) {
				Socket s = ss.accept();
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				User user = (User) ois.readObject();
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				if(user.getPassword().equals("123456")) {

					ms.setMessageType(MessageType.message_login_success);
					ms.setCon("用户id " + user.getUserId() + "用户密码" + user.getPassword());
					System.out.println(QQServer.testTitle() + "用户id " + user.getUserId() + "用户密码" + user.getPassword());
					oos.writeObject(ms);
					
					//然后就生成一个thread 客户端和服务器的对接
					SerConClientThread scct = new SerConClientThread(s);
					ManageSerConClientThread.addSerConClientThread(user.getUserId(), scct);
					scct.start();
					scct.notifyOthers(user.getUserId());
					
				}else {
					ms.setMessageType(MessageType.message_login_fall);
					oos.writeObject(ms);
					//关闭连接 等待再连接
					s.close();
				}
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

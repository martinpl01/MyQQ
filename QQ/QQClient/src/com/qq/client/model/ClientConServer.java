package com.qq.client.model;

import java.net.*;

import com.qq.client.tools.*;
import com.qq.common.*;

import java.io.*;
public class ClientConServer {
	
	private Socket s;
	
	
	public boolean userLoginInfoCheck(User user) {
		boolean a = false;
		try {
			//发送用户给服务器
			System.out.println("客户端：：开始连接9999.....");
			s = new Socket("127.0.0.1", 9999);
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(user);
			
			//接收服务器给用户返回的message 即结果
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			Message ms = (Message) ois.readObject();

			//如果登录成功
		
			if(ms.getMessageType().equals(MessageType.message_login_success)) {
				ClientConSerThread ccst = new ClientConSerThread(s);
				ccst.start();
				ManageClientConSerThread.addClientConSerThread(user.getUserId(), ccst);
				
				//同时为客户端开启线程
				
				//System.out.println(ms.getCon());
				a= true;	
			}
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		
		
		return a;
	}

}

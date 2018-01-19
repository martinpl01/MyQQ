package com.qq.server.model;

import java.net.*;

import com.qq.common.Message;
import com.qq.common.MessageType;

import java.io.*;

public class SerConClientThread extends Thread{
	
	private Socket socket;
	
	
	public Socket getSocket() {
		return socket;
	}


	public SerConClientThread(Socket socket) {
		this.socket = socket;
		
	}
	
	public static String testTitle() {
		return new String("服务器：：");
	}
	
	public void notifyOthers(String ownId) {
		Message m = new Message();
		for(String userId: ManageSerConClientThread.getMap().keySet()) {
			m.setCon(ownId);
			m.setMessageType(MessageType.message_ret_friendlist);
			m.setSender(userId);
			try {
				ObjectOutputStream oos = new ObjectOutputStream(ManageSerConClientThread.getSerConClientThread(userId).socket.getOutputStream());
				oos.writeObject(m);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void run() {	
		while(true) {
			try {
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			    
				Message m = (Message) ois.readObject();
				//如果是聊天包
				if(m.getMessageType().equals(MessageType.message_con)) {
					SerConClientThread scct = ManageSerConClientThread.getSerConClientThread(m.getGetter());
					ObjectOutputStream oos = new ObjectOutputStream(scct.getSocket().getOutputStream());
					oos.writeObject(m);
					
				}else if(m.getMessageType().equals(MessageType.message_get_friendlist)) {
					Message m2 = new Message();
					m2.setSender(m.getSender());
					m2.setMessageType(MessageType.message_ret_friendlist);
					m2.setCon(ManageSerConClientThread.getOnlineFriend());
					ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
					oos.writeObject(m2);
					
				}
				
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	

}

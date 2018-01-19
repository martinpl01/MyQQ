/*
 * 聊天界面
 * */
package com.qq.client.view;

import javax.swing.*;

import com.qq.client.tools.ClientConSerThread;
import com.qq.client.tools.ManageClientConSerThread;
import com.qq.common.Message;
import com.qq.common.MessageType;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;

public class QQClientChat extends JFrame implements ActionListener{
	String ownId;
	String friendId;
	JPanel jp1;
	JButton jb1;
	JTextArea jta1;
	JTextField jtf1;
	
	public static void main(String args[]) {
//		QQClientChat qqcc = new QQClientChat();
	}
	
	public void ShowMessage(Message m) {
		String time = m.getSendTime() + "\r\n";
		String info = m.getSender() + " 对" + m.getGetter() +
				"说：" + m.getCon() + "\r\n";
		jta1.append(time+info);
		
	}
	
	
	public QQClientChat(String ownId, String friendId) {
		this.ownId = ownId;
		this.friendId = friendId;
		jp1 = new JPanel();
		jb1 = new JButton("发送");
		jb1.addActionListener(this);
		jtf1 = new JTextField(15);
		jp1.add(jtf1);
		jp1.add(jb1);
		jta1 = new JTextArea();
		
		this.add(jta1, "Center");
		this.add(jp1, "South");
		
		this.setTitle(ownId + "正在和 " + friendId);
		this.setVisible(true);
		this.setSize(300, 250);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == jb1) {
			Message m = new Message();
			m.setMessageType(MessageType.message_con);
			m.setSender(ownId);
			m.setGetter(friendId);
			m.setSendTime(new Date().toString());
			m.setCon(jtf1.getText());
			
			ClientConSerThread ccst = ManageClientConSerThread.getClientConSerThread(ownId);
			
		  try {
			ObjectOutputStream oos = new ObjectOutputStream(ccst.getSocket().getOutputStream());
			oos.writeObject(m);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		}
	}

}

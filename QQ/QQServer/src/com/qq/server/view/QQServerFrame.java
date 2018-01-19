package com.qq.server.view;

import javax.swing.*;

import com.qq.server.model.QQServer;

import java.awt.*;
import java.awt.event.*;

public class QQServerFrame extends JFrame implements ActionListener{
	JPanel jp1;
	JButton jb1, jb2;
	
	public static void main (String args[]) {
		QQServerFrame qqs = new QQServerFrame();
	}
	public QQServerFrame() {
		
		jp1 = new JPanel();
		jb1 = new JButton ("服务器开启");
		jb1.addActionListener(this);
		jb2 = new JButton ("服务器关闭");
		jp1.add(jb1);
		jp1.add(jb2);
		
		this.add(jp1, "North");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(700, 500);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == jb1) {
			new QQServer();
		}
	}
}

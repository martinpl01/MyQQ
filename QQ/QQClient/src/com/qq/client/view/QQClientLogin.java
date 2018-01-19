/*
 * 显示登录界面 
 * */
package com.qq.client.view;

import javax.swing.*;

import com.qq.client.model.QQClientUser;
import com.qq.client.tools.ClientConSerThread;
import com.qq.client.tools.ManageClientConSerThread;
import com.qq.client.tools.ManageQQFriendList;
import com.qq.common.Message;
import com.qq.common.MessageType;
import com.qq.common.User;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class QQClientLogin extends JFrame implements ActionListener{
	
	
	//top 
	JLabel jlb1;
	
	//center
	CardLayout cl1;
	JTabbedPane jtp1;
	
	
	JPanel jp3, jp4, jp5;
	JLabel jp3_jlb1, jp3_jlb2, jp3_jlb3,jp3_jlb4;
	JButton jp3_jb1;
	JCheckBox jp3_jcb1, jp3_jcb2;
	JTextField jp3_jtf1;
	JPasswordField jp3_jpf1;
	

	//south
	JPanel jp2;
	JButton jp2_jb1, jp2_jb2, jp2_jb3;
	
	public static void main(String args[]) {
		QQClientLogin qqcl = new QQClientLogin();
	}
	
	public QQClientLogin() {
		
		
		//top
		jlb1 = new JLabel(new ImageIcon("images/topImage.png"));
		this.add(jlb1, "North");
		
		//center
		jp3 = new JPanel(new GridLayout(3,3));
		jp3_jlb1 = new JLabel("QQ号码", JLabel.CENTER);
		jp3_jlb2 = new JLabel("QQ密码", JLabel.CENTER);
		jp3_jlb3 = new JLabel("忘记密码", JLabel.CENTER);
		jp3_jlb3.setForeground(Color.BLUE);
		jp3_jlb4 = new JLabel("申请密码保护", JLabel.CENTER);
		jp3_jb1 = new JButton("清除号码");
		jp3_jcb1 = new JCheckBox("记住密码");
		
		jp3_jcb2 = new JCheckBox("隐身登录");
		jp3_jtf1 = new JTextField(15);
		jp3_jpf1 = new JPasswordField();
		
		jp3.add(jp3_jlb1);
		jp3.add(jp3_jtf1);
		jp3.add(jp3_jb1);
		
		jp3.add(jp3_jlb2);
		jp3.add(jp3_jpf1);
		jp3.add(jp3_jlb3);
		
		jp3.add(jp3_jcb2);
		jp3.add(jp3_jcb1);
		jp3.add(jp3_jlb4);
		
		jtp1 = new JTabbedPane();
		jp4 = new JPanel();
		jp5 = new JPanel();
		jtp1.add("QQ号码", jp3);
		jtp1.add("手机号码", jp4);
		jtp1.add("电子邮件", jp5);
		
		this.add(jtp1, "Center");
		
		//south
		jp2 = new JPanel();
		jp2_jb1 = new JButton("登录");
		jp2_jb1.addActionListener(this);
		jp2_jb2 = new JButton("取消");
		jp2_jb3 = new JButton("向导");
		jp2.add(jp2_jb1);
		jp2.add(jp2_jb2);
		jp2.add(jp2_jb3);
		this.add(jp2, "South");
		
		this.setVisible(true);
		this.setSize(340, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == jp2_jb1) {
			//登录先要验证 
			User user = new User();
			user.setUserId(jp3_jtf1.getText());
			user.setPassword(new String(jp3_jpf1.getPassword()));
			QQClientUser qqcu = new QQClientUser();
			//通过检查 说明正确
			if(qqcu.checkLogin(user)) {
				//发送请求好友list 的message
				ClientConSerThread ccst = ManageClientConSerThread.getClientConSerThread(user.getUserId());
				QQClientFriendList qqcfl = new QQClientFriendList(user.getUserId());
				ManageQQFriendList.addQQClientFriendList(user.getUserId(), qqcfl);
//				ManageQQFriendList.getQQClientFriendList(user.getUserId());
				
				try {
					ObjectOutputStream oos = new ObjectOutputStream(ccst.getSocket().getOutputStream());
					Message m = new Message();
					m.setMessageType(MessageType.message_get_friendlist);
					m.setSender(user.getUserId());
					oos.writeObject(m);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				this.dispose();
			}else {
				JOptionPane.showMessageDialog(this, "账号密码错误");
			}
		
			
		}
	}

}

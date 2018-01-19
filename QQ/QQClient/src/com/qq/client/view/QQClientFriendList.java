/*
 * 显示好友的列表
 * 
 * */
package com.qq.client.view;

import javax.swing.*;

import com.qq.client.tools.ManageQQChat;
import com.qq.common.Message;

import java.awt.*;
import java.awt.event.*;

public class QQClientFriendList extends JFrame implements ActionListener, MouseListener{
	//知道自己是谁
	private String ownId;
	//用卡片来管理
	CardLayout cl1;
	//好友界面
	//top
	JPanel jphy1;
	JButton jphy_jb1;
	//center
	JPanel jphy2;
	JScrollPane jphy_jsp1;
	//south
	JPanel jphy3;
	JButton jphy_jb2, jphy_jb3;	
	
	JLabel [] jphy_jlbs1;
	//陌生人界面
	//top
	JPanel jpms1;
	JButton jpms_jb1, jpms_jb2;
	//center
	JPanel jpms2;
	JScrollPane jpms_jsp1;
	//south
	JPanel jpms3;
	JButton jpms_jb3;
    //黑名单界面
	//top
	JPanel jphm1;
	JButton jphm_jb1, jphm_jb2, jphm_jb3;
	//center
	JPanel jphm2;
	JScrollPane jphm_jsp1;
	
	JPanel jphm3;
//	//south
//	JPanel jpms3;
//	JButton jpms_jb3;

	
	public static void main(String args[]) {
//		QQClientFriendList qqcfl = new  QQClientFriendList("2");
	}
	
	
	public void updateFriendList(Message m) {
		String [] friends = m.getCon().split(" ");
		for(int i = 0; i < friends.length;i++) {
			jphy_jlbs1[Integer.parseInt(friends[i]) - 1].setEnabled(true);
		}
	}
	
	public QQClientFriendList(String ownId) {
		this.ownId = ownId;
		
		jphy_jlbs1 = new JLabel[50];
		//好友界面
		//top
		jphy1 = new JPanel(new BorderLayout());
		jphy_jb1 = new JButton("我的好友");
		jphy1.add(jphy_jb1, "North");
		//center
		jphy2 = new	JPanel(new GridLayout(50,1, 4,4));
		
		for(int i = 0; i < jphy_jlbs1.length; i++) {
			jphy_jlbs1[i] = new JLabel(i + 1 +"", new ImageIcon("images/touxiang.png"), JLabel.LEFT);
			jphy2.add(jphy_jlbs1[i]);
//			System.out.println(ownId);
//			System.out.println(jphy_jlbs1[i].getText());
			if(!jphy_jlbs1[i].getText().equals(ownId)) jphy_jlbs1[i].setEnabled(false);
			jphy_jlbs1[i].addMouseListener(this);
			
		}
	    jphy_jsp1 = new JScrollPane(jphy2);
	    jphy1.add(jphy_jsp1,"Center");
		//south
		jphy3 = new JPanel(new GridLayout(2,1));
		jphy_jb2 = new JButton("陌生人");
		jphy_jb2.addActionListener(this);
		jphy_jb3 = new JButton("黑名单");
		jphy_jb3.addActionListener(this);
		jphy3.add(jphy_jb2);
		jphy3.add(jphy_jb3);
		jphy1.add(jphy3,"South");
		
		//陌生人界面
		//top
		jpms1 = new JPanel(new GridLayout(2,1));
		jpms_jb1 = new JButton("我的好友");
		jpms_jb1.addActionListener(this);
		jpms_jb2 = new JButton("陌生人");
		jpms1.add(jpms_jb1);
		jpms1.add(jpms_jb2);
		//center
		jpms2 = new JPanel(new GridLayout(30,1, 4,4));
		JLabel[] jpms_jlbs1 = new JLabel[30];
		for(int i = 0; i <jpms_jlbs1.length; i++) {
			jpms_jlbs1[i] = new JLabel(i + 1 +"", new ImageIcon("images/touxiang.png"), JLabel.LEFT);
			jpms2.add(jpms_jlbs1[i]);
		}
		jpms_jsp1 = new JScrollPane(jpms2);
		//south
		jpms3 = new JPanel(new BorderLayout());
		jpms_jb3 = new JButton("黑名单");
		jpms_jb3.addActionListener(this);
		
		jpms3.add(jpms1, "North");
		jpms3.add(jpms_jsp1, "Center");
		jpms3.add(jpms_jb3, "South");
		
		
		//黑名单界面
		//top
		jphm1 = new JPanel(new GridLayout(3, 1));
		jphm_jb1 = new JButton("我的好友");
		jphm_jb1.addActionListener(this);
		jphm_jb2 = new JButton("陌生人");
		jphm_jb2.addActionListener(this);
		jphm_jb3 = new JButton("黑名单");
		jphm1.add(jphm_jb1);
		jphm1.add(jphm_jb2);
		jphm1.add(jphm_jb3);
		//center
		jphm2 = new JPanel(new GridLayout(30,1, 4,4));
		JLabel[] jphm_jlbs1 = new JLabel[30];
		for(int i = 0; i <jphm_jlbs1.length; i++) {
			jphm_jlbs1[i] = new JLabel(i + 1 +"", new ImageIcon("images/touxiang.png"), JLabel.LEFT);
			jphm2.add(jphm_jlbs1[i]);
		}
		jphm_jsp1 = new JScrollPane(jphm2);
		
		jphm3 = new JPanel(new BorderLayout());
		
		jphm3.add(jphm1, "North");
		jphm3.add(jphm_jsp1, "Center");
		
		
		cl1 = new CardLayout();
		

		
		this.setLayout(cl1);
		this.add("1", jphy1);
		this.add("2", jpms3);
		this.add("3", jphm3);
		
		this.setTitle(ownId +" 用户");
		this.setVisible(true);
		this.setSize(200, 400);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		//好友2个button
		if(e.getSource() == jphy_jb2) {
			cl1.show(this.getContentPane(), "2");
		}else if(e.getSource() == jphy_jb3) {
			cl1.show(this.getContentPane(), "3");
		}
		//陌生人2个button
		else if(e.getSource() == jpms_jb1) {
			cl1.show(this.getContentPane(), "1");
		}else if(e.getSource() == jpms_jb3) {
			cl1.show(this.getContentPane(), "3");
		}
		//黑名单2个button
		else if(e.getSource() == jphm_jb1) {
			cl1.show(this.getContentPane(), "1");
		}else if(e.getSource() == jphm_jb2) {
			cl1.show(this.getContentPane(), "2");
		}
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getClickCount() == 2) {
			JLabel jb = (JLabel) e.getSource();
			String friendId = jb.getText();
			ManageQQChat.addQQChat(ownId + " " +friendId, new QQClientChat(ownId, friendId));
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel jb = (JLabel) e.getSource();
		jb.setForeground(Color.RED);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel jb = (JLabel) e.getSource();
		jb.setForeground(Color.BLACK);
	}

}

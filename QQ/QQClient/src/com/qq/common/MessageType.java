package com.qq.common;

public interface MessageType {
	String message_login_success = "1";//登录成功
	String message_login_fall = "2";//登录失败
	String message_con = "3"; //普通聊天包
	String message_get_friendlist = "4";//请求好友列表
	String message_ret_friendlist = "5"; //返回好友列表
}

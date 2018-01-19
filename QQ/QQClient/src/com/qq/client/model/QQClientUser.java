package com.qq.client.model;

import com.qq.common.*;

public class QQClientUser {
	
	public boolean checkLogin(User user) {
		return new ClientConServer().userLoginInfoCheck(user);
	}

}

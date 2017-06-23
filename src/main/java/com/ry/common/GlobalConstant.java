package com.ry.common;

import com.ry.model.platform.entity.User;

public class GlobalConstant {
	// 系统内置用户属性
	public static User user = new User() {
		{
			setUserId(99999999);
			setUserName("admin");
			setUserPassword("");
		}
	};
}
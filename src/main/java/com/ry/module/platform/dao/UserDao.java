package com.ry.module.platform.dao;

import org.springframework.stereotype.Repository;
import com.ry.module.platform.entity.User;


@Repository("userDao")
public interface UserDao {

	/**
	 * @param userId
	 * @return User
	 */
	public User selectUserById(Integer userId);

	public User getUserInfoByAccount(String currentAccount);
}

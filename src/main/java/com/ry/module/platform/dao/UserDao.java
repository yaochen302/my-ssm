package com.ry.module.platform.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ry.module.platform.entity.User;


@Repository("userDao")
public interface UserDao {

	/**
	 * @param userId
	 * @return User
	 */
	public User selectUserById(Integer userId);

	public User getUserInfoByAccount(@Param("ss")String currentAccount);

	public List<User> selectAllUser();
}

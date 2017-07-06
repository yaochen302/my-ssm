package com.ry.module.platform.service;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ry.common.GlobalConstant;
import com.ry.common.base.BaseResult;
import com.ry.common.base.BaseService;
import com.ry.module.platform.dao.UserDao;
import com.ry.module.platform.entity.User;
import com.ry.shiro.ShiroUtil;

@Service("userService")
public class UserService extends BaseService {

	@Autowired
	private UserDao userDao;

	public User selectUserById(Integer userId) {
		return userDao.selectUserById(userId);

	}

	public BaseResult CheckUser(String user_name, String user_password) {
		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(user_name, user_password);
		try {
			currentUser.login(token);

			// 缓存登录人员对象
			User user = null;

			if (user_name.equals(GlobalConstant.user.getUserName())) {
				user = GlobalConstant.user;
			} else {
				user = userDao.getUserInfoByAccount(user_name);
			}

			// 刷新操作员可以视范围
			// operatorService.refreshOperatorAllScope(operator);

			ShiroUtil.cacheUserInfo(user);

			return generateResult(true, "成功");
		} catch (UnknownAccountException uae) {
			return generateResult(false, "用户名或密码错误");
		} catch (IncorrectCredentialsException ice) {
			return generateResult(false, "用户名或密码错误");
		} catch (LockedAccountException lae) {
			return generateResult(false, "帐号已被锁定");
		} catch (AuthenticationException ae) {
			return generateResult(false, "认证未通过，请输入正确的用户名和密码");
		}
	}

	public User getUserInfoByAccount(String accountNo) {
		return userDao.getUserInfoByAccount(accountNo);
	}
}
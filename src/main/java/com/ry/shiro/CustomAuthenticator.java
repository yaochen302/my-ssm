package com.ry.shiro;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.ry.common.GlobalConstant;
import com.ry.module.platform.dao.UserDao;
import com.ry.module.platform.entity.User;
import com.ry.module.platform.service.UserService;


/**
 * Shiro用户自定义认证类
 * 
 * @author jiangbo
 *
 */
public class CustomAuthenticator extends AuthorizingRealm {
	@Autowired
	private UserService userService;

	/**
	 * 权限认证<br>
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		if (principals == null) {
			throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
		}

		String userNo = (String) getAvailablePrincipal(principals);
		return ShiroUtil.getAuthInfo(userNo);
	}

	/**
	 * 登录认证<br>
	 * 认证回调函数, 登录时调用.
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken upToken = (UsernamePasswordToken) authcToken;
		String accountNo = upToken.getUsername();
		AuthenticationInfo info = null;
		if (accountNo == null) {
			throw new AccountException("用户验证错误-[用户名为空]");
		}

		try {
			// 判断是不是内置用户登录
			if (accountNo.equals(GlobalConstant.user.getUserName())) {
				DateFormat df = new SimpleDateFormat("yyyyMMdd");
				String adminPassword = GlobalConstant.user.getUserPassword() + df.format(new Date());
				info = buildAuthenticationInfo(accountNo, adminPassword.toCharArray());
			} else {
				User user = userService.getUserInfoByAccount(accountNo);
				if (user == null)
					throw new AccountException("用户验证错误[用户信息不存在].");

				// 将用户名密码放入认证信息内，此方法内会将LoginAction.login()方法中的UsernamePasswordToken进行比较
				info = buildAuthenticationInfo(user.getUserName(), user.getUserPassword().toCharArray());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new AccountException("用户验证错误[查询用户信息异常].");
		}
		return info;
	}

	protected AuthenticationInfo buildAuthenticationInfo(String username, char[] password) {
		return new SimpleAuthenticationInfo(username, password, getName());
	}

	protected Set<String> getRoleNamesForUser(String username) {
		try {
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return new LinkedHashSet<String>();
		}
	}

	protected Set<String> getPermissions(String username, Collection<String> roleNames) {
		try {
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return new LinkedHashSet<String>();
		}
	}

	/**
	 * 更新用户授权信息缓存.
	 */
	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
		clearCachedAuthorizationInfo(principals);
	}

	/**
	 * 清除所有用户授权信息缓存.
	 */
	public void clearAllCachedAuthorizationInfo() {
		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
		if (cache != null) {
			for (Object key : cache.keys()) {
				cache.remove(key);
			}
		}
	}
}

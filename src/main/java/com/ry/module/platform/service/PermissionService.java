package com.ry.module.platform.service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import com.ry.common.base.BaseService;
import com.ry.module.platform.dao.PermissionDao;
import com.ry.module.platform.dao.UserDao;
import com.ry.module.platform.entity.Menu;
import com.ry.module.platform.entity.User;


public class PermissionService extends BaseService {
	@Autowired
	private PermissionDao permissionDao;
	@Autowired
	private UserDao userDao;
	
	private static PermissionService ss = new PermissionService();

	private PermissionService() {

	}

	public static PermissionService getInstance() {
		return ss;
	}

	/**
	 * 获取系统中所有需要验证的权限
	 * @param map
	 */
	public void getAllPermission(Map<String, String> map) {
		try {
			// 得到所有需要验证的权限（包括状态为0的）
			List<Menu> list = permissionDao.getMenu();

			for (Menu m : list) {
				map.put(m.getUrl().replace("/", ":"), m.getUrl());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取所有用户的所有权限
	 * @param userNo
	 * @param roleMap
	 * @param permissionMap
	 * @param userAuthInfoMap
	 * @return
	 */
	public boolean getAllRolePermission(Map<String, Set<String>> roleMap, Map<String, Set<String>> permissionMap,
			Map<String, SimpleAuthorizationInfo> userAuthInfoMap) {
		try {
			List<User> list = userDao.selectAllUser();

			for (User u : list) {
				if (!getAllRolePermissionByUser(u.getUserId().toString(), roleMap, permissionMap, userAuthInfoMap)) {
					return false;
				}
			}

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 获取指定用户的所有权限
	 * @param userNo
	 * @param roleMap
	 * @param permissionMap
	 * @param userAuthInfoMap
	 * @return
	 */
	public boolean getAllRolePermissionByUser(String userId, Map<String, Set<String>> roleMap,
			Map<String, Set<String>> permissionMap, Map<String, SimpleAuthorizationInfo> userAuthInfoMap) {
		try {
			List<Menu> list = permissionDao.getAllRolePermissionByUser(userId);

			if (roleMap.containsKey(userId)) {
				roleMap.remove(userId);
			}

			if (permissionMap.containsKey(userId)) {
				permissionMap.remove(userId);
			}

			if (userAuthInfoMap.containsKey(userId)) {
				userAuthInfoMap.remove(userId);
			}

			Set<String> permission = new HashSet<String>();
			Set<String> role = new HashSet<String>();
			for (Menu m : list) {
				String tmp[] = m.getUrl().split("/");
				if (tmp.length > 1) {
					role.add(tmp[0]);
					permission.add(m.getUrl().replaceAll("/", ":"));
				}
			}

			roleMap.put(userId, role);
			permissionMap.put(userId, permission);

			// shiro权限
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(role);
			info.setStringPermissions(permission);

			userAuthInfoMap.put(userId, info);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}

package com.ry.module.platform.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ry.module.platform.entity.Menu;

@Repository("permissionDao")
public interface PermissionDao {

	public List<Menu> getMenu();

	public List<Menu> getAllRolePermissionByUser(@Param("userId")String userId);
	
}

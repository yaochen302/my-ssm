package com.ry.module.platform.entity;

public class Menu {
	private String sysMenuId;
	private String sysMenuName;
	private String parentMenuId;
	private String url;
	private int isFuction;
	private int status;
	private int sort;

	public String getSysMenuId() {
		return sysMenuId;
	}

	public void setSysMenuId(String sysMenuId) {
		this.sysMenuId = sysMenuId;
	}

	public String getSysMenuName() {
		return sysMenuName;
	}

	public void setSysMenuName(String sysMenuName) {
		this.sysMenuName = sysMenuName;
	}

	public String getParentMenuId() {
		return parentMenuId;
	}

	public void setParentMenuId(String parentMenuId) {
		this.parentMenuId = parentMenuId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getIsFuction() {
		return isFuction;
	}

	public void setIsFuction(int isFuction) {
		this.isFuction = isFuction;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}
}

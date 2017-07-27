package com.ry.module.platform.entity;

public class sysMenu {
    private String sysMenuId;

    private String sysMenuName;

    private String parentMenuId;

    private String url;

    private Integer isFunction;

    private Integer status;

    private Integer sort;

    public String getSysMenuId() {
        return sysMenuId;
    }

    public void setSysMenuId(String sysMenuId) {
        this.sysMenuId = sysMenuId == null ? null : sysMenuId.trim();
    }

    public String getSysMenuName() {
        return sysMenuName;
    }

    public void setSysMenuName(String sysMenuName) {
        this.sysMenuName = sysMenuName == null ? null : sysMenuName.trim();
    }

    public String getParentMenuId() {
        return parentMenuId;
    }

    public void setParentMenuId(String parentMenuId) {
        this.parentMenuId = parentMenuId == null ? null : parentMenuId.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getIsFunction() {
        return isFunction;
    }

    public void setIsFunction(Integer isFunction) {
        this.isFunction = isFunction;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
package com.ry.module.platform.entity;

public class sysGroup {
    private String sysGroupId;

    private String sysGroupName;

    private Integer status;

    public String getSysGroupId() {
        return sysGroupId;
    }

    public void setSysGroupId(String sysGroupId) {
        this.sysGroupId = sysGroupId == null ? null : sysGroupId.trim();
    }

    public String getSysGroupName() {
        return sysGroupName;
    }

    public void setSysGroupName(String sysGroupName) {
        this.sysGroupName = sysGroupName == null ? null : sysGroupName.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
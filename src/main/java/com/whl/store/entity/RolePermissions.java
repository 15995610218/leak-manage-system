package com.whl.store.entity;

public class RolePermissions {
    private Integer rid;
    private Integer pid;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "RolePermissions{" +
                "rid=" + rid +
                ", pid=" + pid +
                '}';
    }
}

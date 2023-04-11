package com.whl.store.entity;

public class SysPermissions {
    private Integer pid;
    private String name;
    private String title;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "SysPermissions{" +
                "pid=" + pid +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}

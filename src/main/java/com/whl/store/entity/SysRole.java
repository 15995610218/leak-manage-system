package com.whl.store.entity;

public class SysRole {
    private Integer rid;
    private String title;
    private String status;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SysRole{" +
                "rid=" + rid +
                ", title='" + title + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

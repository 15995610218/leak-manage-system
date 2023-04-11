package com.whl.store.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class BaseEntity implements Serializable {
    private String createdUser;
    private Date createdTime;
    private String modifiedUser;
    private Date modifiedTime;

    public void setCreatedUser(String createdUser){
        this.createdUser = createdUser;
    }
    public void setCreatedTime(Date createdTime){
        this.createdTime = createdTime;
    }
    public void setModifiedUser(String modifiedUser){
        this.modifiedUser = modifiedUser;
    }
    public void setModifiedTime(Date modifiedTime){
        this.modifiedTime = modifiedTime;
    }
    public String getCreatedUser(String createdUser){
        return this.createdUser;
    }
    public Date getCreatedTime(Date createdTime){
        return this.createdTime;
    }
    public String getModifiedUser(String modifiedUser){
        return this.modifiedUser;
    }
    public Date getModifiedTime(Date modifiedTime){
        return this.modifiedTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(createdUser, that.createdUser) && Objects.equals(createdTime, that.createdTime) && Objects.equals(modifiedUser, that.modifiedUser) && Objects.equals(modifiedTime, that.modifiedTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(createdUser, createdTime, modifiedUser, modifiedTime);
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "createUser='" + createdUser + '\'' +
                ", createTime=" + createdTime +
                ", modifiedUser='" + modifiedUser + '\'' +
                ", modifiedTime=" + modifiedTime +
                '}';
    }
}

package com.whl.store.entity;

import java.util.List;

public class ResultUser<T> {
    private List<T> permissions;
    private String username;
    private String token;

    public List<T> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<T> permissions) {
        this.permissions = permissions;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

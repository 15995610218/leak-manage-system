package com.whl.store.entity;

public class RequestAddUser {
    private String username;
    private String oldpassword;
    private String password;
    private String checkPass;
    private Integer rid;
    private String status;

    public String getOldpassword() {
        return oldpassword;
    }

    public void setOldpassword(String oldpassword) {
        this.oldpassword = oldpassword;
    }

    public void setStatus(String status){ this.status = status;}

    public String getStatus(){
        return this.status;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setCheckPass(String checkPass){
        this.checkPass = checkPass;
    }

    public void setRid(Integer rid){
        this.rid = rid;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public String getCheckPass(){
        return this.checkPass;
    }

    public Integer getRid(){
        return this.rid;
    }

    @Override
    public String toString() {
        return "RequestAddUser{" +
                "username='" + username + '\'' +
                ", oldpassword='" + oldpassword + '\'' +
                ", password='" + password + '\'' +
                ", checkPass='" + checkPass + '\'' +
                ", rid=" + rid +
                ", status='" + status + '\'' +
                '}';
    }
}

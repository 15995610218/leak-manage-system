package com.whl.store.util;

import java.io.Serializable;

public class JsonResult<E> implements Serializable {
    /**
     * 状态码
     */
    private Integer state;
    /**
     * 描述信息
     */
    private String message;
    /**
     * 数据
     */
    private E data;

    public JsonResult(){
    }
    public JsonResult(Integer state,E data){
        this.data = data;
        this.state = state;
    }
    public JsonResult(Integer state){
        this.state = state;
    }
    public JsonResult(Throwable e){
        this.message = e.getMessage();
    }

    public void setState(Integer state){
        this.state = state;
    }
    public void setMessage(String message){
        this.message = message;
    }
    public void setData(E data){
        this.data = data;
    }
    public Integer getState(){
        return this.state;
    }
    public String getMessage(){
        return this.message;
    }
    public E getData(){
        return this.data;
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "state=" + state +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}

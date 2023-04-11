package com.whl.store.entity;

import java.util.List;

public class PageData<T> {
    private List<T> rows;
    private Integer totalcount;
    public void setRows(List<T> rows){
        this.rows = rows;
    }
    public void setTotalcount(Integer totalcount){
        this.totalcount = totalcount;
    }
    public List<T> getRows(){
        return this.rows;
    }
    public Integer getTotalcount(){
        return this.totalcount;
    }
}

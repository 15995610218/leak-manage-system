package com.whl.store.entity;

public class ExportDate {
    private String beginDate;
    private String andDate;

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getAndDate() {
        return andDate;
    }

    public void setAndDate(String andDate) {
        this.andDate = andDate;
    }

    @Override
    public String toString() {
        return "exportDate{" +
                "beginDate='" + beginDate + '\'' +
                ", andDate='" + andDate + '\'' +
                '}';
    }
}

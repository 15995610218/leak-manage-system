package com.whl.store.entity;


import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ExcelTarget("ksleak")
public class KsLeak implements Serializable {

    @Excel(name = "编号",orderNum = "0")
    private Integer id;
    @Excel(name = "漏洞名称",orderNum = "1")
    private String bugName;
    @Excel(name = "CVE编号",orderNum = "2")
    private String cve;
    @Excel(name = "发现时间",orderNum = "3",format = "yyyy-MM-dd")
    private Date releaseDate;
    @Excel(name = "危险级别",orderNum = "4")
    private String threatLevel;
    @Excel(name = "漏洞描述",orderNum = "5")
    private String bugInformation;
    @Excel(name = "系统名称",orderNum = "6")
    private String systemName;
    @Excel(name = "运行环境",orderNum = "7")
    private String environName;
    @Excel(name = "IP地址",orderNum = "8")
    private String ipAddress;
    @Excel(name = "负责人",orderNum = "9")
    private String processPerson;
    @Excel(name = "团队名称",orderNum = "10")
    private String deptName;
    @Excel(name = "修复方案",orderNum = "11")
    private String processPlan;
    @Excel(name = "修复计划",orderNum = "12",format = "yyyy-MM-dd")
    private Date processDate;
    @Excel(name = "修复结果",orderNum = "13")
    private String processResult;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBugName() {
        return bugName;
    }

    public void setBugName(String bugName) {
        this.bugName = bugName;
    }

    public String getCve() {
        return cve;
    }

    public void setCve(String cve) {
        this.cve = cve;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getThreatLevel() {
        return threatLevel;
    }

    public void setThreatLevel(String threatLevel) {
        this.threatLevel = threatLevel;
    }

    public String getBugInformation() {
        return bugInformation;
    }

    public void setBugInformation(String bugInformation) {
        this.bugInformation = bugInformation;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getEnvironName() {
        return environName;
    }

    public void setEnvironName(String environName) {
        this.environName = environName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getProcessPerson() {
        return processPerson;
    }

    public void setProcessPerson(String processPerson) {
        this.processPerson = processPerson;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getProcessPlan() {
        return processPlan;
    }

    public void setProcessPlan(String processPlan) {
        this.processPlan = processPlan;
    }

    public Date getProcessDate() {
        return processDate;
    }

    public void setProcessDate(Date processDate) {
        this.processDate = processDate;
    }

    public String getProcessResult() {
        return processResult;
    }

    public void setProcessResult(String processResult) {
        this.processResult = processResult;
    }

    @Override
    public String toString() {
        return "KsLeak{" +
                "id=" + id +
                ", bugName='" + bugName + '\'' +
                ", cve='" + cve + '\'' +
                ", releaseDate=" + releaseDate +
                ", threatLevel='" + threatLevel + '\'' +
                ", bugInformation='" + bugInformation + '\'' +
                ", systemName='" + systemName + '\'' +
                ", environName='" + environName + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", processPerson='" + processPerson + '\'' +
                ", deptName='" + deptName + '\'' +
                ", processPlan='" + processPlan + '\'' +
                ", processDate=" + processDate +
                ", processResult='" + processResult + '\'' +
                '}';
    }
}

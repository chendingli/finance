package com.chinasoft.mybatis.entity;


import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "sys_personl")
public class SysPersonl implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;         // id 主键

    @Excel(name = "工号",height = 25,width = 30)
    @Column(name = "unmber")
    private Long unmberId;

    @Excel(name = "姓名")
    private String name;

    @Excel(name = "工资")
    @Column(name = "pay")
    private Double sal;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "time")
    private Date rtime;

    @Excel(name = "类型")
    private String type;

    @Excel(name = "状态")
    private String state;

    @Column(name = "time_search")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date timeSearch;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    public Date getTimeSearch() { return timeSearch; }

    public void setTimeSearch(Date timeSearch) { this.timeSearch = timeSearch; }

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    public Date getRtime() { return rtime; }

    public void setRtime(Date rtime) { this.rtime = rtime; }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUnmberId() {
        return unmberId;
    }

    public void setUnmberId(Long unmberId) {
        this.unmberId = unmberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSal() {
        return sal;
    }

    public void setSal(Double sal) {
        this.sal = sal;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "SysPersonl{" +
                "id=" + id +
                ", unmberId=" + unmberId +
                ", name='" + name + '\'' +
                ", sal=" + sal +
                ", rtime=" + rtime +
                ", type='" + type + '\'' +
                ", state='" + state + '\'' +
                ", timeSearch=" + timeSearch +
                '}';
    }
}

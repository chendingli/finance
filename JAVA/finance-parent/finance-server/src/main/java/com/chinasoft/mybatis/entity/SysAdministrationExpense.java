package com.chinasoft.mybatis.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Table(name = "sys_administration_expense")
public class SysAdministrationExpense {

    /**
     * 支出项编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer number;

    /**
     * 支出类型
     */
    @Column(name = "type")
    private Integer type;

    /**
     * 支出金额
     */
    @Column(name = "expense")
    private Double expense;

    /**
     * 支出时间
     */
    @Column(name = "time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date time;

    /**
     * 明细
     */
    @Column(name = "details")
    private String details;

    /**
     * 备注
     */
    @Column(name = "remarks")
    private String remarks;

    /**
     * 创建人
     */
    @Column(name = "create_name")
    private String createName;

    /**
     * 搜索时间
     */
    @Column(name = "time_search")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date timeSearch;

    public SysAdministrationExpense() {
    }

    public SysAdministrationExpense(Integer type, Double expense, Date time, String details, String remarks, String createName, Date timeSearch) {
        this.type = type;
        this.expense = expense;
        this.time = time;
        this.details = details;
        this.remarks = remarks;
        this.createName = createName;
        this.timeSearch = timeSearch;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Double getExpense() {
        return expense;
    }

    public void setExpense(Double expense) {
        this.expense = expense;
    }

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public Date getTimeSearch() {
        return timeSearch;
    }

    public void setTimeSearch(Date timeSearch) {
        this.timeSearch = timeSearch;
    }

    @Override
    public String toString() {
        return "SysAdministrationExpense{" +
                "number=" + number +
                ", type=" + type +
                ", expense=" + expense +
                ", time=" + time +
                ", details='" + details + '\'' +
                ", remarks='" + remarks + '\'' +
                ", createName='" + createName + '\'' +
                ", timeSearch=" + timeSearch +
                '}';
    }
}

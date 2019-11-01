package com.chinasoft.mybatis.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Table(name = "sys_client_expense")
public class SysClientExpense {

    /**
     * 支出项ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 客户姓名
     */
    @Column(name = "client_name")
    private String clientName;

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

    public SysClientExpense() {
    }

    public SysClientExpense(String clientName, Integer type, Double expense, Date time, String details, String createName, Date timeSearch) {
        this.clientName = clientName;
        this.type = type;
        this.expense = expense;
        this.time = time;
        this.details = details;
        this.createName = createName;
        this.timeSearch = timeSearch;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
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
        return "SysClientExpense{" +
                "id=" + id +
                ", clientName='" + clientName + '\'' +
                ", type=" + type +
                ", expense=" + expense +
                ", time=" + time +
                ", details='" + details + '\'' +
                ", createName='" + createName + '\'' +
                ", timeSearch=" + timeSearch +
                '}';
    }
}

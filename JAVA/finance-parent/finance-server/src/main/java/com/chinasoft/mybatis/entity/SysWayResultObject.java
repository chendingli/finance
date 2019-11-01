package com.chinasoft.mybatis.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class SysWayResultObject {
    /**
     * 客户ID
     */
    private Long ClientId;

    /**
     * 客户名
     */
    private String clientName;

    /**
     * 支出类型
     */
    private Integer type;

    /**
     * 支出金额
     */
    private Double expense;

    /**
     * 支出时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date time;

    /**
     * 明细
     */
    private String details;

    /**
     * 支出途径
     */
    private String way;

    /**
     * 每种支出途径对应金额
     */
    private String wayExpense;

    /**
     *创建人
     */
    private String createName;

    /**
     * 每个客户对应总支出途径项
     */
    private List<SysExpense> list;

    public SysWayResultObject() {
    }

    public SysWayResultObject(Long clientId, String clientName, Integer type, Double expense, Date time, String details, String way, String wayExpense, String createName, List<SysExpense> list) {
        ClientId = clientId;
        this.clientName = clientName;
        this.type = type;
        this.expense = expense;
        this.time = time;
        this.details = details;
        this.way = way;
        this.wayExpense = wayExpense;
        this.createName = createName;
        this.list = list;
    }

    public Long getClientId() {
        return ClientId;
    }

    public void setClientId(Long id) {
        this.ClientId = id;
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

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public String getWayExpense() {
        return wayExpense;
    }

    public void setWayExpense(String wayExpense) {
        this.wayExpense = wayExpense;
    }

    public List<SysExpense> getList() {
        return list;
    }

    public void setList(List<SysExpense> list) {
        this.list = list;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    @Override
    public String toString() {
        return "SysWayResultObject{" +
                "ClientId=" + ClientId +
                ", clientName='" + clientName + '\'' +
                ", type=" + type +
                ", expense=" + expense +
                ", time=" + time +
                ", details='" + details + '\'' +
                ", way='" + way + '\'' +
                ", wayExpense='" + wayExpense + '\'' +
                ", createName='" + createName + '\'' +
                ", list=" + list +
                '}';
    }
}

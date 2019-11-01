package com.chinasoft.mybatis.entity;

import javax.persistence.*;

@Table(name = "sys_expense_way")
public class SysExpenseWay {

    /**
     * 支出途径ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 客户ID
     */
    @Column(name = "client_id")
    private Long clientId;

    /**
     * 支出类型
     */
    @Column(name = "way_type")
    private Integer wayType;

    /**
     * 支出金额
     */
    @Column(name = "way_expense")
    private Double wayExpense;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Integer getWayType() {
        return wayType;
    }

    public void setWayType(Integer wayType) {
        this.wayType = wayType;
    }

    public Double getWayExpense() {
        return wayExpense;
    }

    public void setWayExpense(Double wayExpense) {
        this.wayExpense = wayExpense;
    }

    @Override
    public String toString() {
        return "SysExpenseWay{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", wayType=" + wayType +
                ", wayExpense='" + wayExpense + '\'' +
                '}';
    }
}

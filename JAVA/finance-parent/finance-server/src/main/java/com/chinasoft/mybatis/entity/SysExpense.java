package com.chinasoft.mybatis.entity;

import javax.persistence.*;

@Table(name = "sys_expense_way")
public class SysExpense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "way_type")
    private Integer wayType;

    @Column(name = "way_expense")
    private Double wayExpense;

    public SysExpense() {
    }

    public SysExpense(Long clientId, Integer wayType, Double wayExpense) {
        this.clientId = clientId;
        this.wayType = wayType;
        this.wayExpense = wayExpense;
    }

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
        return "SysExpense{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", wayType=" + wayType +
                ", wayExpense=" + wayExpense +
                '}';
    }
}

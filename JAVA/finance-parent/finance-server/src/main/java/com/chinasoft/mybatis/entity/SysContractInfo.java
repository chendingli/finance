package com.chinasoft.mybatis.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
/**
 * Name:WangJinhui
 * Date:2018-12-10
 */
public class SysContractInfo {

    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 合同编号
     */
    private Integer number;

    /**
     * 合同名称
     */
    private String name;

    /**
     * 关联客户
     */
    @Column(name = "associated_customers")
    private String associatedCustomers;

    /**
     * 合同类型
     */
    private String type;

    /**
     * 合同金额
     */
    private Double money;

    /**
     * 创建人
     */
    @Column(name = "create_name")
    private String createName;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 已回款
     */
    @Column(name = "back_money")
    private Double backMoney;

    /**
     * 待回款
     */
    @Column(name = "left_money")
    private Double leftMoney;

    /**
     * 总支出
     */
    @Column(name = "total_money")
    private Double totalMoney;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAssociatedCustomers() {
        return associatedCustomers;
    }

    public void setAssociatedCustomers(String associatedCustomers) {
        this.associatedCustomers = associatedCustomers;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Double getBackMoney() {
        return backMoney;
    }

    public void setBackMoney(Double backMoney) {
        this.backMoney = backMoney;
    }

    public Double getLeftMoney() {
        return leftMoney;
    }

    public void setLeftMoney(Double leftMoney) {
        this.leftMoney = leftMoney;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    @Override
    public String toString() {
        return "SysContractInfo{" +
                "id=" + id +
                ", number=" + number +
                ", name='" + name + '\'' +
                ", associatedCustomers='" + associatedCustomers + '\'' +
                ", type='" + type + '\'' +
                ", money=" + money +
                ", createName='" + createName + '\'' +
                ", createTime=" + createTime +
                ", backMoney=" + backMoney +
                ", leftMoney=" + leftMoney +
                ", totalMoney=" + totalMoney +
                '}';
    }
}

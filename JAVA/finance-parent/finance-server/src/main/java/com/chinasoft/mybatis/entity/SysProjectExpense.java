package com.chinasoft.mybatis.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Table(name = "sys_project_expense")
public class SysProjectExpense {

    /**
     * 支出项ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 合同编号
     */
    @Column(name = "contract_number")
    private Integer contractNumber;

    /**
     * 项目名称
     */
    @Column(name = "project_name")
    private String projectName;

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

    public SysProjectExpense() {
    }

    public SysProjectExpense(Integer contractNumber, String projectName, Integer type, Double expense, Date time, String details, String createName, Date timeSearch) {
        this.contractNumber = contractNumber;
        this.projectName = projectName;
        this.type = type;
        this.expense = expense;
        this.time = time;
        this.details = details;
        this.createName = createName;
        this.timeSearch = timeSearch;
    }

    public Double getExpense() {
        return expense;
    }

    public void setExpense(Double expense) {
        this.expense = expense;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(Integer contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    public Date getTimeSearch() {
        return timeSearch;
    }

    public void setTimeSearch(Date timeSearch) {
        this.timeSearch = timeSearch;
    }

    @Override
    public String toString() {
        return "SysProjectExpense{" +
                "id=" + id +
                ", contractNumber=" + contractNumber +
                ", projectName='" + projectName + '\'' +
                ", type=" + type +
                ", expense=" + expense +
                ", time=" + time +
                ", details='" + details + '\'' +
                ", createName='" + createName + '\'' +
                ", timeSearch=" + timeSearch +
                '}';
    }
}

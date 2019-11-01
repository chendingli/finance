package com.chinasoft.mybatis.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class SysBackMoney {

    private Long id;

    private Integer number;

    //合同编号
    private Integer contractNo;

    //合同名称
    private String contractName;

    //合同类型
    private String contractType;

    //合同金额
    private Double contractMoney;

    //回款金额
    private Double backMoney;

    //期数
    private String period;

    //预计回款时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date estimateTime;

    //开票状态
    private Integer invoiceState;

    //开票时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date invoiceTime;

    //回款状态
    private Integer backMoneyState;

    //回款时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date backMoneyTime;

    //发票邮寄地址
    private String address;

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

    public Integer getContractNo() {
        return contractNo;
    }

    public void setContractNo(Integer contractNo) {
        this.contractNo = contractNo;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public Double getContractMoney() {
        return contractMoney;
    }

    public void setContractMoney(Double contractMoney) {
        this.contractMoney = contractMoney;
    }

    public Double getBackMoney() {
        return backMoney;
    }

    public void setBackMoney(Double backMoney) {
        this.backMoney = backMoney;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    public Date getEstimateTime() {
        return estimateTime;
    }

    public void setEstimateTime(Date estimateTime) {
        this.estimateTime = estimateTime;
    }

    public Integer getInvoiceState() {
        return invoiceState;
    }

    public void setInvoiceState(Integer invoiceState) {
        this.invoiceState = invoiceState;
    }

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    public Date getInvoiceTime() {
        return invoiceTime;
    }

    public void setInvoiceTime(Date invoiceTime) {
        this.invoiceTime = invoiceTime;
    }

    public Integer getBackMoneyState() {
        return backMoneyState;
    }

    public void setBackMoneyState(Integer backMoneyState) {
        this.backMoneyState = backMoneyState;
    }

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    public Date getBackMoneyTime() {
        return backMoneyTime;
    }

    public void setBackMoneyTime(Date backMoneyTime) {
        this.backMoneyTime = backMoneyTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

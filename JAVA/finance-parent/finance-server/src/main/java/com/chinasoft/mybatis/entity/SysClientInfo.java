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
public class SysClientInfo {


    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 客户姓名
     */
    private String name;

    /**
     * 联系方式
     */
    private String phone;

    /**
     * 客户关怀总支出
     */
    @Column(name = "client_expense" )
    private Double clientExpense;

    /**
     * 合同总金额
     */
    @Column(name = "contract_money")
    private Double contractMoney;


    /**
     * 合同编号
     */
    @Column(name = "contract_number")
    private Integer contractNumber;

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




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getClientExpense() {
        return clientExpense;
    }

    public void setClientExpense(Double clientExpense) {
        this.clientExpense = clientExpense;
    }

    public Double getContractMoney() {
        return contractMoney;
    }

    public void setContractMoney(Double contractMoney) {
        this.contractMoney = contractMoney;
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

    public Integer getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(Integer contractNumber) {
        this.contractNumber = contractNumber;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    @Override
    public String toString() {
        return "SysClientInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", clientExpense=" + clientExpense +
                ", contractMoney=" + contractMoney +
                ", contractNumber=" + contractNumber +
                ", createName='" + createName + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}

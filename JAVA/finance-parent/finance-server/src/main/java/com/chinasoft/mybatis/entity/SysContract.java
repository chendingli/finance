package com.chinasoft.mybatis.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author 陈定立
 * @Date: 2018/12/5
 * @Description: 合同 实体类
 */

@Table(name = "sys_contract")
public class SysContract {
    /**
     * 合同id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id ;

    /**
     * 合同编号
     */
    @Column(name = "number")
    private Integer number ;

    /**
     * 合同名称
     */
    @Column(name = "name")
    private String name ;

    /**
     * 合同类型
     */
    @Column(name = "type")
    private String type ;

    /**
     * 合同金额
     */
    @Column(name = "money")
    private double money ;

    /**
     * 创建人
     */
    @Column(name = "create_name")
    private String createName;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    /**
     * 关联客户
     */
    /**
     * 关联客户名
     */
    @Column(name = "associated_customers")
    private String customers;
    /**
     * 报头信息
     */
    /**
     * 发票抬头
     */
    @Column(name = "invoice")
    private String invoice;
    /**
     * 税号
     */
    /**
     * 税号
     */
    @Column(name = "taxNumber")
    private String taxNumber ;
    /**
     * 地址
     */
    /**
     * 单位地址
     */
    @Column(name = "address")
    private String address ;
    /**
     * 电话号码
     */
    /**
     * 电话号码
     */
    @Column(name = "phoneNumber")
    private String phoneNumber;
    /**
     * 开户银行
     */
    /**
     * 开户银行
     */
    @Column(name = "bank")
    private  String bank;
    /**
     * 账户
     */
    /**
     * 银行账号
     */
    @Column(name = "account")
    private  String account;
    /**
     * 首期付款额
     */
    /**
     * 首期付款金额
     */
    @Column(name = "ini_paymoney")
    private Double iniPaymoney;
    /**
     * 首期回款时间
     */
    /**
     * 首期付款时间
     */
    @Column(name = "paytime_one")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date paytimeOne;
    /**
     * 二期回款额
     */
    /**
     * 二期付款金额
     */
    @Column(name = "sec_paymoney")
    private Double secPaymoney;
    /**
     * 二期回款时间
     */
    /**
     * 二期付款时间
     */
    @Column(name = "paytime_two")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date paytimeTwo;
    /**
     * 三期回款额
     */
    /**
     * 三期付款金额
     */
    @Column(name = "third_paymoney")
    private Double thirdPaymoney;
    /**
     * 三期回款时间
     */
    /**
     * 三期付款时间
     */
    @Column(name = "paytime_three")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date paytimeThree;
    /**
     * 税点
     */
    /**
     * 税点
     */
    @Column(name = "tax_point")
    private Double taxPoint;
    /**
     * 税费
     */
    /**
     * 税金
     */
    @Column(name = "tax")
    private Double tax;
    /**
     * 文件本地路径
     */
    /**
     * 文件上传路径
     */
    @Column(name = "file_address")
    private String fileAddress;
    /**
     * 文件上传服务器路径
     */
    /**
     * 文件访问路径
     */
    @Column(name = "url")
    private String url;
    /**
     * 首期付款比例
     */
    /**
     * 首期付款比例
     */
    @Column(name = "one")
    private Double first;
    /**
     * 二期还款比例
     */
    /**
     * 二期付款比例
     */
    @Column(name = "two")
    private Double second;
    /**
     * 三期还款比例
     */
    /**
     * 三期付款比例
     */
    @Column(name = "three")
    private Double third;

    public SysContract() {
        super();
    }

    public SysContract(Integer number, String name, String type, double money, String createName, Date createTime, String customers, String invoice, String taxNumber, String address, String phoneNumber, String bank, String account, Double iniPaymoney, Date paytimeOne, Double secPaymoney, Date paytimeTwo, Double thirdPaymoney, Date paytimeThree, Double taxPoint, Double tax, String fileAddress, String url, Double first, Double second, Double third) {
        this.number = number;
        this.name = name;
        this.type = type;
        this.money = money;
        this.createName = createName;
        this.createTime = createTime;
        this.customers = customers;
        this.invoice = invoice;
        this.taxNumber = taxNumber;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.bank = bank;
        this.account = account;
        this.iniPaymoney = iniPaymoney;
        this.paytimeOne = paytimeOne;
        this.secPaymoney = secPaymoney;
        this.paytimeTwo = paytimeTwo;
        this.thirdPaymoney = thirdPaymoney;
        this.paytimeThree = paytimeThree;
        this.taxPoint = taxPoint;
        this.tax = tax;
        this.fileAddress = fileAddress;
        this.url = url;
        this.first = first;
        this.second = second;
        this.third = third;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
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

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Double getIniPaymoney() {
        return iniPaymoney;
    }

    public void setIniPaymoney(Double iniPaymoney) {
        this.iniPaymoney = iniPaymoney;
    }

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    public Date getPaytimeOne() {
        return paytimeOne;
    }

    public void setPaytimeOne(Date paytimeOne) {
        this.paytimeOne = paytimeOne;
    }

    public Double getSecPaymoney() {
        return secPaymoney;
    }

    public void setSecPaymoney(Double secPaymoney) {
        this.secPaymoney = secPaymoney;
    }
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    public Date getPaytimeTwo() {
        return paytimeTwo;
    }

    public void setPaytimeTwo(Date paytimeTwo) {
        this.paytimeTwo = paytimeTwo;
    }

    public Double getThirdPaymoney() {
        return thirdPaymoney;
    }

    public void setThirdPaymoney(Double thirdPaymoney) {
        this.thirdPaymoney = thirdPaymoney;
    }

    @JsonFormat(pattern = "yyyy-MM--dd",timezone = "GMT-8")
    public Date getPaytimeThree() {
        return paytimeThree;
    }

    public void setPaytimeThree(Date paytimeThree) {
        this.paytimeThree = paytimeThree;
    }

    public Double getTaxPoint() {
        return taxPoint;
    }

    public void setTaxPoint(Double taxPoint) {
        this.taxPoint = taxPoint;
    }

    public String getCustomers() {
        return customers;
    }

    public void setCustomers(String customers) {
        this.customers = customers;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public String getFileAddress() {
        return fileAddress;
    }

    public void setFileAddress(String fileAddress) {
        this.fileAddress = fileAddress;
    }


    public Double getFirst() {
        return first;
    }

    public void setFirst(Double first) {
        this.first = first;
    }

    public Double getSecond() {
        return second;
    }

    public void setSecond(Double second) {
        this.second = second;
    }

    public Double getThird() {
        return third;
    }

    public void setThird(Double third) {
        this.third = third;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "SysContract{" +
                "id=" + id +
                ", number=" + number +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", money=" + money +
                ", createName='" + createName + '\'' +
                ", createTime=" + createTime +
                ", customers='" + customers + '\'' +
                ", invoice='" + invoice + '\'' +
                ", taxNumber='" + taxNumber + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", bank='" + bank + '\'' +
                ", account='" + account + '\'' +
                ", iniPaymoney=" + iniPaymoney +
                ", paytimeOne=" + paytimeOne +
                ", secPaymoney=" + secPaymoney +
                ", paytimeTwo=" + paytimeTwo +
                ", thirdPaymoney=" + thirdPaymoney +
                ", paytimeThree=" + paytimeThree +
                ", taxPoint=" + taxPoint +
                ", tax=" + tax +
                ", fileAddress='" + fileAddress + '\'' +
                ", url='" + url + '\'' +
                ", first=" + first +
                ", second=" + second +
                ", third=" + third +
                '}';
    }
}

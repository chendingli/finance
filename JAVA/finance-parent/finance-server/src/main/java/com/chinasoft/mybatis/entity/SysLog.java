package com.chinasoft.mybatis.entity;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Table(name = "sys_log")
public class SysLog {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;         // id 主键

    private Integer type; 		// 日志类型（1：接入日志；2：错误日志）
    private String title;		// 日志标题

    @Column(name = "remote_addr")
    private String remoteAddr; 	// 操作用户的IP地址

    @Column(name = "request_uri")
    private String requestUri; 	// 操作的URI
    private String method; 		// 操作的方式
    private String params; 		// 操作提交的数据

    @Column(name = "user_agent")
    private String userAgent;	// 操作用户代理信息
    private String exception; 	// 异常信息

    @Column(name = "create_by")
    private String createBy;   // 创建者用户名

    @Column(name = "create_time")
    private Date createTime;    // 创建时间

    @Transient
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date beginDate;		// 开始日期

    @Transient
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date endDate;		// 结束日期

    // 日志类型（1：接入日志；2：错误日志）
    @Transient
    public static final Integer TYPE_ACCESS = 1;

    @Transient
    public static final Integer TYPE_EXCEPTION = 2;

    public SysLog(){
        super();
    }

    public SysLog(Long id){
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getRequestUri() {
        return requestUri;
    }

    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }



    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
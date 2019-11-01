package com.chinasoft.service;

import com.chinasoft.mybatis.entity.SysContract;
import com.chinasoft.mybatis.entity.SysContractInfo;
import com.github.pagehelper.PageInfo;

import java.sql.Date;

/**
 * @Author 陈定立
 * @Date: 2018/12/14
 * @Description:合同service
 */

public interface SysContractInfoService extends BaseService<SysContractInfo> {

    //分页查询合同列表
    PageInfo<SysContractInfo> findPage(Integer pageNum, Integer pageSize, SysContractInfo sysContractInfo);

   //得到合同表的最大合同编号
    Integer getNumber();

    //创建合同
    void addContract(SysContract sysContract);

    //往回款表写数据：回款编号、合同编号、回款额、预计回款时间、期数
    void addPeriod(Integer number, Integer contractNo, Double backMoney,
                    Date estimateTime, Integer period);

   //查询回款表最大回款编号
    Integer findMaxNo();
}

package com.chinasoft.service;

import com.chinasoft.mybatis.entity.SysBackMoney;
import com.github.pagehelper.PageInfo;

import java.sql.Date;
import java.util.List;

public interface SysBackMoneyService extends BaseService<SysBackMoney> {

    PageInfo<SysBackMoney> findPage(Integer pageNum, Integer pageSize, SysBackMoney sysBackMoney);

    List<SysBackMoney> selectBackMoneyByNo(Integer number);

    void updateBack(Integer number);

    void updateInvoice(String address,Integer number);

}

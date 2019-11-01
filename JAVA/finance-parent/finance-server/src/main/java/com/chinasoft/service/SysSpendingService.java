package com.chinasoft.service;

import com.chinasoft.mybatis.entity.SysLetters;
import com.chinasoft.mybatis.entity.SysSpending;

import java.util.List;

public interface SysSpendingService extends BaseService<SysSpending>{

    //查询各项支出总金额
    List<SysSpending> selectSumTack(SysSpending sysSpending);

    //查询支出总金额
    Double selectTotal();

    //查询资金流水
    Double selectRunn();
}

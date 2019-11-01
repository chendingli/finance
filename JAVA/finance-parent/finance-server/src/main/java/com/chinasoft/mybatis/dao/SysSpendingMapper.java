package com.chinasoft.mybatis.dao;

import com.chinasoft.mybatis.entity.SysLetters;
import com.chinasoft.mybatis.entity.SysSpending;
import com.chinasoft.util.MyMapper;

import java.util.List;

public interface SysSpendingMapper  extends MyMapper<SysSpending> {

    List<SysSpending> selectSumTack(SysSpending sysSpending);
    Double selectTotal();
    Double selectRunn();
}

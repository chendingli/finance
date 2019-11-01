package com.chinasoft.mybatis.dao;

import com.chinasoft.mybatis.entity.SysExpense;
import com.chinasoft.util.MyMapper;

import java.util.List;

public interface SysExpenseMapper extends MyMapper<SysExpense> {


    List<SysExpense> selectByClientId(Long clientId);
}

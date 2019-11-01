package com.chinasoft.service;


import com.chinasoft.mybatis.entity.SysContract;
import com.chinasoft.mybatis.entity.SysProjectExpense;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface SysProjectExpenseService extends BaseService<SysProjectExpense> {

    PageInfo findPage(Integer pageNum, Integer pageSize,SysProjectExpense sysProjectExpense);

    void insertProjectExpense(SysProjectExpense sysProjectExpense);

    String findContractNameByNumber(Integer number);

    SysProjectExpense get(Long id);

    void update(SysProjectExpense sysProjectExpense);

    List<String> findContract();

    Integer findNumberByName(String name);

    Integer selectProject();

    List<SysProjectExpense> selectProjectExpense();
}

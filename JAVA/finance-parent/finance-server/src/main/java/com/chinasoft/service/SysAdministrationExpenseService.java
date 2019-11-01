package com.chinasoft.service;

import com.chinasoft.mybatis.entity.SysAdministrationExpense;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface SysAdministrationExpenseService extends BaseService<SysAdministrationExpense> {

    PageInfo findPage(Integer pageNum, Integer pageSize,SysAdministrationExpense sysAdministrationExpense);

    void insertAdministratinoExpense(SysAdministrationExpense sysAdministrationExpense);

    SysAdministrationExpense get(Long id);

    void update(SysAdministrationExpense sysAdministrationExpense);

    Integer selectSumExpense();

    List<SysAdministrationExpense> selectAdministrationExpense();
}

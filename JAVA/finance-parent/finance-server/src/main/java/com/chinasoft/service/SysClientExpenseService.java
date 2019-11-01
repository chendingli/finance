package com.chinasoft.service;

import com.chinasoft.mybatis.entity.SysClientExpense;
import com.chinasoft.mybatis.entity.SysExpense;
import com.chinasoft.mybatis.entity.SysExpenseWay;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface SysClientExpenseService extends BaseService<SysClientExpense> {

    PageInfo findPage(Integer pageNum, Integer pageSize,SysClientExpense sysClientExpense);

    int insertClientEx(SysClientExpense sysClientExpense);

    int insertExpense(SysExpense sysExpense);

    List<String> findClientName();

    SysClientExpense get(Long id);

    List<SysExpense> getExpense(Long id);

    void update(SysClientExpense sysClientExpense);

    List<SysClientExpense> selectSysClientExpense();

    int insertExpense(SysExpenseWay sysExpenseWay);

    Integer seceltClientSum();


}

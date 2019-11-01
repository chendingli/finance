package com.chinasoft.mybatis.dao;

import com.chinasoft.mybatis.entity.SysAdministrationExpense;
import com.chinasoft.util.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysAdministrationExpenseMapper extends MyMapper<SysAdministrationExpense> {

    Integer selectSumExpense();
    List<SysAdministrationExpense> selectAdministrationExpense();
}

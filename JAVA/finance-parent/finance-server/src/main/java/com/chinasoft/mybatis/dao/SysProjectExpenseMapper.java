package com.chinasoft.mybatis.dao;

import com.chinasoft.mybatis.entity.SysContract;
import com.chinasoft.mybatis.entity.SysProjectExpense;
import com.chinasoft.util.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SysProjectExpenseMapper extends MyMapper<SysProjectExpense> {

    String findContractNameByNumber(@Param("number") Integer number);

    List<String> findContract();

    Integer findNumberByName(@Param("name") String name);

    Integer selectProject();

    List<SysProjectExpense> selectProjectExpense();
}

package com.chinasoft.mybatis.dao;

import com.chinasoft.mybatis.entity.SysClientExpense;
import com.chinasoft.mybatis.entity.SysExpense;
import com.chinasoft.util.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysClientExpenseMapper extends MyMapper<SysClientExpense> {

    List<String> findClientName();


    int insertClientEx(SysClientExpense sysClientExpense);

    List<SysExpense> selectByClientId(@Param("id") Long id);



    List<SysClientExpense> selectSysClientExpense();


    Integer seceltClientSum();
}

package com.chinasoft.mybatis.dao;

import com.chinasoft.mybatis.entity.SysBackMoney;
import com.chinasoft.util.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface SysBackMoneyMapper extends MyMapper<SysBackMoney> {

    List<SysBackMoney> selectAllBackMoneyInfo(@Param("contractName")String contractName,
                                              @Param("invoiceState")Integer invoiceState,
                                              @Param("backMoneyState")Integer backMoneyState);

    List<SysBackMoney> selectBackMoneyByNo(@Param("number")Integer number);

    void updateBack(@Param("number") Integer number,@Param("backMoneyTime")Date backMoneyTime);

    void updateInvoice(@Param("address")String address,
                       @Param("number")Integer number,
                       @Param("invoiceTime")Date invoiceTime);

}

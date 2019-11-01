package com.chinasoft.service;

import com.chinasoft.mybatis.entity.SysLetters;

import java.util.List;

public interface SysLettersService extends BaseService<SysLetters>{

    //查询合同的数量,总金额,已回款,待回款
    List<SysLetters> selectSumContract(SysLetters sysLetters);

    //查询各项支出总金额
    List<SysLetters> selectSumTack(SysLetters sysLetters);

    //查询支出总金额
    List<SysLetters> selectTotal(SysLetters sysLetters);
}

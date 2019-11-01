package com.chinasoft.mybatis.dao;

import com.chinasoft.mybatis.entity.SysContractInfo;
import com.chinasoft.mybatis.entity.SysLetters;
import com.chinasoft.util.MyMapper;

import java.util.List;

public interface SysLettersMapper extends MyMapper<SysLetters> {

    List<SysLetters> selectSumContract(SysLetters sysLetters);
    List<SysLetters> selectSumTack(SysLetters sysLetters);
    List<SysLetters> selectTotal(SysLetters sysLetters);
}

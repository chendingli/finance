package com.chinasoft.service.impl;

import com.chinasoft.mybatis.dao.SysLettersMapper;
import com.chinasoft.mybatis.entity.SysLetters;
import com.chinasoft.service.SysLettersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysLettersServiceImpl extends BaseServiceImpl<SysLetters> implements SysLettersService{

    @Autowired
    private SysLettersMapper sysLetterMapper;

    //查询合同的数量,总金额,已回款,待回款
    @Override
    public List<SysLetters> selectSumContract(SysLetters sysLetters) {
        return  sysLetterMapper.selectSumContract(sysLetters);

    }

    @Override
    public List<SysLetters> selectSumTack(SysLetters sysLetters) {
        return  sysLetterMapper.selectSumContract(sysLetters);

    }

    @Override
    public List<SysLetters> selectTotal(SysLetters sysLetters) {
        return  sysLetterMapper.selectTotal(sysLetters);

    }

}

package com.chinasoft.service.impl;

import com.chinasoft.mybatis.dao.SysSpendingMapper;
import com.chinasoft.mybatis.entity.SysSpending;
import com.chinasoft.service.SysSpendingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysSpendingServiceImpl extends BaseServiceImpl<SysSpending> implements SysSpendingService{

    @Autowired
    private SysSpendingMapper sysSpendingMapper;

    /**
     * 查询各项支出金额
     * @param sysSpending
     * @return
     */
    @Override
    public List<SysSpending> selectSumTack(SysSpending sysSpending) {
        return sysSpendingMapper.selectSumTack(sysSpending);
    }

    /**
     * 查询总支出金额
     * @return
     */
    @Override
    public Double selectTotal( ) {
        return sysSpendingMapper.selectTotal();
    }

    /**
     * 查询资金流水
     * @return
     */
    @Override
    public Double selectRunn() {
        return sysSpendingMapper.selectRunn();
    }
}

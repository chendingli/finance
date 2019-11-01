package com.chinasoft.service.impl;

import com.chinasoft.mybatis.dao.SysBackMoneyMapper;
import com.chinasoft.mybatis.entity.SysBackMoney;
import com.chinasoft.service.SysBackMoneyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Service
@Transactional
public class SysBackMoneyServiceImpl extends BaseServiceImpl<SysBackMoney> implements SysBackMoneyService {

    @Autowired
    private SysBackMoneyMapper sysBackMoneyMapper;

    @Override
    public PageInfo<SysBackMoney> findPage(Integer pageNum, Integer pageSize,SysBackMoney sysBackMoney) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo(sysBackMoneyMapper.selectAllBackMoneyInfo(sysBackMoney.getContractName(),
                sysBackMoney.getInvoiceState(),sysBackMoney.getBackMoneyState()));
    }

    @Override
    public List<SysBackMoney> selectBackMoneyByNo(Integer number) {
        return sysBackMoneyMapper.selectBackMoneyByNo(number);
    }

    @Override
    public void updateBack(Integer number) {
        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        sysBackMoneyMapper.updateBack(number,sqlDate);
    }

    @Override
    public void updateInvoice(String address, Integer number) {
        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        sysBackMoneyMapper.updateInvoice(address,number,sqlDate);
    }

}

package com.chinasoft.service.impl;

import com.chinasoft.mybatis.dao.SysContractInfoMapper;
import com.chinasoft.mybatis.dao.SysContractMapper;
import com.chinasoft.mybatis.entity.SysContract;
import com.chinasoft.mybatis.entity.SysContractInfo;
import com.chinasoft.service.SysContractInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * Name:WangJinhui
 * Date:2018-12-10
 */
@Service
@Transactional
public class SysContractInfoServiceImpl extends BaseServiceImpl<SysContractInfo> implements SysContractInfoService {

    @Autowired
    private SysContractInfoMapper sysContractInfoMapper;

    @Autowired
    private SysContractMapper sysContractMapper;

    /**
     * 分页查询合同项
     * @param pageNum
     * @param pageSize
     * @param sysContractInfo
     * @return
     */
    @Override
    public PageInfo<SysContractInfo> findPage(Integer pageNum, Integer pageSize, SysContractInfo sysContractInfo) {
        PageHelper.startPage(pageNum, pageSize);

        return new PageInfo(sysContractInfoMapper.selectContractInfo(sysContractInfo));
    }

    @Override
    public Integer getNumber() {
        return sysContractInfoMapper.getNumber();
    }

    /**
     * 新增合同
     * @param sysContract
     */
    @Override
    public void addContract(SysContract sysContract) {
        sysContractMapper.insert(sysContract);
    }
    /**
     * 往回款表写数据
     * @return
     */
    @Override
    public void addPeriod(Integer number, Integer contractNo, Double backMoney, Date estimateTime, Integer period) {
        sysContractInfoMapper.addPeriod(number,contractNo,backMoney,estimateTime,period);
    }
    /**
     * 查询回款表最大回款期数
     * @return
     */
    @Override
    public Integer findMaxNo() {
        return sysContractInfoMapper.findMaxNo();
    }









}

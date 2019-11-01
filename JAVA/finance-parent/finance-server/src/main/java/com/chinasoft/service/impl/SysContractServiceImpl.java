package com.chinasoft.service.impl;

import com.chinasoft.mybatis.dao.SysContractMapper;
import com.chinasoft.mybatis.entity.SysContract;
import com.chinasoft.service.SysContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Name:WangJinhui
 * Date:2018-12-10
 */
@Service
public class SysContractServiceImpl extends BaseServiceImpl<SysContract> implements SysContractService {

    @Autowired
    private SysContractMapper sysContractMapper;

    /**
     * 根据合同编号查询合同信息
     * @param id
     * @return
     */
    @Override
    public SysContract getContractInfo(Long id) {
        return sysContractMapper.selectByPrimaryKey(id);

    }
}

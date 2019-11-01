package com.chinasoft.service.impl;

import com.chinasoft.mybatis.dao.SysClientMapper;
import com.chinasoft.mybatis.entity.SysClient;
import com.chinasoft.service.SysClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Name:WangJinhui
 * Date:2018-12-10
 */
@Service
@Transactional
public class SysClientServiceImpl extends BaseServiceImpl<SysClient> implements SysClientService {

    @Autowired
    private SysClientMapper sysClientMapper;


    /**
     * 查询所有客户
     * @param name
     * @return
     */
    @Override
    public List<SysClient> selectSysClientName(String name) {
        return sysClientMapper.selectSysClientName(name);
    }




}

package com.chinasoft.service.impl;

import com.chinasoft.mybatis.dao.SysClientInfoMapper;
import com.chinasoft.mybatis.entity.SysClientInfo;
import com.chinasoft.service.SysClientInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Name:WangJinhui
 * Date:2018-12-10
 */
@Service
public class SysClientInfoServiceImpl extends BaseServiceImpl<SysClientInfo> implements SysClientInfoService  {


    @Autowired
    private SysClientInfoMapper sysClientInfoMapper;

    /**
     *
     * @param pageNum
     * @param pageSize
     * @param clientInfo  需要模糊查询时前台传入的查询条件
     * @return
     */
    @Override
    public PageInfo<SysClientInfo> findPage(Integer pageNum, Integer pageSize, SysClientInfo clientInfo) {


        PageHelper.startPage(pageNum, pageSize);

        return new PageInfo(sysClientInfoMapper.selectClientInfo(clientInfo));
    }
}

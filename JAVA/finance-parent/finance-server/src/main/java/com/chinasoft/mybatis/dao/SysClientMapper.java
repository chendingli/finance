package com.chinasoft.mybatis.dao;

import com.chinasoft.mybatis.entity.SysClient;
import com.chinasoft.util.MyMapper;

import java.util.List;

public interface SysClientMapper extends MyMapper<SysClient> {
    int insertSysClient(SysClient sysClient);

    List<SysClient> selectSysClientName(String name);

}

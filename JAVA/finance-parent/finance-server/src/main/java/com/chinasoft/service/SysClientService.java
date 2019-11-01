package com.chinasoft.service;

import com.chinasoft.mybatis.entity.SysClient;

import java.util.List;

/**
 * Name:WangJinhui
 * Date:2018-12-10
 */
public interface SysClientService extends BaseService<SysClient> {
    List<SysClient> selectSysClientName(String name);
}

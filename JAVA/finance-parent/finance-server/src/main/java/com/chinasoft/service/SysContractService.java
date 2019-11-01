package com.chinasoft.service;

import com.chinasoft.mybatis.entity.SysContract;
import com.chinasoft.mybatis.entity.SysContractInfo;
/**
 * Name:WangJinhui
 * Date:2018-12-10
 */
public interface SysContractService extends BaseService<SysContract> {


    SysContract getContractInfo(Long id);
}

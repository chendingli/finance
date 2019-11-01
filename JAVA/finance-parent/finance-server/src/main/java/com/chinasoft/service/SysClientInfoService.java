package com.chinasoft.service;

import com.chinasoft.mybatis.entity.SysClientInfo;
import com.github.pagehelper.PageInfo;
/**
 * Name:WangJinhui
 * Date:2018-12-10
 */
public interface SysClientInfoService extends BaseService<SysClientInfo> {

    PageInfo<SysClientInfo> findPage(Integer pageNum, Integer pageSize, SysClientInfo clientInfo);


}

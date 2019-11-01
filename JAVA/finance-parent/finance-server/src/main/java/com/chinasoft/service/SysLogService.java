package com.chinasoft.service;

import com.chinasoft.mybatis.entity.SysLog;
import com.github.pagehelper.PageInfo;

/**
 * @Auther: 汪毅
 * @Date: 2018/6/9 15:57
 * @Description:
 */
public interface SysLogService extends BaseService<SysLog> {

    SysLog get(Long id);

    PageInfo<SysLog> findPage(Integer pageNum, Integer pageSize, SysLog log);
}

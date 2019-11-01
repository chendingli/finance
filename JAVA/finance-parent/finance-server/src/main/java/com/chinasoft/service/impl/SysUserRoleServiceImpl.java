package com.chinasoft.service.impl;

import com.chinasoft.mybatis.dao.SysUserRoleMapper;
import com.chinasoft.mybatis.entity.SysUserRole;
import com.chinasoft.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by SHANGMAI on 2018/5/12.
 */
@Service
@Transactional
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRole> implements SysUserRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
}

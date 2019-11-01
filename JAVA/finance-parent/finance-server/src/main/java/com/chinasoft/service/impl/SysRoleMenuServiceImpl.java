package com.chinasoft.service.impl;

import com.chinasoft.mybatis.dao.SysRoleMenuMapper;
import com.chinasoft.mybatis.entity.SysRoleMenu;
import com.chinasoft.service.SysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by SHANGMAI on 2018/5/12.
 */
@Service
@Transactional
public class SysRoleMenuServiceImpl extends BaseServiceImpl<SysRoleMenu> implements SysRoleMenuService {

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;
}

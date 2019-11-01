package com.chinasoft.service;

import com.chinasoft.mybatis.entity.SysMenu;
import com.chinasoft.mybatis.entity.SysUser;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by SHANGMAI on 2018/5/12.
 */
public interface SysUserService extends BaseService<SysUser> {

    List<String> getRoleNames(Long id);

    List<String> getPermissions(Long id);

    SysUser getUser(Long userId);

    List<SysMenu> getMenuList(Long userId);

    PageInfo<SysUser> findPage(Integer pageNum, Integer pageSize, SysUser user);

    SysUser get(Long id);

    void insertUser(SysUser user);

    void updateUser(SysUser sysUser);
}

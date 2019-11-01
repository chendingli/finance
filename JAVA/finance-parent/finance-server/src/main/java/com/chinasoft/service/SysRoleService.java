package com.chinasoft.service;

import com.chinasoft.mybatis.entity.SysRole;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by SHANGMAI on 2018/5/12.
 */
public interface SysRoleService extends BaseService<SysRole> {

    PageInfo<SysRole> findPage(Integer pageNum, Integer pageSize, SysRole role);

    SysRole get(Long id);

    void deleteById(Long id);

    void insertRole(SysRole role);

    void updateRole(SysRole role);

    List<SysRole> selectRoleByUserId(Long id);

    Boolean checkUserBind(Long id);
}

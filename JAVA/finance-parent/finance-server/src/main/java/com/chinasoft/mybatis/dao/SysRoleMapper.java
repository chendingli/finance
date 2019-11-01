package com.chinasoft.mybatis.dao;

import com.chinasoft.mybatis.entity.SysMenu;
import com.chinasoft.mybatis.entity.SysRole;
import com.chinasoft.util.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysRoleMapper extends MyMapper<SysRole> {
    List<SysMenu> selectMenuByRoleId(@Param("id") Long id);
}
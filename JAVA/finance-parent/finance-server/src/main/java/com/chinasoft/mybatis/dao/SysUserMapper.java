package com.chinasoft.mybatis.dao;

import com.chinasoft.mybatis.entity.SysMenu;
import com.chinasoft.mybatis.entity.SysRole;
import com.chinasoft.mybatis.entity.SysUser;
import com.chinasoft.util.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserMapper extends MyMapper<SysUser> {

    List<String> selectRoleNamesByUserId(@Param("userId") Long userId, @Param("adminFlag") Integer adminFlag);

    List<String> selectPermissionsByUserId(@Param("userId") Long userId, @Param("adminFlag") Integer adminFlag);

    /**
     * 根据用户ID,获取目录菜单
     * @param userId
     * @return
     */
    List<SysMenu> selectMenuListByUserId(@Param("userId") Long userId, @Param("type") Integer type, @Param("adminFlag") Integer adminFlag);

    List<SysRole> selectRoleByUserId(@Param("userId") Long userId, @Param("adminFlag") Integer adminFlag);

    List<SysMenu> selectMenuTreeList(@Param("type") Integer[] type, @Param("userId") Long userId, @Param("adminFlag") Integer adminFlag);

    List<SysMenu> selectChildrenMenuListByUserId(@Param("userId") Long userId, @Param("parentId") Long parentId, @Param("type") Integer type, @Param("adminFlag") Integer adminFlag);

    List<SysUser> selectUserByRoleId(@Param("roleId") Long roleId);
}
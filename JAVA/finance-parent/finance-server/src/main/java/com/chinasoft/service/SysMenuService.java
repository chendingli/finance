package com.chinasoft.service;

import com.chinasoft.mybatis.entity.SysMenu;

import java.util.List;

/**
 * Created by SHANGMAI on 2018/5/12.
 */
public interface SysMenuService extends BaseService<SysMenu> {

    List<SysMenu> selectMenuList();

    List<SysMenu> selectMenuTreeList(Integer[] type, Long userId);

    void deleteMenuById(Long id);

    SysMenu getMenuById(Long id);
}

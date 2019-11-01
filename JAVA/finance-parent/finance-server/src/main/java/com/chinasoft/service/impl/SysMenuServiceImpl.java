package com.chinasoft.service.impl;

import com.chinasoft.mybatis.dao.SysMenuMapper;
import com.chinasoft.mybatis.dao.SysUserMapper;
import com.chinasoft.mybatis.entity.SysMenu;
import com.chinasoft.mybatis.entity.SysUser;
import com.chinasoft.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by SHANGMAI on 2018/5/12.
 */
@Service
@Transactional
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenu> implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public List<SysMenu> selectMenuList() {

        // 获取所有的目录菜单
        Example example = new Example(SysMenu.class);
        example.setOrderByClause("sort asc");
        example.createCriteria()
                .andEqualTo("delFlag", 1)
                .andEqualTo("type", 1);
        List<SysMenu> dirMenuList = sysMenuMapper.selectByExample(example);

        // 查询所有的链接菜单
        for (SysMenu dirMenu : dirMenuList) {
            example = new Example(SysMenu.class);
            example.setOrderByClause("sort asc");
            example.createCriteria()
                    .andEqualTo("delFlag", 1)
                    .andEqualTo("type", 2)
                    .andEqualTo("parentId", dirMenu.getId());
            List<SysMenu> urlMenuList = sysMenuMapper.selectByExample(example);
            dirMenu.setChildren(urlMenuList);

            // 查询所有的按钮菜单
            for (SysMenu urlMenu : urlMenuList) {
                example = new Example(SysMenu.class);
                example.setOrderByClause("sort asc");
                example.createCriteria()
                        .andEqualTo("delFlag", 1)
                        .andEqualTo("type", 3)
                        .andEqualTo("parentId", urlMenu.getId());
                List<SysMenu> btnMenuList = sysMenuMapper.selectByExample(example);
                urlMenu.setChildren(btnMenuList);
            }
        }

        return dirMenuList;
    }

    @Override
    public List<SysMenu> selectMenuTreeList(Integer[] type, Long userId) {
        SysUser user = sysUserMapper.selectByPrimaryKey(userId);
        List<SysMenu> menuList = sysUserMapper.selectMenuTreeList(type, userId, user.getAdminFlag());
        Set<SysMenu> menuSet = new LinkedHashSet<>();
        menuSet.addAll(menuList);
        menuList.clear();
        menuList.addAll(menuSet);
        return menuList;
    }

    @Override
    public void deleteMenuById(Long id) {

        // 删除自己
        SysMenu sysMenu = sysMenuMapper.selectByPrimaryKey(id);
        sysMenu.setDelFlag(0);
        sysMenuMapper.updateByPrimaryKeySelective(sysMenu);

        // 删除所有的下级菜单
        Example example = new Example(SysMenu.class);
        example.createCriteria().andLike("parentIds", sysMenu.getParentIds()+","+id+"%");
        SysMenu menu = new SysMenu();
        menu.setDelFlag(0);
        sysMenuMapper.updateByExampleSelective(menu,example);
    }

    @Override
    public SysMenu getMenuById(Long id) {

        SysMenu menu = sysMenuMapper.selectByPrimaryKey(id);

        SysMenu parent = sysMenuMapper.selectByPrimaryKey(menu.getParentId());

        menu.setParent(parent);

        return menu;
    }
}

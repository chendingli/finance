package com.chinasoft.service.impl;

import com.chinasoft.mybatis.dao.SysRoleMapper;
import com.chinasoft.mybatis.dao.SysRoleMenuMapper;
import com.chinasoft.mybatis.dao.SysUserMapper;
import com.chinasoft.mybatis.entity.SysMenu;
import com.chinasoft.mybatis.entity.SysRole;
import com.chinasoft.mybatis.entity.SysRoleMenu;
import com.chinasoft.mybatis.entity.SysUser;
import com.chinasoft.service.SysRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * Created by SHANGMAI on 2018/5/12.
 */
@Service
@Transactional
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole> implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public PageInfo<SysRole> findPage(Integer pageNum, Integer pageSize, SysRole role) {
        PageHelper.startPage(pageNum,pageSize);

        Example example = new Example(SysRole.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(role.getName())) { // 关键字查询
            criteria.orLike("name", "%" + role.getName() + "%");
            criteria.orLike("description", "%" + role.getName() + "%");
        }

        Example.Criteria andCriteria = example.createCriteria();
        andCriteria.andEqualTo("delFlag", 1);
        example.and(andCriteria);

        PageInfo<SysRole> pageInfo = new PageInfo<>(sysRoleMapper.selectByExample(example));

        return pageInfo;
    }

    @Override
    public SysRole get(Long id) {

        SysRole sysRole = sysRoleMapper.selectByPrimaryKey(id);

        List<SysMenu> menuList = sysRoleMapper.selectMenuByRoleId(id);

        sysRole.getMenuList().addAll(menuList);

        return sysRole;
    }

    @Override
    public void deleteById(Long id) {

        SysRole sysRole = new SysRole();
        sysRole.setId(id);
        sysRole.setDelFlag(0);
        sysRoleMapper.updateByPrimaryKeySelective(sysRole);

    }

    @Override
    public void insertRole(SysRole role) {

        // 保存角色
        sysRoleMapper.insertSelective(role);

        // 获取角色拥有的菜单ID
        List<Long> menuIdList = new ArrayList<>();
        String menuIds = role.getMenuIds();
        if (StringUtils.isNotBlank(menuIds)) {
            String[] list = menuIds.split(",");
            Arrays.asList(list).forEach(menuId -> {
                menuIdList.add(Long.parseLong(menuId));
            });
        }

        // 保存角色与菜单的关系
        for (Long menuId : menuIdList) {
            SysRoleMenu roleMenu = new SysRoleMenu();
            roleMenu.setMenuId(menuId);
            roleMenu.setRoleId(role.getId());
            sysRoleMenuMapper.insertSelective(roleMenu);
        }

    }

    @Override
    public void updateRole(SysRole role) {

        // 保存角色
        sysRoleMapper.updateByPrimaryKeySelective(role);

        // 先删除角色菜单关系
        Example example = new Example(SysRoleMenu.class);
        example.createCriteria().andEqualTo("roleId", role.getId());
        sysRoleMenuMapper.deleteByExample(example);

        // 获取角色拥有的菜单ID
        List<Long> menuIdList = new ArrayList<>();
        String menuIds = role.getMenuIds();
        if (StringUtils.isNotBlank(menuIds)) {
            String[] list = menuIds.split(",");
            Arrays.asList(list).forEach(menuId -> {
                menuIdList.add(Long.parseLong(menuId));
            });
        }

        // 保存角色与菜单的关系
        for (Long menuId : menuIdList) {
            SysRoleMenu roleMenu = new SysRoleMenu();
            roleMenu.setMenuId(menuId);
            roleMenu.setRoleId(role.getId());
            sysRoleMenuMapper.insertSelective(roleMenu);
        }

    }

    @Override
    public List<SysRole> selectRoleByUserId(Long id) {
        SysUser user = sysUserMapper.selectByPrimaryKey(id);
        List<SysRole> roleList = sysUserMapper.selectRoleByUserId(id, user.getAdminFlag());
        Set<SysRole> sysRoleSet = new LinkedHashSet<>();
        sysRoleSet.addAll(roleList);
        roleList.clear();
        roleList.addAll(sysRoleSet);
        return roleList;
    }

    @Override
    public Boolean checkUserBind(Long id) {
        List<SysUser> userList = sysUserMapper.selectUserByRoleId(id);
        if (CollectionUtils.isEmpty(userList)) {
            return false;
        } else {
            return true;
        }
    }

}

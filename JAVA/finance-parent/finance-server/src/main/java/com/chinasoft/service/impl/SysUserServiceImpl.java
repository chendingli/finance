package com.chinasoft.service.impl;

import com.chinasoft.mybatis.dao.SysUserMapper;
import com.chinasoft.mybatis.dao.SysUserRoleMapper;
import com.chinasoft.mybatis.entity.SysMenu;
import com.chinasoft.mybatis.entity.SysRole;
import com.chinasoft.mybatis.entity.SysUser;
import com.chinasoft.mybatis.entity.SysUserRole;
import com.chinasoft.service.SysUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by SHANGMAI on 2018/5/12.
 */
@Service
@Transactional
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    /**
     * 获取用户所有的角色名
     * @param userId
     * @return
     */
    public List<String> getRoleNames(Long userId) {
        SysUser user = getUser(userId);
        return sysUserMapper.selectRoleNamesByUserId(userId,user.getAdminFlag());
    }

    /**
     * 获取用户所有的权限串
     * @param userId
     * @return
     */
    public List<String> getPermissions(Long userId) {
        SysUser user = getUser(userId);
        return sysUserMapper.selectPermissionsByUserId(userId,user.getAdminFlag());
    }

    public SysUser getUser(Long userId) {
        return sysUserMapper.selectByPrimaryKey(userId);
    }

    @Override
    public List<SysMenu> getMenuList(Long userId) {

        SysUser user = getUser(userId);

        // 查询所有的目录菜单
        List<SysMenu> dirMenuList = sysUserMapper.selectMenuListByUserId(userId, 1, user.getAdminFlag());

        // 查询所有的链接菜单
        for (SysMenu sysMenu : dirMenuList) {
            sysMenu.setChildren(sysUserMapper.selectChildrenMenuListByUserId(userId, sysMenu.getId(), 2, user.getAdminFlag()));
        }

        return dirMenuList;
    }

    @Override
    public PageInfo<SysUser> findPage(Integer pageNum, Integer pageSize, SysUser user) {
        PageHelper.startPage(pageNum, pageSize);

        Example example = new Example(SysUser.class);

        Example.Criteria orCriteria = example.createCriteria();
        if (!StringUtils.isEmpty(user.getUsername())) {
            orCriteria.orLike("username", "%" + user.getUsername() + "%");
            orCriteria.orLike("name", "%" + user.getUsername() + "%");
            orCriteria.orLike("phone", "%" + user.getUsername() + "%");
            orCriteria.orLike("email", "%" + user.getUsername() + "%");
        }

        Example.Criteria andCriteria = example.createCriteria();
        andCriteria.andEqualTo("delFlag", 1);

        example.and(andCriteria);
        return new PageInfo(sysUserMapper.selectByExample(example));
    }

    @Override
    public SysUser get(Long id) {

        SysUser sysUser = sysUserMapper.selectByPrimaryKey(id);

        List<SysRole> roleList = sysUserMapper.selectRoleByUserId(id, sysUser.getAdminFlag());

        sysUser.getRoleList().addAll(roleList);

        return sysUser;
    }

    @Override
    public void insertUser(SysUser user) {

        // 保存用户信息
        sysUserMapper.insertSelective(user);

        // 保存用户角色关系
        List<Long> roleIdList = new ArrayList<>();
        String roleIds = user.getRoleIds();
        if (StringUtils.isNotBlank(roleIds)) {
            String[] list = roleIds.split(",");
            Arrays.asList(list).forEach(roleId -> {
                roleIdList.add(Long.parseLong(roleId));
            });
        }
        // 保存用户与角色的关系
        for (Long roleId : roleIdList) {
            SysUserRole userRole = new SysUserRole();
            userRole.setRoleId(roleId);
            userRole.setUserId(user.getId());
            sysUserRoleMapper.insertSelective(userRole);
        }

    }

    @Override
    public void updateUser(SysUser user) {

        // 保存用户信息
        sysUserMapper.updateByPrimaryKeySelective(user);

        // 删除原来的关系
        Example example = new Example(SysUserRole.class);
        example.createCriteria().andEqualTo("userId", user.getId());
        sysUserRoleMapper.deleteByExample(example);

        // 保存用户角色关系
        List<Long> roleIdList = new ArrayList<>();
        String roleIds = user.getRoleIds();
        if (StringUtils.isNotBlank(roleIds)) {
            String[] list = roleIds.split(",");
            Arrays.asList(list).forEach(roleId -> {
                roleIdList.add(Long.parseLong(roleId));
            });
        }

        // 保存用户与角色的关系
        for (Long roleId : roleIdList) {
            SysUserRole userRole = new SysUserRole();
            userRole.setRoleId(roleId);
            userRole.setUserId(user.getId());
            sysUserRoleMapper.insertSelective(userRole);
        }
    }

}

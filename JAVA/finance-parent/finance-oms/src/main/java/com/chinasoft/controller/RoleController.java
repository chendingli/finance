package com.chinasoft.controller;

import com.chinasoft.annotation.CurrentUser;
import com.chinasoft.annotation.MethodDesc;
import com.chinasoft.mybatis.entity.SysRole;
import com.chinasoft.mybatis.entity.SysUser;
import com.chinasoft.service.SysRoleService;
import com.chinasoft.vo.output.PageResult;
import com.chinasoft.vo.output.ResultObject;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: 汪毅
 * @Date: 2018/6/6 10:03
 * @Description:
 */
@RestController
@RequestMapping("/api/oms/role")
public class RoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @MethodDesc(value = "查询角色列表", type = MethodDesc.Type.VIEW)
    @RequiresPermissions("role:view")
    @RequestMapping("/list")
    public PageResult list(@RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "10")Integer pageSize,
                           SysRole role) {

        PageInfo<SysRole> pageInfo = sysRoleService.findPage(pageNum, pageSize, role);

        return new PageResult(pageInfo.getTotal(),pageInfo.getList());
    }

    /**
     * 获取当前用户拥有的角色列表
     * @param user
     * @return
     */
    @MethodDesc(value = "获取当前用户拥有的角色列表", type = MethodDesc.Type.VIEW)
    @RequestMapping("/listAll")
    public ResultObject listAll(@CurrentUser SysUser user) {

        List<SysRole> roleList = sysRoleService.selectRoleByUserId(user.getId());

        return new ResultObject(roleList);
    }

    @MethodDesc(value = "获取指定角色", type = MethodDesc.Type.VIEW)
    @RequiresPermissions("role:view")
    @RequestMapping("/get")
    public ResultObject get(Long id) {
        SysRole role = sysRoleService.get(id);
        return new ResultObject(role);
    }

    @MethodDesc(value = "添加角色", type = MethodDesc.Type.ADD)
    @RequiresPermissions("role:add")
    @RequestMapping("/add")
    public ResultObject add(SysRole role) {

        SysRole sysRole = new SysRole();
        sysRole.setName(role.getName());
        sysRole.setDelFlag(1);
        sysRole = sysRoleService.selectOne(sysRole);

        if (null != sysRole) {
            return new ResultObject("-1", "该角色名已存在",null);
        }

        sysRoleService.insertRole(role);

        return new ResultObject();
    }

    @MethodDesc(value = "删除角色", type = MethodDesc.Type.DELETE)
    @RequiresPermissions("role:delete")
    @RequestMapping("/delete")
    public ResultObject delete(Long id) {

        // 如果角色已经绑定了用户则该角色不能删除
        if (sysRoleService.checkUserBind(id)){
            return new ResultObject("-1", "该角色已经分配给了用户，请先进行解绑",null);
        }

        sysRoleService.deleteById(id);

        return new ResultObject();

    }

    @MethodDesc(value = "更新角色", type = MethodDesc.Type.UPDATE)
    @RequiresPermissions("role:update")
    @RequestMapping("/update")
    public ResultObject update(SysRole role) {

        SysRole sysRole = new SysRole();
        sysRole.setName(role.getName());
        sysRole.setDelFlag(1);
        sysRole = sysRoleService.selectOne(sysRole);

        if (null != sysRole && !sysRole.getId().equals(role.getId())) {
            return new ResultObject("-1", "该角色名已存在",null);
        }

        sysRoleService.updateRole(role);
        return new ResultObject();

    }
}

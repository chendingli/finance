package com.chinasoft.controller;

import com.alibaba.fastjson.JSONObject;
import com.chinasoft.annotation.CurrentUser;
import com.chinasoft.annotation.MethodDesc;
import com.chinasoft.mybatis.entity.SysMenu;
import com.chinasoft.mybatis.entity.SysUser;
import com.chinasoft.service.SysMenuService;
import com.chinasoft.vo.output.ResultObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: 汪毅
 * @Date: 2018/5/29 10:24
 * @Description:
 */
@RestController
@RequestMapping("/api/oms/menu")
public class MenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @MethodDesc(value = "获取指定菜单",type = MethodDesc.Type.VIEW)
    @RequiresPermissions("menu:view")
    @RequestMapping("/get")
    public ResultObject get(Long id) {
        SysMenu sysMenu = sysMenuService.getMenuById(id);
        return new ResultObject(sysMenu);
    }

    /**
     * 获取菜单列表
     * @return
     */
    @MethodDesc(value = "获取菜单列表", type = MethodDesc.Type.VIEW)
    @RequiresPermissions("menu:view")
    @RequestMapping("/list")
    public ResultObject list() {
        List<SysMenu> menuList = sysMenuService.selectMenuList();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("menuList", menuList);
        return new ResultObject(jsonObject);
    }

    /**
     * 获取菜单树列表
     * @param type 指定类型 1-目录，2-菜单，3-按钮
     * @return
     */
    @MethodDesc(value = "获取菜单树列表", type = MethodDesc.Type.VIEW)
    @RequestMapping("/tree")
    public ResultObject tree(Integer[] type, @CurrentUser SysUser user) {
        List<SysMenu> menuList = sysMenuService.selectMenuTreeList(type, user.getId());
        return new ResultObject(menuList);
    }

    /**
     * 添加菜单
     * @return
     */
    @MethodDesc(value = "添加菜单", type = MethodDesc.Type.ADD)
    @RequiresPermissions("menu:add")
    @RequestMapping("/add")
    public ResultObject add(@RequestBody SysMenu sysMenu) {

        // 设置上级菜单
        if (sysMenu.getParentId().equals(0l)) {
            sysMenu.setParentIds("0");
        } else {
            SysMenu parentMenu = sysMenuService.selectByPrimaryKey(sysMenu.getParentId());
            sysMenu.setParentIds(parentMenu.getParentIds() + "," + sysMenu.getParentId());
        }

        sysMenuService.insertSelective(sysMenu);
        return new ResultObject(null);
    }

    /**
     * 删除菜单
     * @return
     */
    @MethodDesc(value = "删除菜单", type = MethodDesc.Type.DELETE)
    @RequiresPermissions("menu:delete")
    @RequestMapping("/delete")
    public ResultObject delete(Long id) {

        // 删除当前菜单及其子菜单
        sysMenuService.deleteMenuById(id);

        return new ResultObject(null);
    }

    /**
     * 修改菜单
     * @return
     */
    @MethodDesc(value = "修改菜单", type = MethodDesc.Type.UPDATE)
    @RequiresPermissions("menu:update")
    @RequestMapping("/update")
    public ResultObject update(@RequestBody SysMenu sysMenu) {

        sysMenuService.updateByPrimaryKeySelective(sysMenu);

        return new ResultObject(null);
    }
}

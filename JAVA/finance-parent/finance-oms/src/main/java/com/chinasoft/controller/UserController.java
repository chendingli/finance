package com.chinasoft.controller;

import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SecureUtil;
import com.chinasoft.annotation.CurrentUser;
import com.chinasoft.annotation.MethodDesc;
import com.chinasoft.mybatis.entity.SysUser;
import com.chinasoft.service.SysUserService;
import com.chinasoft.util.encrytion.sha.ShaEncryptionUtil;
import com.chinasoft.vo.output.PageResult;
import com.chinasoft.vo.output.ResultObject;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: 汪毅
 * @Date: 2018/6/4 09:58
 * @Description:
 */
@RestController
@RequestMapping("/api/oms/user")
public class UserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 分页查询用户列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @MethodDesc(value = "分页查询用户列表", type = MethodDesc.Type.VIEW)
    @RequiresPermissions("user:view")
    @RequestMapping("/list")
    public PageResult list(@RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "10")Integer pageSize,
                           SysUser user) {

        PageInfo<SysUser> pageInfo = sysUserService.findPage(pageNum,pageSize,user);
        return new PageResult(pageInfo.getTotal(),pageInfo.getList());
    }

    /**
     * 用户添加
     * @param user
     * @return
     */
    @MethodDesc(value = "添加用户", type = MethodDesc.Type.ADD)
    @RequiresPermissions("user:add")
    @RequestMapping("/add")
    public ResultObject add(SysUser user) {

        SysUser sysUser = new SysUser();
        sysUser.setUsername(user.getUsername());
        sysUser.setDelFlag(1);
        sysUser = sysUserService.selectOne(sysUser);

        if (sysUser != null) {
            return new ResultObject("-1", "该用户名已存在",null);
        }

        user.setPassword(HexUtil.encodeHexStr(SecureUtil.hmacSha1(user.getUsername()).digest("123456"))); // 默认密码 123456
        sysUserService.insertUser(user);

        return new ResultObject();
    }

    /**
     * 获取用户
     * @param id
     * @return
     */
    @MethodDesc(value = "获取用户", type = MethodDesc.Type.VIEW)
    @RequiresPermissions("user:view")
    @RequestMapping("/get")
    public ResultObject get(Long id) {
        SysUser user = sysUserService.get(id);
        return new ResultObject(user);
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @MethodDesc(value = "删除用户", type = MethodDesc.Type.DELETE)
    @RequiresPermissions("user:delete")
    @RequestMapping("/delete")
    public ResultObject delete(Long id, @CurrentUser SysUser currentUser) {

        SysUser u = sysUserService.getUser(id);

        if (u.getAdminFlag().equals(1)) { // 超级管理员不能删除
            return new ResultObject("-1","超级管理员不能删除",null);
        }

        if (ObjectUtil.equal(id, currentUser.getId())) {
            return new ResultObject("-1","不可以删除自己",null);
        }

        SysUser sysUser = new SysUser();
        sysUser.setDelFlag(0);
        sysUser.setId(id);

        sysUserService.updateByPrimaryKeySelective(sysUser);
        return new ResultObject(null);
    }

    /**
     * 更新用户
     * @param user
     * @return
     */
    @MethodDesc(value = "更新用户", type = MethodDesc.Type.UPDATE)
    @RequiresPermissions("user:update")
    @RequestMapping("/update")
    public ResultObject update(SysUser user) {

        SysUser u = sysUserService.getUser(user.getId());

        if (u.getAdminFlag().equals(1)) { // 超级管理员不能修改
            return new ResultObject("-1","超级管理员不能修改",null);
        }

        SysUser sysUser = new SysUser();
        sysUser.setUsername(user.getUsername());
        sysUser.setDelFlag(1);
        sysUser = sysUserService.selectOne(sysUser);

        if (sysUser != null && !sysUser.getId().equals(user.getId())) {
            return new ResultObject("-1", "该用户名已存在",null);
        }

        sysUserService.updateUser(user);

        return new ResultObject(null);
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @MethodDesc(value = "修改个人信息", type = MethodDesc.Type.UPDATE)
    @RequestMapping("/updateUserInfo")
    public ResultObject updateUserInfo(SysUser user) {

        SysUser u = sysUserService.getUser(user.getId());

        /*if (u.getAdminFlag().equals(1)) { // 超级管理员不能修改
            return new ResultObject("-1","超级管理员不能修改",null);
        }*/

        // 修改密码
        if(StringUtils.isBlank(user.getPassword())){
           user.setPassword(null);
        } else {
            user.setPassword(HexUtil.encodeHexStr(SecureUtil.hmacSha1(user.getUsername()).digest(user.getPassword())));
        }
        sysUserService.updateByPrimaryKeySelective(user);

        return new ResultObject(null);
    }

    /**
     * 重置密码
     * @param user
     * @return
     */
    @MethodDesc(value="重置密码",type = MethodDesc.Type.UPDATE)
    @RequestMapping("/resetPassword")
    public ResultObject resetPassword(SysUser user){
        user.setPassword(HexUtil.encodeHexStr(SecureUtil.hmacSha1(user.getUsername()).digest("123456")));
        sysUserService.updateByPrimaryKeySelective(user);
        return new ResultObject(null);
    }

}

package com.chinasoft.controller;

import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSONObject;
import com.chinasoft.annotation.MethodDesc;
import com.chinasoft.auth.util.Constant;
import com.chinasoft.auth.util.JWTUtil;
import com.chinasoft.mybatis.entity.SysMenu;
import com.chinasoft.mybatis.entity.SysUser;
import com.chinasoft.service.SysUserService;
import com.chinasoft.util.code.StatusCode;
import com.chinasoft.vo.output.ResultObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/oms")
public class LoginController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @MethodDesc(value = "登录", type = MethodDesc.Type.OTHER)
    @RequestMapping("/login")
    public ResultObject login(@RequestBody Map<String,String> userVo) {

        ResultObject resultObject = ResultObject.ResultObjectBuilder.aResultObject()
                .withResultMsg("登录成功")
                .build();

        String username = userVo.get("username");
        String encodedPassword = HexUtil.encodeHexStr(SecureUtil.hmacSha1(username).digest(userVo.get("password")));

        SysUser user = new SysUser();
        user.setUsername(username);
        user.setDelFlag(1);
        user = sysUserService.selectOne(user);

        if (null == user) {
            resultObject.setResultMsg("用户不存在");
            resultObject.setResultCode(StatusCode.STATUS_USER_NOT_EXIST);
        } else if (!encodedPassword.equals(user.getPassword())) {
            resultObject.setResultMsg("密码错误");
            resultObject.setResultCode(StatusCode.STATUS_USER_PWD_INVALID);
        } else {
            Date expAt = new Date(System.currentTimeMillis() + JWTUtil.EXPIRE_TIME);
            String token = JWTUtil.sign(username, user.getPassword(), expAt);

            // 将 token 保存在redis中，可以实现主动让token失效，踢下线
            //redisTemplate.opsForValue().set(Constant.REDIS_SK_TOKEN + username, token);
            //redisTemplate.expireAt(Constant.REDIS_SK_TOKEN + username, expAt);

            // 查询用户菜单
            List<SysMenu> menuList = sysUserService.getMenuList(user.getId());

            // 查询用户角色
            List<String> roleList = sysUserService.getRoleNames(user.getId());

            // 查询用户权限
            List<String> permissionList = sysUserService.getPermissions(user.getId());

            JSONObject data = new JSONObject();
            data.put("accessToken", token);
            data.put("user", user);
            data.put("menuList", menuList);
            data.put("roleList",roleList);
            data.put("permissionList", permissionList);
            resultObject.setData(data);
        }

        return resultObject;
    }

}

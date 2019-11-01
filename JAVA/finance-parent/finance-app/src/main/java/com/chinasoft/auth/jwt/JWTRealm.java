package com.chinasoft.auth.jwt;

import com.chinasoft.auth.util.Constant;
import com.chinasoft.auth.util.JWTUtil;
import com.chinasoft.mybatis.entity.SysUser;
import com.chinasoft.service.SysUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SHANGMAI on 2018/5/12.
 */
public class JWTRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 根据身份标识获取权限信息
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        // 获取用户名
        String jwtToken = principals.getPrimaryPrincipal().toString();
        String username = JWTUtil.getUsername(jwtToken);

        SysUser user = new SysUser();
        user.setUsername(username);
        user.setDelFlag(1);
        user = sysUserService.selectOne(user);

        // 添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        // 查询用户的角色
        List<String> roles = sysUserService.getRoleNames(user.getId());
        simpleAuthorizationInfo.addRoles(CollectionUtils.isEmpty(roles) ? new ArrayList<String>() : roles);

        // 查询用户权限
        List<String> permissions = sysUserService.getPermissions(user.getId());
        simpleAuthorizationInfo.addStringPermissions(CollectionUtils.isEmpty(permissions) ? new ArrayList<String>() : permissions);

        return simpleAuthorizationInfo;
    }

    /**
     * 根据TOKEN获取认证信息
     * @param token
     * @return 如果返回null, 调用 subject.login(token) 会抛出 AuthenticationException， 表示认证失败
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        // 获取JWTToken
        String jwtToken = token.getCredentials().toString();

        // 从token中，解析出用户名
        String username = JWTUtil.getUsername(jwtToken);
        if (username == null) {
            throw new AuthenticationException("token 无效");
        }

        SysUser user = new SysUser();
        user.setUsername(username);
        user.setDelFlag(1);
        user = sysUserService.selectOne(user);
        if (user == null) {
            throw new AuthenticationException("用户不存在");
        }

        // 1. 验证 token 是否有效
        if (!JWTUtil.verify(jwtToken, username, user.getPassword())) {
            throw new AuthenticationException("token 无效");
        }

        // 2.redis中获取token是否存在，是否踢下线
        /*else {
            String redisToken = (String) redisTemplate.opsForValue().get(Constant.REDIS_SK_TOKEN + username);
            if (null == redisToken || !jwtToken.equals(redisToken)) {
                throw new AuthenticationException("token 无效");
            }
        }*/

        // 登录成功，将当前用户设置到 request 中
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        request.setAttribute(Constant.CURRENT_USER, user);

        return new SimpleAuthenticationInfo(jwtToken, jwtToken, getName());
    }
}

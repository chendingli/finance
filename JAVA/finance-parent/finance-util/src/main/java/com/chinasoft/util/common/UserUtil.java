package com.chinasoft.util.common;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: 汪毅
 * @Date: 2018/6/22 18:29
 * @Description:
 */
public class UserUtil {

    /**
     * 获取当前登录用户
     * @return
     */
    public static Object getCurrentUser() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getAttribute("CURRENT_USER");
    }
}

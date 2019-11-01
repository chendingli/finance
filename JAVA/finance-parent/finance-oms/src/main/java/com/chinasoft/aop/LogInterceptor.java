package com.chinasoft.aop;

import cn.hutool.core.util.StrUtil;
import com.chinasoft.annotation.MethodDesc;
import com.chinasoft.auth.util.Constant;
import com.chinasoft.mybatis.entity.SysLog;
import com.chinasoft.mybatis.entity.SysUser;
import com.chinasoft.task.InsertLogTask;
import com.chinasoft.util.common.IpUtil;
import com.chinasoft.util.common.ScheduleUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * 日志拦截器
 */
public class LogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        // 保存日志
        if(handler instanceof HandlerMethod) {
            HandlerMethod h = (HandlerMethod) handler;
            MethodDesc md = h.getMethodAnnotation(MethodDesc.class);
            if (null != md) { // 只有在方法上有 MethodDesc 注解，才会记录日志

                SysUser user = (SysUser) request.getAttribute(Constant.CURRENT_USER);

                SysLog log = new SysLog();
                log.setTitle(md.value());
                log.setType(md.type().getType());
                log.setRemoteAddr(IpUtil.getIpAddr(request));
                log.setUserAgent(request.getHeader("user-agent"));
                log.setRequestUri(request.getRequestURI());
                log.setParams(getParams(request));
                log.setMethod(request.getMethod());
                log.setCreateBy(user == null ? null : user.getUsername());
                log.setCreateTime(new Date());

                // 异步保存日志
                ScheduleUtil.schedule(new InsertLogTask(log), 0, TimeUnit.MILLISECONDS);

            }

        }
    }

    public static String getParams(HttpServletRequest request) {

        Map<String, String[]> paramMap = request.getParameterMap();

        if (null == paramMap){
            return null;
        }

        StringBuilder params = new StringBuilder();
        for (Map.Entry<String, String[]> param : (paramMap).entrySet()){
            params.append(("".equals(params.toString()) ? "" : "&") + param.getKey() + "=");
            String paramValue = (param.getValue() != null && param.getValue().length > 0 ? param.getValue()[0] : "");
            params.append(StrUtil.endWithIgnoreCase(param.getKey(), "password") ? "" : paramValue);
        }

        return params.toString();

    }

}
